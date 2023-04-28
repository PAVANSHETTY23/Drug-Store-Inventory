package Controller;

import Model.*;
import Model.expirationStatus;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class MedicineController implements IMedicine{
DatabaseConnection datacon = new DatabaseConnection();

public void addMedicine(Medicine item){
	try{
	datacon.openConnection();
	String exp = checkExpiry(item);
	MyDate exp_date = item.getExpiryDate();
	String date_str = exp_date.year + "-" + exp_date.month + "-" + exp_date.day; 
	Date date = Date.valueOf(date_str);
	datacon.st.executeUpdate("insert into medicine values ('"+item.getMedId()+"','"+item.getMedName()+"',"+item.getUnitPrice()+",'"+date+"','"+exp+"',"+item.getQuantity()+");");
	datacon.st.executeUpdate("insert into stock values ('"+item.getMedId()+"','"+item.getMedName()+"',"+item.getQuantity()+");");
	datacon.closeConnection();
	}
	catch(Exception e){
	System.out.println("addmedicine" +e.getMessage());
	}
}

public void updatePrice(Medicine item){
try{
datacon.openConnection();
datacon.st.executeUpdate("update medicine set Price='"+item.getUnitPrice()+"' where MedID='"+item.getMedId()+"';");
datacon.closeConnection();
}
catch(Exception e){
System.out.println(e.getMessage());
}
}

public  Medicine getMedicine(String Name){
	Medicine med = null;    
	try{
		datacon.openConnection();
	datacon.result = datacon.st.executeQuery("select * from medicine where Name = '"+Name+"';");
		while(datacon.result.next()){
			 String MedId = datacon.result.getString("MedID");
			 String MedName = datacon.result.getString("Name");
			 double unitPrice = datacon.result.getDouble("Price");
			 java.sql.Date ExpiryDate = datacon.result.getDate("ExpiryDate");
			 LocalDate localDate = ExpiryDate.toLocalDate();
			 int exp_day = localDate.getDayOfMonth();
			 int exp_month = localDate.getMonthValue();
			 int exp_year = localDate.getYear();
			 MyDate expdate = new MyDate(exp_day,exp_month,exp_year);
			 int quantity = datacon.result.getInt("quantity");

			 med = new Medicine(MedId,MedName,expdate,unitPrice,quantity);
			 
			 }
}
catch(Exception e){
	System.out.println("getmed"+e.getMessage());
	}
	datacon.closeConnection();
	return med;
}

public String checkExpiry(Medicine item) {
	LocalDate curr_date = java.time.LocalDate.now();
	int month = curr_date.getMonthValue();
	int date = curr_date.getDayOfMonth();
	int year = curr_date.getYear();
	
	String ExpiryStatus = null;
	MyDate exp_date = item.getExpiryDate();
	int exp_day = exp_date.day;
	int exp_month = exp_date.month;
	int exp_year = exp_date.year;
	expirationStatus singleton = expirationStatus.INSTANCE;
	
	if(year > exp_year) {
	singleton.setValue("Expired");
	ExpiryStatus = singleton.getValue();}
	
	else if(year == exp_year) {
		if((month - exp_month) > 0) {
			singleton.setValue("Expired");
			ExpiryStatus = singleton.getValue();}
		else if(month == exp_month) {
			if(date >= exp_day) {
				singleton.setValue("Expired");
				ExpiryStatus = singleton.getValue();
			}
		else if((month - exp_month) < 3 ) {
			singleton.setValue("ExpiringSoon");
			ExpiryStatus = singleton.getValue();}
		
			else {
				singleton.setValue("NotExpired");
				ExpiryStatus = singleton.getValue();
			}
		}
		else {
			singleton.setValue("NotExpired");
			ExpiryStatus = singleton.getValue();
		}
	}
	
	else {
		singleton.setValue("NotExpired");
		ExpiryStatus = singleton.getValue();
		}
	
	return ExpiryStatus;
}

public String[][] getMedicines(){
	 ArrayList<Medicine> ar = new ArrayList<Medicine>();
	
	 try{
	 datacon.openConnection();
	 datacon.result = datacon.st.executeQuery("SELECT * FROM medicine;");
	
	 while(datacon.result.next()){
	 String MedId = datacon.result.getString("MedID");
	 String MedName = datacon.result.getString("Name");
	 double unitPrice = datacon.result.getDouble("Price");
	 java.sql.Date ExpiryDate = datacon.result.getDate("ExpiryDate");
	 LocalDate localDate = ExpiryDate.toLocalDate();
	 int exp_day = localDate.getDayOfMonth();
	 int exp_month = localDate.getMonthValue();
	 int exp_year = localDate.getYear();
	 MyDate expdate = new MyDate(exp_day,exp_month,exp_year);
	 int quantity = datacon.result.getInt("quantity"); 
	
	 Medicine med = new Medicine(MedId,MedName,expdate,unitPrice,quantity);
	 ar.add(med);
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
	MyDate date = ((Medicine)obj[i]).getExpiryDate();
	String date_str = date.year + "-" + date.month + "-" + date.day;
	 data[i][0] = ((Medicine)obj[i]).getMedId();
	 data[i][1] = ((Medicine)obj[i]).getMedName();
	 data[i][2] = date_str;
	 data[i][3] = ((Medicine)obj[i]).getUnitPrice()+"";
	 data[i][4] = ((Medicine)obj[i]).getQuantity()+"";
	 }
	 return data;
	 }

}