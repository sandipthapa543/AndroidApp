package model;

public class UserDetailsModel {
    private String first_Name;
    private String last_Name;
    private String email;
    private String phone;
    private String address;


    public UserDetailsModel(String first_Name, String last_Name, String email, String phone, String address) {
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public String getLast_Name() {
        return last_Name;
    }

    public void setLast_Name(String last_Name) {
        this.last_Name = last_Name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}