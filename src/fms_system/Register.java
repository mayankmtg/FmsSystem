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
import java.io.PrintWriter;
import javax.swing.JButton;
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
    Login login_obj=new Login();
    
    
    
    public int setID(){
        String s;
        int id=0;
        try{
            BufferedReader in = new BufferedReader(new FileReader("database/userinfo.csv"));
            String line;
            int ind=0;
            String[] var = null;
            while((line = in.readLine()) != null){
                var = line.split(",");
                ind++;
            }
            if(ind==0){
                id=1;
            }
            else{
                id=Integer.parseInt(var[0])+1;
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            return id;
        }
        
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
        
        JLabel ID=new JLabel("ID:");
        ID.setLocation(300, 90);
        ID.setSize(100, 20);
        add(ID);
        
        JLabel Type=new JLabel("Type:");
        Type.setLocation(300, 140);
        Type.setSize(100, 20);
        add(Type);
        
        JLabel  Name=new JLabel("Name:");
        Name.setLocation(300, 190);
        Name.setSize(100, 20);
        add(Name);
        
        JLabel uname=new JLabel("User Name:");
        uname.setLocation(300, 240);
        uname.setSize(100, 20);
        add(uname);
        
        JLabel pass=new JLabel("Password:");
        pass.setLocation(300, 290);
        pass.setSize(100, 20);
        add(pass);
        
        JLabel DOB=new JLabel("DOB:");
        DOB.setLocation(300, 340);
        DOB.setSize(100, 20);
        add(DOB);
        
        JLabel addr=new JLabel("Address:");
        addr.setLocation(300, 390);
        addr.setSize(100, 20);
        add(addr);
        
        JLabel dept=new JLabel("Department:");
        dept.setLocation(300, 440);
        dept.setSize(100, 20);
        add(dept);
        
        ///////////////////////////////////////
        JTextField [] Fields=new JTextField[8];
        Fields[0]=new JTextField();
        Fields[0].setLocation(400, 90);
        Fields[0].setSize(200,20);
        Fields[0].setEnabled(false);
        Fields[0].setText(""+this.setID());
        
        Fields[1]=new JTextField();
        Fields[1].setLocation(400, 140);
        Fields[1].setSize(200,20);
        Fields[1].requestFocus();
        
        Fields[2]=new JTextField();
        Fields[2].setLocation(400, 190);
        Fields[2].setSize(200,20);
        
        Fields[3]=new JTextField();
        Fields[3].setLocation(400, 240);
        Fields[3].setSize(200,20);
        
        Fields[4]=new JPasswordField();
        Fields[4].setLocation(400, 290);
        Fields[4].setSize(200,20);
        
        Fields[5]=new JTextField();
        Fields[5].setLocation(400, 340);
        Fields[5].setSize(200,20);
        
        Fields[6]=new JTextField();
        Fields[6].setLocation(400, 390);
        Fields[6].setSize(200,20);
        
        Fields[7]=new JTextField();
        Fields[7].setLocation(400, 440);
        Fields[7].setSize(200,20);
        
        for(int i=0;i<8;i++){
            add(Fields[i]);
        }
        JButton sub=new JButton("Submit");
        sub.setLocation(200, 470);
        sub.setSize(200,20);
        sub.setBackground(Color.green);
        sub.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                try{
                    PrintWriter writer = new PrintWriter(new FileWriter("database/userinfo.csv",true));
                    String userData="";
                    for(int i=0;i<7;i++){
                        userData+=Fields[i].getText() +",";
                    }
                    userData+=Fields[7].getText();
                    writer.println(userData);
                    writer.close();
                } catch (Exception e) {
                }
                JOptionPane.showMessageDialog (null, "Hey "+Fields[3].getText()+"! You have been succssfully registered. Please Login Now!", "Thank You", JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
                login_obj.setVisible(true);
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
                //login_obj.setVisible(true);
                new FMS_System().setVisible(true);
            }
        });
        add(cancel);
    }
}
