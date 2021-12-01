package company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;

public class Menu extends JPanel implements ActionListener {

    public Menu(JFrame frame){

        //Fonts
        Font f1= new Font(Font.SERIF,  Font.BOLD, 50);
        Font f3= new Font(Font.SERIF, Font.PLAIN,  20);

        //Add button to layout
        JButton start = new JButton("Start");
        start.setBackground(new Color(59,47,47));
        start.setForeground(new Color(239,223,187));
        start.setFont(f3);

        JButton rules = new JButton("Rules");
        rules.setBackground(new Color(59,47,47));
        rules.setForeground(new Color(239,223,187));
        rules.setFont(f3);

        JButton exit = new JButton("Exit");
        exit.setBackground(new Color(59,47,47));
        exit.setForeground(new Color(239,223,187));
        exit.setFont(f3);

        GridBagLayout gbl= new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        //Container c= frame.getContentPane();

        //Set layout for the content pane
        setLayout(new GridBagLayout());


        setBorder(new EmptyBorder(150, 10, 10, 10));

        this.setBackground(new Color(239,223,187));

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        JLabel menuTitle = new JLabel("AI CHESS");
        menuTitle.setForeground(Color.DARK_GRAY);
        menuTitle.setFont(f1);
        add(menuTitle,gbc);

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
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Board b= new Board(frame);
                frame.getContentPane().add(b);
                setVisible(false);
                b.setVisible(true);
            }
        });
        rules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rules b= new Rules(frame);
                frame.getContentPane().add(b);
                setVisible(false);
                b.setVisible(true);
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel buttons = new JPanel(gbl);

        buttons.setBackground(new Color(239,223,187));
        //Add buttons to JPanel
        buttons.add(start, gbc);
        buttons.add(rules, gbc);
        buttons.add(exit, gbc);

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
