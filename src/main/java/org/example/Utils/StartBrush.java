package org.example.Utils;

import jnr.ffi.annotations.In;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import java.util.List;

public class StartBrush {
    public static void startBrush(EdgeDriver edgeDriver) {
        edgeDriver.get(OpenWebsite.getProperties("URL") + "user/node?nodeId=" + OpenWebsite.getProperties("nodeId"));
        WebElement video = edgeDriver.findElement(By.tagName("video"));
        while ("true".equals(video.getDomProperty("paused"))) {
            System.out.println("true".equals(video.getDomProperty("paused")));
            video.click();
            if ("true".equals(video.getDomProperty("paused"))) {
                break;
            } else {
                String s = "";
                try {
                    Thread.sleep(1000);
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
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        String duration = video.getDomProperty("duration");
        System.out.println(duration);
        try {
            Thread.sleep(Integer.parseInt(video.getDomProperty("duration"))* 500L) ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean flag = true;
        while (flag) {
            if ("true".equals(video.getDomProperty("ended"))) flag = false;
        }
    }

    public static void Brush(EdgeDriver edgeDriver){
        edgeDriver.get(OpenWebsite.getProperties("URL") + "user/node?nodeId=" + OpenWebsite.getProperties("nodeId"));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
