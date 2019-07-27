	
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.sql.Connection;
import java.sql.DriverManager;
public class GUI {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";    // connect the db
    static final String DB_URL = "jdbc:mysql://localhost:3306/Ass4";
    static final String USER = "root";
    static final String PASS = "Zy123456";
    //public static Worder worder;
    public static void main(String args[]) {
        
		JFrame myframe = new JFrame("Main Page");      // main frame
        myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myframe.setSize(800, 800);

        JPanel pane2 = new JPanel();     // create buttons in main frame 
        JButton Create = new JButton("View the order History");
        pane2.add(Create);
        
        JButton phone = new JButton("Have an Order now");
        pane2.add(phone);
        
        JPanel panel = new JPanel();

        JTextField testPart = new JTextField(5);
  
        panel.add(testPart);
   
        JTextArea information = new JTextArea();
            ///////------------------------------------------Add phone number BUTTON EVENT------------------------------------------///////       
        phone.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	myframe.dispose();
            	JFrame myframePhone = new JFrame("Have an Order now");
            	myframePhone.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                myframePhone.setSize(600, 400);
                JPanel pane1Create = new JPanel();
                JPanel pane2Create = new JPanel();
                JPanel pane3Create = new JPanel();
                JButton Back = new JButton("Back to main page");
                JButton PhoneScc = new JButton("Deal");
                pane1Create.add(Back);
                pane3Create.add(PhoneScc);
                Back.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    myframePhone.dispose();
                    myframe.setVisible(true);
                    }
                });
                JLabel labelCreate1 = new JLabel("Enter the Company ID:");
                JTextField testPartCreate1 = new JTextField(48);
                pane2Create.add(labelCreate1); 
                pane2Create.add(testPartCreate1);
                //String CompanyId = testPartCreate1.getText().toString();
                //worder.setCid(CompanyId);
                JLabel labelCreate2 = new JLabel("Enter the Company Name:");
                JTextField testPartCreate2 = new JTextField(48);
                pane2Create.add(labelCreate2); 
                pane2Create.add(testPartCreate2);
                //String CompanyName = testPartCreate2.getText().toString();
                //worder.setCName(CompanyName);
                JLabel labelCreate3 = new JLabel("Enter the Product ID that you want:");
                JTextField testPartCreate3 = new JTextField(48);
                pane2Create.add(labelCreate3); 
                pane2Create.add(testPartCreate3);
                //String ProductId = testPartCreate3.getText().toString();
                //worder.setPid(ProductId);
                JLabel labelCreate4 = new JLabel("Enter the amount of the Product that you want to buy:");
                JTextField testPartCreate4 = new JTextField(48);
                pane2Create.add(labelCreate4); 
                pane2Create.add(testPartCreate4);
                //String ProductAmount = testPartCreate4.getText().toString();
                //worder.setPamount(Integer.parseInt(ProductAmount));
                PhoneScc.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	String infor="";
                    	Connection conn = null;
                        Statement stmt = null;
                        try{
                        	Class.forName(JDBC_DRIVER);
                            conn = DriverManager.getConnection(DB_URL,USER,PASS);
                            stmt = conn.createStatement();

                            String sql;
                            sql = "INSERT INTO Inovice (invoiceID) " + "VALUES ('" +testPartCreate3.getText()+"')";

                            
                            //sql = "INSERT INTO phoneNumber_249 (id_249, phoneNum_249)" + 
                            		//"VALUES ('"+testPartCreate1.getText()+"', '"+testPartCreate2.getText()+"');";   
                            PreparedStatement pstmt = conn.prepareStatement(sql);
                            
                            pstmt.executeUpdate();
                            
                            pstmt.close();
                            stmt.close();
                            conn.close();     
                        }catch(SQLException se){
                            se.printStackTrace();
                        }catch(Exception e1){
                            e1.printStackTrace();
                        }finally{
                            try{
                                if(stmt!=null) stmt.close();
                            }catch(SQLException se2){
                            }
                            try{
                                if(conn!=null) conn.close();
                            }catch(SQLException se){
                                se.printStackTrace();
                            }
                        }             
                        myframePhone.dispose();
                        myframe.setVisible(true);
                    }
                            
                    });
                        
                    
                myframePhone.getContentPane().add(BorderLayout.NORTH, pane1Create);
                myframePhone.getContentPane().add(BorderLayout.CENTER, pane2Create);
                myframePhone.getContentPane().add(BorderLayout.SOUTH, pane3Create);
                myframePhone.setVisible(true);
            }
        });
            
                    	
              
///////------------------------------------------CREATE BUTTON EVENT------------------------------------------///////        
        Create.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                                myframe.dispose();
                JFrame myframeCreate = new JFrame("Loging in as manager to view history");
                myframeCreate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                myframeCreate.setSize(600, 300);
                JPanel pane1Create = new JPanel();
                JPanel pane2Create = new JPanel();
                JPanel pane3Create = new JPanel();
                JButton Back = new JButton("Back to main page");
                JButton CreateScc = new JButton("Log in");
                pane1Create.add(Back);
                pane3Create.add(CreateScc);
                Back.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    myframeCreate.dispose();
                    myframe.setVisible(true);
                    }
                });
                
                JLabel labelCreate1 = new JLabel("Enter Manage name:");
                JTextField testPartCreate1 = new JTextField(48);
                pane2Create.add(labelCreate1); 
                pane2Create.add(testPartCreate1);
                JLabel labelCreate2 = new JLabel("Enter Password:");
                JTextField testPartCreate2 = new JTextField(48);
                pane2Create.add(labelCreate2); 
                pane2Create.add(testPartCreate2);
                
                
                  CreateScc.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	String infor="";
                    	Connection conn = null;
                        Statement stmt = null;
                        try{
                            Class.forName(JDBC_DRIVER);
                            conn = DriverManager.getConnection(DB_URL,USER,PASS);
                            stmt = conn.createStatement();
                            String sql;
                            sql = "INSERT INTO team_249 (id_249, name_249, major_249, totleTeamNumber_249)" + 
                            		"VALUES ('"+testPartCreate2.getText()+"', '"+testPartCreate1.getText()+"');";                            
                            PreparedStatement posted = conn.prepareStatement(sql);                           
                            posted.executeUpdate();
                            PreparedStatement posted2 = conn.prepareStatement(sql);                           
                            posted2.executeUpdate();
                            posted.close();
                            posted2.close();
                            stmt.close();
                            conn.close();
                            
                        }catch(SQLException se){
                            se.printStackTrace();
                        }catch(Exception e1){
                            e1.printStackTrace();
                        }finally{
                            try{
                                if(stmt!=null) stmt.close();
                            }catch(SQLException se2){
                            }
                            try{
                                if(conn!=null) conn.close();
                            }catch(SQLException se){
                                se.printStackTrace();
                            }
                        }
                    	/////////////////////////////DB//////////////////////////////////
                    	myframeCreate.dispose();
                        myframe.setVisible(true);
                        information.setText("Succsess Create: ");
                    }
                });
                myframeCreate.getContentPane().add(BorderLayout.NORTH, pane1Create);
                myframeCreate.getContentPane().add(BorderLayout.CENTER, pane2Create);
                myframeCreate.getContentPane().add(BorderLayout.SOUTH, pane3Create);
                myframeCreate.setVisible(true);
            }
        });
        
        

        
        
        
        
        
        //myframe.getContentPane().add(BorderLayout.SOUTH, panel);
        myframe.getContentPane().add(BorderLayout.NORTH, pane2);
        myframe.getContentPane().add(BorderLayout.CENTER, information);
        myframe.setVisible(true);

       

        String infor="";
    	Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM team_249 ";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String name = rs.getString("name_249");   
                String id = rs.getString("id_249");
                String major = rs.getString("major_249"); 
                String totalTeamNumber = rs.getString("totleTeamNumber_249");
                infor = infor + "Name: "+name+"     "+"ID: "+id+"     "+"Major: "+major+"     "+"Total Number of Teammates: " + totalTeamNumber+"\n";
                information.setText(infor);
            }
            rs.close();
            stmt.close();
            conn.close();            
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e3){
            e3.printStackTrace();
        }finally{
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
	}
}

        
