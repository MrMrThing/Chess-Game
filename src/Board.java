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



    ArrayList<Piece> m_pieces = new ArrayList<>();
    int clicked = 0;
    int size = 100;
    int clickedX = 0;
    int clickedY = 0;

    private BufferedImage BKnight;

    public Board(JFrame frame){
        Game g = new Game();
        try{
            BKnight = ImageIO.read(getClass().getResource("/BKing.png"));
        } catch (IOException e){
            e.printStackTrace();
        }

        addMouseListener(new MouseAdapter() { 
            public void mousePressed(MouseEvent me) { 
                m_pieces = g.getPieces();
                clickedX = me.getX()/100;
                clickedY = me.getY()/100;
                clicked = (clickedX + 1) + (clickedY + 1)*8 - 8;
                System.out.println("X " + clickedX);
                System.out.println("Y " + clickedY);
                System.out.println(clicked);
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
            g.drawImage(BKnight, x, y, null);


            for(int k = 0; k < m_pieces.size(); k++){
           
            }

        }
        

    }

    public static void main(String[] args){
        

    }
   
}