package com.equifinder.webservice.resource;

import javax.ws.rs.core.Context;
import javax.ws.rs.Produces;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import com.equifinder.webservice.model.PictureFile;
import com.equifinder.webservice.service.FilesService;

import javax.ws.rs.PathParam;
import javax.ws.rs.Path;

@Path("/pictures")
public class FilesResource
{
    FilesService filesService;
    
    public FilesResource() {
        this.filesService = new FilesService();
    }
    
    // Info from picture
    // -----------------
    @GET
    @Path("/info/{fileId}")
    @Produces({ "application/json" })
    public PictureFile getId(@PathParam("fileId") final int fileId) {
        return this.filesService.getFile(fileId);
    }
    
    // Download picture
    // ----------------
    @GET
    @Path("/download/{pictureNameAndExt}")
    public void downloadImage(@Context final HttpServletResponse res, @PathParam("pictureNameAndExt") final String pictureNameAndExt) throws Exception {
        // filesService method
        this.filesService.downloadImage(res, pictureNameAndExt);
    }
    
    // Show picture
    // ------------
    @GET
    @Path("/show/{pictureId}")
    public void showImage(@Context final HttpServletResponse res, @PathParam("pictureId") final int pictureId) throws Exception {
        this.filesService.getImage(res, pictureId);
    }
    
    @GET
    @Path("/show/thumbnail/{pictureId}")
    public void showImageThumbnail(@Context final HttpServletResponse res, @PathParam("pictureId") final int pictureId) throws Exception {
        this.filesService.getImageThumbnail(res, pictureId);
    }
    
    // Delete picture
    // --------------
    @GET
    @Path("/delete/{pictureId}")
    public void deleteImage(@Context final HttpServletResponse res, @PathParam("pictureId") final int pictureId) throws Exception {
        this.filesService.deleteImage( res, pictureId );;
    }
}
