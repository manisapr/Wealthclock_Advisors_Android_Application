package android.wealthclockadvisors.app.wealthclockadvisors.model;

public class ReferralDetails {
    String id,ClientCode,Email,RefCode,LinksCount,ShortCode,SentCount;

    public String getClientCode() {
        return ClientCode;
    }

    public void setClientCode(String clientCode) {
        ClientCode = clientCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getRefCode() {
        return RefCode;
    }

    public void setRefCode(String refCode) {
        RefCode = refCode;
    }

    public String getLinksCount() {
        return LinksCount;
    }

    public void setLinksCount(String linksCount) {
        LinksCount = linksCount;
    }

    public String getShortCode() {
        return ShortCode;
    }

    public String setShortCode(String shortCode) {
        ShortCode = shortCode;
        return shortCode;
    }

    public String getSentCount() {
        return SentCount;
    }

    public void setSentCount(String sentCount) {
        SentCount = sentCount;
    }
}
