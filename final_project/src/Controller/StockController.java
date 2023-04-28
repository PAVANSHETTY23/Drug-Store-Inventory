package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.*;

public class StockController implements IStock{
	DatabaseConnection datacon = new DatabaseConnection();
	
public void addStock(Medicine med){
int quantity=0;
try{
	datacon.openConnection();
	datacon.result=datacon.st.executeQuery("select StockQuantity from stock where MedName = '" +med.getMedName()+"';");
	
	while(datacon.result.next()){
	quantity = datacon.result.getInt("StockQuantity");
	//System.out.println(quantity);
	
	//System.out.println(quantity1);
	
}}
catch(Exception e){
	System.out.println(e.getMessage());
}
datacon.closeConnection();

datacon.openConnection();
try {
	int quantity1 = quantity + med.getQuantity(); 
	datacon.st.executeUpdate("update Stock set StockQuantity = '"+quantity1+"' where MedName = '" +med.getMedName()+"';");
	datacon.st.executeUpdate("update medicine set quantity = '"+quantity1 +"' where Name = '" +med.getMedName()+"';");
}
 catch (SQLException e) {
// TODO Auto-generated catch block
	e.printStackTrace();
	}
datacon.closeConnection();
}

public void removeStock(Bill item){
int quantity=0;

try{
	datacon.openConnection();
	datacon.result=datacon.st.executeQuery("select quantity from medicine where MedID = '" +item.getMedId()+"';");
	
	while(datacon.result.next()){
	quantity = datacon.result.getInt("quantity");
	}
}

catch(Exception e){
	System.out.println(e.getMessage());
}
datacon.closeConnection();
int finalquantity= quantity-item.getquantity();
datacon.openConnection();

try {
	datacon.st.executeUpdate("update medicine set quantity = "+finalquantity+" where Name = '" +item.getMedName()+"';");
	datacon.st.executeUpdate("update stock set StockQuantity = "+finalquantity+" where MedName = '" +item.getMedName()+"';");
} catch (SQLException e) {
	e.printStackTrace();
	}
datacon.closeConnection();
}}
