package NI_GUI;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class LoadCSV {
	
	public void loadCSV(Connection conn, String addr){
		Statement stmt = null;
		String sql = "LOAD DATA LOCAL INFILE '"+addr+"' INTO TABLE `Neighborhood_Informant` FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\n' IGNORE 1 ROWS; ";
		try{
			stmt = conn.createStatement();
			stmt.execute(sql);
			stmt.close();
		}catch(SQLException sqle){
			System.out.println("Error: "+sqle.getMessage());
		}
	}
}
