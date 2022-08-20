package Request;

public class WishEditRequest {
    private String title;
    private String content;

    public WishEditRequest(String title, String content, String color) {
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
