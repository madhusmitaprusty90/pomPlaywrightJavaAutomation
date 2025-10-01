package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class LoginPage {

    Page page;

    public LoginPage(Page page){
        this.page=page;
    }

    private String emailLocator = "input#input-email";
    private String passwordLocator = "input#input-password";
    private String loginButton = "input[value='Login']";
    private String forgotPassword = "//form//a[text()='Forgotten Password']";

    public String getLoginPageTitle(){
        return page.title();
    }

    public boolean isForgotPasswordLinkVisible(){
        return page.isVisible(forgotPassword);
    }

    public void doLogin(String email, String password){
        System.out.println("Entering email id: "+email);
        page.fill(emailLocator, email);
        System.out.println("Entering password: "+password);
        page.fill(passwordLocator, password);

        System.out.println("Clicking Login button...");
        page.click(loginButton);

        //return true if logout button is visible

    }
}
