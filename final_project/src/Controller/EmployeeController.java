package Controller;

import Model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class EmployeeController implements IEmployee{
	private Employee user;
	DatabaseConnection datacon = new DatabaseConnection();
	public void deleteEmployee(String name){
		try{
		datacon.openConnection();
		datacon.st.executeUpdate("delete from employee where Name ='"+name+"';");
		datacon.closeConnection();
		}
		catch(Exception e){
		System.out.println(e.getMessage());
		}
}
public void addUser(User user) {}

public void addUser(Employee user){
try{
	datacon.openConnection();
	datacon.st.executeUpdate("insert into employee VALUES('"+user.getUserId()+"','"+user.getName()+"','"+user.getContact()+"','"+user.getSalary()+"','"+user.getPassword()+"','"+user.getDesignation()+"')");
	datacon.closeConnection();
	}
	catch(Exception e){
	System.out.println(e.getMessage());
	}
}


public User searchUser(String name){
	Employee user = null;
	try{
	datacon.openConnection();
	datacon.result = datacon.st.executeQuery("select UserId, Name, Contact, Designation,salary from employee where Name='"+name+"';");
	
	while(datacon.result.next()){
	String id = datacon.result.getString("UserId");
	String username = datacon.result.getString("Name");
	String contact = datacon.result.getString("Contact");
	String status = datacon.result.getString("Designation");
	double salary = datacon.result.getDouble("salary");
	
	user = new Employee();
	user.setUserId(id);
	user.setName(username);
	user.setContact(contact);
	user.setDesignation(status);
	user.setSalary(salary);
	}
	
	}
	catch(Exception e){
	System.out.println(e.getMessage());
	}
	datacon.closeConnection();
	return user;
}


 public String[][] searchEmployee(String keyword){
	 ArrayList<Employee> ar = new ArrayList<Employee>();
	
	 try
	 {
	 datacon.openConnection();
	 datacon.result = datacon.st.executeQuery("select UserId, Name, Contact, Designation, salary, password from employee where Name like '%"+keyword+"%';");
	
	 while(datacon.result.next())
	{
	 String empId = datacon.result.getString("UserId");
	 String name = datacon.result.getString("Name");
	 String contact = datacon.result.getString("Contact");
	 String designation = datacon.result.getString("Designation");
	 String password = datacon.result.getString("password");
	 double salary = datacon.result.getDouble("salary");
	
	 Employee emp = new Employee(empId,name,contact,salary,password, designation);
	 ar.add(emp);
	 }
	 }
	 catch(Exception e){
	System.out.println(e.getMessage());
	 }
	 datacon.closeConnection();
	
	 Object obj[] = ar.toArray();
	 String data[][] = new String [ar.size()][5];
	
	 for(int i=0; i<obj.length; i++)
	 {
	 data[i][0] = ((Employee)obj[i]).getUserId()+"";
	 data[i][1] = ((Employee)obj[i]).getName();
	 data[i][2] = ((Employee)obj[i]).getContact()+"";
	 data[i][3] = ((Employee)obj[i]).getDesignation();
	 data[i][4] = (((Employee)obj[i]).getSalary())+"";
	 }
	 return data;
 }

 public String[][] getEmployees(){

	 ArrayList<Employee> ar = new ArrayList<Employee>();
	
	 try
	 {
	 datacon.openConnection();
	datacon.result = datacon.st.executeQuery("SELECT * FROM employee");
	
	 while(datacon.result.next())
	 {
		 String empId = datacon.result.getString("UserID");
		 String name = datacon.result.getString("Name");
		 String contact = datacon.result.getString("Contact");
		 String designation = datacon.result.getString("Designation");
		 String password = datacon.result.getString("password");
		 double salary = datacon.result.getDouble("salary");
	
	 Employee emp = new Employee(name,contact,empId,salary,password, designation);
	 ar.add(emp);
	 }
	 }
	 catch(Exception e){
	 System.out.println(e.getMessage());
	 }
	 datacon.closeConnection();
	
	 Object obj[] = ar.toArray();
	 String data[][] = new String [ar.size()][5];
	
	 for(int i=0; i<obj.length; i++)
	 {
	 data[i][0] = ((Employee)obj[i]).getUserId()+"";
	 data[i][1] = ((Employee)obj[i]).getName();
	 data[i][2] = ((Employee)obj[i]).getContact()+"";
	 data[i][3] = (((Employee)obj[i]).getSalary())+"";
	 data[i][4] = ((Employee)obj[i]).getDesignation();
	 }
	 return data;
 }
public void updateUser(User user) {}
public void updateUser(Employee user){
try{
datacon.openConnection();
datacon.st.executeUpdate("update employee set Contact='"+user.getContact()+"', salary = "+user.getSalary()+", Designation = '"+user.getDesignation()+"'  where Name='"+user.getName()+"';");
datacon.closeConnection();
}
catch(Exception e){
System.out.println(e.getMessage());
}
}

public void updatePassword(Employee user){
	try{
	datacon.openConnection();
	datacon.st.executeUpdate("update employee set password = '"+user.getPassword()+"'  where Name='"+user.getName()+"';");
	datacon.closeConnection();
	}
	catch(Exception e){
	System.out.println(e.getMessage());
	}
}

public boolean loginEmployee(Employee emp){
try{
Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection ("jdbc:mysql://localhost:3306/drug_store_inventory","root","pavan");
        Statement Stmt = con.createStatement();
        
String query1 = "Select * FROM employee WHERE Name='"+emp.getName()+"' and password='"+emp.getPassword()+"'" ;
ResultSet rs = Stmt.executeQuery(query1);

if (rs.next()) {
	user = new Employee(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6));
             return true;
           }
           con.close();
}
catch(Exception e){
System.out.println(e.getMessage());
}
return false;
}

public Employee getUser(String Username,String password){
Employee user1 = null;
try
{
datacon.openConnection();

datacon.result = datacon.st.executeQuery("select Name, password, Designation from employee where Name='"+Username+"' and password='"+password+"'");

user1 = new Employee();
user1.setName(datacon.result.getString("Name"));
user1.password(datacon.result.getString("password"));

System.out.print(user.getDesignation());
}
        catch(Exception e)
{
System.out.println(e.getMessage());
        }
datacon.closeConnection();
return user1;
}
public Employee getEmployee()
{
	return user;
}
}