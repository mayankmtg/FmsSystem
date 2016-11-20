/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fms_system;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author mayank
 * @author amit
 */
public class Task extends JFrame{
    int width=1000;
    int height=700;
    JPanel Left;
    public int setID(){
        String s;
        int id=-1;
        try{
            BufferedReader in = new BufferedReader(new FileReader("database/getTaskId.csv"));
            String line;
            String[] var = null;
            line = in.readLine();
            var = line.split(",");
            id=Integer.parseInt(line);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            return id+1;
        }    
    }
    
    public Task(){
        
        super("Task Assigner");
        setSize(width,height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        
        JLabel title =new JLabel("Task Assigner");
        title.setLocation(400,50);
        title.setSize(300,20);
        title.setFont(new Font("Serif", Font.PLAIN, 24));
        add(title);
        
        JLabel Texts[]=new JLabel[7];
        
        Texts[0]=new JLabel("Task ID:");
        Texts[1]=new JLabel("TaskName:");
        Texts[2]=new JLabel("Task Desc. Dept.:");
        Texts[3]=new JLabel("Supervisor:");
        //Texts[4]=new JLabel("No. of Staff:");
        Texts[4]=new JLabel("Equipment:");
        Texts[5]=new JLabel("Status:");
        Texts[6]=new JLabel("Deadline:");
        
        ///////////////////////////////////////
        
        JTextField Fields[]=new JTextField[7];
        
        Left=new JPanel(new GridLayout(7,2,30,50));
        for(int i=0;i<7;i++){
            Fields[i]=new JTextField();
            Fields[i].setSize(200,20);
            Texts[i].setSize(200,20);
            Left.add(Texts[i]);
            Left.add(Fields[i]);
        }
        Left.setSize(450,500);
        Left.setLocation(50,200);
        //Left.setBorder(BorderFactory.createLineBorder(Color.black));
        add(Left);
        
        
        
        //submit Panel
        JButton sub=new JButton("Submit");
        sub.setBackground(Color.yellow);
        sub.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                //code after the submit of the Task
            }
            
        });
        
        JButton back=new JButton("Back");
        back.setBackground(Color.red);
        back.setForeground(Color.white);
        back.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                //code after the submit of the Task
            }
            
        });
        JPanel sub_panel=new JPanel(new GridLayout(1,2,30,50));
        sub_panel.add(sub);
        sub_panel.add(back);
        sub_panel.setSize(350,30);
        sub_panel.setLocation(600,600);
        add(sub_panel);
    }
    
}
