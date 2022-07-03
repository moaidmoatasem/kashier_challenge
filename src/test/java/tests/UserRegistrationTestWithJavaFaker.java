package tests;



import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import pages.UserRegistrationPage;

import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.testng.AssertJUnit.assertTrue;

public class UserRegistrationTestWithJavaFaker extends TestBase
{

	Faker fakeData = new Faker();
	String full_name = fakeData.name().fullName();
	String[] prefixPhone = new String[]{"010","011","012","015"};
	String shortInvalidPhone = prefixPhone[fakeData.random().nextInt(prefixPhone.length)];
	String validPhone = shortInvalidPhone+fakeData.numerify("########");
	String longInvalidPhone = shortInvalidPhone+fakeData.numerify("###############");
	String store_name = fakeData.company().name();
	String email = fakeData.internet().emailAddress();
	String invalidEmail = fakeData.lorem().toString();
	char[] special = new char[]{'!', '@', '#', '$', '%', '^', '&', '*'};
	String password = fakeData.numerify("###")+special[fakeData.random().nextInt(special.length)]+fakeData.letterify("???",true)+fakeData.letterify("???",false);

	//'Kashier | Dashboard'


	@Test(priority=0,alwaysRun=true)
	public void allSignUpEmptyFieldsExecptEmail()
	{
		UserRegistrationPage registerObject = new UserRegistrationPage(driver) ;
		registerObject.userRegistration(" "," "," "," "," "," ");

		//pre-signUp FE checks
		WebElement nameErrorMsg = driver.findElement(By.id("full_name-error"));
		Assert.assertTrue(nameErrorMsg.isDisplayed());

		WebElement storeErrorMsg = driver.findElement(By.id("store_name-error"));
		Assert.assertTrue(storeErrorMsg.isDisplayed());

		WebElement mobileErrorMsg = driver.findElement(By.id("mobile_number-error"));
		Assert.assertTrue(mobileErrorMsg.isDisplayed());

		//No FE validations on mandatory of email field
//		WebElement emailErrorMsg = driver.findElement(By.id("email-error"));
//		Assert.assertFalse(emailErrorMsg.isDisplayed());

		List<WebElement> passwordErrorMsg = driver.findElements(By.className("passwordCheckList"));
		Assert.assertFalse(passwordErrorMsg.isEmpty());
	}
	@Test
	public void UserCanRegisterSuccssfully()
	{
		UserRegistrationPage registerObject = new UserRegistrationPage(driver) ;
		registerObject.userRegistration(full_name,store_name, validPhone,email,password,password);

//		post-signUp / BE checks
		registerObject.clickSignUp();
//						in case there is a capthca
//		WebDriverWait wait = new WebDriverWait(driver, 30);
//		wait.until(ExpectedConditions.visibilityOf(registerObject.captcha));
//		boolean isCaptchaDisplayed = ;

//		if (!registerObject.captcha.isSelected()){
			WebElement userNameDashboard = driver.findElement(By.className("m-topbar__username"));
//			wait.until(ExpectedConditions.visibilityOf(userNameDashboard));
			//Assert.assertEquals(userNameDashboard.getText(),full_name);
			boolean checkFullName = userNameDashboard.getText().contains(full_name);
			Assert.assertTrue(checkFullName);
			String pageTitle = driver.getTitle();
			Assert.assertEquals(pageTitle,"Kashier | Dashboard");

			System.out.println(checkFullName+"\t"+pageTitle);
//		}
//		else
//			 return;


	}
	@Test(priority=2,alwaysRun=true)
	public void UserRegisterWithEmptyName_afterBE_Response() {
		UserRegistrationPage registerObject = new UserRegistrationPage(driver);
		registerObject.userRegistration("", store_name, validPhone, email, password, password);
		registerObject.clickSignUp();
//		wait.until(ExpectedConditions.visibilityOf(registerObject.captcha));
//		boolean isCaptchaDisplayed = ;
//		if (!registerObject.captcha.isSelected()){
//		 System.out.println("Captcha is shown up");return;}
//		else {
			WebElement nameErrorMsg = driver.findElement(By.xpath("//h6[contains(.,'Oops! There were some missing data!')]"));
			//css=.alert
			//h6
			String actualText = nameErrorMsg.getText();
//			wait.until(ExpectedConditions.visibilityOf(nameErrorMsg));
			assertTrue(actualText.contains("Oops! There were some missing data!"));
//		}
	}
	@Test (priority=3,alwaysRun=true)
	public void UserRegisterWithEmptyStoreName_afterBE_Response() {
		UserRegistrationPage registerObject = new UserRegistrationPage(driver);
		registerObject.userRegistration(full_name, "", validPhone, email, password, password);
		registerObject.clickSignUp();
		if (registerObject.captcha.isDisplayed()) {System.out.println("Captcha is shown up");return;}
		else {
			WebElement errorMsg = driver.findElement(By.xpath("//*[@id=\"m_login\"]/div/div/div[2]/div[1]/h6"));
			String actualText = errorMsg.getText();
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(errorMsg));
			assertTrue(actualText.contains("Oops! There were some missing data!"));
		}
		return;
	}

	@Test (priority=4,alwaysRun=true)
	public void UserEntersPhoneNumberMoreThan11digit_FE_Validation(){
		UserRegistrationPage registerObject = new UserRegistrationPage(driver) ;
		registerObject.userRegistration(full_name,store_name, longInvalidPhone,email,password,password);

		WebElement errorMsg = driver.findElement(By.id("mobile_number-error"));
		String actualText = errorMsg.getText();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(errorMsg));
		assertTrue(actualText.contains("Please enter no more than 14 characters."));
	}
	@Test (priority=5,alwaysRun=true)
	public void UserEntersPhoneNumberLessThan11digit_FE_Validation(){
		UserRegistrationPage registerObject = new UserRegistrationPage(driver) ;
		registerObject.userRegistration(full_name,store_name, shortInvalidPhone,email,password,password);

		WebElement errorMsg = driver.findElement(By.id("mobile_number-error"));
		String actualText = errorMsg.getText();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(errorMsg));
		assertTrue(actualText.contains("Please enter at least 11 characters."));
	}
	@Test (priority=6,alwaysRun=true)
	public void UserEntersInvalidEmailFormat_case1_FE_Validation(){
		UserRegistrationPage registerObject = new UserRegistrationPage(driver) ;
		registerObject.userRegistration(full_name,store_name, validPhone,invalidEmail,password,password);

		WebElement errorMsg = driver.findElement(By.id("email-error"));
		String actualText = errorMsg.getText();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(errorMsg));
		assertTrue(actualText.contains("Please enter a valid Email."));
	}
	@Test (priority=7,alwaysRun=true)
	public void UserEntersInvalidEmailFormat_case2_FE_Validation(){
		UserRegistrationPage registerObject = new UserRegistrationPage(driver) ;
		String invalidEmail = email+email;
		registerObject.userRegistration(full_name,store_name, validPhone,invalidEmail,password,password);

		WebElement errorMsg = driver.findElement(By.id("email-error"));
		String actualText = errorMsg.getText();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(errorMsg));
		assertTrue(actualText.contains("Please enter a valid Email."));
	}
}
