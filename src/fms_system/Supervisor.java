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
public class Supervisor extends JFrame{
    int width=800;
    int height=500;
    AssignTask task_obj;
    public Supervisor(){
        
        super("Supervisor Interface");
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
        
        JButton inquire=new JButton("View Staff");
        inquire.setLocation(50,50);
        inquire.setSize(200,50);
        inquire.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                new showEmployee().setVisible(true);
                setVisible(false);
            }
            
        });
        add(inquire);
        
        JButton Assign_task=new JButton("Assign Task");
        Assign_task.setLocation(50,110);
        Assign_task.setSize(200,50);
        Assign_task.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                task_obj=new AssignTask();
                setVisible(false);
                task_obj.setVisible(true);
            }
            
        });
        add(Assign_task);
        
        JButton Logistics=new JButton("Show Leave Requests");
        Logistics.setLocation(50,170);
        Logistics.setSize(200,50);
        Logistics.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                new showLeaveRequests().setVisible(true);
                setVisible(false);
            }
            
        });
        add(Logistics);
        
        JButton Approve_Requests=new JButton("Approve Logistic Requests");
        Approve_Requests.setLocation(50,230);
        Approve_Requests.setSize(200,50);
        Approve_Requests.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                new Logistic_Approval_Request().setVisible(true);
            }
            
        });
        add(Approve_Requests);
        
        JButton Send_Requests=new JButton("Send Logistics Requests");
        Send_Requests.setLocation(50,290);
        Send_Requests.setSize(200,50);
        Send_Requests.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                new Logistic_Requirement_Request().setVisible(true);
                setVisible(false);
            }
            
        });
        add(Send_Requests);
        
        JButton Send_Leave=new JButton("Send Leave");
        Send_Leave.setLocation(50,350);
        Send_Leave.setSize(200,50);
        Send_Leave.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                new Leave().setVisible(true);
                setVisible(false);
            }
            
        });
        add(Send_Leave);
        
        JButton Task_reports=new JButton("View Task Reports");
        Task_reports.setLocation(50,410);
        Task_reports.setSize(200,50);
        Task_reports.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                new viewTaskReports().setVisible(true);
                setVisible(false);
            }
            
        });
        add(Task_reports);
        
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
        
        JButton show_task=new JButton("Show Tasks");
        show_task.setLocation(400, 260);
        show_task.setSize(200,50);
        show_task.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                new viewTasks().setVisible(true);
                setVisible(false);
            }
        });
        add(show_task);     
        
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
