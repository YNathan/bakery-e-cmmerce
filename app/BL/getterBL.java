package BL;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashSet;


import DB.getterDB;
import Entity.*;
import File.FileGetter;
import play.Logger;

import javax.imageio.ImageIO;

/**
 * Will do all the logic of the data who asked from the server
 *
 * @author Yaacov
 */
public class getterBL {
    getterDB getterDB = new getterDB();
    FileGetter fileGetter = new FileGetter();
    mailBL mail = new mailBL();
    private static String HOUSES_DOCUMENTS_DIR = "HousesDocuments";

    /**
     * @param szUserName - the user-name that the user send
     * @param szPassword - the password that the user send
     * @return true is the user-name and the password is correct
     */
    public String isLoginPermited(String szUserName, String szPassword) {
        // INFO
        Logger.info("<BUSINESS_LOGIC> Get is login permited");
        String szPermission = "-1";
        ArrayList<User> userList = getterDB.getUsers();
        boolean isGreateLogin = false;
        for (User currUser : userList) {
            if (currUser.getUsername().equals(szUserName) && currUser.getPassword().equals(szPassword)) {
                szPermission = currUser.convertBooleanToString(currUser.getPermissionManager());
               /* try {
                    mail.sendLoginSuccess(szUserName, request().remoteAddress());
                } catch (MessagingException e) {
                    Logger.error(e.getMessage());
                    e.printStackTrace();
                }*/
            }
        }
        return szPermission;
    }

    /**
     * @param szUserName - the user-name that will found for hem the id
     * @return the id of the user in the system
     */
    public int getIdByName(String szUserName) {
        // INFO
        Logger.info("<BUSINESS_LOGIC> Get Id by name");

        int nUserId = -1;
        ArrayList<User> userLst = getterDB.getUsers();
        for (User currUser : userLst) {
            if (currUser.getUsername().equals(szUserName)) {
                nUserId = Integer.parseInt(currUser.getUserId());
            }
        }
        return nUserId;
    }

    /**
     * @param nUserId - the user-id that will found for hem the user-name
     * @return the name of the user in the system
     */
    public String getNameById(int nUserId) {
        // INFO
        Logger.info("<BUSINESS_LOGIC> Get Name by Id");

        String szUserName = "";
        ArrayList<User> userLst = getterDB.getUsers();
        for (User currUser : userLst) {
            if (Integer.parseInt(currUser.getUserId()) == nUserId) {
                szUserName = currUser.getUsername();
            }
        }
        return szUserName;
    }

    /**
     * @param szUserName - the userName that we want to check if still exist
     * @return true if there still a user name like this in the system
     */
    public boolean isUserNameExist(String szUserName) {
        // INFO
        Logger.info("<BUSINESS_LOGIC> Get is user_name exist");

        boolean isUserNameAlreadyExist = false;
        ArrayList<String> usersName = getterDB.getUserNames();
        for (String currName : usersName) {
            if (currName.equals(szUserName)) {
                isUserNameAlreadyExist = true;
            }
        }
        return isUserNameAlreadyExist;
    }

    /**
     * @param szEmail - the email that we want to check if still exist
     * @return true if there still a email like this in the system
     */
    public boolean isEmailAlreadyExist(String szEmail) {
        // INFO
        Logger.info("<BUSINESS_LOGIC> Get is email already exist");

        boolean isEmailAlreadyExist = false;
        ArrayList<String> emails = getterDB.getEmails();
        for (String currEmail : emails) {
            if (currEmail.equals(szEmail)) {
                isEmailAlreadyExist = true;
            }
        }
        return isEmailAlreadyExist;
    }

    public ArrayList<String> getUsersName(String szUserName) {
        // INFO
        Logger.info("<BUSINESS_LOGIC> Get users");

        ArrayList<String> usersName = getterDB.getUserNames();
        usersName.remove(szUserName);
        return usersName;
    }


    public Date getDateByString(String szDate) {
        // INFO
        Logger.info("<BUSINESS_LOGIC> Get date by string");

        String szYear = szDate.substring(0, 4);
        int nYear = Integer.parseInt(szYear);
        nYear -= 1900;
        String szMonth = szDate.substring(5, 7);
        int nMonth = Integer.parseInt(szMonth);
        nMonth -= 1;
        String szDay = szDate.substring(8);
        int nDay = Integer.parseInt(szDay);
        return new Date(nYear, nMonth, nDay);
    }

    /***
     *
     * @param szUserName the name of the user
     * @return all personal information about a user
     */
    public String getUserInformation(String szUserName) {
        // INFO
        Logger.info("<BUSINESS_LOGIC> Get user information with user name : " + szUserName);
        int nUserId = getterDB.getUserIdByName(szUserName);
        User userToReturn = getterDB.getUser(nUserId);
        return userToReturn.toJson();
    }


    public Dictionary getHouseLanguageByLanguage(String szLanguage) {
        Dictionary dictionaryToReturn = new Dictionary();

        dictionaryToReturn = getterDB.getHouseLanguageByLanguage(szLanguage);
        System.out.println(dictionaryToReturn.toJson());
        return dictionaryToReturn;

    }

    public ArrayList<FoodEntity> getAllFoodEntities() {
        ArrayList<FoodEntity> foodEntitiesToReturn = new ArrayList<>();
        foodEntitiesToReturn = getterDB.getFoodEntitys();
        return foodEntitiesToReturn;
    }

    public ArrayList<FoodEntity> getAllHalaviFoodEntities() {
        ArrayList<FoodEntity> allFoodEntitys = new ArrayList<>();
        ArrayList<FoodEntity> halaviFoodEntitiesToReturn = new ArrayList<>();
        allFoodEntitys = getterDB.getFoodEntitys();
        for (FoodEntity currentFoodEntity : allFoodEntitys) {
            if (currentFoodEntity.isHalavi()) {
                halaviFoodEntitiesToReturn.add(currentFoodEntity);
            }
        }
        return halaviFoodEntitiesToReturn;
    }

    public ArrayList<FoodEntity> getAllBessariFoodEntities() {
        ArrayList<FoodEntity> allFoodEntitys = new ArrayList<>();
        ArrayList<FoodEntity> BessariFoodEntitiesToReturn = new ArrayList<>();
        allFoodEntitys = getterDB.getFoodEntitys();
        for (FoodEntity currentFoodEntity : allFoodEntitys) {
            if (currentFoodEntity.isBassari()) {
                BessariFoodEntitiesToReturn.add(currentFoodEntity);
            }
        }
        return BessariFoodEntitiesToReturn;
    }

    public ArrayList<FoodEntity> getAllParveFoodEntities() {
        ArrayList<FoodEntity> allFoodEntitys = new ArrayList<>();
        ArrayList<FoodEntity> parveFoodEntitiesToReturn = new ArrayList<>();
        allFoodEntitys = getterDB.getFoodEntitys();
        for (FoodEntity currentFoodEntity : allFoodEntitys) {
            if (currentFoodEntity.isParve()) {
                parveFoodEntitiesToReturn.add(currentFoodEntity);
            }
        }
        return parveFoodEntitiesToReturn;
    }

    public ArrayList<FoodEntity> getAllVegetarianFoodEntities() {
        ArrayList<FoodEntity> allFoodEntitys = new ArrayList<>();
        ArrayList<FoodEntity> vegetarianFoodEntitiesToReturn = new ArrayList<>();
        allFoodEntitys = getterDB.getFoodEntitys();
        for (FoodEntity currentFoodEntity : allFoodEntitys) {
            if (currentFoodEntity.isVegetarian()) {
                vegetarianFoodEntitiesToReturn.add(currentFoodEntity);
            }
        }
        return vegetarianFoodEntitiesToReturn;
    }

    public ArrayList<FoodEntity> getAllVeganFoodEntities() {
        ArrayList<FoodEntity> allFoodEntitys = new ArrayList<>();
        ArrayList<FoodEntity> veganFoodEntitiesToReturn = new ArrayList<>();
        allFoodEntitys = getterDB.getFoodEntitys();
        for (FoodEntity currentFoodEntity : allFoodEntitys) {
            if (currentFoodEntity.isVegan()) {
                veganFoodEntitiesToReturn.add(currentFoodEntity);
            }
        }
        return veganFoodEntitiesToReturn;
    }

    public ArrayList<FoodEntity> getAllInKeytringFoodEntities() {
        ArrayList<FoodEntity> allFoodEntitys = new ArrayList<>();
        ArrayList<FoodEntity> inKeyrtringFoodEntitiesToReturn = new ArrayList<>();
        allFoodEntitys = getterDB.getFoodEntitys();
        for (FoodEntity currentFoodEntity : allFoodEntitys) {
            if (currentFoodEntity.isInKeytring()) {
                inKeyrtringFoodEntitiesToReturn.add(currentFoodEntity);
            }
        }
        return inKeyrtringFoodEntitiesToReturn;
    }

    public ArrayList<FoodEntity> getFood(String foodName) {
        ArrayList<FoodEntity> allFoodEntitys = new ArrayList<>();
        ArrayList<FoodEntity> matchFoodToReturn = new ArrayList<>();
        allFoodEntitys = getterDB.getFoodEntitys();
        for (FoodEntity currentFoodEntity : allFoodEntitys) {

            switch (foodName) {
                case "Bessari":
                    if (currentFoodEntity.isBassari()) {
                        matchFoodToReturn.add(currentFoodEntity);
                    }
                    break;
                case "Parve":
                    if (currentFoodEntity.isParve()) {
                        matchFoodToReturn.add(currentFoodEntity);
                    }
                    break;
                case "Halavi":
                    if (currentFoodEntity.isHalavi()) {
                        matchFoodToReturn.add(currentFoodEntity);
                    }
                    break;
                default:
                    if (currentFoodEntity.isBassari()) {
                        matchFoodToReturn.add(currentFoodEntity);
                    }
                    break;
            }
        }
        return matchFoodToReturn;
    }

    public ArrayList<FoodEntity> getAllInSucursalFoodEntities() {
        ArrayList<FoodEntity> allFoodEntitys = new ArrayList<>();
        ArrayList<FoodEntity> InKeytringFoodEntitiesToReturn = new ArrayList<>();
        allFoodEntitys = getterDB.getFoodEntitys();
        for (FoodEntity currentFoodEntity : allFoodEntitys) {
            if (currentFoodEntity.isInSucurSal()) {
                InKeytringFoodEntitiesToReturn.add(currentFoodEntity);
            }
        }
        return InKeytringFoodEntitiesToReturn;
    }

    public StringBuilder getListOFExistingLanguage() {
        StringBuilder sbExistingLanguageToReturn = new StringBuilder();
        sbExistingLanguageToReturn.append("{ \"languages\": [");
        ArrayList<String> lstExistingLaguages = getterDB.getListOFExistingLanguage();

        Iterator<String> ltrHouseLanguage = lstExistingLaguages.iterator();
        String currHouseLanguage = null;
        if (ltrHouseLanguage.hasNext()) {
            currHouseLanguage = ltrHouseLanguage.next();
        }
        while (currHouseLanguage != null) {
            sbExistingLanguageToReturn.append("\"" + currHouseLanguage + "\"");
            if (ltrHouseLanguage.hasNext()) {
                sbExistingLanguageToReturn.append(",");
                currHouseLanguage = ltrHouseLanguage.next();
            } else {
                currHouseLanguage = null;
            }

        }
        sbExistingLanguageToReturn.append("]}");
        return sbExistingLanguageToReturn;
    }


    // Get Profile House Picture
    public File getProfileHousePicture(String szFolderName, String szFolderProfilName, String szFileName) {
        String szFullFilePath = System.getProperty("user.dir") + "\\HousesDocuments\\" + szFolderName + "\\" + szFolderProfilName + "\\" + szFileName;
        File fileToReturn = fileGetter.getFile(szFullFilePath);
        System.out.println("Get File " + szFullFilePath);
        return fileToReturn;
    }

    // Get Specific Picture
    public File getSpecificPicture(String szFolderName, String szFileName) {
        String szFullFilePath = System.getProperty("user.dir") + "\\HousesDocuments\\" + szFolderName + "\\" + szFileName;
        File fileToReturn = fileGetter.getFile(szFullFilePath);
        return fileToReturn;
    }


    // Get Specific Documents
    public File getSpecificDocuments(String szFolderName, String szFileName) {
        String szFullFilePath = System.getProperty("user.dir") + "\\HousesDocuments\\" + szFolderName + "\\" + szFileName;
        File fileToReturn = fileGetter.getFile(szFullFilePath);
        System.out.println("Get File " + szFullFilePath);
        return fileToReturn;
    }


    /**
     * Get house profile pictures
     *
     * @return
     */
    public StringBuilder getListOfPermitedToView() {
        StringBuilder sbExistingFilesToReturn = new StringBuilder();
        sbExistingFilesToReturn.append("{ \"PermissionedToView\": [");
        ArrayList<Integer> lstPermissionsViews = new ArrayList<>();
        lstPermissionsViews = getterDB.getListOfPermitedToView();

        // Creating LinkedHashSet
        LinkedHashSet<Integer> lhs = new LinkedHashSet<>();

     /* Adding ArrayList elements to the LinkedHashSet
      * in order to remove the duplicate elements and
      * to preserve the insertion order.
      */
        lhs.addAll(lstPermissionsViews);

        // Removing ArrayList elements
        lstPermissionsViews.clear();

        // Adding LinkedHashSet elements to the ArrayList
        lstPermissionsViews.addAll(lhs);


        Iterator<Integer> permitedToViewIterator = lstPermissionsViews.iterator();
        PermissionsView currPermitedToView = null;
        if (permitedToViewIterator.hasNext()) {
            currPermitedToView = new PermissionsView(permitedToViewIterator.next());
        }
        while (currPermitedToView != null) {
            sbExistingFilesToReturn.append(currPermitedToView.toJson());
            if (permitedToViewIterator.hasNext()) {
                sbExistingFilesToReturn.append(",");
                currPermitedToView = new PermissionsView(permitedToViewIterator.next());
            } else {
                currPermitedToView = null;
            }

        }

        sbExistingFilesToReturn.append("]}");
        return sbExistingFilesToReturn;
    }

    // Get Specific Picture
    public File getSpecificImages(String szFileName) {
        String szFullFilePath = System.getenv("FOLIES_PICTS") + "\\" + szFileName;
        File fileToReturn = fileGetter.getFile(szFullFilePath);
        return fileToReturn;
    }


}
