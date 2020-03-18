package cathay.hospital.example.viewModel;

public class DataModel {
    private Object dataObj;
    private Boolean dataExist;
    private Boolean error;
    private String rtn_code;

    public Object getDataObj(){
        return dataObj;
    }

    public void setDataObj(Object dataObj){
        this.dataObj = dataObj;
    }

    public Boolean getError(){
        return error;
    }

    public void setError(Boolean error){
        this.error = error;
    }

    public Boolean getDataExist() {
        return dataExist;
    }

    public void setDataExist(Boolean dataExist) {
        this.dataExist = dataExist;
    }

    public String getRtn_code() {
        return rtn_code;
    }

    public void setRtn_code(String rtn_code) {
        this.rtn_code = rtn_code;
    }
}
