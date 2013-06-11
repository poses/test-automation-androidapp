package uiautomator;

import android.os.RemoteException;
import com.android.uiautomator.core.*;
import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import junit.framework.Assert;

/**
 * Created with IntelliJ IDEA.
 * User: dbykovskyy
 * Date: 6/11/13
 * Time: 10:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class DeviceUnlockerTest extends UiAutomatorTestCase {


    private static final String LOG_TAG = "DeviceUnlockerPseudoTest";
    private static final String SCREENLOCKKEYPAD_STRING = "ScreenLockKeypadWatcher";
    private static final String TESTING_GIT = "ScreenLockKeypadWatcher";


    public void testScreenUnlocker() throws UiObjectNotFoundException {

        //Check if screen is asleep and wake/unlock as needed
        try {
            if (!getUiDevice().isScreenOn()){
                getUiDevice().wakeUp();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        //Screen lock keypad watcher
        //this watcher handles the scenario where the screen is awake but still locked
        UiWatcher screenLockKeypadWatcher = new UiWatcher() {
            @Override
            public boolean checkForCondition(){
                //Check if screen is locked using the Emergency Call button
                UiObject emergencyCallButton = new UiObject(new UiSelector().text("Emergency call").className("android.widget.Button"));
                if (emergencyCallButton.exists()){
                    UiObject pinBox = new UiObject(new UiSelector().className("android.widget.EditText"));
                    try {
                        pinBox.setText("111111");
                    } catch (UiObjectNotFoundException e) {
                        e.printStackTrace();
                    }
                    getUiDevice().pressEnter();

                    return true;
                }
                return false;
            }
        };


        UiDevice.getInstance().registerWatcher(SCREENLOCKKEYPAD_STRING, screenLockKeypadWatcher);
        UiDevice.getInstance().runWatchers();


        getUiDevice().pressHome();

        UiObject allAppsButton = new UiObject(new UiSelector().description("Apps"));
        Assert.assertTrue("Could not find the Apps button in the launcher.", allAppsButton.isClickable());

    }



}

