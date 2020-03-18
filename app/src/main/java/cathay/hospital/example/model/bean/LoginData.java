package cathay.hospital.example.model.bean;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("status")
    public String status;

    @SerializedName("userName")
    public String userName;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
