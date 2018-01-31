package testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import reUsableMethods.ReusableMethods;
import reUsableMethods.TestcaseFailException;

public class Purdue_Feature_NegativeTesting extends ReusableMethods{

	/**
	 * Enters incorrect maild ID and checks for the expected popup
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws TestcaseFailException
	 */
	@Test(priority=0)
	public void enterincorrectmailid() throws IOException, InterruptedException, TestcaseFailException {
		try{
			openapplication();
			enterincorrectusername();
			verification(findElementText(getVerifyProperties("TOKENALERT")),getVerifyProperties("TOKENALERTWRONGNAME"));
			driver.close();
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			driver.close();
			throw ex;
		}
		log.info("SUCCESS:User entered incorrect mail ID and the corresponding popup message is shown");
	}
	
	/**
	 * Clicks on submit button without entering any username
	 * @throws Exception
	 */
	@Test(priority=1)
	public void verifyEmailWithoutTypingAnything() throws Exception
	{
		try {
			log.info("");
			openapplication();
			enteremptyusername();
			verification(findElementText(getVerifyProperties("TOKENALERT")),getVerifyProperties("TOKENEMPTYMAILID"));
			driver.close();
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			driver.close();
			throw ex;
		}
		log.info("SUCCESS:User entered no mail ID and the corresponding popup message is shown");
	}
	/**
	 * Token is generated for registered user and that token is entered after selecting guest user
	 * @throws Exception
	 */
	@Test(priority=2)
	public void loginAsGuestWithRegisterYourDeviceTokenKey() throws Exception
	{
		try {
			log.info("");
			openapplication();
			registereduser();
			enterusername();
			popuphandle();
			driver.navigate().refresh();
			Thread.sleep(2000);
			guestuser();
			actionOnElements(getProperties("EMAIL"),"send","soumyabillava@sagarsoft.in");
			actionOnElements(getProperties("EMAILSUBMIT"),"click","");
			popuphandle();
			readgmailentertoken();
			verification(findElementText(getVerifyProperties("TOKENALERT")),getVerifyProperties("TOKENALERTWRONGTOKEN"));
			driver.close();
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			driver.close();
			throw ex;
		}
		log.info("SUCCESS:User entered Registered user's token to the Guest User and the corresponding popup message is shown");
	}
	
	/**
	 * Token is generated for guest user and that token is entered after selecting registered user
	 * @throws Exception
	 */
	@Test(priority=3)
	public void loginAsRegisteredUserWithGuestTokenKey() throws Exception
	{
		try {
			log.info("");
			openapplication();
			guestuser();
			enterusername();
			popuphandle();
			driver.navigate().refresh();
			Thread.sleep(2000);
			registereduser();
			actionOnElements(getProperties("EMAIL"),"send","soumyabillava@sagarsoft.in");
			actionOnElements(getProperties("EMAILSUBMIT"),"click","");
			popuphandle();
			readgmailentertoken();
			verification(findElementText(getVerifyProperties("TOKENALERT")),getVerifyProperties("TOKENALERTWRONGTOKEN"));
			driver.close();
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			driver.close();
			throw ex;
		}
		log.info("SUCCESS:User entered Guest user's token to the Registered User and the corresponding popup message is shown");
	}

	/**
	 * Enters the token which is not latest and expects a popup telling wrong token
	 * @throws Exception
	 */
	@Test(priority=4)
	public void enterNotLatestToken() throws Exception {
		try {
			log.info("");
			openapplication();
			guestuser();
			enterusername();
			popuphandle();
			String token = readgmailentertoken();
			logout();
			Thread.sleep(2000);
			guestuser();
			enterusername();
			popuphandle();
			log.info("Entering the old token");
			actionOnElements(getProperties("TOKENFIELD"),"send",token);
			actionOnElements(getProperties("TOKENSUBMIT"),"click","");
			log.info("Token is entered");
			verification(findElementText(getVerifyProperties("TOKENALERT")),getVerifyProperties("TOKENALERTWRONGTOKEN"));
			driver.close();
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			driver.close();
			throw ex;
		}
		log.info("SUCCESS:User entered not latest token and the corresponding popup message is shown");
	}

	/**
	 * Enters incorrect token and checks if corresponding popup is shown
	 * @throws Exception
	 */
	@Test(priority=5)
	public void enterIncorrectToken() throws Exception {
		try {
			log.info("");
			openapplication();
			enterusername();
			popuphandle();
			invalidtoken();
			Thread.sleep(1000);
			verification(findElementText(getVerifyProperties("TOKENALERT")),getVerifyProperties("TOKENALERTWRONGTOKEN"));
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			driver.close();
			throw ex;
		}
		Thread.sleep(1000);
		popuphandle();
		driver.close();
		log.info("SUCCESS:User entered incorrect token and the corresponding popup message is shown");
	}

	/**
	 * Wrong domain is entered for the username and checks if corresponding popup is shown
	 * @throws Exception
	 */
	@Test(priority=6)
	public void verifyWrongDomain() throws Exception
	{
		try {
			log.info("");
			openapplication();
			setcookie();
			Thread.sleep(2000);
			enterincorrectDomainname();
			verification(findElementText(getVerifyProperties("TOKENALERT")),getVerifyProperties("TOKENALERTWRONGDOMAIN"));

		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			driver.close();
			throw ex;
		}
		driver.close();
		log.info("SUCCESS:Application allows only @sagarsoft.in domain");	
	}

	/**
	 * Clicks on print button without selecting any plan
	 * @throws Exception
	 */
	@Test(priority=7)
	public void verifyprintbuttonNOplan() throws Exception
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
			messageselection();
			selectlessplans_purdue();
			verification(findElementText(getVerifyProperties("TOKENALERT")),getVerifyProperties("TOKENALERTLESSPLAN"));
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			driver.close();
			throw ex;
		}
		driver.close();
		log.info("SUCCESS:A minimum of 1 plan is required for sell sheet printing");
	}

	/**
	 * Selects more than maximum number of plans allowed and checks for the corresponding popup
	 * @throws Exception
	 */
	@Test(priority=8)
	public void verifyMaxNoPlanPlusOne() throws Exception
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
			messageselection();

			int numberofmaxplans = selectmoreplans_purdue();
			if(numberofmaxplans==10)
				verification(findElementText(getVerifyProperties("TOKENALERT")),getVerifyProperties("TOKENALERTMAXPLAN1"));
			else if(numberofmaxplans<10&&numberofmaxplans>0)
				verification(findElementText(getVerifyProperties("TOKENALERT")),getVerifyProperties("TOKENALERTMAXPLAN2"));
		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			driver.close();
			throw ex;
		}
		driver.close();
		log.info("SUCCESS:A maximum of plans that may be selected for any message is 10.");
	}

	/**
	 * Checks if the links which are greyed out are clickable
	 * @throws Exception
	 */
	@Test(priority=9)
	public void verifynonclickablelinks() throws Exception
	{
		try {
			log.info("");
			openapplication();
			setcookie();
			Thread.sleep(2000);
			enterusername();
			Thread.sleep(2000);
			findElement(getProperties("HCPNONCLICKABLE")).click();
			verification(driver.getCurrentUrl(),getVerifyProperties("AREAPAGE"));
			findElement(getProperties("MESSAGENONCLICKABLE")).click();
			verification(driver.getCurrentUrl(),getVerifyProperties("AREAPAGE"));
			findElement(getProperties("PODNONCLICKABLE")).click();
			verification(driver.getCurrentUrl(),getVerifyProperties("SAREAPAGE"));
			selectdropdown(getProperties("USERTHERAPEUTICAREA"),getProperties("DROPDOWNLIST"));
			Thread.sleep(2000);
			selectdropdown(getProperties("USERTERRITORY"),getProperties("DROPDOWNLIST2"));
			Thread.sleep(3000);
			findElement(getProperties("MESSAGENONCLICKABLE")).click();
			verification(driver.getCurrentUrl(),getVerifyProperties("PROVIDERPAGE"));
			selectdropdown(getProperties("USERPROVIDER"),getProperties("DROPDOWNLIST"));
			Thread.sleep(3000);
			findElement(getProperties("PODNONCLICKABLE")).click();
			verification(driver.getCurrentUrl(),getVerifyProperties("MESSAGEPAGE"));		

		}catch(Exception ex) {
			log.error("FAIL:"+ex);
			driver.close();
			throw ex;
		}
		driver.close();
		log.info("SUCCESS:SelectHCP,Select Message and Print On Demand are Un-clickable links");
	}

	/**
	 * passes wrong value to the territory dropdown box and expects a message no results found
	 * @throws Exception
	 */
	@Test(priority=10)
	public void verifyWrongDropDownInput() throws Exception
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
			selectdropdownwrongvalue(getProperties("WRONGTERRITORY"),getProperties("DROPDOWNLIST2"));
			Thread.sleep(2000);
			verification(findElementText(getProperties("NOMATCHTEXT")),getVerifyProperties("NOMATCHTEXTEXPECTED"));
		}
		catch(Exception ex) {
			log.error("FAIL:"+ex);
			driver.close();
			throw ex;
		}
		driver.close();
		log.info("SUCCESS:No matches found is shown to the user for the Wrong dropdown value");
	}

}
