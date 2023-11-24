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

/**
 *
 * @author Kanto
 */
public class Conversion {
    String id;
    String conversion;
    String unity1;
    String unity2;
    Double value;

//    Encapsulation
    public String getId() {return id;}
    public void setId(String id) {
        this.id = id;
    }

    public String getConversion() {return conversion;}
    public void setConversion(String conversion) {
        this.conversion = conversion;
    }

    public String getUnity1() {return unity1;}
    public void setUnity1(String unity1) {
        this.unity1 = unity1;
    }

    public String getUnity2() {return unity2;}
    public void setUnity2(String unity2) {
        this.unity2 = unity2;
    }

    public Double getValue() {return value;}
    public void setValue(Double value) {
        this.value = value;
    }


//      Method
    public Double makeConversion(String unity1, String unity2){
        Double value = 0.0;
        try{
            Connect c = new Connect();
            Connection con = c.connectPostgres();
            String sql = " select value from conversion where unity1 = ? AND unity2 = ?";
            PreparedStatement state = con.prepareStatement(sql);
            state.setString(1, unity1);
            state.setString(2, unity2);
            ResultSet result = state.executeQuery();
            while(result.next()){
                value = result.getDouble("value");
            }
            con.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return value;
    }    


    
//    Constructors 

    public Conversion() {
    }

    public Conversion(String id, String conversion,String unity1, String unity2, Double value) {
        this.setId(id);
        this.setConversion(conversion);
        this.setUnity1(unity1);
        this.setUnity2(unity2);
        this.setValue(value);
    }
    
    
}
