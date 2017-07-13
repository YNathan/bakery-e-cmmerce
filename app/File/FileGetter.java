package File;

import Entity.WebResponce;
import play.Logger;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by jacky on 28/11/2016.
 */
public class FileGetter {
    WebResponce webResponce = new WebResponce();

    public ArrayList<String> getImagesName(String szDocumentsDir, String szFolderName) {
        ArrayList<String> lstFileNamesToReturn = new ArrayList<>();
        File folder = new File(System.getProperty("user.dir") + "\\" + szDocumentsDir + "\\" + szFolderName);
        File[] listOfFiles = folder.listFiles();


        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                if ((listOfFiles[i].getName().toLowerCase().contains(".jpg")) || (listOfFiles[i].getName().toLowerCase().contains(".png")) || (listOfFiles[i].getName().toLowerCase().contains(".jpeg"))) {
                    lstFileNamesToReturn.add(szFolderName + "/" + listOfFiles[i].getName());
                    Logger.debug("File " + szDocumentsDir + "/" + szFolderName + "/" + listOfFiles[i].getName());
                }
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }
        return lstFileNamesToReturn;
    }

    public ArrayList<String> getFilesName(String szDocumentsDir, String szFolderName) {
        ArrayList<String> lstFileNamesToReturn = new ArrayList<>();
        File folder = new File(System.getProperty("user.dir") + "\\" + szDocumentsDir + "\\" + szFolderName);
        System.out.println("[FileGetter] Get list of file from folder " + szFolderName);
        Logger.debug("[FileGetter] Get list of file from folder " + szFolderName);
        File[] listOfFiles = folder.listFiles();


        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                lstFileNamesToReturn.add(szFolderName + "/" + listOfFiles[i].getName());
                Logger.debug("File " + szDocumentsDir + "/" + szFolderName + "/" + listOfFiles[i].getName());
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }
        return lstFileNamesToReturn;
    }

    public File getFile(String szPathName) {
        System.out.println("[FileGetter] Get File " + szPathName);
        Logger.debug("[FileGetter] Get File " + szPathName);
        File fileToReturn = new File(szPathName);
        return fileToReturn;
    }

    public String getDataBasePassword() {
        String szDbPassToReturn = "";
        String szDBFilePass = System.getProperty("user.dir") + "\\Dossier\\database";
        File DbFilePass;
        DbFilePass = new File(szDBFilePass);
        try {
            FileReader fileReader = new FileReader(szDBFilePass);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            szDbPassToReturn = bufferedReader.readLine();
        } catch (FileNotFoundException e) {
            Logger.error(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Logger.error(e.getMessage());
        }
        return szDbPassToReturn;
    }
}
