package com.qa.opencart.factory;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.qameta.allure.Allure;
import org.apache.poi.ss.formula.functions.T;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class PlaywrightFactory {

    Properties prop;
    private static ThreadLocal<Playwright> tlplaywright = new ThreadLocal<>();
    private static ThreadLocal<Browser> tlbrowser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> tlbrowsercontext = new ThreadLocal<>();
    private static ThreadLocal<Page> tlpage = new ThreadLocal<>();

    //Getter methods for tl objects
    public static Playwright getPlaywright(){
        return tlplaywright.get();
    }

    public static Browser getBrowser(){
        return tlbrowser.get();
    }

    public static BrowserContext getBrowserContext(){
        return tlbrowsercontext.get();
    }

    public static Page getPage(){
        return tlpage.get();
    }

    public Page initBrowser(Properties prop){
        String browserName = prop.getProperty("browser").trim();
        System.out.println("Browser name is: "+browserName);
//        Playwright playwright;
//        Browser browser = null;
//        BrowserContext context;
//        Page page;

//        playwright = Playwright.create();

        tlplaywright.set(Playwright.create());
        switch(browserName.toLowerCase()){
            case "chrome":
//                browser = playwright.chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false));
                tlbrowser.set(getPlaywright().chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false)));
                break;
            case "firefox":
//                browser = playwright.firefox().launch(new LaunchOptions().setHeadless(false));
                tlbrowser.set(getPlaywright().firefox().launch(new LaunchOptions().setHeadless(false)));
                break;
            case "safari":
//                browser = playwright.webkit().launch(new LaunchOptions().setHeadless(false));
                tlbrowser.set(getPlaywright().webkit().launch(new LaunchOptions().setHeadless(false)));
                break;
            case "chromium":
//                browser = playwright.chromium().launch(new LaunchOptions().setHeadless(false));
                tlbrowser.set(tlplaywright.get().chromium().launch(new LaunchOptions().setHeadless(false)));
                break;
            case "edge":
//                browser = playwright.chromium().launch(new LaunchOptions().setHeadless(false));
                tlbrowser.set(tlplaywright.get().chromium().launch(new LaunchOptions().setChannel("msedge").setHeadless(false)));
                break;
            default:
                System.out.println("Please pass the correct browser name. " +
                        "Supported browser names are: 'chrome', 'firefox', 'safari', 'chromium'");
                break;
        }

//        context = browser.newContext();
//        page = context.newPage();
//        page.navigate(prop.getProperty("URL"));
//        return page;
        tlbrowsercontext.set(getBrowser().newContext());
        tlpage.set(getBrowserContext().newPage());
        getPage().navigate(prop.getProperty("URL").trim());
        return getPage();
    }

    public Properties initProperties() throws FileNotFoundException {
        try{
            FileInputStream fis = new FileInputStream("./src/test/resources/config/config.properties");
            prop = new Properties();
            prop.load(fis);
        } catch (FileNotFoundException fe){
            fe.printStackTrace();
            throw fe;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public void takeScreenshot(String testMethodName){
        try{
            String path = System.getProperty("user.dir")+"/Screenshots/"+testMethodName+"_"+System.currentTimeMillis()+".png";
            getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
            try(FileInputStream fis = new FileInputStream(new File(path))){
                Allure.addAttachment(testMethodName+" Screenshot", "image/png",fis , "png");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }


    }
}
