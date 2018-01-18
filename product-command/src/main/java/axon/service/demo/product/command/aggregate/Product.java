package axon.service.demo.product.command.aggregate;

import axon.service.demo.product.command.exception.ProductAlreadyExistsException;
import axon.service.demo.product.command.model.command.CreateProductCommand;
import axon.service.demo.product.command.model.event.ProductCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Objects;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class Product {

    @AggregateIdentifier
    private String productId;

    public Product() {
    }

    @CommandHandler
    public Product(CreateProductCommand command) {
        if(Objects.equals(this.productId, command.getProductId()))
            throw new ProductAlreadyExistsException();

        apply(new ProductCreatedEvent(command.getProductId(), command.getProductName()));
    }

    @EventSourcingHandler
    protected void on(ProductCreatedEvent event){
        this.productId = event.getProductId();
    }

}
