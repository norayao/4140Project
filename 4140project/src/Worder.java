import java.sql.*;

public class Worder {

	private int ProductId,OrderId,Ramount,PamountTotal,PamountX,PamountY,PamountZ;
	private String CompanyId,CompanyName;
	
	
	public Worder(int Pid, String Cid, int PamountTotal, int Ramount, int OrderId, String CompanyName){
		ProductId = Pid;
		CompanyId = Cid;
		this.PamountTotal = PamountTotal;
		this.Ramount = Ramount;
		this.OrderId = OrderId;
		this.CompanyName = CompanyName;
		
	}
	public int getPid(){
		return ProductId;
	}
	public void setPid(int Pid){
		ProductId = Pid;
	}
	
	public String getCid(){
		return CompanyId;
	}
	public void setCid(String Cid){
		CompanyId = Cid;
	}
	public int getPamountTotal(){
		return PamountTotal;
	}
	public void setPamount(int PamountTotal){
		this.PamountTotal = PamountTotal;
	}
	public int getRamount(){
		return Ramount;
	}
	public void setRamount(int Ramount){
		this.Ramount = Ramount;
	}
	public int getOrderId(){
		return OrderId;
	}
	public void setOrderId(int OrderId){
		this.OrderId = OrderId;
	}
	public String getCName(){
		return CompanyName;
	}
	public void setCName(String CName){
		CompanyName = CName;
	}
}
