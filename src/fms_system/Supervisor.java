/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fms_system;

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
    Task task_obj;
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
        
        JButton inquire=new JButton("Add/Delete/view Staff");
        inquire.setLocation(50,50);
        inquire.setSize(200,50);
        add(inquire);
        
        JButton Assign_task=new JButton("Assign Task");
        Assign_task.setLocation(50,110);
        Assign_task.setSize(200,50);
        Assign_task.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                task_obj=new Task();
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
        
        JButton Send_Requests=new JButton("Send requests");
        Send_Requests.setLocation(50,290);
        Send_Requests.setSize(200,50);
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
        add(Task_reports);
        
        
        
    }
}
