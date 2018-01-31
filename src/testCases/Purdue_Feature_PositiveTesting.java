package testCases;


import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import reUsableMethods.ReusableMethods;
import reUsableMethods.TestcaseFailException;

public class Purdue_Feature_PositiveTesting extends ReusableMethods{

	/**
	 * Selects the radio button Enter as Guest and checks for the change
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws TestcaseFailException
	 */
	@Test(priority=0)
	public void verifyEnterAsGuestSelection() throws IOException, InterruptedException, TestcaseFailException
	{
		try{
			openapplication();
			log.info("Application is opened successfully");
			guestuser();	
			log.info("Verification started");
			verificationcontinue(findElement(getVerifyProperties("GUESTUSERRADIO")).getAttribute("checked"),"true");
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			throw ex;
		}
		log.info("SUCCESS:User Selected the GUEST USER Button successfully");
	}
	
	/**
	 * Selects the radio button Enter as Registered User and checks for the change
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws TestcaseFailException
	 */
	@Test(priority=1)
	public void verifyRegisterYourDeviceSelection() throws IOException, InterruptedException, TestcaseFailException
	{
		try {
			log.info("");
			registereduser();
			log.info("Verification started");
			verificationcontinue(findElement(getVerifyProperties("REGISTEREDUSERRADIO")).getAttribute("checked"),"true");
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			throw ex;
		}
		log.info("SUCCESS:User Selected the Registered User Button successfully");
	}
	
	/**
	 * Clicks on Contact Us Icon and checks for the popup with details
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws TestcaseFailException
	 */
	@Test(priority=2)
	public void verifyContactUsIcon() throws IOException, InterruptedException, TestcaseFailException
	{
		try {
			log.info("");
			Thread.sleep(1000);
			contactus();
			log.info("Verification started");
			Thread.sleep(2000);
			verification(findElement(getVerifyProperties("CONTACTBUTTONPOPUP")).getText(),getVerifyProperties("CONTACTBUTTONRESULT"));
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			driver.close();
			throw ex;
		}
		driver.close();
		log.info("SUCCESS:User Selected Contact Us Button successfully");
	}
	
	/**
	 * Enters the username with correct domain and checks for the expected popup
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws TestcaseFailException
	 */
	@Test(priority=3)
	public void enterCorrectDomain() throws IOException, InterruptedException, TestcaseFailException {

		try {
			log.info("");
			openapplication();
			enterusername();
			verification(findElementText(getVerifyProperties("TOKENALERT")),getVerifyProperties("TOKENALERTEXPECTED"));
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			driver.close();
			throw ex;
		}
		driver.close();
		log.info("SUCCESS:User entered the correct domain");
	}	
	
	/**
	 * Selects login as Guest radio button and verifies the token sent to gmail 
	 * @throws Exception
	 */
	@Test(priority=4)
	public void loginAsGuest() throws Exception {
		try {
		log.info("");
		openapplication();
		guestuser();
		enterusername();
		popuphandle();
		readgmailentertoken();
		Thread.sleep(2000);
		log.info("Verification started");
		verificationcontinue(driver.getCurrentUrl(),getVerifyProperties("AREAPAGE"));
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			throw ex;
		}
		log.info("SUCCESS:User entered correct token for the Guest User");
	}

	/**
	 * Selects to download FAQ pdf and checks for downloaded pdf file
	 * @throws Exception
	 */
	@Test(priority=5)
	public void	verifyFAQInformation() throws Exception
	{
		try {
			log.info("");
			selectFAQs();
			Thread.sleep(2000);
			robotenter();
			Thread.sleep(3000);
			downloadverification();
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			logout();
			driver.close();
			throw ex;
		}
		logout();
		driver.close();
		log.info("SUCCESS:FAQ PDF is downloaded successfully");
	}
	
	/**
	 * logs in to the application and then clicks on logout button and checks if the user has logged out
	 * @throws Exception
	 */
	@Test(priority=6)
	public void	verifyLogoutIcon() throws Exception
	{
		try {
			log.info("");
			openapplication();
			setcookie();
			Thread.sleep(2000);
			enterusername();
			Thread.sleep(2000);
			logout();
			verification(driver.getCurrentUrl(),getVerifyProperties("URL"));
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			driver.close();
			throw ex;
		}
		driver.close();
		log.info("SUCCESS:User logged out of the application successfully");
	}
	
	/**
	 * Logs in to the application and checks for the UI Fields above the footer
	 * @throws Exception
	 */
	@Test(priority=7)
	public void	uiFieldsabovefooter() throws Exception
	{
		try {
			log.info("");
			openapplication();
			setcookie();
			Thread.sleep(2000);
			enterusername();
			selectdropdown(getProperties("USERTHERAPEUTICAREA"),getProperties("DROPDOWNLIST"));
			Thread.sleep(3000);
			selectdropdown(getProperties("USERTERRITORY"),getProperties("DROPDOWNLIST2"));
			selectdropdown(getProperties("USERPROVIDER"),getProperties("DROPDOWNLIST"));
			String reldate = releaseDate();
			verificationcontinue(reldate,getVerifyProperties("EXPECTEDRELEASEDATE"));
			String lateData = latestData();
			verificationcontinue(lateData,getVerifyProperties("EXPECTEDLATESTDATA"));
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			throw ex;
		}
		log.info("SUCCESS:UI fields above footer are present");
	}
	
	/**
	 * Logs in to the application and Verifies the global footer
	 * @throws Exception
	 */
	@Test(priority=8)
	public void	verifyTheGlobalFooter() throws Exception
	{
		try {
			log.info("");
			globalFooterVerify();
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			throw ex;
		}
		log.info("SUCCESS:Global footer is verified succesfully");
	}
	
	/**
	 * Checks if a message type is selected by default when the application enters message selection page
	 * @throws Exception
	 */
	@Test(priority=9)
	public void	DefaultMessageVerification() throws Exception
	{
		try {
			log.info("");
		
			if(findElement(getVerifyProperties("DEFAULTMESSAGESELECTED")).getText().equals("Formulary Announcement"))
				verificationcontinue(getVerifyProperties("FORMULARYANNOUNCEMENTRESULTEXPECTED"),findElement(getVerifyProperties("FORMULARYANNOUNCEMENTRESULT")).getText());
			else
				verificationcontinue(findElement(getVerifyProperties("DEFAULTMESSAGESELECTED")).getText(),findElement(getVerifyProperties("MESSAGETOPRINT")).getText());
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			throw ex;
		}
		log.info("SUCCESS:Default selected message is "+findElement(getVerifyProperties("DEFAULTMESSAGESELECTED")).getText());
	}
	
	/**
	 * Selects the user given message and check for the change
	 * @throws Exception
	 */
	@Test(priority=10)
	public void	MessageSelection() throws Exception
	{
		try {
			log.info("");
			messageselection();
			verificationcontinue(findElement(getVerifyProperties("MESSAGETOPRINT")).getText(),getProperties("USERMESSAGE"));
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			throw ex;
		}
		log.info("SUCCESS:User selected the Message:"+getProperties("USERMESSAGE"));
	}
	
	/**
	 * verifies if the column headers are in the expected order
	 * @throws Exception
	 */
	@Test(priority=11)
	public void	columnHeaderLeftToRightVerify() throws Exception
	{
		try {
			log.info("");
			columnHeaderLeftToRight();
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			throw ex;
		}
	}
	
	/**
	 * Makes the given column Header in Alphabetic Order and verifies the same
	 * @throws Exception
	 */
	@Test(priority=12)
	public void	ColumnHeaderAlphabeticOrder() throws Exception
	{
		try {
			log.info("");
			int planscolumns = planascendingorder();
			Thread.sleep(2000);
			plansascendingorderverification(planscolumns);
			log.info("SUCCESS:Plans are in the ascending order");
			Thread.sleep(2000);
			plandescendingorder(planscolumns);
			Thread.sleep(2000);
			plansdescendingorderverification(planscolumns);
			log.info("SUCCESS:Plans are in the descending order");
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			throw ex;
		}
	}
	
	/**
	 * checks if navigating to previous pages are allowed from current page
	 * @throws Exception
	 */
	@Test(priority=13)
	public void verifyclickablelinks() throws Exception
	{
		try {
			log.info("");
			messagetop();
			verificationcontinue(driver.getCurrentUrl(),getVerifyProperties("MESSAGEPAGE"));
			providertop();
			verificationcontinue(driver.getCurrentUrl(),getVerifyProperties("PROVIDERPAGE"));
			territorytop();
			verification(driver.getCurrentUrl(),getVerifyProperties("AREAPAGE"));
		}
		catch(Exception ex) {
			log.error("FAIL:"+ex);
			driver.close();
			throw ex;
		}
		driver.close();
		log.info("SUCCESS:SelectHCP,Select Message and Print On Demand are clickable links in navigation bar and user navigates backwards");
	}
	
	
	/**
	 * clicks on download button and checks for the pdf file in downloaded path
	 * @throws Exception
	 */
	@Test(priority=14)
	public void	downloadButtonFunctionality() throws Exception
	{
		try {
			log.info("");
			openapplication();
			setcookie();
			Thread.sleep(2000);
			enterusername();
			selectdropdown(getProperties("USERTHERAPEUTICAREA"),getProperties("DROPDOWNLIST"));
			Thread.sleep(2000);
			selectdropdown(getProperties("USERTERRITORY"),getProperties("DROPDOWNLIST2"));
			selectdropdown(getProperties("USERPROVIDER"),getProperties("DROPDOWNLIST"));
			messageselection();
			selectplans_purdue();
			flashboardbutton();
			downloadbutton();
			Thread.sleep(2000);
			robotenter();
			log.info("Verification started");
			Thread.sleep(4000);
			downloadverification();
			}catch(Exception ex) {
			log.error("FAIL:"+ex);
			logout();
			driver.close();
			throw ex;
		}
		log.info("SUCCESS:Download functionality is working as expected");
	}
	
	/**
	 * Prints the downloaded filename into log file for the user to check
	 * @throws Exception
	 */
	@Test(priority=15,dependsOnMethods="downloadButtonFunctionality")
	public void	verifyDownloadFilename() throws Exception
	{
		try {
			log.info("");
			filenameverification();
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			logout();
			driver.close();
			throw ex;
		}
		logout();
		driver.close();
	}	
	
	/**
	 * Checks  When user navigates to HCP page, previous selection is cleared
	 * @throws Exception
	 */
	@Test(priority=16)
	public void selectHCPClearedofPreviousSelection() throws Exception {
		try {
			log.info("");
		openapplication();
		setcookie();
		enterusername();
		selectdropdown(getProperties("USERTHERAPEUTICAREA"),getProperties("DROPDOWNLIST"));
		log.info("User Therapeutic Area is selected");
		Thread.sleep(4000);
		selectdropdown(getProperties("USERTERRITORY"),getProperties("DROPDOWNLIST2"));
		log.info("User Territory is selected");
		Thread.sleep(6000);
		log.info("Verified for Therapeutic Area and territory");
		Thread.sleep(1000);
		selectdropdown(getProperties("USERPROVIDER"),getProperties("DROPDOWNLIST"));
		Thread.sleep(4000);
		log.info("Verified for User selected Provider");
		providertop();
		
		log.info("Verification started");
		verification(findElement(getVerifyProperties("SELECTHCPSPAN")).getText(),getVerifyProperties("SELECTHCPSPANTEXTEXPECTED"));
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			logout();
			driver.close();
			throw ex;
		}
		logout();
		driver.close();
		log.info("SUCCESS:Navigating back to Select HCP page has cleared the previous entry");
	}
	
	/**
	 * Checks  When user navigates to Select Ares page, previous selection is cleared
	 * @throws Exception
	 */
	@Test(priority=17)
	public void selectAreaClearedofPreviousSelection() throws Exception {
		try {
			log.info("");
		openapplication();
		setcookie();
		enterusername();
		selectdropdown(getProperties("USERTHERAPEUTICAREA"),getProperties("DROPDOWNLIST"));
		log.info("User Therapeutic Area is selected");
		Thread.sleep(4000);
		selectdropdown(getProperties("USERTERRITORY"),getProperties("DROPDOWNLIST2"));
		log.info("User Territory is selected");
		Thread.sleep(6000);
		log.info("Verified for Therapeutic Area and territory");
		Thread.sleep(1000);
		selectdropdown(getProperties("USERPROVIDER"),getProperties("DROPDOWNLIST"));
		Thread.sleep(4000);
		log.info("Verified for User selected Provider");
		territorytop();
		
		log.info("Verification started");
		verification(findElement(getVerifyProperties("SELECTTHERAPEUTICAREASPAN")).getText(),getVerifyProperties("SELECTTHERAPEUTICAREASPANTEXTEXPECTED"));
		log.info("Therapeutic area has cleared the previous entry");
		verification(findElement(getVerifyProperties("SELECTTERRITORYSPAN")).getText(),getVerifyProperties("SELECTTERRITORYSPANTEXTEXPECTED"));
		log.info("territory has cleared the previous entry");
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			logout();
			driver.close();
			throw ex;
		}
		logout();
		driver.close();
		log.info("SUCCESS:Navigating back to Select Area page has cleared the previous entry");
	}
	
	/**
	 * Verifies the logo navigation bar
	 * @throws Exception
	 */
	@Test(priority=18)
	public void verifyLogoNavbar() throws Exception
	{
		try {
			log.info("");
			openapplication();
			setcookie();
			Thread.sleep(2000);
			enterusername();
			Thread.sleep(2000);
			selectdropdown(getProperties("USERTHERAPEUTICAREA"),getProperties("DROPDOWNLIST"));
			Thread.sleep(2000);
			selectdropdown(getProperties("USERTERRITORY"),getProperties("DROPDOWNLIST2"));
			Thread.sleep(2000);
			selectdropdown(getProperties("USERPROVIDER"),getProperties("DROPDOWNLIST"));
			Thread.sleep(3000);
			verification(findElement(getVerifyProperties("LOGO")).getAttribute("src"),getVerifyProperties("LOGOMESSAGEEXPECTED"));	
			verification(findElement(getProperties("TOPBUTTONTERRITORY")).getText(),getVerifyProperties("AREABUTTONTEXT"));
			verification(findElement(getProperties("TOPBUTTONPROVIDER")).getText(),getVerifyProperties("HCPBUTTONTEXT"));
			verification(findElement(getProperties("TOPBUTTONMESSAGE")).getText(),getVerifyProperties("MESSAGEBUTTONTEXT"));
			log.info("Select Area,Select HCP,Select Message links are present");

		}
		catch(Exception ex) {
			log.error("FAIL:"+ex);
			driver.close();
			throw ex;
		}
		driver.close();
		log.info("SUCCESS:Global Purdue logo and navigation bar verified in Select Message screen");
	}
	
	/**
	 * Verifies the Native navigation in each page of application
	 * @throws Exception
	 */
	@Test(priority=19)
	public void verifyNativeNavigation() throws Exception
	{
	try {
		log.info("");
		openapplication();
		setcookie();
		Thread.sleep(2000);
		enterusername();
		Thread.sleep(2000);
		driver.navigate().back();
		verification(driver.getCurrentUrl(),getVerifyProperties("AREAPAGE"));
		log.info("User moved to Select Area page");
		selectdropdown(getProperties("USERTHERAPEUTICAREA"),getProperties("DROPDOWNLIST"));
		Thread.sleep(2000);
		selectdropdown(getProperties("USERTERRITORY"),getProperties("DROPDOWNLIST2"));
		Thread.sleep(2000);
		driver.navigate().back();
		verification(driver.getCurrentUrl(),getVerifyProperties("AREAPAGE"));
		log.info("User moved back to Select Area page");
		selectdropdown(getProperties("USERTHERAPEUTICAREA"),getProperties("DROPDOWNLIST"));
		Thread.sleep(2000);
		selectdropdown(getProperties("USERTERRITORY"),getProperties("DROPDOWNLIST2"));
		Thread.sleep(2000);
		selectdropdown(getProperties("USERPROVIDER"),getProperties("DROPDOWNLIST"));
		Thread.sleep(3000);
		driver.navigate().back();
		verification(driver.getCurrentUrl(),getVerifyProperties("PROVIDERPAGE"));
		log.info("User moved back to provider page");
	}
	catch(Exception ex) {
		log.error("FAIL:"+ex);
		driver.close();
		throw ex;
	}
	driver.close();
	log.info("SUCCESS:User is able to navigate through native browser navigation options");
	}
	
	/**
	 * Selects the user entered  product
	 * @throws Exception
	 */
	@Test(priority=20)
	public void selecttheproduct() throws Exception {
		try {
			log.info("");
			openapplication();
			setcookie();
			enterusername();
			selectdropdown(getProperties("USERTHERAPEUTICAREA"),getProperties("DROPDOWNLIST"));
			log.info("User Therapeutic Area is selected");
			Thread.sleep(4000);
			selectdropdown(getProperties("USERTERRITORY"),getProperties("DROPDOWNLIST2"));
			log.info("User Territory is selected");
			Thread.sleep(6000);
			log.info("Verified for Therapeutic Area and territory");
			Thread.sleep(1000);
			selectdropdown(getProperties("USERPROVIDER"),getProperties("DROPDOWNLIST"));
			Thread.sleep(4000);
			log.info("Verified for User selected Provider");
			selectproduct();
			Thread.sleep(2000);
			if(elementPresence(getProperties("OPENDROPDOWNPRODUCT"))==true) 
				verificationcontinue(getProperties("PRODUCT"),findElement(getVerifyProperties("PRODUCTLOGO")).getAttribute("alt"));
			else
				verificationcontinue(findElementText(getProperties("SINGLEPRODUCTSELECTED")),findElement(getVerifyProperties("PRODUCTLOGO")).getAttribute("alt"));
			
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			throw ex;
		}
		log.info("SUCCESS:User entered product is selected successfully");
	}
	
	/**
	 * Selects channel using mouse over and checks the table for corresponding plans
	 * @throws Exception
	 */
	@Test(priority=21)
	public void selectmouseoverchannel() throws Exception {
		try {
			log.info("");
			messageselection();
			Thread.sleep(2000);
			channelselection();
			System.out.println("selecting done");
			channelselectionverification();
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			logout();
			driver.close();
			throw ex;
		}
		logout();
		driver.close();
		}
	
}
