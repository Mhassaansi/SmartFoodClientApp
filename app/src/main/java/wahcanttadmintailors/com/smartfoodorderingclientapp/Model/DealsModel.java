package wahcanttadmintailors.com.smartfoodorderingclientapp.Model;

public class DealsModel {
    public String id;
    public String deal_img;
    public String deal_name;
    public String deal_price;
    public String deal_disc;
    public String created_at;
    public String updated_at;

    public DealsModel(String id, String deal_img,
                      String deal_name, String deal_price, String deal_disc, String created_at,
                      String updated_at) {
        this.id = id;
        this.deal_img = deal_img;
        this.deal_name = deal_name;
        this.deal_price = deal_price;
        this.deal_disc = deal_disc;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public DealsModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeal_img() {
        return deal_img;
    }

    public void setDeal_img(String deal_img) {
        this.deal_img = deal_img;
    }

    public String getDeal_name() {
        return deal_name;
    }

    public void setDeal_name(String deal_name) {
        this.deal_name = deal_name;
    }

    public String getDeal_price() {
        return deal_price;
    }

    public void setDeal_price(String deal_price) {
        this.deal_price = deal_price;
    }

    public String getDeal_disc() {
        return deal_disc;
    }

    public void setDeal_disc(String deal_disc) {
        this.deal_disc = deal_disc;
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
