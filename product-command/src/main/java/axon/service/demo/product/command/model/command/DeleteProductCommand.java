package axon.service.demo.product.command.model.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.Objects;

public class DeleteProductCommand {

    @TargetAggregateIdentifier
    private String productId;

    public DeleteProductCommand(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteProductCommand that = (DeleteProductCommand) o;
        return Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}
