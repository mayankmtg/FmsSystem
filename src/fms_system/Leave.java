/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fms_system;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author mayank
 * @author amit
 */
public class Leave extends JFrame{
    int width=1000;
    int height=700;
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
        Texts[0]=new JLabel("To Whom:");
        Texts[1]=new JLabel("Reason:");
        Texts[2]=new JLabel("Start Date:");
        Texts[3]=new JLabel("End Date:");
        String[] box={"Admin","Department_Supervisor"};
        JComboBox whom=new JComboBox(box);
        JTextField[] Fields=new JTextField[4];
        JPanel leave=new JPanel(new GridLayout(4,2,30,30));
        for(int i=0;i<4;i++)
        {
            Fields[i]=new JTextField();
            leave.add(Texts[i]);
            if(i!=0)
                leave.add(Fields[i]);
            else{
                leave.add(whom);
            }
        }
        leave.setLocation(300,150);
        leave.setSize(400,400);
        add(leave);
        
        
        JButton sub=new JButton("Apply");
        sub.setLocation(300,600);
        sub.setSize(200,40);
        sub.setForeground(Color.white);
        sub.setBackground(Color.red);
        sub.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
            }
        });
        add(sub);
    }
}

