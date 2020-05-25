package com.equifinder.webservice.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import com.equifinder.webservice.model.PictureFile;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Connection;

public class H2DBConnection
{
    private static final String JDBC_Driver = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/test";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    Connection conn;
    Statement stmt;
    
    public H2DBConnection() {
        this.conn = null;
        this.stmt = null;
    }
    
    public PictureFile getFileById(final int id) {
        final PictureFile file = new PictureFile();
        try {
            Class.forName("org.h2.Driver");
            this.conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            this.stmt = this.conn.createStatement();
            final String sql = "SELECT * FROM Files WHERE id=" + id;
            final ResultSet rs = this.stmt.executeQuery(sql);
            while (rs.next()) {
                final int resId = Integer.parseInt(rs.getString("id"));
                final String resName = rs.getString("name");
                final double resSizeKiloBytes = Math.round( Double.parseDouble(rs.getString("Size")) / 1024 );
                final Timestamp resTimestamp = rs.getTimestamp("Timestamp");
                
                String strTimestamp = resTimestamp.toString();
                String frDate = this.getFrDate( strTimestamp );
                String enDate = this.getEnDate( strTimestamp );
                
                String frTime = this.getFrTime( strTimestamp );
                String enTime = this.getEnTime( strTimestamp );

                String creationDateFr = frDate + "_" + frTime;
                System.out.println( creationDateFr );
                
                String creationDateEn = enDate + "_" + enTime;
                System.out.println( creationDateEn );
                
                file.setId(resId);
                file.setName(resName);
                file.setSizeKiloBytes(resSizeKiloBytes);
                file.setCreationDateFr( creationDateFr );
                file.setCreationDateEn( creationDateEn );
            }
            this.stmt.close();
            this.conn.close();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e2) {
            e2.printStackTrace();
        }
        finally {
            if (this.stmt != null) {
                try {
                    this.stmt.close();
                }
                catch (SQLException e3) {
                    e3.printStackTrace();
                }
            }
        }
        if (this.stmt != null) {
            try {
                this.stmt.close();
            }
            catch (SQLException e3) {
                e3.printStackTrace();
            }
        }
        return file;
    }
    
    public PictureFile getFileByName(final String id) {
        final PictureFile file = new PictureFile();
        try {
            Class.forName("org.h2.Driver");
            this.conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            this.stmt = this.conn.createStatement();
            final String sql = "SELECT * FROM Files WHERE id=" + id;
            final ResultSet rs = this.stmt.executeQuery(sql);
            while (rs.next()) {
                final int resId = Integer.parseInt(rs.getString("id"));
                final String resPath = rs.getString("path");
                final double resSizeKiloBytes = Math.round( Double.parseDouble(rs.getString("Size")) / 1024 );
                file.setId(resId);
                file.setPath(resPath);
                file.setSizeKiloBytes(resSizeKiloBytes);
            }
            this.stmt.close();
            this.conn.close();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e2) {
            e2.printStackTrace();
        }
        finally {
            if (this.stmt != null) {
                try {
                    this.stmt.close();
                }
                catch (SQLException e3) {
                    e3.printStackTrace();
                }
            }
        }
        if (this.stmt != null) {
            try {
                this.stmt.close();
            }
            catch (SQLException e3) {
                e3.printStackTrace();
            }
        }
        return file;
    }
    
    public int getImageId(String pictureNameAndExt) {
        int imageResId = 0;
        
        try {
            Class.forName("org.h2.Driver");
            this.conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            this.stmt = this.conn.createStatement();
            final String sql = "SELECT ID FROM Files WHERE NAME='" + pictureNameAndExt + "'";
            final ResultSet rs = this.stmt.executeQuery(sql);
            while (rs.next()) {
                final int resId = Integer.parseInt(rs.getString("id"));
                imageResId = resId;
            }
            this.stmt.close();
            this.conn.close();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e2) {
            e2.printStackTrace();
        }
        finally {
            if (this.stmt != null) {
                try {
                    this.stmt.close();
                }
                catch (SQLException e3) {
                    e3.printStackTrace();
                }
            }
        }
        if (this.stmt != null) {
            try {
                this.stmt.close();
            }
            catch (SQLException e3) {
                e3.printStackTrace();
            }
        }
        return imageResId;
    }
    
    public void deleteImage(int id) {
        try {
            Class.forName("org.h2.Driver");
            this.conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            this.stmt = this.conn.createStatement();
            final String sql = "DELETE FROM Files WHERE ID=" + id + "";
            
            stmt.executeUpdate( sql );
            System.out.println( "Record deleted successfully from id " + id );
            
            this.stmt.close();
            this.conn.close();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e2) {
            e2.printStackTrace();
        }
        finally {
            if (this.stmt != null) {
                try {
                    this.stmt.close();
                }
                catch (SQLException e3) {
                    e3.printStackTrace();
                }
            }
        }
        if (this.stmt != null) {
            try {
                this.stmt.close();
            }
            catch (SQLException e3) {
                e3.printStackTrace();
            }
        }
    }
    
    public String getFrDate(String resTimestamp) {
        String fullDate[] = resTimestamp.split(" ");
        String yearMonthDay = fullDate[0];
        
        String tabEnDate[] = yearMonthDay.split("-");
        
        String year = tabEnDate[0];
        String month = tabEnDate[1];
        String day = tabEnDate[2];
        
        String frDate = day + "/" + month + "/" + year;
        return frDate;
    }
    
    public String getEnDate(String resTimestamp) {
        String fullDate[] = resTimestamp.split(" ");
        String yearMonthDay = fullDate[0];
        
        String tabEnDate[] = yearMonthDay.split("-");
        
        String year = tabEnDate[0];
        String month = tabEnDate[1];
        String day = tabEnDate[2];
        
        String enDate = month + "/" + day + "/" + year;
        return enDate;
    }
    
    public String getFrTime(String resTimestamp) {
        String fullTime = resTimestamp.split( " " )[1];
        
        String hour = fullTime.split( ":" )[0] + "h";
        String minutes = fullTime.split( ":" )[1] + "m";
        
        String time = hour + " " + minutes;
        return time;
    }
    
    public String getEnTime(String resTimestamp) {
        String fullTime = resTimestamp.split( " " )[1];
        
        String hour = fullTime.split( ":" )[0];
        String minutes = fullTime.split( ":" )[1];
        
        try {
            int integerHour = Integer.parseInt( hour );
            
            if (integerHour >= 12) {
                return hour + ":" + minutes + " PM";
            } else if (integerHour >= 0) {
                return hour + ":" + minutes + " AM";
            } else {
                return null;
            }
            
        } catch (Exception ex) {
            throw ex;
        }
    }
}
