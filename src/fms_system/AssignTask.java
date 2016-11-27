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
public class AssignTask extends JFrame{
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
    public void setTaskFileID(int id){
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("database/getTaskId.csv"));
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
        PrintWriter writer;
        String write="Busy till " + deadline;
        try{
            writer = new PrintWriter(new FileWriter("database/persons/"+staffname+"/Status.csv"));
            writer.print(write);
            writer.close();
        }   catch(IOException e){}
    }
    public boolean checkStatus(String staffname){
        String line;
        BufferedReader reader;
        try{       
            reader = new BufferedReader(new FileReader("database/persons/"+staffname+"/Status.csv"));
            line = reader.readLine();
            if(line.split(" ")[0].equals("Busy")){
                return false;
            }
            else if(line.split(" ")[0].equals("Available")){
                return true;
            }
            reader.close();
        }   catch(IOException e){}
        return false;
    }
    public void assignTask(String userData,String employeeName){
        try{
            PrintWriter writer = new PrintWriter(new FileWriter("database/persons/"+employeeName+"/TasksAssigned.csv",true));
            writer.println(userData);
            writer.close();
        
        } catch (Exception e) {}
        String line;
        BufferedReader reader;
        String write="";
        try{       
            reader = new BufferedReader(new FileReader("database/persons/"+employeeName+"/Notification.csv"));
            line = reader.readLine();
            String[] var=line.split(",");
            var[0]=""+(Integer.parseInt(var[0])+1);
            write=var[0]+","+var[1]+","+var[2];
            reader.close();
            PrintWriter writer2 = new PrintWriter(new FileWriter("database/persons/"+employeeName+"/Notification.csv"));
            writer2.println(write);
            writer2.close();
         }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error");
            e.printStackTrace();
        }
    }
    public AssignTask(){
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
                Fields[0].setText(""+AssignTask.this.setTaskID());
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
                    setTaskFileID(Integer.parseInt(Fields[0].getText()));
                    int check=0;
                    String userData="";
                    for(int i=0;i<8;i++){
                        userData+=Fields[i].getText() +",";
                    }
                    for(int i=0;i<selectedModel.getSize();i++){
                        update_status(selectedModel.getElementAt(i).toString(),Fields[7].getText());
                        assignTask(userData,selectedModel.getElementAt(i).toString());
                    }
                    
                    JOptionPane.showMessageDialog(null,"Task Successfully Assigned", "Done", JOptionPane.INFORMATION_MESSAGE);                    
                }
            });

            JButton back=new JButton("Back");
            back.setBackground(Color.red);
            back.setForeground(Color.white);
            back.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if(Login.getCurrentType().equals("Supervisor")){
                        new Supervisor().setVisible(true);
                        setVisible(false);
                    }
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
                    setTaskFileID(Integer.parseInt(Fields[0].getText()));
                    Fields[3].setText(dept_combo.getSelectedItem().toString());
                    Fields[4].setText(sup_combo.getSelectedItem().toString());
                    int check=0;
                    String userData="";
                    for(int i=0;i<8;i++){
                        userData+=Fields[i].getText() +",";
                    }
                    assignTask(userData,sup_combo.getSelectedItem().toString());
                    JOptionPane.showMessageDialog(null,"Task Successfully Assigned", "Done", JOptionPane.INFORMATION_MESSAGE);                    
                }
            });

            JButton back=new JButton("Back");
            back.setBackground(Color.red);
            back.setForeground(Color.white);
            back.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae) {
                    setVisible(false);
                    if(Login.getCurrentType().equals("Supervisor")){
                        new Supervisor().setVisible(true);
                    }
                    else if(Login.getCurrentType().equals("Admin")){
                        new Admin().setVisible(true);
                    }
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
}
