package android.wealthclockadvisors.app.wealthclockadvisors.model;

public class FamilyMemberModel {
    private String netInvestment;
    private String currentmarketValue;
    private String fundCurrentValue;
    private String name;
    private String imagepath;

    public FamilyMemberModel() {

    }

    public String getNetInvestment() {
        return netInvestment;
    }

    public void setNetInvestment(String netInvestment) {
        this.netInvestment = netInvestment;
    }

    public String getCurrentmarketValue() {
        return currentmarketValue;
    }

    public void setCurrentmarketValue(String currentmarketValue) {
        this.currentmarketValue = currentmarketValue;
    }

    public String getFundCurrentValue() {
        return fundCurrentValue;
    }

    public void setFundCurrentValue(String fundCurrentValue) {
        this.fundCurrentValue = fundCurrentValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }
}
