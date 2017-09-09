package BL;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import Entity.*;
import File.FileSetter;
import DB.getterDB;
import DB.setterDB;
import com.fasterxml.jackson.databind.JsonNode;
import play.mvc.Http;

import javax.imageio.ImageIO;


/**
 * @author Yaacov
 */
public class setterBL {
    private setterDB setterDB = new setterDB();
    private getterDB getterDB = new getterDB();
    private getterBL getterBL = new getterBL();
    private FileSetter fileSetter = new FileSetter();
    private WebResponce webResponce = new WebResponce();
    private mailBL mailBl = new mailBL();

    /**
     * Registering a new user into the system.
     *
     * @param szUserName
     * @param szTelephone
     * @param szEmail
     * @param szPassword
     * @return
     * @throws Exception
     */
    public boolean registerNewUser(String szUserName, String szTelephone,
                                   String szEmail, String szPassword, String szPermissionManager, String szPermissionView) throws Exception {
        // INFO
        play.Logger.info("<BUISNESS_LOGIC> Register new user : ");
        play.Logger.info("============================");
        play.Logger.info("For : =>>");
        play.Logger.info("User name : " + szUserName);
        play.Logger.info("Telephone : " + szTelephone);
        play.Logger.info("Email : " + szEmail);
        play.Logger.info("Password : " + szPassword);
        play.Logger.info("============================");
        int nPermissionManager = Integer.parseInt(szPermissionManager);
        int nPermissionView = Integer.parseInt(szPermissionView);
        boolean isRegitred = false;
        if (setterDB.registerNewUser(szUserName, szTelephone, szEmail, szPassword, nPermissionManager, nPermissionView)) {
            isRegitred = true;
        }
        return isRegitred;
    }

    /**
     * Registering a new user into the system.
     *
     * @param szUserName
     * @param szTelephone
     * @param szEmail
     * @param szPassword
     * @return
     * @throws Exception
     */
    public WebResponce addNewUser(String szUserName, String szTelephone, String szEmail, String szPassword, String szPermissionManager, String szPermissionView) throws Exception {
        webResponce = new WebResponce();
        // INFO
        play.Logger.info("<BUISNESS_LOGIC> Register new user : ");
        play.Logger.info("============================");
        play.Logger.info("For : =>>");
        play.Logger.info("User name : " + szUserName);
        play.Logger.info("Telephone : " + szTelephone);
        play.Logger.info("Email : " + szEmail);
        play.Logger.info("Password : " + szPassword);
        play.Logger.info("============================");
        int nPermissionManager = Integer.parseInt(szPermissionManager);
        int nPermissionView = Integer.parseInt(szPermissionView);
        webResponce = setterDB.addNewUser(szUserName, szTelephone, szEmail, szPassword, nPermissionManager, nPermissionView);
        return webResponce;
    }


    private ArrayList<HousePermitedToView> getArrayListOfHousePermissionToView(Iterator<JsonNode> lsthousePermitedToViews) {
        ArrayList<HousePermitedToView> lstHousePermitedToViewsToReturn = new ArrayList<>();
        HousePermitedToView housePermitedToView = new HousePermitedToView();
        JsonNode currJsonNode = null;
        if (lsthousePermitedToViews.hasNext()) {
            currJsonNode = lsthousePermitedToViews.next();
        }
        while (currJsonNode != null) {
            lstHousePermitedToViewsToReturn.add(new HousePermitedToView(currJsonNode.findPath("houseId").asInt(), currJsonNode.findPath("houseAdress").textValue(), currJsonNode.findPath("isPermitedToView").asBoolean()));
            if (lsthousePermitedToViews.hasNext()) {
                currJsonNode = lsthousePermitedToViews.next();
            } else {
                currJsonNode = null;
            }
        }
        return lstHousePermitedToViewsToReturn;
    }

    public WebResponce deleteUser(User m_user) {
        int nUserId = Integer.parseInt(m_user.getUserId());
        webResponce = setterDB.deleteUser(nUserId);
        return webResponce;
    }

    public WebResponce updateHouse(House m_house) {
        webResponce = new WebResponce();
        webResponce.setReason("The house was update In the System. הבית עודכן בהצלחה");
        setterDB.updateHouseDetails(m_house);
        return webResponce;
    }



    public WebResponce insertNewFood(FoodEntity m_foodEntity, List<Http.MultipartFormData.FilePart> foodPictures) throws IOException {
        webResponce = new WebResponce();
        int nFoodIdToReturn = -1;

        // insert data to data base
        webResponce = setterDB.insertNewFood(m_foodEntity);

        // insert pictures files
        nFoodIdToReturn = getterDB.getFoodIdByName(m_foodEntity.getFoodName());

        if(nFoodIdToReturn != -1){
            saveFoodFiles(foodPictures, nFoodIdToReturn);
        }

        return webResponce;
    }

    private void saveFoodFiles(List<Http.MultipartFormData.FilePart> foodPictures, int nFoodIdToReturn) throws IOException {
        int nSumOfFileCounter = 0;
        ArrayList<File> foodPicturesToSave = new ArrayList<>();
        for (Http.MultipartFormData.FilePart currFile : foodPictures) {
            String dstFileName = null;
            if (nSumOfFileCounter == 0) {
                dstFileName = System.getenv("FOLIES_PICTS") + "\\" + nFoodIdToReturn + fileSetter.getTypeFile(currFile.getFilename());
            } else {
                dstFileName = System.getenv("FOLIES_PICTS") + "\\" + nFoodIdToReturn + "_" + String.valueOf(nSumOfFileCounter) + fileSetter.getTypeFile(currFile.getFilename());
            }
            fileSetter.saveToFile(currFile.getFile(), dstFileName, fileSetter.getTypeFile(currFile.getFilename()));
            nSumOfFileCounter++;
        }
    }


}
