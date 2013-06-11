package uiautomator.Utils;

import android.util.Log;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;

/**
 * Created with IntelliJ IDEA.
 * User: dbykovskyy
 * Date: 3/26/13
 * Time: 3:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommonLib {

    public UiObject clickOnText (String s) throws UiObjectNotFoundException {
        log("Trying to click text " + s);
        UiObject objName = null;
        try{
            objName = new UiObject(new UiSelector().text(s));
            objName.clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e){
            System.out.println("TEST> Could not find element by text " + s);
        }
        return objName;
    }

    public UiObject clickOnDescription (String s) throws UiObjectNotFoundException{
        log("Trying to click text " + s);
        UiObject objName = null;
        try{
            objName = new UiObject(new UiSelector().description(s));
            objName.clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e){
            System.out.println("Could not find element by description   " + s);
        }
        return objName;
    }

    public void typeIntoTextField (String s, String text) throws UiObjectNotFoundException {
        log("Trying to type into textfield " + s + "text " + text);
        try {
            UiObject objName = new UiObject(new UiSelector().description(s));
            objName.setText(text);
        } catch (UiObjectNotFoundException e){
            System.out.println("Could not find element " + s + " to type text " + text);
        }
    }

    public UiObject getObjectByText (String s) throws UiObjectNotFoundException {
        log("Locating element with text " + s);
        UiObject objName = new UiObject(new UiSelector().text(s));
        return objName;
    }

    public UiObject getObjectByDescription (String s) throws UiObjectNotFoundException {
        log("Getting element with description " + s);
        UiObject objName = new UiObject(new UiSelector().description(s));
        return objName;
    }


    public void log(String message) {
        System.out.println("TEST> " + message);
    }

        public boolean checkForCondition(String dialogText, String buttontext) {
            UiObject okCancelDialog = new UiObject(new UiSelector().textStartsWith(dialogText));
            if(okCancelDialog.exists()){
                UiObject okButton = new UiObject(new UiSelector().className("android.widget.Button").text(buttontext));
                try {
                    okButton.click();
                } catch (UiObjectNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                return (okCancelDialog.waitUntilGone(25000));
            }
            return false;
        }


}
