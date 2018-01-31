package testCases;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import reUsableMethods.ReusableMethods;
import reUsableMethods.TestcaseFailException;


public class FeatureTesting_Lundbeck extends ReusableMethods {

	/**
	 * Opens the user selected application in user selected browser
	 * @throws IOException
	 * @throws TestcaseFailException
	 * @throws InterruptedException
	 */
	@Test(priority=0)
	public void verifyEnterAsGuestSelection() throws IOException, TestcaseFailException, InterruptedException
	{
		try {
			openapplication();
			log.info("Application is opened successfully");
			guestuser();	
			log.info("Verification started");
			verification(findElement(getVerifyProperties("GUESTUSERRADIO")).getAttribute("checked"),"true");
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			throw ex;
		}
		log.info("SUCCESS:User Selected the GUEST USER Button successfully");

	}
	@Test(priority=1)
	public void verifyRegisterYourDeviceSelection() throws IOException, InterruptedException, TestcaseFailException
	{
		try {
			registereduser();
			log.info("Verification started");
			verification(findElement(getVerifyProperties("REGISTEREDUSERRADIO")).getAttribute("checked"),"true");
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			throw ex;
		}
		log.info("SUCCESS:User Selected the Registered User Button successfully");
	}

	@Test(priority=2)
	public void verifyContactUsIcon() throws IOException, InterruptedException, TestcaseFailException
	{
		try {
			actionOnElements(getProperties("CONTACTICON"),"click","");
			log.info("User selected Contact Us Button");
			log.info("Verification started");
			verification(findElement(getVerifyProperties("CONTACTBUTTON")).getText(),getVerifyProperties("CONTACTBUTTONRESULT"));
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			throw ex;
		}
		log.info("SUCCESS:User Selected Contact Us Button successfully");
	}
	@Test(priority=3)
	public void entercorrectmailid() throws IOException, InterruptedException, TestcaseFailException {

		try {
			openapplication();
			enterusername();

			//Check for the expected result
			verification(findElementText(getVerifyProperties("TOKENALERT")),getVerifyProperties("TOKENALERTEXPECTED"));
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			throw ex;
		}
		//popuphandle();
	}

	@Test(priority=4)
	public void enterincorrectmailid() throws IOException, InterruptedException, TestcaseFailException {
		try{
			openapplication();
			enterincorrectusername();

			//Check for the expected result
			verification(findElementText(getVerifyProperties("TOKENALERT")),getVerifyProperties("TOKENALERTWRONGNAME"));

			}catch(Exception ex) {
				log.error("FAIL:"+ex);
				throw ex;
		}
	}

}
