/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fms_system;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;

/**
 *
 * @author mayank
 * @author amit
 */
public class Staff extends JFrame{
    int width=800;
    int height=500;
    Leave leave_obj;
    Logistic_Requirement_Request obj;
    TaskReport obj1;
    Login login_obj;
    public Staff(){
        
        super("Staff Interface");
        setSize(width,height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        
        JLabel date=new JLabel();
        Calendar now= Calendar.getInstance();
        int month = now.get(Calendar.MONTH);
        int day = now.get(Calendar.DAY_OF_MONTH);
        int year = now.get(Calendar.YEAR);
        date.setText("Today's Date: " + day +"/"+ month +"/"+ year);
        date.setLocation(500,50);
        date.setSize(250,20);
        add(date);
        
        JButton log_out=new JButton("Log Out");
    log_out.setLocation(700,0);
    log_out.setSize(100,40);
    log_out.setBackground(Color.orange);
    log_out.setForeground(Color.white);
    add(log_out);
    log_out.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e) {
             login_obj=new Login();
             setVisible(false);
             login_obj.setVisible(true);
         }
        
    });
        JLabel time=new JLabel();
        int h = now.get(Calendar.HOUR_OF_DAY);
        int m = now.get(Calendar.MINUTE);
        //int s = now.get(Calendar.SECOND);
        time.setText("Time: "+h+":"+m);
        time.setLocation(500, 80);
        time.setSize(250,20);
        add(time);
        
        JButton Send_Requests=new JButton("Send Logistic requests");
        Send_Requests.setLocation(50,50);
        Send_Requests.setSize(200,50);
        add(Send_Requests);
        Send_Requests.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                obj=new Logistic_Requirement_Request();
                setVisible(false);
                obj.setVisible(true);
            }
            
        });
        
        JButton Send_Leave=new JButton("Send Leave");
        Send_Leave.setLocation(50,110);
        Send_Leave.setSize(200,50);
        add(Send_Leave);
        
        JButton Update_status=new JButton("Update Status");
        Update_status.setLocation(50,170);
        Update_status.setSize(200,50);
        add(Update_status);
        
        Send_Leave.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                leave_obj=new Leave();
                leave_obj.setVisible(true);
            }
            
        });
        
        JButton task=new JButton("Task Report");
        task.setLocation(50,230);
        task.setSize(200,50);
        add(task);
        task.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                obj1=new TaskReport();
                setVisible(false);
                obj1.setVisible(true);
            }
            
        });
    }
}
