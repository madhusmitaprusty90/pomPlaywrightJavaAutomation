package com.qa.opencart.test;

import com.qa.opencart.pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterPageTest extends BaseTest {
    private static final String REGISTRATION_SUCCESS_HEADER = "Your Account Has Been Created!";
    RegisterPage registerPage;

    @Test
    public void registerUserTest(){
        registerPage = homePage.navigateToRegisterPage();
        Assert.assertTrue(registerPage.isRegisterHeaderDisplayed());
        registerPage.doRegister();
        Assert.assertEquals(registerPage.getPageHeader(), REGISTRATION_SUCCESS_HEADER);
    }

}
