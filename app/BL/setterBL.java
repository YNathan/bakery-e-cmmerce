package BL;

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

    public WebResponce updateHouseGeneralDetails(House m_house) {
        webResponce = new WebResponce();
        webResponce = setterDB.updateHouseGeneralDetails(m_house);
        return webResponce;
    }

    public WebResponce updateHouseFinancialDetails(House m_house) {
        webResponce = new WebResponce();
        webResponce = setterDB.updateHouseFinancialDetails(m_house);
        return webResponce;
    }



    /**
     * Copy file from old location to a new location in the system we will use
     * that for save the profile picture who we get from the client to the local
     * directory in the server
     *
     * @param oldLocation
     * @param newLocation
     * @throws IOException
     */
    public static void copyFile(File oldLocation, File newLocation) throws IOException {
        if (oldLocation.exists()) {
            BufferedInputStream reader = new BufferedInputStream(new FileInputStream(oldLocation));
            BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(newLocation, false));
            try {
                byte[] buff = new byte[8192];
                int numChars;
                while ((numChars = reader.read(buff, 0, buff.length)) != -1) {
                    writer.write(buff, 0, numChars);
                }
            } catch (IOException ex) {
                throw new IOException(
                        "IOException when transferring " + oldLocation.getPath() + " to " + newLocation.getPath());
            } finally {
                try {
                    if (reader != null) {
                        writer.close();
                        reader.close();
                    }
                } catch (IOException ex) {
                    System.out.println("Error closing files when transferring " + oldLocation.getPath() + " to "
                            + newLocation.getPath());
                }
            }
        } else {
            throw new IOException("Old location does not exist when transferring " + oldLocation.getPath() + " to "
                    + newLocation.getPath());
        }
    }

    public WebResponce setFiles(String szHouseName, List<Http.MultipartFormData.FilePart> pictures) {
        return fileSetter.setFiles(szHouseName, pictures);
    }

    public WebResponce setHouseDocuments(String szHouseName, List<Http.MultipartFormData.FilePart> pictures) {
        return fileSetter.setHouseDocuments(szHouseName, pictures);
    }

    public WebResponce setHouseProfilePictures(String szHouseName, List<Http.MultipartFormData.FilePart> pictures) {
        return fileSetter.setHouseProfilePicture(szHouseName, pictures);
    }





}
