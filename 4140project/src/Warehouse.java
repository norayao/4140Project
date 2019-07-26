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
				
				if(worder.getPid() == partIdX && worder.getPid() == partIdY && worder.getPid() == partIdZ){
					int amountTotal = amountX + amountY + amountZ;
					if(worder.getRamount() <= amountTotal){
						//order amount <= stock amount can be processed
						if(worder.getRamount() < amountX){
							pstmtX.setInt(1, amountX - worder.getRamount());
							pstmtX.setInt(2, partIdX);
							pstmtX.setString(3, wIdX);
							pstmtX.executeUpdate();
						}
						else if(worder.getRamount() > amountX && worder.getRamount() <= (amountX+amountY)){
							int temp; 
							temp = worder.getRamount() - amountX;
							pstmtX.setInt(1, 0);
							pstmtX.setInt(2, partIdX);
							pstmtX.setString(3, wIdX);
							pstmtY.setInt(1, amountY - temp);
							pstmtY.setInt(2, partIdY);
							pstmtY.setString(3, wIdY);
							pstmtX.executeUpdate();
							pstmtY.executeUpdate();
						}
						else if(worder.getRamount() > (amountX + amountY) && worder.getRamount() <= (amountX + amountY + amountZ)){
							int temp;
							temp = worder.getRamount() - (amountX + amountY);
							pstmtX.setInt(1, 0);
							pstmtX.setInt(2, partIdX);
							pstmtX.setString(3, wIdX);
							pstmtY.setInt(1, 0);
							pstmtY.setInt(2, partIdY);
							pstmtY.setString(3, wIdY);
							pstmtZ.setInt(1, amountZ - temp);
							pstmtZ.setInt(2, partIdZ);
							pstmtZ.setString(3, wIdZ);
							pstmtX.executeUpdate();
							pstmtY.executeUpdate();
							pstmtZ.executeUpdate();
						}
						else{
							System.out.print("Error or insufficient inventory");
						}
					}
					else{
						System.out.print("Insufficient inventory, please reduce the order quantity or cancel the order");
					}
				}
				else{
					System.out.print("Product ID not match, please check the product.");
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
