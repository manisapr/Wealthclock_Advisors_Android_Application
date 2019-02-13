package android.wealthclockadvisors.app.wealthclockadvisors.model;

public class DashBoardModel {
    private String userName;
    private String xirr;
    private String netInvestment;
    private String currentMarketValue;
    private String absoluteReturns;
    private String imagePath;

    public DashBoardModel() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getXirr() {
        return xirr;
    }

    public void setXirr(String xirr) {
        this.xirr = xirr;
    }

    public String getNetInvestment() {
        return netInvestment;
    }

    public void setNetInvestment(String netInvestment) {
        this.netInvestment = netInvestment;
    }

    public String getCurrentMarketValue() {
        return currentMarketValue;
    }

    public void setCurrentMarketValue(String currentMarketValue) {
        this.currentMarketValue = currentMarketValue;
    }

    public String getAbsoluteReturns() {
        return absoluteReturns;
    }

    public void setAbsoluteReturns(String absoluteReturns) {
        this.absoluteReturns = absoluteReturns;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
