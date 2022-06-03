package connexion;

import exceptions.GeneralException;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private final static Logger LOGGER = LogManager.getLogger(GeneralException.class.getName());

    private final String URL = DBPropertiesUtil.getString(Constants.URL);
    private final String USER = DBPropertiesUtil.getString(Constants.USERNAME);
    private final String PASS = DBPropertiesUtil.getString(Constants.PASSWORD);
    private static ConnectionPool dataSource;
    private static BasicDataSource basicDataSource = null;

    private ConnectionPool(){

        basicDataSource = new BasicDataSource();

        basicDataSource.setUrl(URL);
        basicDataSource.setUsername(USER);
        basicDataSource.setPassword(PASS);

        basicDataSource.setInitialSize(2);
        //min of active connections
        basicDataSource.setMinIdle(5);
        //max of active connections
        basicDataSource.setMaxIdle(20);
        basicDataSource.setMaxTotal(50);
        basicDataSource.setMaxWaitMillis(10000);

    }

    public static ConnectionPool getInstance() {
        if (dataSource == null) {
            dataSource = new ConnectionPool();
            return dataSource;
        } else {
            return dataSource;
        }
    }

    public Connection getConnection(){
        Connection conn = null;
        try{
            conn = basicDataSource.getConnection();
        }catch (SQLException e){
            LOGGER.log(Level.ERROR, "Unable to connect DataBase", e);
            System.out.println(e.getErrorCode());
        }
        return conn;
    }

    public void closeConnection(Connection conn) throws  SQLException{
        conn.close();
    }
}
