package org.example.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.ArrayList;
import java.util.List;

public class SkipWeb {
//    用于储存所有的网课列表
    public static final List<String> Wangke = new ArrayList<>();
//  获取所有的网课
    public static void getWebUrl(EdgeDriver edgeDriver){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement element = edgeDriver.findElement(By.className("user-course"));
        List<WebElement> img = element.findElements(By.className("img"));
        for (WebElement webElement : img) {
            String domProperty = webElement.findElement(By.tagName("a")).getDomProperty("href");
            Wangke.add(domProperty);
        }
    }
    // 跳转到课程详情,获取视频参数
    public static void  skipWeb(EdgeDriver edgeDriver, String url){
        edgeDriver.get(url);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        找到需要的组件
        WebElement element = edgeDriver.findElement(By.className("ncoursecon-intro"));
        String domProperty = element.findElement(By.className("btn1")).getDomProperty("href");
        System.out.println(domProperty);
        String substring = domProperty.substring(domProperty.length() - 7);
//        将参数保存到文件中
        OpenWebsite.setProperties("nodeId", substring);
    }
}
