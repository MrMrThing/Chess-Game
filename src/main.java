

import javax.swing.*;
import java.awt.*;

public class main {

    JFrame frame;
    JButton squares[][] = new JButton[8][8];
    private Color colorGreen = Color.green;


    public main() {
        // frame
        frame = new JFrame("Play Chess!!!");
        frame.setSize(800, 800);
        frame.setLayout(new GridLayout(8, 8));

        // Menu bar
        JMenuBar menu = new JMenuBar();
        JMenu menu1 = new JMenu("New game");
        menu.add(menu1);
        JMenu menu2 = new JMenu("Resign");
        menu.add(menu2);
        frame.setJMenuBar(menu);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = new JButton();
                if ((i + j) % 2 != 0) {
                    squares[i][j].setBackground(colorGreen);
                }
                frame.add(squares[i][j]);
            }
        }
/*
        // image for rooks
        squares[0][0].add(new JButton(new ImageIcon(getClass().getResource("BRook.png"))));
        squares[0][7].add(new JButton(new ImageIcon(getClass().getResource("BRook.png"))));
        squares[7][0].add(new JButton(new ImageIcon(getClass().getResource("WRook.png"))));
        squares[7][7].add(new JButton(new ImageIcon(getClass().getResource("WRook.png"))));

        // image for knights
        squares[0][1].add(new JButton(new ImageIcon(getClass().getResource("BKnight.png"))));
        squares[0][6].add(new JButton(new ImageIcon(getClass().getResource("BKnight.png"))));
        squares[7][1].add(new JButton(new ImageIcon(getClass().getResource("WKnight.png"))));
        squares[7][6].add(new JButton(new ImageIcon(getClass().getResource("WKnight.png"))));

        // image for bishops
        squares[0][2].add(new JButton(new ImageIcon(getClass().getResource("BBishop.png"))));
        squares[0][5].add(new JButton(new ImageIcon(getClass().getResource("BBishop.png"))));
        squares[7][2].add(new JButton(new ImageIcon(getClass().getResource("WBishop.png"))));
        squares[7][5].add(new JButton(new ImageIcon(getClass().getResource("WBishop.png"))));

        // image for Queen
        squares[0][3].add(new JButton(new ImageIcon(getClass().getResource("BQueen.png"))));
        squares[0][4].add(new JButton(new ImageIcon(getClass().getResource("BKing.png"))));
        squares[7][3].add(new JButton(new ImageIcon(getClass().getResource("WQueen.png"))));
        squares[7][4].add(new JButton(new ImageIcon(getClass().getResource("WKing.png"))));
    
        // image for pawns
        for (int i = 0; i < 8; i++) {
            squares[1][i].add(new JButton(new ImageIcon(getClass().getResource("BPawn.png"))));
            squares[6][i].add(new JButton(new ImageIcon(getClass().getResource("WPawn.png"))));
        }*/ //sorry it creates an error for me

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.validate();
        frame.setLocationRelativeTo(null);

    }

    public static void main(String[] args) {

        new main();
    }
}