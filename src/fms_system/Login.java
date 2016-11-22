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
import java.security.Timestamp;
import java.text.SimpleDateFormat;
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
public class Login extends JFrame{
    int width=800;
    int height=500;
    String user_name;
    String pass_word;
    Supervisor sup_obj;
    Staff staff_obj;
    Admin admin_obj;
    private static String current_user_name;// this contains the username of the person who has logged in
    private static String current_user_dept;
    private static String current_user_type;
    public static String getCurrentUser(){
        return current_user_name;
    }
    
    public static String getCurrentDept(){
        return current_user_dept;
    }
    public static String getCurrentType(){
        return current_user_type;
    }
    public void setLog(){
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("database/Log.csv",true));
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
            String log=current_user_name + "," + timeStamp;
            writer.println(log);
            writer.close();
        } catch (IOException ex) {}
    }
    public Login(){
        super("Login Form");
        setSize(width,height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        
        JLabel title=new JLabel("Login Form",SwingConstants.CENTER);
        title.setLocation(300, 100);
        title.setSize(200, 40);
        title.setForeground(Color.red);
        
        JLabel uname=new JLabel("User Name:");
        uname.setLocation(300, 150);
        uname.setSize(100, 20);
        
        JLabel pass=new JLabel("Password:");
        pass.setLocation(300, 200);
        pass.setSize(100, 20);
        
        JTextField userName=new JTextField();
        userName.setLocation(400, 150);
        userName.setSize(200,20);
        
        JPasswordField password=new JPasswordField();
        password.setLocation(400, 200);
        password.setSize(200,20);
        
        JButton sub=new JButton("Submit");
        sub.setLocation(400, 250);
        sub.setSize(100,30);
        sub.setBackground(Color.orange);
        
        sub.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                user_name=userName.getText();
                pass_word=password.getText();
                
                try{
                    BufferedReader in = new BufferedReader(new FileReader("database/userinfo.csv"));
                    String line;
                    int ind=0;
                    int flag=0;
                    String[] var=null;
                    while((line = in.readLine()) != null){
                        
                        var=line.split(",");
                        if(var[3].equals(user_name) && var[4].equals(pass_word)){
                            current_user_name=user_name;
                            current_user_dept=var[7];
                            current_user_type=var[1];
                            setLog();
                            flag=1;
                            break;
                        }
                        else{
                            continue;
                        }
                    }
                    if(flag==1){
                        setVisible(false);
                        if(var[1].equals("Supervisor")){
                            sup_obj=new Supervisor();
                            sup_obj.setVisible(true);
                        }
                        else if(var[1].equals("Admin")){
                            admin_obj=new Admin();
                            admin_obj.setVisible(true);
                        }
                        else if(var[1].equals("Staffer")){
                            staff_obj=new Staff();
                            staff_obj.setVisible(true);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog (null, "Invalid Credentials!", "Invalid", JOptionPane.INFORMATION_MESSAGE);
                        userName.setText("");
                        password.setText("");
                    }
                    
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                
            }
            
        });
        
        add(title);
        add(userName);
        add(password);
        add(uname);
        add(pass);
        add(sub);
        
    }
}
