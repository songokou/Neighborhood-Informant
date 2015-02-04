package NI_GUI;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextArea;

public class DataAccesser {
	private JTextArea textA;

	public DataAccesser(JTextArea textArea) {
		textA = textArea;
	}

	public void infoFromCmmName(Database db, String cmmName) {
		textA.append("USER INPUT = " + cmmName + "\n");
		int index = 0,counter = 0;
		textA.append("\n--------------------------------------------------------------------------------------------------------------------------------");
		textA.append("\n"
				+ String.format("| %-25s | %-30s | %-8s | %-35s | %-15s |",
						"Property Type", "Address", "ZipCode", "Management",
						"Phone#"));
		textA.append("\n--------------------------------------------------------------------------------------------------------------------------------");
		while (index < db.getSize()) {
			HouseInfo house = db.getHouses().get(index);
			if (house.getAreaName().equals(cmmName)) {
				String ptype = house.getpType();
				String address = house.getAddress();
				String zip = house.getZipCode();
				String company = house.getCompany();
				String phone = house.getPhoneNum();
				textA.append("\n"
						+ String.format(
								"| %-25s | %-30s | %-8s | %-35s | %-15s |",
								ptype, address, zip, company, phone));
				counter++;
			}
			index++;
		}
		if (counter == 0) {
			textA.append("\n\t\t\t\t---- Sorry, Cannot find Information :( ----");
		}
		textA.append("\n--------------------------------------------------------------------------------------------------------------------------------\n");

	}

	public void infoFromPtype(Database db, String ptype) {
		int index = 0, counter = 0;
		textA.append("USER INPUT = " + ptype + "\n");
		textA.append("\n--------------------------------------------------------------------------------------------------------------------------------");
		textA.append("\n"
				+ String.format("| %-25s | %-30s | %-8s | %-35s | %-15s |",
						"Community Area", "Address", "ZipCode", "Management",
						"Phone#"));
		textA.append("\n--------------------------------------------------------------------------------------------------------------------------------");
		while (index < db.getSize()) {
			HouseInfo house = db.getHouses().get(index);
			if (house.getpType().equals(ptype)) {
				String com = house.getAreaName();
				String address = house.getAddress();
				String zip = house.getZipCode();
				String company = house.getCompany();
				String phone = house.getPhoneNum();
				textA.append("\n"
						+ String.format(
								"| %-25s | %-30s | %-8s | %-35s | %-15s |",
								com, address, zip, company, phone));
				counter++;
			}
			index++;
		}
		if (counter == 0)
			textA.append("\n\t\t\t\t---- Sorry, Cannot find Information :( ----");
		textA.append("\n--------------------------------------------------------------------------------------------------------------------------------\n");
	}

	public void infoFromZip(Database db, String zipcode) {
		int index = 0,counter = 0;
		textA.append("USER INPUT = " + zipcode + "\n");
		textA.append("\n----------------------------------------------------------------------------------------------------------------------");
		textA.append("\n"
				+ String.format("| %-25s | %-30s | %-35s | %-15s |",
						"Community Area", "Address", "Management", "Phone#"));
		textA.append("\n----------------------------------------------------------------------------------------------------------------------");
		while (index < db.getSize()) {
			HouseInfo house = db.getHouses().get(index);
			if (house.getZipCode().equals(zipcode)) {
				String comName = house.getAreaName();
				String address = house.getAddress();
				String company = house.getCompany();
				String phone = house.getPhoneNum();
				textA.append("\n"
						+ String.format("| %-25s | %-30s | %-35s | %-15s |",
								comName, address, company, phone));
				counter++;
			}
			index++;
		}
		if (counter == 0)
			textA.append("\n\t\t\t\t---- Sorry, Cannot find Information :( ----");
		textA.append("\n--------------------------------------------------------------------------------------------------------------------------------\n");
	}

	public void ManagementCompany(Database db, String Name) {
		int index = 0,counter = 0;
		textA.append("USER INPUT = " + Name + "\n");
		textA.append("\n--------------------------------------------------------------------------------------------------------------------------------");
		textA.append("\n"
				+ String.format(
						"| %-30s | %-30s | %-30s | %-10s | %-20s | %-30s |",
						"Community Area Name", "Poperty Type", "Address",
						"Zip Code", "Phone#", "Property Name"));
		textA.append("\n--------------------------------------------------------------------------------------------------------------------------------");
		while (index < db.getSize()) {
			HouseInfo house = db.getHouses().get(index);
			if (house.getCompany().equals(Name)) {
				String communityArea = house.getAreaName();
				String ptype = house.getpType();
				String address = house.getAddress();
				String zip = house.getZipCode();
				String phone = house.getPhoneNum();
				String pname = house.getpName();
				textA.append("\n"
						+ String.format(
								"| %-30s | %-30s | %-30s | %-10s | %-20s | %-30s |",
								communityArea, ptype, address, zip, phone,
								pname));
				counter++;
			}
			index++;

		}
		if (counter == 0)
			textA.append("\n\t\t\t\t---- Sorry, Cannot find Information :( ----");
		textA.append("\n--------------------------------------------------------------------------------------------------------------------------------\n");
	}

	public void CommunityAreaNumber(Database db, int number) {
		int index = 0,counter=0;
		textA.append("\nUSER INPUT = " + number + "\n");
		textA.append("\n--------------------------------------------------------------------------------------------------------------------------------");
		textA.append("\n"
				+ String.format("| %-30s | %-30s | %-10s | %-30s | %-30s |",
						"Community Area Name", "Property Type", "Zip-Code",
						"Management Company", "Poperty Name"));
		textA.append("\n--------------------------------------------------------------------------------------------------------------------------------");
		while (index < db.getSize()) {
			HouseInfo house = db.getHouses().get(index);
			if (house.getAreaNumber() == number) {
				String communityArea = house.getAreaName();
				String ptype = house.getpType();
				String pname = house.getpName();
				String mcompany = house.getCompany();
				String zip = house.getZipCode();
				textA.append("\n"
						+ String.format(
								"| %-30s | %-30s | %-10s | %-30s | %-30s |",
								communityArea, ptype, zip, mcompany, pname));
				counter++;
			}
			index++;
		}
		if (counter == 0)
			textA.append("\n\t\t\t\t---- Sorry, Cannot find Information :( ----");
		textA.append("--------------------------------------------------------------------------------------------------------------------------------");
	}

	public void Units(Database db, int low, int high) {
		int index = 0,counter = 0;
		textA.append("USER INPUT = Low is : " + low + " High is : " + high
				+ "\n");
		textA.append("\n--------------------------------------------------------------------------------------------------------------------------------");
		// Community Area Name, Community Area Number, Property Name, Address
		textA.append("\n"
				+ String.format(
						"| %-10s | %-30s | %-10s | %-30s | %-10s | %-30s |",
						"Units", "Community Area Name", "CAN", "Address",
						"Zip-Code", "Poperty Name"));
		textA.append("\n--------------------------------------------------------------------------------------------------------------------------------");
		while (index < db.getSize()) {
			HouseInfo house = db.getHouses().get(index);

			if (house.getUnit() >= low && house.getUnit() <= high) {

				String communityArea = house.getAreaName();
				int communityAreaNumber = house.getAreaNumber();
				String pname = house.getpType();
				String address = house.getAddress();
				String zip = house.getZipCode();
				int units = house.getUnit();
				counter++;
				textA.append("\n"
						+ String.format(
								"| %-10s | %-30s | %-10s | %-30s | %-10s | %-30s |",
								units, communityArea, communityAreaNumber,
								address, zip, pname));

			}
			index++;
		}
		if (counter == 0)
			textA.append("\n\t\t\t\t---- Sorry, Cannot find Information :( ----");
		textA.append("\n--------------------------------------------------------------------------------------------------------------------------------");
	}// if

	public void infoFromPname(Database db, String pname) { // input:
																// Property Name
																// | output:
																// Community
																// Area Name,
																// Property
																// Type,Address,
																// Zipcode,
																// Management
																// Company,
																// Phonenumber");
		int index = 0;
		int counter = 0;
		textA.append("\nUSER INPUT = " + pname + "\n");
		textA.append("\n------------------------------------------------------------------------------------------------------------------------------------");
		textA.append("\n"
				+ String.format(
						"| %-25s | %-20s | %-30s | %-8s | %-35s | %-15s |",
						"Community Area", "Property Type", "Address",
						"ZipCode", "Management", "Phone#"));
		textA.append("\n------------------------------------------------------------------------------------------------------------------------------------");
		while(index < db.getSize()){
			HouseInfo house = db.getHouses().get(index);
			if(house.getpName().equals(pname)){
					String comname = house.getAreaName();
					String ptype = house.getpType();
					String address = house.getAddress();
					String zip = house.getZipCode();
					String company = house.getCompany();
					String phone = house.getPhoneNum();
					counter++;
					textA.append("\n"
							+ String.format(
									"| %-25s | %-20s | %-30s | %-8s | %-35s | %-15s |",
									comname, ptype, address, zip, company,
									phone));
			}
			index++;
		}
		if (counter == 0)
			textA.append("\n\t\t\t\t---- Sorry, Cannot find Information :( ----");
		textA.append("\n--------------------------------------------------------------------------------------------------------------------------------");
	}// if
}
