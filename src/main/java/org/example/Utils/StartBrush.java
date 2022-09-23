package org.example.Utils;

import jnr.ffi.annotations.In;
import org.example.Driver.webDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import java.util.List;

public class StartBrush {
    public static void startBrush(EdgeDriver edgeDriver) {
        edgeDriver.get(OpenWebsite.getProperties("URL") + "user/node?nodeId=" + OpenWebsite.getProperties("nodeId"));
        webDriver.TimeOut(1000);
        WebElement video = edgeDriver.findElement(By.tagName("video"));
        if("true".equals(video.getDomProperty("paused"))) {
            String duration = video.getDomProperty("duration");
            System.out.println(duration);
//            Double a = Double.parseDouble(duration) - 100;
//            edgeDriver.executeScript("arguments[0].currentTime = "+a.toString(), video);
            System.out.println("true".equals(video.getDomProperty("paused")));
            video.click();
            boolean elem = false;
            while ("true".equals(video.getDomProperty("paused"))) {
                try{
                    edgeDriver.findElement(By.className("layui-layer-content"));
                }catch (NoSuchElementException e){
                    elem = true;
                }
                if(!elem){
                    String s = "";
                    try {
                        webDriver.TimeOut(1000);
                        ImageDispose.saveImage(edgeDriver);
                        s = ImageDispose.ImageCode();
                        char[] data = new char[10];
                        System.out.println(String.copyValueOf(data).length());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    WebElement div = edgeDriver.findElement(By.className("layui-layer-content"));
                    List<WebElement> input = div.findElements(By.tagName("input"));
                    WebElement code = input.get(1);
                    code.sendKeys(s);
                    edgeDriver.findElement(By.className("layui-layer-btn0")).click();
                    webDriver.TimeOut(1000);
                }
                if("true".equals(video.getDomProperty("paused"))) video.click();
                elem = false;
            }
        }


//            Thread.sleep((long) (Double.parseDouble(video.getDomProperty("duration"))* 500L)) ;
        boolean flag = true;
        while (flag) {
            if ("true".equals(video.getDomProperty("ended"))) flag = false;
        }
    }

    public static void Brush(EdgeDriver edgeDriver){
        edgeDriver.get(OpenWebsite.getProperties("URL") + "user/node?nodeId=" + OpenWebsite.getProperties("nodeId"));
        webDriver.TimeOut(2000);
        OpenWebsite.setEndId(edgeDriver);
        int nodeId = Integer.parseInt(OpenWebsite.getProperties("nodeId"));
        int endId = Integer.parseInt(OpenWebsite.getProperties("endId"));
        System.out.println(endId);
        while(nodeId <= endId){
            startBrush(edgeDriver);
            nodeId++;
            OpenWebsite.setProperties("nodeId", Integer.toString(nodeId));
        }
    }

}
