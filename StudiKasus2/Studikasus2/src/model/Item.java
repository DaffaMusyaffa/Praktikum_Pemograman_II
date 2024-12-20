package model;

import java.math.BigDecimal;

public class Item {

    private int id; // Primary Key
    private String name;
    private int quantity;
    private String location;
    private BigDecimal price;
    private String category;

    // Constructor dengan parameter
    public Item(String name, int quantity, String location, BigDecimal price, String category) {
        this.name = name;
        this.quantity = quantity;
        this.location = location;
        this.price = price;
        this.category = category;
    }

    // Default Constructor
    public Item() {
    }

    // Getter dan Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("ID must be greater than 0");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        } else {
            throw new IllegalArgumentException("Quantity must be non-negative");
        }
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        if (location != null && !location.trim().isEmpty()) {
            this.location = location;
        } else {
            throw new IllegalArgumentException("Location cannot be null or empty");
        }
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if (price != null && price.compareTo(BigDecimal.ZERO) >= 0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Price must be non-negative");
        }
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        if (category != null && !category.trim().isEmpty()) {
            this.category = category;
        } else {
            throw new IllegalArgumentException("Category cannot be null or empty");
        }
    }

    @Override
    public String toString() {
        return "Item{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", quantity=" + quantity
                + ", location='" + location + '\''
                + ", price=" + price
                + ", category='" + category + '\''
                + '}';
    }
}