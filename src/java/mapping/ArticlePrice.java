/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapping;

import generalisation.Connect;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Kanto
 */
public class ArticlePrice {
    int id;
    String article;
    String store;
    Double price;
    Date date;
    
//    Encapsulation

    public int getId() {return id;}
    public void setId(int id) {
        this.id = id;
    }

    public String getArticle() {return article;}
    public void setArticle(String article) {
        this.article = article;
    }

    public String getStore() {return store;}
    public void setStore(String store) {
        this.store = store;
    }

    public Double getPrice() {return price;}
    public void setPrice(Double price) {
        if (price == 0) {
            throw new IllegalArgumentException ("Price is null");
        }
        this.price = price;
    }

    public Date getDate() {return date;}
    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
//  Methods
    
    public Double getLastPriceArticle(String article,String store,Date date){
        double price = 0;
        try{
            Connect c = new Connect();
            Connection con = c.connectPostgres();
            String sql = "select price from articleprice where article = ? and store = ? and date <= ? order by date desc limit 1";
            PreparedStatement state = con.prepareStatement(sql);
            state.setString(1, article);
            state.setString(2, store);
            state.setDate(3, (java.sql.Date) date);
            System.out.println("requete" +state);
            
            ResultSet result = state.executeQuery();
            while(result.next()){
                price = result.getDouble("price");
            }
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return price;
    }
    
    
    
    
//    Constructors
    public ArticlePrice() {
    }
    public ArticlePrice(int id, String article, String store, Double price, Date date) {
        this.setId(id);
        this.setArticle(article);
        this.setStore(store);
        this.setPrice(price);
        this.setDate(date);
    }
    
    
    
    
}
