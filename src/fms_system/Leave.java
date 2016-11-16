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
        
        JLabel Texts[]=new JLabel[4];
        Texts[0]=new JLabel("To Whome:");
        Texts[1]=new JLabel("Reason:");
        Texts[2]=new JLabel("Start Date:");
        Texts[3]=new JLabel("End Date:");
        
        for(int i=0;i<4;i++){
            Texts[i].setLocation(400, 90+50*i);
            Texts[i].setSize(200,20);
            add(Texts[i]);
        }
        ///////////////////////////////////////
        JTextField[] Fields=new JTextField[4];
        
        for(int i=0;i<4;i++){
            if(i==0) { 
                Fields[0].setEnabled(false);
                continue;
            }
            Fields[i]=new JTextField();
            Fields[i].setLocation(400, 90+50*i);
            Fields[i].setSize(200,20);
            add(Fields[i]);
        }
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

