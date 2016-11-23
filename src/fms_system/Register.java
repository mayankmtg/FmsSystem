/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fms_system;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author mayank
 * @author amit
 */
public class Register extends JFrame{
    int width=800;
    int height=500;
    FMS_System home_obj;
    
    public int setID(){
        String s;
        int id=-1;
        try{
            BufferedReader in = new BufferedReader(new FileReader("database/getId.csv"));
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
    public void writeStatus(String s1,String s2, String s3){
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("database/employeeStatus.csv",true));
            String temp= s1 + "," + s2 + "," + s3 + ",Available";
            writer.println();
            writer.close();
        } catch (IOException ex) {}
    }
    public void sendRequest(String userData){
        try{
            PrintWriter writer = new PrintWriter(new FileWriter("database/registerRequests.csv",true));
            
            writer.println(userData);

            writer.close();
        
        } catch (Exception e) {}
        JOptionPane.showMessageDialog (null, "Your Request has been forwarded to the Admin.", "Thank You", JOptionPane.INFORMATION_MESSAGE);
        setVisible(false);
        home_obj=new FMS_System();
        home_obj.setVisible(true);
    }
    public void updateID(String x){
        PrintWriter writer;
        try {
            writer = new PrintWriter(new FileWriter("database/getId.csv"));
            writer.println();
            writer.close();
        } catch (IOException ex) {}
    }
    
    public Register(){
        super("Registeration");
        setSize(width,height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        
        JLabel title=new JLabel("Registeration Details",SwingConstants.CENTER);
        title.setLocation(300, 10);
        title.setSize(200, 40);
        add(title);
        JLabel Texts[]=new JLabel[8];
        
        Texts[0]=new JLabel("ID:");
        Texts[1]=new JLabel("Type:");
        Texts[2]=new JLabel("Name:");
        Texts[3]=new JLabel("User Name:");
        Texts[4]=new JLabel("Password:");
        Texts[5]=new JLabel("DOB:");
        Texts[6]=new JLabel("Address:");
        Texts[7]=new JLabel("Department:");
        
        for(int i=0;i<8;i++){
            Texts[i].setLocation(300, 90+50*i);
            Texts[i].setSize(100,20);
            add(Texts[i]);
        }
        ///////////////////////////////////////
        JTextField [] Fields=new JTextField[8];
        Fields[0]=new JTextField();
        Fields[0].setLocation(400, 90);
        Fields[0].setSize(200,20);
        Fields[0].setEnabled(false);
        Fields[0].setText(""+this.setID());
        add(Fields[0]);
        for(int i=1;i<8;i++){
            Fields[i]=new JTextField();
            Fields[i].setLocation(400, 90+50*i);
            Fields[i].setSize(200,20);
            if(i!=1 && i!=7){
                add(Fields[i]);
            }
        }
        
        String[] type_vals={"","Supervisor", "Staffer"};
        JComboBox type_job=new JComboBox(type_vals);
        type_job.setBounds(400, 140,200,20);  
        add(type_job);
        
        String[] dep_vals={"", "Electricity","HVAC","Audio/Video","Security","Housekeeping"};
        JComboBox dep_job=new JComboBox(dep_vals);
        dep_job.setBounds(400, 90+7*50,200,20);  
        add(dep_job);
        
        Fields[3].setEnabled(false);
        Fields[2].addFocusListener(new java.awt.event.FocusListener() {
            public void focusGained(java.awt.event.FocusEvent focusEvent) {}
            public void focusLost(java.awt.event.FocusEvent focusEvent) {
                try {
                    if(!Fields[2].getText().equals("")){
                        Fields[3].setText("" + Fields[2].getText() + Fields[0].getText() );
                    }
                } catch (ClassCastException ignored) {}
            }
        });
        
        JButton sub=new JButton("Submit");
        sub.setLocation(200, 470);
        sub.setSize(200,20);
        sub.setBackground(Color.green);
        sub.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                int check=0;
                Fields[1].setText(type_job.getSelectedItem().toString());
                Fields[7].setText(dep_job.getSelectedItem().toString());
                String userData="";
                for(int i=0;i<7;i++){
                    userData+=Fields[i].getText() +",";
                }
                userData+=Fields[7].getText();
                sendRequest(userData);
                updateID(Fields[0].getText());
            }
        });
        add(sub);
        
        JButton cancel=new JButton("Cancel");
        cancel.setLocation(400, 470);
        cancel.setSize(200,20);
        cancel.setBackground(Color.red);
        cancel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                new FMS_System().setVisible(true);
            }
        });
        add(cancel);
    }
}
