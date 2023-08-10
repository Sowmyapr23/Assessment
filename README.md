#TestStore Application

**Application URL :** https://automationteststore.com/
**Application Description :** This shopping application allows the user to shop and order items as per their needs. 
It is mainly developed for educational purpose and no actual orders or payments can be made

**Git Repository to access the test automation scripts**
URL : https://github.com/Sowmyapr23/TestStoreAutomation.git

**Cloning Project Steps:**
1.Create an empty folder in your local machine
2.In command prompt, navigate to the folder
3.Give command “git init”
4.Give command “git clone https://github.com/Sowmyapr23/TestStoreAutomation.git” (The URL is taken from GIT under Code->Local->Clone->HTTPS)
5.After cloning, navigate to the folder created inside the workspace folder

**To Open Project in IntelliJ**
1.Open IntelliJ
2.Select File -> Open -> select the folder where the project is present
3.Refresh the project to download the external dependencies 

**Framework, tools and Technologies :** BDD Cucumber with Selenium Java

**Folder Structure of the Framework:**
1. Feature File - The feature file has 3 scenarios with tags @ValidateUserDetailsAfterRegistration,
@LoginAndAddProductToCart and @CheckOutAndValidateProductInPaymentsPage [ValidateLoginAndProductDetails.feature]
- Used TestData from DataTable of Feature file

2. Step Definitions - The class file has all the steps written in the feature file using Cucumber Annotations [ValidateLoginAndProductDetails]
3. Runner File - The class file using annotations such as @RunWith, @CucumberOptions helps in taking care of the execution
4. Configurations Folder - This folder consists of 2 class files such as CommonMethods (utility/re-usable methods)
and ExtendReport (Reporting)
5. Resources Folder - Here all the drivers files, locator file, execution and report configuration files are placed
6. pom.xml - This file has the required maven dependencies

**Execution steps:**
1. Browser and application site URL can be changed in src/test/resources/config.properties
2. In the runner file, 
- specify just the path of feature file without tags (Runs the feature while as whole includes as the tags) like
  feature=src/test/java/features/ValidateLoginAndProductDetails.feature 
- specify the feature file path along the required tags to run the particular scenario like
  tags="@ValidateUserDetailsAfterRegistration or @LoginAndAddProductToCart" 
- modify the email and loginName field in feature file for every execution
3. After the above steps, we can execute the runner file

**Results:**
1. Once the execution is completed, Cucumber report can be accessed from target folder [C:\Users\sowmyar\TestStoreApplication\target]
2. Extent report can be accessed from reports folder [C:\Users\sowmyar\TestStoreApplication\reports]