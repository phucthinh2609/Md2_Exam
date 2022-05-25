package vn.mvpt.md2.exam.model;

public class Product {
    private Long id;
    private String title;
    private Double price;
    private int quantity;
    private String content;

    public Product() {
    }

    public Product(Long id, String title, Double price, int quantity, String content) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.content = content;
    }

    public static Product parse(String record) {
        String[] field = record.split(",");
        Product product = new Product();
        product.id = Long.parseLong(field[0]);
        product.title = field[1];
        product.price = Double.parseDouble(field[2]);
        product.quantity = Integer.parseInt(field[3]);
        product.content = field[4];

        return product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s",
                id,
                title,
                price,
                quantity,
                content
        );
    }
}

