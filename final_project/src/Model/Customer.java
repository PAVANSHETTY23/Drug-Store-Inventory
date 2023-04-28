package Model;

public class Customer extends User{
private String Address;

public Customer(){}

public Customer(String userId, String name,String contact,String Address){
super(userId,name,contact);
this.Address = Address;

}

public void setAddress(String Address){
this.Address=Address;
}

public String getAddress() {
return this.Address;
}
}