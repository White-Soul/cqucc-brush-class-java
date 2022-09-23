package org.example.Utils;

import org.example.Driver.webDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.*;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class OpenWebsite {
//    登录网站
    public static void OpenWeb(EdgeDriver edgeDriver){
        edgeDriver.get(getProperties("URL")+"user/login");
        webDriver.TimeOut(1000);
        System.out.println(edgeDriver.getTitle());
        edgeDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        WebElement username = edgeDriver.findElement(By.id("username"));
        username.sendKeys(getProperties("username"));
        WebElement password = edgeDriver.findElement(By.id("password"));
        password.sendKeys(getProperties("password"));
        String s = "";
        try {
            ImageDispose.saveImage(edgeDriver);
            webDriver.TimeOut(1000);
            s = ImageDispose.ImageCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        WebElement code = edgeDriver.findElement(By.id("code"));
        code.sendKeys(s);
        edgeDriver.findElement(By.className("btn")).click();
        webDriver.TimeOut(1000);
        SkipWeb.getWebUrl(edgeDriver);
//        刷所有的课程
        for (String s1 : SkipWeb.Wangke) {
            SkipWeb.skipWeb(edgeDriver, s1);
            StartBrush.Brush(edgeDriver);
        }
    }
//  读取文件属性
    public static String getProperties(String key) {
        Properties properties = new Properties();
        InputStream resourceAsStream;
        try {
            resourceAsStream = new FileInputStream("./webConfig.properties");
            properties.load(resourceAsStream);
            resourceAsStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties.getProperty(key);
    }
//    存入文件属性
    public static void setProperties(String key, String value){
        Properties properties = new Properties();
        InputStream resourceAsStream;
        try {
            resourceAsStream = new FileInputStream("./webConfig.properties");
            properties.load(resourceAsStream);
            properties.setProperty(key, value);
            FileOutputStream fileOutputStream = new FileOutputStream("./webConfig.properties");
            properties.store(fileOutputStream, "更新");
            resourceAsStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//  存入课程结尾Id
    public static void setEndId(EdgeDriver edgeDriver){
        WebElement wrapper = edgeDriver.findElement(By.id("wrapper"));
        List<WebElement> r = wrapper.findElement(By.className("detmain-navs")).findElements(By.className("item"));
        String end = r.get(r.size()-1).findElement(By.tagName("a")).getDomProperty("href");
        end = end.substring(end.length()-7);
        System.out.println(end);
        setProperties("endId", end);
    }
}
