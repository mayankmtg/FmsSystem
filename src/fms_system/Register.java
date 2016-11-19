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
            add(Fields[i]);
        }
        
        Fields[3].setEnabled(false);
        Fields[2].addFocusListener(new java.awt.event.FocusListener() {
            public void focusGained(java.awt.event.FocusEvent focusEvent) {}
            public void focusLost(java.awt.event.FocusEvent focusEvent) {
                try {
                    if(Fields[2].getText().equals("")){
                        
                    }
                    else{
                        Fields[3].setText("" + Fields[2].getText() + Fields[0].getText() );
                    }
                } catch (ClassCastException ignored) {
                }
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
                        PrintWriter writer = new PrintWriter(new FileWriter("database/registerRequests.csv",true));
                        PrintWriter writer1 = new PrintWriter(new FileWriter("database/getId.csv"));
                        String userData="";
                        for(int i=0;i<7;i++){
                            userData+=Fields[i].getText() +",";
                        }
                        userData+=Fields[7].getText();
                        writer.println(userData);
                        writer1.println(Fields[0].getText());
                        writer.close();
                        writer1.close();
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
