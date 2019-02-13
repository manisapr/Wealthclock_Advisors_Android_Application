package android.wealthclockadvisors.app.wealthclockadvisors.model;

public class MultipleFundModel {
    private String FolioNo;

    private String OrderVal;

    private String SchemeCd;

    private String AmcCode;

    private String SchemeType;

    private String fundname;

    public String getFolioNo() {
        return FolioNo;
    }

    public void setFolioNo(String folioNo) {
        FolioNo = folioNo;
    }

    public String getOrderVal() {
        return OrderVal;
    }

    public void setOrderVal(String orderVal) {
        OrderVal = orderVal;
    }

    public String getSchemeCd() {
        return SchemeCd;
    }

    public void setSchemeCd(String schemeCd) {
        SchemeCd = schemeCd;
    }

    public String getAmcCode() {
        return AmcCode;
    }

    public void setAmcCode(String amcCode) {
        AmcCode = amcCode;
    }

    public String getSchemeType() {
        return SchemeType;
    }

    public void setSchemeType(String schemeType) {
        SchemeType = schemeType;
    }

    public String getFundname() {
        return fundname;
    }

    public void setFundname(String fundname) {
        this.fundname = fundname;
    }
}
