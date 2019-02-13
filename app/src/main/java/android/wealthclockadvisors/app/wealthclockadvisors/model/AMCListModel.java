package android.wealthclockadvisors.app.wealthclockadvisors.model;

public class AMCListModel {
    private String AmcSchemeName;
    private String AmcSchemeCode;

    public AMCListModel() {

    }

    public String getAmcSchemeName() {
        return AmcSchemeName;
    }

    public void setAmcSchemeName(String amcSchemeName) {
        AmcSchemeName = amcSchemeName;
    }

    public String getAmcSchemeCode() {
        return AmcSchemeCode;
    }

    public void setAmcSchemeCode(String amcSchemeCode) {
        AmcSchemeCode = amcSchemeCode;
    }
}
