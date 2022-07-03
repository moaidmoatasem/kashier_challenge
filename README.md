# About the project

This is a simple challenge by kashier.io on Sign Up screen for the merchant web view, mainly the 

# Tech stack

The most well known framework has been user over here in order to go fast. 

The used language is **Java**, with **Selenium** tool/libraries for automation scripts over the browser, **Maven** mainly used for automation building of dependencies needed, **testNG** testing framework of Java that is perfectly working with selenium and covering all needed goals.

The architecture mainly relied on **POM** (Page Object Model) for sack of readability, and most commonly used in Test automation. 

POC of Adapting agile methodolgies like BDD & TDD, using **Cucumber** & **Jerkins** in order to cover the needed acceptance test in ease.

Mainly used javafaker to save the time of creating the test data manually and automated it based on the needed criteria.

used WebDriverManager to work independently anywhere

## Covered Scenarios 

1-Only Happy scenario of register using valid data for all field, to validate sign up successfully.
2-Entering invalid white space on all fields to validate that they are all mandatory by FE validation
3-Register with valid data execpt for empty **fullName**, and relying on the BE response validation to get shown by the FE to validate the field mandatory’s. 
4-Register with valid data execpt for empty store **name**, and relying on the BE response validation to get shown by the FEf to validate ield mandatory’s.
5-Validate that phone number can't be less that 11 using FE validation alerts 
6-Validate that the phone number can't be more that 11 using FE validation alerts 
7-Validate the invalid email format validation by sending blank text without @ or . or domain...etc , using a FE validation alerts
8-Validate the valid email format validation by sending correct email but with duplicates of "@", "domains"...etc , using a FE validation alerts

## Proposed Enhancement

-To have a FE validation on making the email as a mandatory field
-To block sending to the BE as long as not matching the FE validations (to disable the sign up button)



https://user-images.githubusercontent.com/47253204/177022639-5c5fc702-99ed-47e9-8c47-aa557200c888.mp4


## Challenges
**Capcha** will never be automated!, as long as it is a production env. so it's acceptable but is should get disabled on other testing/staging enviroment in order to run our autmated test efficiently.


