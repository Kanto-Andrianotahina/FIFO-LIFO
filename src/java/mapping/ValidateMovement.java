package mapping;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import generalisation.Connect;

public class ValidateMovement {
    String idValidation;
    String idMovement;
    String type;
    String article;
    Double quantity;
    String unity;
    String store;
    Timestamp dateMovement;
    Timestamp dateValidation;



    public String getIdValidation() {
        return idValidation;
    }
    public void setIdValidation(String idValidation) {
        this.idValidation = idValidation;
    }
    public String getIdMovement() {
        return idMovement;
    }
    public void setIdMovement(String idMovement) {
        this.idMovement = idMovement;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getArticle() {
        return article;
    }
    public void setArticle(String article) {
        this.article = article;
    }
    public Double getQuantity() {
        return quantity;
    }
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
    public String getUnity() {
        return unity;
    }
    public void setUnity(String unity) {
        this.unity = unity;
    }
    public String getStore() {
        return store;
    }
    public void setStore(String store) {
        this.store = store;
    }
    public Timestamp getDateMovement() {
        return dateMovement;
    }
    public void setDateMovement(Timestamp dateMovement) {
        this.dateMovement = dateMovement;
    }
    public Timestamp getDateValidation() {
        return dateValidation;
    }
    public void setDateValidation(Timestamp dateValidation) {
        this.dateValidation = dateValidation;
    }



    // checking for movement in validation
    public ValidateMovement getAllMovementInValidate(String article, String store){
        ValidateMovement listMovementValidate = null;
        try {
            Connect c = new Connect();
            Connection con = c.connectPostgres();
            String sql = " select * from view_validation_movement where article =? and store =? order by dateValidation desc, idValidation desc limit 1";
            PreparedStatement state =  con.prepareStatement(sql);
            state.setString(1, article);
            state.setString(2, store);
            ResultSet result = state.executeQuery();
            System.out.println("requete " +state);
            while (result.next()) {
                listMovementValidate = new ValidateMovement();
                listMovementValidate.setIdValidation(result.getString("idValidation"));
                listMovementValidate.setIdMovement(result.getString("idMovement"));
                listMovementValidate.setType(result.getString("type"));
                listMovementValidate.setArticle(result.getString("article"));
                listMovementValidate.setQuantity(result.getDouble("quantity"));
                listMovementValidate.setUnity(result.getString("unity"));
                listMovementValidate.setStore(result.getString("store"));
                listMovementValidate.setDateMovement(result.getTimestamp("dateMovement"));
                listMovementValidate.setDateValidation(result.getTimestamp("dateValidation"));

            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listMovementValidate;
    } 


    public ValidateMovement(){}
    public ValidateMovement(String idValidation, String idMovement, String type, String article, Double quantity,
        String unity, String store, Timestamp dateMovement, Timestamp dateValidation) {
        this.setIdValidation(idValidation);
        this.setIdMovement(idMovement);
        this.setType(type);
        this.setArticle(article);;
        this.setQuantity(quantity);
        this.setUnity(unity);
        this.setStore(store);
        this.setDateMovement(dateMovement);
        this.setDateValidation(dateValidation);
    }


    
    
}
