import java.sql.*;


public class Warehouse {

	private static Worder worder;
	
	private static GUI gui;
	static final String DriverBackX ="";
	static final String UrlBackX = "";
	static final String UserBackX = "";
	static final String PasswordBackX = "";
	
	static final String DriverBackY ="";
	static final String UrlBackY = "";
	static final String UserBackY = "";
	static final String PasswordBackY = "";
	
	static final String DriverBackZ ="";
	static final String UrlBackZ = "";
	static final String UserBackZ = "";
	static final String PasswordBackZ = "";
	
	static final String DriverFront ="";
	static final String UrlFront = "";
	static final String UserFront = "";
	static final String PasswordFront = "";
	
	public static void main(String args[]) throws ClassNotFoundException{
		Connection conBackX = null;
		Statement stmtBackX = null;
		
		Connection conBackY = null;
		Statement stmtBackY = null;
		
		Connection conBackZ = null;
		Statement stmtBackZ = null;

		Connection conFront = null;
		Statement stmtFront = null;
		try{
			Class.forName(DriverBackX);
			conBackX = DriverManager.getConnection(UrlBackX,UserBackX,PasswordBackX);
			stmtBackX = conBackX.createStatement();
			String backX;
			backX = "SELECT partID, warehouseID,current FROM Stock";
			ResultSet rsX = stmtBackX.executeQuery(backX);
			
			Class.forName(DriverBackY);
			conBackY = DriverManager.getConnection(UrlBackY,UserBackY,PasswordBackY);
			stmtBackY = conBackY.createStatement();
			String backY;
			backY = "SELECT partID, warehouseID,current FROM Stock";
			ResultSet rsY = stmtBackY.executeQuery(backY);
			
			Class.forName(DriverBackZ);
			conBackZ = DriverManager.getConnection(UrlBackZ,UserBackZ,PasswordBackZ);
			stmtBackZ = conBackZ.createStatement();
			String backZ;
			backZ = "SELECT partID, warehouseID,current FROM Stock";
			ResultSet rsZ = stmtBackZ.executeQuery(backZ);
			
			Class.forName(DriverFront);
			conFront = DriverManager.getConnection(UrlFront,UserFront,PasswordFront);
			stmtFront = conFront.createStatement();
			String front;
			front = "";
			ResultSet rsf = stmtFront.executeQuery(front);
			while(rsX.next() && rsY.next() && rsZ.next()){
				int partIdX = rsX.getInt("PartID");//get PartId from database
				String wIdX = rsX.getString("warehouseID");//get warehouseId from databse
				int amountX = rsX.getInt("current");//get current amount from databse
				
				int partIdY = rsY.getInt("PartID");//get PartId from database
				String wIdY = rsY.getString("warehouseID");//get warehouseId from databse
				int amountY = rsX.getInt("current");//get current amount from databse
				
				int partIdZ = rsZ.getInt("PartID");//get PartId from database
				String wIdZ = rsZ.getString("warehouseID");//get warehouseId from databse
				int amountZ = rsZ.getInt("current");//get current amount from databse
				
				if(worder.getPid() == partIdX){
					if(worder.getRamount() < amountX){
						//the order can be process.
					}
					else if(worder.getRamount() == amountX){
						//the order can be process.
					}
					else{
						//the order can not process, not enought product amount.
					}
				}
				else{
					System.out.print("Product ID not match, please check the product.");
				}
				
			}
			rsX.close();
			stmtBackX.close();
			conBackX.close();
		}
		catch(SQLException sqle){
			sqle.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
            try{
                if(stmtBackX!=null) stmtBackX.close();
            }catch(SQLException se2){}
            try{
                if(conBackX!=null) conBackX.close();
            }catch(SQLException sqle){
                sqle.printStackTrace();
            }
		}
	}
}
