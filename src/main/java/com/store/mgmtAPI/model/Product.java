package com.store.mgmtAPI.model;

import com.store.mgmtAPI.utils.FieldLengths;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "name")
    @Size(min = FieldLengths.PRODUCT_NAME_MIN_LENGTH, max = FieldLengths.PRODUCT_NAME_MAX_LENGTH,
            message = "Name length has to be between " + FieldLengths.PRODUCT_NAME_MIN_LENGTH + " and " + FieldLengths.PRODUCT_NAME_MAX_LENGTH)
    private String name;

    @Column(name = "description")
    @Size(max = FieldLengths.DESCRIPTION_MAX_LENGTH, message = "Description cannot exceed " + FieldLengths.DESCRIPTION_MAX_LENGTH)
    private String description;
    @NotNull
    @Positive(message = "Price must be > 0")
    @Column(name = "price")
    private BigDecimal price;
    @NotNull
    @Min(value = 0, message = "Quantity must be >= 0")
    @Column(name = "quantity")
    private int quantity;

    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;

    public Product() {
    }

    public Product(String name, String description, BigDecimal price, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
