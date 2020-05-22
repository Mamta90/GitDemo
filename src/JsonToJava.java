import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.protocol.Resultset;

public class JsonToJava {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, JsonGenerationException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
Class.forName("com.mysql.cj.jdbc.Driver");
Connection conn=null;
//CustometInfo c= new CustometInfo();

ArrayList<CustometInfo> a=new ArrayList<CustometInfo>();

conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/SDEMO", "root", "Neutrino@123");

Statement st=conn.createStatement();
 ResultSet rs= st.executeQuery("Select * from Customerinfo where Location='Asia' and PurchaseDate=current_date() ;");
	
 
 while(rs.next())
 {
	 CustometInfo c=new CustometInfo();
	 c.setId(rs.getInt(1));
	 c.setCourseName(rs.getString(2));
	 c.setPurchaseDate(rs.getString(3));
	 c.setAmount(rs.getInt(4));
	 c.setLocation(rs.getString(5));
	 a.add(c);
	 
	// System.out.println(c.getId());
	//System.out.println(c.getCourseName());
	//System.out.println(c.getPurchaseDate());
	// System.out.println(c.getAmount());
	 //System.out.println(c.getLocation());
 }
 for(int i=0;i<a.size();i++)
 {
   ObjectMapper O=new ObjectMapper();
   O.writeValue(new File("C:\\Users\\mamta\\eclipse-workspace\\Jsonjava\\CustomerInfo"+i+".json"),a.get(i));
 }
	conn.close();
	}

}
 