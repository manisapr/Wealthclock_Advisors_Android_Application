package android.wealthclockadvisors.app.wealthclockadvisors.handler;

import android.content.Context;
import android.wealthclockadvisors.app.wealthclockadvisors.controller.httpController;
import android.wealthclockadvisors.app.wealthclockadvisors.iinterface.ihandler;
import android.wealthclockadvisors.app.wealthclockadvisors.iinterface.ihttpResultHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.model.MutualFundDetailsforModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.OnboardDatainfo;
import android.wealthclockadvisors.app.wealthclockadvisors.model.OrderEntryModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.Top3Funds;
import android.wealthclockadvisors.app.wealthclockadvisors.model.User_DetailsForIMPS;
import android.wealthclockadvisors.app.wealthclockadvisors.model.xsipOrderEntryParamModel;

import java.io.File;
import java.util.ArrayList;

public class UserHandler implements ihandler {

    private static UserHandler _userHandler;
    private httpController _HttpController;
    private ihttpResultHandler _ihttpResultHandler;

    public ihttpResultHandler get_ihttpResultHandler() {
        return _ihttpResultHandler;
    }

    public void set_ihttpResultHandler(ihttpResultHandler _ihttpResultHandler) {
        this._ihttpResultHandler = _ihttpResultHandler;
    }

    public static UserHandler getInstance()
    {
        if(_userHandler == null)
            _userHandler =  new UserHandler();
        return _userHandler;
    }

    @Override
    public void sendRegistrationDetails(String name, String mob, String email, String pwd, Context context) {
        _HttpController = httpController.getInstance();
        _HttpController.set_iHttpResultHandler(_ihttpResultHandler);
        _HttpController.sendRegistrationDetails(name,mob,email,pwd,context);
    }

    @Override
    public void sendLoginData(String email, String password, Context context) {
        _HttpController = httpController.getInstance();
        _HttpController.set_iHttpResultHandler(_ihttpResultHandler);
        _HttpController.sendLoginData(email,password,context);
    }

    @Override
    public void getDasBoardInfo(String clientcode, Context context) {
        _HttpController = httpController.getInstance();
        _HttpController.set_iHttpResultHandler(_ihttpResultHandler);
        _HttpController.getDasBoardInfo(clientcode,context);
    }

    @Override
    public void getAccountDetails(String email, Context context) {
        _HttpController = httpController.getInstance();
        _HttpController.set_iHttpResultHandler(_ihttpResultHandler);
        _HttpController.getAccountDetails(email,context);
    }

    @Override
    public void getPortfolioData(String clientcode, Context context) {
        _HttpController = httpController.getInstance();
        _HttpController.set_iHttpResultHandler(_ihttpResultHandler);
        _HttpController.getPortfolioData(clientcode,context);
    }

    @Override
    public void getFundDetails(String clientCode, String schemeCode, String foliono, Context context) {
        _HttpController = httpController.getInstance();
        _HttpController.set_iHttpResultHandler(_ihttpResultHandler);
        _HttpController.getFundDetails(clientCode,schemeCode,foliono,context);
    }

    @Override
    public void getFundNameList(Context context) {
        _HttpController = httpController.getInstance();
        _HttpController.set_iHttpResultHandler(_ihttpResultHandler);
        _HttpController.getFundNameList(context);
    }

    @Override
    public void getFundType(String clientCode, String amcCode, Context context) {
        _HttpController = httpController.getInstance();
        _HttpController.set_iHttpResultHandler(_ihttpResultHandler);
        _HttpController.getFundType(clientCode,amcCode,context);
    }

    @Override
    public void getfundNameAllList(String amccode, String schemetype, Context context) {
        _HttpController = httpController.getInstance();
        _HttpController.set_iHttpResultHandler(_ihttpResultHandler);
        _HttpController.getfundNameAllList(amccode,schemetype,context);
    }

    @Override
    public void getMinimumAmountData(String schemename, String schemetype, String plan,String clientcode, Context context) {
        _HttpController = httpController.getInstance();
        _HttpController.set_iHttpResultHandler(_ihttpResultHandler);
        _HttpController.getMinimumAmountData(schemename,schemetype,plan,clientcode,context);
    }

    @Override
    public void purchase(OrderEntryModel orderEntryModel, Context context) {
        _HttpController = httpController.getInstance();
        _HttpController.set_iHttpResultHandler(_ihttpResultHandler);
        _HttpController.purchase(orderEntryModel,context);
    }

    @Override
    public void getSchemeCode(MutualFundDetailsforModel mutualFundDetailsforModel, Context context) {
        _HttpController = httpController.getInstance();
        _HttpController.set_iHttpResultHandler(_ihttpResultHandler);
        _HttpController.getSchemeCode(mutualFundDetailsforModel,context);
    }

    @Override
    public void getRedeemtionSchemeList(String clientcode,String type ,Context context) {
        _HttpController = httpController.getInstance();
        _HttpController.set_iHttpResultHandler(_ihttpResultHandler);
        _HttpController.getRedeemtionSchemeList(clientcode,type,context);
    }

    @Override
    public void getRedeemFolioList(String clientCode, String schemeCode, Context context) {
        _HttpController = httpController.getInstance();
        _HttpController.set_iHttpResultHandler(_ihttpResultHandler);
        _HttpController.getRedeemFolioList(clientCode,schemeCode,context);
    }

    @Override
    public void getRedemptionDetails(String clientCode, String schemeCode, String folio, Context context) {
        _HttpController = httpController.getInstance();
        _HttpController.set_iHttpResultHandler(_ihttpResultHandler);
        _HttpController.getRedemptionDetails(clientCode,schemeCode,folio,context);
    }

    @Override
    public void redeemFund(OrderEntryModel orderEntryModel, Context context) {
        _HttpController = httpController.getInstance();
        _HttpController.set_iHttpResultHandler(_ihttpResultHandler);
        _HttpController.redeemFund(orderEntryModel,context);
    }

    @Override
    public void getFamilyDetails(String clientcode, Context context) {
        _HttpController = httpController.getInstance();
        _HttpController.set_iHttpResultHandler(_ihttpResultHandler);
        _HttpController.getFamilyDetails(clientcode,context);
    }

    @Override
    public void sipDateApi(OrderEntryModel orderEntryModel, Context context) {
        _HttpController = httpController.getInstance();
        _HttpController.set_iHttpResultHandler(_ihttpResultHandler);
        _HttpController.sipDateApi(orderEntryModel,context);
    }

    @Override
    public void sip(xsipOrderEntryParamModel xsipOrderEntryParamModel, Context context) {
        _HttpController = httpController.getInstance();
        _HttpController.set_iHttpResultHandler(_ihttpResultHandler);
        _HttpController.sip(xsipOrderEntryParamModel,context);
    }

    @Override
    public void getMandatetId(xsipOrderEntryParamModel xsipOrderEntryParamModel, Context context) {
        _HttpController = httpController.getInstance();
        _HttpController.set_iHttpResultHandler(_ihttpResultHandler);
        _HttpController.getMandatetId(xsipOrderEntryParamModel,context);
    }

    @Override
    public void getFundListInGoalPlanner(Top3Funds top3Funds, Context context) {
        _HttpController = httpController.getInstance();
        _HttpController.set_iHttpResultHandler(_ihttpResultHandler);
        _HttpController.getFundListInGoalPlanner(top3Funds,context);
    }

    @Override
    public void redeemIMPS(User_DetailsForIMPS user_detailsForIMPS, Context context) {
        _HttpController = httpController.getInstance();
        _HttpController.set_iHttpResultHandler(_ihttpResultHandler);
        _HttpController.redeemIMPS(user_detailsForIMPS,context);
    }

    @Override
    public void multifundSip(ArrayList<Top3Funds> top3FundsArrayList, ArrayList<Top3Funds> list, String mandateId,String d1,String d2,String d3 ,String type,String paymentmode,Context context) {
        _HttpController = httpController.getInstance();
        _HttpController.set_iHttpResultHandler(_ihttpResultHandler);
        _HttpController.multifundSip(top3FundsArrayList,list,mandateId,d1,d2,d3,type,paymentmode,context);
    }


    @Override
    public void getMultiFundFolioList(Top3Funds arrayList, String n,Context context) {
        _HttpController = httpController.getInstance();
        _HttpController.set_iHttpResultHandler(_ihttpResultHandler);
        _HttpController.getMultiFundFolioList(arrayList,n,context);
    }

    @Override
    public void dateformultifund(String schemecd, Context context) {
        _HttpController = httpController.getInstance();
        _HttpController.set_iHttpResultHandler(_ihttpResultHandler);
        _HttpController.dateformultifund(schemecd,context);
    }

    @Override
    public void dateformultifund1(String schemecd, Context context) {
        _HttpController = httpController.getInstance();
        _HttpController.set_iHttpResultHandler(_ihttpResultHandler);
        _HttpController.dateformultifund1(schemecd,context);
    }

    @Override
    public void dateformultifund2(String schemecd, Context context) {
        _HttpController = httpController.getInstance();
        _HttpController.set_iHttpResultHandler(_ihttpResultHandler);
        _HttpController.dateformultifund2(schemecd,context);
    }

    @Override
    public void tickeregistration(String userId, File file, Context context) {
        _HttpController = httpController.getInstance();
        _HttpController.set_iHttpResultHandler(_ihttpResultHandler);
        _HttpController.tickeregistration(userId,file,context);
    }

    @Override
    public void kycdetails(String panno, Context context) {
        _HttpController = httpController.getInstance();
        _HttpController.set_iHttpResultHandler(_ihttpResultHandler);
        _HttpController.kycdetails(panno,context);
    }

    @Override
    public void sendIdentityToDbOnBoard(OnboardDatainfo datainfo, Context context) {
        _HttpController = httpController.getInstance();
        _HttpController.set_iHttpResultHandler(_ihttpResultHandler);
        _HttpController.sendIdentityToDbOnBoard(datainfo,context);
    }
}
