package bhavik.exmple.com.wayforlife;

public class complaints_model {
    private String title;
    private String complaint;

    public complaints_model(String title, String complaint) {

        this.title = title;
        this.complaint = complaint;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }
}

