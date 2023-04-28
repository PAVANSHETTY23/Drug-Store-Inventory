package Model;

public class Employee extends User{
private double salary;
private String password;
private String designation;

public Employee(){}

public Employee(String userId, String name,String contact,double salary,String password,String designation){
super(userId,name,contact);
this.password = password;
this.salary = salary;
this.designation = designation;
}

public Employee(String name,String password){
super(name);
this.password = password;
}

public Employee(String userId, String name,String contact,double salary,String designation){
super(userId,name,contact);
this.salary = salary;
this.designation = designation;

}

public void password(String password){
this.password=password;
}

public String getPassword(){
return this.password;
}

public void setSalary(double salary){
this.salary=salary;
}

public double getSalary(){
return this.salary;
}
public void setDesignation(String designation){
this.designation=designation;
}
public String getDesignation(){
return this.designation;
}

}