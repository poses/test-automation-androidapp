package uiautomator.Views;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import uiautomator.Utils.CommonLib;

/**
 * Created with IntelliJ IDEA.
 * User: dbykovskyy
 * Date: 3/28/13
 * Time: 4:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class SRPpage extends UiAutomatorTestCase{
    //Locators
    public final String PRICE_VIEW = "list_pice_list";
    public final String ADDRESS_VIEW = "estimate_price_3_list";
    public final String BED_BATH_SQFT = "bed_bath_sqft_list";
    public final String BPROPERTY_TYPE = "property_type_list";
    public final String SAVE_BTN = "save_bottom_btn";
    public final String MAP_BTN ="list_bottom_btn";
    public final String SINGLE_CELL_RESULT = "result_cell_layout_list";
    public final String SEARCH_TOTAL = "total_result_bottom";
    public final String SEARCH_CONTEXT = "sarch_msg_bottom";


    CommonLib commonlibSrp = new CommonLib();

    public Integer getNumberOfSearchResults() throws UiObjectNotFoundException {
         UiObject searchResultTotal = commonlibSrp.getObjectByDescription(SEARCH_TOTAL);
         String[] getTotalNumber = searchResultTotal.getText().split(" ");
         String splitedResult = getTotalNumber[0];
         int n = Integer.parseInt(splitedResult);
         return n;
    }
    public Integer countNumberOfResultInList() throws UiObjectNotFoundException {
        UiObject searchResultTotal = commonlibSrp.getObjectByDescription(PRICE_VIEW);
        String[] getTotalNumber = searchResultTotal.getText().split(" ");
        String splitedResult = getTotalNumber[0];
        int n = Integer.parseInt(splitedResult);
        return n;
    }

    public String[] getSearchContext() throws UiObjectNotFoundException {
        UiObject searchContent = commonlibSrp.getObjectByDescription(SEARCH_CONTEXT);
        String[] getTotalNumber = searchContent.getText().split(" ");
        String db = getTotalNumber[2];
        String bt = getTotalNumber[4];
        String[] returner = new String[2];
        returner[0] = db;
        returner[1] = bt;
        return returner;
    }

    public char getDollarSignFormPrice() throws NullPointerException, UiObjectNotFoundException {
        UiObject singleCell = new UiObject (new UiSelector().description(SINGLE_CELL_RESULT));
        UiObject price = singleCell.getChild(new UiSelector().description(PRICE_VIEW));
        String dollarSign = price.getText();
        char c = dollarSign.charAt(0);
        return c;
    }
}

