package axon.service.demo.product.query.handler;

import axon.service.demo.product.query.model.entity.Product;
import axon.service.demo.product.query.model.event.ProductCreatedEvent;
import axon.service.demo.product.query.model.event.ProductDeletedEvent;
import axon.service.demo.product.query.model.event.ProductUpdatedEvent;
import axon.service.demo.product.query.model.repository.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProductEventHandler {

    private ProductRepository productRepository;

    public ProductEventHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event){
        productRepository.save(new Product(event.getProductId(), event.getProductName()));
    }

    @EventHandler
    public void on(ProductUpdatedEvent event){
        Product product = productRepository.findOne(event.getProductId());
        if(Objects.isNull(product))
            return;
        product.setProductName(event.getProductName());
        productRepository.save(product);
    }

    @EventHandler
    public void on(ProductDeletedEvent event){
        Product product = productRepository.findOne(event.getProductId());
        if(Objects.isNull(product))
            return;
        productRepository.delete(product);
    }

}
