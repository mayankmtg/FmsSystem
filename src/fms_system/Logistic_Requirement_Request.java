/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fms_system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mayank
 * @author amit
 */

public class Logistic_Requirement_Request extends JFrame {
    int width=500;
    int height=700;
    JPanel logisPanel;
    String requirements="";
    public int setLogisID(){
        String s;
        int id=-1;
        try{
            BufferedReader in = new BufferedReader(new FileReader("database/getLogisID.csv"));
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
    public void setLogisFileID(int id){
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("database/getLogisID.csv"));
            writer.println(id);
            writer.close();
        } catch (IOException ex) {}
    }
    public String[] onetoten(){
        String [] s=new String[10];
        for(int i=0;i<10;i++){
            s[i]=""+(i+1);
        }
        return s;
    }
    public String[] getTaskIDs(){
        String line;
        BufferedReader reader;
        ArrayList<String> correspondingTask=new ArrayList<String>();
        try{       
            reader = new BufferedReader(new FileReader("database/persons/"+Login.getCurrentUser()+"/TasksAssigned.csv"));
            while((line = reader.readLine()) != null){
                correspondingTask.add(line.split(",")[0]);
            }
            reader.close();
        }   catch(IOException e){}
        String[] ans=new String[correspondingTask.size()];
        int ind=0;
        for(String s : correspondingTask){
            ans[ind++]=s;
        }
        return ans;
    }
    public void setRequirements(String s){
        this.requirements=s;
    }
    
    public void sendLogisticForStaff(String userData){
        try{
            PrintWriter writer = new PrintWriter(new FileWriter("database/persons/"+getSupName()+"/Logistics.csv",true));
            writer.println(userData);
            writer.close();
        } catch (Exception e) {}
        String line;
        BufferedReader reader;
        String write="";
        try{       
            reader = new BufferedReader(new FileReader("database/persons/"+getSupName()+"/Notification.csv"));
            line = reader.readLine();
            String[] var=line.split(",");
            var[1]=""+(Integer.parseInt(var[1])+1);
            write=var[0]+","+var[1]+","+var[2];
            reader.close();
            PrintWriter writer2 = new PrintWriter(new FileWriter("database/persons/"+getSupName()+"/Notification.csv"));
            writer2.println(write);
            writer2.close();
         }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error");
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog (null,"Your Logistic Request Has been Sent","Thank You", JOptionPane.INFORMATION_MESSAGE);
    }
    public void sendLogisticForSup(String userData){
        try{
            PrintWriter writer = new PrintWriter(new FileWriter("database/persons/admin1/Logistics.csv",true));
            writer.println(userData);
            writer.close();
        } catch (Exception e) {}
        
        String line;
        BufferedReader reader;
        String write="";
        try{       
            reader = new BufferedReader(new FileReader("database/persons/admin1/Notification.csv"));
            line = reader.readLine();
            String[] var=line.split(",");
            var[1]=""+(Integer.parseInt(var[1])+1);
            write=var[0]+","+var[1]+","+var[2];
            reader.close();
            PrintWriter writer2 = new PrintWriter(new FileWriter("database/persons/admin1/Notification.csv"));
            writer2.println(write);
            writer2.close();
         }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error");
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog (null,"Your Logistics Request Has been Sent","Thank You", JOptionPane.INFORMATION_MESSAGE);
    }
    public String getSupName(){
        String line;
        BufferedReader reader;
        String retString="";
        try{       
            reader = new BufferedReader(new FileReader("database/Supervisors.csv"));
            while((line = reader.readLine()) != null){
               if(line.split(",")[7].equals(Login.getCurrentDept())){
                   retString=line.split(",")[3];
                   break;
               }
            }
            reader.close();
         }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error");
            e.printStackTrace();
        }
        return retString;
    }
    public Logistic_Requirement_Request(){

        super("Logistic Requirement");
        setSize(width,height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        //title
        JLabel title=new JLabel("Logistic Requirements",SwingConstants.CENTER);
        title.setLocation(100, 40);
        title.setSize(200, 40);
        add(title);
        
        
        //panel for viewing structure
        JLabel id_label=new JLabel("Logistics ID:");
        
        JTextField id_field=new JTextField();
        id_field.setEnabled(false);
        id_field.setText(""+setLogisID());
        
        JLabel noi=new JLabel("No. of Items");
        
        JComboBox no_of_items=new JComboBox(onetoten());
        no_of_items.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                String requirements="";
                int x=Integer.parseInt(no_of_items.getSelectedItem().toString());
                JLabel Texts[]=new JLabel[x];
                JTextField Fields[]=new JTextField[x];
                JTextField Quants[]=new JTextField[x];
                JPanel op_panel=new JPanel(new GridLayout(x,3));
                for(int i=0;i<x;i++){
                    Texts[i]=new JLabel();
                    Texts[i].setText("Item "+(i+1));
                    Fields[i]=new JTextField();
                    Quants[i]=new JTextField();
                    op_panel.add(Texts[i]);
                    op_panel.add(Fields[i]);
                    op_panel.add(Quants[i]);
                }
                
                int n = JOptionPane.showConfirmDialog(null,op_panel, "Fill In the Form",JOptionPane.OK_CANCEL_OPTION);
                if (n == JOptionPane.OK_OPTION) {
                    for(int i=0;i<x;i++){
                        requirements+=Fields[i].getText();
                        requirements+="#";
                        requirements+=Quants[i].getText();
                        requirements+="%";
                    }
                }
                setRequirements(requirements);
            }
        });
        
        JLabel reference=new JLabel("Task Reference");
        JComboBox taskReference=new JComboBox(getTaskIDs());
        
        logisPanel=new JPanel(new GridLayout(3,2));
        logisPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        logisPanel.add(id_label);
        logisPanel.add(id_field);
        
        logisPanel.add(noi);
        logisPanel.add(no_of_items);
        
        logisPanel.add(reference);
        logisPanel.add(taskReference);
        
        logisPanel.setLocation(50,100);
        logisPanel.setSize(400,400);
        add(logisPanel);
        
        ///buttons
        JButton send=new JButton("Send Logistic Requirement Request");
        send.setLocation(50,550);
        send.setSize(400,50);
        send.setBackground(Color.green);
        send.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                String userData=id_field.getText()+","+requirements+","+taskReference.getSelectedItem().toString();
                userData+=","+Login.getCurrentUser()+","+"For_Approval";
                //sendRequest(userData);
                if(Login.getCurrentType().equals("Supervisor")){
                    sendLogisticForSup(userData);
                }
                else{
                    sendLogisticForStaff(userData);
                }
            }
        });
        add(send);
        
        JButton back=new JButton("Back");
        back.setLocation(50,610);
        back.setSize(400,50);
        back.setForeground(Color.white);
        back.setBackground(Color.red);
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
        add(back);
        
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
