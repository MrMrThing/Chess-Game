import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class Player {
    Scanner in;
    String m_name;
    boolean m_turn;
    int m_win;
    boolean m_exists=false, m_c=false;
    boolean m_color = false;
    ArrayList<Piece> p= new ArrayList<>();
    Vector<String> players= new Vector<>();
    public Player(){
        in = new Scanner(System.in);
        String choice;
        boolean menu=true;
        while (menu == true){
            System.out.println("If you want to create a new user tap 1 if you want to access an existing one click 2?");
            choice=in.nextLine();
            switch (choice){
                case "1":
                    upload();
                    menu=false;
                    break;
                case "2":
                    this.m_c=true;
                    collect();
                    menu=false;
                    break;
                default:
                    menu=true;
                    System.out.println("You didn't click the correct number, try again");
                    break;
            }
        }
    }

    boolean getTurn(){
        return m_turn;
    }
    public void setM_turn(boolean turn) {
        this.m_turn = turn;
    }

    public int getM_win() {
        return m_win;
    }

    public void setM_win(int win) {
        this.m_win = win;
    }

    void upload(){
        System.out.println("What's your username? ");
        m_name=in.nextLine();
        save_vector();
    }

    void collect(){
        if(m_c==true){
            System.out.println("What's your username? ");
            m_name=in.nextLine();
        }
        CreateFile();
        ReadFile();
        if(this.m_exists==true){
            System.out.println("Nice you already have an account and your Username is: "+ this.m_name);
        }
        else {
            String choice;
            boolean menu=true;
            while (menu == true){
                System.out.println("Profile name doesn't exists sorry... Do you want to load an other one (tap 1) or create a new one (tap 2) ?");
                choice=in.nextLine();
                switch (choice){
                    case "1":
                        this.m_c=true;
                        collect();
                        menu=false;
                        break;
                    case "2":
                        this.m_name="";
                        this.m_exists=false;
                        upload();
                        menu=false;
                        break;
                    default:
                        menu=true;
                        System.out.println("You didn't click the correct number, try again");
                        break;
                }
            }

        }
    }
    void CreateFile(){
        //Create file for players profile
        File myF = new File("profile.txt");
        try {
            if (myF.createNewFile())
                System.out.println("File" + myF.getName() + "created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void WriteFile(){
        try {
            //Write in file
            FileWriter myW = new FileWriter("profile.txt",true);
            if (this.m_exists == true) {
                String choice;
                boolean menu=true;
                while (menu == true){
                    System.out.println("Profile name already exists sorry... Do you want to load that one (tap 1) or create a new one(tap 2)?");
                    choice=in.nextLine();
                    switch (choice){
                        case "1":
                            this.m_exists=false;
                            this.m_c=false;
                            collect();
                            menu=false;
                            break;
                        case "2":
                            this.m_name="";
                            this.m_exists=false;
                            upload();
                            menu=false;
                            break;
                        default:
                            menu=true;
                            System.out.println("You didn't click the correct number, try again");
                            break;
                    }
                }
            }
            //we put everything to play here
            else {
                myW.write(m_name+"\n");
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
                this.players.add(line);
                for (int i=0; i<this.players.size(); i++){
                    if (players.contains(this.m_name)) {
                        this.m_exists = true;
                    }
                }
            }
            if((line = br.readLine()) == null) {
                this.players.add(this.m_name);
            }
            // close the reader
            br.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    void save_vector() {
        CreateFile();
        ReadFile();
        WriteFile();
    }

}
