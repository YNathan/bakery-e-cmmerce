package Entity;

import akka.io.SslTlsSupport;

/**
 * Created by jacky on 18/11/2016.
 */
public class Dictionary {
    private String szHouseLanguage;
    private String nHouseId;
    private String szAddress;
    private String szState;
    private String szCity;
    private String szStreet;
    private String nHouseNumber;
    private String EHouseKind;
    private String nNumberOfRooms;
    private String nNumberOfLivingRooms;
    private String nNumberOfKitchens;
    private String nNumberOfBedrooms;
    private String nNumberOfBathrooms;
    private String eLocationKind;
    private String nScore;
    private String szComments;
    private String dPurchasePrice;
    private String dTreatmentFees;
    private String dRenovationFeesForSale;
    private String dRenovationFeesForRenting;
    private String dDiversFees;
    private String szGeneralHouseDetailes;
    private String szFinancialHouseDetailes;
    private String UploadHouseFiles;
    private String DragOrDropFilesHere;
    private String HouseTable;
    private String Menu;
    private String Information;
    private String House;
    private String ManageHouses;
    private String NewHouse;
    private String Copyright;
    private String WellcomToYourAccount;
    private String SelectAHouse;
    private String AreaOnTheMap;
    private String HousesList;
    private String Save;
    private String SendMeThisHouseToMyMail;
    private String PageDirection;
    private String Logout;
    private String SystemManager;
    private String HouseProfilePicture;

    private String UploadHousePictures;
    private String BackToTheCatalog;

    public Dictionary() {
    }

    public Dictionary(String szHouseLanguage, String nHouseId, String szAddress, String szState, String szCity, String szStreet, String nHouseNumber, String EHouseKind, String nNumberOfRooms, String nNumberOfLivingRooms, String nNumberOfKitchens, String nNumberOfBedrooms, String nNumberOfBathrooms, String eLocationKind, String nScore, String szComments, String dPurchasePrice, String dTreatmentFees, String dRenovationFeesForSale, String dRenovationFeesForRenting, String dDiversFees, String szGeneralHouseDetailes, String szFinancialHouseDetailes, String uploadHouseFiles, String dragOrDropFilesHere, String houseTable, String menu, String information, String house, String manageHouses, String newHouse, String copyright, String WellcomToYourAccount, String selectAHouse, String areaOnTheMap, String housesList, String save) {
        this.szHouseLanguage = szHouseLanguage;
        this.nHouseId = nHouseId;
        this.szAddress = szAddress;
        this.szState = szState;
        this.szCity = szCity;
        this.szStreet = szStreet;
        this.nHouseNumber = nHouseNumber;
        this.EHouseKind = EHouseKind;
        this.nNumberOfRooms = nNumberOfRooms;
        this.nNumberOfLivingRooms = nNumberOfLivingRooms;
        this.nNumberOfKitchens = nNumberOfKitchens;
        this.nNumberOfBedrooms = nNumberOfBedrooms;
        this.nNumberOfBathrooms = nNumberOfBathrooms;
        this.eLocationKind = eLocationKind;
        this.nScore = nScore;
        this.szComments = szComments;
        this.dPurchasePrice = dPurchasePrice;
        this.dTreatmentFees = dTreatmentFees;
        this.dRenovationFeesForSale = dRenovationFeesForSale;
        this.dRenovationFeesForRenting = dRenovationFeesForRenting;
        this.dDiversFees = dDiversFees;
        this.szGeneralHouseDetailes = szGeneralHouseDetailes;
        this.szFinancialHouseDetailes = szFinancialHouseDetailes;
        this.UploadHouseFiles = uploadHouseFiles;
        this.DragOrDropFilesHere = dragOrDropFilesHere;
        this.HouseTable = houseTable;
        this.Menu = menu;
        this.Information = information;
        this.House = house;
        this.ManageHouses = manageHouses;
        this.NewHouse = newHouse;
        this.Copyright = copyright;
        this.WellcomToYourAccount = WellcomToYourAccount;
        this.SelectAHouse = selectAHouse;
        this.AreaOnTheMap = areaOnTheMap;
        this.HousesList = housesList;
        this.Save = save;
    }

    public String getHouseId() {
        return nHouseId;
    }

    public void setHouseId(String nHouseId) {
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

    public String getHouseNumber() {
        return nHouseNumber;
    }

    public void setHouseNumber(String nHouseNumber) {
        this.nHouseNumber = nHouseNumber;
    }

    public String getEHouseKind() {
        return EHouseKind;
    }

    public void setHouseKind(String EHouseKind) {
        this.EHouseKind = EHouseKind;
    }

    public String getNumberOfRooms() {
        return nNumberOfRooms;
    }

    public void setNumberOfRooms(String nNumberOfRooms) {
        this.nNumberOfRooms = nNumberOfRooms;
    }

    public String getNumberOfLivingRooms() {
        return nNumberOfLivingRooms;
    }

    public void setNumberOfLivingRooms(String nNumberOfLivingRooms) {
        this.nNumberOfLivingRooms = nNumberOfLivingRooms;
    }

    public String getNumberOfKitchens() {
        return nNumberOfKitchens;
    }

    public void setNumberOfKitchens(String nNumberOfKitchens) {
        this.nNumberOfKitchens = nNumberOfKitchens;
    }

    public String getNumberOfBedrooms() {
        return nNumberOfBedrooms;
    }

    public void setNumberOfBedrooms(String nNumberOfBedrooms) {
        this.nNumberOfBedrooms = nNumberOfBedrooms;
    }

    public String getNumberOfBathrooms() {
        return nNumberOfBathrooms;
    }

    public void setNumberOfBathrooms(String nNumberOfBathrooms) {
        this.nNumberOfBathrooms = nNumberOfBathrooms;
    }

    public String getLocationKind() {
        return eLocationKind;
    }

    public void setLocationKind(String m_locationKind) {
        eLocationKind = m_locationKind;
    }

    public String getComments() {
        return szComments;
    }

    public void setComments(String szComments) {
        this.szComments = szComments;
    }

    public String getPurchasePrice() {
        return dPurchasePrice;
    }

    public void setPurchasePrice(String dPurchasePrice) {
        this.dPurchasePrice = dPurchasePrice;
    }

    public String getTreatmentFees() {
        return dTreatmentFees;
    }

    public void setTreatmentFees(String dTreatmentFees) {
        this.dTreatmentFees = dTreatmentFees;
    }

    public String getdRenovationFeesForSale() {
        return dRenovationFeesForSale;
    }

    public void setRenovationFeesForSale(String dRenovationFees) {
        this.dRenovationFeesForSale = dRenovationFees;
    }

    public String getDiversFees() {
        return dDiversFees;
    }

    public void setDiversFees(String dDiversFees) {
        this.dDiversFees = dDiversFees;
    }

    public String getUploadHouseFiles() {
        return UploadHouseFiles;
    }

    public void setUploadHouseFiles(String uploadHouseFiles) {
        UploadHouseFiles = uploadHouseFiles;
    }

    public String getDragOrDropFilesHere() {
        return DragOrDropFilesHere;
    }

    public void setDragOrDropFilesHere(String dragOrDropFilesHere) {
        DragOrDropFilesHere = dragOrDropFilesHere;
    }

    public String getHouseTable() {
        return HouseTable;
    }

    public void setHouseTable(String houseTable) {
        HouseTable = houseTable;
    }

    public String getMenu() {
        return Menu;
    }

    public void setMenu(String menu) {
        Menu = menu;
    }

    public String getInformation() {
        return Information;
    }

    public void setInformation(String information) {
        Information = information;
    }

    public String getHouse() {
        return House;
    }

    public void setHouse(String house) {
        House = house;
    }

    public String getManageHouses() {
        return ManageHouses;
    }

    public void setManageHouses(String manageHouses) {
        ManageHouses = manageHouses;
    }

    public String getNewHouse() {
        return NewHouse;
    }

    public void setNewHouse(String newHouse) {
        NewHouse = newHouse;
    }

    public String getCopyright() {
        return Copyright;
    }

    public void setCopyright(String copyright) {
        Copyright = copyright;
    }

    public String getWellcomToYourAccount() {
        return WellcomToYourAccount;
    }

    public void setWellcomToYourAccount(String WellcomToYourAccount) {
        this.WellcomToYourAccount = WellcomToYourAccount;
    }

    public String getSelectaHouse() {
        return SelectAHouse;
    }

    public void setSelectAHouse(String selectAHouse) {
        SelectAHouse = selectAHouse;
    }

    public String getAreaOnTheMap() {
        return AreaOnTheMap;
    }

    public void setAreaOnTheMap(String areaOnTheMap) {
        AreaOnTheMap = areaOnTheMap;
    }

    public String getHousesList() {
        return HousesList;
    }

    public void setHousesList(String housesList) {
        HousesList = housesList;
    }

    public String getSave() {
        return Save;
    }

    public void setSave(String save) {
        Save = save;
    }

    public String getHouseLanguage() {
        return szHouseLanguage;
    }

    public void setHouseLanguage(String szHouseLanguage) {
        this.szHouseLanguage = szHouseLanguage;
    }

    public String getAddress() {
        return szAddress;
    }

    public void setAddress(String szAdress) {
        this.szAddress = szAdress;
    }

    public String getScore() {
        return nScore;
    }

    public void setScore(String nScore) {
        this.nScore = nScore;
    }

    public String getRenovationFeesForRenting() {
        return dRenovationFeesForRenting;
    }

    public void setRenovationFeesForRenting(String dRenovationFeesForRenting) {
        this.dRenovationFeesForRenting = dRenovationFeesForRenting;
    }

    public String getGeneralHouseDetailes() {
        return szGeneralHouseDetailes;
    }

    public void setGeneralHouseDetailes(String szGeneralHouseDetailes) {
        this.szGeneralHouseDetailes = szGeneralHouseDetailes;
    }

    public String getFinancialHouseDetailes() {
        return szFinancialHouseDetailes;
    }

    public void setFinancialHouseDetailes(String szFinancialHouseDetailes) {
        this.szFinancialHouseDetailes = szFinancialHouseDetailes;
    }

    public String getSendMeThisHouseToMyMail() {
        return SendMeThisHouseToMyMail;
    }

    public void setSendMeThisHouseToMyMail(String sendMeThisHouseToMyMail) {
        SendMeThisHouseToMyMail = sendMeThisHouseToMyMail;
    }

    public String getPageDirection() {
        return PageDirection;
    }

    public void setPageDirection(String pageDirection) {
        PageDirection = pageDirection;
    }

    public String getLogout() {
        return Logout;
    }

    public void setLogout(String logout) {
        Logout = logout;
    }

    public String getSystemManager() {
        return SystemManager;
    }

    public void setSystemManager(String systemManager) {
        SystemManager = systemManager;
    }

    public String getHouseProfilePicture() {
        return HouseProfilePicture;
    }

    public void setHouseProfilePicture(String houseProfilePicture) {
        HouseProfilePicture = houseProfilePicture;
    }

    public String getUploadHousePictures() {
        return UploadHousePictures;
    }

    public String getBackToTheCatalog() {
        return BackToTheCatalog;
    }

    public void setBackToTheCatalog(String backToTheCatalog) {
        BackToTheCatalog = backToTheCatalog;
    }

    public void setUploadHousePictures(String uploadHousePictures) {
        UploadHousePictures = uploadHousePictures;
    }
    public String toJson() {
        return "{ \"Dictionary\":[ {" +
                "\"Dictionary\":\"" + szHouseLanguage + "\"" +
                ",\"HouseId\":\"" + nHouseId + "\"" +
                ",\"Address\":\"" + szAddress + "\"" +
                ",\"State\":\"" + szState + "\"" +
                ",\"City\":\"" + szCity + "\"" +
                ",\"Street\":\"" + szStreet + "\"" +
                ",\"HouseNumber\":\"" + nHouseNumber + "\"" +
                ",\"HouseKind\":\"" + EHouseKind + "\"" +
                ",\"NumberOfRooms\":\"" + nNumberOfRooms + "\"" +
                ",\"NumberOfLivingRooms\":\"" + nNumberOfLivingRooms + "\"" +
                ",\"NumberOfKitchens\":\"" + nNumberOfKitchens + "\"" +
                ",\"NumberOfBedrooms\":\"" + nNumberOfBedrooms + "\"" +
                ",\"NumberOfBathrooms\":\"" + nNumberOfBathrooms + "\"" +
                ",\"LocationKind\":\"" + eLocationKind + "\"" +
                ",\"Score\":\"" + nScore + "\"" +
                ",\"Comments\":\"" + szComments + "\"" +
                ",\"PurchasePrice\":\"" + dPurchasePrice + "\"" +
                ",\"TreatmentFees\":\"" + dTreatmentFees + "\"" +
                ",\"RenovationFeesForSale\":\"" + dRenovationFeesForSale + "\"" +
                ",\"RenovationFeesForRenting\":\"" + dRenovationFeesForRenting + "\"" +
                ",\"GeneralHouseDetailes\":\"" + szGeneralHouseDetailes + "\"" +
                ",\"FinancialHouseDetailes\":\"" + szFinancialHouseDetailes + "\"" +
                ",\"DiversFees\":\"" + dDiversFees + "\"" +
                ",\"UploadHouseFiles\":\"" + UploadHouseFiles + "\"" +
                ",\"DragOrDropFilesHere\":\"" + DragOrDropFilesHere + "\"" +
                ",\"HouseTable\":\"" + HouseTable + "\"" +
                ",\"Menu\":\"" + Menu + "\"" +
                ",\"Information\":\"" + Information + "\"" +
                ",\"House\":\"" + House + "\"" +
                ",\"ManageHouses\":\"" + ManageHouses + "\"" +
                ",\"NewHouse\":\"" + NewHouse + "\"" +
                ",\"Copyright\":\"" + Copyright + "\"" +
                ",\"WellcomToYourAccount\":\"" + WellcomToYourAccount + "\"" +
                ",\"SelectaHouse\":\"" + SelectAHouse + "\"" +
                ",\"AreaOnTheMap\":\"" + AreaOnTheMap + "\"" +
                ",\"HousesList\":\"" + HousesList + "\"" +
                ",\"Save\":\"" + Save + "\"" +
                ",\"SendMeThisHouseToMyMail\":\"" + SendMeThisHouseToMyMail + "\"" +
                ",\"PageDirection\":\"" + PageDirection + "\"" +
                ",\"Logout\":\"" + Logout + "\"" +
                ",\"SystemManager\":\"" + SystemManager + "\"" +
                ",\"HouseProfilePicture\":\"" + HouseProfilePicture + "\"" +
                ",\"UploadHousePictures\":\"" + UploadHousePictures + "\"" +
                ",\"BackToTheCatalog\":\"" + BackToTheCatalog + "\"" +
                "} ]}";
    }
}
