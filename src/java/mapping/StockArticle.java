/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import generalisation.Connect;

/**
 *
 * @author Kanto
 */
public class StockArticle {
    int id;
    String article;
    String store;
    Double quantity;
    Timestamp date;
    String unity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        if (quantity == 0) {
            throw new IllegalArgumentException("Quantity in stock 0");
        }
        this.quantity = quantity;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getUnity() {
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }


    // Methods
    public void insertStock(Connect c,String article, String store, Double quantity, Timestamp date, String unity){
        try{
            Connection con = c.connectPostgres();
            String sql =  "insert into stockarticle values (default,?,?,?,?,?)";
            PreparedStatement state = con.prepareStatement(sql);
            state.setString(1, article);
            state.setString(2, store);
            state.setDouble(3, quantity);
            state.setTimestamp(4, date);
            state.setString(5, unity);
            state.executeUpdate();
            System.out.println("requete " +state);
            con.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public Double getQuantityInit(String article){
        Double quantityInit = 0.0;
        try{
            Connect c = new Connect();
            Connection con = c.connectPostgres();
            String sql = "SELECT quantity FROM stockarticle WHERE article = ? ORDER BY date DESC LIMIT 1 OFFSET 1";
            PreparedStatement state  = con.prepareStatement(sql);
            state.setString(1, article);
            ResultSet result = state.executeQuery();
            while(result.next()){
                quantityInit = result.getDouble("quantity");
            }
            con.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return quantityInit;
    }

    public Double getlastQuantityArticle(String article, String store){
        Double lastQuantity = 0.0;
        try{
            Connect c = new Connect();
            Connection con = c.connectPostgres();
            String sql = "SELECT quantity FROM stockarticle WHERE article = ? AND store =? ORDER BY id DESC LIMIT 1";
            PreparedStatement state  = con.prepareStatement(sql);
            state.setString(1, article);
            state.setString(2, store);
            ResultSet result = state.executeQuery();
            while(result.next()){
                lastQuantity = result.getDouble("quantity");
            }
            con.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return lastQuantity;
    }
    public Double[] getListStock(){
        List<Double> listStock = new ArrayList<>();
         try{
            Connect c = new Connect();
            Connection con = c.connectPostgres();
            String sql = "SELECT previous_quantity FROM stock_view";
            PreparedStatement state  = con.prepareStatement(sql);
            ResultSet result = state.executeQuery();
            while(result.next()){
                Double previous = result.getDouble("previous_quantity");
                listStock.add(previous);
            }
            con.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return listStock.toArray(new Double[listStock.size()]);
    }





    public StockArticle(){}
    public StockArticle(int id, String article, String store, Double quantity, Timestamp date, String unity) {
        this.setId(id);
        this.setArticle(article);
        this.setStore(store);
        this.setQuantity(quantity);
        this.setDate(date);
        this.setUnity(unity);
    }
    
    
    
    
}




