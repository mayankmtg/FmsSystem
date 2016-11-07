/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fms_system;

import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author mayank
 * @author amit
 */
public class Leave extends JFrame{
    int width=800;
    int height=500;
    public Leave(){
        
        super("Leave Application");
        setSize(width,height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        
    }
}

