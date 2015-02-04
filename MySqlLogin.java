package NI_GUI;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MySqlLogin {


	public int getInfo(Connection conn, String Id, String PW, int info) throws SQLException{
	
		int Serial = -99;
	Statement select = null;
	Connection connection = null;
	connection = conn;
	
	select = connection.createStatement();
	
	if(info == 1){
	
	ResultSet data = select.executeQuery("SELECT Serial FROM Info WHERE Id ='"+Id+"' AND Password ='"+PW+"'");
	while(data.next())
    {	
		Serial = Integer.parseInt(data.getString(1));
		
        System.out.println("Serial Number is : " +Serial);    //First Column

    }
	
	if(Serial == -99){
		System.out.printf(" Fail, Serial : %d ", Serial);
		return Serial;
	}
	else{
		System.out.printf(" Successful, Serial : %d ", Serial);		
		return Serial;
	}}
	
	if(info == 2){
		
		Serial = 0;
		ResultSet data = select.executeQuery("SELECT MAX(Serial) FROM Info");
		while(data.next())
	    {	
			Serial = Integer.parseInt(data.getString(1));
			if(data.getString(1) == null){
				Serial = 0;
			}
			Serial = Serial+1;
	        System.out.printf(" Maximum Number is : %d", Serial);    //First Column
	        
	    }
		
		select.executeUpdate("INSERT INTO Info VALUES("+Serial+",'"+Id+"','"+PW+"')");
		
	}
	return Serial;
}
	
	
	


	public void insertNewInfo(Connection conn, String Id, String PW) {
		// TODO Auto-generated method stub
	

		int Serial = -99;
		Statement select = null;
		Connection connection = null;
		connection = conn;
		
		try {
			select = connection.createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		int NewSerial = -990;
		ResultSet data = null;
		try {
			data = select.executeQuery("SELECT MAX(Serial) FROM Info");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			NewSerial = Integer.parseInt(data.getString(1));
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			select.executeUpdate(" INSET INTO Info VALUES("+(NewSerial+1)+",'"+Id+"','"+PW+"')");
			System.out.printf("\n INSER Successful \n");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
