package NI_GUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseModifier {
	
	public Connection connectDB() {
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "440_group7";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "root";
        Connection conn = null;
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, userName, password);
          //  System.out.println("Connected to the database");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }
	
	public void creatTable(Connection conn) {
        Statement stmt = null;
        String sql1 = "CREATE TABLE `440_group7`.`Neighborhood_Informant` (`community_area` VARCHAR(30)  NOT NULL, `community_area_number` int(11),`property_type` VARCHAR(30)  NOT NULL,`property_name` TEXT NOT NULL,`address` CHAR(50) NOT NULL, `zip_code` int(11) NOT NULL,`phone_number` VARCHAR(20) NOT NULL,`management_company` TEXT NOT NULL,`units` int(11) NOT NULL, `x_coordinate` VARCHAR(30), `y_coordinate` VARCHAR(30), `latitude` VARCHAR(30),`longitude` VARCHAR(30),`location` TEXT )";
//        String sql2 = "CREATE TABLE `courses`.`PrerequisiteCourse` (`cid` INTEGER  NOT NULL,`pid` INTEGER  NOT NULL,  PRIMARY KEY (`cid`, `pid`))";
        try {
            stmt = conn.createStatement();
            stmt.execute(sql1);
//            stmt.execute(sql2);
            stmt.close();
        } catch (SQLException sqle) {
            System.out.println("Error: "+sqle.getMessage());
        }
    }
	
	public void deleteTable(Connection conn) {
	       Statement stmt = null;
	        String sql1 = "drop table `440_group7`.`Neighborhood_Informant`";
//	        String sql2 = "drop table `courses`.`PrerequisiteCourse`";
	        try {
	            stmt = conn.createStatement();
	            stmt.execute(sql1);
//	            stmt.execute(sql2);
	            stmt.close();
	        } catch (SQLException sqle) {
				System.out.println("Error: "+sqle.getMessage());
	        }
	    }
}
