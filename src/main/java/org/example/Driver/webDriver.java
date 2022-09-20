package org.example.Driver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.App;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.Objects;


public class webDriver {
    public static EdgeDriver initDriver(){
        System.setProperty("webdriver.edge.driver", "D:\\Download\\edgedriver_win64\\msedgedriver.exe");
        return new EdgeDriver();
    }

    public static String url(){
        String str = Objects.requireNonNull(App.class.getClassLoader().getResource("")).getPath() + "tesseract/tessdata";
        return str.substring(1);
    }
}
