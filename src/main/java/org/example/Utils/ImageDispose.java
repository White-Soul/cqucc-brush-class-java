package org.example.Utils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
// 图片验证码
public class ImageDispose {
//    识别验证码
    public static String ImageCode(){
        Process exec;
        String str = "";
        try {
            exec= Runtime.getRuntime().exec("python G:\\Download\\image.py");
            BufferedReader in = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            String line = "";
            while((line = in.readLine()) != null){
                str = line;
            }
            in.close();
            exec.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(str);
        return str;
    }
//将验证码储存到本地
    public static void saveImage(EdgeDriver edgeDriver) throws IOException {
        WebElement codeImage = edgeDriver.findElement(By.id("codeImg"));
        File screenshotAs = codeImage.getScreenshotAs(OutputType.FILE);
//// copy图片放到指定的目录下
        File file = new File("G:/Download/code.jpg");
        FileUtils.copyFile(screenshotAs, file);
    }
}
//        Properties props = new Properties();
//        props.put("python.home", "path to the Lib folder");
//        props.put("python.console.encoding", "UTF-8");
//        props.put("python.security.respectJavaAccessibility", "false");
//        props.put("python.import.site", "false");
//        Properties preprops = System.getProperties();
//        PythonInterpreter.initialize(preprops, props, new String[0]);
//
//        PythonInterpreter pythonInterpreter = new PythonInterpreter();
////        pythonInterpreter.exec("import sys");
////        pythonInterpreter.exec("sys.path.append('G:\\\\Download')");
//        pythonInterpreter.execfile("image.py");
//        PyFunction image = pythonInterpreter.get("image", PyFunction.class);
//        PyObject pyObject = image.__call__();
//        System.out.println(pyObject);