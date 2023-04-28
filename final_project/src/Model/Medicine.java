package Model;

public class Medicine{
private String MedId;
private String MedName;
private double unitPrice;
private MyDate ExpiryDate;
private String ExpiryStatus;
private int quantity;

public Medicine(){}
public Medicine(String MedId, String MedName, MyDate ExpiryDate, double unitPrice, int quantity){
	this.MedId = MedId;
	this.MedName = MedName;
	this.unitPrice = unitPrice;
	this.ExpiryDate = ExpiryDate;
	this.quantity = quantity;
}

public Medicine(String MedId, String MedName, MyDate ExpiryDate, double unitPrice, String ExpiryStatus){
this.MedId = MedId;
this.MedName = MedName;
this.unitPrice = unitPrice;
this.ExpiryDate = ExpiryDate;
this.ExpiryStatus = ExpiryStatus;
}

public void setMedId(String MedId){
this.MedId = MedId;
}

public String getMedId(){
return this.MedId;
}

public void setMedName(String MedName){
this.MedName = MedName;
}

public String getMedName(){
return this.MedName;
}

public void setExpiryStatus(String ExpiryStatus){
this.ExpiryStatus = ExpiryStatus;
}

public String getExpiryStatus(){
return this.ExpiryStatus;
}

public void setExpiryDate(MyDate ExpiryDate){
this.ExpiryDate = ExpiryDate;
}

public MyDate getExpiryDate(){
return this.ExpiryDate;
}


public void setUnitPrice(double unitPrice){
this.unitPrice = unitPrice;
}

public double getUnitPrice(){
return this.unitPrice;
}

public void setQuantity(int quantity){
this.quantity = quantity;
}

public int getQuantity(){
return this.quantity;
}
}