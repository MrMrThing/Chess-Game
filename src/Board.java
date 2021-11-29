import java.awt.Color;
import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Board extends JPanel {

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

    ArrayList<Piece> m_pieces = new ArrayList<>();
    int clicked = 0;
    int size = 100;
    int clickedX = 0;
    int clickedY = 0;
    Piece selected = null;

    

    public Board(JFrame frame){
        Game g = new Game();
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
                m_pieces = g.getPieces();
                clickedX = me.getX()/100;
                clickedY = me.getY()/100;
                clicked = (clickedX + 1) + (clickedY + 1)*8 - 8;

                move();

                frame.repaint();
            } 
          }); 
    }



    
    
    public void paint(Graphics g){

        boolean color = false;
        Color white = new Color(180,180,180);
        Color black = new Color(118,134,71);
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

    public void move(){
        

        if(selected != null){

            if(selected.toString().contains("Knight")){


                for(int k = 0; k < m_pieces.size(); k++){
                    if(m_pieces.get(k).getPositionX() == clickedX && m_pieces.get(k).getPositionY() == clickedY){
                        
                        System.out.println(m_pieces.get(k).getColor());
                        System.out.println(selected.getColor());
    
                        if(m_pieces.get(k).getColor() != selected.getColor()){
                            m_pieces.remove(m_pieces.get(k));
                            selected.setPosition(clickedX, clickedY);
    
                        } 
                    } else {
                        selected.setPosition(clickedX, clickedY);
                    }
                } 
                
                selected = null;
            }


            
        }else{

            for(int k = 0; k < m_pieces.size(); k++){
                if(m_pieces.get(k).getPositionX() == clickedX && m_pieces.get(k).getPositionY() == clickedY){
                    selected = m_pieces.get(k);
                }
            }

        }
   }
}