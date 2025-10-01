package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class HomePage {

    Page page;
    private String searchBox = "input[name='search']";
    private String searchIcon = "div#search button";
    private String searchHeader = "div#content h1";
    private String myAccount = "a[title='My Account']";
    private String loginLocator = "//ul/li/a[text()='Login']";
    private String registerLocator = "//ul/li/a[text()='Register']";
    private String logoutButton = "//ul/li/a[text()='Logout']";

    // HomePage constructor to initialize the Page object
    public HomePage(Page page){
        this.page=page;
    }

    // Page class methods/page actions
    public String getHomePageTitle(){
        String title = page.title();
        System.out.println("Title is: "+title);
        return title;
    }

    public String getHomepageUrl(){
        String url = page.url();
        System.out.println("URL is: "+url);
        return url;
    }

    public String search(String productName){
//        page.click(searchBox);
        page.fill(searchBox, productName);
        page.click(searchIcon);
        page.locator(searchHeader).waitFor();
        String header = page.textContent(searchHeader);
        return header;
    }

    public LoginPage navigateToLoginPage() {
        page.click(myAccount);
        page.click(loginLocator);
        return new LoginPage(page);
    }

    public RegisterPage navigateToRegisterPage() {
        page.click(myAccount);
        page.click(registerLocator);
        return new RegisterPage(page);
    }

    public boolean isLogoutVisible() {
        page.click(myAccount);
        return  page.isVisible(logoutButton);
    }
}
