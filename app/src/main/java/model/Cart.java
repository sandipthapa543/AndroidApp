package model;

public class Cart {
    private String _id, user, status;
    public Product product;
    public Cart(String _id, String user, Product product, String status) {
        this._id = _id;
        this.user = user;
        this.product = product;
        this.status = status;
    }

    public String get_id() {
        return _id;
    }

    public String getUser() {
        return user;
    }

    public Product getProduct(){
        return product;
    }
    public String getStatus() {
        return status;
    }
}
