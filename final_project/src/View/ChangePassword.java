package View;

import javax.swing.*;
import java.awt.event.*;
import Model.*;
import Controller.*;
import java.awt.Color;
import java.awt.Font;

public class ChangePassword extends JFrame implements ActionListener,MouseListener{
	
	JButton logout=new JButton("Logout");
    JButton back=new JButton("Back");
	
	JLabel currentPasswordLb = new JLabel("Current Password");
	JPasswordField currentPasswordTf = new JPasswordField();
	JLabel newPasswordLb = new JLabel("New Password");
	JPasswordField newPasswordTf = new JPasswordField();
	
	JButton showCurrentPasswordBt = new JButton("Show");
	JButton showNewPasswordBt = new JButton("Show");
    JButton doneBt=new JButton("Done");
	
	Employee user;
	EmployeeController pr = new EmployeeController();
	private final JLabel lblNewLabel_1 = new JLabel("Change Password");
	public ChangePassword(Employee user){
		getContentPane().setBackground(new Color(0, 64, 64));
		setTitle("Change Password");
		setSize(1200,700);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.user = user;
		
		
		logout.setBounds(1070,20,80,20); 
        logout.addActionListener(this);
        getContentPane().add(logout); 

        back.setBounds(1070,50,75,20); 
        back.addActionListener(this);
        getContentPane().add(back);
		currentPasswordLb.setForeground(new Color(255, 255, 255));
		currentPasswordLb.setFont(new Font("Tahoma", Font.BOLD, 14));
		currentPasswordLb.setBackground(new Color(0, 128, 0));
		
		currentPasswordLb.setBounds(510,250,150,30);
		getContentPane().add(currentPasswordLb);
		currentPasswordTf.setBounds(510,290,150,30);		//current password
		getContentPane().add(currentPasswordTf);
		
		
		showCurrentPasswordBt.setBounds(680,290,80,20);
		getContentPane().add(showCurrentPasswordBt);
		showCurrentPasswordBt.addMouseListener(this);
		newPasswordLb.setForeground(new Color(255, 255, 255));
		newPasswordLb.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		newPasswordLb.setBounds(510,330,150,30);
		getContentPane().add(newPasswordLb);
		newPasswordTf.setBounds(510,370,150,30);
		getContentPane().add(newPasswordTf);
		
		showNewPasswordBt.setBounds(680,370,80,20);
		getContentPane().add(showNewPasswordBt);
		showNewPasswordBt.addMouseListener(this);
		
		doneBt.setBounds(545,420,75,30);
		getContentPane().add(doneBt);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(204, 153, 255));
		lblNewLabel_1.setFont(new Font("Tw Cen MT", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_1.setBounds(160, 52, 888, 79);
		
		getContentPane().add(lblNewLabel_1);
		doneBt.addActionListener(this);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent a){
		if(a.getSource()==logout){
			new Login();
			this.setVisible(false);
		}
		else if(a.getSource()==back){
			if (user.getDesignation().equalsIgnoreCase("admin"))
			{
				new Home(user);
				this.setVisible(false);
			}
		else if(user.getDesignation().equalsIgnoreCase("manager"))
			{
				new UserHome(user);
				this.setVisible(false);
			}
			else
			{
				new EmpHome(user);
				this.setVisible(false);
			}
		}
		else if(a.getSource()==doneBt){
			user.password(newPasswordTf.getText());
			pr.updatePassword(user);
			JOptionPane.showMessageDialog(this,"Password changed successfully","Succesful",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void mousePressed(MouseEvent me){
		if(me.getSource()==showCurrentPasswordBt){
			currentPasswordTf.setEchoChar((char)0);
		}
		else if(me.getSource()==showNewPasswordBt){
			newPasswordTf.setEchoChar((char)0);
		}
	}
    
	public void mouseReleased(MouseEvent me){
		if(me.getSource()==showCurrentPasswordBt){
			currentPasswordTf.setEchoChar('*');
		}
		else if(me.getSource()==showNewPasswordBt){
			newPasswordTf.setEchoChar('*');
		}
	}
	
    public void mouseClicked(MouseEvent me){}
    public void mouseEntered(MouseEvent me){}
    public void mouseExited(MouseEvent me){}
}