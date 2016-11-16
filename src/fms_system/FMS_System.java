package fms_system;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    Register register_obj=new Register();
    Login login_obj=new Login();
    public FMS_System(){
        super("FMS_System");
        setSize(width,height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        
        JLabel title=new JLabel("Faculty Management Services System",SwingConstants.CENTER);
        title.setLocation(200, 100);
        title.setSize(400, 40);
        title.setForeground(Color.red);
        title.setOpaque(true);
        
        JButton login=new JButton("Login");
        login.setLocation(300, 200);
        login.setSize(200,40);
        login.setBackground(Color.orange);
        
        
        
        login.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                login_obj.setVisible(true);
            }
            
        });
        JButton register=new JButton("Register");
        register.setLocation(300, 300);
        register.setSize(200,40);
        register.setBackground(Color.red);
        register.setForeground(Color.white);
        
        
        register.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                register_obj.setVisible(true);
            }
            
        });
        add(title);
        add(login);
        add(register);
        
        setVisible(true);
    }
    public static void main(String[] args) {
        new FMS_System();
    }
    
}
