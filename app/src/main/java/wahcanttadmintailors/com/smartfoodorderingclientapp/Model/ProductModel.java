package wahcanttadmintailors.com.smartfoodorderingclientapp.Model;

public class ProductModel {
    public String id;
    public String product_name;
    public String product_price;
    public String product_discription;
    public  String ingridient_id;
    public  String category_id;
    public String product_img;
    public String created_at;
    public  String updated_at;

    public ProductModel(String id, String product_name, String product_price,
                        String product_discription, String ingridient_id, String category_id,
                        String product_img, String created_at, String updated_at) {
        this.id = id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_discription = product_discription;
        this.ingridient_id = ingridient_id;
        this.category_id = category_id;
        this.product_img = product_img;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public ProductModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_discription() {
        return product_discription;
    }

    public void setProduct_discription(String product_discription) {
        this.product_discription = product_discription;
    }

    public String getIngridient_id() {
        return ingridient_id;
    }

    public void setIngridient_id(String ingridient_id) {
        this.ingridient_id = ingridient_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getProduct_img() {
        return product_img;
    }

    public void setProduct_img(String product_img) {
        this.product_img = product_img;
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
