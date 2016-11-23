/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fms_system;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

/**
 *
 * @author mayank
 * @author amit
 */
public class Task extends JFrame{
    int width=1000;
    int height=700;
    JPanel Left;
    public int setTaskID(){
        String s;
        int id=-1;
        try{
            BufferedReader in = new BufferedReader(new FileReader("database/getTaskId.csv"));
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
    public void setTaskID(int id){
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("database/registerRequests.csv"));
            writer.println(id);
            writer.close();
        } catch (IOException ex) {}
    }
    public String[] sup_string(){
        int id=-1;
        ArrayList<String> supervisors=new ArrayList<String>();
        try{
            BufferedReader in = new BufferedReader(new FileReader("database/Supervisors.csv"));
            String line;
            while((line = in.readLine()) != null){
                String[] var = line.split(",");
                supervisors.add(var[3]);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            String[] s=new String[supervisors.size()];
            int ind=0;
            for (String k: supervisors){
                s[ind++]=k;
            }
            return s;
        }
    }
    public ArrayList<String> getStaffList(){
        String line;
        BufferedReader reader;
        ArrayList<String> staff=new ArrayList<>();
        try{       
            reader = new BufferedReader(new FileReader("database/Staffer.csv"));
            while((line = reader.readLine()) != null){
                if(line.split(",")[7].equals(Login.getCurrentDept())){
                    staff.add(line.split(",")[3]);
                }
            }
            reader.close();
        }   catch(IOException e){}
        return staff;
    }
    public void update_status(String staffname, String deadline){
        String line;
        BufferedReader reader;
        PrintWriter writer;
        String write="";
        try{
            reader = new BufferedReader(new FileReader("database/employeeStatus.csv"));
            while((line = reader.readLine()) != null){
                if(line.split(",")[0].equals(staffname)){
                    String[] var=line.split(",");
                    write+=var[0]+","+var[1]+","+var[2]+",";
                    write+="Busy till " + deadline;
                }
                else{
                    write+=line;
                }
                write+="\r\n";
            }
            reader.close();
            writer = new PrintWriter(new FileWriter("database/employeeStatus.csv"));
            writer.print(write);
            writer.close();
        }   catch(IOException e){}
    }
    public boolean checkStatus(String staffname){
        String line;
        BufferedReader reader;
        try{       
            reader = new BufferedReader(new FileReader("database/employeeStatus.csv"));
            while((line = reader.readLine()) != null){
                if(line.split(",")[0].equals(staffname)){
                    if(line.split(",")[3].split(" ")[0].equals("Busy")){
                        return false;
                    }
                    else if(line.split(",")[3].split(" ")[0].equals("Available")){
                        return true;
                    }
                }
            }
            reader.close();
        }   catch(IOException e){}
        return false;
    }
    
    public Task(){
        //frame settings
        super("Task Assigner");
        setSize(width,height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        //title
        JLabel title =new JLabel("Task Assigner");
        title.setLocation(400,50);
        title.setSize(300,30);
        title.setFont(new Font("Serif", Font.PLAIN, 24));
        add(title);
        
        //labels
        JLabel Texts[]=new JLabel[8];
        Texts[0]=new JLabel("Task ID:");
        Texts[1]=new JLabel("TaskName:");
        Texts[2]=new JLabel("Task Desc.:");
        Texts[3]=new JLabel("Task Dept.:");
        Texts[4]=new JLabel("Supervisor:");
        Texts[5]=new JLabel("Equipment:");
        Texts[6]=new JLabel("Status:");
        Texts[7]=new JLabel("Deadline:");
        
        ///////////////////////////////////////
        //Fields
        JTextField Fields[]=new JTextField[8];
        
        /*Combo Boxes instead of fields*/
        String[] dept_vals={"", "Electricity","HVAC","Audio/Video","Security","Housekeeping"};
        JComboBox dept_combo=new JComboBox(dept_vals);
        JComboBox sup_combo=new JComboBox(sup_string());
        
        /*panel for the addition of labels and fields*/
        Left=new JPanel(new GridLayout(8,2,30,30));
        for(int i=0;i<8;i++){
            Fields[i]=new JTextField();
            if(i==0){
                Fields[0].setText(""+Task.this.setTaskID());
                Fields[0].setEnabled(false);
            }
            Fields[i].setSize(200,20);
            Texts[i].setSize(200,20);
            Left.add(Texts[i]);
            if(i==3){
                if(Login.getCurrentType().equals("Supervisor")){
                    Left.add(Fields[i]);
                    Fields[i].setEnabled(false);
                    Fields[i].setText(Login.getCurrentDept());
                }
                else if(Login.getCurrentType().equals("Admin")){
                    Left.add(dept_combo);
                }
            }
            else if(i==4){
                if(Login.getCurrentType().equals("Supervisor")){
                    Left.add(Fields[i]);
                    Fields[i].setEnabled(false);
                    Fields[i].setText(Login.getCurrentUser());
                }
                else if(Login.getCurrentType().equals("Admin")){
                    Left.add(sup_combo);
                }        
            }
            else{            
                Left.add(Fields[i]);
            }
        }
        Left.setSize(450,450);
        Left.setLocation(50,200);
        add(Left);
        
        
        //Staff lists when Supervisor logs in
            
        if(Login.getCurrentType().equals("Supervisor")){
            JButton getWorkers=new JButton("---Add Selected Worker--->");
            getWorkers.setBackground(Color.black);
            getWorkers.setForeground(Color.white);
            getWorkers.setLocation(600,200);
            getWorkers.setSize(350,30);

            JLabel left_list=new JLabel("All Workers of " + Login.getCurrentDept());
            JLabel right_list=new JLabel("Selected Workers");

            JPanel staffers=new JPanel(new GridLayout(2,2,10,10));
            staffers.setLocation(600,240);
            staffers.setSize(350,250);
            staffers.add(left_list);
            staffers.add(right_list);

            DefaultListModel listModel = new DefaultListModel();
            DefaultListModel selectedModel = new DefaultListModel();

            JList list_staff=new JList();
            list_staff.setModel(listModel);
            staffers.add(list_staff);

            JList selected_staff=new JList();
            selected_staff.setModel(selectedModel);
            staffers.add(selected_staff);

            //setting data to the left list
            ArrayList<String> staff=getStaffList();
            for(String s:staff){
                listModel.addElement(s);
            }
            
            //pressing button to shift worker
            getWorkers.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if(checkStatus(list_staff.getSelectedValue().toString())){
                        selectedModel.addElement(list_staff.getSelectedValue().toString());
                        listModel.removeElementAt(list_staff.getSelectedIndex());
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"This Staffer is busy", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                    }
                }

            });
            add(getWorkers);
            add(staffers);
            
            //buton panel
            JButton sub=new JButton("Assign Task");
            sub.setBackground(Color.yellow);
            sub.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae) {
                    setTaskID(Integer.parseInt(Fields[0].getText()));
                    for(int i=0;i<selectedModel.getSize();i++){
                        update_status(selectedModel.getElementAt(i).toString(),Fields[7].getText());
                    }
                }
            });

            JButton back=new JButton("Back");
            back.setBackground(Color.red);
            back.setForeground(Color.white);
            back.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae) {
                    //code after the submit of the Task
                }
            });

            JPanel sub_panel=new JPanel(new GridLayout(1,2,30,30));
            sub_panel.add(sub);
            sub_panel.add(back);
            sub_panel.setSize(350,30);
            sub_panel.setLocation(600,600);
            add(sub_panel);
        }
        else{
            JButton sub=new JButton("Assign Task");
            sub.setBackground(Color.yellow);
            sub.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae) {
                    setTaskID(Integer.parseInt(Fields[0].getText()));
                    
                    ////////////////////
                    //send supervisor notificatio//
                    /////////////////
                }
            });

            JButton back=new JButton("Back");
            back.setBackground(Color.red);
            back.setForeground(Color.white);
            back.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae) {
                    //code after the submit of the Task
                }
            });

            JPanel sub_panel=new JPanel(new GridLayout(1,2,30,30));
            sub_panel.add(sub);
            sub_panel.add(back);
            sub_panel.setSize(350,30);
            sub_panel.setLocation(600,600);
            add(sub_panel);
            
        }
        
        //submit and back buttons
            JButton sub=new JButton("Assign Task");
            sub.setBackground(Color.yellow);
            sub.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae) {
                    setTaskID(Integer.parseInt(Fields[0].getText()));
                }
            });

            JButton back=new JButton("Back");
            back.setBackground(Color.red);
            back.setForeground(Color.white);
            back.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae) {
                    //code after the submit of the Task
                }
            });

            JPanel sub_panel=new JPanel(new GridLayout(1,2,30,30));
            sub_panel.add(sub);
            sub_panel.add(back);
            sub_panel.setSize(350,30);
            sub_panel.setLocation(600,600);
            add(sub_panel);
    }
}
