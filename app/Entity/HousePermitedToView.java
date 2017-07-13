package Entity;

/**
 * Created by jacky on 07/01/2017.
 */
public class HousePermitedToView {
    private int nHouseId;
    private String szHouseAdress;
    boolean bIsPermitedToView;

    public HousePermitedToView(int nHouseId, String szHouseAdress, boolean bIsPermitedToView) {
        this.nHouseId = nHouseId;
        this.szHouseAdress = szHouseAdress;
        this.bIsPermitedToView = bIsPermitedToView;
    }

    public HousePermitedToView() {
    }

    public int getHouseId() {
        return nHouseId;
    }

    public void setHouseId(int nHouseId) {
        this.nHouseId = nHouseId;
    }

    public String getHouseAdress() {
        return szHouseAdress;
    }

    public void setHouseAdress(String szHouseAdress) {
        this.szHouseAdress = szHouseAdress;
    }

    public boolean getIsPermitedToView() {
        return bIsPermitedToView;
    }

    public void setIsPermitedToView(boolean bIsPermitedToView) {
        this.bIsPermitedToView = bIsPermitedToView;
    }

    @Override
    public String toString() {
        return "HousePermitedToView{" +
                "HouseId=" + nHouseId +
                ", HouseAdress='" + szHouseAdress + '\'' +
                ", PermitedToView=" + bIsPermitedToView +
                '}';
    }
}
