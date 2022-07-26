package WishPostRequest;

public class PostRequest {

    private String title;
    private String content;
    private String color;

    public PostRequest(String title, String content, String color){
        this.title = title;
        this.content = content;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
