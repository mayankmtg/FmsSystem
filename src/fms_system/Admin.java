/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fms_system;

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
    public Admin(){
        
        super("Admin Interface");
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
        
        JButton home=new JButton("Home");
        home.setLocation(50,50);
        home.setSize(250,50);
        add(home);
        
        JButton inquire=new JButton("Add/Delete/view Staff");
        inquire.setLocation(50,110);
        inquire.setSize(250,50);
        add(inquire);
        
        JButton inquire2=new JButton("Add/Delete/view Supervisor");
        inquire2.setLocation(50,170);
        inquire2.setSize(250,50);
        add(inquire2);
        
        JButton Approve_requests=new JButton("Approve Logistics requests");
        Approve_requests.setLocation(50,230);
        Approve_requests.setSize(250,50);
        add(Approve_requests);

    }
}
