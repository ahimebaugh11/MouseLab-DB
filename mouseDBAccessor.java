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
        System.out.println("2. Genotype");
        System.out.println("3. Strain");
        System.out.println("4. JUST EXAMPLES HERE, CAN BE FILLED WITH WHATEVER WE NEED");
        System.out.println("=====================================================");

        filter = input.nextLine();
        if(filter.equals("1")){
            System.out.print("Please enter the name you wish to search:");
             filter = input.nextLine();
             System.out.println();

            //access the mysql server given the specified name, return the correct mouse here
             searchByAID(filter);

        }

        else if(filter.equals("2")){
            System.out.print("Please enter the genotype you wish to search:");
            filter = input.nextLine();
            System.out.println();

            //access the mysql server given the specified genotype, return any mice with this specific genotype


        }
        else if(filter.equals("3")){
            System.out.println("Please enter the strain you wish to search:");
            filter = input.nextLine();
            System.out.println();
            //access the mysql server given the specified strain, return any mice of this specific strain

        }
    }
    
    public void searchByAID(String item) throws SQLException {
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
					if (rs.getString("id_an").equalsIgnoreCase(item)) {
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
                System.out.println();
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
    public void deleteMouse(){

    }
}