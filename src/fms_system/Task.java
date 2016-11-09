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
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author mayank
 * @author amit
 */
public class Task extends JFrame{
    int width=800;
    int height=500;
    public Task(){
        
        super("Task Assigner");
        setSize(width,height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        
        JLabel title=new JLabel("Task Assignment",SwingConstants.CENTER);
        title.setLocation(300, 40);
        title.setSize(200, 40);
        add(title);
        
        JLabel ID=new JLabel("Task ID:");
        ID.setLocation(300, 90);
        ID.setSize(100, 20);
        add(ID);
        
        JLabel TaskName=new JLabel("TaskName:");
        TaskName.setLocation(300, 140);
        TaskName.setSize(100, 20);
        add(TaskName);
        
        JLabel  TaskDescDept=new JLabel("Task Desc. Dept.:");
        TaskDescDept.setLocation(300, 190);
        TaskDescDept.setSize(100, 20);
        add(TaskDescDept);
        
        JLabel Supervisor=new JLabel("Supervisor:");
        Supervisor.setLocation(300, 240);
        Supervisor.setSize(100, 20);
        add(Supervisor);
        
        JLabel noofStaff=new JLabel("No. of Staff:");
        noofStaff.setLocation(300, 290);
        noofStaff.setSize(100, 20);
        add(noofStaff);
        
        JLabel equip=new JLabel("Equipment:");
        equip.setLocation(300, 340);
        equip.setSize(100, 20);
        add(equip);
        
        JLabel status=new JLabel("Status:");
        status.setLocation(300, 390);
        status.setSize(100, 20);
        add(status);
        
        JLabel deadline=new JLabel("Deadline:");
        deadline.setLocation(300, 440);
        deadline.setSize(100, 20);
        add(deadline);
        
        ///////////////////////////////////////
        
        JTextField ID_text=new JTextField();
        ID_text.setLocation(400, 90);
        ID_text.setSize(200,20);
        ID_text.setEnabled(false);
        add(ID_text);
        
        JTextField taskName_text=new JTextField();
        taskName_text.setLocation(400, 140);
        taskName_text.setSize(200,20);
        TaskName.requestFocus();
        add(taskName_text);
        
        JTextField taskDesc_text=new JTextField();
        taskDesc_text.setLocation(400, 190);
        taskDesc_text.setSize(200,20);
        add(taskDesc_text);
        
        JTextField supText=new JTextField();
        supText.setLocation(400, 240);
        supText.setSize(200,20);
        add(supText);
        
        JTextField staffText=new JTextField();
        staffText.setLocation(400, 290);
        staffText.setSize(200,20);
        add(staffText);
        
        JTextField Equip_text=new JTextField();
        Equip_text.setLocation(400, 340);
        Equip_text.setSize(200,20);
        add(Equip_text);
        
        JTextField statusText=new JTextField();
        statusText.setLocation(400, 390);
        statusText.setSize(200,20);
        add(statusText);
        
        JTextField deadlineText=new JTextField();
        deadlineText.setLocation(400, 440);
        deadlineText.setSize(200,20);
        add(deadlineText);
        
        JButton sub=new JButton("Submit");
        sub.setLocation(350, 470);
        sub.setSize(200,20);
        sub.setBackground(Color.yellow);
        
        sub.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                //code after the submit of the Task
            }
            
        });
        add(sub);
        
    }
    
}
