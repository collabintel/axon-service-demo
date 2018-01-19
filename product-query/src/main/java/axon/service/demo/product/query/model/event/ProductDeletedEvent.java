package axon.service.demo.product.query.model.event;

import java.util.Objects;

public class ProductDeletedEvent {

    private String productId;

    public ProductDeletedEvent(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDeletedEvent that = (ProductDeletedEvent) o;
        return Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}
