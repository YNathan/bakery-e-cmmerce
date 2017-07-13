package Entity;

/**
 * Created by jacky on 09/07/2017.
 */
public class FoodEntity {
    private int foodId;
    private String foodName;
    private float price;
    private boolean isParve;
    private boolean isHalavi;
    private boolean isBassari;
    private boolean isVegetarian;
    private boolean isVegan;
    private boolean isInKeytring;
    private boolean isInSucurSal;
    private String szDescryption;

    public FoodEntity() {
    }

    public FoodEntity(int foodId, String foodName, float price, boolean isParve, boolean isHalavi, boolean isBassari, boolean isVegetarian, boolean isVegan, boolean isInKeytring, boolean isInSucurSal) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.price = price;
        this.isParve = isParve;
        this.isHalavi = isHalavi;
        this.isBassari = isBassari;
        this.isVegetarian = isVegetarian;
        this.isVegan = isVegan;
        this.isInKeytring = isInKeytring;
        this.isInSucurSal = isInSucurSal;
    }
    public FoodEntity(int foodId, String foodName, float price, boolean isParve, boolean isHalavi, boolean isBassari, boolean isVegetarian, boolean isVegan, boolean isInKeytring, boolean isInSucurSal, String szDescription) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.price = price;
        this.isParve = isParve;
        this.isHalavi = isHalavi;
        this.isBassari = isBassari;
        this.isVegetarian = isVegetarian;
        this.isVegan = isVegan;
        this.isInKeytring = isInKeytring;
        this.isInSucurSal = isInSucurSal;
        this.szDescryption = szDescription;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isParve() {
        return isParve;
    }

    public void setParve(boolean parve) {
        isParve = parve;
    }

    public boolean isHalavi() {
        return isHalavi;
    }

    public void setHalavi(boolean halavi) {
        isHalavi = halavi;
    }

    public boolean isBassari() {
        return isBassari;
    }

    public void setBassari(boolean bassari) {
        isBassari = bassari;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    public boolean isVegan() {
        return isVegan;
    }

    public void setVegan(boolean vegan) {
        isVegan = vegan;
    }

    public boolean isInKeytring() {
        return isInKeytring;
    }

    public void setInKeytring(boolean isInKeytring) {
        isInKeytring = isInKeytring;
    }

    public boolean isInSucurSal() {
        return isInSucurSal;
    }

    public void setInSucurSal(boolean inSucurSal) {
        isInSucurSal = inSucurSal;
    }

    public String getDescryption() {
        return szDescryption;
    }

    public void setDescryption(String szDescryption) {
        this.szDescryption = szDescryption;
    }



    public static boolean setBoolean(int nTrueOrFalse) {
        boolean isTrue = false;
        if (nTrueOrFalse == 0) {
            isTrue = false;
        } else if (nTrueOrFalse == 1) {
            isTrue = true;
        }
        return isTrue;
    }

    public static int getBoolean(boolean bTrueOrFalse) {
        int nIsTrue = 0;
        if (bTrueOrFalse == false) {
            nIsTrue = 0;
        } else if (bTrueOrFalse == true) {
            nIsTrue = 1;
        }
        return nIsTrue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FoodEntity that = (FoodEntity) o;

        if (foodId != that.foodId) return false;
        if (Float.compare(that.price, price) != 0) return false;
        if (isParve != that.isParve) return false;
        if (isHalavi != that.isHalavi) return false;
        if (isBassari != that.isBassari) return false;
        if (isVegetarian != that.isVegetarian) return false;
        if (isVegan != that.isVegan) return false;
        if (isInKeytring != that.isInKeytring) return false;
        if (isInSucurSal != that.isInSucurSal) return false;
        if (foodName != null ? !foodName.equals(that.foodName) : that.foodName != null) return false;
        return szDescryption != null ? szDescryption.equals(that.szDescryption) : that.szDescryption == null;
    }

    @Override
    public int hashCode() {
        int result = foodId;
        result = 31 * result + (foodName != null ? foodName.hashCode() : 0);
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + (isParve ? 1 : 0);
        result = 31 * result + (isHalavi ? 1 : 0);
        result = 31 * result + (isBassari ? 1 : 0);
        result = 31 * result + (isVegetarian ? 1 : 0);
        result = 31 * result + (isVegan ? 1 : 0);
        result = 31 * result + (isInKeytring ? 1 : 0);
        result = 31 * result + (isInSucurSal ? 1 : 0);
        result = 31 * result + (szDescryption != null ? szDescryption.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FoodEntity{" +
                "foodId=" + foodId +
                ", foodName='" + foodName + '\'' +
                ", price=" + price +
                ", isParve=" + isParve +
                ", isHalavi=" + isHalavi +
                ", isBassari=" + isBassari +
                ", isVegetarian=" + isVegetarian +
                ", isVegan=" + isVegan +
                ", isInKeytring=" + isInKeytring +
                ", isInSucurSal=" + isInSucurSal +
                ", Descryption='" + szDescryption + '\'' +
                '}';
    }
}
