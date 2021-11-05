import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Player {
    Scanner in;
    String m_name;
    boolean m_turn;
    int m_win;
    boolean m_exists;
    ArrayList<Piece> p= new ArrayList<>();
    Vector<String> players= new Vector<>();
    public Player(){
        in = new Scanner(System.in);
        System.out.println("What's your username? ");
        this.m_name=in.nextLine();
        for (int i = 0; i < players.size(); i++) {
            if (players.contains(this.m_name)) {
                this.m_exists = true;
            }
        }
        save_vector(this.players);
    }

    public String getM_name() {
        return m_name;
    }
    public void setM_name(String name) {
        this.m_name = name;
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

    void save_vector(Vector<String> profile) {
        String name="";
        //Vector<String> temp_profile= new Vector<>();
        //boolean exists = false;
        int i;
        //Create file for players profile
        try {
            File myF = new File("profile.txt");
            if (myF.createNewFile())
                System.out.println("File" + myF.getName() + "created");
            else
                System.out.println("File already exists");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //Write in file
        try {
            FileWriter myW = new FileWriter("profile.txt");
            if (this.m_exists == true) {
                System.out.println("Profile name already exists sorry");
            }
            //we put everything to play here
            else {
                profile.add(name);

            }
            myW.write(name);
            myW.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //Read txt
        //FileReader reader = null;
        try {
            // create a reader
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));

            // read until end of file
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            // close the reader
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        /*String doc = "C:\\Java RUC\\SD\\Chess-AI\\com\\company\\player.txt";
        Path fp = Paths.get(doc);
        List<String> lines=Files.readAllLines(fp);
        //int i;
        lines.forEach(line -> {
            // split each line by an arbitrary number of whitespaces
            String[] lineValues = line.split("\\s+");
            // and do what you want with the results, e.g. create an edge of the graph
            /*lineValues[0]: name*/
            /*lineValues[1]): psw*/
        /*});
        while (i < lines.size()) {

        }*/
    }

        void edit_p(){
        in = new Scanner(System.in);
        Vector <String> temp_profile;
        String profile;
        System.out.println("Whats your name? ");
        profile=in.nextLine();

        boolean exists= false;

        System.out.println("What's your ");
        /*fonction pour remplir vecteur*/

    }

   /* boolean chargePlayer(){
        boolean exists = false;
        String doc = "C:\\Java RUC\\SD\\Chess-AI\\com\\company\\player.txt";
        Path fp= Paths.get(doc);
        List<String> lines;
        return exists;
    }*/

    void backup(){

    }

}
