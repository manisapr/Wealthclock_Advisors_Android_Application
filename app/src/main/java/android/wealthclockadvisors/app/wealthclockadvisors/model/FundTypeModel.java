package android.wealthclockadvisors.app.wealthclockadvisors.model;

public class FundTypeModel {
    private String schemType;
    private String folioNo;
    private String SchemeCode;
    private String SchemeName;

    public FundTypeModel() {

    }

    public String getSchemType() {
        return schemType;
    }

    public void setSchemType(String schemType) {
        this.schemType = schemType;
    }

    public String getFolioNo() {
        return folioNo;
    }

    public void setFolioNo(String folioNo) {
        this.folioNo = folioNo;
    }

    public String getSchemeCode() {
        return SchemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        SchemeCode = schemeCode;
    }

    public String getSchemeName() {
        return SchemeName;
    }

    public void setSchemeName(String schemeName) {
        SchemeName = schemeName;
    }
}
