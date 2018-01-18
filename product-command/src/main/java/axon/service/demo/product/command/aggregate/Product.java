package axon.service.demo.product.command.aggregate;

import axon.service.demo.product.command.model.command.CreateProductCommand;
import axon.service.demo.product.command.model.command.DeleteProductCommand;
import axon.service.demo.product.command.model.command.UpdateProductCommand;
import axon.service.demo.product.command.model.event.ProductCreatedEvent;
import axon.service.demo.product.command.model.event.ProductDeletedEvent;
import axon.service.demo.product.command.model.event.ProductUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class Product {

    @AggregateIdentifier
    private String productId;
    private Boolean isActive;

    public Product() {
    }

    @CommandHandler
    public Product(CreateProductCommand command) {
        apply(new ProductCreatedEvent(command.getProductId(), command.getProductName()));
    }

    @CommandHandler
    public void handle(UpdateProductCommand command){
        Assert.state(isActive, "Can't update deleted product");
        apply(new ProductUpdatedEvent(command.getProductId(), command.getProductName()));
    }

    @CommandHandler
    public void handle(DeleteProductCommand command){
        apply(new ProductDeletedEvent(command.getProductId()));
    }

    @EventSourcingHandler
    protected void on(ProductCreatedEvent event){
        this.productId = event.getProductId();
        this.isActive = true;
    }

    @EventSourcingHandler
    protected void on(ProductUpdatedEvent event) {}

    @EventSourcingHandler
    protected void on(ProductDeletedEvent event) {
        this.isActive = false;
    }

}
