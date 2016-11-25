/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fms_system;

import java.awt.Event;
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
public class Admin extends JFrame{
    int width=800;
    int height=500;
    ShowPendingRequests update_obj=new ShowPendingRequests();
    Task task_obj;
    showEmployee employee_obj;
    employeeStatus status_obj;
    showLeaveRequests leave_obj;
    public Admin(){
        
        super("Admin Interface");
        setSize(width,height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        
        ///label for home
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
        
        
        ///employee button
        JButton view_employee=new JButton("View Employees");
        view_employee.setLocation(50,50);
        view_employee.setSize(250,50);
        view_employee.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                employee_obj=new showEmployee();
                employee_obj.setVisible(true);
                setVisible(false);
            }
            
        });
        add(view_employee);
        
        
        //pending requests
        JButton inquire=new JButton("Show Pending Requests");
        inquire.setLocation(50,110);
        inquire.setSize(250,50);
        inquire.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                update_obj.setVisible(true);
            }
        });
        add(inquire);
        
        
        //assign tasks
        JButton Task_Assign=new JButton("Assign Tasks");
        Task_Assign.setLocation(50,170);
        Task_Assign.setSize(250,50);
        Task_Assign.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                task_obj=new Task();
                task_obj.setVisible(true);
            }
        });
        add(Task_Assign);
        
        //view employee status
        JButton Employee_Status=new JButton("Employee Status");
        Employee_Status.setLocation(50,230);
        Employee_Status.setSize(250,50);
        Employee_Status.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                status_obj=new employeeStatus();
                status_obj.setVisible(true);
            }
        });
        add(Employee_Status);
        JButton leave_req=new JButton("Show Leave Requests");
        leave_req.setLocation(50,290);
        leave_req.setSize(250,50);
        leave_req.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                leave_obj=new showLeaveRequests();
                leave_obj.setVisible(true);
            }
        });
        add(leave_req);
        
        JButton logisButton=new JButton("Logistic Approval Requests");
        logisButton.setLocation(50,350);
        logisButton.setSize(250,50);
        logisButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                new Logistic_Approval_Request().setVisible(true);
            }
        });
        add(logisButton);

    }
}
