package Request;

public class WishPostRequest {
    private String title;
    private String content;



    public WishPostRequest(String title, String content) {
        this.title = title;
        this.content = content;

    }


    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

}
