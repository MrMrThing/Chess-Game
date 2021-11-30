import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JPanel implements ActionListener {
    private JLabel menuTitle;
    private JButton start;
    private JButton score;
    private JButton rules;
    private JButton exit;

    public Menu(JFrame frame){

        //Add button to layout
        this.start= new JButton("Start");
        this.start.setBackground(Color.DARK_GRAY);
        this.start.setForeground(Color.PINK);

        this.score= new JButton("Show scores");
        this.score.setBackground(Color.DARK_GRAY);
        this.score.setForeground(Color.PINK);

        this.rules= new JButton("Rules");
        this.rules.setBackground(Color.DARK_GRAY);
        this.rules.setForeground(Color.PINK);

        this.exit=new JButton("Exit");
        this.exit.setBackground(Color.DARK_GRAY);
        this.exit.setForeground(Color.PINK);

        GridBagLayout gbl= new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        //Container c= frame.getContentPane();

        //Set layout for the content pane
        setLayout(new GridBagLayout());


        setBorder(new EmptyBorder(200, 10, 10, 10));

        this.setBackground(new Color(208, 171, 179));

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        this.menuTitle= new JLabel("<html><h1><strong><i>AI CHESS</i></strong></h1><hr></html>");
        this.menuTitle.setForeground(Color.DARK_GRAY);
        add(this.menuTitle,gbc);

        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //Add actionListener for each button
        this.start.addActionListener(this);
        this.score.addActionListener(this);
        this.rules.addActionListener(this);
        this.exit.addActionListener(this);

        JPanel buttons = new JPanel(gbl);
        //Add buttons to JPanel
        buttons.add(this.start, gbc);
        buttons.add(this.score, gbc);
        buttons.add(this.rules, gbc);
        buttons.add(this.exit, gbc);


        gbc.weightx = 0;
        gbc.weighty = 1;
        add(buttons, gbc);
    }

    public void actionPerformed(ActionEvent e){
        //Check to see which button has been clicked
        if(e.getActionCommand().equals("Start")){
            System.out.println("Start");
        }
        else if(e.getActionCommand().equals("Show scores")){
            System.out.println("Show score");
        }
        else if(e.getActionCommand().equals("Rules")){
            System.out.println("Rules");
        }
        else if(e.getActionCommand().equals("Exit")){
            System.out.println("Exit");
        }
    }
}
