package Entity;

import DB.getterDB;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by yaacov nathan on 05/01/2017.
 */
public class PermissionsView {
    private int nUserId;
    private ArrayList<Integer> lstHouse;

    public PermissionsView() {
        lstHouse = new ArrayList<>();
    }

    public PermissionsView(int nUserId) {
        this.nUserId = nUserId;
        this.lstHouse = new ArrayList<>();
    }

    public int getUserId() {
        return nUserId;
    }

    public void setUserId(int nUserId) {
        this.nUserId = nUserId;
    }

    public ArrayList<Integer> getLstHouse() {
        return lstHouse;
    }

    public void setLstHouse(ArrayList<Integer> lstHouse) {
        this.lstHouse = lstHouse;
    }

    @Override
    public String toString() {
        return "PermissionsView{" +
                "nUserId=" + nUserId +
                ", lstHouse=" + lstHouse +
                '}';
    }

    public String toJson() {
        getterDB getterDB = new getterDB();
        String username = getterDB.getUserNameById(nUserId);
        ArrayList<House> lstAdressPermited = getterDB.getHouseesAdressPermitedByUserId(nUserId);
        String arrAdressOfHousesPermitedToView = ArrayListToJsonArray(lstAdressPermited);
        String ptvToReturn = "{ " +
                "\"UserName\":\"" + username + "\"" +
                ", \"ListOfHouses\" : " + arrAdressOfHousesPermitedToView +
                "}";
        return ptvToReturn;
    }

    public String ArrayListToJsonArray(ArrayList<House> arrayList) {
        StringBuilder sbToReturn = new StringBuilder();
        sbToReturn.append("[");
        Iterator<House> iterat = arrayList.iterator();
        House currHouse = null;
        if (iterat.hasNext()) {
            currHouse = iterat.next();
        }
        while (currHouse != null) {
            sbToReturn.append("{ \"HouseId\":\"" + currHouse.getHouseId() + "\",\"HouseAddress\":\"" + currHouse.getState() + "_" + currHouse.getCity() + "_" + currHouse.getStreet() + "_" + currHouse.getHouseNumber() + "\"}");
            if (iterat.hasNext()) {
                sbToReturn.append(",");
                currHouse = iterat.next();
            } else {
                currHouse = null;
            }
        }
        sbToReturn.append("]");
        return sbToReturn.toString();
    }


}
