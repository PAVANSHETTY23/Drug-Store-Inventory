package Model;

public class Bill {
private int BillId;
private double UnitPrice;
private String medId;
private String medName;
private double amount;
private int quantity;

public Bill(){}

public Bill(int BillId, String medId, String medName, double UnitPrice, double amount,int quantity){

this.BillId=BillId;
this.UnitPrice=UnitPrice;
this.medName=medName;
this.medId=medId;
this.amount=amount;
this.quantity=quantity;

}

public void setBillId(int BillId){
this.BillId = BillId;
}

public int getBillId(){
return this.BillId;
}

public void setUnitPrice(double UnitPrice){
this.UnitPrice = UnitPrice;
}

public double getUnitPrice(){
return this.UnitPrice;
}


public void setamount(double amount){
this.amount = amount;
}

public double getamount(){
return this.amount;
}

public void setMedId(String medId){
this.medId = medId;
}

public String getMedId(){
return this.medId;
}

public void setMedName(String medName){
this.medName = medName;
}

public String getMedName(){
return this.medName;
}

public void setquantity(int quantity){
this.quantity = quantity;
}

public int getquantity(){
return this.quantity;
}

}