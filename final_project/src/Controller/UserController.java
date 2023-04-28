package Controller;

import Model.*;

public class UserController implements IUser{
DatabaseConnection datacon = new DatabaseConnection();

public void addUser(User user){
try{
datacon.openConnection();
datacon.st.executeUpdate("insert into user VALUES('"+user.getUserId()+"','"+user.getName()+"','"+user.getContact()+"')");


datacon.closeConnection();
}
catch(Exception e){
System.out.println(e.getMessage());
}
}


public User searchUser(String name){
User user = null;
try{
datacon.openConnection();
datacon.result = datacon.st.executeQuery("select empId, empName, contact from user where userName='"+name+"';");

while(datacon.result.next()){
String id = datacon.result.getString("empId");
String username = datacon.result.getString("empName");
String contact = datacon.result.getString("contact");
//String status = datacon.result.getString("status");
//double salary = datacon.result.getDouble("salary");

user = new User();
user.setUserId(id);
user.setName(username);
user.setContact(contact);
//emp.setStatus(status);
//emp.setSalary(salary);
}

}
catch(Exception e){
System.out.println(e.getMessage());
}
datacon.closeConnection();
return user;
}

public void updateUser(User user){
try{
datacon.openConnection();
datacon.st.executeUpdate("update user set userName= '"+user.getName()+"', contact='"+user.getContact()+"'  where empId='"+user.getUserId()+"';");
datacon.closeConnection();
}
catch(Exception e){
System.out.println(e.getMessage());
}
}
}
