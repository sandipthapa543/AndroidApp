package serverresponse;


public class UserResponse {
    private String token;
    private String _id;
    private String status;

    private String message;

    public String getMessage() {
        return message;
    }

    public UserResponse(String message) {
        this.message = message;
    }

    public UserResponse(String _id, String status, String token) {
        this._id = _id;
        this.status = status;
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public  String getToken() {
        return token;
    }

    public String getId() {
        return _id;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
