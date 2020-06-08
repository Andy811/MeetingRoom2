package cathay.hospital.example.activity;

public class Upload {
    private String mName;
    private String mImageUrl;



    private String meetingName;


    private String note;
    public Upload() {
        //empty constructor needed
    }
    public Upload(String name, String imageUrl,String meetingName,String note) {
        if (name.trim().equals("")) {
            name = "No Name";
        }
        mName = name;
        mImageUrl = imageUrl;
        this.meetingName = meetingName;
        this.note = note;

    }
    public Upload(String name, String meetingName,String note) {
        if (name.trim().equals("")) {
            name = "No Name";
        }
        mName = name;
       
        this.meetingName = meetingName;
        this.note = note;

    }
    public String getName() {
        return mName;
    }
    public void setName(String name) {
        mName = name;
    }
    public String getImageUrl() {
        return mImageUrl;
    }
    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }
    public String getMeetingName() {
        return meetingName;
    }
    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}