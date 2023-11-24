package mapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import generalisation.Connect;

/**
 * QuantityLeftArt
 */
public class QuantityLeftArt {
    int id;
    String movement;
    Double quantity;

    
//    Encapsulation
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getMovement() {return movement;}
    public void setMovement(String movement) {this.movement = movement;}

    public Double getQuantity() {return quantity;}
    public void setQuantity(Double quantity) {this.quantity = quantity;}
    
    
    public Double quantityLeftMov(String movement){
        Double quantityLeft = 0.0;
        try{
            Connect c = new Connect();
            Connection con = c.connectPostgres();
            String sql = "SELECT quantityleft FROM quantityleftart WHERE id =(SELECT MAX(id) FROM quantityleftart WHERE movement = ?)";
            PreparedStatement state = con.prepareStatement(sql);
            state.setString(1, movement);
            ResultSet result = state.executeQuery();
            while(result.next()){
                quantityLeft = result.getDouble("quantityleft");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return quantityLeft;
    }
    
    public void insertQuantityLeftArt(String movement, Double quantity){
        try{
            Connect c = new Connect();
            Connection con = c.connectPostgres();
            String sql = "insert into quantityleftart values (default,?,?)";
            PreparedStatement state = con.prepareStatement(sql);
            state.setString(1, movement);
            state.setDouble(2, quantity);
            System.out.println("requete " + state);
            state.executeUpdate();
            con.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
//    Constructors

    public QuantityLeftArt() {
    }

    public QuantityLeftArt(int id, String movement, Double quantity) {
        this.setId(id);
        this.setMovement(movement);
        this.setQuantity(quantity);
    }
    
}