/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fms_system;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    Login login_obj=new Login();
    
    public Registeration(){
        super("Registeration");
        setSize(width,height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        
        JLabel title=new JLabel("Registeration Details",SwingConstants.CENTER);
        title.setLocation(300, 40);
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
        
        JTextField ID_text=new JTextField();
        ID_text.setLocation(400, 90);
        ID_text.setSize(200,20);
        ID_text.setEnabled(false);
        add(ID_text);
        
        JTextField Type_text=new JTextField();
        Type_text.setLocation(400, 140);
        Type_text.setSize(200,20);
        Type.requestFocus();
        add(Type_text);
        
        JTextField Name_text=new JTextField();
        Name_text.setLocation(400, 190);
        Name_text.setSize(200,20);
        add(Name_text);
        
        JTextField userName=new JTextField();
        userName.setLocation(400, 240);
        userName.setSize(200,20);
        add(userName);
        
        JPasswordField password=new JPasswordField();
        password.setLocation(400, 290);
        password.setSize(200,20);
        add(password);
        
        JTextField DOB_text=new JTextField();
        DOB_text.setLocation(400, 340);
        DOB_text.setSize(200,20);
        add(DOB_text);
        
        JTextField addr_text=new JTextField();
        addr_text.setLocation(400, 390);
        addr_text.setSize(200,20);
        add(addr_text);
        
        JTextField dept_text=new JTextField();
        dept_text.setLocation(400, 440);
        dept_text.setSize(200,20);
        add(dept_text);
        
        JButton sub=new JButton("Submit");
        sub.setLocation(350, 470);
        sub.setSize(200,20);
        sub.setBackground(Color.green);
        
        sub.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                login_obj.setVisible(true);
            }
            
        });
        add(sub);
        
        
    }
}
