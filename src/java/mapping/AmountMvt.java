/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapping;

import generalisation.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Kanto
 */
public class AmountMvt {
    int id;
    String movement;
    Double quantity;
    Double amount;

    
//    Encapsulation
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovement() {
        return movement;
    }

    public void setMovement(String movement) {
        this.movement = movement;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    
    public void insertAmountMvt(Connect c,String movement, Double quantity, Double amount){
        try{;
            Connection con = c.connectPostgres();
            String sql = "insert into amountmvt values (default,?,?,?)";
            PreparedStatement state = con.prepareStatement(sql);
            state.setString(1, movement);
            state.setDouble(2, quantity);
            state.setDouble(3, amount);
            state.executeUpdate();
            System.out.println("requete" + state);
            con.close();
        } catch(Exception e ){
            e.printStackTrace();
        }   
    }
    
    
//    Constructors
    public AmountMvt() {
    }
    public AmountMvt(int id, String movement, Double quantity, Double amount) {
        this.setId(id);
        this.setMovement(movement);
        this.setQuantity(quantity);
        this.setAmount(amount);
    }
    
    
    
   
    
    
    
    
}
