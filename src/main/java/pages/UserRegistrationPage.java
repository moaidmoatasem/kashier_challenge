package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserRegistrationPage extends PageBase 
{

	public UserRegistrationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(name="full_name")
	WebElement full_name ;
	
	@FindBy(name="store_name")
	WebElement store_name;
	
	@FindBy(className="transform-checkbox-icon")
	WebElement transform_checkbox_icon ;
	
	@FindBy(name="mobile_number")
	WebElement mobile_number;
	
	@FindBy(name="email")
	WebElement email ;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(name="rpassword")
	WebElement rpassword ;
	
	@FindBy(id="register-submit-btn")
	public WebElement register_btn ;
	
	@FindBy(xpath="//a[@href ='/en/login']")
	public WebElement login_link;
	
	@FindBy(id="rc-imageselect")
	public WebElement captcha;

	//*[contains(text(),'Welcome to Kashier')]
	@FindBy(xpath = "//*[contains(text(),'Welcome to Kashier')]")
	public WebElement welcomePopUp;
	public void userRegistration(String full_name , String store_name , String mobile_number, String email , String password, String rpassword)
	{
		setTextElementText(this.full_name, full_name);
		setTextElementText(this.store_name, store_name);
		setTextElementText(this.mobile_number,mobile_number);
		setTextElementText(this.email, email);
		setTextElementText(this.password, password);
		setTextElementText(this.rpassword, rpassword);
	}
	public void userLogin()
	{
		clickButton(login_link);
	}
	public void clickSignUp(){
		clickButton(register_btn);
	}
	public void openRegistrationPage()
	{
		drvier.navigate().to("https://merchant.kashier.io/signup");
	}
}
