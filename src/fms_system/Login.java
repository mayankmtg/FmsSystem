/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fms_system;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
        
        add(title);
        add(userName);
        add(password);
        add(uname);
        add(pass);
        add(sub);
        
    }
}
