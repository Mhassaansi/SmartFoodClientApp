package wahcanttadmintailors.com.smartfoodorderingclientapp;


public class ApiUrls {


    //API BASE URL
    public static String base_url="http://letseat.cf/api/";
    public static String img_url="http://letseat.cf/";
    //POST APIS

    public static final String apply_book_tabel=base_url+"reservations";
    public static final String sign_in=base_url+"login";
    public static final String send_orders=base_url+"orders";
    public static final String sign_up=base_url+"register";

    // GET APIS

    public static final String tables=base_url+"tables";
    public static final String ingridients=base_url+"ingridients";
    public static final String tax_Api=base_url+"taxes";
    public static final String products_Api=base_url+"categories/filter/";
    public static final String employees=base_url+"employees";
    public static final String view_deals=base_url+"deals";
    public static final String category_Api=base_url+"categories";

    //Image url
    public static final String catimg_path=img_url+"Category/";
    public static final String prod_img=img_url+"Product/";
    public static final String deal_img=img_url+"Deal/";





}
