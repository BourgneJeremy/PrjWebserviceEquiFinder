package com.equifinder.webservice.model;

import java.sql.Timestamp;

public class PictureFile
{
    private int id;
    private String name;
    private String path;
    private double sizeKiloBytes;
    private String creationDateFr;
    private String creationDateEn;
    
    public int getId() {
        return this.id;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public double getSizeKiloBytes() {
        return this.sizeKiloBytes;
    }
    
    public void setSizeKiloBytes(final double sizeKiloBytes) {
        this.sizeKiloBytes = sizeKiloBytes;
    }
    
    public String getPath() {
        return this.path;
    }
    
    public void setPath(final String path) {
        this.path = path;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getCreationDateFr() {
        return creationDateFr;
    }

    public void setCreationDateFr( String creationDateFr ) {
        this.creationDateFr = creationDateFr;
    }

    public String getCreationDateEn() {
        return creationDateEn;
    }

    public void setCreationDateEn( String creationDateEn ) {
        this.creationDateEn = creationDateEn;
    }

    @Override
    public String toString() {
        return "File [id=" + this.id 
                + ", name=" + this.name 
                + ", path=" + this.path 
                + ", size=" + this.sizeKiloBytes 
                + ", creationDateFr=" + this.creationDateFr 
                + ", creationDateEn=" + this.creationDateEn + "]";
    }
}
