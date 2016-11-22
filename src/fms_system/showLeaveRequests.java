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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class showLeaveRequests extends JFrame {
    int width=1000;
    int height=700;
    JTable leaveTable;
    DefaultTableModel tableModel;
    JScrollPane table_pane;
    JPanel table_panel;
    JPanel button_panel;
    Admin admin_obj;
    
    public void loadTable(){
        
        String line;
        BufferedReader reader;
            try{       
                reader = new BufferedReader(new FileReader("database/leave.csv"));
                while((line = reader.readLine()) != null){
                   if(line.split(",")[1].equals("Admin"))
                        tableModel.addRow(line.split(",")); 
                }
                reader.close();
             }
            catch(IOException e){
                JOptionPane.showMessageDialog(null, "Error");
                e.printStackTrace();
            }
    }
    public void deleteRequest(){
        
        int i=leaveTable.getSelectedRow();
        int x=leaveTable.getRowCount();
        int y=leaveTable.getColumnCount();
        if(i>=0){
            tableModel.removeRow(i);
            x--;
            JOptionPane.showMessageDialog (null, "Request Rejected!!", "Done", JOptionPane.INFORMATION_MESSAGE);
        }
        
        try{
            PrintWriter writer = new PrintWriter(new FileWriter("database/leave.csv",false));
            String userData="";
            int j,k;
            for(j=0;j<x;j++){
                System.out.println();
                for (k=0;k<y-1;k++){
                    userData+=leaveTable.getModel().getValueAt(j,k).toString();
                    userData+=",";
                }
                userData+=leaveTable.getModel().getValueAt(j,y-1);
                if(j!=x-1)
                    userData+="\r\n";
            }
            writer.println(userData);
            writer.close();
        } 
        catch (Exception e) {
        }
    }
    
    public void acccept() throws IOException{
        int i=leaveTable.getSelectedRow();
        int x=leaveTable.getRowCount();
        int y=leaveTable.getColumnCount();
        BufferedReader reader;
        if(i>=0){
            reader = new BufferedReader(new FileReader("database/employeeStatus.csv"));
            PrintWriter writer1 = new PrintWriter(new FileWriter("database/employeeStatus.csv",false)); 
            String line;
            String userData2="";
            int j,k;
            while((line=reader.readLine()) != null){
                String[] b=line.split(",");
                if(b[0].equals(leaveTable.getValueAt(i,0)))
                {
                    b[3]="On_leave till "+leaveTable.getValueAt(i,4);
                }
                for(j=0;j<4;j++)
                {
                    if(j!=3)
                        userData2+=b[j]+",";
                    else
                        userData2+=b[j];
                }
                userData2+="\r\n";
            }
            writer1.println(userData2);
            
        }
            tableModel.removeRow(i);
            x--;
            JOptionPane.showMessageDialog (null, "Request Accepted!!", "Done", JOptionPane.INFORMATION_MESSAGE);
            PrintWriter writer = new PrintWriter(new FileWriter("database/leave.csv",false));
            String userData="";
            int j,k;
            for(j=0;j<x;j++){
                System.out.println();
                for (k=0;k<y-1;k++){
                    userData+=leaveTable.getModel().getValueAt(j,k).toString();
                    userData+=",";
                }
                userData+=leaveTable.getModel().getValueAt(j,y-1);
                if(j!=x-1)
                    userData+="\r\n";
            }
            writer.println(userData);
            writer.close();
        }   
    
    public showLeaveRequests(){
        super("Update Employee Database");
        setSize(width,height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        JButton Delete=new JButton("Reject Request");
        JButton Add =new JButton("Accept Request");
        String columns[] ={"UserName","Whom","Reason","From","To" };
        tableModel = new DefaultTableModel(0,5); 
        tableModel.setColumnIdentifiers(columns);
        leaveTable=new JTable(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        leaveTable.getTableHeader().setReorderingAllowed(false);
        leaveTable.setModel(tableModel);
        leaveTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                Delete.setEnabled(true);
                Add.setEnabled(true);
            }
        });
        table_pane=new JScrollPane(leaveTable);
        table_panel=new JPanel(new BorderLayout());
        table_panel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        table_panel.add(table_pane);
        table_panel.setLocation(0,100);
        table_panel.setSize(800,600);
        add(table_panel);
        
        //Button Panel
        button_panel=new JPanel(new GridLayout(4,1,3,100));
        JButton View=new JButton("Show Requests");
        View.setBackground(Color.cyan);
        View.addActionListener(new ActionListener(){
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
                try {
                    acccept();
                } catch (IOException ex) {
                    Logger.getLogger(showLeaveRequests.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        button_panel.add(Add);
        
        //title
        JButton Back=new JButton("Back");
        Back.setBackground(Color.red);
        Back.setForeground(Color.white);
        Back.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                admin_obj.setVisible(true);
                setVisible(false);
            }
        });
        button_panel.add(Back);
        button_panel.setLocation(width-200,100);
        button_panel.setSize(200,600);
        add(button_panel);
    }
}

