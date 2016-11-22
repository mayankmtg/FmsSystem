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
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    Staff staff_obj;
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
        String[] box={"Admin","Supervisor"};
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
        sub.setBackground(Color.green);
        sub.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                Fields[0].setText(whom.getSelectedItem().toString());
                try{
                        PrintWriter writer = new PrintWriter(new FileWriter("database/leave.csv",true));
                        String userData="";
                        for(int i=0;i<3;i++){
                            if(Fields[0].getText().equals("Supervisor") && i==0){
                                System.out.print(Fields[0].getText());
                                userData+=Login.getCurrentDept()+",";
                            }
                            else
                                userData+=Fields[i].getText() +",";
                        }
                        userData+=Fields[3].getText();
                        writer.println(userData);
                        writer.close();
                        } catch (Exception e) {
                    }
                JOptionPane.showMessageDialog (null,"Hey! You have been succssfully sent a requset.","Thank You", JOptionPane.INFORMATION_MESSAGE);
                for(int i=1;i<4;i++)
                {
                    Fields[i].setText("");
                }
            }
        });
        add(sub);
        
        JButton back=new JButton("Back");
        back.setLocation(550,600);
        back.setSize(200,40);
        back.setForeground(Color.white);
        back.setBackground(Color.red);
        add(back);
        back.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                staff_obj=new Staff();
                staff_obj.setVisible(true);
            }
            
        });
    }
}

