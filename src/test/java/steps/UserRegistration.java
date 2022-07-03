package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import pages.UserRegistrationPage;
import tests.TestBase;

public class UserRegistration extends TestBase {

	UserRegistrationPage registerObject  = new UserRegistrationPage(driver);

	@Given("^the user in the sign up page$")
	public void the_user_in_the_signup_page() throws Throwable {
		registerObject.openRegistrationPage();
		Assert.assertTrue(driver.getTitle().contains("Sign Up"));
	}
	@And("^clicks on Sign up$")
	public void clicks_on_signup()  {
		registerObject.clickSignUp();
	}

	@When("^I entered valid data \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void iEnteredValidData(String fullName, String storeName, String phone ,String email, String password) {
		registerObject.userRegistration(fullName,storeName,phone,email,password,password);
	}

	@Then("The captcha shows up")
	public void theCaptchaShowsUp() {
	}



}
