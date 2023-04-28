package Controller;

import java.util.ArrayList;
import Model.*;

public class BillController implements IBill{

DatabaseConnection datacon = new DatabaseConnection();

public BillController(){}

	public int getSaleId(){
		int saleId=-1;
		try{
		datacon.openConnection();
		datacon.result=datacon.st.executeQuery("select MAX(saleId) from sale ;");
		
		while(datacon.result.next()){
		saleId = datacon.result.getInt("MAX(saleId)");
		}
		}
		catch(Exception e){
		System.out.println(e.getMessage());
		}
		
		datacon.closeConnection();
		
		return saleId+1;
	}

public double calculatePrice(String BillId){
	double amount=0;
	try{
	datacon.openConnection();
	datacon.result=datacon.st.executeQuery("select sum(amount) from sale where SaleId = '"+BillId+"';");
	
	while(datacon.result.next()){
	amount = datacon.result.getDouble("sum(amount)");
	}
	}
	catch(Exception e){
	System.out.println(e.getMessage());
	}
	datacon.closeConnection();
	
	return amount;
	}
	

	public void insertSale(Bill bill){
	try{
	datacon.openConnection();
	datacon.st.executeUpdate("insert into sale values('"+bill.getBillId()+"','"+bill.getMedId()+"','"+bill.getMedName()+"','"+bill.getquantity()+"','"+bill.getUnitPrice()+"','"+bill.getamount()+"')");
	datacon.closeConnection();
	}
	catch(Exception e){
	System.out.println(e.getMessage());
	}
	}
	
	public void updateSale(Bill bill){
	try{
	datacon.openConnection();
	datacon.st.executeUpdate("update sale set quantity='"+bill.getquantity()+ "', amount ='" +bill.getamount()+ "' where medId='"+bill.getMedId()+"';");
	datacon.closeConnection();
	}
	catch(Exception e){
	System.out.println(e.getMessage());
	}
	}

	public String [][] getSale(String id){
		ArrayList<Bill> ar = new ArrayList<Bill>();
		try{
			datacon.openConnection();
			datacon.result=datacon.st.executeQuery("select saleId, medId, medName, quantity, unitPrice, amount from sale where saleId ='"+id+"';");

			while(datacon.result.next()){
				int saleId = datacon.result.getInt("saleId");
				String medId = datacon.result.getString("medId");
				String medName = datacon.result.getString("medName");
				int quantity = datacon.result.getInt("quantity");
				double unitPrice = datacon.result.getDouble("unitPrice");
				double amount = datacon.result.getDouble("amount");
				
				Bill sale = new Bill(saleId, medId, medName, unitPrice, amount,quantity);
				ar.add(sale);
			}
		}
		
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		datacon.closeConnection();
		
		Object obj[] = ar.toArray();
		String data[][] = new String [ar.size()][6];
		
		for(int i=0; i<obj.length; i++)
		{
			data[i][0] = ((Bill)obj[i]).getBillId()+"";
			data[i][1] = ((Bill)obj[i]).getMedId()+"";
			data[i][2] = ((Bill)obj[i]).getMedName();
			data[i][3] = ((Bill)obj[i]).getquantity()+"";
			data[i][4] = ((Bill)obj[i]).getUnitPrice()+"";
			data[i][5] = ((Bill)obj[i]).getamount()+"";
		}
		return data;
	}
}