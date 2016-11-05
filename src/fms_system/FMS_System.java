package fms_system;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author mayank
 * @author amit
 */
public class FMS_System extends JFrame{

    /**
     * @param args the command line arguments
     */
    int width=800;
    int height=500;
    
    public FMS_System(){
        super("FMS_System");
        setSize(width,height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        
        JLabel title=new JLabel("Faculty Management Services System",SwingConstants.CENTER);
        title.setLocation(300, 100);
        title.setSize(200, 40);
        title.setBackground(Color.white);
        title.setForeground(Color.red);
        title.setOpaque(true);
        
        JButton login=new JButton("Login");
        login.setLocation(300, 200);
        login.setSize(200,40);
        login.setBackground(Color.orange);
        
        JButton register=new JButton("Register");
        register.setLocation(300, 300);
        register.setSize(200,40);
        register.setBackground(Color.red);
        register.setForeground(Color.white);
        
        add(title);
        add(login);
        add(register);
        
        setVisible(true);
    }
    public static void main(String[] args) {
        new FMS_System();
    }
    
}
