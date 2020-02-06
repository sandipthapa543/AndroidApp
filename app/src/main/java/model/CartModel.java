package model;

public class CartModel {
    private String _id, user, product, status;

    public CartModel(String _id, String user, String product, String status) {
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

    public String getProduct() {
        return product;
    }

    public String getStatus() {
        return status;
    }
}
