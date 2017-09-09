package controllers;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import File.FileGetter;
import BL.setterBL;
import Entity.*;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import static play.mvc.Controller.flash;
import static play.mvc.Controller.request;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;

/**
 * @author Yaacov
 */
public class setter {
    private static WebResponce webResponce = new WebResponce();
    private static setterBL setterBL = new setterBL();
    private static FileGetter fileGetter = new FileGetter();


    /**
     * Inserting new house.
     *
     * @return
     */

    public static Result updateHouse() {
        webResponce = new WebResponce();
        JsonNode json = request().body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        } else {
            House houseToRegistre = new House();
            try {
                System.out.println(json.toString());
                houseToRegistre.setHouseId(json.findPath("house_id").intValue());
                houseToRegistre.setState(json.findPath("state").textValue());
                houseToRegistre.setCity(json.findPath("city").textValue());
                houseToRegistre.setStreet(json.findPath("street").textValue());
                houseToRegistre.setHouseNumber(json.findPath("house_number").asInt());
                EHouseKind eHouseKind = EHouseKind.ALONE;
                eHouseKind.setValue(json.findPath("house_kind").asInt());
                houseToRegistre.setHouseKind(eHouseKind);
                houseToRegistre.setNumberOfRooms(json.findPath("number_of_rooms").asInt());
                houseToRegistre.setNumberOfLivingRooms(json.findPath("number_of_living_rooms").asInt());
                houseToRegistre.setNumberOfKitchens(json.findPath("number_of_kitchens").asInt());
                houseToRegistre.setNumberOfBedrooms(json.findPath("number_of_bedrooms").intValue());
                houseToRegistre.setNumberOfBathrooms(json.findPath("number_of_bathrooms").intValue());
                ELocationKind eLocationKind = ELocationKind.WHITE;
                eLocationKind.setValue(json.findPath("location_kind").asInt());
                houseToRegistre.setLocationKind(eLocationKind);
                houseToRegistre.setComments(json.findPath("comments").textValue());
                houseToRegistre.setPurchasePrice(json.findPath("purchase_price").asDouble());
                houseToRegistre.setTreatmentFees(json.findPath("treatment_fees").asDouble());
                houseToRegistre.setRenovationFeesForRenting(json.findPath("renovation_fees_for_renting").asDouble());
                houseToRegistre.setRenovationFeesForSale(json.findPath("renovation_fees_for_sale").asDouble());
                houseToRegistre.setDiversFees(json.findPath("divers_fees").asDouble());
            } catch (Exception e) {
                webResponce.seteSuccessFailed(ESuccessFailed.FAILED);
                webResponce.setReason("Missing parameter the system did'nt save the details ,חסר פרטים המערכת לא שמרה ת הנתונים" + houseToRegistre.toString());
                e.printStackTrace();
                return badRequest(webResponce.toJson());
            }
            webResponce = setterBL.updateHouse(houseToRegistre);
            if (webResponce.getSuccessFailed() == ESuccessFailed.FAILED) {
                System.out.println(webResponce.toString());
                return badRequest(webResponce.toJson());
            }
            System.out.println("The House was Register correctly" + houseToRegistre.toString());
            return ok(webResponce.getReason());
        }
    }

    /**
     * Inserting house general details.
     *
     * @return
     */
    public static Result setHouseGeneralDetails() {
        webResponce = new WebResponce();
        JsonNode json = request().body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        } else {
            House houseToRegistre = new House();
            try {
                System.out.println(json.toString());
                EHouseKind eHouseKind = EHouseKind.ALONE;
                eHouseKind.setValue(json.findPath("house_kind").asInt());
                houseToRegistre.setHouseId(json.findPath("houseId").asInt());
                houseToRegistre.setHouseKind(eHouseKind);
                houseToRegistre.setNumberOfRooms(json.findPath("number_of_rooms").asInt());
                houseToRegistre.setNumberOfLivingRooms(json.findPath("number_of_living_rooms").asInt());
                houseToRegistre.setNumberOfKitchens(json.findPath("number_of_kitchens").asInt());
                houseToRegistre.setNumberOfBedrooms(json.findPath("number_of_bedrooms").intValue());
                houseToRegistre.setNumberOfBathrooms(json.findPath("number_of_bathrooms").intValue());
                ELocationKind eLocationKind = ELocationKind.WHITE;
                eLocationKind.setValue(json.findPath("location_kind").asInt());
                houseToRegistre.setLocationKind(eLocationKind);
                houseToRegistre.setComments(json.findPath("comments").textValue());
            } catch (Exception e) {
                webResponce.seteSuccessFailed(ESuccessFailed.FAILED);
                webResponce.setReason("Missing parameter the system did'nt save the details ,חסר פרטים המערכת לא שמרה ת הנתונים" + houseToRegistre.toString());
                e.printStackTrace();
                return badRequest(webResponce.toJson());
            }
            webResponce = setterBL.updateHouseGeneralDetails(houseToRegistre);
            if (webResponce.getSuccessFailed() == ESuccessFailed.FAILED) {
                System.out.println(webResponce.toString());
                return badRequest(webResponce.toJson());
            }
            return ok(webResponce.toJson());
        }
    }


    /**
     * Inserting house financial details.
     *
     * @return
     */
    public static Result setHouseFinancialDetails() {
        webResponce = new WebResponce();
        JsonNode json = request().body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        } else {
            House houseToRegistre = new House();
            try {
                System.out.println(json.toString());
                houseToRegistre.setHouseId(json.findPath("houseId").asInt());
                houseToRegistre.setPurchasePrice(json.findPath("purchase_price").asDouble());
                houseToRegistre.setTreatmentFees(json.findPath("treatment_fees").asDouble());
                houseToRegistre.setRenovationFeesForRenting(json.findPath("renovation_fees_for_renting").asDouble());
                houseToRegistre.setRenovationFeesForSale(json.findPath("renovation_fees_for_sale").asDouble());
                houseToRegistre.setDiversFees(json.findPath("divers_fees").asDouble());
            } catch (Exception e) {
                webResponce.seteSuccessFailed(ESuccessFailed.FAILED);
                webResponce.setReason("Missing parameter the system did'nt save the details ,חסר פרטים המערכת לא שמרה ת הנתונים" + houseToRegistre.toString());
                e.printStackTrace();
                return badRequest(webResponce.toJson());
            }
            webResponce = setterBL.updateHouseFinancialDetails(houseToRegistre);
            if (webResponce.getSuccessFailed() == ESuccessFailed.FAILED) {
                System.out.println(webResponce.toString());
                return badRequest(webResponce.toJson());
            }
            return ok(webResponce.toJson());
        }
    }

    /**
     * Register new user into the system
     *
     * @param szUserName  - user name
     * @param szTelephone - telephone
     * @param szEmail     - email
     * @param szPassword  - password
     * @return
     * @throws Exception
     */
    public static Result registerNewUser(String szUserName, String szTelephone, String szEmail, String szPassword, String szPermission_manager, String szPermission_view) throws Exception {
        // updateProfilePicture();
        // INFO
        play.Logger.info("<SETTER> Register new user : \n============================\nFor : =>>\nUser name : "
                + szUserName + "\nTelephone : " + szTelephone + "\nEmail : " + szEmail + "\nPassword : " + szPassword
                + "\n============================\n");

        System.out.println("[INFO] " + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime())
                + " <SETTER> Register new user : ");
        System.out.println("============================");
        System.out.println("For : =>>");
        System.out.println("User name : " + szUserName);
        System.out.println("Telephone : " + szTelephone);
        System.out.println("Email : " + szEmail);
        System.out.println("Password : " + szPassword);
        System.out.println("============================");

        if ((szUserName != null) && (szTelephone != null)
                && (szEmail != null) && (szPassword != null) && (szPermission_manager != null) && (szPermission_view != null)) {
            if (setterBL.registerNewUser(szUserName, szTelephone, szEmail, szPassword, szPermission_manager, szPermission_view)) {
                return ok("true");
            } else {
                return badRequest("An internal error as ocurred when trying to register");
            }

        } else {
            return badRequest(
                    "Null pointer screw you! \nyou send your request with an empty user-name or an empty first-name or an last-name or an telephone or an email or an password or an birthdate!");
        }
    }

    /**
     * add user into the system
     *
     * @return
     * @throws Exception
     */
    public static Result addUser() throws Exception {
        webResponce = new WebResponce();
        JsonNode json = request().body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        } else {
            User userToUpdate = new User();
            try {
                System.out.println(json.toString());
                jsonToUserEntity(json, userToUpdate);
            } catch (Exception e) {
                webResponce.seteSuccessFailed(ESuccessFailed.FAILED);
                webResponce.setReason("Missing parameter the system did'nt save the details ,חסר פרטים המערכת לא שמרה את הנתונים" + userToUpdate.toString());
                e.printStackTrace();
                return badRequest(webResponce.toJson());
            }
            System.out.println("Update User: Receive User For Update: " + userToUpdate.toString());
            webResponce = setterBL.addNewUser(userToUpdate.getUsername(), userToUpdate.getTelephone(), userToUpdate.getEmail(), userToUpdate.getPassword(), userToUpdate.convertBooleanToDataBaseFormatString(userToUpdate.getPermissionManager()), userToUpdate.convertBooleanToDataBaseFormatString(userToUpdate.getPermissionView()));
            if (webResponce.getSuccessFailed() == ESuccessFailed.FAILED) {
                System.out.println(webResponce.toString());
                return badRequest(webResponce.toJson());
            }
            System.out.println("The User was added correctly" + userToUpdate.toString());
            return ok(webResponce.getReason());
        }
    }

    /**
     * add user into the system
     *
     * @return
     * @throws Exception
     */
    public static Result deleteUser() throws Exception {
        webResponce = new WebResponce();
        JsonNode json = request().body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        } else {
            User userToDelete = new User();
            jsonToUserEntity(json, userToDelete);
            System.out.println("Delete User: Receive User For Delete: " + userToDelete.toString());
            webResponce = setterBL.deleteUser(userToDelete);
            if (webResponce.getSuccessFailed() == ESuccessFailed.FAILED) {
                System.out.println(webResponce.toString());
                return badRequest(webResponce.toJson());
            }
            System.out.println("The User was deleted correctly" + userToDelete.toString());
            return ok(webResponce.getReason());
        }
    }

    private static Results.Status jsonToUserEntity(JsonNode json, User userToUpdate) throws IOException {
        try {
            System.out.println(json.toString());
            userToUpdate.setUserId(json.findPath("userId").textValue());
            userToUpdate.setUserName(json.findPath("username").textValue());
            userToUpdate.setEmail(json.findPath("email").textValue());
            userToUpdate.setPassword(json.findPath("password").textValue());
            userToUpdate.setEmail(json.findPath("email").textValue());
            userToUpdate.setTelephone(json.findPath("telephone").textValue());
            userToUpdate.setPermissionManager(json.findPath("permissionManager").booleanValue());
            userToUpdate.setPermissionView(json.findPath("permissionView").booleanValue());
        } catch (Exception e) {
            webResponce.seteSuccessFailed(ESuccessFailed.FAILED);
            webResponce.setReason("Missing parameter the system did'nt save the details ,חסר פרטים המערכת לא שמרה את הנתונים" + userToUpdate.toString());
            e.printStackTrace();
            return badRequest(webResponce.toJson());
        }
        return null;
    }


    /**
     * Get file(*can be a profile picture) from client and save in the server
     *
     * @return
     * @throws IOException
     */
    public static Result uploadFile() throws IOException {
        if (updateProfilePicture()) {
            return redirect("assets/index.html");
        } else {
            flash("error", "Missing file");
            return badRequest();
        }
    }

    public static boolean updateProfilePicture(String szUserName) {
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("file");
        Http.MultipartFormData.FilePart picture1 = body.getFile("lfFile");
        if (picture != null) {
            File sourceFile = picture.getFile();
            File dest = new File(System.getProperty("user.dir") + "\\profilsPicture\\" + szUserName + ".jpg");
            try {
                play.Logger.info("<SETTER> save profile picture on file");
                setterBL.copyFile(sourceFile, dest);
            } catch (IOException e) {
                e.printStackTrace();
                play.Logger.info(e.getMessage());
            }
            return true;
        } else {
            flash("error", "Missing file");
            return false;
        }
    }


    /**
     * Get file(*can be a profile picture) from client and save in the server
     *
     * @return
     * @throws IOException
     */
    public static Result setHousePictures(String szHouseName) throws IOException {
        Http.MultipartFormData body = request().body().asMultipartFormData();
        List<Http.MultipartFormData.FilePart> pictures = body.getFiles();
        webResponce = setterBL.setFiles(szHouseName, pictures);
        if (webResponce.getSuccessFailed() == ESuccessFailed.FAILED) {
            return badRequest(webResponce.getReason());
        } else {
            return ok(webResponce.getReason());
        }

    }

    /**
     * Get file(*can be a profile picture) from client and save in the server
     *
     * @return
     * @throws IOException
     */
    public static Result setHouseDocuments(String szHouseName) throws IOException {
        Http.MultipartFormData body = request().body().asMultipartFormData();
        List<Http.MultipartFormData.FilePart> pictures = body.getFiles();
        webResponce = setterBL.setHouseDocuments(szHouseName, pictures);
        if (webResponce.getSuccessFailed() == ESuccessFailed.FAILED) {
            return badRequest(webResponce.getReason());
        } else {
            return ok(webResponce.getReason());
        }

    }

    /**
     * Setting the profile picture of the house will be displayed in the front of the house
     *
     * @return
     * @throws IOException
     */
    public static Result setHouseProfilePictures(String szHouseName) throws IOException {
        Http.MultipartFormData body = request().body().asMultipartFormData();
        List<Http.MultipartFormData.FilePart> pictures = body.getFiles();
        webResponce = setterBL.setHouseProfilePictures(szHouseName, pictures);
        if (webResponce.getSuccessFailed() == ESuccessFailed.FAILED) {
            return badRequest(webResponce.getReason());
        } else {
            return ok(webResponce.getReason());
        }

    }

    public static boolean updateProfilePicture() {
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("file");
        if (picture != null) {
            File sourceFile = picture.getFile();
            File dest = new File(System.getProperty("user.dir") + "\\profilsPicture\\" + picture.getFilename());
            try {
                play.Logger.info("<SETTER> save profile picture on file");
                setterBL.copyFile(sourceFile, dest);
            } catch (IOException e) {
                e.printStackTrace();
                play.Logger.info(e.getMessage());
            }
            return true;
        } else {
            flash("error", "Missing file");
            return false;
        }
    }

    /**
     * Get file(*can be a profile picture) from client and save in the server
     *
     * @return
     * @throws IOException
     */
    public static Result uploadFileWithName(String szUserName) throws IOException {
        if (updateProfilePicture(szUserName)) {
            return redirect("assets/index.html");
        } else {
            flash("error", "Missing file");
            return badRequest();
        }
    }

    public static boolean updateHousePicture(String szUserName) {
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("file");
        if (picture != null) {
            File sourceFile = picture.getFile();
            File dest = new File(System.getProperty("user.dir") + "\\profilsPicture\\" + szUserName + ".jpg");
            try {
                play.Logger.info("<SETTER> save profile picture on file");
                setterBL.copyFile(sourceFile, dest);
            } catch (IOException e) {
                e.printStackTrace();
                play.Logger.info(e.getMessage());
            }
            return true;
        } else {
            flash("error", "Missing file");
            return false;
        }
    }


    /**
     * Inserting house general details.Work
     *
     * @return
     */
    public static Result insertFoodEntity() {
        webResponce = new WebResponce();
        Http.MultipartFormData body = request().body().asMultipartFormData();
        List<Http.MultipartFormData.FilePart> foodPictures = new ArrayList<>();
        if (body != null) {
            foodPictures = body.getFiles();
            for (Http.MultipartFormData.FilePart currPictures : foodPictures) {
                System.out.println(currPictures.getFile());
            }
        }
        String szFoodEntity = body.asFormUrlEncoded().get("foodToUpload")[0].toString();
        JsonNode json = Json.parse(szFoodEntity.toString());
        if (json == null) {
            return badRequest("Expecting Json data");
        } else {
            FoodEntity foodEntityToRegistre = new FoodEntity();
            try {
                System.out.println(json.toString());
                foodEntityToRegistre.setFoodName(json.findPath("foodName").asText());
                foodEntityToRegistre.setDescryption(json.findPath("descryption").asText());
                foodEntityToRegistre.setPrice((float) json.findPath("price").asDouble());
                foodEntityToRegistre.setBassari(json.findPath("bassari").asBoolean());
                foodEntityToRegistre.setHalavi(json.findPath("halavi").asBoolean());
                foodEntityToRegistre.setParve(json.findPath("parve").asBoolean());
                foodEntityToRegistre.setVegetarian(json.findPath("vegetarian").asBoolean());
                foodEntityToRegistre.setVegan(json.findPath("vegan").asBoolean());
                foodEntityToRegistre.setInSucurSal(json.findPath("inSucurSal").asBoolean());
                foodEntityToRegistre.setInKeytring(json.findPath("inKeytring").asBoolean());

            } catch (Exception e) {
                webResponce.seteSuccessFailed(ESuccessFailed.FAILED);
                webResponce.setReason("Missing parameter the system did'nt save the details ,חסר פרטים המערכת לא שמרה ת הנתונים" + foodEntityToRegistre.toString());
                e.printStackTrace();
                return badRequest(webResponce.toJson());
            }

            try {
                webResponce = setterBL.insertNewFood(foodEntityToRegistre, foodPictures);
            } catch (IOException e) {
                webResponce.seteSuccessFailed(ESuccessFailed.FAILED);
                webResponce.setReason("An Errore has occured when try to save pictures for food name" + foodEntityToRegistre.getFoodName());
                e.printStackTrace();
                return badRequest(webResponce.toJson());
            }
            if (webResponce.getSuccessFailed() == ESuccessFailed.FAILED) {
                System.out.println(webResponce.toString());
                return badRequest(webResponce.toJson());
            }
            return ok(webResponce.toJson());
        }
    }
}
