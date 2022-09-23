package org.example;

import org.example.Driver.webDriver;
import org.example.Utils.OpenWebsite;
import org.example.Utils.StartBrush;
import org.openqa.selenium.edge.EdgeDriver;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        EdgeDriver edgeDriver = webDriver.initDriver();
        OpenWebsite.OpenWeb(edgeDriver);
        System.out.println("网课已经刷完！！！！");
    }
}
