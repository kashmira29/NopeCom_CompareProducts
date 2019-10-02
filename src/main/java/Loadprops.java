import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Loadprops {
    static Properties props;

    static FileInputStream inputStream;

    public String getProperty(String Key){
        props = new Properties();
        try {
            inputStream = new FileInputStream("src\\main\\Resources\\TestDataConfig.Properties");
            props.load(inputStream);
            inputStream.close();}
        catch (IOException e) {
            e.printStackTrace();
        }

        return props.getProperty(Key);
    }
}

