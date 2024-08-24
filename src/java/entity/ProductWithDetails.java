
package entity;

import java.util.Date;

public class ProductWithDetails {
    private Product product;
    private ProductDetail productDetails;

    // Constructor
    public ProductWithDetails(Product product, ProductDetail productDetails) {
        this.product = product;
        this.productDetails = productDetails;
    }

    // Getters and setters
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductDetail getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetail productDetails) {
        this.productDetails = productDetails;
    }
}

