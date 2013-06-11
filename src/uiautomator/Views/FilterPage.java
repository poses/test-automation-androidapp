package uiautomator.Views;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import uiautomator.Utils.CommonLib;

/**
 * Created with IntelliJ IDEA.
 * User: dbykovskyy
 * Date: 3/27/13
 * Time: 7:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class FilterPage extends UiAutomatorTestCase {
    //Locators
    public final String LOCATION_AUTOCOMPLETE = "autopromt_filter";
    public final String PRICE_RANGE_FILTER = "price_range_filter";
    public final String SLIDER_PRICE_FILTER = "slider1_filter";
    public final String BEDS_FILTER = "beds_prompt_filter";
    public final String BATH_FILTER = "baths_prompt_filter";
    public final String SQFT_RANGE_FILTER ="square_ft_filter";
    public final String SLIDER_SQFT_FILTER = "slider2_filter";
    public final String LOT_SIZE_FILTER = "spinner_filter1_filter";
    public final String YEAR_END = "year_build_year_hint";
    public final String YEAR_START = "year_build_start_date_filter";
    public final String MSL = "msl_location_filter";
    public final String RESET_BTN = "reset_filter";
    public final String FIND_HOMES_BTN = "find_homes_filter";

    //initializing
    CommonLib commonlibFilter = new CommonLib();

    public void inputLocation (String s) throws InterruptedException, UiObjectNotFoundException {
                commonlibFilter.typeIntoTextField("autopromt_filter", s);
                sleep(10);
                getUiDevice().pressEnter();
    }

    public void inputNumberOfBeds (int s) throws InterruptedException, UiObjectNotFoundException {
        commonlibFilter.clickOnDescription(BEDS_FILTER);
        UiCollection textViewsBath = new UiCollection(new UiSelector().className("android.widget.FrameLayout"));
        int numOfTextView = textViewsBath.getChildCount(new UiSelector().className("android.widget.TextView"));
        //"Any bed" option won't work
        //Looping through text views and click one that matches passed value
        for (int i = 1; i < numOfTextView; i++){
            UiObject bathTextView = textViewsBath.getChild(new UiSelector().className("android.widget.TextView").index(i));
            String tempValue = bathTextView.getText();
            String[] part = tempValue.split("\\+");
            String numberOfBedRooms = part[0];
            if (Integer.parseInt(numberOfBedRooms)==s){
                commonlibFilter.log("Wow there is a mach in the cell #" +i);
                bathTextView.click();
                break;
            }
            else{
            commonlibFilter.log("No bedrooms matching value of " + s + " in the cell #" + i);
            }
        }
    }

    public void inputNumberOfBath (int s) throws InterruptedException, UiObjectNotFoundException {
        commonlibFilter.clickOnDescription(BATH_FILTER);
        UiCollection textViewsBath = new UiCollection(new UiSelector().className("android.widget.FrameLayout"));
        int numOfTextView = textViewsBath.getChildCount(new UiSelector().className("android.widget.TextView"));
        //"Any bath" option won't work
        //Looping through text views and click one that matches passed value
        for (int i = 1; i < numOfTextView; i++){
            UiObject bathTextView = textViewsBath.getChild(new UiSelector().className("android.widget.TextView").index(i));
            String tempValue = bathTextView.getText();
            String[] part = tempValue.split("\\+");
            String numberOfBedRooms = part[0];
            if (Integer.parseInt(numberOfBedRooms)==s){
                commonlibFilter.log("Wow there is a mach in the cell #" +i);
                bathTextView.click();
                break;            }
            else{
                commonlibFilter.log("No bathrooms matching value of " + s + " in the cell #" + i);
            }
        }
    }
}
