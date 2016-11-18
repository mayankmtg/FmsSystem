/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fms_system;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mayank
 */
public class UpdateEmployee extends JFrame{
    int width=800,height=500;
    JTable contactTable = new JTable();
    DefaultTableModel tableModel;
    public void loadTable(){
        
        String line;
        BufferedReader reader;
            try{       
                reader = new BufferedReader(new FileReader("database/userinfo.csv"));
                while((line = reader.readLine()) != null) 
                {
                   tableModel.addRow(line.split(",")); 
                }
                reader.close();
             }
            catch(IOException e){
                JOptionPane.showMessageDialog(null, "Error");
                e.printStackTrace();
            }
    }
    
    public UpdateEmployee(){
        super("Update Employee Database");
        String columns[] ={"ID", "Type","Name", "User Name", "Password", "DOB", "Address", "Departments" };
        tableModel = new DefaultTableModel(0,8); 
        tableModel.setColumnIdentifiers(columns);
        
        setSize(width,height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        
        contactTable.setModel(tableModel);
        contactTable.setVisible(true);
        contactTable.setLocation(50,100);
        contactTable.setSize(700,100);
        JButton Add =new JButton("Show Approval Request");
        Add.setLocation(50,50);
        Add.setSize(250,50);
        Add.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                loadTable();
            }
        });
        add(Add);
        add(contactTable);
        JButton View=new JButton("Show Employees");
        JButton Delete=new JButton("Fire Employee");
    }
    
}
