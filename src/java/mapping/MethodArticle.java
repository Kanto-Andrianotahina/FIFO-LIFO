/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapping;

import generalisation.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author Kanto
 */
public class MethodArticle {
     int id;
    String article;
    String store;
    String method;
    Date date;

    
//    Encapsulation
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getArticle() {return article;}
    public void setArticle(String article) {this.article = article;}

    public String getStore() {return store;}
    public void setStore(String store) {this.store = store;}

    public String getMethod() {return method;}
    public void setMethod(String method) {this.method = method;}

    public Date getDate() {return date;}
    public void setDate(Date date) {this.date = date;}
    
    
    public String getMethodArticle(String article){
        String methods = "";
        try{
            Connect c = new Connect();
            Connection con = c.connectPostgres();
            String sql = "select method from methodarticle where article= ?";
            PreparedStatement state = con.prepareStatement(sql);
            state.setString(1, article);
            ResultSet result = state.executeQuery();
            while(result.next()){
                methods = result.getString("method");
            }
            con.close();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return methods;
    }
    
    
//  Constructors
    public MethodArticle() {}
    public MethodArticle(int id, String article, String store, String method, Date date) {
        this.setId(id);
        this.setArticle(article);
        this.setStore(store);
        this.setMethod(method);
        this.setDate(date);
    }
}
