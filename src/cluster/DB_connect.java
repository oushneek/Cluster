/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cluster;

import java.sql.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oushneek
 */
public class DB_connect {

    static final String DB_URL = "jdbc:mysql://localhost/clustering";
    static final String DB_USER = "root";
    static final String DB_PASS = "";
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
       ArrayList<String> rawKeyWord = new ArrayList<String>();
       ArrayList<String> without_space_rawKeyWord = new ArrayList<String>();

        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            stmt = conn.createStatement();
            String sql = "SELECT * from keywords";
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                String temp="";
                temp=rs.getString("keyword_raw").toLowerCase().trim();
                
                if(!rawKeyWord.contains(temp)){
                    StringTokenizer c = new StringTokenizer(temp, " ");
                    String without_space_word="";
                    while(c.hasMoreTokens()){
                        without_space_word+=c.nextToken();
                    }
                    if(without_space_rawKeyWord.isEmpty()){
                        without_space_rawKeyWord.add(without_space_word);
                        rawKeyWord.add(temp);
                    }
                    else if(!without_space_rawKeyWord.contains(without_space_word)){
                        without_space_rawKeyWord.add(without_space_word);
                        rawKeyWord.add(temp);
                    }
                   
                }
                
            }
            //Matching keys=new Matching();
            //keys.match(rawKeyWord);
            //Starts_at keyword=new Starts_at();
            //keyword.stringMatch(rawKeyWord);
            Jaccard_similarity words=new Jaccard_similarity();
            words.getsimilarity(rawKeyWord);

        } catch (SQLException ex) {
            Logger.getLogger(DB_connect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DB_connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
