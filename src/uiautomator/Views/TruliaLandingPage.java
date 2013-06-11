package uiautomator.Views;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import uiautomator.Utils.CommonLib;

/**
 * Created with IntelliJ IDEA.
 * User: dbykovskyy
 * Date: 3/26/13
 * Time: 3:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class TruliaLandingPage extends UiAutomatorTestCase {

    CommonLib commonlib1 = new CommonLib();
    public final String username = "denist2@trulia.com";
    public final String password = "denis12345";

    public void typeIntoLoginField (String s){
        try {
            UiObject objName = new UiObject(new UiSelector().description("email_edit"));
            objName.setText(s);
        } catch (UiObjectNotFoundException e){
            System.out.println("Cold not set the text " + s);
        }
    }

    public void typeIntoPasswordField (String s){
        try {
            UiObject objName = new UiObject(new UiSelector().description("password_edit"));
            objName.setText(s);
        } catch (UiObjectNotFoundException e){
            System.out.println("Cold not set the text " + s);
        }
    }

    public void userSignIn(String username, String password) throws InterruptedException, UiObjectNotFoundException {
        UiObject uiObject = new UiObject(new UiSelector().text("Log In"));
        uiObject.clickAndWaitForNewWindow();
        typeIntoLoginField(username);
        typeIntoPasswordField(password);
    }
}
