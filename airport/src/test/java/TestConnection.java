import connexion.ConnectionPool;
import exceptions.GeneralException;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnection {
    public static final Logger LOGGER = LogManager.getLogger(GeneralException.class.getName());


    public static void main(String[] args) {
        String addres;
        try{
            Connection conn = ConnectionPool.getInstance().getConnection();
            //connection successfully
            if (conn != null){
                LOGGER.log(Level.INFO, "Connected to DATABASE");
                //ConnectionPool.getInstance().closeConnection(conn);
                String query = "SELECT * FROM `employee`";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()){
                    addres = rs.getString("firstName");
                    System.out.println(addres);
                }

            }else{
                LOGGER.log(Level.ERROR, "Unable to connect DATABASE");
            }
        }catch (SQLException e){
            LOGGER.log(Level.FATAL, "Unable to execute");
        }
    }
}
