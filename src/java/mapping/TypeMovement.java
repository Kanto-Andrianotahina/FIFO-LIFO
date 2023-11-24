package mapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import generalisation.Connect;

public class TypeMovement {
    String id;
    String type;
    
    // Encapsulation 
    public String getId() {return id;}
    public void setId(String id) {this.id = id;}
    public String getType() {return type;}
    public void setType(String type) {this.type = type;}

    
    // get  all the type of movement
    public TypeMovement[] getAllTypesMovement(){
        List<TypeMovement> listTypeMovement = new ArrayList<>();
        try{
            Connect c = new Connect();
            Connection con = c.connectPostgres();
            String sql = "select * from typemovement";
            PreparedStatement state = con.prepareStatement(sql);
            ResultSet result = state.executeQuery();
            while(result.next()){
                TypeMovement typemovement = new TypeMovement(result.getString("id"),result.getString("type"));
                listTypeMovement.add(typemovement);
            }
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return listTypeMovement.toArray(new TypeMovement[listTypeMovement.size()]);
    }

    public TypeMovement getInfoTypeMovement(String idType){
        TypeMovement typeMovement = null;
        try {
            Connect c = new Connect();
            Connection con = c.connectPostgres();
            String sql = "select * from typemovement where id =?";
            PreparedStatement state = con.prepareStatement(sql);
            state.setString(1,idType);
            ResultSet result = state.executeQuery();
            while (result.next()) {
                typeMovement = new TypeMovement();
                typeMovement.setId(result.getString("id"));
                typeMovement.setType(result.getString("type"));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return typeMovement;
    }

    // Constructors
    public TypeMovement(){}
    public TypeMovement(String id, String type){
        this.setId(id);
        this.setType(type);
    }

     
}
