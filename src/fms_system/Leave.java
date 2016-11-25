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
import java.text.DateFormat.Field;
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
    Login login_obj;
    public Leave(){
        //frame
        super("Leave Application");
        setSize(width,height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        //title
        JLabel title=new JLabel("Leave Application",SwingConstants.CENTER);
        title.setLocation(300, 40);
        title.setSize(200, 40);
        add(title);
        //labels
        JLabel Texts[]=new JLabel[5];
        Texts[0]=new JLabel("UserName");
        Texts[1]=new JLabel("To Whom:");
        Texts[2]=new JLabel("Reason:");
        Texts[3]=new JLabel("Start Date:");
        Texts[4]=new JLabel("End Date:");
        
        String[] box={"","Admin","Supervisor"};
        JComboBox whom=new JComboBox(box);
        JTextField[] Fields=new JTextField[5];
        JPanel leave=new JPanel(new GridLayout(5,2,30,30));
        for(int i=0;i<5;i++)
        {
            Fields[i]=new JTextField();
            if(i==0){
                Fields[0].setText(Login.getCurrentUser());
                Fields[0].setEnabled(false);
            }
            leave.add(Texts[i]);
            if(i!=1)
                leave.add(Fields[i]);
            else{
                leave.add(whom);
            }
        }
        leave.setLocation(300,150);
        leave.setSize(400,400);
        add(leave);
        
        //buttons
        JButton log_out=new JButton("Log Out");
    log_out.setLocation(900,0);
    log_out.setSize(100,40);
    log_out.setBackground(Color.orange);
    log_out.setForeground(Color.white);
    add(log_out);
    log_out.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e) {
             login_obj=new Login();
             setVisible(false);
             login_obj.setVisible(true);
         }
        
    });
        JButton sub=new JButton("Apply");
        sub.setLocation(300,600);
        sub.setSize(200,40);
        sub.setBackground(Color.green);
        sub.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                Fields[1].setText(whom.getSelectedItem().toString());
                try{
                    PrintWriter writer = new PrintWriter(new FileWriter("database/leave.csv",true));
                    String userData="";
                    for(int i=0;i<4;i++){
                        if(i==1){
                            if(Fields[1].getText().equals("Supervisor")){
                                userData+=Login.getCurrentDept()+",";
                            }
                            else{
                                userData+="Admin,";
                            }
                        }
                        else{
                            userData+=Fields[i].getText() +",";
                        }
                    }
                    userData+=Fields[4].getText();
                    writer.println(userData);
                    writer.close();
                } catch (Exception e) {}
                JOptionPane.showMessageDialog (null,"Your Leave Request Has been Sent","Thank You", JOptionPane.INFORMATION_MESSAGE);
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

