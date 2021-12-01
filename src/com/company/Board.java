package com.company;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Board extends JPanel {

    //Attributes of the board
    private BufferedImage BKnight;
    private BufferedImage BBishop;
    private BufferedImage BKing;
    private BufferedImage BPawn;
    private BufferedImage BQueen;
    private BufferedImage BRook;
    
    private BufferedImage WKnight;
    private BufferedImage WBishop;
    private BufferedImage WKing;
    private BufferedImage WPawn;
    private BufferedImage WQueen;
    private BufferedImage WRook;

    Countdown countdown = new Countdown();

    ArrayList<Piece> m_pieces = new ArrayList<>();
    int clicked = 0;
    int size = 100; //size of a square?
    int clickedX = 0;
    int clickedY = 0;
    Piece selected = null;
    Point tempPoint;

    Game b_game; //the board is connected to the game

    

    //Creation of the board
    public Board(JFrame frame){

        this.b_game = new Game();
        frame.add(Countdown.counterLabel);
        frame.add(Countdown.counterLabel2);
        frame.add(Countdown.scoreCounter);

        try{
            BKnight = ImageIO.read(getClass().getResource("/BKnight.png"));
            BBishop = ImageIO.read(getClass().getResource("/BBishop.png"));
            BKing = ImageIO.read(getClass().getResource("/BKing.png"));
            BPawn = ImageIO.read(getClass().getResource("/BPawn.png"));
            BQueen = ImageIO.read(getClass().getResource("/BQueen.png"));
            BRook = ImageIO.read(getClass().getResource("/BRook.png"));

            WKnight = ImageIO.read(getClass().getResource("/WKnight.png"));
            WBishop = ImageIO.read(getClass().getResource("/WBishop.png"));
            WKing = ImageIO.read(getClass().getResource("/WKing.png"));
            WPawn = ImageIO.read(getClass().getResource("/WPawn.png"));
            WQueen = ImageIO.read(getClass().getResource("/WQueen.png"));
            WRook = ImageIO.read(getClass().getResource("/WRook.png"));
        } catch (IOException e){
            e.printStackTrace();
        }

        addMouseListener(new MouseAdapter() { 
            public void mousePressed(MouseEvent me) { 
                m_pieces = b_game.getPieces();
                clickedX = me.getX()/100;
                clickedY = me.getY()/100;
                clicked = (clickedX + 1) + (clickedY + 1)*8 - 8;

                tempPoint = new Point(clickedX,clickedY);
                move();

                frame.repaint();
            } 
          }); 
    }

    
    public void paint(Graphics g){
        boolean color = false;
        Color white = new Color(239,223,187);
        Color black = new Color(59,47,47);
        int x = 0;
        int y = 0;
        int tempx = 0;
        int tempy = 0;
        for(int i = 1; i < 65; i++){
            if(i == clicked){
                g.setColor(Color.ORANGE);
                color = !color;
            }

            else if(color){
                g.setColor(white);
                color = false;  
            } else{
                g.setColor(black);
                color = true;
            }
            g.fillRect(x, y, size, size);
            x = x + size;
            if(i%8 == 0){
                y = y + size;
                x = 0;
                if(color)
                    color = false;
                else
                    color = true;
            }
            
            for(int k = 0; k < m_pieces.size(); k++){

                if(m_pieces.get(k).getPositionX() == tempx && m_pieces.get(k).getPositionY() == tempy){

                    if(m_pieces.get(k).getColor()){
                        if(m_pieces.get(k).toString().contains("Pawn")){
                            g.drawImage(WPawn, tempx*100 + 20, tempy*100 + 20, null);
                        } else if (m_pieces.get(k).toString().contains("King")){
                            g.drawImage(WKing, tempx*100 + 20, tempy*100 + 20, null);
                        } else if (m_pieces.get(k).toString().contains("Knight")){
                            g.drawImage(WKnight, tempx*100 + 20, tempy*100 + 20, null);
                        } else if (m_pieces.get(k).toString().contains("Queen")){
                            g.drawImage(WQueen, tempx*100 + 20, tempy*100 + 20, null);
                        } else if (m_pieces.get(k).toString().contains("Bishop")){
                            g.drawImage(WBishop, tempx*100 + 20, tempy*100 + 20, null);
                        } else if (m_pieces.get(k).toString().contains("Rook")){
                            g.drawImage(WRook, tempx*100 + 20, tempy*100 + 20, null);
                        } 
                    } else{
                        if(m_pieces.get(k).toString().contains("Pawn")){
                            g.drawImage(BPawn, tempx*100 + 20, tempy*100 + 20, null);
                        } else if (m_pieces.get(k).toString().contains("King")){
                            g.drawImage(BKing, tempx*100 + 20, tempy*100 + 20, null);
                        } else if (m_pieces.get(k).toString().contains("Knight")){
                            g.drawImage(BKnight, tempx*100 + 20, tempy*100 + 20, null);
                        } else if (m_pieces.get(k).toString().contains("Queen")){
                            g.drawImage(BQueen, tempx*100 + 20, tempy*100 + 20, null);
                        } else if (m_pieces.get(k).toString().contains("Bishop")){
                            g.drawImage(BBishop, tempx*100 + 20, tempy*100 + 20, null);
                        } else if (m_pieces.get(k).toString().contains("Rook")){
                            g.drawImage(BRook, tempx*100 + 20, tempy*100 + 20, null);
                        } 
                    }

                  

                }
            }

            if(tempx == 7){
                tempy++;
                tempx = 0;
            } else{
                tempx++;
            }
            

        }
        

    }

    //This method moves the selected piece on the board to the new clicked position if its available
    public void move(){
        

        if(selected != null){ //if something has been selected

            selected.UpdatePossiblePositions(b_game); //Selected is a piece. We update its possible positions

                System.out.println(selected);

                if(selected.contains(tempPoint, selected.possiblePositions)){
                    for(int k = 0; k < m_pieces.size(); k++){

                        if(m_pieces.get(k).getPositionX() == clickedX && m_pieces.get(k).getPositionY() == clickedY){ //we check if a piece is on the selected position the player wants to go to
                            countdown.timer.start();                            

                            /*if(b_game.player.m_color == true && b_game.ai.m_color==false){
                                countdown.elapsedTime+=10000;
                                countdown.timer1.stop();
                                countdown.timer.start();                            
                            }else if(b_game.player.m_color == false && b_game.ai.m_color==true){
                                countdown.elapsedTime+=10000;
                                countdown.timer.stop();
                                countdown.timer1.start();
                                }*/
                                //if(b_game.m_round == 40){
                                //    countdown.elapsedTime+=1000;
                                //}
                            
                        
                            System.out.println(m_pieces.get(k).getColor());
                            System.out.println(selected.getColor());
        
                            if(m_pieces.get(k).getColor() != selected.getColor()){ //if the color of the piece is different from our knight
                                m_pieces.remove(m_pieces.get(k)); //we delete the piece
                                selected.setPosition(clickedX, clickedY); //we move our knight there
        
                            } 
                        } else {
                            selected.setPosition(clickedX, clickedY); //we move the knight there
                        }
                    } 
                    
                    

                }
                
            

            selected = null; //we say that nothing has been selected  
            
        }else{ //if nothing has been selected

            for(int k = 0; k < m_pieces.size(); k++){
                if(m_pieces.get(k).getPositionX() == clickedX && m_pieces.get(k).getPositionY() == clickedY){
                    selected = m_pieces.get(k);
                }
            }

        }
   }
}
