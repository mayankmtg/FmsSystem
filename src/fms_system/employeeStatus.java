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
import java.io.IOException;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mayank
 * @author amit
 */
public class employeeStatus extends JFrame{
    int width=500;
    int height=700;
    JTable statusTable;
    DefaultTableModel tableModel;
    JScrollPane table_pane;
    JPanel table_panel;
    public void loadTable(){
        
        String line;
        BufferedReader reader;
            try{       
                reader = new BufferedReader(new FileReader("database/employeeStatus.csv"));
                while((line = reader.readLine()) != null){
                   tableModel.addRow(line.split(",")); 
                }
                reader.close();
             }
            catch(IOException e){
                JOptionPane.showMessageDialog(null, "Error");
                e.printStackTrace();
            }
    }
    public employeeStatus(){
        
        super("Leave Application");
        setSize(width,height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        
        JLabel title=new JLabel("View Employee Status",SwingConstants.CENTER);
        title.setLocation(100, 40);
        title.setSize(200, 40);
        add(title);
        
        //table for viewing status
        String columns[] ={"User Name","Type","Department","Status"};
        tableModel = new DefaultTableModel(0,4); 
        tableModel.setColumnIdentifiers(columns);
        statusTable=new JTable(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        statusTable.getTableHeader().setReorderingAllowed(false);
        statusTable.setModel(tableModel);
        
        table_pane=new JScrollPane(statusTable);
        table_panel=new JPanel(new BorderLayout());
        table_panel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        table_panel.add(table_pane);
        table_panel.setLocation(50,100);
        table_panel.setSize(400,400);
        add(table_panel);
        
        ///buttons
        JButton view=new JButton("View Status");
        view.setLocation(50,550);
        view.setSize(400,50);
        view.setBackground(Color.cyan);
        view.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                loadTable();
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

