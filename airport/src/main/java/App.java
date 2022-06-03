import connexion.ConnectionPool;
import exceptions.CustomSQLException;
import model.Airport;
import view.AirportView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int option;

        try{
            Connection conn = ConnectionPool.getInstance().getConnection();

            while (!salir) {

                System.out.println("1. Airport");
                System.out.println("2. NOT DEVELOPED YET");
                System.out.println("3. NOT DEVELOPED YET");
                System.out.println("4. NOT DEVELOPED YET");
                System.out.println("5. NOT DEVELOPED YET");
                System.out.println("6. Terminate Program");

                try {

                    System.out.println("Select: ");
                    option = sn.nextInt();

                    switch (option) {
                        case 1:
                            System.out.println("Airport Management: ");
                            AirportView airportView = new AirportView();
                            airportView.AirportMenu(conn);
                            break;

                        case 6:
                            salir = true;
                            break;
                        default:
                            System.out.println("Only numbers between 1 and 4");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Wrong value. Only numbers.");
                    sn.next();
                }
            }


        } catch (Exception e) {
            throw new CustomSQLException("Connection was not possible", e);
        }

    }
}
