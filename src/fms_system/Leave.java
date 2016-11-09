/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fms_system;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author mayank
 * @author amit
 */
public class Leave extends JFrame{
    int width=800;
    int height=500;
    public Leave(){
        
        super("Leave Application");
        setSize(width,height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        
        JLabel title=new JLabel("Leave Application",SwingConstants.CENTER);
        title.setLocation(300, 40);
        title.setSize(200, 40);
        add(title);
        
        JLabel person=new JLabel("To Whome:");
        person.setLocation(300, 90);
        person.setSize(100, 20);
        add(person);
        
        JLabel Reason=new JLabel("Reason:");
        Reason.setLocation(300, 140);
        Reason.setSize(100, 20);
        add(Reason);
        
        JLabel  Start=new JLabel("Start Date:");
        Start.setLocation(300, 190);
        Start.setSize(100, 20);
        add(Start);
        
        JLabel End=new JLabel("End Date:");
        End.setLocation(300, 240);
        End.setSize(100, 20);
        add(End);
        
        ///////////////////////////////////////
        
        JTextField ID_text=new JTextField();
        ID_text.setLocation(400, 90);
        ID_text.setSize(200,20);
        ID_text.setEnabled(false);
        add(ID_text);
        
        JTextField Type_text=new JTextField();
        Type_text.setLocation(400, 140);
        Type_text.setSize(200,20);
        Reason.requestFocus();
        add(Type_text);
        
        JTextField Name_text=new JTextField();
        Name_text.setLocation(400, 190);
        Name_text.setSize(200,20);
        add(Name_text);
        
        JTextField userName=new JTextField();
        userName.setLocation(400, 240);
        userName.setSize(200,20);
        add(userName);
        
        JButton sub=new JButton("Apply");
        sub.setLocation(350, 300);
        sub.setSize(200,40);
        sub.setForeground(Color.white);
        sub.setBackground(Color.red);
        
        sub.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                //code after leave application
            }
            
        });
        add(sub);
        
    }
}

