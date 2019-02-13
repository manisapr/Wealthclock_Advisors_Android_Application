package android.wealthclockadvisors.app.wealthclockadvisors.constant;

public class APIConstant {
    /*public static final String BASE_URL = "http://demo.wealthclockadvisors.com/MobileAppApi" ;*/
    public static final String BASE_URL =  "https://www.wealthclockadvisors.com/API/MobileAppApi";
    public static final String LOGIN_URL = "/MobileLogin" ;
    public static final String DASBOARD_URL = "/GetDashBoardDataByClientCode" ;
    public static final String ACCOUNT_URL = "/GetAccountDetailsByEmailForMobile" ;
    public static final String PORTFOLIO_URL = "/GetSchemeDetailsByClientCodeForPortFolio";
    public static final String FUND_DETAILS_URL = "/GetIndvSchemeDetailsForMobile";
    public static final String GET_AMC_LIST_URL = "/GetAmcList";
    public static final String FUND_TYPE_URL = "/GetFundTypeFolio";
    public static final String FUND_NAME_LIST = "/GetFundNameForMobileApp" ;
    public static final String GET_MINIMUM_FUND_AMOUNT = "/GetMinAmountForMobileApp";
    public static final String PURCHASE_WITHOUT_FOLIO = "https://www.wealthclockadvisors.com/API/Order/NormalOrder";
    public static final String GET_SCHEME_CODE_FOR_PURCHASE = "/GetSchemeCode" ;
    public static final String PURCHASE_WITH_FOLIO = "https://www.wealthclockadvisors.com/API/Order/AdditionalOrder";
    public static final String REDEEM_URL = "/RedempSchemeList" ;
    public static final String REDEEM_FOLIO_URL = "/GetFolio" ;
    public static final String REDEEM_DETAILS = "/GetRedemptionDetails";
    public static final String REDEEM_AMOUNT_URL = "https://www.wealthclockadvisors.com/API/Order/RedeemFund";
    public static final String FAMILY_DETAILS_URL = "/GetFamilyPortFolioDetailsByClientCode";
    public static final String SIP_DATE_URL = "/GetMinAmtAndDateForSip";
    public static final String SIP = "https://www.wealthclockadvisors.com/API/Order/SipOrder";
    public static final String MANDATE_ID = "/GetSipStatus";
    public static final String FUND_GOAL_PLANNER = "/GoalScheme";
    public static final String REDEEM_IMPS = "https://www.wealthclockadvisors.com/API/IMPS/ImpsRedemption";
    public static final String MULTI_FUND_SIP = "https://www.wealthclockadvisors.com/API/MultipleOrder/SipOrder";
    public static final String MULTI_SIP_DATE_URL ="/GetDateForGoalPlanner" ;
}
