package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class RegisterPage {

    Page page;
    private String registerHeader = "//div[@id='content']/h1[text()='Register Account']";
    private String firstname = "input#input-firstname";
    private String lastname = "input#input-lastname";
    private String email = "input#input-email";
    private String telephone = "input#input-telephone";
    private String password = "input#input-password";
    private String confirmPassword = "input#input-confirm";
    private String subscribeRadioOptionYes = "//label[text()='Subscribe']/following-sibling::div//input[@value='1']";
    private String subscribeRadioOptionNo = "//label[text()='Subscribe']/following-sibling::div//input[@value='0']";
    private String agreeCheckbox = "//input[@type='checkbox' && @name='agree']";
    private String continueButton = "//input[@type='checkbox' && @value='Continue']";
    private String header = "div#content h1";

    public RegisterPage(Page page){
        this.page=page;
    }

    public String getRegisterPageTitle(){
        return page.title();
    }

    public boolean isRegisterHeaderDisplayed(){
        return page.locator(registerHeader).isVisible();
    }
    public void doRegister(){
        page.fill(firstname, "Madhusmita");
        page.fill(lastname, "Prusty");
        page.fill(email, "mp.shp90@gmail.com");
        page.fill(telephone, "1234567890");
        page.fill(password, "Mp@123456789");
        page.fill(confirmPassword, "Mp@123456789");
        page.click(subscribeRadioOptionNo);
        page.click(agreeCheckbox);
        page.click(continueButton);
    }

    public String getPageHeader(){
        page.locator(header).waitFor();
        return page.textContent(header);
    }
}
