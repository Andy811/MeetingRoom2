package cathay.hospital.example.model.bean;

import com.google.gson.annotations.SerializedName;

public class ReturnData {
    @SerializedName("rtn_code")
    public String rtn_code;

    @SerializedName("rtn_data")
    public String rtn_data;
}
