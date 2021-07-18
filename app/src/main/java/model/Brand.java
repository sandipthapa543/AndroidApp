package model;

public class Brand {
    String _id;
    String brandImage,brandName;

    public Brand(String _id, String brandImage, String brandName) {
        this._id = _id;
        this.brandImage = brandImage;
        this.brandName = brandName;
    }

    public String get_id() {
        return _id;
    }

    public String getBrandImage() {
        return brandImage;
    }

    public String getBrandName() {
        return brandName;
    }
}
