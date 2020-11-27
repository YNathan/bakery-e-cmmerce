package Entity;

/**
 * Created by jacky on 09/09/2017.
 */
public class Product {
    int nProductId = -1;
    String szCompanyName;
    String szNameToDsiplay;
    float fMinPrice;
    float fMaxPrice;
    String[] sTags;
    float[] fSizes;
    String[] sColors;
    int nSellerId;
    int[] nBuyersId;

    public Product(int nProductId, String szCompanyName, String szNameToDsiplay, float fMinPrice, float fMaxPrice, String[] sTags, float[] fSizes, String[] sColors, int nSellerId, int[] nBuyersId) {
        this.nProductId = nProductId;
        this.szCompanyName = szCompanyName;
        this.szNameToDsiplay = szNameToDsiplay;
        this.fMinPrice = fMinPrice;
        this.fMaxPrice = fMaxPrice;
        this.sTags = sTags;
        this.fSizes = fSizes;
        this.sColors = sColors;
        this.nSellerId = nSellerId;
        this.nBuyersId = nBuyersId;
    }

    public Product() {
    }

    public int getProductId() {
        return nProductId;
    }

    public void setProductId(int nProductId) {
        this.nProductId = nProductId;
    }

    public String getCompanyName() {
        return szCompanyName;
    }

    public void setCompanyName(String szCompanyName) {
        this.szCompanyName = szCompanyName;
    }

    public String getNameToDsiplay() {
        return szNameToDsiplay;
    }

    public void setNameToDsiplay(String szNameToDsiplay) {
        this.szNameToDsiplay = szNameToDsiplay;
    }

    public float getMinPrice() {
        return fMinPrice;
    }

    public void setMinPrice(float fMinPrice) {
        this.fMinPrice = fMinPrice;
    }

    public float getMaxPrice() {
        return fMaxPrice;
    }

    public void setMaxPrice(float fMaxPrice) {
        this.fMaxPrice = fMaxPrice;
    }

    public String[] getTags() {
        return sTags;
    }

    public void setTags(String[] sTags) {
        this.sTags = sTags;
    }

    public float[] getSizes() {
        return fSizes;
    }

    public void setSizes(float[] fSizes) {
        this.fSizes = fSizes;
    }

    public String[] getColors() {
        return sColors;
    }

    public void setColors(String[] sColors) {
        this.sColors = sColors;
    }

    public int getSellerId() {
        return nSellerId;
    }

    public void setSellerId(int nSellerId) {
        this.nSellerId = nSellerId;
    }

    public int[] getBuyersId() {
        return nBuyersId;
    }

    public void setBuyersId(int[] nBuyersId) {
        this.nBuyersId = nBuyersId;
    }
}
