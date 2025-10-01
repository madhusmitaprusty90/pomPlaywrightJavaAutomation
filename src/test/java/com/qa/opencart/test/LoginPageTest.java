package com.qa.opencart.test;


import com.qa.opencart.Constants.OpenCartConstants;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest{

    @Step
    @Test(priority = 0)
    public void loginPageNavigationTest(){
        loginPage = homePage.navigateToLoginPage();
        String actualLoginPageTitle = loginPage.getLoginPageTitle();
        System.out.println("Actual login page title: "+actualLoginPageTitle);
        if(actualLoginPageTitle.equalsIgnoreCase(OpenCartConstants.LOGIN_PAGE_TITLE)){
            Allure.step("Title matched. Actual: "+actualLoginPageTitle+ "Expected: "+OpenCartConstants.LOGIN_PAGE_TITLE, Status.PASSED);
            Assert.assertEquals(actualLoginPageTitle, OpenCartConstants.LOGIN_PAGE_TITLE);
        } else{
            Allure.step("Title mismatched. Actual: "+actualLoginPageTitle+ "Expected: "+OpenCartConstants.LOGIN_PAGE_TITLE, Status.FAILED);
        }
    }

    @Test(priority = 1)
    public void isForgotPasswordLinkExist(){
        Assert.assertTrue(loginPage.isForgotPasswordLinkVisible());
    }

    @Test(priority = 2)
    public void doLoginTest(){
        loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
        Assert.assertTrue(homePage.isLogoutVisible());
    }
}
