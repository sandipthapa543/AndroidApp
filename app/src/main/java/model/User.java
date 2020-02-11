package model;

public class User {
    private String _id, role, first_Name,last_Name,email,password,phone,address;

    public User(String _id, String role, String first_Name, String last_Name, String email, String password, String phone, String address) {
        this._id = _id;
        this.role = role;
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public User(String first_Name, String last_Name, String email, String password, String phone, String address) {
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public String get_id() {
        return _id;
    }

    public String getRole() {
        return role;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public String getLast_Name() {
        return last_Name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}
