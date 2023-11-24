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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.DateFormatter;

import generalisation.BddObject;
import generalisation.Connect;

/**
 *
 * @author Kanto
 */
public class Movement extends BddObject {
    String id;
    String type;
    String article;
    Double quantity;
    String unity;
    String store;
    Timestamp date;
    

    // Encapsulation
    public String getId() {return id;}
    public void setId(String id) {this.id = id;}
    
    public String getType() {return type;}
    public void setType(String type) {this.type = type;}
    
    public String getArticle() {return article;}
    public void setArticle(String article) {this.article = article;}
    
    public Double getQuantity() {return quantity;}
    public void setQuantity(String quantity) {
        Double qty = Double.parseDouble(quantity);
        if (qty <0) {
            throw new IllegalArgumentException ("Invalid quantity");
        }
        this.quantity = qty;
    }

    public String getUnity() {return unity;}
    public void setUnity(String unity) {this.unity = unity;}
    
    public String getStore() {return store;}
    public void setStore(String store) {this.store = store;}

    public Timestamp getDate() {return date;}
    public void setDate(String date) {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatDate);
        Timestamp dateTimestamp = Timestamp.valueOf(dateTime);
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        if(dateTimestamp.after(currentDate)){
            throw new IllegalStateException ("Invalid date");
        }
        this.date = dateTimestamp;
    }
    public void setDateSql(String date) {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatDate);
        Timestamp dateTimestamp = Timestamp.valueOf(dateTime);
        this.date = dateTimestamp;
    }


    // Methods 

    // insertion mouvement
    public void insertMovement(Connect c,String id,String type, String article, Double quantity, String unity, String store,Timestamp date) {
        try {
            Connection con = c.connectPostgres();
            String sql = "insert into movement values (?,?,?,?,?,?,?)";
            PreparedStatement state =  con.prepareStatement(sql);
            state.setString(1, id);
            state.setString(2, type);
            state.setString(3, article);
            state.setDouble(4, quantity);
            state.setString(5, unity);
            state.setString(6, store);
            state.setTimestamp(7,date);
            
            System.out.println("Sql: " + state);
            state.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // get all movements not valid
    public Movement[] getAllMovementsNotValid(){
        List<Movement> listMovementsNotValid = new ArrayList<>();
        try {
            Connect c = new Connect();
            Connection con = c.connectPostgres();
            String sql = "select * from view_movement_not_valid";
            PreparedStatement state = con.prepareStatement(sql);
            ResultSet result = state.executeQuery();
            while (result.next()) {
                Movement movement = new Movement();
                movement.setId(result.getString("id"));
                movement.setType(result.getString("type"));
                movement.setArticle(result.getString("article"));
                movement.setQuantity(result.getString("quantity"));
                movement.setUnity(result.getString("unity"));
                movement.setStore(result.getString("store"));
                movement.setDateSql(result.getString("date"));
                
                listMovementsNotValid.add(movement);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listMovementsNotValid.toArray(new Movement[listMovementsNotValid.size()]);
    }

    // get  movement not valid by id 
    public Movement getMovementNotValidById(String idMovement) {
        Movement movement = null;
        try{
            Connect c = new Connect();
            Connection con = c.connectPostgres();
            String sql = "select * from view_movement_not_valid where id =?";
            PreparedStatement state = con.prepareStatement(sql);
            state.setString(1, idMovement);
            ResultSet result = state.executeQuery();
            System.out.println("Requete "+state);
            while (result.next()) {
                movement = new Movement();
                movement.setId(result.getString("id"));
                movement.setType(result.getString("type"));
                movement.setArticle(result.getString("article"));
                movement.setQuantity(result.getString("quantity"));
                movement.setUnity(result.getString("unity"));
                movement.setStore(result.getString("store"));
                movement.setDateSql(result.getString("date"));
                
            }
             con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movement;
    }

    
    //Constructors
    public Movement() {
        super("MVT", "getseqmovement");
    }
    public Movement(String id,String type, String article, String quantity, String unity, String store, String date) {
        super("MVT", "getseqmovement");
        this.setId(id);
        this.setType(type);
        this.setArticle(article);
        this.setQuantity(quantity);
        this.setUnity(unity);
        this.setStore(store);
        this.setDate(date);
    }
    
    
}
