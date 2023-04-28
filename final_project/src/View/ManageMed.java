package View;

import javax.swing.*;
import java.awt.event.*;
import Model.*;
import Controller.*;
import java.awt.Color;

public class ManageMed extends JFrame implements ActionListener {

JTextField searchTf = new JTextField();
JButton searchBt = new JButton("Search");
JButton showAllBt = new JButton("Show All");

JTable medTable;
JScrollPane medTableSP;

JLabel  medIdLb =new JLabel("Med Id");
JTextField medIdTf= new JTextField();

JCheckBox load = new JCheckBox("Load");

JLabel  medNameLb =new JLabel("Med Name");
JTextField medNameTf= new JTextField();

JLabel unitPriceLb = new JLabel("Unit price");
JTextField unitPriceTf = new JTextField();

JLabel ExpiryLb = new JLabel("Expiry Date");
JTextField ExpiryTf = new JTextField();

JLabel quantityLb = new JLabel("Quantity");
JTextField quantityTf = new JTextField();

JButton addBt = new JButton("Add");

JButton refreshBt = new JButton("Refresh");
JButton updateBt = new JButton("Update");

JButton logout=new JButton("Logout");
JButton back=new JButton("Back");

Employee user;

MedicineController mr = new MedicineController();

    public ManageMed(Employee user){
    	getContentPane().setBackground(new Color(128, 255, 255));
		setSize(1200,700);
		setTitle("Medicine Management");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		this.user=user;
        logout.setBackground(new Color(255, 0, 0));
       
        logout.setBounds(1070,20,80,20); //Logout button size
        logout.addActionListener(this);
        getContentPane().add(logout);
        back.setBackground(new Color(255, 255, 0));

        back.setBounds(1075,50,70,20); //Back button size
        back.addActionListener(this);
        getContentPane().add(back);

        searchTf.setBounds(550,120,200,30);
getContentPane().add(searchTf);
searchBt.setBackground(new Color(192, 192, 192));
searchBt.setBounds(760,120,80,30);
getContentPane().add(searchBt);
searchBt.addActionListener(this);
showAllBt.setBackground(new Color(192, 192, 192));

showAllBt.setBounds(850,120,100,30);
getContentPane().add(showAllBt);
showAllBt.addActionListener(this);

String dataM[][] = {{"", "", "", "",""}};
String headM[] = {"medId", "medName", "medType", "unitPrice", "quantity"};

medTable = new JTable(dataM,headM);
medTable.setEnabled(false);
medTableSP = new JScrollPane(medTable);
medTableSP.setBounds(500,170,600,400);
getContentPane().add(medTableSP);

medIdLb.setBounds(100,200,60,30);
getContentPane().add(medIdLb);
medIdTf.setBounds(170,200,120,30);
getContentPane().add(medIdTf);

load.setBounds(310,204,60,20);
getContentPane().add(load);
load.addActionListener(this);

medNameLb.setBounds(100,250,60,30);
getContentPane().add(medNameLb);
medNameTf.setBounds(170,250,120,30);
getContentPane().add(medNameTf);

unitPriceLb.setBounds(100,300,60,30);
getContentPane().add(unitPriceLb);
unitPriceTf.setBounds(170,300,120,30);
getContentPane().add(unitPriceTf);

ExpiryLb.setBounds(100,350,60,30);
getContentPane().add(ExpiryLb);
ExpiryTf.setBounds(170,350,120,30);
getContentPane().add(ExpiryTf);

quantityLb.setBounds(100,400,60,30);
getContentPane().add(quantityLb);
quantityTf.setBounds(170,400,120,30);
getContentPane().add(quantityTf);
addBt.setBackground(new Color(192, 192, 192));

addBt.setBounds(100,450,100,30);
getContentPane().add(addBt);
addBt.addActionListener(this);
refreshBt.setBackground(new Color(192, 192, 192));



refreshBt.setBounds(100,500,100,30);
getContentPane().add(refreshBt);
refreshBt.addActionListener(this);
updateBt.setBackground(new Color(192, 192, 192));

updateBt.setBounds(210,500,100,30);
getContentPane().add(updateBt);
updateBt.addActionListener(this);
updateBt.setEnabled(false);

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
	else if(a.getSource()==searchBt){
	if(searchTf.getText().equals("")){
	JOptionPane.showMessageDialog(this,"Empty Text Field!!","warning",JOptionPane.WARNING_MESSAGE);
	 }
else{
	mr = new MedicineController();
	Medicine med = mr.getMedicine(searchTf.getText());
	MyDate expdate = med.getExpiryDate();
	String date_str = expdate.year + "-" + expdate.month + "-" + expdate.day;
	String dataM[][] = {{med.getMedId(),med.getMedName(),med.getUnitPrice()+"",date_str,med.getQuantity()+""}};
	String headM[] = {"medId", "medName","unitPrice", "ExpiryDate",  "Quantity"};
	remove(medTableSP);
	
	medTable = new JTable(dataM,headM);
	 medTable.setEnabled(false);
	medTableSP = new JScrollPane(medTable);
	 medTableSP.setBounds(500,170,600,400);
	 getContentPane().add(medTableSP);
	
	 this.revalidate();
	 //this.repaint();
	 }
}
else if(a.getSource()==showAllBt){
mr = new MedicineController();

String[][] dataM= mr.getMedicines();
String headM[] = {"medId", "medName",  "ExpiryDate","unitPrice", "Quantity"};
remove(medTableSP);

medTable = new JTable(dataM,headM);
medTable.setEnabled(false);
medTableSP = new JScrollPane(medTable);
medTableSP.setBounds(500,170,600,400);
getContentPane().add(medTableSP);

this.revalidate();
this.repaint();
}
else if(a.getSource()==load){
	if(medNameTf.getText().equals("")){
	JOptionPane.showMessageDialog(this,"Medicine Name required!!","WARNING",JOptionPane.WARNING_MESSAGE);
	load.setSelected(false);
}
else{
	MedicineController mr = new MedicineController();
	Medicine med = mr.getMedicine(medNameTf.getText());
	
	if(med==null){
	JOptionPane.showMessageDialog(this,"No medicine is assigned to this name","warning",JOptionPane.WARNING_MESSAGE);
	load.setSelected(false);
}
else{
	medNameTf.setText(med.getMedName());
	unitPriceTf.setText(med.getUnitPrice()+"");
	medIdTf.setText(med.getMedId());
	MyDate date = med.getExpiryDate();
	String date_str = date.year + "-" + date.month + "-" + date.day;
	ExpiryTf.setText(date_str);
	quantityTf.setText(med.getQuantity()+"");
	
	updateBt.setEnabled(true);
	addBt.setEnabled(false);
}
}
}

else if(a.getSource()==addBt){
	if(medIdTf.getText().equals("") || medNameTf.getText().equals("")  || unitPriceTf.getText().equals("") || quantityTf.getText().equals("") || ExpiryTf.getText().equals("")){
	JOptionPane.showMessageDialog(this,"Empty Text Field!!","warning",JOptionPane.WARNING_MESSAGE);
	}
else{
MedicineController mr = new MedicineController();
Medicine med = new Medicine();

med.setMedId(medIdTf.getText());
med.setMedName(medNameTf.getText());
String exp_date = ExpiryTf.getText();
//System.out.println(exp_date);
String[] arrOfStr = exp_date.split("-", 5);
int arr[] = new int[3];
for (int i=0; i<3; i++) {
	arr[i] = Integer.parseInt(arrOfStr[i]); 
	//System.out.println(arr[i]);
}
MyDate exp = new MyDate(arr[2],arr[1],arr[0]);
//System.out.println(exp);
med.setExpiryDate(exp);
med.setUnitPrice(Double.parseDouble(unitPriceTf.getText()));
med.setQuantity(Integer.parseInt(quantityTf.getText()));

if(mr.checkExpiry(med) == "NotExpired" || mr.checkExpiry(med) == "ExpiringSoon" )
	{mr.addMedicine(med);}
else
	JOptionPane.showMessageDialog(null,"Expiry date exceeded!","ERROR",JOptionPane.INFORMATION_MESSAGE);
	

String dataM[][] = mr.getMedicines();
String headM[] = {"medId", "medName", "ExpiryDate","unitPrice",  "Quantity"};
remove(medTableSP);

medTable = new JTable(dataM,headM);
medTable.setEnabled(false);
medTableSP = new JScrollPane(medTable);
medTableSP.setBounds(500,170,600,400);
getContentPane().add(medTableSP);

this.revalidate();
this.repaint();
}
}
else if(a.getSource()==refreshBt){
searchTf.setText("");
medIdTf.setText("");
medNameTf.setText("");
unitPriceTf.setText("");
quantityTf.setText("");
//qtyTf.setText("");
//qtyTf.setEnabled(false);

load.setSelected(false);

updateBt.setEnabled(false);
//insert.setSelected(false);
addBt.setEnabled(true);
}
else if(a.getSource()==updateBt){
	if(medIdTf.getText().equals("") || medNameTf.getText().equals("") || unitPriceTf.getText().equals("") || quantityTf.getText().equals("")){
	JOptionPane.showMessageDialog(this,"Empty Text Field!!","warning",JOptionPane.WARNING_MESSAGE);
	}
else{
	MedicineController mr = new MedicineController();
	Medicine med = new Medicine();
	StockController Sr = new StockController();
	med.setMedId(medIdTf.getText());
	med.setMedName(medNameTf.getText());
	med.setUnitPrice(Double.parseDouble(unitPriceTf.getText()));
	med.setQuantity(Integer.parseInt(quantityTf.getText()));
	mr.updatePrice(med);
	Sr.addStock(med);

JOptionPane.showMessageDialog(this,"Medicine Updated","Information",JOptionPane.INFORMATION_MESSAGE);
String dataM[][] = mr.getMedicines();
String headM[] = {"medId", "medName", "ExpiryDate","unitPrice",  "Quantity"};
remove(medTableSP);

medTable = new JTable(dataM,headM);
medTable.setEnabled(false);
medTableSP = new JScrollPane(medTable);
medTableSP.setBounds(500,170,600,400);
getContentPane().add(medTableSP);

this.revalidate();
this.repaint();
}
}
}
}