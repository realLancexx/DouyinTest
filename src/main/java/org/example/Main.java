package org.example;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        Date date = new Date();
        System.out.println("开始时间：");
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        System.out.println(dateFormat.format(date));

        DesiredCapabilities capabilities = getCapabilities();
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), capabilities);
        try {
            //设置每天最大评论次数
            process(driver,200,false);
        }catch (Exception e){
            driver.close();
        }finally {
            System.out.println("结束时间：");
            date = new Date();
            dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            System.out.println(dateFormat.format(date));
        }


//        shutDown();
    }

    private static DesiredCapabilities getCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "garnet");  // 设备序列号（通过adb获取）
        capabilities.setCapability("platformName", "Android");  // 手机操作系统
        capabilities.setCapability("platformVersion", "14");  // 操作系统版本号
        capabilities.setCapability("appPackage", "com.ss.android.ugc.aweme.lite");  // APP包名
        capabilities.setCapability("appActivity", "com.ss.android.ugc.aweme.splash.SplashActivity");  // APP最先启动的Activity（窗体）
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("ignoreHiddenApiPolicyError", true);
        // newCommandTimeout：默认60s，60s之内没有给appium发请求，appium就会自动结束session连接，自动关闭app。
        // 使用场景比如视频处理上传超过60s，或由于网络原因，或者视频过大，或上传apk之后再运行测试等，app还没启动，任务就失败了
        capabilities.setCapability("newCommandTimeout", 6000);
        return capabilities;
    }


    static void process(AndroidDriver driver,int num,boolean local) throws InterruptedException {

        int time = 800;
        Thread.sleep(time);

        //true同城，false探索
        if(local){
            final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            var start = new Point(400, 1025);
            var end = new Point (700, 1025);
            var swipe = new Sequence(finger, 1);
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                    PointerInput.Origin.viewport(), start.getX(), start.getY()));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(800),
                    PointerInput.Origin.viewport(), end.getX(), end.getY()));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(Arrays.asList(swipe));
        }


        for (int i = 0; i < num; i++) {
            System.out.println("评论数量："+(i+1));

            //点击评论按钮
            Thread.sleep(100);
            clickCommentButt(driver);
            //点击输入框
            Thread.sleep(time);
            clickCommentRect(driver);
            //输入剪切板文字并发送
            Thread.sleep(time);
            clipboardInput(driver);
            //点击X退出评论
            Thread.sleep(time);
            clickX(driver);

            Thread.sleep(time);
            nextVideo(driver);
        }



    }

    private static void pauseVideo(AndroidDriver driver) {
        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var tapPoint = new Point(593, 1265);
        var tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(tap));
    }

    private static void clickCommentRect(AndroidDriver driver) {
        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var tapPoint = new Point(146, 2577);
        var tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(tap));
    }

    private static void clipboardInput(AndroidDriver driver) throws InterruptedException {
        int time = 700;

        //剪切板按钮
        Thread.sleep(time);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Point tapPoint = new Point(1109, 2618);
        var tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(tap));

        //常用语按钮
        Thread.sleep(time);
        finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        tapPoint = new Point(885, 1572);
        tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(tap));

        //第一段常用语
        Thread.sleep(time);
        finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        tapPoint = new Point(807, 1760);
        tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(tap));

        //发送键位置
        Thread.sleep(time);
        finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        tapPoint = new Point(1104, 1406);
        tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(tap));

    }


    private static void clickX(AndroidDriver driver) {
        //用关闭按钮来判断是否进入下一个视频
        try {
            // 可能会抛出异常的代码
            driver.findElement(By.id("com.ss.android.ugc.aweme.lite:id/back_btn")).click();
        } catch (Exception e) {
            // 处理异常的代码
            nextVideo(driver);
        }

    }

    private static void clickCommentButt(AndroidDriver driver) throws InterruptedException {
        while (true) {
            //检测到com.ss.android.ugc.aweme.lite:id/n28，就点评论按钮；否则跳过
            try {
                driver.findElement(By.id("com.ss.android.ugc.aweme.lite:id/n28"));
                break;
            } catch (Exception e) {
                nextVideo(driver);
            }
        }
        pauseVideo(driver);
        Thread.sleep(200);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var tapPoint = new Point(1130, 1640);
        var tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(tap));

    }


    static void nextVideo(AndroidDriver driver){
            final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            var start = new Point(583, 2025);
            var end = new Point (588, 495);
            var swipe = new Sequence(finger, 1);
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                    PointerInput.Origin.viewport(), start.getX(), start.getY()));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(800),
                    PointerInput.Origin.viewport(), end.getX(), end.getY()));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(Arrays.asList(swipe));
        }
    static void shutDown(){
        try {
            Process process = Runtime.getRuntime().exec("shutdown -s -t 0");

            process.waitFor();

        } catch (Exception e) {
            e.printStackTrace();

        }
        }

}

