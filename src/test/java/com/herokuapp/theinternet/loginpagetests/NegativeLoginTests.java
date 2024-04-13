package com.herokuapp.theinternet.loginpagetests;

import com.herokuapp.theinternet.base.CsvDataProviders;
import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.LoginPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Map;

public class NegativeLoginTests extends TestUtilities {


//	@Parameters({ "username", "password", "expectedMessage" })//Больше не нужен
	@Test(priority = 1, dataProvider = "csvReader", dataProviderClass = CsvDataProviders.class)
	public void negativeLoginTest(Map<String, String> testData) {
		//Data
		String num = testData.get("num");
		String username = testData.get("username");
		String password = testData.get("password");
		String expectedErrorMessage = testData.get("expectedMessage");
		String description = testData.get("description");

		log.info("Starting negativeLoginTest #" + num + " for " + description);

		// open main page
		WelcomePage welcomePage = new WelcomePage(driver, log);
		// Click on Form Authentication link and log in with wrong values
		LoginPage loginPage = welcomePage.openPage()
				.clickFormAuthenticationLink()
				.negativeLogin(username, password);

		String message = loginPage.getErrorMessageText();
		Assert.assertEquals(loginPage.getCurrentUrl(), loginPage.getPageUrl(), "Oops! I expected the other URL!");
		Assert.assertTrue(loginPage.isLoginBtnVisible(), "Oops! There is no Login button!");
		Assert.assertTrue(message.contains(expectedErrorMessage),
				"Oops! The text is incorrect." + "\nIt should be: " + expectedErrorMessage +
						"\nbut it actually is: " + message);

	}

}
