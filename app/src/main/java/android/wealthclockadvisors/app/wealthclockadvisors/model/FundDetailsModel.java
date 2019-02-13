package android.wealthclockadvisors.app.wealthclockadvisors.model;

public class FundDetailsModel {
    private String totalInvest;
    private String currentValue;
    private String unitBalance;
    private String category;
    private String schemecode;
    private String folioNo;
    private String navDetails;
    private String absoluteReturn;
    private String Return_percent;
    private String XIRR;
    private String schemeName;
    private String GoalType;
    private String dividendReunvestment;

    public FundDetailsModel() {

    }

    public String getTotalInvest() {
        return totalInvest;
    }

    public void setTotalInvest(String totalInvest) {
        this.totalInvest = totalInvest;
    }

    public String getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(String currentValue) {
        this.currentValue = currentValue;
    }

    public String getUnitBalance() {
        return unitBalance;
    }

    public void setUnitBalance(String unitBalance) {
        this.unitBalance = unitBalance;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSchemecode() {
        return schemecode;
    }

    public void setSchemecode(String schemecode) {
        this.schemecode = schemecode;
    }

    public String getFolioNo() {
        return folioNo;
    }

    public void setFolioNo(String folioNo) {
        this.folioNo = folioNo;
    }

    public String getNavDetails() {
        return navDetails;
    }

    public void setNavDetails(String navDetails) {
        this.navDetails = navDetails;
    }

    public String getAbsoluteReturn() {
        return absoluteReturn;
    }

    public void setAbsoluteReturn(String absoluteReturn) {
        this.absoluteReturn = absoluteReturn;
    }

    public String getReturn_percent() {
        return Return_percent;
    }

    public void setReturn_percent(String return_percent) {
        Return_percent = return_percent;
    }

    public String getXIRR() {
        return XIRR;
    }

    public void setXIRR(String XIRR) {
        this.XIRR = XIRR;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getGoalType() {
        return GoalType;
    }

    public void setGoalType(String goalType) {
        GoalType = goalType;
    }

    public String getDividendReunvestment() {
        return dividendReunvestment;
    }

    public void setDividendReunvestment(String dividendReunvestment) {
        this.dividendReunvestment = dividendReunvestment;
    }
}
