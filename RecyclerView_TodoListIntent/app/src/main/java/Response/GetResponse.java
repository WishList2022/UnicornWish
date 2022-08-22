package Response;

public class GetResponse {
    private String title;
    private String content;

    public GetResponse(String title, String content){
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
