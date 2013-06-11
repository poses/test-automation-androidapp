package uiautomator;

import android.os.RemoteException;
import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import junit.framework.Assert;
import uiautomator.Utils.CommonLib;
import uiautomator.Utils.LaunchTheAppCalled;
import uiautomator.Views.FilterPage;
import uiautomator.Views.SRPpage;
import uiautomator.Views.TruliaLandingPage;

import java.util.ListIterator;

/**
 * Created with IntelliJ IDEA.
 * User: dbykovskyy
 * Date: 3/25/13
     * Time: 6:21 PM
     * To change this template use File | Settings | File Templates.
 */

public class MainLoginTest extends UiAutomatorTestCase {
        //Some vars for the test
        public final String username = "denist2@trulia.com";
        public final String password = "denis12345";
        public final int numberOfBed = 3;
        public final int numberOfBath = 2;
        //Initializing pages
        CommonLib commonlib = new CommonLib();
        TruliaLandingPage landingPage = new TruliaLandingPage();
        LaunchTheAppCalled launchtheapp = new LaunchTheAppCalled();
        FilterPage filterpage = new FilterPage();
        SRPpage srppage = new SRPpage();

        public void test1SuccessfulLaunch() throws UiObjectNotFoundException, RemoteException, InterruptedException {
            //Tap Home and then App button
            getUiDevice().pressHome();
            sleep(10);
            commonlib.clickOnDescription("Apps");
            //Launch Trulia
            sleep(10);
            //Swipe for real dewice 4.2
            launchtheapp.launchAppCalled("Trulia");
/*          Swipe for an emulator 4.1.2
            launchtheapp.swipeToTheRightAndClickByPackage("Trulia");*/
            String currentPackageName = getUiDevice().getCurrentPackageName();
            //Assert that desirable app is running
            Assert.assertEquals("Trulia app seems to be not launched", "com.trulia.android", currentPackageName);
        }

        public void test2SignIn() throws UiObjectNotFoundException, RemoteException, InterruptedException {
            // Click on more options
            commonlib.clickOnDescription("More options");
            UiObject optionMenueTextLogOut = commonlib.getObjectByText("Log Out");
            //if yes sign user out
            if (optionMenueTextLogOut.exists()){
                commonlib.log("User is logged in");
                optionMenueTextLogOut.click();
                commonlib.log("Loging out the user");
                sleep(10);
            }
            commonlib.clickOnDescription("More options");
            commonlib.log("Signin in a user");
            landingPage.userSignIn(username, password);
            getUiDevice().pressBack();
            commonlib.clickOnDescription("login_submit");
            //Verification
            commonlib.clickOnDescription("More options");
            Boolean ifLogoutExists = optionMenueTextLogOut.exists();
            Assert.assertTrue("Log out button is not there", ifLogoutExists);
            getUiDevice().pressBack();
        }
        public void test3SearchCount() throws UiObjectNotFoundException, RuntimeException, InterruptedException {
            commonlib.clickOnDescription("Search");
            sleep(5);
            commonlib.clickOnDescription(filterpage.RESET_BTN);
            sleep(5);
 /*           UiObject blah = new UiObject(new UiSelector().description(filterpage.LOCATION_AUTOCOMPLETE));
             String foo = blah.getText();
            commonlib.clickOnDescription(filterpage.LOCATION_AUTOCOMPLETE);
            commonlib.log("text of aoutocomplete isssssssssssssssssssss" + foo);
            if (blah.exists()){
                blah.clearTextField();
            }
            filterpage.inputLocation("San Francisco, CA");
            getUiDevice().pressBack();
*/
            filterpage.inputNumberOfBeds(numberOfBed);
            filterpage.inputNumberOfBath(numberOfBath);

/*          UiObject slider1 = new UiObject(new UiSelector().description("slider1_filter"));
            for determining slider cords
            String dfs = slider1.getVisibleBounds().toString();
*/
            //count whole list results*/
            sleep(10);
            commonlib.clickOnDescription(filterpage.FIND_HOMES_BTN);
            int totalSearchresultDisplayed = srppage.getNumberOfSearchResults();
            commonlib.log("Totlat serch results " + totalSearchresultDisplayed);

            //Verify that price starts from $ in the view SRP
            String expectedSign = "$";
            char expectedChar = expectedSign.charAt(0);
            char actualChar = srppage.getDollarSignFormPrice();
            Assert.assertEquals(expectedChar, actualChar);

            //Verifying that number of Bath and Beds = requested
            String [] searchContextRes = srppage.getSearchContext();
            int bed = Integer.parseInt(searchContextRes[0]) ;
            Assert.assertEquals(numberOfBed, bed);
            int bath = Integer.parseInt(searchContextRes[1]);
            Assert.assertEquals(numberOfBath, bath);
        }

        public void test4TryingToScrollDown() throws UiObjectNotFoundException, RuntimeException, InterruptedException{
           //trying to scroll it down
           UiCollection cellItem = new UiScrollable(new UiSelector().className("android.widget.ListView"));
           //UiObject oneOf = cellItem.getChildByDescription(new UiSelector().className("android.widget.RelativeLayout"), "result_cell_layout_list");
           UiObject oneOf = cellItem.getChildByDescription(new UiSelector().className("android.widget.RelativeLayout"), "result_cell_layout_list");

           int count = cellItem.getChildCount(new UiSelector().description("result_cell_layout_list"));
           commonlib.log("Get text from thedddddddddddddddddddddddddddddddddd  " + count);

           UiObject onecell = new UiObject(new UiSelector().description("result_cell_layout_list"));

          for (int i =0; i <= 50; i++){

             commonlib.log("Text isssssssssssssssssssssssssssssssssss" + onecell);

          }


          //String test = oneOf.getText();
          //commonlib.log("Get text from the " + test);


        }
}

