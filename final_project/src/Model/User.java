package Model;

public class User{
	
	private String userId;
	private String name;
	private String contact;
	
	public User(){}
	
	public User(String name){
		this.name = name;
	}
	
	public User(String userId, String name, String contact){
		this.name = name; 
		this.userId = userId;
		this.contact = contact;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return this.userId;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
		
	public void setContact(String contact){
		this.contact = contact;
	}
	
	public String getContact(){
		return this.contact;
	}
	
}