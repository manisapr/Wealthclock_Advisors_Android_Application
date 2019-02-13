package android.wealthclockadvisors.app.wealthclockadvisors.model;

public class PortfolioDetailsModel {
    private String NetInvestment;
    private String CurrentMarketValue;
    private String XirrValue;

    public PortfolioDetailsModel() {

    }

    public String getNetInvestment() {
        return NetInvestment;
    }

    public void setNetInvestment(String netInvestment) {
        NetInvestment = netInvestment;
    }

    public String getCurrentMarketValue() {
        return CurrentMarketValue;
    }

    public void setCurrentMarketValue(String currentMarketValue) {
        CurrentMarketValue = currentMarketValue;
    }

    public String getXirrValue() {
        return XirrValue;
    }

    public void setXirrValue(String xirrValue) {
        XirrValue = xirrValue;
    }
}
