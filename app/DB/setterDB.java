package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import Entity.ESuccessFailed;
import Entity.FoodEntity;
import Entity.House;
import Entity.WebResponce;
import controllers.Application;
import File.FileGetter;

/**
 * @author Yaacov
 */
public class setterDB {
    private static Connection connect;
    private static ResultSet resultSet;
    private static PreparedStatement preparedStatement;
    private static String TABLE_USERS_NAME = "shamayim.users";
    private static String TABLE_HOUSE_NAME = "shamayim.house";
    private static String TABLE_PERMITED_VIEW_NAME = "shamayim.permissions_view";
    private static String TABLE_FOOD_NAME = "folies.food";
    private static String DATA_BASE_USER_NAME = "root";
    private static String DATA_BASE_PASSWORD_NAME = Application.szDbPassword;
    private WebResponce webResponce = new WebResponce();
    private static FileGetter fileGetter = new FileGetter();

    public setterDB() {
        Application.szDbPassword = fileGetter.getDataBasePassword();
    }

    /**
     * This method wiil delete a user
     *
     * @param nUserId the table that want to delete
     */
    public WebResponce deleteUser(int nUserId) {
        webResponce = new WebResponce();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME
                    + "&password=" + DATA_BASE_PASSWORD_NAME);
            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect.prepareStatement("DELETE FROM " + TABLE_USERS_NAME + " WHERE user_id=" + nUserId + ";");
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            webResponce = new WebResponce(ESuccessFailed.FAILED, "Error When Try To Delete A User");
            e.printStackTrace();
            play.Logger.error(e.getMessage());
        } finally {
            // Closing the resultSet
            close();
        }
        // Commit changes
        commit();
        webResponce.setReason("Delete User Success. המשתמש הוסר בהצלחה");
        return webResponce;
    }


    /**
     * This method wiil delete a house
     *
     * @param nHousId the table that want to delete
     */
    public WebResponce deleteHouse(int nHousId) {
        webResponce = new WebResponce();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME
                    + "&password=" + DATA_BASE_PASSWORD_NAME);
            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect.prepareStatement("DELETE FROM " + TABLE_HOUSE_NAME + " WHERE user_id=" + nHousId + ";");
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            webResponce = new WebResponce(ESuccessFailed.FAILED, "Error When Try To Delete A User");
            e.printStackTrace();
            play.Logger.error(e.getMessage());
        } finally {
            // Closing the resultSet
            close();
        }
        // Commit changes
        commit();
        webResponce.setReason("Delete User Success. המשתמש הוסר בהצלחה");
        return webResponce;
    }

    /**
     * Register a new user into the data-base
     *
     * @param userName  - user name
     * @param telephone - telephone
     * @param email     - email
     * @param password  - password
     * @return
     * @throws Exception
     */
    public boolean registerNewUser(String userName, String telephone, String email,
                                   String password, int nPermissionManager, int nPermissionView) throws Exception {

        boolean bWasRegister = true;
        // INFO
        play.Logger.info(" " + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime())
                + " <SETTER> Register new user : ");
        System.out.println("============================");
        System.out.println("For : =>>");
        System.out.println("User name : " + userName);
        System.out.println("Telephone : " + telephone);
        System.out.println("Email : " + email);
        System.out.println("Password : " + password);
        System.out.println("============================");

        try {
            // The newInstance() call is a work around for some broken Java
            // implementations
            Class.forName("com.mysql.jdbc.Driver").newInstance();


            String url = "jdbc:mysql://localhost/shamayim?characterEncoding=UTF-8";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //connect = DriverManager.getConnection(url, DATA_BASE_USER_NAME, DATA_BASE_USER_NAME);

            // String strConnection=
            // "Server=127.0.0.1;Port=3306;Database=shamayim;Uid=root;password=Ny7516399;";
            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME
                    + "&password=" + DATA_BASE_PASSWORD_NAME);

            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect.prepareStatement("insert into " + TABLE_USERS_NAME
                    + " (user_name,telephone,email,password,permission_manager,permission_view) values (?, ?, ? , ?, ?,?)");
            play.Logger.info(" Insert new user to the data-base");
            // Parameters start with 1
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, telephone);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);
            preparedStatement.setInt(5, nPermissionManager);
            preparedStatement.setInt(6, nPermissionView);
            preparedStatement.executeUpdate();
            System.out.println("registered successfully!!!");
            System.out.println("============================");
        } catch (Exception e) {
            System.out.println("a problem occurred when try to registered");
            System.out.println("==========================================");
            bWasRegister = false;
            play.Logger.info("<setterDB> " + e.getMessage());
        } finally {
            // Closing the resultSet
            close();
        }
        // Commit changes;
        commit();
        return bWasRegister;

    }


    /**
     * Register a new user into the data-base
     *
     * @param userName  - user name
     * @param telephone - telephone
     * @param email     - email
     * @param password  - password
     * @return
     * @throws Exception
     */
    public WebResponce addNewUser(String userName, String telephone, String email,
                                  String password, int nPermissionManager, int nPermissionView) throws Exception {

        webResponce = new WebResponce();
        // INFO
        play.Logger.info(" " + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime())
                + " <SETTER> Register new user : ");
        System.out.println("============================");
        System.out.println("For : =>>");
        System.out.println("User name : " + userName);
        System.out.println("Telephone : " + telephone);
        System.out.println("Email : " + email);
        System.out.println("Password : " + password);
        System.out.println("============================");

        try {
            // The newInstance() call is a work around for some broken Java
            // implementations
            Class.forName("com.mysql.jdbc.Driver").newInstance();


            String url = "jdbc:mysql://localhost/shamayim?characterEncoding=UTF-8";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //connect = DriverManager.getConnection(url, DATA_BASE_USER_NAME, DATA_BASE_USER_NAME);

            // String strConnection=
            // "Server=127.0.0.1;Port=3306;Database=shamayim;Uid=root;password=Ny7516399;";
            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME
                    + "&password=" + DATA_BASE_PASSWORD_NAME);

            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect.prepareStatement("insert into " + TABLE_USERS_NAME
                    + " (user_name,telephone,email,password,permission_manager,permission_view) values (?, ?, ? , ?, ?,?)");
            play.Logger.info(" Insert new user to the data-base");
            // Parameters start with 1
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, telephone);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);
            preparedStatement.setInt(5, nPermissionManager);
            preparedStatement.setInt(6, nPermissionView);
            preparedStatement.executeUpdate();
            System.out.println("registered successfully!!!");
            System.out.println("============================");
            webResponce.setReason("registered successfully!!!. המשתמש התווסף בהצלחה למערכת");
        } catch (Exception e) {
            System.out.println("a problem occurred when try to add the new user. ארעה שגיעה במהלך הוספת המשתמש");
            System.out.println("==========================================");
            webResponce.seteSuccessFailed(ESuccessFailed.FAILED);
            webResponce.setReason("a problem occurred when try to add the new user. ארעה שגיעה במהלך הוספת המשתמש");
            play.Logger.info("<setterDB> " + e.getMessage());
        } finally {
            // Closing the resultSet
            close();
        }
        // Commit changes;
        commit();
        return webResponce;

    }

    /**
     * Register a new user into the data-base
     *
     * @param userName  - user name
     * @param telephone - telephone
     * @param email     - email
     * @param password  - password
     * @return
     * @throws Exception
     */
    public WebResponce updateUser(int nUserId, String userName, String telephone, String email, String password, int nPermissionManager, int nPermissionView) throws Exception {
        webResponce = new WebResponce();
        // INFO
        play.Logger.info(" " + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime())
                + " <SETTER> Update user : ");
        System.out.println("============================");
        System.out.println("For : User Id =>>");
        System.out.println("User name : " + userName);
        System.out.println("Telephone : " + telephone);
        System.out.println("Email : " + email);
        System.out.println("Password : " + password);
        System.out.println("============================");

        try {
            // The newInstance() call is a work around for some broken Java
            // implementations
            Class.forName("com.mysql.jdbc.Driver").newInstance();


            String url = "jdbc:mysql://localhost/shamayim?characterEncoding=UTF-8";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //connect = DriverManager.getConnection(url, DATA_BASE_USER_NAME, DATA_BASE_USER_NAME);

            // String strConnection=
            // "Server=127.0.0.1;Port=3306;Database=shamayim;Uid=root;password=Ny7516399;";
            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME
                    + "&password=" + DATA_BASE_PASSWORD_NAME);

            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect.prepareStatement("update " + TABLE_USERS_NAME + " set user_name='" + userName + "',telephone='" + telephone + "',email='" + email + "',password='" + password + "',permission_manager=" + nPermissionManager + ",permission_view=" + nPermissionView + " where user_id=" + nUserId + ";");
            play.Logger.info(" Update user to the data-base");
            preparedStatement.executeUpdate();
            System.out.println("update successfully");
            System.out.println("============================");
            webResponce.setReason("update successfully");
        } catch (Exception e) {
            System.out.println("a problem occurred when try to update");
            System.out.println("==========================================");
            play.Logger.info("<setterDB> " + e.getMessage());
            webResponce.seteSuccessFailed(ESuccessFailed.FAILED);
            webResponce.setReason("a problem occurred when try to update");
        } finally {
            // Closing the resultSet
            close();
        }
        // Commit changes;
        commit();
        return webResponce;

    }

    public WebResponce updateHouseDetails(House m_house) {
        WebResponce webResponceToReturn = new WebResponce();
        // INFO
        play.Logger.info(" " + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime())
                + " <SETTER> Update house : ");
        System.out.println("============================");
        System.out.println("For : =>>");
        System.out.println(m_house.toStringMailFormat());
        System.out.println("============================");

        try {
            // The newInstance() call is a work around for some broken Java
            // implementations
            Class.forName("com.mysql.jdbc.Driver").newInstance();


            String url = "jdbc:mysql://localhost/shamayim?characterEncoding=UTF-8";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //connect = DriverManager.getConnection(url, DATA_BASE_USER_NAME, DATA_BASE_USER_NAME);

            // String strConnection=
            // "Server=127.0.0.1;Port=3306;Database=shamayim;Uid=root;password=Ny7516399;";
            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME
                    + "&password=" + DATA_BASE_PASSWORD_NAME);

            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect.prepareStatement("update " + TABLE_HOUSE_NAME
                    + " set state = ?, city = ?, street = ?, house_number = ?, zip_code = ?, house_kind = ?, number_of_rooms = ?, number_of_living_rooms = ?, number_of_kitchens = ?, number_of_bedrooms = ?, number_of_bathrooms = ?, location_kind = ?, comments = ?, purchase_price = ?, treatment_fees = ?, renovation_fees_for_sale = ?, renovation_fees_for_renting = ?, divers_fees = ? WHERE house_id = " + m_house.getHouseId() + ";");
            play.Logger.info(" update house in the data-base");

            preparedStatement.setString(1, m_house.getState());
            preparedStatement.setString(2, m_house.getCity());
            preparedStatement.setString(3, m_house.getStreet());
            preparedStatement.setInt(4, m_house.getHouseNumber());
            preparedStatement.setInt(5, 00000);
            preparedStatement.setInt(6, m_house.getHouseKind().getValue());
            preparedStatement.setInt(7, m_house.getNumberOfRooms());
            preparedStatement.setInt(8, m_house.getNumberOfLivingRooms());
            preparedStatement.setInt(9, m_house.getNumberOfKitchens());
            preparedStatement.setInt(10, m_house.getNumberOfBedrooms());
            preparedStatement.setInt(11, m_house.getNumberOfBathrooms());
            preparedStatement.setInt(12, m_house.getLocationKind().getValue());
            preparedStatement.setString(13, m_house.getComments());
            preparedStatement.setDouble(14, m_house.getPurchasePrice());
            preparedStatement.setDouble(15, m_house.getTreatmentFees());
            preparedStatement.setDouble(16, m_house.getRenovationFeesForSale());
            preparedStatement.setDouble(17, m_house.getRenovationFeesForRenting());
            preparedStatement.setDouble(18, m_house.getDiversFees());
            preparedStatement.executeUpdate();
            System.out.println("update successfully!");
        } catch (Exception e) {
            System.out.println("a problem occurred when try to update");
            System.out.println("=====================================");
            webResponceToReturn.seteSuccessFailed(ESuccessFailed.FAILED);
            webResponceToReturn.setReason("a problem occurred when try to update");
            play.Logger.info("<setterDB> " + e.getMessage());
        } finally {
            // Closing the resultSet
            close();
        }
        // Commit changes;
        commit();
        return webResponceToReturn;

    }

    public WebResponce setNewHouseDetails(House m_house) {
        WebResponce webResponceToReturn = new WebResponce();
        // INFO
        play.Logger.info(" " + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime())
                + " <SETTER> Register new user : ");
        System.out.println("============================");
        System.out.println("For : =>>");
        System.out.println(m_house.toString());
        System.out.println("============================");

        try {
            // The newInstance() call is a work around for some broken Java
            // implementations
            Class.forName("com.mysql.jdbc.Driver").newInstance();


            String url = "jdbc:mysql://localhost/shamayim?characterEncoding=UTF-8";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //connect = DriverManager.getConnection(url, DATA_BASE_USER_NAME, DATA_BASE_USER_NAME);

            // String strConnection=
            // "Server=127.0.0.1;Port=3306;Database=shamayim;Uid=root;password=Ny7516399;";
            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME
                    + "&password=" + DATA_BASE_PASSWORD_NAME);

            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect.prepareStatement("insert into " + TABLE_HOUSE_NAME
                    + " (state, city, street, house_number,zip_code, house_kind, number_of_rooms, number_of_living_rooms, number_of_kitchens, number_of_bedrooms, number_of_bathrooms, location_kind, comments,purchase_price,treatment_fees,renovation_fees_for_sale,renovation_fees_for_renting,divers_fees) values (?, ?, ?, ?, ? , ?, ?, ?, ?, ?, ? , ?,?,?,?,?,?,?)");
            play.Logger.info(" Insert new user to the data-base");
            // Parameters start with 1
            preparedStatement.setString(1, m_house.getState());
            preparedStatement.setString(2, m_house.getCity());
            preparedStatement.setString(3, m_house.getStreet());
            preparedStatement.setInt(4, m_house.getHouseNumber());
            preparedStatement.setInt(5, m_house.getZipCode());
            preparedStatement.setInt(6, m_house.getHouseKind().getValue());
            preparedStatement.setInt(7, m_house.getNumberOfRooms());
            preparedStatement.setInt(8, m_house.getNumberOfLivingRooms());
            preparedStatement.setInt(9, m_house.getNumberOfKitchens());
            preparedStatement.setInt(10, m_house.getNumberOfBedrooms());
            preparedStatement.setInt(11, m_house.getNumberOfBathrooms());
            preparedStatement.setInt(12, m_house.getLocationKind().getValue());
            preparedStatement.setString(13, m_house.getComments());
            preparedStatement.setDouble(14, m_house.getPurchasePrice());
            preparedStatement.setDouble(15, m_house.getTreatmentFees());
            preparedStatement.setDouble(16, m_house.getRenovationFeesForSale());
            preparedStatement.setDouble(17, m_house.getRenovationFeesForRenting());
            preparedStatement.setDouble(18, m_house.getDiversFees());
            preparedStatement.executeUpdate();
            System.out.println("registered successfully!!!");
            System.out.println("============================");
        } catch (Exception e) {
            System.out.println("a problem occurred when try to registered");
            System.out.println("==========================================");
            webResponceToReturn.seteSuccessFailed(ESuccessFailed.FAILED);
            webResponceToReturn.setReason("a problem occurred when try to registered");
            play.Logger.info("<setterDB> " + e.getMessage());
        } finally {
            // Closing the resultSet
            close();
        }
        // Commit changes;
        commit();
        return webResponceToReturn;

    }

    /**
     * update a house general details into the data-base
     *
     * @return
     * @throws Exception
     */
    public WebResponce updateHouseFinancialDetails(House m_House) {
        webResponce = new WebResponce();
        // INFO
        play.Logger.info(" " + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime())
                + " <SETTER> Update house : " + m_House.toStringMailFormat());
        System.out.println("Update house :" + m_House.toStringMailFormat());


        try {
            // The newInstance() call is a work around for some broken Java
            // implementations
            Class.forName("com.mysql.jdbc.Driver").newInstance();


            String url = "jdbc:mysql://localhost/shamayim?characterEncoding=UTF-8";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //connect = DriverManager.getConnection(url, DATA_BASE_USER_NAME, DATA_BASE_USER_NAME);

            // String strConnection=
            // "Server=127.0.0.1;Port=3306;Database=shamayim;Uid=root;password=Ny7516399;";
            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME
                    + "&password=" + DATA_BASE_PASSWORD_NAME);

            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect.prepareStatement("update " + TABLE_HOUSE_NAME + " set purchase_price=" + m_House.getPurchasePrice() + " ,treatment_fees=" + m_House.getTreatmentFees() + " ,renovation_fees_for_sale=" + m_House.getRenovationFeesForSale() + " ,renovation_fees_for_renting=" + m_House.getRenovationFeesForRenting() + " ,divers_fees=" + m_House.getDiversFees() + " where house_id=" + m_House.getHouseId() + ";");
            play.Logger.info(" Update house general details to the data-base");
            preparedStatement.executeUpdate();
            System.out.println("update successfully");
            System.out.println("============================");
            webResponce.setReason("update successfully");
        } catch (Exception e) {
            System.out.println("a problem occurred when try to update house financial details");
            System.out.println("==========================================");
            play.Logger.info("<setterDB> " + e.getMessage());
            webResponce.seteSuccessFailed(ESuccessFailed.FAILED);
            webResponce.setReason("a problem occurred when try to update house financial details");
        } finally {
            // Closing the resultSet
            close();
        }
        // Commit changes;
        commit();
        return webResponce;
    }


    /**
     * update a house financial details into the data-base
     *
     * @return
     * @throws Exception
     */
    public WebResponce updateHouseGeneralDetails(House m_House) {
        webResponce = new WebResponce();
        // INFO
        play.Logger.info(" " + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime())
                + " <SETTER> Update house : " + m_House.toStringMailFormat());
        System.out.println("Update house :" + m_House.toStringMailFormat());


        try {
            // The newInstance() call is a work around for some broken Java
            // implementations
            Class.forName("com.mysql.jdbc.Driver").newInstance();


            String url = "jdbc:mysql://localhost/shamayim?characterEncoding=UTF-8";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //connect = DriverManager.getConnection(url, DATA_BASE_USER_NAME, DATA_BASE_USER_NAME);

            // String strConnection=
            // "Server=127.0.0.1;Port=3306;Database=shamayim;Uid=root;password=Ny7516399;";
            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME
                    + "&password=" + DATA_BASE_PASSWORD_NAME);

            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect.prepareStatement("update " + TABLE_HOUSE_NAME + " set house_kind=" + m_House.getHouseKind().getValue() + " ,number_of_rooms=" + m_House.getNumberOfRooms() + " ,number_of_living_rooms=" + m_House.getNumberOfLivingRooms() + " ,number_of_kitchens=" + m_House.getNumberOfKitchens() + " ,number_of_bedrooms=" + m_House.getNumberOfBedrooms() + " ,number_of_bathrooms=" + m_House.getNumberOfBathrooms() + " ,location_kind=" + m_House.getLocationKind().getValue() + " ,comments=\"" + m_House.getComments() + "\" where house_id=" + m_House.getHouseId() + ";");
            play.Logger.info(" Update house general details to the data-base");
            preparedStatement.executeUpdate();
            System.out.println("update successfully");
            System.out.println("============================");
            webResponce.setReason("update successfully");
        } catch (Exception e) {
            System.out.println("a problem occurred when try to update house general details");
            System.out.println("==========================================");
            play.Logger.info("<setterDB> " + e.getMessage());
            webResponce.seteSuccessFailed(ESuccessFailed.FAILED);
            webResponce.setReason("a problem occurred when try to update house general details");
        } finally {
            // Closing the resultSet
            close();
        }
        // Commit changes;
        commit();
        return webResponce;
    }

    /**
     * Commit changes of data base
     */
    public void commit() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME
                    + "&password=" + DATA_BASE_PASSWORD_NAME);
            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect.prepareStatement("commit");
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            play.Logger.error(e.getMessage());
        } finally {
            // Closing the resultSet
            close();
        }
    }

    // Closing the resultSet
    private static void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

    public void deletePermitedToView(int nUserId, int houseId) {
        webResponce = new WebResponce();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME
                    + "&password=" + DATA_BASE_PASSWORD_NAME);
            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect.prepareStatement("DELETE FROM " + TABLE_PERMITED_VIEW_NAME + " WHERE user_id=" + nUserId + " AND house_id=" + houseId + ";");
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            webResponce = new WebResponce(ESuccessFailed.FAILED, "Error When Try To Delete A the permission");
            e.printStackTrace();
            play.Logger.error(e.getMessage());
        } finally {
            // Closing the resultSet
            close();
        }
        // Commit changes
        commit();
        webResponce.setReason("Delete User Success. המשתמש הוסר בהצלחה");
    }

    public void addNewPremitedToView(int nUserId, int houseId) {
        try {
            // The newInstance() call is a work around for some broken Java
            // implementations
            Class.forName("com.mysql.jdbc.Driver").newInstance();


            String url = "jdbc:mysql://localhost/shamayim?characterEncoding=UTF-8";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //connect = DriverManager.getConnection(url, DATA_BASE_USER_NAME, DATA_BASE_USER_NAME);

            // String strConnection=
            // "Server=127.0.0.1;Port=3306;Database=shamayim;Uid=root;password=Ny7516399;";
            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME
                    + "&password=" + DATA_BASE_PASSWORD_NAME);

            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect.prepareStatement("insert into " + TABLE_PERMITED_VIEW_NAME
                    + " (user_id,house_id) values (?, ?)");
            play.Logger.info(" Insert new user to the data-base");
            // Parameters start with 1
            preparedStatement.setInt(1, nUserId);
            preparedStatement.setInt(2, houseId);
            preparedStatement.executeUpdate();
            System.out.println("add permission to view for user-id : " + nUserId + " To House-Id : " + houseId + " successfully!!!");
            System.out.println("============================");
            webResponce.setReason("add permission to view for user-id : " + nUserId + " To House-Id : " + houseId + " successfully!!!");
        } catch (Exception e) {
            System.out.println("a problem occurred when try to add the permission to view for user-id : " + nUserId + " To House-Id : " + houseId + ". ארעה שגיעה במהלך הוספת המשתמש");
            System.out.println("==========================================");
            webResponce.seteSuccessFailed(ESuccessFailed.FAILED);
            webResponce.setReason("a problem occurred when try to add the permission to view for user-id : " + nUserId + " To House-Id : " + houseId + ". ארעה שגיעה במהלך הוספת המשתמש");
            play.Logger.info("<setterDB> " + e.getMessage());
        } finally {
            // Closing the resultSet
            close();
        }
        // Commit changes;
        commit();
    }

    public void addNewFood(ArrayList<FoodEntity> foodEntities) {
        try {
            // The newInstance() call is a work around for some broken Java
            // implementations
            Class.forName("com.mysql.jdbc.Driver").newInstance();


            String url = "jdbc:mysql://localhost/shamayim?characterEncoding=UTF-8";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //connect = DriverManager.getConnection(url, DATA_BASE_USER_NAME, DATA_BASE_USER_NAME);

            // String strConnection=
            // "Server=127.0.0.1;Port=3306;Database=shamayim;Uid=root;password=Ny7516399;";
            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME
                    + "&password=" + DATA_BASE_PASSWORD_NAME);

            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect.prepareStatement("insert into " + TABLE_PERMITED_VIEW_NAME
                    + " (user_id,house_id) values (?, ?)");
            play.Logger.info(" Insert new user to the data-base");
            // Parameters start with 1
            //preparedStatement.setInt(1, nUserId);
            //preparedStatement.setInt(2, houseId);
            preparedStatement.executeUpdate();
            //System.out.println("add permission to view for user-id : " + nUserId + " To House-Id : " + houseId + " successfully!!!");
            System.out.println("============================");
            //webResponce.setReason("add permission to view for user-id : " + nUserId + " To House-Id : " + houseId + " successfully!!!");
        } catch (Exception e) {
            //System.out.println("a problem occurred when try to add the permission to view for user-id : " + nUserId + " To House-Id : " + houseId + ". ארעה שגיעה במהלך הוספת המשתמש");
            System.out.println("==========================================");
            webResponce.seteSuccessFailed(ESuccessFailed.FAILED);
            //webResponce.setReason("a problem occurred when try to add the permission to view for user-id : " + nUserId + " To House-Id : " + houseId + ". ארעה שגיעה במהלך הוספת המשתמש");
            play.Logger.info("<setterDB> " + e.getMessage());
        } finally {
            // Closing the resultSet
            close();
        }
        // Commit changes;
        commit();
    }

    /**
     * update a house financial details into the data-base
     *
     * @return
     * @throws Exception
     */
    public WebResponce insertNewFood(FoodEntity m_foodEntity) {
        WebResponce webResponce = new WebResponce();
        // INFO
        play.Logger.info(" " + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime())
                + " <SETTER> insert new food : " + m_foodEntity.toString());

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connect = DriverManager.getConnection("jdbc:mysql://localhost/folies?user=" + DATA_BASE_USER_NAME
                    + "&password=" + DATA_BASE_PASSWORD_NAME);
            preparedStatement = connect.prepareStatement("insert into " + TABLE_FOOD_NAME
                    + " (foodName,descryption,price,isParve,isHalavi,isBassari,isVegetarian,isVegan,isInKeytring,isInSucurSal) values (?,?,?,?,?,?,?,?,?,?)");
            play.Logger.info(" Insert food to the data-base");
            preparedStatement.setString(1, m_foodEntity.getFoodName());
            preparedStatement.setString(2, m_foodEntity.getDescryption());
            preparedStatement.setFloat(3, m_foodEntity.getPrice());
            preparedStatement.setBoolean(4, m_foodEntity.isParve());
            preparedStatement.setBoolean(5, m_foodEntity.isHalavi());
            preparedStatement.setBoolean(6, m_foodEntity.isBassari());
            preparedStatement.setBoolean(7, m_foodEntity.isVegetarian());
            preparedStatement.setBoolean(8, m_foodEntity.isVegan());
            preparedStatement.setBoolean(9, m_foodEntity.isInKeytring());
            preparedStatement.setBoolean(10, m_foodEntity.isInSucurSal());
            preparedStatement.executeUpdate();
            System.out.println("registered successfully!");
            System.out.println("============================");
            webResponce.setReason("registered successfully!");
            webResponce.seteSuccessFailed(ESuccessFailed.SUCCESS);
        } catch (Exception e) {
            System.out.println("a problem occurred when try to registered");
            System.out.println("==========================================");
            webResponce.setReason("a problem occurred when try to registered");
            webResponce.seteSuccessFailed(ESuccessFailed.FAILED);
            play.Logger.info("<setterDB> " + e.getMessage());
        } finally {
            // Closing the resultSet
            close();
        }
        // Commit changes;
        commit();
        return webResponce;

    }


}

