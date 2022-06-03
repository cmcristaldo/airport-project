package connexion;

import exceptions.GeneralException;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class DBPropertiesUtil {

    private final static Logger LOGGER = LogManager.getLogger(GeneralException.class.getName());

    private static Properties properties = new Properties();
    private static DBPropertiesUtil dbPropertiesUtil = new DBPropertiesUtil();

    private DBPropertiesUtil(){
        try{
            properties.load(new FileReader("src/main/resources/db.properties"));
        }catch (FileNotFoundException ex){
            throw new GeneralException("File not found", ex);

        } catch (IOException e) {
            LOGGER.log(Level.FATAL, "FILE NOT FOUND", e);
        }
    }

    public static String getString(String key) {
        return properties.getProperty(key);
    }

    public static int getInt(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }

}
