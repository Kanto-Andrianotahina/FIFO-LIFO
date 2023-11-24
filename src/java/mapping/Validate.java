/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapping;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import javax.swing.text.DateFormatter;

import generalisation.BddObject;
import generalisation.Connect;

/**
 *
 * @author Kanto
 */
public class Validate extends BddObject {
    String validate;
    String movement;
    Timestamp date;

    
    // Encapsulation 
    public String getValidate() {
        return validate;
    }
    public void setValidate(String validate) {
        this.validate = validate;
    }
    
    public String getMovement() {
        return movement;
    }

    public void setMovement(String movement) {
        this.movement = movement;
    }

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
    
    public void insertValidation(Connect c,String id ,String movement, Timestamp date){
        try {
            Connection con = c.connectPostgres();
            String sql = "insert into validation values (?,?,?)";
            PreparedStatement state = con.prepareStatement(sql);
            state.setString(1, id);
            state.setString(2, movement);
            state.setTimestamp(3, date);
            state.executeUpdate();
            con.close(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    





    // Constructors
    public Validate() {
        super("VDT", "getseqvalidation");

    }
    public Validate(String validate, String movement, String date) {
        super("VDT", "getseqvalidation");
        this.setValidate(validate);
        this.setMovement(movement);
        this.setDate(date);
    }

    



}
