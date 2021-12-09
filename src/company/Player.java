package company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Player extends JPanel{
    Scanner in;
    String m_name, m_password, m_pointsString;
    int m_points;
    boolean m_turn, m_win, m_help=true;
    boolean m_color;
    boolean m_exists=false, m_c=false;
    Vector<String> players= new Vector<>();

    //In the constructor we make our first choice
    public Player(JFrame frame){
        in = new Scanner(System.in);

        //JFrame function: does not work for some reason
        //paint(frame);

        String choice;
        boolean menu=true;

        //Menu from which you decide to load or create a new player
        while (menu){
            System.out.println("If you want to create a new user tap 1 if you want to access an existing one click 2?");
            choice=in.nextLine();
            switch (choice) {
                case "1": {
                    this.m_c = true;
                    upload();
                    menu = false;
                    break;
                }
                case "2": {
                    this.m_c = true;
                    collect();
                    menu = false;
                    break;
                }
                default:
                    System.out.println("You didn't click the correct number, try again");
                    break;
            }
        }
    }

    //Methode that calls upon all the necessary ones to create a new player
    void upload(){
        if(this.m_c) {
            System.out.println("What's your username? ");
            m_name = in.nextLine();
        }
        //if the players name isn't already in our vector we add the name to the profile text file and then
            if (!this.m_exists) {
                CreateFile();
                ReadFile();
                WriteFile();
                //we create a text file named after the player where we store the password information as well as his score
                if (this.m_help) {
                    System.out.println("Nice you now have an account and your Username is: " + this.m_name);
                    CreateEachProfileFile();
                    this.m_help=false;
                }
            }
    }

    //Methode that calls upon all the necessary ones to upload an existing player
    void collect(){
        //Here we test if we already asked the name
        if(this.m_c){
            System.out.println("What's your username? ");
            m_name=in.nextLine();
        }
        CreateFile();
        ReadFile();
        //if the players name already exists
        if(this.m_exists){
            System.out.println("Nice you already have an account and your Username is: "+ this.m_name);
            //we open the players file and verify that he is indeed the owner of the account
            ReadEachFile(this.m_name+ ".txt");
            TestPsw();
        }
        //if it doesn't we ask again
        else {
            String choice;
            boolean menu=true;
            while (menu){
                System.out.println("Profile name doesn't exists sorry... Do you want to load an other one (tap 1) or create a new one with that name (tap 2) ?");
                choice=in.nextLine();
                switch (choice) {
                    case "1" : {
                        this.players.remove(this.m_name);
                        this.m_c = true;
                        this.m_exists = false;
                        collect();
                        menu = false;
                        break;
                    }
                    case "2" : {
                        this.m_exists = false;
                        this.players.remove(this.m_name); //we need to free the space in which this name was being stock otherwise it will think this username already exists
                        this.m_c = false; //this boolean must be false so that the name of the player isn't asked twice
                        upload();
                        menu = false;
                        break;
                    }
                    default:
                        System.out.println("You didn't click the correct number, try again");
                        break;
                }
            }

        }
    }

    void CreateFile(){
        //Create file for players profile if it doesn't already exist
        File myF = new File("profile.txt");
        try {
            if (myF.createNewFile())
                System.out.println("File " + myF.getName() + " created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void WriteFile(){
        try {
            //Write in file
            FileWriter myW = new FileWriter("profile.txt",true); //do not overwrite
            //if it already exists we go back to the menu option
            if (this.m_exists) {
                String choice;
                boolean menu=true;
                while (menu){
                    System.out.println("Profile name already exists sorry... Do you want to load that one (tap 1) or create a new one(tap 2)?");
                    choice=in.nextLine();
                    switch (choice) {
                        case "1": {
                            this.m_exists = true;
                            this.m_c = false;
                            this.m_help = false;
                            collect();
                            menu = false;
                            break;
                        }
                        case "2" : {
                            this.m_name = "";
                            this.m_exists = false;
                            upload();
                            menu = false;
                            break;
                        }
                        default:
                            System.out.println("You didn't click the correct number, try again");
                            break;
                    }
                }
            }
            //we write down the new username on the file
            else {
                myW.write(this.m_name+"\n");
            }
            myW.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void ReadFile(){
        try {
            //Read File
            BufferedReader br = new BufferedReader(new FileReader("profile.txt"));

            // read until end of file
            String line;
            while ((line = br.readLine()) != null) {
                //we add every name to the vector players
                //we look at this list of names to be sure it's a new name
                this.players.add(line);
                for (int i=0; i<this.players.size(); i++){
                    if (players.contains(this.m_name)) {
                        this.m_exists = true;
                        break;
                    }
                }
            }
            if(br.readLine() == null) {
                this.players.add(this.m_name);
            }
            // close the reader
            br.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    //Once you create a new user
    void CreateEachProfileFile(){
        String doc=this.m_name + ".txt";
        File EachFile= new File(doc);
        try {
            if (EachFile.createNewFile())
                System.out.println("File " + EachFile.getName() + " created");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ReadEachFile(doc);
        InitializeEachFile(doc);
    }

    void ReadEachFile(String doc) {
        Path filePath = Paths.get(doc);
        List<String> lines;
        try {
            lines = Files.readAllLines(filePath);
            lines.forEach(line -> {
                // split each line by an arbitrary number of whitespaces
                String[] lineValues = line.split("\\s+");
                // and do what you want with the results, e.g. create an edge of the graph
                this.m_password= lineValues[0];
                this.m_pointsString= lineValues[1];
                this.m_points=Integer.parseInt(this.m_pointsString);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void InitializeEachFile(String doc){
        try {
            FileWriter myW = new FileWriter(doc,true);
            System.out.println("Initialise your password: ");
            this.m_password=in.nextLine();
            this.m_points=0;
            myW.write(this.m_password + " " + this.m_points);
            myW.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void TestPsw() {
        String doc = this.m_name + ".txt";
        String test_password;
        boolean menu = true;
        int i = 0;
        System.out.println("What is your password? ");
        while (menu) {
            test_password = in.nextLine();
            if (this.m_password.equals(test_password)) {
                System.out.println("Hi " + this.m_name + " it's nice to see you again!\nYou currently have " + this.m_pointsString + " points\n\n");
                menu = false;
            } else {
                if(i<2)
                {
                    System.out.println("Incorrect password... Try again: ");
                    i++;
                }
                else if (i == 2) {
                    System.out.println("You have forgotten your password... ");
                    try {
                        FileWriter myW = new FileWriter(doc, false);
                        System.out.println("Choose a new password: ");
                        this.m_password = in.nextLine();
                        myW.write(this.m_password + " " + this.m_points);
                        myW.close();
                        menu = false;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //Use this once the game is over to update the point system
    void WriteEachFileWin(){
        try {
            String doc= this.m_name+ ".txt";
            FileWriter myW = new FileWriter(doc,false);
            if(m_win){
                ReadEachFile(doc);
                this.m_points+=10;
                myW.write(this.m_password + " " + this.m_points);
                myW.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void paint(JFrame frame){
        this.setVisible(true);
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel1.setBackground(new Color(239,223,187));
        gbc.gridwidth = GridBagConstraints.REMAINDER;


        JButton upload = new JButton("Access existing profile");
        upload.setBackground(new Color(59,47,47));
        upload.setForeground(new Color(239,223,187));

        JButton collect = new JButton("Create new profile");
        collect.setBackground(new Color(59,47,47));
        collect.setForeground(new Color(239,223,187));

        upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                upload();
            }
        });

        collect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                collect();
            }
        });

        panel1.add(upload, gbc);
        panel1.add(collect, gbc);
        frame.add(panel1);
    }


}
