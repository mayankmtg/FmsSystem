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
public class Registeration extends JFrame{
    int width=800;
    int height=500;
    public Registeration(){
        super("Registeration");
        setSize(width,height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        
        JLabel title=new JLabel("Registeration Details",SwingConstants.CENTER);
        title.setLocation(300, 50);
        title.setSize(200, 40);
        add(title);
        
        JLabel ID=new JLabel("ID:");
        ID.setLocation(300, 100);
        ID.setSize(100, 20);
        add(ID);
        
        JLabel Type=new JLabel("Type:");
        Type.setLocation(300, 150);
        Type.setSize(100, 20);
        add(Type);
        
        JLabel  Name=new JLabel("Name:");
        Name.setLocation(300, 200);
        Name.setSize(100, 20);
        add(Name);
        
        JLabel uname=new JLabel("User Name:");
        uname.setLocation(300, 250);
        uname.setSize(100, 20);
        add(uname);
        
        JLabel pass=new JLabel("Password:");
        pass.setLocation(300, 300);
        pass.setSize(100, 20);
        add(pass);
        
        JLabel DOB=new JLabel("DOB:");
        DOB.setLocation(300, 350);
        DOB.setSize(100, 20);
        add(DOB);
        
        JLabel addr=new JLabel("Address:");
        addr.setLocation(300, 400);
        addr.setSize(100, 20);
        add(addr);
        
        JLabel dept=new JLabel("Department:");
        dept.setLocation(300, 450);
        dept.setSize(100, 20);
        add(dept);
        
        ///////////////////////////////////////
        
        JTextField ID_text=new JTextField();
        ID_text.setLocation(400, 100);
        ID_text.setSize(200,20);
        add(ID_text);
        
        JTextField Type_text=new JTextField();
        Type_text.setLocation(400, 150);
        Type_text.setSize(200,20);
        add(Type_text);
        
        JTextField Name_text=new JTextField();
        Name_text.setLocation(400, 200);
        Name_text.setSize(200,20);
        add(Name_text);
        
        JTextField userName=new JTextField();
        userName.setLocation(400, 250);
        userName.setSize(200,20);
        add(userName);
        
        JPasswordField password=new JPasswordField();
        password.setLocation(400, 300);
        password.setSize(200,20);
        add(password);
        
        JTextField DOB_text=new JTextField();
        DOB_text.setLocation(400, 350);
        DOB_text.setSize(200,20);
        add(DOB_text);
        
        JTextField addr_text=new JTextField();
        addr_text.setLocation(400, 400);
        addr_text.setSize(200,20);
        add(addr_text);
        
        JTextField dept_text=new JTextField();
        dept_text.setLocation(400, 450);
        dept_text.setSize(200,20);
        add(dept_text);
        
        JButton login=new JButton("Submit");
        login.setLocation(300, 470);
        login.setSize(200,40);
        login.setBackground(Color.orange);
        
        
        
    }
}
