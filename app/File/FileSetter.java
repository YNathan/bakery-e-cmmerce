package File;

import BL.setterBL;
import Entity.ESuccessFailed;
import Entity.House;
import Entity.WebResponce;
import play.mvc.Http;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jacky on 28/11/2016.
 */
public class FileSetter {
    private WebResponce webResponce = new WebResponce();

    public void CreateFolder(String szUserAddress) {
        File theDir;
        theDir = new File(System.getProperty("user.dir") + "\\HousesDocuments\\" + szUserAddress);

        // if the directory does not exist, create it
        if (!theDir.exists()) {
            System.out.println("creating directory: " + szUserAddress);
            boolean result = false;

            try {
                theDir.mkdir();
                result = true;
            } catch (SecurityException se) {
                //handle it
            }
            if (result) {
                System.out.println("DIR created");
            }
        }
    }

    public WebResponce setFiles(String szHouseName, List<Http.MultipartFormData.FilePart> pictures) {
        Iterator<Http.MultipartFormData.FilePart> filePartIterator = pictures.iterator();
        while (filePartIterator.hasNext()) {
            Http.MultipartFormData.FilePart picture = filePartIterator.next();
            if (picture != null) {
                File sourceFile = picture.getFile();
                System.out.println(sourceFile);
                String szDestinationFilePath = System.getProperty("user.dir") + "\\HousesDocuments\\" + szHouseName + "\\" + picture.getFilename();
                File dest = new File(szDestinationFilePath);
                saveAndTryToResizeImg(sourceFile, szDestinationFilePath, dest);
            } else {
                webResponce.seteSuccessFailed(ESuccessFailed.FAILED);
                webResponce.setReason("Error When Try To Upload A Null File To The Server");
            }
        }
        return webResponce;
    }

    private void saveAndTryToResizeImg(File sourceFile, String szDestinationFilePath, File dest) {
        try {
            play.Logger.info("<SETTER> save profile picture on file");
            setterBL.copyFile(sourceFile, dest);
            if (getTypeFile(szDestinationFilePath) != null) {
                resizePhotoAndWrite(szDestinationFilePath, dest);
            }
            webResponce.setReason("Success Upload File To the Sever");
        } catch (IOException e) {
            e.printStackTrace();
            webResponce.seteSuccessFailed(ESuccessFailed.FAILED);
            webResponce.setReason("Error When Upload File To Server");
            play.Logger.info(e.getMessage());
        }
    }

    public WebResponce setHouseDocuments(String szHouseName, List<Http.MultipartFormData.FilePart> pictures) {
        Iterator<Http.MultipartFormData.FilePart> filePartIterator = pictures.iterator();
        while (filePartIterator.hasNext()) {
            Http.MultipartFormData.FilePart picture = filePartIterator.next();
            if (picture != null) {
                File sourceFile = picture.getFile();
                System.out.println(sourceFile);
                String szDestinationFilePath = System.getProperty("user.dir") + "\\HousesDocuments\\" + szHouseName + "\\Docs\\" + picture.getFilename();
                File dest = new File(szDestinationFilePath);
                saveAndTryToResizeImg(sourceFile, szDestinationFilePath, dest);
            } else {
                webResponce.seteSuccessFailed(ESuccessFailed.FAILED);
                webResponce.setReason("Error When Try To Upload A Null File To The Server");
            }
        }
        return webResponce;
    }

    public WebResponce setHouseProfilePicture(String szHouseName, List<Http.MultipartFormData.FilePart> pictures) {
        Iterator<Http.MultipartFormData.FilePart> filePartIterator = pictures.iterator();
        while (filePartIterator.hasNext()) {
            Http.MultipartFormData.FilePart picture = filePartIterator.next();
            if (picture != null) {
                File sourceFile = picture.getFile();
                System.out.println(sourceFile);
                String szDestinationFilePath = System.getProperty("user.dir") + "\\HousesDocuments\\" + szHouseName + "\\Profile\\" + szHouseName + "_profile.jpg";
                File dest = new File(szDestinationFilePath);
                saveAndTryToResizeImg(sourceFile, szDestinationFilePath, dest);
            } else {
                webResponce.seteSuccessFailed(ESuccessFailed.FAILED);
                webResponce.setReason("Error When Try To Upload A Null File To The Server");
            }
        }
        return webResponce;
    }

    private String getTypeFile(String szFile) {
        String szTypeToReturn = null;
        if (szFile.toLowerCase().contains(".jpg")) {
            szTypeToReturn = ".jpg";
        } else if (szFile.toLowerCase().contains(".jpeg")) {
            szTypeToReturn = ".jpeg";
        } else if (szFile.toLowerCase().contains(".png")) {
            szTypeToReturn = ".png";
        }
        return szTypeToReturn;
    }

    private void resizePhotoAndWrite(String szFullFilePath, File fileToReturn) {
        BufferedImage bimg = null;
        try {
            bimg = ImageIO.read(fileToReturn);
            BufferedImage newImage = resizeImage(bimg, bimg.getType());
            File outputfile = new File(szFullFilePath);
            ImageIO.write(newImage, getTypeFile(szFullFilePath), outputfile);
            System.out.println("save picture after name:" + szFullFilePath);
            System.out.println("Get Picture " + szFullFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type) {
        int nNewWidth = originalImage.getWidth();
        int nNewHeight = originalImage.getHeight();
        if (originalImage.getWidth() > 1200) {
            nNewWidth = (originalImage.getWidth() / 100) * 60;
            nNewHeight = (originalImage.getHeight() / 100) * 60;
            System.out.println("Get image Max Than 1200px width: will be converted to / 100 * 60px");
        }

        BufferedImage resizedImage = new BufferedImage(nNewWidth, nNewHeight, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, nNewWidth, nNewHeight, null);
        g.dispose();

        return resizedImage;
    }

    private static void deleteHouse(House m_houseToDelete) {
        String szPathFolderHouse = System.getProperty("user.dir") ;
    }

}
