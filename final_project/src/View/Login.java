package View;

import Model.*;
import Controller.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Font;
import java.awt.Color;

public class Login extends JFrame implements ActionListener,MouseListener{

    JTextField userNameTf = new JTextField();
    JPasswordField passwordTf = new JPasswordField();
    JLabel userNameLb = new JLabel("Username:");
    JLabel passwordLb = new JLabel("Password:");
   
   
    JButton showBt=new JButton("Show");
    JButton loginBt=new JButton("Login");
   
EmployeeController pr;
Employee user;

    public Login(){
        super("Login");
        setSize(1200,700);
        getContentPane().setLayout(null);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        loginBt.setFont(new Font("Tahoma", Font.PLAIN, 14));

       

        loginBt.setBounds(526,413,100,30); //Login button size
        loginBt.addActionListener(this);
        getContentPane().add(loginBt);
        showBt.setFont(new Font("Tahoma", Font.PLAIN, 14));

        showBt.setBounds(751,300,80,30); //Show button size
        showBt.addMouseListener(this);
        getContentPane().add(showBt);
       
        userNameTf.setBounds(480,200,200,40); //Username Textfield size
        getContentPane().add(userNameTf);
        userNameLb.setFont(new Font("Tahoma", Font.PLAIN, 14));
        userNameLb.setBounds(370,202,100,30); //Username Label size
        getContentPane().add(userNameLb);

        passwordTf.setBounds(480,296,200,40); //Password Textfield size
        getContentPane().add(passwordTf);
        passwordLb.setFont(new Font("Tahoma", Font.PLAIN, 14));

        passwordLb.setBounds(370,298,100,30); //Password label size
        getContentPane().add(passwordLb);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 128, 128));
        panel.setBounds(0, 0, 1186, 93);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Login Page");
        lblNewLabel.setForeground(new Color(0, 255, 255));
        lblNewLabel.setBackground(new Color(255, 255, 255));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
        lblNewLabel.setBounds(339, 10, 460, 73);
        panel.add(lblNewLabel);
     }
   
public void mousePressed(MouseEvent me){
        passwordTf.setEchoChar((char)0);
    }
   
public void mouseReleased(MouseEvent me){
         passwordTf.setEchoChar('*');
    }

    public void mouseClicked(MouseEvent me){}
    public void mouseEntered(MouseEvent me){}
    public void mouseExited(MouseEvent me){}

    public void actionPerformed(ActionEvent a){
if(userNameTf.getText().equals("")){
JOptionPane.showMessageDialog(this,"Username required","ERROR",JOptionPane.WARNING_MESSAGE);
}
else if(passwordTf.getText().equals("")){
JOptionPane.showMessageDialog(this,"Password required","ERROR",JOptionPane.WARNING_MESSAGE);
        }
else
{
	
	pr = new EmployeeController();
	user = new Employee(userNameTf.getText(),passwordTf.getText());
	if(pr.loginEmployee(user))
	{
		Employee user1 = pr.getEmployee();
		String des = user1.getDesignation();
		System.out.print(des);
		if(des.equalsIgnoreCase("admin"))
		{
			new Home(user1);
			this.setVisible(false);
		}
		else if(des.equalsIgnoreCase("manager"))
		{
			new UserHome(user1);
			this.setVisible(false);
		}
		else
		{
			new EmpHome(user1);
			this.setVisible(false);
		}
			//JOptionPane.showMessageDialog(null,"User found.\nTry Again","ERROR",JOptionPane.INFORMATION_MESSAGE);
			
}

else{
JOptionPane.showMessageDialog(null,"User not found.\nTry Again","ERROR",JOptionPane.INFORMATION_MESSAGE);
}
}
}
}