package mapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import generalisation.BddObject;
import generalisation.Connect;

public class Article extends BddObject{
    String id;
    String article;
    String category;
    String unity;


//    Encapsulation
    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getArticle() {return article;}
    public void setArticle(String article) {this.article = article;}

    public String getCategory() {return category;}
    public void setCategory(String category) {this.category = category;}

    public String getUnity() {return unity;}
    public void setUnity(String unity) {this.unity = unity;}



//    Methods
    public Article[] getAllArticle(){
        List<Article>  listArticle = new ArrayList<>();
        try {
            Connect c = new Connect();
            Connection con = c.connectPostgres();
            String sql = " select * from article";
            PreparedStatement state = con.prepareStatement(sql);
            ResultSet result = state.executeQuery();
            while (result.next()) {
                Article article = new Article(result.getString("id"),result.getString("article"),result.getString("category"),result.getString("unity"));
                listArticle.add(article);
            }
            con.close();
        }catch  (Exception e){
            e.printStackTrace();
        }
        return listArticle.toArray(new Article[listArticle.size()]);
    }
    
    // get info of an article
    public Article getInfoArticle(String idArticle){
        Article article = null;
        try{
            Connect c = new Connect();
            Connection con = c.connectPostgres();
            String sql = "select * from article where id = ?";
            PreparedStatement state = con.prepareStatement(sql);
            state.setString(1,idArticle);
            ResultSet result = state.executeQuery();
            while(result.next()){;
                article = new Article(result.getString("id"),result.getString("article"),result.getString("category"),result.getString("unity"));
            }
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return article;
    }

    



//    Constructors
    public Article(){}
    public Article(String id, String article, String category, String unity) {
        this.setId(id);
        this.setArticle(article);
        this.setCategory(category);
        this.setUnity(unity);
    }
}
