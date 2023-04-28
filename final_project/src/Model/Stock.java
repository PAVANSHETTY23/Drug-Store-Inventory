package Model;

public class Stock{
private String MedId;
private String MedName;
private int StockQuantity;

public Stock(){}
public Stock(int StockQuantity){
	this.StockQuantity = StockQuantity;
}
public Stock(String MedId, String MedName, int StockQuantity){

this.MedId=MedId;
this.MedName=MedName;
this.StockQuantity=StockQuantity;

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

public void setStockQuantity(int StockQuantity){
this.StockQuantity = StockQuantity;
}

public int getStockQuantity(){
return this.StockQuantity;
}


}