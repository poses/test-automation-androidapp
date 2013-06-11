package uiautomator.Utils;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

/**
 * Created with IntelliJ IDEA.
 * User: dbykovskyy
 * Date: 3/27/13
 * Time: 2:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class LaunchTheAppCalled extends UiAutomatorTestCase {

    /**
     * Launches an app by it's name.
     *
     * @param nameOfAppToLaunch the localized name, an exact match is required to launch it.
     */
    public void launchAppCalled(String nameOfAppToLaunch) throws UiObjectNotFoundException {
        UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
        // Set the swiping mode to horizontal (the default is vertical)
        appViews.setAsHorizontalList();
        appViews.scrollToBeginning(10);  // Otherwise the Apps may be on a later page of apps.
        int maxSearchSwipes = appViews.getMaxSearchSwipes();

        UiSelector selector;
        selector = new UiSelector().className(android.widget.TextView.class.getName());

        UiObject appToLaunch;

        // The following loop is to workaround a bug in Android 4.2.2 which
        // fails to scroll more than once into view.
        for (int i = 0; i < maxSearchSwipes; i++) {

            try {
                appToLaunch = appViews.getChildByText(selector, nameOfAppToLaunch);
                if (appToLaunch != null) {
                    // Create a UiSelector to find the Settings app and simulate
                    // a user click to launch the app.
                    appToLaunch.clickAndWaitForNewWindow();
                    break;
                }
            } catch (UiObjectNotFoundException e) {
                System.out.println("Did not find match for " + e.getLocalizedMessage());
            }

            for (int j = 0; j < i; j++) {
                appViews.scrollForward();
                System.out.println("scrolling forward 1 page of apps.");
            }
        }
    }

    public void swipeToTheRightAndClickByPackage (String s) throws UiObjectNotFoundException {
        UiObject truliaApp = new UiObject (new UiSelector().text(s));
        while (!truliaApp.exists()){
            try {
                new UiObject(new UiSelector().packageName("com.android.launcher")).swipeLeft(15);
                sleep(10);
            }catch (UiObjectNotFoundException e){
                System.out.println("TEST> Could not find element by text " + e);
            }
        }
        truliaApp.clickAndWaitForNewWindow();
    }

}
