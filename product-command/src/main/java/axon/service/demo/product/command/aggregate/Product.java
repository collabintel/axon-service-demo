package axon.service.demo.product.command.aggregate;

import axon.service.demo.product.command.model.command.CreateProductCommand;
import axon.service.demo.product.command.model.event.ProductCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class Product {

    @AggregateIdentifier
    private String productId;

    public Product() {
    }

    @CommandHandler
    public Product(CreateProductCommand command) {
        apply(new ProductCreatedEvent(command.getProductId(), command.getProductName()));
    }

    @EventSourcingHandler
    protected void on(ProductCreatedEvent event){
        this.productId = event.getProductId();
    }

}
