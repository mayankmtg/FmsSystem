/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fms_system;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

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
    public void updateStatus1(String s){
        PrintWriter writer;
        try {
            writer = new PrintWriter(new FileWriter("database/persons/"+Login.getCurrentUser()+"/Status.csv"));
            writer.println(s);
            writer.close();
        } catch (IOException ex) {}
        
    }
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
                setVisible(false);
                new Logistic_Requirement_Request().setVisible(true);
            }
            
        });
        
        JButton Send_Leave=new JButton("Send Leave");
        Send_Leave.setLocation(50,110);
        Send_Leave.setSize(200,50);
        Send_Leave.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                new Leave().setVisible(true);
            }
            
        });
        add(Send_Leave);
        
        JButton Update_status=new JButton("Update Status");
        Update_status.setLocation(50,170);
        Update_status.setSize(200,50);
        Update_status.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                JPanel p1=new JPanel(new GridLayout(1,3));
                JRadioButton b1=new JRadioButton("Busy");
                JRadioButton b2=new JRadioButton("On Leave");
                JRadioButton b3=new JRadioButton("Available");
                ButtonGroup group = new ButtonGroup();
                group.add(b1);
                group.add(b2);
                group.add(b3);
                
                p1.add(b1);
                p1.add(b2);
                p1.add(b3);
                
                int result = JOptionPane.showConfirmDialog(null, p1,"Update Status", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        if(b1.isSelected()){
                            updateStatus1("Busy");
                        }
                        else if(b2.isSelected()){
                            updateStatus1("On_Leave");
                        }
                        else{
                            updateStatus1("Available");
                        }
                    }
                    else{
                        
                    }
            }
            
        });
        add(Update_status);
        
        JButton view_task=new JButton("View Tasks");
        view_task.setLocation(50,230);
        view_task.setSize(200,50);
        view_task.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                new viewTasks().setVisible(true);
                setVisible(false);
            }
            
        });
        add(view_task);
        
        JButton Task_report=new JButton("Generate Task Report");
        Task_report.setLocation(50,290);
        Task_report.setSize(200,50);
        Task_report.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                new TaskReport().setVisible(true);
                setVisible(false);
            }
            
        });
        add(Task_report);
        
        JButton notify=new JButton("Show Notifications");
        notify.setLocation(400, 200);
        notify.setSize(200,50);
        notify.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                new Notifications().setVisible(true);
                setVisible(false);
            }
        });
        add(notify);
        
        JButton log_out=new JButton("Log Out");
        log_out.setLocation(900,0);
        log_out.setSize(100,40);
        log_out.setBackground(Color.orange);
        log_out.setForeground(Color.white);
        add(log_out);
        log_out.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e) {
                 new Login().setVisible(true);
                 setVisible(false);
                 
             }
        });
        
    }
}
