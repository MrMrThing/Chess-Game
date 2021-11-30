import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {
    public Menu(JFrame frame){
        JButton b= new JButton("Play");
        JButton e= new JButton("Exit");
        b.setPreferredSize(new Dimension(100,30));
        frame.add(b);
        frame.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
