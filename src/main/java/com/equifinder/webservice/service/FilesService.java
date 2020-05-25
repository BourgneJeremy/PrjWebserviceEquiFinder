package com.equifinder.webservice.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import com.equifinder.webservice.model.PictureFile;
import com.equifinder.webservice.database.H2DBConnection;

public class FilesService
{
    private H2DBConnection h2db;
    public static final String FILES_FOLDER = ".//EquiFinderFiles//";
    
    public FilesService() {
        this.h2db = new H2DBConnection();
    }
    
    public PictureFile getFile(final int fileId) {
        return this.h2db.getFileById(fileId);
    }
    
    public void downloadImage(@Context final HttpServletResponse res, final String pictureNameAndExt) throws IOException {
        int picId = this.h2db.getImageId( pictureNameAndExt );
        
        // get the picture extension from id
        File picFile = new File(pictureNameAndExt);
        String picFileExt = this.getFileExtension( picFile );
        
        // get picture name
        String localPicName = Integer.toString(picId) + picFileExt;
        
        // Find the picture
        final Path path = Paths.get(".//EquiFinderFiles//" + localPicName, new String[0]);
        res.getOutputStream().write(Files.readAllBytes(path));
        res.getOutputStream().flush(); 
    }
    
    public void getImage(@Context final HttpServletResponse res, final int pictureId) throws IOException {
        final PictureFile file = this.getFile(pictureId);
        final File tempFile = new File(file.getName());
        final String ext = this.getFileExtension(tempFile);
        final String pictureName = String.valueOf(pictureId) + ext;
        final Path path = Paths.get(".//EquiFinderFiles//" + pictureName, new String[0]);
        res.getOutputStream().write(Files.readAllBytes(path));
        res.getOutputStream().flush();
    }
    
    public void getImageThumbnail(@Context final HttpServletResponse res, final int pictureId) throws IOException {
        final PictureFile file = this.getFile(pictureId);
        final File tempFile = new File(file.getName());
        final String ext = this.getFileExtension(tempFile);
        final String pictureName = String.valueOf(pictureId) + ext;
        final Path path = Paths.get(".//EquiFinderFiles//thumbnails//" + pictureName, new String[0]);
        res.getOutputStream().write(Files.readAllBytes(path));
        res.getOutputStream().flush();
    }
    
    public void deleteImage(@Context final HttpServletResponse res, final int pictureId) throws IOException {
        final PictureFile file = this.getFile(pictureId);
        final File tempFile = new File(file.getName());
        final String ext = this.getFileExtension(tempFile);
        final String pictureName = String.valueOf(pictureId) + ext;
        
        // delete picture from database here
        this.h2db.deleteImage( pictureId );
        
        File picFile = new File(".//EquiFinderFiles//" + pictureName);
        File thumbnailFile = new File(".//EquiFinderFiles//thumbnails//" + pictureName);
        
        // delete picture from disk here
        picFile.delete();
        thumbnailFile.delete();
    }
    
    private String getFileExtension(final File file) {
        final String name = file.getName();
        final int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return name.substring(lastIndexOf);
    }
}
