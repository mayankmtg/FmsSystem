package fms_system;

import java.awt.BorderLayout;
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
import java.util.List;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mayank
 * @author amit
 */
public class showEmployee extends JFrame{
    int width=1000,height=700;
    JTable contactTable;
    DefaultTableModel tableModel;
    JScrollPane table_pane;
    JPanel table_panel;
    JPanel button_panel;
    public void loadTable(){
        
        String line;
        BufferedReader reader;
        BufferedReader reader2;
            try{       
                reader = new BufferedReader(new FileReader("database/Supervisors.csv"));
                reader2= new BufferedReader(new FileReader("database/Staffer.csv"));
                while((line = reader.readLine()) != null){
                   tableModel.addRow(line.split(",")); 
                }
                reader.close();
                while((line = reader2.readLine()) != null){
                   tableModel.addRow(line.split(",")); 
                }
                reader2.close();
             }
            catch(IOException e){
                JOptionPane.showMessageDialog(null, "Error");
                e.printStackTrace();
            }
    }
    
    public void deleteRequest(){
        
        int i=contactTable.getSelectedRow();
        int x=contactTable.getRowCount();
        int y=contactTable.getColumnCount();
        if(i>=0){
            tableModel.removeRow(i);
            x--;
            JOptionPane.showMessageDialog (null, "Request Rejected!!", "Done", JOptionPane.INFORMATION_MESSAGE);
        }
        
        try{
            PrintWriter writer = new PrintWriter(new FileWriter("database/registerRequests.csv",false));
            String userData="";
            int j,k;
            for(j=0;j<x;j++){
                System.out.println();
                for (k=0;k<y-1;k++){
                    userData+=contactTable.getModel().getValueAt(j,k).toString();
                    userData+=",";
                }
                userData+=contactTable.getModel().getValueAt(j,y-1);
                if(j!=x-1)
                    userData+="\r\n";
            }
            writer.println(userData);
            writer.close();
        } 
        catch (Exception e) {
        }
    }
    
    public void hire(){
        int i=contactTable.getSelectedRow();
        int x=contactTable.getRowCount();
        int y=contactTable.getColumnCount();
        
        try{
            PrintWriter writer = new PrintWriter(new FileWriter("database/userinfo.csv",true));
            
            PrintWriter writer2 = new PrintWriter(new FileWriter("database/Supervisors.csv",true));
            PrintWriter writer3 = new PrintWriter(new FileWriter("database/Staffer.csv",true));
            String userData="";
            for(int j=0;j<7;j++){
                userData+=contactTable.getModel().getValueAt(i, j) +",";
            }
            userData+=contactTable.getModel().getValueAt(i, 7);
            if(contactTable.getModel().getValueAt(i, 1).equals("Supervisor")){
                writer2.println(userData);
                writer2.close();
            }
            else{
                writer3.println(userData);
                writer3.close();
            }
            writer.println(userData);
            writer.close();
            
            
            if(i>=0){
                tableModel.removeRow(i);
                x--;
                JOptionPane.showMessageDialog (null, "Person Added To Database!!", "Done", JOptionPane.INFORMATION_MESSAGE);
            }
            PrintWriter writer1 = new PrintWriter(new FileWriter("database/registerRequests.csv",false));
            String userData1="";
            int j,k;
            for(j=0;j<x;j++){
                System.out.println();
                for (k=0;k<y-1;k++){
                    userData1+=contactTable.getModel().getValueAt(j,k).toString();
                    userData1+=",";
                }
                userData1+=contactTable.getModel().getValueAt(j,y-1);
                if(j!=x-1)
                    userData1+="\r\n";
            }
            
            writer1.println(userData1);
            writer1.close();
        } 
        catch (Exception e) {
        }
    }
    
    public showEmployee(){
        super("View Employee Database");
        setSize(width,height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        JButton Delete=new JButton("Reject Request");
        JButton Add =new JButton("Hire");
        //table setup
        String columns[] ={"ID", "Type","Name", "User Name", "Password", "DOB", "Address", "Departments" };
        tableModel = new DefaultTableModel(0,8); 
        tableModel.setColumnIdentifiers(columns);
        contactTable=new JTable(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        contactTable.getTableHeader().setReorderingAllowed(false);
        contactTable.setModel(tableModel);
        contactTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                Delete.setEnabled(true);
                Add.setEnabled(true);
            }
        });
        table_pane=new JScrollPane(contactTable);
        table_panel=new JPanel(new BorderLayout());
        table_panel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        table_panel.add(table_pane);
        table_panel.setLocation(0,100);
        table_panel.setSize(800,600);
        add(table_panel);
        
        //Button Panel
        button_panel=new JPanel(new GridLayout(4,1,3,100));
        JButton View=new JButton("Show Employees");
        View.setBackground(Color.cyan);
        View.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(tableModel.getRowCount()>0){
                    for(int i=tableModel.getRowCount()-1;i>-1;i--){
                        tableModel.removeRow(i);
                    }
                }
                loadTable();
            }
        });
        button_panel.add(View);
        
        Delete.setEnabled(false);
        Delete.setBackground(Color.green);
        Delete.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                deleteRequest();
                Delete.setEnabled(false);
                Add.setEnabled(false);
            }
        });
        button_panel.add(Delete); 
        
        Add.setEnabled(false);
        Add.setBackground(Color.green);
        Add.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                hire();
            }
        });
        button_panel.add(Add);
        
        //title
        JLabel title =new JLabel("Request Status");
        title.setLocation(400,50);
        title.setSize(300,20);
        title.setFont(new Font("Serif", Font.PLAIN, 24));
        add(title);
        
        JButton Back=new JButton("Back");
        Back.setBackground(Color.red);
        Back.setForeground(Color.white);
        Back.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                //admin_obj.setVisible(true);
                //setVisible(false);
            }
        });
        button_panel.add(Back);
        button_panel.setLocation(width-200,100);
        button_panel.setSize(200,600);
        add(button_panel);
    }
}