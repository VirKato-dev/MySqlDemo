package mysql.demo.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static Properties prop;

    public static String getProperty(String attr) {
        if (prop == null) {
            try (InputStream is = DBConnection.class.getClassLoader().getResourceAsStream("mysql/demo/db.properties") /*new FileInputStream("path/to/db.properties")*/) {
                prop = new Properties();
                prop.load(is);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return prop.getProperty(attr, "");
    }
}
