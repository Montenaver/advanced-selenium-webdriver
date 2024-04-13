package com.herokuapp.theinternet.loginpagetests;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.BasePageObject;
import com.herokuapp.theinternet.pages.LoginPage;
import com.herokuapp.theinternet.pages.SecureAreaPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Test;


public class PositiveLoginTests extends TestUtilities {

	@Test
	public void logInTest() {
//		log.info("Starting login test");

		// open main page
		WelcomePage welcomePage = new WelcomePage(driver, log);
		welcomePage.openPage();
		takeScreenshot("WelcomePage opened");

		// Click on Form Authentication link
		LoginPage loginPage = welcomePage.clickFormAuthenticationLink();
		takeScreenshot("LoginPage opened");

		//TODO Add new cookie
		Cookie ck = new Cookie("username", "tomsmith",
				"the-internet.herokuapp.com", "/", null);//path = "/" значит, что cookie будут добавлены на все страницы этого домена

		loginPage.setCookie(ck);

		// execute log in
		SecureAreaPage secureAreaPage = loginPage.login("tomsmith", "SuperSecretPassword!");
		takeScreenshot("SecureAreaPage opened");

		//TODO Get cookies
		String username = secureAreaPage.getCookie("username");//должен вернуть "tomsmith"
		log.info("Username cookie: " + username);
		String session = secureAreaPage.getCookie("rack.session");
		log.info("Session cookie: " + session);

		// verifications
		// new url (к getCurrentUrl() обращаемся через secureAreaPage т.к. она наследует от basePageObject)
		Assert.assertEquals(secureAreaPage.getCurrentUrl(), secureAreaPage.getPageUrl(), "Oups! URL is incorrect!");
		// log out button is visible
		Assert.assertTrue(secureAreaPage.isLogoutBtnVisible(), "Oups! There is no Log Out Button");
		// Successful log in message
		String expectedSuccessMessage = secureAreaPage.getExpectedMessage();
		String actualSuccessMessage = secureAreaPage.getSuccessMessageText();
//		Assert.assertTrue(actualSuccessMessage.contains(expectedSuccessMessage),
//				"actualSuccessMessage does not contain expectedSuccessMessage\nexpectedSuccessMessage: "
//						+ expectedSuccessMessage + "\nactualSuccessMessage: " + actualSuccessMessage);
		Assert.assertTrue(actualSuccessMessage.contains(expectedSuccessMessage),
				"The error message is incorrect\nIt should be: "
						+ expectedSuccessMessage + "\nBut actually it is: " + actualSuccessMessage);
	}
	@Test
	public void logInTest2() {
		String username = "tomsmith";
		String password = "SuperSecretPassword!";
		LoginPage loginPage = new WelcomePage(driver, log).openPage().clickFormAuthenticationLink();
		SecureAreaPage secureAreaPage = loginPage.login(username, password);
		BasePageObject basePageObject = new BasePageObject(driver, log);

		Assert.assertEquals(secureAreaPage.getPageUrl(),basePageObject.getCurrentUrl(), "Oops! URL is incorrect!");
		Assert.assertTrue(secureAreaPage.isLogoutBtnVisible(), "Oops! There is no Log Out Button");
		Assert.assertTrue(secureAreaPage.getSuccessMessageText().contains(secureAreaPage.getExpectedMessage()),
					"Oops! The text is incorrect." + "\n It should be: " + secureAreaPage.getExpectedMessage()
							+ "\nbut it actually is: " + secureAreaPage.getSuccessMessageText());

	}
}
