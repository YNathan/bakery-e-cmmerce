package Entity;

/**
 * Created by jacky on 18/11/2016.
 */
public class House {
    private int nHouseId = -1;
    private String szState = "Newark";
    private String szCity = "SNull";
    private String szStreet = "SNull";
    private int nHouseNumber = -1;
    private int nZipCode = -1;
    private EHouseKind eHouseKind = Entity.EHouseKind.FAMILLY;
    private int nNumberOfRooms = -1;
    private int nNumberOfLivingRooms = -1;
    private int nNumberOfKitchens = -1;
    private int nNumberOfBedrooms = -1;
    private int nNumberOfBathrooms = -1;
    private ELocationKind eLocationKind = ELocationKind.WHITE;
    private String szComments = "SNull";
    private double dPurchasePrice = -1;
    private double dTreatmentFees = -1;
    private double dRenovationFeesForSale = -1;
    private double dRenovationFeesForRenting = -1;
    private double dDiversFees = -1;

    public House(int nHouseId, String szState, String szCity, String szStreet, int nHouseNumber) {
        this.nHouseId = nHouseId;
        this.szState = szState;
        this.szCity = szCity;
        this.szStreet = szStreet;
        this.nHouseNumber = nHouseNumber;
    }

    public House(int nHouseId, String szState, String szCity, String szStreet, int nHouseNumber, int m_nZipCode, Entity.EHouseKind EHouseKind, int nNumberOfRooms, int nNumberOfLivingRooms, int nNumberOfKitchens, int nNumberOfBedrooms, int numberOfBathrooms, ELocationKind locationKind, String szComments, double dPurchasePrice, double dTreatmentFees, double dRenovationFeesForSale, double dRenovationFeesForRenting, double dDiversFees) {
        this.nHouseId = nHouseId;
        this.szState = szState;
        this.szCity = szCity;
        this.szStreet = szStreet;
        this.nHouseNumber = nHouseNumber;
        this.eHouseKind = EHouseKind;
        this.nNumberOfRooms = nNumberOfRooms;
        this.nNumberOfLivingRooms = nNumberOfLivingRooms;
        this.nNumberOfKitchens = nNumberOfKitchens;
        this.nNumberOfBedrooms = nNumberOfBedrooms;
        this.nNumberOfBathrooms = numberOfBathrooms;
        this.eLocationKind = locationKind;
        this.szComments = szComments;
        this.dPurchasePrice = dPurchasePrice;
        this.dTreatmentFees = dTreatmentFees;
        this.dRenovationFeesForSale = dRenovationFeesForSale;
        this.dRenovationFeesForRenting = dRenovationFeesForRenting;
        this.dDiversFees = dDiversFees;
        this.nZipCode = m_nZipCode;
    }

    /***
     * Constractor for house details
     * @param nHouseId
     * @param szState
     * @param szCity
     * @param szStreet
     * @param nHouseNumber
     * @param EHouseKind
     * @param nNumberOfRooms
     * @param nNumberOfLivingRooms
     * @param nNumberOfKitchens
     * @param nNumberOfBedrooms
     * @param numberOfBathrooms
     * @param locationKind
     * @param szComments
     */
    public House(int nHouseId, String szState, String szCity, String szStreet, int nHouseNumber, Entity.EHouseKind EHouseKind, int nNumberOfRooms, int nNumberOfLivingRooms, int nNumberOfKitchens, int nNumberOfBedrooms, int numberOfBathrooms, ELocationKind locationKind, String szComments) {
        this.nHouseId = nHouseId;
        this.szState = szState;
        this.szCity = szCity;
        this.szStreet = szStreet;
        this.nHouseNumber = nHouseNumber;
        this.eHouseKind = EHouseKind;
        this.nNumberOfRooms = nNumberOfRooms;
        this.nNumberOfLivingRooms = nNumberOfLivingRooms;
        this.nNumberOfKitchens = nNumberOfKitchens;
        this.nNumberOfBedrooms = nNumberOfBedrooms;
        this.nNumberOfBathrooms = numberOfBathrooms;
        this.eLocationKind = locationKind;
        this.szComments = szComments;
    }

    public House() {
    }

    public int getHouseId() {
        return nHouseId;
    }

    public void setHouseId(int nHouseId) {
        this.nHouseId = nHouseId;
    }

    public String getState() {
        return szState;
    }

    public void setState(String szState) {
        this.szState = szState;
    }

    public String getCity() {
        return szCity;
    }

    public void setCity(String szCity) {
        this.szCity = szCity;
    }

    public String getStreet() {
        return szStreet;
    }

    public void setStreet(String szStreet) {
        this.szStreet = szStreet;
    }

    public int getHouseNumber() {
        return nHouseNumber;
    }

    public void setHouseNumber(int nHouseNumber) {
        this.nHouseNumber = nHouseNumber;
    }

    public EHouseKind getHouseKind() {
        return eHouseKind;
    }

    public void setHouseKind(EHouseKind ehouseKind) {
        this.eHouseKind = ehouseKind;
    }

    public int getNumberOfRooms() {
        return nNumberOfRooms;
    }

    public void setNumberOfRooms(int nNumberOfRooms) {
        this.nNumberOfRooms = nNumberOfRooms;
    }

    public int getNumberOfLivingRooms() {
        return nNumberOfLivingRooms;
    }

    public void setNumberOfLivingRooms(int nNumberOfLivingRooms) {
        this.nNumberOfLivingRooms = nNumberOfLivingRooms;
    }

    public int getNumberOfKitchens() {
        return nNumberOfKitchens;
    }

    public void setNumberOfKitchens(int nNumberOfKitchens) {
        this.nNumberOfKitchens = nNumberOfKitchens;
    }

    public int getNumberOfBedrooms() {
        return nNumberOfBedrooms;
    }

    public void setNumberOfBedrooms(int nNumberOfBedrooms) {
        this.nNumberOfBedrooms = nNumberOfBedrooms;
    }

    public int getNumberOfBathrooms() {
        return nNumberOfBathrooms;
    }

    public void setNumberOfBathrooms(int nNumberOfBathrooms) {
        this.nNumberOfBathrooms = nNumberOfBathrooms;
    }

    public ELocationKind getLocationKind() {
        return eLocationKind;
    }

    public void setLocationKind(ELocationKind eLocationKind) {
        this.eLocationKind = eLocationKind;
    }

    public String getComments() {
        return szComments;
    }

    public void setComments(String szComments) {
        this.szComments = szComments;
    }

    public double getPurchasePrice() {
        return dPurchasePrice;
    }

    public void setPurchasePrice(double dPurchasePrice) {
        this.dPurchasePrice = dPurchasePrice;
    }

    public double getTreatmentFees() {
        return dTreatmentFees;
    }

    public void setTreatmentFees(double dTreatmentFees) {
        this.dTreatmentFees = dTreatmentFees;
    }

    public double getRenovationFeesForSale() {
        return dRenovationFeesForSale;
    }

    public void setRenovationFeesForSale(double dRenovationFees) {
        this.dRenovationFeesForSale = dRenovationFees;
    }

    public double getDiversFees() {
        return dDiversFees;
    }

    public void setDiversFees(double dDiversFees) {
        this.dDiversFees = dDiversFees;
    }

    public double getRenovationFeesForRenting() {
        return dRenovationFeesForRenting;
    }

    public void setRenovationFeesForRenting(double dRenovationFeesForRenting) {
        this.dRenovationFeesForRenting = dRenovationFeesForRenting;
    }

    public int getZipCode() {
        return nZipCode;
    }

    public void setZipCode(int nZipCode) {
        this.nZipCode = nZipCode;
    }


    public String GetHouseAddessForLazy() {
        return this.szState + " " + this.szCity + " " + this.szStreet + " " + this.nHouseNumber;
    }

    @Override
    public String toString() {
        return "House{" +
                "nHouseId=" + nHouseId +
                ", szState='" + szState + '\'' +
                ", szCity='" + szCity + '\'' +
                ", szStreet='" + szStreet + '\'' +
                ", nHouseNumber=" + nHouseNumber +
                ", EHouseKind=" + eHouseKind +
                ", nNumberOfRooms=" + nNumberOfRooms +
                ", nNumberOfLivingRooms=" + nNumberOfLivingRooms +
                ", nNumberOfKitchens=" + nNumberOfKitchens +
                ", nNumberOfBedrooms=" + nNumberOfBedrooms +
                ", nNumberOfBathrooms=" + nNumberOfBathrooms +
                ", eLocationKind=" + eLocationKind +
                ", szComments='" + szComments + '\'' +
                ", dPurchasePrice=" + dPurchasePrice +
                ", dTreatmentFees=" + dTreatmentFees +
                ", dRenovationFeesForSale=" + dRenovationFeesForSale +
                ", RenovationFeesForRenting=" + dRenovationFeesForRenting +
                ", dDiversFees=" + dDiversFees +
                ", ZipCode=" + nZipCode +
                '}';
    }

    public String toStringMailFormat() {
        return "********************shamayim nadlan********************\n" +
                "\n HouseId = " + nHouseId +
                "\n State = " + szState +
                "\n City =" + szCity +
                "\n Street = " + szStreet +
                "\n HouseNumber = " + nHouseNumber +
                "\n HouseKind = " + eHouseKind +
                "\n NumberOfRooms = " + nNumberOfRooms +
                "\n NumberOfLivingRooms = " + nNumberOfLivingRooms +
                "\n NumberOfKitchens = " + nNumberOfKitchens +
                "\n NumberOfBedrooms = " + nNumberOfBedrooms +
                "\n NumberOfBathrooms = " + nNumberOfBathrooms +
                "\n LocationKind=" + eLocationKind +
                "\n Comments =" + szComments +
                "\n PurchasePrice = " + dPurchasePrice +
                "\n TreatmentFees = " + dTreatmentFees +
                "\n RenovationFeesForSale = " + dRenovationFeesForSale +
                "\n RenovationFeesForRenting = " + dRenovationFeesForRenting +
                "\n DiversFees=" + dDiversFees +
                "\n ZipCode=" + nZipCode
                ;
    }
}
