package com.janhabersetzer.fahrtenbuch.server.db;


import java.sql.*;

//import com.google.appengine.api.utils.SystemProperty;


public class DBConnection {
	
	private static Connection con = null;
	
	// Richtige URL fehlt noch
	private static String googleUrl = "jdbc:google:mysql://fahrtenbuch-189510:europe-west1:fahrtenbuch-db";
    private static String localUrl = "jdbc:mysql://35.205.177.107:3306/fahrtenbuch_db?useSSL=false";
    private static String user = "root";
    private static String password = "0";
	
	
    public static Connection connection() {
        // Wenn es bisher keine Conncetion zur DB gab, ...
        if (con == null) {
            String url = null;
            try {
                //****if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
                    // Load the class that provides the new
                    // "jdbc:google:mysql://" prefix.
                   //*** Class.forName("com.mysql.jdbc.GoogleDriver");
                    // *****url = googleUrl;
               //*** } else {
                    // Local MySQL instance to use during development.
                    Class.forName("com.mysql.jdbc.Driver");
                    url = localUrl;
                //****}
                /*
                 * Dann erst kann uns der DriverManager eine Verbindung mit den
                 * oben in der Variable url angegebenen Verbindungsinformationen
                 * aufbauen.
                 * 
                 * Diese Verbindung wird dann in der statischen Variable con
                 * abgespeichert und fortan verwendet.
                 */
                con = DriverManager.getConnection(url,user,password);
            } catch (Exception e) {
                con = null;
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }

        // Zurückgegeben der Verbindung
        return con;
    }

    public void close(){
        if(con != null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
