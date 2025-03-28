package com.sb.auto.OrangeHRMSpringBootAutomation.framework.util;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Lazy
@Component
public class ScreenshotUtil {

   /* @Value("${screenshot.path}/")
    private Path path;*/
   private static final String SCREENSHOT_DIR = "screenshots"; // Change to "target/screenshots" if needed

    /*@Autowired
    private WebDriver driver;*/

    private final WebDriver driver;

    // Constructor-based dependency injection
    public ScreenshotUtil(WebDriver driver) {
        this.driver = driver;
    }

    //private static final Logger LOGGER = LogManager.getLogger(ScreenshotUtil.class);

   // @Attachment(value = "Screenshot on Failure", type = "image/png")
    public void captureScreenshot(String testName) {
        if (this.driver == null) {
            Reporter.log("WebDriver is null, cannot capture screenshot.");
            return;
        }
        TakesScreenshot ts = (TakesScreenshot) driver;
        File srcFile = ts.getScreenshotAs(OutputType.FILE);

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath = SCREENSHOT_DIR + "/" + testName + "_" + timestamp + ".png";

        try {
            File screenshotDir = new File(SCREENSHOT_DIR);
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs(); // Create directory if not exists
            }
            //FileUtils.copyFile(srcFile, new File(screenshotPath));
            FileHandler.copy(srcFile, new File(screenshotPath));
            Reporter.log("Screenshot saved at: " + screenshotPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public void captureScreenshot(WebDriver driver, String testName) {
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String filePath = "screenshots/" + testName + "_" + timestamp + ".png";
            FileCopyUtils.copy(srcFile,this.path.resolve(testName).toFile());
            //FileUtils.copyFile(srcFile, new File(filePath));
            System.out.println("Screenshot saved: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public static String captureScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String dest = SCREENSHOT_DIR + "/" + testName + "_" + timestamp + ".png";

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(src, new File(dest));
            return dest;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
