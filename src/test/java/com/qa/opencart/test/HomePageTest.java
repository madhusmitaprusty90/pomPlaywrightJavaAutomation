package com.qa.opencart.test;

//import com.aventstack.extentreports.Status;
import com.qa.opencart.Constants.OpenCartConstants;
import io.qameta.allure.Step;
import io.qameta.allure.Allure;
//import io.qameta.allure.model.Status;

import io.qameta.allure.testng.AllureTestNg;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//@Listeners({AllureTestNg.class})
public class HomePageTest extends BaseTest{

    @Test(priority = 0)
//    @Parameters({"browser"})
    @Step
    public void verifyUrlTest() {
        Allure.step("Started Verifying Home page URL ..");
        String actualUrl = homePage.getHomepageUrl();
        Assert.assertEquals(actualUrl, prop.getProperty("URL"));
    }

    @Step
    @Test(priority = 1)
    public void verifyTitleTest(){
        Allure.step("Started Verifying Home page Title ..");
        String actualTitle = homePage.getHomePageTitle();
        Assert.assertEquals(actualTitle, OpenCartConstants.HOME_PAGE_TITLE);
    }

    @DataProvider
    public Object[][] getProductName(){
        return new Object[][]{
                {"Macbook"},
                {"iMac"},
                {"Samsung"}
        };
    }

    @Step
    @Test(priority = 2, dataProvider = "getProductName")
    public void verifySearchTest(String productName){
//        Allure.step("Started Verifying Search for ", Status.PASSED);
        String actualHeader = homePage.search(productName);
        Assert.assertEquals(actualHeader, "Search - "+productName);
    }

}
