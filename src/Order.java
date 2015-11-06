public class Order {

    public Order(String bookUrl, String username) {
        this.bookUrl = bookUrl;
        this.username = username;
    }

    private String bookUrl;
    private String username;

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    public String getUsername() {
        return username;
    }
}
