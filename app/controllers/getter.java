package controllers;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import BL.getterBL;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;



/**
 * @author Yaacov
 */
public class getter extends Controller {
    private static final Lock lock = new ReentrantLock();
    private static getterBL getterBL = new getterBL();

    public static Result isLoginPermited(String szUserName, String szPassword) {
        Logger.info("<GETTER> Clien in IP : " + request().remoteAddress() + " Trying to connect");
        System.out.println("<GETTER> Clien in IP : " + request().remoteAddress() + " Trying to connect");

        if ((szUserName != null) && (szPassword != null)) {
            String szPermission = getterBL.isLoginPermited(szUserName, szPassword);
            if (szPermission != "-1") {
                Logger.info("<GETTER> " + szUserName + " is login from IP: " + request().remoteAddress());
                System.out.println("<GETTER> " + szUserName + " is login from IP: " + request().remoteAddress());
                return play.mvc.Results.ok(szPermission);
            } else {

                System.out.println("[INFO] Error when trying to connect with wrong user-name or password.\nUSER_NAME : '"
                        + szUserName + "'\nPASSWORD : '" + szPassword + "'");
                return play.mvc.Results.badRequest("The user-name or the password is incorrect");
            }
        } else {
            return play.mvc.Results.badRequest(
                    "Null pointer screw you! \nyou send your request with an empty user-name or an empty password!");
        }
    }


    public static Result isUserNameAlreadyExist(String szUserName) {
        if (szUserName != null) {
            if (getterBL.isUserNameExist(szUserName)) {
                return play.mvc.Results.badRequest("user name already exist");
            }
            return play.mvc.Results.ok("true");
        } else {
            return play.mvc.Results
                    .badRequest("Null pointer screw you! \nyou send your request with an empty user-name!");
        }
    }

    public static Result isEmailAlreadyExist(String szEmail) {
        if (szEmail != null) {
            if (getterBL.isEmailAlreadyExist(szEmail)) {
                return play.mvc.Results.badRequest("email already exist");
            }
            return play.mvc.Results.ok("true");
        } else {
            return play.mvc.Results.badRequest("Null pointer screw you! \nyou send your request with an empty email!");
        }

    }


    public static Result getUser(String szUserName) {
        if (szUserName != null) {
            return play.mvc.Results.ok(Json.toJson(getterBL.getUsersName(szUserName)));
        } else {
            return play.mvc.Results
                    .badRequest("Null pointer screw you! \nyou send your request with an empty user-name!");
        }
    }


    public static Result getUserInformation(String szUserName) {
        Logger.info("<GETTER> " + szUserName + " ask information on the user");
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER> " + szUserName + " in IP : " + request().remoteAddress() + " : ask information on user name : " + szUserName);
        if (szUserName != null) {
            String szResponce = getterBL.getUserInformation(szUserName).toString();
            Logger.info("<GETTER> <DATA>" + szResponce);
            return play.mvc.Results.ok(szResponce);
        } else {
            return play.mvc.Results
                    .badRequest("Null pointer screw you! \nyou send your request with an empty user-name!");
        }
    }


    public static Result getListOfExistingLanguages() {
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get List Of Existing Language");
        String szResponce = getterBL.getListOFExistingLanguage().toString();
        System.out.println(szResponce);
        return ok(szResponce);
    }

    public static Result getLanguage(String szLanguage) {
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get Language : " + szLanguage);
        String szResponce = getterBL.getHouseLanguageByLanguage(szLanguage).toJson();
        return ok(szResponce);
    }


    /***
     * Get a specific house picture from server
     * @return
     */
    public static Result getHouseProfilePicture(String szFolderHouseName, String szFolderProfileName, String szFileName) {
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get File For House : " + szFolderHouseName + ": File Name:" + szFileName);
        Logger.debug(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get File For House : " + szFolderHouseName + ": File Name:" + szFileName);
        File fileToReturn = getterBL.getProfileHousePicture(szFolderHouseName, szFolderProfileName, szFileName);
        return ok(fileToReturn);
    }




    /***
     * Get a specific house picture from server
     * @return
     */
    public static Result getSpecificPicture(String szFolderName, String szFileName) {
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get File For House : " + szFolderName + ": File Name:" + szFileName);
        Logger.debug(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get File For House : " + szFolderName + ": File Name:" + szFileName);
        File fileToReturn = getterBL.getSpecificPicture(szFolderName, szFileName);
        return ok(fileToReturn);
    }

    /***
     * Get a specific house picture from server
     * @return
     */
    public static Result getSpecificDocument(String szFolderName, String szDocumentsDir, String szFileName) {
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get File For House : " + szFolderName + ": File Name:" + szFileName);
        Logger.debug(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get File For House : " + szFolderName + ": File Name:" + szFileName);
        File fileToReturn = getterBL.getSpecificPicture(szFolderName + "\\" + szDocumentsDir, szFileName);
        return ok(fileToReturn);
    }

    /***
     * Get a specific house picture from server
     * @return
     */
    public static Result getPermitedToView() {
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get Permited To View List");
        Logger.debug(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get Permited To View List");
        String szResponce = getterBL.getListOfPermitedToView().toString();
        return ok(szResponce);
    }
    /***
     * Get a all food from server
     * @return
     */
    public static Result getAllFoods() {
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get all food");
       Logger.debug(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get all food");
        String szResponce = "to";
        return ok(Json.toJson(getterBL.getAllFoodEntities()));
    }

    /***
     * Get a all food from server
     * @return
     */
    public static Result getHalaviFoods() {
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get all food");
        Logger.debug(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get all food");
        String szResponce = "to";
        return ok(Json.toJson(getterBL.getAllHalaviFoodEntities()));
    }
    /***
     * Get a all food from server
     * @return
     */
    public static Result getBassariFoods() {
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get all food");
        Logger.debug(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get all food");
        String szResponce = "to";
        return ok(Json.toJson(getterBL.getAllBessariFoodEntities()));
    }
    /***
     * Get a all food from server
     * @return
     */
    public static Result getParveFoods() {
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get all food");
        Logger.debug(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get all food");
        String szResponce = "to";
        return ok(Json.toJson(getterBL.getAllParveFoodEntities()));
    }
    /***
     * Get a all food from server
     * @return
     */
    public static Result getVegetarianFoods() {
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get all food");
        Logger.debug(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get all food");
        String szResponce = "to";
        return ok(Json.toJson(getterBL.getAllVegetarianFoodEntities()));
    }
    /***
     * Get a all food from server
     * @return
     */
    public static Result getVeganFoods() {
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get all food");
        Logger.debug(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get all food");
        String szResponce = "to";
        return ok(Json.toJson(getterBL.getAllVeganFoodEntities()));
    }
    /***
     * Get a all food from server
     * @return
     */
    public static Result getInKeytring() {
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get all food");
        Logger.debug(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get all food");
        return ok(Json.toJson(getterBL.getAllInKeytringFoodEntities()));
    }
    /***
     * Get a all food from server
     * @return
     */
    public static Result getInSucursal() {
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get all food");
        Logger.debug(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get all food");
        String szResponce = "to";
        return ok(Json.toJson(getterBL.getAllParveFoodEntities()));
    }
    /***
     * Get a specific house picture from server
     * @return
     */
    public static Result getSpecificImage(String szFileName) {
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get File Name:" + szFileName);
        Logger.debug(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get File Name:" + szFileName);
        File fileToReturn = getterBL.getSpecificImages(szFileName);
        return ok(fileToReturn);
    }
    public static Result getFood(String szFoodname) {
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get food name:" + szFoodname);
        Logger.debug(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get food name:" + szFoodname);
        return ok(Json.toJson(getterBL.getFood(szFoodname)));
    }

}
