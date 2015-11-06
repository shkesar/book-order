public class Book {
    public Book(String url, int price) {
        this.url = url;
        this.price = price;
    }
    private String url;
    private int price;

    public String getUrl() {
        return url;
    }

    public int getPrice() {
        return price;
    }
}
