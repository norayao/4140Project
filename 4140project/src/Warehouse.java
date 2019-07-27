import java.sql.*;


public class Warehouse {

	private static Worder worder;
	
	private static GUI gui;
	static final String DriverBackX ="com.mysql.jdbc.Driver";
	static final String UrlBackX = "";
	static final String UserBackX = "";
	static final String PasswordBackX = "";
	
	static final String DriverBackY ="com.mysql.jdbc.Driver";
	static final String UrlBackY = "";
	static final String UserBackY = "";
	static final String PasswordBackY = "";
	
	static final String DriverBackZ ="com.mysql.jdbc.Driver";
	static final String UrlBackZ = "";
	static final String UserBackZ = "";
	static final String PasswordBackZ = "";
	
	static final String DriverFront ="com.mysql.jdbc.Driver";
	static final String UrlFront = "";
	static final String UserFront = "";
	static final String PasswordFront = "";
	
	public static void main(String args[]) throws ClassNotFoundException{
		
	}
	public void DBoperation(){
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
			backX = "SELECT * FROM Stock";
			ResultSet rsX = stmtBackX.executeQuery(backX);
			PreparedStatement pstmtX = conBackX.prepareStatement("UPDATE Stock SET current = ? WHERE PartID = ?, warehouseID = ?");

			Class.forName(DriverBackY);
			conBackY = DriverManager.getConnection(UrlBackY,UserBackY,PasswordBackY);
			stmtBackY = conBackY.createStatement();
			String backY;
			backY = "SELECT * FROM Stock";
			ResultSet rsY = stmtBackY.executeQuery(backY);
			PreparedStatement pstmtY = conBackY.prepareStatement("UPDATE Stock SET current = ? WHERE PartID = ?, warehouseID = ?");

			Class.forName(DriverBackZ);
			conBackZ = DriverManager.getConnection(UrlBackZ,UserBackZ,PasswordBackZ);
			stmtBackZ = conBackZ.createStatement();
			String backZ;
			backZ = "SELECT * FROM Stock";
			ResultSet rsZ = stmtBackZ.executeQuery(backZ);
			PreparedStatement pstmtZ = conBackZ.prepareStatement("UPDATE Stock SET current = ? WHERE PartID = ?, warehouseID = ?");

			Class.forName(DriverFront);
			conFront = DriverManager.getConnection(UrlFront,UserFront,PasswordFront);
			stmtFront = conFront.createStatement();
			String front;
			front = "";
			ResultSet rsf = stmtFront.executeQuery(front);
			PreparedStatement pstmtF = conBackZ.prepareStatement("UPDATE Line SET partStatus = ? WHERE partID = ?");

			while(rsX.next() && rsY.next() && rsZ.next()){
				String partIdX = rsX.getString("PartID");//get PartId from database X
				int wIdX = rsX.getInt("warehouseID");//get warehouseId from databse X
				int amountX = rsX.getInt("current");//get current amount from databse X
				
				String partIdY = rsY.getString("PartID");//get PartId from database Y
				int wIdY = rsY.getInt("warehouseID");//get warehouseId from databse Y
				int amountY = rsX.getInt("current");//get current amount from databse Y
				
				String partIdZ = rsZ.getString("PartID");//get PartId from database Z
				int wIdZ = rsZ.getInt("warehouseID");//get warehouseId from databse Z
				int amountZ = rsZ.getInt("current");//get current amount from databse Z
				
				if(worder.getPid() == partIdX && worder.getPid() == partIdY && worder.getPid() == partIdZ){
					int amountTotal = amountX + amountY + amountZ;
					if(worder.getRamount() <= amountTotal){ //order amount <= stock amount can be processed
						if(worder.getRamount() < amountX){
							//If the request amount can totally take from warehouse X, then only reduce prodcut amount in X
							pstmtX.setInt(1, amountX - worder.getRamount());
							pstmtX.setString(2, partIdX);
							pstmtX.setInt(3, wIdX);
							pstmtX.executeUpdate();
							pstmtF.setString(1, "Ture");
							pstmtF.executeUpdate();
			
						}
						else if(worder.getRamount() > amountX && worder.getRamount() <= (amountX+amountY)){
							//If the request amount is larger than X current stock, but smaller than total stock of X and Y, then first
							//take all stock from X then rest of amount take from Y
							int temp; 
							temp = worder.getRamount() - amountX;
							pstmtX.setInt(1, 0);
							pstmtX.setString(2, partIdX);
							pstmtX.setInt(3, wIdX);
							pstmtY.setInt(1, amountY - temp);
							pstmtY.setString(2, partIdY);
							pstmtY.setInt(3, wIdY);
							pstmtX.executeUpdate();
							pstmtY.executeUpdate();
							pstmtF.setString(1, "Ture");
							pstmtF.executeUpdate();
						}
						else if(worder.getRamount() > (amountX + amountY) && worder.getRamount() <= (amountX + amountY + amountZ)){
							//if the request amount is larger than X and Y, then first take from X and Y then rest of amount take from Z.
							int temp;
							temp = worder.getRamount() - (amountX + amountY);
							pstmtX.setInt(1, 0);
							pstmtX.setString(2, partIdX);
							pstmtX.setInt(3, wIdX);
							pstmtY.setInt(1, 0);
							pstmtY.setString(2, partIdY);
							pstmtY.setInt(3, wIdY);
							pstmtZ.setInt(1, amountZ - temp);
							pstmtZ.setString(2, partIdZ);
							pstmtZ.setInt(3, wIdZ);
							pstmtX.executeUpdate();
							pstmtY.executeUpdate();
							pstmtZ.executeUpdate();
							pstmtF.setString(1, "Ture");
							pstmtF.executeUpdate();
						}
						else{
							//This may not happen ever, but in case.
							System.out.print("Error or insufficient inventory");
							pstmtF.setString(1, "False");
							pstmtF.executeUpdate();
						}
					}
					else{
						//If order amount is larger than total current of X, Y and Z. 
						System.out.print("Insufficient inventory, please reduce the order quantity or cancel the order");
						pstmtF.setString(1, "False");
						pstmtF.executeUpdate();
					}
				}
				else{
					//Prodcut Id error
					System.out.print("Product ID not match, please check the product.");
					pstmtF.setString(1, "False");
					pstmtF.executeUpdate();
				}
				
			}
			rsX.close();
			stmtBackX.close();
			conBackX.close();
			rsY.close();
			stmtBackY.close();
			conBackY.close();
			rsZ.close();
			stmtBackZ.close();
			conBackZ.close();
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
