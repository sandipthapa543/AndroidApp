package model;

public class Product {
    String _id;
    String productName;
    String productPrice;
    String productImage;

    public Product(String _id, String name, String price, String productImage) {
        this._id = _id;
        this.productName = name;
        this.productPrice = price;
        this.productImage = productImage;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return productName;
    }

    public String getPrice() {
        return productPrice;
    }

    public String getImage() {
        return productImage;
    }
}
