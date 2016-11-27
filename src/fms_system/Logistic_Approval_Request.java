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
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Logistic_Approval_Request extends JFrame {
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
            reader = new BufferedReader(new FileReader("database/persons/"+Login.getCurrentUser()+"/Logistics.csv"));
            while((line = reader.readLine()) != null){
                tableModel.addRow(line.split(",")); 
            }
            reader.close();
        }
        catch(IOException e){}
    }
    public void deleteRequest(){
        int i=leaveTable.getSelectedRow();
        int x=leaveTable.getRowCount();
        int y=leaveTable.getColumnCount();
        String line;
        BufferedReader reader;
        PrintWriter writer;
        String write="";
        try{
            reader = new BufferedReader(new FileReader("database/persons/"+Login.getCurrentUser()+"/Logistics.csv"));
            while((line = reader.readLine()) != null){
                if(!line.split(",")[0].equals(tableModel.getValueAt(i, 0))){
                    write+=line;
                }
                else{
                    String[] var=line.split(",");
                    var[4]="Rejected";
                    write+=var[0]+","+var[1]+","+var[2]+","+var[3]+","+var[4];
                }
                write+="\r\n";
            }
            reader.close();
            writer = new PrintWriter(new FileWriter("database/persons/"+Login.getCurrentUser()+"/Logistics.csv"));
            writer.print(write);
            writer.close();
        }   
        catch(IOException e){
        System.out.println("Error");
        }
        tableModel.setValueAt("Rejected", i, 4);
    }
    public void acccept() throws IOException{
        int i=leaveTable.getSelectedRow();
        int x=leaveTable.getRowCount();
        int y=leaveTable.getColumnCount();
        String line;
        BufferedReader reader;
        PrintWriter writer;
        String write="";
        try{
            reader = new BufferedReader(new FileReader("database/persons/"+Login.getCurrentUser()+"/Logistics.csv"));
            while((line = reader.readLine()) != null){
                if(!line.split(",")[0].equals(tableModel.getValueAt(i, 0))){
                    write+=line;
                }
                else{
                    String[] var=line.split(",");
                    var[4]="Accepted";
                    write+=var[0]+","+var[1]+","+var[2]+","+var[3]+","+var[4];
                }
                write+="\r\n";
            }
            reader.close();
            writer = new PrintWriter(new FileWriter("database/persons/"+Login.getCurrentUser()+"/Logistics.csv"));
            writer.print(write);
            writer.close();
        }   
        catch(IOException e){
        System.out.println("Error");
        }
        tableModel.setValueAt("Approved", i, 4);
     }
    
    public void replyLogistic(){
        int i=leaveTable.getSelectedRow();
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("database/persons/"+tableModel.getValueAt(i, 3).toString()+"/Logistics.csv",true));
            String temp="";
            for(int j=0;j<tableModel.getColumnCount()-1;j++){
                temp+=tableModel.getValueAt(i, j) + ",";
            }
            temp+=tableModel.getValueAt(i, tableModel.getColumnCount()-1);
            writer.println(temp);
            writer.close();
        } catch (IOException ex) {}
    
    }
    
    public Logistic_Approval_Request(){
        //frame
        super("Logistic Approval Requests");
        setSize(width,height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        
        //title
        JLabel title =new JLabel("Logistic Approval Request");
        title.setLocation(400,50);
        title.setSize(300,20);
        title.setFont(new Font("Serif", Font.PLAIN, 24));
        add(title);
        
        JButton Delete=new JButton("Decline Logistic");
        JButton Add =new JButton("Accept Logistic");
        String columns[] ={"Logistic ID","Requirements","Task Reference","From","Status"};
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
        JButton View=new JButton("Show Approval Requests");
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
                replyLogistic();
                JOptionPane.showMessageDialog (null, "Logistic Declined!!", "Done", JOptionPane.INFORMATION_MESSAGE);
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
                    replyLogistic();
                    JOptionPane.showMessageDialog (null, "Request Accepted!!", "Done", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {}
            }
        });
        button_panel.add(Add);
        
        
        JButton Back=new JButton("Back");
        Back.setBackground(Color.red);
        Back.setForeground(Color.white);
        Back.addActionListener(new ActionListener(){
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
        button_panel.add(Back);
        button_panel.setLocation(width-200,100);
        button_panel.setSize(200,600);
        add(button_panel);
        
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

