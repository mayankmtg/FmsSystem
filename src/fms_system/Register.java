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
        add(Fields[0]);
        for(int i=1;i<8;i++){
            Fields[i]=new JTextField();
            Fields[i].setLocation(400, 90+50*i);
            Fields[i].setSize(200,20);
            add(Fields[i]);
        }
        JButton sub=new JButton("Submit");
        sub.setLocation(200, 470);
        sub.setSize(200,20);
        sub.setBackground(Color.green);
        sub.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                int check=0;
                try{
                    BufferedReader in = new BufferedReader(new FileReader("database/userinfo.csv"));
                    String line;
                    check=0;
                    while((line = in.readLine()) != null){
                        String[] var = line.split(",");
                        if(var[3].equals(Fields[3].getText())){
                            check=1;
                            break;
                        }
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                if(check==1){
                    JOptionPane.showMessageDialog (null, "User Name is Taken!! Please change User Name", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                    Fields[3].setText("");
                    Fields[3].requestFocus();
                }
                else{
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
