package reUsableMethods;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class ReusableMethods {

	//to log in a file
	public Logger log = Logger.getLogger("devpinoyLogger");

	//Load the property file

	public WebDriver driver;
	Properties p = new Properties();

	/**
	 * Gets the value from Login.properties when you give the key details
	 * @param pname
	 * @return
	 * @throws IOException
	 */
	public String getProperties(String pname) throws IOException{
		//Create fileinputstream object
		FileInputStream pf = new FileInputStream("C:\\Users\\soumya.billava\\eclipse-workspace\\Framework\\src\\properties\\Login.properties");
		p.load(pf);
		return p.getProperty(pname);
	}
	/**
	 * Gets the value from Verification.properties when you give the key details
	 * @param pname
	 * @return
	 * @throws IOException
	 */
	public String getVerifyProperties(String pname) throws IOException{
		//Create fileinputstream object
		FileInputStream pf2 = new FileInputStream("C:\\Users\\soumya.billava\\eclipse-workspace\\Framework\\src\\properties\\Verification.properties");
		p.load(pf2);
		return p.getProperty(pname);
	}

	/**
	 * Opens the user entered browser either chrome or firefox
	 * @param browserName
	 */
	public void browserType(String browserName){

		switch(browserName){
		case "firefox":
			System.setProperty("webdriver.firefox.marionette","C:\\Users\\soumya.billava\\Downloads\\geckodriver-v0.18.0-win64\\geckodriver.exe");
			driver=new FirefoxDriver();
			log.info("User opened the Firefox browser");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			break;
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
			log.info("User opened the Chrome browser");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			break;	
		}	
	}

	/**
	 * Opens the User entered application
	 */
	public void openApplicationURL(){
		driver.get(p.getProperty("URL"));
		log.info("User Entered url:"+p.getProperty("URL"));
	}

	public void openGmailURL(){
		driver.get(p.getProperty("GMAILURL"));
	}

	public void openNewTab(){
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
	}

	public void closeTab(){
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "w");
	}

	/**
	 * Handles the alert whenever popup appears for the alert in gmail
	 */
	public void alertHandle() {
		try
		{
			driver.switchTo().alert().accept();	
		}catch(NoAlertPresentException ex)
		{
			return;
		}

	}
	/**
	 * Takes value of Login.properties file as input and returns the text of that element
	 * @param efind
	 * @return
	 */
	public String findElementText(String efind) {
		String element[] = efind.split("@@");
		String text = null;

		if (element[0].equals("cssSelector")) {

			text = driver.findElement(By.cssSelector(element[1])).getText();

		}

		else if (element[0].equals("name")) {
			text = driver.findElement(By.name(element[1])).getText();
		}

		else if (element[0].equals("xpath")) {
			text = driver.findElement(By.xpath(element[1])).getText();
		}

		else if(element[0].equals("id")) {
			text = driver.findElement(By.id(element[1])).getText();
		}
		return text;
	}

	/**
	 * Takes value of Login.properties file as input and returns the WebElement of the same
	 * @param efind
	 * @return
	 */
	public WebElement findElement(String efind) {
		String element[] = efind.split("@@");
		WebElement localelement = null;

		if (element[0].equals("cssSelector")) {

			localelement = driver.findElement(By.cssSelector(element[1]));

		}

		else if (element[0].equals("name")) {
			localelement = driver.findElement(By.name(element[1]));
		}

		else if (element[0].equals("xpath")) {
			localelement = driver.findElement(By.xpath(element[1]));
		}

		else if(element[0].equals("id")) {
			localelement = driver.findElement(By.id(element[1]));
		}
		return localelement;
	}

	/**
	 * Takes value of Login.properties file as input and returns the list of WebElements of the same
	 * @param efind
	 * @return
	 */
	public List<WebElement> findElements(String efind) {
		String element[] = efind.split("@@");
		List<WebElement> elements = null;

		if (element[0].equals("cssSelector")) {

			elements = driver.findElements(By.cssSelector(element[1]));

		}

		else if (element[0].equals("name")) {
			elements = driver.findElements(By.name(element[1]));
		}

		else if (element[0].equals("xpath")) {
			elements = driver.findElements(By.xpath(element[1]));
		}

		else if(element[0].equals("id")) {
			elements = driver.findElements(By.id(element[1]));
		}
		return elements;
	}
	/**
	 * Performs the action on the eIdentifier and sends the data eData
	 * @param eIdentifier
	 * @param eAction
	 * @param eData
	 */
	public void actionOnElements(String eIdentifier, String eAction, String eData){
		String element[] = eIdentifier.split("@@");

		if(element[0].equals("id")){
			if(eAction.equals("click")){
				driver.findElement(By.id(element[1])).click();
			}
			else if(eAction.equals("send")){
				driver.findElement(By.id(element[1])).sendKeys(eData);
			}
		}

		if(element[0].equals("name")){
			if(eAction.equals("click")){
				driver.findElement(By.name(element[1])).click();
			}
			else if(eAction.equals("send")){
				driver.findElement(By.name(element[1])).sendKeys(eData);
			}
		}

		if(element[0].equals("xpath")){
			if(eAction.equals("click")){
				driver.findElement(By.xpath(element[1])).click();
			}
			else if(eAction.equals("send")){
				driver.findElement(By.xpath(element[1])).sendKeys(eData);
			}
		}

		if(element[0].equals("cssSelector")){
			if(eAction.equals("click")){
				driver.findElement(By.cssSelector(element[1])).click();
			}
			else if(eAction.equals("send")){
				driver.findElement(By.cssSelector(element[1])).sendKeys(eData);
			}
		}
	}
	/**
	 *  Call the methods to Open the application in the given browser
	 * @throws IOException
	 */
	public void openapplication() throws IOException
	{
		String s = getProperties("BROWSER");
		browserType(s);
		openApplicationURL();
	}

	/**
	 * Selects the guest User in the login page
	 * @throws IOException
	 */
	public void guestuser() throws IOException
	{
		actionOnElements(getProperties("GUESTUSER"),"click","");
		log.info("User selected GUEST USER");
	}

	/**
	 * Enters the username in Login page and submits
	 * @throws IOException
	 */
	public void enterusername() throws IOException
	{
		actionOnElements(getProperties("EMAIL"),"send",getProperties("GMAILUSERNAME"));
		actionOnElements(getProperties("EMAILSUBMIT"),"click","");
		log.info("User Entered the username:"+getProperties("GMAILUSERNAME"));
	}
	/**
	 * User enters no username and then submits
	 * @throws IOException
	 */
	public void enteremptyusername() throws IOException
	{
		actionOnElements(getProperties("EMAILSUBMIT"),"click","");
		log.info("User Entered no username");
	}
	/**
	 * Enters the incorrect username in Login page and submits
	 * @throws IOException
	 */
	public void enterincorrectusername() throws IOException
	{
		actionOnElements(getProperties("EMAIL"),"send",getProperties("GMAILINCORRECTUSERNAME"));
		actionOnElements(getProperties("EMAILSUBMIT"),"click","");
		log.info("Usert entered the incorrect username:"+getProperties("GMAILINCORRECTUSERNAME"));
	}
	/**
	 * Enters the username with incorrect domain and submits
	 * @throws IOException
	 */
	public void enterincorrectDomainname() throws IOException
	{
		actionOnElements(getProperties("EMAIL"),"send",getProperties("GMAILWRONGDOMAINNAME"));
		actionOnElements(getProperties("EMAILSUBMIT"),"click","");
		log.info("User entered the incorrect domainname:"+getProperties("GMAILWRONGDOMAINNAME"));
	}
	/**
	 * Handles anytype of popup in application's login page
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void popuphandle() throws IOException, InterruptedException
	{
		Thread.sleep(2000);
		actionOnElements(getProperties("TOKENPOPUP"),"click","");
		log.info("popup is handled");
	}

	/**
	 * Opens the gmail with given credentials and reads the token from the mail, enters the token in login page and click on submit button
	 * @throws IOException
	 */
	public String readgmailentertoken() throws IOException
	{
		log.info("opening gmail");
		openNewTab();
		openGmailURL();		
		actionOnElements(getProperties("GMAILUNAME"),"send",getProperties("GMAILUSERNAME"));
		actionOnElements(getProperties("GMAILNEXT"),"click","");
		actionOnElements(getProperties("PASSWORD"),"send",getProperties("GMAILPASSWORD"));	
		actionOnElements(getProperties("SIGNIN"),"click","");		
		actionOnElements(getProperties("INBOXID"),"click","");

		//
		log.info("Getting the token from mail");

		int appnamelength=getProperties("APPLICATION").length();
		List<WebElement> unreadmails=findElements(getProperties("MAILTEXTBODY"));
		int unreadmailsize = unreadmails.size();
		String tokennew = unreadmails.get(unreadmailsize-1).getText().substring(96+appnamelength,108+appnamelength);
		log.info("token value is "+tokennew);

		closeTab();
		alertHandle();

		log.info("Entering the token");
		actionOnElements(getProperties("TOKENFIELD"),"send",tokennew);
		actionOnElements(getProperties("TOKENSUBMIT"),"click","");
		log.info("Token is entered");
		return tokennew;
	}

	/**
	 * sets the cookie for the application so that user can login without a token
	 * @throws IOException
	 */
	public void setcookie() throws IOException
	{
		Cookie name = new Cookie(getProperties("NAME"),getProperties("CONTENT"));
		driver.manage().addCookie(name);
	}

	/**
	 * Enters invalid token and click on submit button
	 * @throws IOException
	 */
	public void invalidtoken() throws IOException
	{
		actionOnElements(getProperties("TOKENFIELD"),"send",getProperties("INVALIDTOKEN"));
		actionOnElements(getProperties("TOKENSUBMIT"),"click","");
		log.info("Invalid token is entered");
	}

	/**
	 * Opens the dropdown list and searches for the user entered value and selects if found
	 * @param UserDropdownValue
	 * @param DropdownListLocator
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void selectdropdown(String UserDropdownValue, String DropdownListLocator) throws IOException, InterruptedException
	{
		Thread.sleep(2000);
		actionOnElements(getProperties("OPENDROPDOWN"),"click",null);
		Thread.sleep(1000);
		List<WebElement> dropdownlist = findElements(DropdownListLocator);
		for (WebElement eachdropdownvalue : dropdownlist)
		{
			if(eachdropdownvalue.getText().equals(UserDropdownValue)) {
				eachdropdownvalue.click();
				log.info("Found the user selected Value:"+UserDropdownValue);
				break;
			}
		}
	}

	/**
	 * Opens the dropdown list and searches for the user entered product and selects if found
	 * @param UserDropdownValue
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void selectproduct() throws IOException, InterruptedException
	{
		Thread.sleep(2000);
		if(elementPresence(getProperties("OPENDROPDOWNPRODUCT"))==true) {
			actionOnElements(getProperties("OPENDROPDOWNPRODUCT"),"click",null);
			Thread.sleep(1000);
			List<WebElement> dropdownlist = findElements(getProperties("DROPDOWNLIST"));
			for (WebElement eachdropdownvalue : dropdownlist)
			{
				if(eachdropdownvalue.getText().equals(getProperties("PRODUCT"))) {
					eachdropdownvalue.click();
					log.info("Found the user entered Product:"+getProperties("PRODUCT"));
					break;
				}
			}
		}
		else
			log.info("only 1 product is present:"+findElement(getProperties("ONLYONEPRODUCTNAME")).getText());
	}

	/**
	 * Checks if the given element is present or not
	 * @param s
	 * @return
	 */
	public boolean elementPresence(String s)
	{
		try {
			findElement(s);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}

	/**
	 * Logs out of the product
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public void logout() throws InterruptedException, IOException
	{
		Thread.sleep(2000);
		actionOnElements(getProperties("LOGOUT"),"click",null);
		log.info("User Logged out of the application");
	}

	/**
	 * clicks on the Contact Us button
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void contactus() throws InterruptedException, IOException
	{
		actionOnElements(getProperties("CONTACTICON"),"click",null);
		log.info("User Selected Contact Us button");
	}

	/**
	 * Clicks on FAQ button to download the FAQ PDF
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void selectFAQs() throws InterruptedException, IOException
	{
		Thread.sleep(2000);
		actionOnElements(getProperties("RESOURCEICON"),"click",null);
		log.info("User Selected Resource Icon");
		actionOnElements(getProperties("FAQS"),"click",null);
		log.info("User Selected FAQs Icon");
	}

	/**
	 * Selecting the channel reflects only selected type of rows in the table
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public void selectchannel() throws InterruptedException, IOException
	{
		Thread.sleep(4000);
		Actions action = new Actions(driver);
		WebElement mouseoverchannel = findElement(getProperties("MOUSEOVERCHANNEL"));
		action.moveToElement(mouseoverchannel).build().perform();
		log.info("User moved the mouse over the channel");
		Thread.sleep(700);
		List<WebElement> forms = findElements(getProperties("MOUSEOVERFORM"));
		int iCount = forms.size();            	
		System.out.println(iCount);
		for(int i=0; i<iCount; i++)
		{
			WebElement mouseoverelement =driver.findElement(By.id("ctl00_bodyContent_rptBenTypeFilter_ctl0"+i+"_lnkBenTypeFilter"));
			String s1 =  mouseoverelement.getText();
			if(s1.equals(getProperties("USERSELECTSCHANNEL")))        	  
			{
				mouseoverelement.click();
				log.info("User selects:"+getProperties("USERSELECTSCHANNEL"));
				break;
			}
		}
	}
	/**
	 * Selects all the plans or 5 plans if they are more than 5
	 * @param driver
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public void selectplans_otsuka() throws InterruptedException, IOException
	{

		Thread.sleep(4000);
		List <WebElement> planslist = findElements(getProperties("PLANSTABLE"));
		int locatorElementSize = planslist.size();

		if(locatorElementSize>5){
			for(int i=1;i<=5;i++)
			{
				new Actions(driver).moveToElement(driver.findElement(By.xpath(".//*[@id='ctl00_bodyContent_grdCampaign_ob_grdCampaignBodyContainer']/div/table/tbody/tr["+i+"]/td[1]/div/div"))).click().perform();
				log.info("User selects 5 plans");
			}
		}

		else 
		{
			for(int i=1;i<=locatorElementSize;i++)
			{
				new Actions(driver).moveToElement(driver.findElement(By.xpath(".//*[@id='ctl00_bodyContent_grdCampaign_ob_grdCampaignBodyContainer']/div/table/tbody/tr["+i+"]/td[1]/div/div"))).click().perform();
				log.info("User selects "+locatorElementSize+" plans");
			}

		}
	}


	/**
	 * Verify the  alert when more than MAXIMUMPLANS plans are selected
	 * @param driver
	 * @return
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public String moreplans_otsuka() throws InterruptedException, IOException {
		Thread.sleep(4000);
		List <WebElement> planslist = findElements(getProperties("PLANSTABLE"));
		int locatorElementSize = planslist.size();
		int maximum = Integer.parseInt(getProperties("MAXIMUMPLANS"));
		if(locatorElementSize>maximum){
			for(int i=1;i<=maximum+1;i++) {
				new Actions(driver).moveToElement(driver.findElement(By.xpath(".//*[@id='ctl00_bodyContent_grdCampaign_ob_grdCampaignBodyContainer']/div/table/tbody/tr["+i+"]/td[1]/div/div"))).click().perform();
				log.info("Selected "+driver.findElement(By.xpath(".//*[@id='ctl00_bodyContent_grdCampaign_ob_grdCampaignBodyContainer']/div/table/tbody/tr["+i+"]/td[1]/div/div")).getText());
			}
			Thread.sleep(4000);
			WebElement alertelement =findElement(getProperties("ALERTTEXT"));
			String alert = alertelement.getText();
			return alert;

		}
		String error = "There are lesser than 6 elements";
		return error;
	}
	/**
	 * Verify the  alert when less than MINIMUMPLANS plans are selected
	 * @return
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public String lessplans_otsuka() throws InterruptedException, IOException
	{
		Thread.sleep(4000);
		int minimum = Integer.parseInt(getProperties("MINIMUMPLANS"));
		for(int i=1;i<=minimum-1;i++) {
			new Actions(driver).moveToElement(driver.findElement(By.xpath(".//*[@id='ctl00_bodyContent_grdCampaign_ob_grdCampaignBodyContainer']/div/table/tbody/tr["+i+"]/td[1]/div/div"))).click().perform();
			log.info("Selected "+driver.findElement(By.xpath(".//*[@id='ctl00_bodyContent_grdCampaign_ob_grdCampaignBodyContainer']/div/table/tbody/tr["+i+"]/td[1]/div/div")).getText());
		}
		flashboardbutton();
		Thread.sleep(4000);
		WebElement alertelement =findElement(getProperties("ALERTTEXT"));
		String alert = alertelement.getText();
		return alert;
	}

	/**
	 * Select recent win to highlight in the sell sheet
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void recentwin() throws InterruptedException, IOException
	{
		Thread.sleep(4000);
		List <WebElement> messagelist = findElements(getProperties("RECENTWINTABLE"));
		int locatorElementSize = messagelist.size();
		for(int i=1;i<=locatorElementSize;i++) {
			WebElement plans = driver.findElement(By.xpath("html/body/form/div[4]/div[2]/div[1]/div[1]/div/div/div/div[3]/div[3]/div/div[3]/div/table/tbody/tr["+i+"]"));
			if(!(driver.findElement(By.xpath("html/body/form/div[4]/div[2]/div[1]/div[1]/div/div/div/div[3]/div[3]/div/div[3]/div/table/tbody/tr["+i+"]/td[1]")).getAttribute("class").contains("rowdisabledImproved")))
			{
				new Actions(driver).moveToElement(plans).click().perform();
				log.info("User selects  recent win to highlight in the cell sheet");
				break;
			}
			else
			{
				if(locatorElementSize==1)
					log.info("Please select different Area and HCP as this is no visble plans");
				else
					log.info("This has Print Restriction");
			}
		}
	}

	/**
	 * Selects number of plans given by user
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void selectplans_purdue() throws InterruptedException, IOException
	{
		Thread.sleep(4000);
		List <WebElement> planslist = findElements(getProperties("PLANSTABLE"));
		int locatorElementSize = planslist.size();
		log.info("total number of plans present:"+locatorElementSize);
		int userenteredplans = Integer.parseInt(getProperties("NUMBEROFPLANS"));
		log.info("user wants to select plans:"+userenteredplans);
		int numberofplans;
		int i=1,j=1;
		int planscount =planscounttoselect();
		if(locatorElementSize>=userenteredplans){
			numberofplans=userenteredplans;
			log.info("User wants to select:"+numberofplans);
		}else
		{
			log.info("There are not "+userenteredplans +" plans. Hence selecting "+planscount+" plans");
			numberofplans=planscount;
		}

		while((j<=numberofplans)&&(j<=planscount))
		{
			WebElement plans = driver.findElement(By.xpath("html/body/form/div[4]/div[2]/div[1]/div[1]/div/div/div/div[3]/div[2]/div/div[3]/div/table/tbody/tr["+i+"]"));
			if(!(plans.getAttribute("title").contains("has a print restriction and cannot be printed")))
			{
				new Actions(driver).moveToElement(plans).click().perform();
				j++;
				log.info("User selects row:"+i);
			}
			else
				log.info("User skips row:"+i);
			i++;
		}
	}

	/**
	 * Selects zero plans and clicks on the button that navigates to print page
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void selectlessplans_purdue() throws InterruptedException, IOException
	{
		log.info("user selected no plans");
		flashboardbutton();
	}

	/**
	 * Selects 10 plans or lesser plans depending on the number of characters in the column and then click on the next plan
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public int selectmoreplans_purdue() throws InterruptedException, IOException
	{

		Thread.sleep(4000);
		List <WebElement> planslist = findElements(getProperties("PLANSTABLE"));
		int locatorElementSize = planslist.size();
		int planscount = planscounttoselect();
		if(locatorElementSize>planscount){
			int i=1,j=1;
			while(j<=planscount+1)
			{
				WebElement plans = driver.findElement(By.xpath("html/body/form/div[4]/div[2]/div[1]/div[1]/div/div/div/div[3]/div[2]/div/div[3]/div/table/tbody/tr["+i+"]"));
				if(!(plans.getAttribute("title").contains("has a print restriction and cannot be printed")))
				{
					new Actions(driver).moveToElement(plans).click().perform();
					j++;
					log.info("User selects plans"+j);
				}		
				i++;
			}
			return planscount;
		}
		else
			log.info("There are lesser plans");
		return 0;
	}

	/**
	 * Counts the maximum number of plans that can be selected depending on the character limit in the rows
	 * @return
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public int planscounttoselect() throws NumberFormatException, IOException
	{
		int k=1, j=0, i=1;
		int plannamelength=0, formalarycoveragelength=0;
		boolean copay = false;

		List <WebElement> planslist = findElements(getProperties("PLANSTABLE"));
		int locatorElementSize = planslist.size();

		for(int l=1;(!(driver.findElement(By.xpath("html/body/form/div[4]/div[2]/div[1]/div[1]/div/div/div/div[3]/div[2]/div/div[2]/div/table/tbody/tr/td["+l+"]")).getText().isEmpty()));l++)
		{
			if(driver.findElement(By.xpath("html/body/form/div[4]/div[2]/div[1]/div[1]/div/div/div/div[3]/div[2]/div/div[2]/div/table/tbody/tr/td["+l+"]")).getText().equals("Co-pay Range"))
			{
				copay = true;
				break;
			}
		}
		String productSelected;
		if(elementPresence(getProperties("OPENDROPDOWNPRODUCT"))==true) 
			productSelected = getProperties("PRODUCTSELECTED");

		else 
			productSelected = getProperties("SINGLEPRODUCTSELECTED");

		if(copay==true)
		{
			if(findElementText(productSelected).equals("Hysingla® ER (hydrocodone bitartrate)"))
			{
				plannamelength = Integer.parseInt(getProperties("COPAY_PLANNAMECHARACTERLIMIT_HYSINGLA"));
				formalarycoveragelength = Integer.parseInt(getProperties("COPAY_FORMULARYCOVERAGECHARACTERLIMIT_HYSINGLA"));
			}			
			else if(findElementText(productSelected).equals("Butrans® (buprenorphine)"))
			{
				plannamelength = Integer.parseInt(getProperties("COPAY_PLANNAMECHARACTERLIMIT_BUTRANS"));
				formalarycoveragelength = Integer.parseInt(getProperties("COPAY_FORMULARYCOVERAGECHARACTERLIMIT_BUTRANS"));
			}
			else if(findElementText(productSelected).equals("OxyContin® (oxycodone HCl)"))
			{
				plannamelength = Integer.parseInt(getProperties("COPAY_PLANNAMECHARACTERLIMIT_OXYCOTIN"));
				formalarycoveragelength = Integer.parseInt(getProperties("COPAY_FORMULARYCOVERAGECHARACTERLIMIT_OXYCOTIN"));
			}
			else if(findElementText(productSelected).equals("Symproic® (naldemedine)"))
			{
				plannamelength = Integer.parseInt(getProperties("COPAY_PLANNAMECHARACTERLIMIT_SYMPROIC"));
				formalarycoveragelength = Integer.parseInt(getProperties("COPAY_FORMULARYCOVERAGECHARACTERLIMIT_SYMPROIC"));
			}

		}else
		{
			if(findElementText(productSelected).equals("Hysingla® ER (hydrocodone bitartrate)"))
			{
				plannamelength = Integer.parseInt(getProperties("NOCOPAY_PLANNAMECHARACTERLIMIT_HYSINGLA"));
				formalarycoveragelength = Integer.parseInt(getProperties("NOCOPAY_FORMULARYCOVERAGECHARACTERLIMIT_HYSINGLA"));
			}			
			else if(findElementText(productSelected).equals("Butrans® (buprenorphine)"))
			{
				plannamelength = Integer.parseInt(getProperties("NOCOPAY_PLANNAMECHARACTERLIMIT_BUTRANS"));
				formalarycoveragelength = Integer.parseInt(getProperties("NOCOPAY_FORMULARYCOVERAGECHARACTERLIMIT_BUTRANS"));
			}
			else if(findElementText(productSelected).equals("OxyContin® (oxycodone HCl)"))
			{
				plannamelength = Integer.parseInt(getProperties("NOCOPAY_PLANNAMECHARACTERLIMIT_OXYCOTIN"));
				formalarycoveragelength = Integer.parseInt(getProperties("NOCOPAY_FORMULARYCOVERAGECHARACTERLIMIT_OXYCOTIN"));
			}
			else if(findElementText(productSelected).equals("Symproic® (naldemedine)"))
			{
				plannamelength = Integer.parseInt(getProperties("NOCOPAY_PLANNAMECHARACTERLIMIT_SYMPROIC"));
				formalarycoveragelength = Integer.parseInt(getProperties("NOCOPAY_FORMULARYCOVERAGECHARACTERLIMIT_SYMPROIC"));
			}
		}


		while(k<=10&&k<=locatorElementSize)
		{
			WebElement plans = driver.findElement(By.xpath("html/body/form/div[4]/div[2]/div[1]/div[1]/div/div/div/div[3]/div[2]/div/div[3]/div/table/tbody/tr["+i+"]"));
			if(!(plans.getAttribute("title").contains("has a print restriction and cannot be printed")))
			{
				if((driver.findElement(By.xpath("html/body/form/div[4]/div[2]/div[1]/div[1]/div/div/div/div[3]/div[2]/div/div[3]/div/table/tbody/tr["+i+"]/td[2]")).getText().length()>plannamelength)||(driver.findElement(By.xpath("html/body/form/div[4]/div[2]/div[1]/div[1]/div/div/div/div[3]/div[2]/div/div[3]/div/table/tbody/tr["+i+"]/td[3]")).getText().length()>formalarycoveragelength))
				{			
					if(k<=8)
						j++;
					k=k+2;		
				}
				else
				{
					k++;
					j++;
				}
			}
			i++;
		}
		return j;
	}
	/**
	 * Sets the given column in ascending order 
	 * @return
	 * @throws IOException
	 */
	public int planascendingorder() throws IOException
	{
		for(int i=1;(!(driver.findElement(By.xpath("html/body/form/div[4]/div[2]/div[1]/div[1]/div/div/div/div[3]/div[2]/div/div[2]/div/table/tbody/tr/td["+i+"]")).getText().isEmpty()));i++)
		{
			if(driver.findElement(By.xpath("html/body/form/div[4]/div[2]/div[1]/div[1]/div/div/div/div[3]/div[2]/div/div[2]/div/table/tbody/tr/td["+i+"]")).getText().equals(getProperties("CHANGECOLUMNORDER")))
			{
				log.info("Found the user entered column "+getProperties("CHANGECOLUMNORDER")+ " at column number" +i);
				driver.findElement(By.xpath("html/body/form/div[4]/div[2]/div[1]/div[1]/div/div/div/div[3]/div[2]/div/div[2]/div/table/tbody/tr/td["+i+"]")).click();	
				return i;
			}
		}return 0;
	}
	/**
	 * verifies if the given column in ascending order
	 * @param columnnumber
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws TestcaseFailException
	 */
	public void plansascendingorderverification(int columnnumber) throws InterruptedException, IOException, TestcaseFailException
	{
		Thread.sleep(4000);
		List <WebElement> planslist = findElements(getProperties("PLANSTABLE"));
		int locatorElementSize = planslist.size();

		for(int i=1;i<locatorElementSize;i++)
		{
			if(driver.findElement(By.xpath("html/body/form/div[4]/div[2]/div[1]/div[1]/div/div/div/div[3]/div[2]/div/div[3]/div/table/tbody/tr["+(i+1)+"]/td["+columnnumber+"]")).getText().compareToIgnoreCase(driver.findElement(By.xpath("html/body/form/div[4]/div[2]/div[1]/div[1]/div/div/div/div[3]/div[2]/div/div[3]/div/table/tbody/tr["+i+"]/td["+columnnumber+"]")).getText())<0)
			{
				log.info(driver.findElement(By.xpath("html/body/form/div[4]/div[2]/div[1]/div[1]/div/div/div/div[3]/div[2]/div/div[3]/div/table/tbody/tr["+i+"]/td["+columnnumber+"]")).getText());
				log.info("and");
				log.info(driver.findElement(By.xpath("html/body/form/div[4]/div[2]/div[1]/div[1]/div/div/div/div[3]/div[2]/div/div[3]/div/table/tbody/tr["+(i+1)+"]/td["+columnnumber+"]")).getText());

				log.info("are not in ascending order");
				throw new TestcaseFailException("Not in ascending order");
			}
		}
	}
	/**
	 * sets the given column in descending order
	 * @param columnnumber
	 * @throws IOException
	 */
	public void plandescendingorder(int columnnumber) throws IOException
	{
		driver.findElement(By.xpath("html/body/form/div[4]/div[2]/div[1]/div[1]/div/div/div/div[3]/div[2]/div/div[2]/div/table/tbody/tr/td["+columnnumber+"]")).click();	
	}
	
	/**
	 * verifies if the given column in descending order
	 * @param columnnumber
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws TestcaseFailException
	 */
	public void plansdescendingorderverification(int columnnumber) throws InterruptedException, IOException, TestcaseFailException
	{
		Thread.sleep(4000);
		List <WebElement> planslist = findElements(getProperties("PLANSTABLE"));
		int locatorElementSize = planslist.size();

		for(int i=1;i<locatorElementSize;i++)
		{
			if(driver.findElement(By.xpath("html/body/form/div[4]/div[2]/div[1]/div[1]/div/div/div/div[3]/div[2]/div/div[3]/div/table/tbody/tr["+(i+1)+"]/td["+columnnumber+"]")).getText().compareToIgnoreCase(driver.findElement(By.xpath("html/body/form/div[4]/div[2]/div[1]/div[1]/div/div/div/div[3]/div[2]/div/div[3]/div/table/tbody/tr["+i+"]/td["+columnnumber+"]")).getText())>0)
			{
				log.info(driver.findElement(By.xpath("html/body/form/div[4]/div[2]/div[1]/div[1]/div/div/div/div[3]/div[2]/div/div[3]/div/table/tbody/tr["+i+"]/td["+columnnumber+"]")).getText());
				log.info("and");
				log.info(driver.findElement(By.xpath("html/body/form/div[4]/div[2]/div[1]/div[1]/div/div/div/div[3]/div[2]/div/div[3]/div/table/tbody/tr["+(i+1)+"]/td["+columnnumber+"]")).getText());

				log.info("are not in descending order");
				throw new TestcaseFailException("Not in ascending order");
			}
		}
	}
	/**
	 * Clicks on the flashboard/Create Sell sheet button to navigate to PRINT page
	 * @param driver
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void flashboardbutton() throws InterruptedException, IOException
	{
		Thread.sleep(2000);
		actionOnElements(getProperties("FLASHBOARD"),"click",null);
		log.info("Navigating to Print On demand page");
	}
	/**
	 * Selects the given message type in the vertical tab. If message type is Message Vault then selects Internal or External Message Type
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void messageselection() throws IOException, InterruptedException
	{
		List <WebElement> Scrolldownelement = findElements(getProperties("MESSAGELIST"));
		int elementcount = Scrolldownelement.size();
		for(int i=0;i<elementcount;i++)
		{
			String elementtext = Scrolldownelement.get(i).getText();
			if(elementtext.equals(getProperties("USERMESSAGE")))
			{
				Thread.sleep(2000);
				driver.findElement(By.cssSelector("#ctl00_bodyContent_rptCampaigns_ctl0"+i+"_lnkCampaignType")).click();
				log.info("User selected the Message:"+getProperties("USERMESSAGE"));
				break;
			}
		}
		if((getProperties("USERMESSAGE")).equals("Message Vault"))
		{
			if((getProperties("MESSAGEVAULTTYPE")).equals("Internal Resources"))
			{
				Thread.sleep(1000);
				findElement(getProperties("MESSAGELISTINTERNAL")).click();
				log.info("User selected Internal Resources");
			}
			else if((getProperties("MESSAGEVAULTTYPE")).equals("External Resources"))
			{
				Thread.sleep(1000);
				findElement(getProperties("MESSAGELISTEXTERNAL")).click();
				log.info("User selected External Resources");
			}
		}else if((getProperties("USERMESSAGE")).equals("Formulary Announcement"))
			recentwin();
	}
	/**
	 * Selects the pdf if present
	 * @param driver
	 * @throws InterruptedException 
	 * @throws IOException 
	 */

	public void pdfselection() throws InterruptedException, IOException
	{
		Thread.sleep(2000);
		try {
			driver.findElement(By.cssSelector(".pdf")).click();
			log.info("pdf present");
		} catch (ElementNotVisibleException e) {
			log.info("pdf not present");
		}
	}

	/**
	 * Allows you to go back to territory page by clicking territory on top horizontal bar
	 * @param driver
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public void territorytop() throws InterruptedException, IOException
	{
		Thread.sleep(2000);
		findElement(getProperties("TOPBUTTONTERRITORY")).click();
		log.info("User navigated to territory  page");
		Thread.sleep(2000);
	}
	/**
	 * Allows you to go back to provider page by clicking provider on top horizontal bar
	 * @param driver
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public void providertop() throws InterruptedException, IOException
	{
		Thread.sleep(2000);
		findElement(getProperties("TOPBUTTONPROVIDER")).click();
		log.info("User navigated to Provider  page");
	}

	/**
	 * Allows you to go back to message page by clicking message on top horizontal bar
	 * @param driver
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public void messagetop() throws InterruptedException, IOException
	{
		Thread.sleep(2000);
		findElement(getProperties("TOPBUTTONMESSAGE")).click();
		log.info("User navigated to Message  page");
	}
	/**
	 * Downloads the PDF
	 * @param driver
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public void downloadbutton() throws InterruptedException, IOException
	{
		Thread.sleep(4000);
		actionOnElements(getProperties("DOWNLOADBUTTON"),"click","");
		log.info("Selected download option");
	}

	/**
	 * Press the enter key for system generated message
	 * @throws AWTException
	 */
	public void robotenter() throws AWTException
	{
		try {
			Robot robot = new Robot();
			robot.delay(2000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.delay(200);
		}catch(Exception ex) {
			return;
		}
	}
	/**
	 * Prints the message in PDF
	 * @param driver
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public void printbutton() throws InterruptedException, IOException
	{
		Thread.sleep(4000);
		actionOnElements(getProperties("PRINTBUTTON"),"click","");
		log.info("Selected print option");
	}

	/**
	 * Selects the registered user in login page
	 * @throws IOException
	 */
	public void registereduser() throws IOException
	{
		actionOnElements(getProperties("REGISTEREDUSER"),"click","");
		log.info("User selected REGISTERED USER");
	}

	/**
	 * Verifies if the download is successful, returns boolean value 1 if download is successful else zero
	 * @return
	 * @throws IOException
	 * @throws TestcaseFailException 
	 * @throws InterruptedException 
	 */
	public void downloadverification() throws IOException, TestcaseFailException, InterruptedException
	{
		Thread.sleep(3000);
		File file =getLatestFilefromDir();
		if(file.getName().contains(".pdf"))
			log.info("pdf download is successful");
		else
		{
			log.info("pdf file is not found");
			throw new TestcaseFailException("FAIL:pdf file is not present");
		}
	}
	/**
	 * Gets the latest file from the given directory
	 * @return
	 * @throws IOException
	 */
	public File getLatestFilefromDir() throws IOException
	{
		File dir = new File(getProperties("DOWNLOADPATH"));
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}
		File lastModifiedFile = files[0];

		for (int i = 0; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		return lastModifiedFile;
	}

	/**
	 * Prints the latest file name in log file for the user to verify
	 * @throws IOException
	 */
	public void filenameverification() throws IOException
	{
		File file =getLatestFilefromDir();
		String filename=file.getName();
		log.info("SUCCESS:Downloaded filename is:"+filename+". Verify if it's in the format of Brand_MessageType_DrLastName_Dr FirstName_YYYYMMDD");
	}
	/**
	 * Receives two strings and compares them and then closes the page if they don't match and throws exception
	 * @param current
	 * @param expected
	 * @throws InterruptedException
	 * @throws TestcaseFailException
	 * @throws IOException
	 */
	public void verification(String current,String expected) throws InterruptedException, TestcaseFailException, IOException
	{
		Thread.sleep(2000);
		if(!current.equals(expected)) {
			log.error("FAIL:Verification failed."+current +" is not matching with "+expected);
			driver.close();
			throw new TestcaseFailException(current+" Text is not matching with "+expected);	
		}
	}
	/**
	 * Compares two strings and throws excpetion if they are not same
	 * @param current
	 * @param expected
	 * @throws InterruptedException
	 * @throws TestcaseFailException
	 * @throws IOException
	 */
	public void verificationcontinue(String current,String expected) throws InterruptedException, TestcaseFailException, IOException
	{
		Thread.sleep(2000);
		if(!current.equals(expected)) {
			log.error("FAIL:Verification failed."+current +" is not matching with "+expected);
			throw new TestcaseFailException(current+" Text is not matching with "+expected);	
		}
	}

	/**
	 * Returns the releasedate of the product
	 * @return
	 * @throws IOException
	 */
	public String releaseDate() throws IOException
	{
		return findElement(getProperties("RELEASEDATE")).getText();
	}
	/**
	 * Returns the latestdate of the product
	 * @return
	 * @throws IOException
	 */
	public String latestData() throws IOException
	{
		return findElement(getProperties("LATESTDATA")).getText();
	}
	
	/**
	 * Checks if the column headers are in the proper order from left to right
	 * @throws TestcaseFailException
	 * @throws InterruptedException
	 */
	public void columnHeaderLeftToRight() throws TestcaseFailException, InterruptedException
	{
		Thread.sleep(2000);
		int planname=0, formularycoverage=0;
		for(int i=1;!(driver.findElement(By.xpath("html/body/form/div[4]/div[2]/div[1]/div[1]/div/div/div/div[3]/div[2]/div/div[2]/div/table/tbody/tr/td["+i+"]")).getText().isEmpty());i++)
		{
			if(driver.findElement(By.xpath("html/body/form/div[4]/div[2]/div[1]/div[1]/div/div/div/div[3]/div[2]/div/div[2]/div/table/tbody/tr/td["+i+"]")).getText().equals("Plan Name"))
			{
				planname=i;
				log.info("Plan Name is in the column "+planname );
			}
			else if(driver.findElement(By.xpath("html/body/form/div[4]/div[2]/div[1]/div[1]/div/div/div/div[3]/div[2]/div/div[2]/div/table/tbody/tr/td["+i+"]")).getText().equals("Formulary Covergae"))
			{
				formularycoverage=i;
				log.info("Formulary Coverage is in the column "+ formularycoverage);
			}

		}
		if((planname>formularycoverage)&&(planname!=0)&&(formularycoverage!=0))
			throw new TestcaseFailException("Plan Name is in the column "+planname+ "And Formulary Coverage is in the column "+ formularycoverage);	
		else
			log.info("SUCCESS: Column headers are in the order left to Right");
	}

	/**
	 * Verifies the global footer
	 * @throws InterruptedException
	 * @throws TestcaseFailException
	 * @throws IOException
	 */
	public void globalFooterVerify() throws InterruptedException, TestcaseFailException, IOException

	{
		log.info("Text at left corner of footer:"+findElement(getProperties("FOOTER1")).getText());
		log.info("Text at middle of footer:"+findElement(getProperties("FOOTER2")).getText());
		log.info("Text at right corner of footer:"+findElement(getProperties("FOOTER3")).getAttribute("src"));
		verification(findElement(getProperties("FOOTER1")).getText(),getVerifyProperties("FOOTER1EXPECTED"));
		verification(findElement(getProperties("FOOTER2")).getText(),getVerifyProperties("FOOTER2EXPECTED"));
		verification(findElement(getProperties("FOOTER3")).getAttribute("src"),getVerifyProperties("FOOTER3EXPECTED"));

	}

	/**
	 * Allows the user to use the mouseover on Channel and select one of them
	 * @param driver
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void channelselection() throws InterruptedException, IOException
	{
		Thread.sleep(4000);

		Actions action = new Actions(driver);
		WebElement we = findElement(getProperties("COLUMNHEADERCHANNEL"));
		action.moveToElement(we).build().perform();
		Thread.sleep(700);

		List<WebElement> forms = findElements(getProperties("DYNAMICMENU"));
		int iCount = forms.size();            	
		System.out.println(iCount);


		for(int i=0; i<iCount; i++ )
		{
			WebElement mouseoverelement =driver.findElement(By.id("ctl00_bodyContent_rptBenTypeFilter_ctl0"+i+"_lnkBenTypeFilter"));
			String s1 =  mouseoverelement.getText();
			if(s1.equals(getProperties("USERINPUTCHANNELSELECTION")))        	  
			{
				mouseoverelement.click();
				break;
			}
		}
	}
	/**
	 * Verifies if the table contains only selected channel
	 */
	public void channelselectionverification() throws InterruptedException, IOException, TestcaseFailException
	{
		Thread.sleep(4000);
		List <WebElement> Scrolldownelement = findElements(getProperties("TABLEROWCHANNEL"));
		int locatorElementSize = Scrolldownelement.size();
		boolean value = false;
		if(getProperties("USERINPUTCHANNELSELECTION").equals("All"))
		{
			value=true;
		}
		else
			for(int i=1;i<=locatorElementSize;i++)
			{
				String text = driver.findElement(By.xpath(".//*[@id='ctl00_bodyContent_grdCampaign_ob_grdCampaignBodyContainer']/div/table/tbody/tr["+i+"]/td[1]/div/div")).getText();
				if(text.equals(getProperties("USERINPUTCHANNELSELECTION")))
					value=true;
				else
				{
					value=false;
					break;
				}
			}
		if (value == true)
			log.info("SUCCESS:User selected the Channel:"+getProperties("USERINPUTCHANNELSELECTION")+" successfully");
		else
			throw new TestcaseFailException(getProperties("USERINPUTCHANNELSELECTION")+ "is not selected"); 
	}
	
	/**
	 * Receives a value and sends that to the dropdownbox
	 * @param UserDropdownValue
	 * @param DropdownListLocator
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void selectdropdownwrongvalue(String UserDropdownValue, String DropdownListLocator) throws IOException, InterruptedException
	{
		Thread.sleep(2000);
		actionOnElements(getProperties("OPENDROPDOWN"),"click",null);
		Thread.sleep(2000);
		actionOnElements(getProperties("DROPDOWNINPUT"),"send",getProperties("WRONGTERRITORY"));
		log.info("User input not found in dropdown");
	
	}

}
