package wahcanttadmintailors.com.smartfoodorderingclientapp.Model;

public class CaetgoryModel {
    public String id;
    public String category_name;
    public String category_img;
    public String created_at;
    public  String updated_at;

    public CaetgoryModel(String id, String category_name, String category_img, String created_at,
                         String updated_at) {
        this.id = id;
        this.category_name = category_name;
        this.category_img = category_img;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public CaetgoryModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_img() {
        return category_img;
    }

    public void setCategory_img(String category_img) {
        this.category_img = category_img;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}