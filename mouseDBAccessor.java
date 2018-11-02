import java.util.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class mouseDBAccessor {
	
    public void viewMouse() throws SQLException{

        Scanner input = new Scanner(System.in);
        String filter;

        System.out.println("Please select the parameter(s) you would like to search by:");
        System.out.println();
        System.out.println("=====================================================");
        System.out.println("1. Alphanumerical ID");
        System.out.println("=====================================================");

        filter = input.nextLine();
        if(filter.equals("1")){
            System.out.print("Please enter the name you wish to search:");
            filter = input.nextLine();
            System.out.println();

            //access the mysql server given the specified name, return the correct mouse here
            searchByAID(filter);
            if (mouseExists(filter)) {
            	String mouse = filter;
            	System.out.println("Do you wish to:");
            	System.out.println("1. Update this mouse");
            	System.out.println("2. Show all mouses with the same Date of Birth");
            	System.out.println("3. Show siblings");
            	System.out.println("4. Go back to menu");
            	
            	filter = input.nextLine();
            	if (filter.equals("1")) updateMouse(mouse);
            //	if (filter.equals("2")) searchByDOB(mouse);
            //	if (filter.equals("3")) searchSiblings(mouse);
            }

        }
    }
    
    public void updateMouse(String mouse) throws SQLException{
    	boolean goer = true;
        Scanner input = new Scanner(System.in);
        String[] data =  new String[10];
        String[] dataLabels =  new String[8];
        dataLabels[0] = "Alphanumerical ID: ";
        dataLabels[1] = "Sex (m/f): ";
        dataLabels[2] = "Date of Birth (mm/dd/yyyy): ";
        dataLabels[3] = "Date of Death (mm/dd/yyyy): ";
        dataLabels[4] = "Status (int 0-4): ";
        dataLabels[5] = "Mother (A/ID): ";
        dataLabels[6] = "Father (A/ID): ";
        dataLabels[7] = "Genotype: ";

        do {
            String decision;
            System.out.println("Please input the data needed to update the mouse. Simply press enter if you do not want to update that field.");
            System.out.println();
            System.out.println("=====================================================");

            for (int i = 0; i < 8; i++) {
                System.out.print(dataLabels[i]);
                data[i] = input.nextLine();
                System.out.println();
            }
            
            for (int i = 0; i < 8; i++) {
                System.out.println(dataLabels[i] + data[i]);
            }
            System.out.println("=====================================================");
            System.out.println("Is this data correct? Y/N");
            decision = input.nextLine();
            if (decision.equals("Y") || decision.equals("y")) {
                goer = false;
                //INSERT CODE FOR ADDING NEW MOUSE TO MYSQL, CAN PASS THE ARRAY "data" TO FILL FIELDS
                
                String sql = "SELECT * FROM mouselab";
    			
    			try (
    					Connection con = mouseDBconnect.getConnection();
    					Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
    					ResultSet rs = stmt.executeQuery(sql);
    					)
    				{
    					rs.beforeFirst();
    					while (rs.next()) {
    						
    						rs.getInt("id");
    						if (rs.getString("id_an").equalsIgnoreCase(mouse)) {
    							
    							if (!(data[0].equals("")))
    								rs.updateString("id_an", data[0]);
    							if (!(data[1].equals("")))
    								rs.updateString("sex", data[1]);
    							if (!(data[2].equals("")))
    								rs.updateString("dob", data[2]);
    							if (!(data[3].equals("")))
    								rs.updateString("dod", data[3]);
    							if (!(data[4].equals("")))
    								rs.updateString("status", data[4]);
    							if (!(data[5].equals("")))
    								rs.updateString("mother", data[5]);
    							if (!(data[6].equals("")))
    								rs.updateString("father", data[6]);
    							if (!(data[7].equals("")))
    								rs.updateString("genotype", data[7]);
    							
    							rs.updateRow(); //looks for 'id'
    							System.out.println("You successfully updated this mouse!");
    						
    						}
    					
    					}
    				}
                
            }
            else if (decision.equals("N") || decision.equals("n")){
                //LOOPS AGAIN
            }
        } while(goer);
    }
    
    public void searchByAID(String mouse) throws SQLException {
    	String sql = "SELECT * FROM mouselab";
		
		try (
				Connection con = mouseDBconnect.getConnection();
				Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(sql);
				)
			{
				rs.beforeFirst();
				int count = 0;
				
				while (rs.next()) {
					if (rs.getString("id_an").equalsIgnoreCase(mouse)) {
						System.out.println("ID: " + rs.getString("id_an") + 
								"\tSex: " + rs.getString("sex") +
								"\tDay of Birth: " + rs.getString("dob") +
								"\tDay of Death: " + rs.getString("dod") +
								"\tStatus: " + rs.getString("status") +
								"\tMother: " + rs.getString("mother") +
								"\tFather: " + rs.getString("father") +
								"\tGenotype: " + rs.getString("genotype"));
						count++;
					}
				}
				
				if (count == 0) System.out.println("No mouse has that Alphanumerical ID.");
			}
    }


    public void addMouse() throws SQLException {

        boolean goer = true;
        Scanner input = new Scanner(System.in);
        String[] data =  new String[10];
        String[] dataLabels =  new String[8];
        dataLabels[0] = "Alphanumerical ID: ";
        dataLabels[1] = "Sex (m/f): ";
        dataLabels[2] = "Date of Birth (mm/dd/yyyy): ";
        dataLabels[3] = "Date of Death (mm/dd/yyyy): ";
        dataLabels[4] = "Status (int 0-4): ";
        dataLabels[5] = "Mother (A/ID): ";
        dataLabels[6] = "Father (A/ID): ";
        dataLabels[7] = "Genotype: ";

        do {
            String decision;
            System.out.println("Please input the data needed for a new mouse when prompted");
            System.out.println();
            System.out.println("=====================================================");

            for (int i = 0; i < 8; i++) {
                System.out.print(dataLabels[i]);
                data[i] = input.nextLine();
            }

            //check user input
            //checks to see if mouse sex is correctly entered & will eliminate all spaces & change to lowercase
            data[1].trim().toLowerCase();
            if (!(data[1].length() == 1)){
                System.out.println("Error");
            }

            //splits birthdate into month, day & checks
            String[] birthdateSplit = data[2].split("/");
            int birthdateMonth = Integer.parseInt(birthdateSplit[0]);
            int birthdateDay = Integer.parseInt(birthdateSplit[1]);
            int birthdateYear = Integer.parseInt(birthdateSplit[2]);
            if (!(birthdateMonth <= 12 && birthdateMonth > 0)){
                System.out.println("Error");
            }
            else if (!(birthdateDay <= 31 && birthdateDay > 0)){
                System.out.println("Error");
            }
		
            //checks mouse status
            int mouseStatus = Integer.parseInt(data[4]);
            if (!(mouseStatus <= 4)){
                System.out.println("Error");
            }

            System.out.println("=====================================================");

            for (int i = 0; i < 8; i++) {
                System.out.println(dataLabels[i] + data[i]);
            }
            System.out.println("=====================================================");
            System.out.println("Is this data correct? Y/N");
            decision = input.nextLine();
            if (decision.equals("Y") || decision.equals("y")) {
                goer = false;
                //INSERT CODE FOR ADDING NEW MOUSE TO MYSQL, CAN PASS THE ARRAY "data" TO FILL FIELDS
                
                String sql = "INSERT INTO mouselab(id_an, sex, dob, dod, status, mother, father, genotype) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    			
    			try (
    					Connection conn = mouseDBconnect.getConnection();
    					PreparedStatement stmt = conn.prepareStatement(sql);
    					)
    				{
    					for (int i = 0; i < 8; i++) {
    						stmt.setString(i+1, data[i]);
    					}
    					stmt.execute();
    				}
                
            }
            else if (decision.equals("N") || decision.equals("n")){
                //LOOPS AGAIN
            }

        }while(goer);
    }
    public void deleteMouse() throws SQLException {

        Scanner input = new Scanner(System.in);
        String filter;

        do {
        	System.out.println("Please enter the alphanumerical ID of the mouse you wish to delete");
        	filter = input.nextLine();
        } while (!mouseExists(filter));
        
        //MYSQL code goes here
        
        String sql = "DELETE FROM mouselab WHERE (id_an = ?)";
		
		try (
				Connection conn = mouseDBconnect.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				)
		{
			stmt.setString(1, filter);
			stmt.execute();
			System.out.println(filter + " was successfully deleted.");
		}

    }
    
	public static boolean mouseExists(String mouse) throws SQLException {
			
			String sql = "SELECT * FROM mouselab";
				
				try (
						Connection con = mouseDBconnect.getConnection();
						Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
						ResultSet rs = stmt.executeQuery(sql);
						)
					{
						rs.beforeFirst();
						boolean exists = false;
						
						while (rs.next()) {
							
							if (rs.getString("id_an").equals(mouse)) {
								//mouse exists
								exists = true;
							}
						
						}
						
						if (exists) return true;
						else return false; //course doesn't exist
					}
		}
}
