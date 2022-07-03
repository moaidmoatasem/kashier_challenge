Feature: User Registration
   I want to check that the user can register in Kashier website.
   
	Scenario Outline: User Registration
	Given the user in the sign up page
	When I entered valid data "<fullname>","<storename>","<phone>","<email>","<password>"
	And clicks on Sign up
	Then The captcha shows up
 
 	Examples:
 	 | fullname | storename | phone | email | password |
 	 | ahmed | mohamed |12345667800| ahmed@user434.com | 12345678 |
 	 | Moataz | ahmed |12345667800|test@newuser233.com | 87654321 |
 	  