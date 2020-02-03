package serverresponse;


public class UserResponse {
    private String token;
    private String id;
    private String status;

    private String message;

    public String getMessage() {
        return message;
    }

    public UserResponse(String message) {
        this.message = message;
    }

    public UserResponse(String status, String token) {
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
        return id;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
