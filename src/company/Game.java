package company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Game extends JPanel{
    float m_round;
    boolean isOver;
    Player player2; //the one playing from up
    Player player; //the one playing from down
    public ArrayList<Point> m_positionsTaken = new ArrayList<>(); //all the positions taken arranged in a certain order
    public ArrayList<Piece> m_pieces = new ArrayList<>(); //all pieces on the board
    //when a piece is taken of the board, we just delete it from the arrayList

    public Game(){
/*
        JPanel panel1 = new JPanel();
        setBorder(new EmptyBorder(100, 10, 10, 10));
        this.setBackground(new Color(239,223,187));

        Font f2= new Font(Font.SERIF,  Font.BOLD, 20);

        Label a= new Label("Here we go!! ", JLabel.CENTER);
        a.setFont(f2);

        panel1.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel1.setBackground(new Color(239,223,187));
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        panel1.add(a, gbc); //adds in all the labels to panels


        JButton welcome= new JButton(":D");
        welcome.setBackground(new Color(59,47,47));
        welcome.setForeground(new Color(239,223,187));
        welcome.setFont(f2);

        welcome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player= new Player(frame);
                frame.getContentPane().add(player);
                setVisible(false);
                player.setVisible(true);
            }
        });

        panel1.add(welcome,gbc);

        this.add(panel1);
*/

        this.isOver = false;
        this.m_round = 0;
        player=new Player();
        player2 = new Player();

        //Here the player chooses which color he wants to play
        System.out.println("Do you want to play white or black? Enter 1 for white and 2 for black.");
        boolean color = true; //By default the player 1 will play the white pieces
        int choice;
        Scanner input = new Scanner(System.in);
        choice = input.nextInt();
        if(choice == 1){
            color = true;
            this.player.m_color = true;
            this.player2.m_color = false;
        }
        else if(choice == 2){
            color = false;
            this.player.m_color = false;
            this.player2.m_color = true;
        }

        //We place every piece on their starting spot
        //Starting with all player 1 pieces in their value order, then same for player 2
        for(int i = 0; i < 8; i++){ //player 1 pawns

            this.m_pieces.add(new Pawn(new Point(i,6), color));
            this.m_positionsTaken.add(new Point(i,6));

        }

        //player 1 Knights and bishops
        this.m_pieces.add(new Knight(new Point(1,7), color));
        this.m_pieces.add(new Knight(new Point(6,7), color));
        this.m_pieces.add(new Bishop(new Point(2,7), color));
        this.m_pieces.add(new Bishop(new Point(5,7), color));
        //player 1 rooks
        this.m_pieces.add(new Rook(new Point(0,7), color));
        this.m_pieces.add(new Rook(new Point(7,7), color));
        this.m_pieces.add(new Queen(new Point(4,7), color)); //player 1 Queen
        this.m_pieces.add(new King(new Point(3,7), color)); //player 1 King

        for(int i = 0; i < 8; i++) {
            //we set all the positions of line 7 as taken
            this.m_positionsTaken.add(new Point(i, 7));
        }

        //Same code for player 2 at the other side of the board with the color left
        for(int i = 0; i < 8; i++){ //player 2 pawns
            this.m_pieces.add(new Pawn(new Point(i,1), !color));
            this.m_positionsTaken.add(new Point(i,1));

        }
        //player 2 Knights and bishops
        this.m_pieces.add(new Knight(new Point(1,0), !color));
        this.m_pieces.add(new Knight(new Point(6,0), !color));
        this.m_pieces.add(new Bishop(new Point(2,0), !color));
        this.m_pieces.add(new Bishop(new Point(5,0), !color));
        //player 2 rooks
        this.m_pieces.add(new Rook(new Point(0,0), !color));
        this.m_pieces.add(new Rook(new Point(7,0), !color));
        this.m_pieces.add(new Queen(new Point(4,0), !color)); //player 2 Queen
        this.m_pieces.add(new King(new Point(3,0), !color)); //player 2 King

        for(int i = 0; i < 8; i++) {
            //we set all the positions of line 0 as taken
            this.m_positionsTaken.add(new Point(i, 0));
        }
        System.out.println("The pieces have been initialized. Let's see what the board looks like.");


    }

    ArrayList<Point> getPositionsTaken(){return this.m_positionsTaken; }
    public ArrayList<Piece> getPieces(){return this.m_pieces; }


}
