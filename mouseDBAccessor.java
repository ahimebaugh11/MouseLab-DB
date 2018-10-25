import java.util.*;

public class mouseDBAccessor {

    public void viewMouse(){

        Scanner input = new Scanner(System.in);
        String filter;

        System.out.println("Please select the parameter(s) you would like to search by:");
        System.out.println();
        System.out.println("=====================================================");
        System.out.println("1. Name");
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


    public void addMouse(){

        boolean goer = true;
        Scanner input = new Scanner(System.in);
        String[] data =  new String[10];
        String[] dataLabels =  new String[10];
        dataLabels[0] = "Name: ";
        dataLabels[1] = "Date: ";
        dataLabels[2] = "EXAMPLE: ";
        dataLabels[3] = "EXAMPLE: ";
        dataLabels[4] = "EXAMPLE: ";
        dataLabels[5] = "EXAMPLE: ";
        dataLabels[6] = "EXAMPLE: ";
        dataLabels[7] = "EXAMPLE: ";
        dataLabels[8] = "EXAMPLE: ";
        dataLabels[9] = "EXAMPLE: ";

        do {
            String decision;
            System.out.println("Please input the data needed for a new mouse when prompted");
            System.out.println();
            System.out.println("=====================================================");

            for (int i = 0; i < 10; i++) {
                System.out.print(dataLabels[i]);
                data[i] = input.nextLine();
                System.out.println();
            }

            System.out.println("=====================================================");

            for (int i = 0; i < 10; i++) {
                System.out.println(dataLabels[i] + data[i]);
            }
            System.out.println("=====================================================");
            System.out.println("Is this data correct? Y/N");
            decision = input.nextLine();
            if (decision.equals("Y") || decision.equals("y")) {
                goer = false;
                //INSERT CODE FOR ADDING NEW MOUSE TO MYSQL, CAN PASS THE ARRAY "data" TO FILL FIELDS
            }
            else if (decision.equals("N") || decision.equals("n")){
                //LOOPS AGAIN
            }

        }while(goer);
    }
    public void deleteMouse(){

    }
}
