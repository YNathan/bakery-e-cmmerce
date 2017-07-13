package Entity;

/**
 * Created by jacky on 18/11/2016.
 */
public class WebResponce {
    private ESuccessFailed eSuccessFailed;
    private String szReason;
    private String szMoreDetails;

    public WebResponce() {
        this.eSuccessFailed = ESuccessFailed.SUCCESS;
        this.szReason = "Operation Success";
    }

    public WebResponce(ESuccessFailed eSuccessFailed, String szReason) {
        this.eSuccessFailed = eSuccessFailed;
        this.szReason = szReason;
    }

    public String getMoreDetails() {
        return szMoreDetails;
    }

    public void setMoreDetails(String szMoreDetails) {
        this.szMoreDetails = szMoreDetails;
    }

    public ESuccessFailed getSuccessFailed() {
        return eSuccessFailed;
    }

    public void seteSuccessFailed(ESuccessFailed eSuccessFailed) {
        this.eSuccessFailed = eSuccessFailed;
    }

    public String getReason() {
        return szReason;
    }

    public void setReason(String szReason) {
        this.szReason = szReason;
    }

    @Override
    public String toString() {
        return "WebResponce{" +
                "eSuccessFailed=" + eSuccessFailed +
                ", szReason='" + szReason + '\'' +
                ", szMoreDetails='" + szMoreDetails + '\'' +
                '}';
    }

    public String toJson() {
        return "{ \"WebResponce\":[ {" +
                "\"SuccessFailed\":\"" + eSuccessFailed +"\""+
                ",\"Reason\":\"" + szReason +"\""+
                ",\"MoreDetails\":\"" + szMoreDetails +"\""+
                "} ]}";
    }
}
