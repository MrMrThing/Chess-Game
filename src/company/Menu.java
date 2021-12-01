package company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;

public class Menu extends JPanel implements ActionListener {
    private JLabel menuTitle;
    private JButton start;
    private JButton score;
    private JButton rules;
    private JButton exit;

    public Menu(JFrame frame){

        //Fonts
        Font f1= new Font(Font.SERIF,  Font.BOLD, 50);
        Font f3= new Font(Font.SERIF, Font.PLAIN,  20);

        //Add button to layout
        this.start= new JButton("Start");
        this.start.setBackground(new Color(59,47,47));
        this.start.setForeground(new Color(239,223,187));
        this.start.setFont(f3);

        this.score= new JButton("Show scores");
        this.score.setBackground(new Color(59,47,47));
        this.score.setForeground(new Color(239,223,187));
        this.score.setFont(f3);

        this.rules= new JButton("Rules");
        this.rules.setBackground(new Color(59,47,47));
        this.rules.setForeground(new Color(239,223,187));
        this.rules.setFont(f3);

        this.exit=new JButton("Exit");
        this.exit.setBackground(new Color(59,47,47));
        this.exit.setForeground(new Color(239,223,187));
        this.exit.setFont(f3);

        GridBagLayout gbl= new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        //Container c= frame.getContentPane();

        //Set layout for the content pane
        setLayout(new GridBagLayout());


        setBorder(new EmptyBorder(150, 10, 10, 10));

        this.setBackground(new Color(239,223,187));

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        this.menuTitle= new JLabel("AI CHESS");
        this.menuTitle.setForeground(Color.DARK_GRAY);
        this.menuTitle.setFont(f1);
        add(this.menuTitle,gbc);

        Label a= new Label("");
        add(a,gbc);

        JLabel line= new JLabel("__________________");
        line.setForeground(Color.DARK_GRAY);
        line.setFont(f1);
        add(line,gbc);


        Label b= new Label("");
        add(b,gbc);
        Label c= new Label("");
        add(c,gbc);
        Label d= new Label("");
        add(d,gbc);
        Label e= new Label("");
        add(e,gbc);
        Label f= new Label("");
        add(f,gbc);
        Label g= new Label("");
        add(g,gbc);
        Label h= new Label("");
        add(h,gbc);

        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //Add actionListener for each button
        this.start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Board b= new Board(frame);
                frame.getContentPane().add(b);
                setVisible(false);
                b.setVisible(true);
            }
        });
        this.score.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Show score");
            }
        });
        this.rules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rules b= new Rules(frame);
                frame.getContentPane().add(b);
                setVisible(false);
                b.setVisible(true);
            }
        });
        this.exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel buttons = new JPanel(gbl);

        buttons.setBackground(new Color(239,223,187));
        //Add buttons to JPanel
        buttons.add(this.start, gbc);
        buttons.add(this.score, gbc);
        buttons.add(this.rules, gbc);
        buttons.add(this.exit, gbc);

        gbc.weightx = 0;
        gbc.weighty = 1;
        add(buttons, gbc);

        JLabel line1= new JLabel("__________________");
        line1.setForeground(Color.DARK_GRAY);
        line1.setFont(f1);
        add(line1,gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
