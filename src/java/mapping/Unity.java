package mapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import generalisation.Connect;

public class Unity {
    String id;
    String unity;
    
    // Encapsulation 

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getUnity() {return unity;}
    public void setUnity(String unity) {this.unity = unity;}


    //  get all unities 
    public Unity[] getAllUnities() {
        List<Unity> listUnity = new ArrayList<>();
        try {
            Connect c = new Connect();
            Connection con = c.connectPostgres();
            String sql = " select * from unity";
            PreparedStatement state = con.prepareStatement(sql);
            ResultSet result = state.executeQuery();
            while(result.next()){
                Unity unity = new Unity(result.getString("id"),result.getString("unity"));
                listUnity.add(unity);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listUnity.toArray(new Unity[listUnity.size()]);
    }
    
    
    // get info of an unity
    public Unity getInfoUnity(String idUnity){
        Unity unity = null;
        try{
            Connect c = new Connect();
            Connection con = c.connectPostgres();
            String sql = "select * from unity where id = ?";
            PreparedStatement state = con.prepareStatement(sql);
            state.setString(1,idUnity);
            ResultSet result = state.executeQuery();
            System.out.println("requete " +state);
            while(result.next()){;
                unity = new Unity(result.getString("id"),result.getString("unity"));
            }
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return unity;
    }

    // Encapsulation
    public Unity(){}
    public Unity(String id, String unity) {
        this.setId(id);
        this.setUnity(unity);
    }

    

    
}
