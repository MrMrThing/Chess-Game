import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Player {
    Scanner in;
    String m_name, m_password;
    boolean m_turn;
    int m_win;
    public Player(String name, String password){
        this.m_name=name;
        this.m_password=password;
    }

    public String getM_name() {
        return m_name;
    }
    public void setM_name(String name) {
        this.m_name = name;
    }

    public String getM_password() {
        return m_password;
    }
    public void setM_password(String password) {
        this.m_password = password;
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
        Vector<String> temp_profile= new Vector<>();
        boolean exists = false;
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
            for (i = 0; i < temp_profile.size(); i++) {
                if (temp_profile.contains(name)) {
                    exists = true;
                }
            }
            if (exists == true) {
                System.out.println("Profile name already exists sorry");
            }
            //we put everything to play here
            else {
                temp_profile.add(name);

            }
            myW.write(name);
        } catch (IOException e) {
            System.out.println("An error occurred.");
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
