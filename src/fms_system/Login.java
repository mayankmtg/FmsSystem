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
    public static String superUser;// this contains the username of the person who has logged in
    String user_name;
    String pass_word;
    Supervisor sup_obj=new Supervisor();
    Staff staff_obj=new Staff();
    Admin admin_obj=new Admin();
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
                            superUser=user_name;
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
                            sup_obj.setVisible(true);
                        }
                        else if(var[1].equals("Admin")){
                            admin_obj.setVisible(true);
                        }
                        else if(var[1].equals("Staff")){
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
