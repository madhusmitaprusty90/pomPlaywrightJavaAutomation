package com.qa.opencart.test;

import com.microsoft.playwright.Page;
import com.qa.opencart.factory.PlaywrightFactory;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.FileNotFoundException;
import java.util.Properties;

public class BaseTest {

    PlaywrightFactory pf;
    Page page;
    protected Properties prop;
    protected HomePage homePage;
    protected LoginPage loginPage;

    @BeforeTest
    public void setup() throws FileNotFoundException {
        pf = new PlaywrightFactory();
        prop = pf.initProperties();
        page = pf.initBrowser(prop);
        homePage = new HomePage(page);
    }


    @AfterMethod
    public void captureScreenshotOnFailure(ITestResult result){
        if(!result.isSuccess()){
            pf.takeScreenshot(result.getMethod().getMethodName());
        }
    }
    @AfterTest
    public void teardown(){
        page.context().browser().close();

    }
}
