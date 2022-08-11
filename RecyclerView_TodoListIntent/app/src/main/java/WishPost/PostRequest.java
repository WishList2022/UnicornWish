package WishPost;

public class PostRequest {
    private String title;
    private String content;



    public PostRequest(String title, String content, String color) {
        this.title = title;
        this.content = content;

    }

    public PostRequest(String title, String content) {
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

}
