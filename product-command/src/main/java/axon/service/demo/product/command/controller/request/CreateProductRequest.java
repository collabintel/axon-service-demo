package axon.service.demo.product.command.controller.request;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Objects;

public class CreateProductRequest {

    @NotEmpty
    private String productId;
    private String productName;

    public CreateProductRequest() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateProductRequest request = (CreateProductRequest) o;
        return Objects.equals(productId, request.productId) &&
                Objects.equals(productName, request.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName);
    }

}
