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
public class Task extends JFrame{
    int width=800;
    int height=500;
    public Task(){
        
        super("Task Assigner");
        setSize(width,height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        
        JLabel title=new JLabel("Task Assignment",SwingConstants.CENTER);
        title.setLocation(300, 40);
        title.setSize(200, 40);
        add(title);
        
        JLabel Texts[]=new JLabel[8];
        
        JLabel ID=new JLabel("Task ID:");
        JLabel TaskName=new JLabel("TaskName:");
        JLabel  TaskDescDept=new JLabel("Task Desc. Dept.:");
        JLabel Supervisor=new JLabel("Supervisor:");
        JLabel noofStaff=new JLabel("No. of Staff:");
        JLabel equip=new JLabel("Equipment:");
        JLabel status=new JLabel("Status:");
        JLabel deadline=new JLabel("Deadline:");
        for(int i=0;i<4;i++){
            if(i==0) { 
                Texts[0].setEnabled(false);
                continue;
            }
            Texts[i].setLocation(300, 90+50*i);
            Texts[i].setSize(100,20);
            add(Texts[i]);
        }
        
        ///////////////////////////////////////
        JTextField Fields[]=new JTextField[8];
        
        for(int i=0;i<8;i++){
            if(i==0) { 
                Fields[0].setEnabled(false);
                continue;
            }
            Fields[i]=new JTextField();
            Fields[i].setLocation(400, 90+50*i);
            Fields[i].setSize(200,20);
            add(Fields[i]);
        }
        JButton sub=new JButton("Submit");
        sub.setLocation(350, 470);
        sub.setSize(200,20);
        sub.setBackground(Color.yellow);
        
        sub.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                //code after the submit of the Task
            }
            
        });
        add(sub);
        
    }
    
}
