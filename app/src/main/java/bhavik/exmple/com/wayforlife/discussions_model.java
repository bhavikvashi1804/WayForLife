package bhavik.exmple.com.wayforlife;

public class discussions_model {
    private String title;
    private String discussion;

    public discussions_model(String title, String discussion) {

        this.title = title;
        this.discussion = discussion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscussion() {
        return discussion;
    }

    public void setDiscussion(String discussion) {
        this.discussion = discussion;
    }


}

