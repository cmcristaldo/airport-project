package view;

import model.Airport;
import service.implemented.AirportImpl;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AirportView {

    public void AirportMenu(Connection conn){

        Scanner sc0 = new Scanner(System.in);
        boolean salir = false;
        int option;
        String name;
        String shortName;
        AirportImpl airportImpl = new AirportImpl(conn);
        Airport a = new Airport();


        while (!salir) {

            System.out.println("1. Get Airport by id");
            System.out.println("2. List all Airports");
            System.out.println("3. Create Airport");
            System.out.println("4. NOT DEVELOPED YET");
            System.out.println("5. NOT DEVELOPED YET");
            System.out.println("6. Go Back");

            try {

                System.out.println("Select: ");
                option = sc0.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("Enter Airport's id for searching: ");
                        int id = sc0.nextInt();
                        airportImpl.getUsers(id);
                        break;
                    case 2:
                        System.out.println("List all Airports:");
                        airportImpl.getAll();
                        break;
                    case 3:
                        System.out.println("Create Airport:");
                        System.out.println("Enter name:");
                        name = sc0.next();
                        //clear buffer
                        sc0.nextLine();
                        System.out.println("Enter short name:");
                        shortName = sc0.nextLine();
                        System.out.println(name);
                        a.setName(name);
                        a.setShortName(shortName);
                        airportImpl.create(a);
                        break;
                    case 6:
                        salir = true;
                        break;
                    default:
                        System.out.println("Only numbers between 1 and 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong value. Only numbers.");
                sc0.next();
            }
        }

    }
}
