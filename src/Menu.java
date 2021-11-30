import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Menu extends JPanel {
    private JLabel menuTitle;
    private JPanel menuTitlePanel;
    private JPanel buttonPanel;

    public Menu(){
        JButton start= new JButton("Start");
        start.setBackground(Color.DARK_GRAY);
        start.setForeground(Color.PINK);
        JButton score= new JButton("Show scores");
        score.setBackground(Color.DARK_GRAY);
        score.setForeground(Color.PINK);
        JButton rules= new JButton("Rules");
        rules.setBackground(Color.DARK_GRAY);
        rules.setForeground(Color.PINK);
        JButton exit=new JButton("Exit");
        exit.setBackground(Color.DARK_GRAY);
        exit.setForeground(Color.PINK);

        GridBagLayout gbl= new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());


        setBorder(new EmptyBorder(200, 10, 10, 10));

        this.setBackground(new Color(208, 171, 179));

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        menuTitle= new JLabel("<html><h1><strong><i>AI CHESS</i></strong></h1><hr></html>");
        menuTitle.setForeground(Color.DARK_GRAY);
        add(menuTitle,gbc);

        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;



        JPanel buttons = new JPanel(gbl);

        buttons.add(start, gbc);
        buttons.add(score, gbc);
        buttons.add(rules, gbc);
        buttons.add(exit, gbc);

        gbc.weightx = 0;
        gbc.weighty = 1;
        add(buttons, gbc);
    }
}
