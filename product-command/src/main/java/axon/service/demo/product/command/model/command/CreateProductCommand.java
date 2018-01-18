package axon.service.demo.product.command.model.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.Objects;

public class CreateProductCommand {

    @TargetAggregateIdentifier
    private String productId;
    private String productName;

    public CreateProductCommand(String productId, String productName) {
        this.productId = productId;
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateProductCommand that = (CreateProductCommand) o;
        return Objects.equals(productId, that.productId) &&
                Objects.equals(productName, that.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName);
    }

}
