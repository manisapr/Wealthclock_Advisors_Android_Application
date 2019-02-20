package android.wealthclockadvisors.app.wealthclockadvisors.iinterface;

import android.content.Context;
import android.wealthclockadvisors.app.wealthclockadvisors.model.MutualFundDetailsforModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.OrderEntryModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.Top3Funds;
import android.wealthclockadvisors.app.wealthclockadvisors.model.User_DetailsForIMPS;
import android.wealthclockadvisors.app.wealthclockadvisors.model.xsipOrderEntryParamModel;

import java.util.ArrayList;

public interface ihandler {
    void sendRegistrationDetails(String name,String mob,String email,String pwd, Context context);
    void sendLoginData(String email,String password,Context context);
    void getDasBoardInfo(String clientcode,Context context);
    void getAccountDetails(String email,Context context);
    void getPortfolioData(String clientcode,Context context);
    void getFundDetails(String clientCode,String schemeCode,String foliono,Context context);
    void getFundNameList(Context context);
    void getFundType(String clientCode,String amcCode,Context context);
    void getfundNameAllList(String amccode,String schemetype,Context  context);
    void getMinimumAmountData(String schemename,String schemetype,String plan,Context context);
    void purchase(OrderEntryModel orderEntryModel, Context context);
    void getSchemeCode(MutualFundDetailsforModel mutualFundDetailsforModel, Context context);
    void getRedeemtionSchemeList(String clientcode,String type,Context context);
    void getRedeemFolioList(String clientCode,String schemeCode,Context context);
    void getRedemptionDetails(String clientCode,String schemeCode,String folio,Context context);
    void redeemFund(OrderEntryModel orderEntryModel,Context context);
    void getFamilyDetails(String clientcode,Context context);
    void sipDateApi(OrderEntryModel orderEntryModel,Context context);
    void sip(xsipOrderEntryParamModel xsipOrderEntryParamModel, Context context);
    void getMandatetId(xsipOrderEntryParamModel xsipOrderEntryParamModel,Context context);
    void getFundListInGoalPlanner(Top3Funds top3Funds, Context context);
    void redeemIMPS(User_DetailsForIMPS user_detailsForIMPS, Context context);
    void multifundSip(ArrayList<Top3Funds> top3FundsArrayList,ArrayList<Top3Funds> list,String mandateId,String d1,String d2,String d3 ,String type,String paymentmode,Context context);
    void getMultiFundFolioList(Top3Funds arrayList,String n,Context context);
    void  dateformultifund(String schemecd,Context context);
    void  dateformultifund1(String schemecd,Context context);
    void  dateformultifund2(String schemecd,Context context);

}
