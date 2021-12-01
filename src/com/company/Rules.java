package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Rules extends JPanel{
    public Rules(JFrame frame){
        setBorder(new EmptyBorder(200, 10, 10, 10));
        this.setBackground(new Color(239,223,187));
        Label i= new Label("You should have looked it up online you lazy player!", JLabel.NORTH);
        add(i);
    }
}
