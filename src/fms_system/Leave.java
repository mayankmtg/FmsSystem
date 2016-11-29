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
import java.io.IOException;
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
    
    public void sendLeaveForStaff(String userData){
        try{
            PrintWriter writer = new PrintWriter(new FileWriter("database/persons/"+getSupName()+"/LeaveRequests.csv",true));
            writer.println(userData);
            writer.close();
        } catch (Exception e) {}
        String line;
        BufferedReader reader;
        String write="";
        try{       
            reader = new BufferedReader(new FileReader("database/persons/"+getSupName()+"/Notification.csv"));
            line = reader.readLine();
            String[] var=line.split(",");
            var[2]=""+(Integer.parseInt(var[2])+1);
            write=var[0]+","+var[1]+","+var[2];
            reader.close();
            PrintWriter writer2 = new PrintWriter(new FileWriter("database/persons/"+getSupName()+"/Notification.csv"));
            writer2.println(write);
            writer2.close();
         }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error");
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog (null,"Your Leave Request Has been Sent","Thank You", JOptionPane.INFORMATION_MESSAGE);
    }
    public void sendLeaveForSup(String userData){
        try{
            PrintWriter writer = new PrintWriter(new FileWriter("database/persons/admin1/LeaveRequests.csv",true));
            writer.println(userData);
            writer.close();
        } catch (Exception e) {}
        
        String line;
        BufferedReader reader;
        String write="";
        try{       
            reader = new BufferedReader(new FileReader("database/persons/admin1/Notification.csv"));
            line = reader.readLine();
            String[] var=line.split(",");
            var[2]=""+(Integer.parseInt(var[2])+1);
            write=var[0]+","+var[1]+","+var[2];
            reader.close();
            PrintWriter writer2 = new PrintWriter(new FileWriter("database/persons/admin1/Notification.csv"));
            writer2.println(write);
            writer2.close();
         }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error");
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog (null,"Your Leave Request Has been Sent","Thank You", JOptionPane.INFORMATION_MESSAGE);
    }
    public String getSupName(){
        String line;
        BufferedReader reader;
        String retString="";
        try{       
            reader = new BufferedReader(new FileReader("database/Supervisors.csv"));
            while((line = reader.readLine()) != null){
               if(line.split(",")[7].equals(Login.getCurrentDept())){
                   retString=line.split(",")[3];
                   break;
               }
            }
            reader.close();
         }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error");
            e.printStackTrace();
        }
        return retString;
    }
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
            if(i==1 && Login.getCurrentType().equals("Satffer")){
                Fields[i].setEnabled(false);
                Fields[i].setText("Supervisor");
            }
            else if(i==1 && Login.getCurrentType().equals("Supervisor")){
                Fields[i].setEnabled(false);
                Fields[i].setText("Admin");
            }
            leave.add(Fields[i]);
        }
        leave.setLocation(300,150);
        leave.setSize(400,400);
        add(leave);
        
        //buttons
        JButton sub=new JButton("Send Leave Request");
        sub.setLocation(300,600);
        sub.setSize(200,40);
        sub.setBackground(Color.green);
        sub.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                String userData="";
                for(int i=0;i<4;i++){
                    userData+=Fields[i].getText() +",";
                }
                userData+=Fields[4].getText();
                if(Login.getCurrentType().equals("Staffer")){
                    sendLeaveForStaff(userData);
                }
                else{
                    sendLeaveForSup(userData);
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
        
        JButton log_out=new JButton("Log Out");
        log_out.setLocation(900,0);
        log_out.setSize(100,40);
        log_out.setBackground(Color.orange);
        log_out.setForeground(Color.white);
        add(log_out);
        log_out.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e) {
                 new Login().setVisible(true);
                 setVisible(false);
                 
             }

        });
    }
}

