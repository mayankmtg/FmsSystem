package fms_system;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class TaskReport extends JFrame {
    int width=1000;
    int height=700;
    Login login_obj;
    Staff staff_obj;
    public void writeFile(String userData){
        try{
            PrintWriter writer = new PrintWriter(new FileWriter("database/TaskReports.csv",true));
            writer.println(userData);
            writer.close();
        
        } catch (Exception e) {}
        JOptionPane.showMessageDialog (null, "Task Report Successfully Generated.", "Thank You", JOptionPane.INFORMATION_MESSAGE);
    }
    public TaskReport(){
     super("Task Report Form");
     setSize(width,height);
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(null);
    JLabel head=new JLabel("Task Report");
    add(head);
    head.setSize(400,200);
    head.setLocation(400,50);
    JPanel panel=new JPanel(new GridLayout(7,2));    
    JLabel[] labels=new JLabel[7];
    labels[0]=new JLabel("Task ID");
    labels[1]=new JLabel("Task Name");
    labels[2]=new JLabel("Task Description");
    labels[3]=new JLabel("Items used");
    labels[4]=new JLabel("Time Taken");
    labels[5]=new JLabel("Comments");
    labels[6]=new JLabel("To Whom");
    JTextField[] fields=new JTextField[7];
    for(int i=0;i<7;i++){
        fields[i]=new JTextField();
        panel.add(labels[i]);
        panel.add(fields[i]);
    }
    add(panel);
    panel.setSize(300,300);
    panel.setLocation(300,200);
    JButton submit=new JButton("Submit");
    JButton back=new JButton("Back");
    submit.setLocation(400, 550);
    back.setLocation(550,550);
    
    JButton log_out=new JButton("Log Out");
    log_out.setLocation(900,0);
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
    submit.setSize(100,40);
    submit.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e) {
             String userData="";
             for(int i=0;i<6;i++){
                 userData+=fields[i].getText();
                 userData+=",";
             }
             userData+=fields[6].getText();
             writeFile(userData);
        }
    });
    
    back.setSize(100,40);
    back.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e) {
             staff_obj=new Staff();
             setVisible(false);
             staff_obj.setVisible(true);
         }
    });
    add(submit);
    add(back);
           
    
    
    }


}
