package org.example.Driver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.App;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.Objects;


public class webDriver {
    public static EdgeDriver initDriver(){
        System.setProperty("webdriver.edge.driver", "G:\\Brush\\src\\main\\resources\\msedgedriver.exe");
        return new EdgeDriver();
    }
    public static void TimeOut(Integer time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
