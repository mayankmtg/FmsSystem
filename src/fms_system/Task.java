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
    public void setTaskFile(int id){
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
    
    public Task(){
        
        super("Task Assigner");
        setSize(width,height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        
        JLabel title =new JLabel("Task Assigner");
        title.setLocation(400,50);
        title.setSize(300,30);
        title.setFont(new Font("Serif", Font.PLAIN, 24));
        add(title);
        
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
        
        JTextField Fields[]=new JTextField[8];
        
        String[] dept_vals={"", "Electricity","HVAC","Audio/Video","Security","Housekeeping"};
        JComboBox dept_combo=new JComboBox(dept_vals);
        
        JComboBox sup_combo=new JComboBox(sup_string());
        
        Left=new JPanel(new GridLayout(8,2,30,30));
        for(int i=0;i<7;i++){
            Fields[i]=new JTextField();
            if(i==0){
                Fields[0].setText(""+setTaskID());
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
        //Left.setBorder(BorderFactory.createLineBorder(Color.black));
        add(Left);
        
        
        
        //submit Panel
        JButton sub=new JButton("Submit");
        sub.setBackground(Color.yellow);
        sub.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                setTaskFile(Integer.parseInt(Fields[0].getText()));
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
        
        
        //Staffer
        JButton getWorkers=new JButton("Add Selected Worker");
        
        JPanel staff_combos=new JPanel(new GridLayout(1,2,10,10));
        staff_combos.setLocation(600,230);
        staff_combos.setSize(350,300);
        
        
        DefaultListModel listModel = new DefaultListModel();
        DefaultListModel selectedModel = new DefaultListModel();
        
        JList list_staff=new JList();
        list_staff.setModel(listModel);
        list_staff.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION );
        staff_combos.add(list_staff);
        
        JList selected_staff=new JList();
        selected_staff.setModel(selectedModel);
        staff_combos.add(selected_staff);
        
        add(staff_combos);
        String line;
        BufferedReader reader;
        ArrayList<String> staff=new ArrayList<>();
        try{       
            reader = new BufferedReader(new FileReader("database/Staffer.csv"));
            while((line = reader.readLine()) != null){
               staff.add(line.split(",")[3]);
            }
            reader.close();
         }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error");
            e.printStackTrace();
        }
        for(String s:staff){
            listModel.addElement(s);
        }
        getWorkers.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                ArrayList<String> task_staff=(ArrayList<String>) list_staff.getSelectedValuesList();
                for(String s: task_staff){
                    selectedModel.addElement(s);
                }
            }
            
        });
        JPanel nos=new JPanel(new GridLayout(1,1,30,50));
        nos.add(getWorkers);
        nos.setLocation(600,200);
        nos.setSize(350,30);
        add(nos);
        
        //combos
        
        
        
    }
    
}
