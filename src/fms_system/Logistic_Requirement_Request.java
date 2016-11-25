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
            reader = new BufferedReader(new FileReader("database/Tasks.csv"));
            while((line = reader.readLine()) != null){
                if(line.split(",")[8].equals(Login.getCurrentUser())){
                    correspondingTask.add(line.split(",")[0]);
                }
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
    
    public void sendRequest(String userData){
        try{
            PrintWriter writer = new PrintWriter(new FileWriter("database/LogisticRequirement.csv",true));
            writer.println(userData);
            writer.close();
        
        } catch (Exception e) {}
        JOptionPane.showMessageDialog (null, "Logistic Requirement Request Sent", "Thank You", JOptionPane.INFORMATION_MESSAGE);
        
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
        JButton view=new JButton("Send Logistic Requirement Request");
        view.setLocation(50,550);
        view.setSize(400,50);
        view.setBackground(Color.green);
        view.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                String userData=id_field.getText()+","+requirements+","+taskReference.getSelectedItem().toString();
                userData+=","+Login.getCurrentUser()+","+"For_Approval";
                sendRequest(userData);
            }
            
        });
        add(view);
        
        JButton back=new JButton("Back");
        back.setLocation(50,610);
        back.setSize(400,50);
        back.setForeground(Color.white);
        back.setBackground(Color.red);
        back.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                
            }
        });
        add(back);
    }

}
