package WishPost;

public class PostRequest {
    private String title;
    private String content;
    private String color;


    public PostRequest(String title, String content, String color) {
        this.title = title;
        this.content = content;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getColor() {
        return color;
    }
}
