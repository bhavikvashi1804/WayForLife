package bhavik.exmple.com.wayforlife;

public class admin_polls_model {

    private String question;
    private Long yes,no;

    public admin_polls_model(String question, long yes, long no) {
        this.question = question;
        this.yes = yes;
        this.no = no;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Long getYes() {
        return yes;
    }

    public void setYes(long yes) {
        this.yes = yes;
    }

    public Long getNo() {
        return no;
    }

    public void setNo(long no) {
        this.no = no;
    }
}
