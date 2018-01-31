package testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import reUsableMethods.ReusableMethods;
import reUsableMethods.TestcaseFailException;

public class BVT_Purdue extends ReusableMethods {
	/**
	 * Opens the user selected application in user selected browser
	 * @throws IOException
	 * @throws TestcaseFailException
	 * @throws InterruptedException
	 */
	@Test(priority=0)
	public void openbrowserwithurl() throws IOException, TestcaseFailException, InterruptedException
	{
		try {
			openapplication();
			log.info("Verification started");
			verification(findElement(getVerifyProperties("LOGO")).getAttribute("src"),getVerifyProperties("LOGOMESSAGEEXPECTED"));	
			log.info("SUCCCESS:User opened "+getProperties("APPLICATION")+" in "+getProperties("BROWSER")+" browser");
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			throw ex;
		}
	}

	/**
	 * Enters the username to receive a token in gmail
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws TestcaseFailException
	 */
	@Test(priority=1,dependsOnMethods="openbrowserwithurl")
	public void enterusernametogettoken() throws IOException, InterruptedException, TestcaseFailException
	{
		try {
			log.info("");
			enterusername();
			Thread.sleep(6000);
			log.info("Verification started");
			verification(findElementText(getVerifyProperties("TOKENALERT")),getVerifyProperties("TOKENALERTEXPECTED"));
			popuphandle();
			log.info("SUCCESS:"+"User Entered the correct username "+getProperties("GMAILUSERNAME"));
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			throw ex;
		}
	}

	/**
	 * Reads the token value from gmail and enters the value
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws TestcaseFailException
	 */
	@Test(priority=2,dependsOnMethods="enterusernametogettoken")
	public void readgmailandentertoken() throws IOException, InterruptedException, TestcaseFailException
	{
		try {
			log.info("");
			readgmailentertoken();
			Thread.sleep(2000);
			log.info("Verification started");
			verification(driver.getCurrentUrl(),getVerifyProperties("AREAPAGE"));
			
			log.info("SUCCESS:Entered correct Token");
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			throw ex;
		}
	}

	/**
	 * Selects the user Entered Territory
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws TestcaseFailException
	 */
	@Test(priority=3,dependsOnMethods="readgmailandentertoken")
	public void userselectsterritory() throws IOException, InterruptedException, TestcaseFailException
	{
		try {
			log.info("");
			selectdropdown(getProperties("USERTHERAPEUTICAREA"),getProperties("DROPDOWNLIST"));
			log.info("User Therapeutic Area is selected");
			Thread.sleep(2000);
			selectdropdown(getProperties("USERTERRITORY"),getProperties("DROPDOWNLIST2"));
			log.info("User Territory is selected");
			Thread.sleep(5000);
			log.info("Verification started");
			verification(driver.getCurrentUrl(),getVerifyProperties("PROVIDERPAGE"));
			log.info("SUCCESS:User Entered correct Territory-"+getProperties("USERTERRITORY"));
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			throw ex;
		}
	}

	/**
	 * Selects the user Entered Provider
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws TestcaseFailException
	 */
	@Test(priority=4,dependsOnMethods="userselectsterritory")
	public void userselectsprovider() throws IOException, InterruptedException, TestcaseFailException
	{
		try {
			log.info("");
			Thread.sleep(1000);
			selectdropdown(getProperties("USERPROVIDER"),getProperties("DROPDOWNLIST"));
			log.info("User Provider is selected");
			log.info("Verification started");
			Thread.sleep(2000);
			verification(driver.getCurrentUrl(),getVerifyProperties("MESSAGEPAGE"));
			log.info("SUCCESS:User Entered correct Provider-"+getProperties("USERPROVIDER"));
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			throw ex;
		}
	}

	/**
	 * Selects 1st two plans and checks redirection to print page
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws TestcaseFailException
	 */
	@Test(priority=5,dependsOnMethods="userselectsprovider")
	public void userselectsplans() throws InterruptedException, IOException, TestcaseFailException
	{
		try {
			log.info("");
			messageselection();
			selectplans_purdue();
			flashboardbutton();
			Thread.sleep(4000);
			log.info("Verification started");
			verification(driver.getCurrentUrl(),getVerifyProperties("PRINTPAGE"));
			log.info("SUCCESS:User selected the plans successfully");
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			throw ex;
		}
	}

	/**
	 * Verifies the print button presence and download functionality
	 * @throws Exception 
	 */
	@Test(priority=6,dependsOnMethods="userselectsplans")
	public void verifyprintpage() throws Exception{
		try {
			log.info("");
			log.info("Checking for print button");
			Thread.sleep(4000);
			verification(findElement(getProperties("PRINTBUTTON")).getAttribute("src"),getVerifyProperties("PRINTPNG"));
			log.info("Checking for download button");
			downloadbutton();
			Thread.sleep(3000);
			robotenter();
			log.info("Verification started");
			downloadverification();
			logout();
			driver.close();
			log.info("SUCCESS:Print and download buttons are present");

		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			logout();
			driver.close();
			throw ex;
		}
	}
}
