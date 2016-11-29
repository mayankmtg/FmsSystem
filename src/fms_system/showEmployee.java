package fms_system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
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
                if(Login.getCurrentType().equals("Admin")){
                    reader = new BufferedReader(new FileReader("database/Supervisors.csv"));
                    while((line = reader.readLine()) != null){
                       tableModel.addRow(line.split(",")); 
                    }
                    reader.close();
                }
                reader2= new BufferedReader(new FileReader("database/Staffer.csv"));
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
    
    public void deleteEmployee(String employeeName,String fileName){
         
        String line;
        BufferedReader reader,reader1,reader2;
        PrintWriter writer;
        String write="";
        try{
            reader = new BufferedReader(new FileReader("database/"+fileName));
            while((line = reader.readLine()) != null){
               if(!line.split(",")[3].equals(employeeName)){
                   write+=line + "\r\n";
               }
                    
            }
            reader.close();
            writer = new PrintWriter(new FileWriter("database/"+fileName));
            writer.print(write);
            writer.close();
        }
        catch(IOException e){}
    }
    
    public void deleteEmployeeDirec(String employeeName){
        File index = new File("database/persons/"+employeeName);
        String[]entries = index.list();
        for(String s: entries){
            File currentFile = new File(index.getPath(),s);
            currentFile.delete();
        }
        index.delete();
    }
    
    public showEmployee(){
        super("View Employee Database");
        setSize(width,height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        JButton Delete=new JButton("Fire");
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
                int i=contactTable.getSelectedRow();
                deleteEmployee(tableModel.getValueAt(i, 3).toString(), "Supervisor.csv");
                deleteEmployee(tableModel.getValueAt(i, 3).toString(), "Staffer.csv");
                deleteEmployee(tableModel.getValueAt(i, 3).toString(), "userinfo.csv");
                deleteEmployeeDirec(tableModel.getValueAt(i, 3).toString());
                if(i>=0){
                    tableModel.removeRow(i);
                    JOptionPane.showMessageDialog (null, "Employee Fired!!", "Done", JOptionPane.INFORMATION_MESSAGE);
                }
                
                Delete.setEnabled(false);
            }
        });
        button_panel.add(Delete);
        
        //title
        JLabel title =new JLabel("Show Employee Database");
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