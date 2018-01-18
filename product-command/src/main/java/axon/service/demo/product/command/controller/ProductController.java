package axon.service.demo.product.command.controller;

import axon.service.demo.product.command.controller.endpoint.ProductEndpoint;
import axon.service.demo.product.command.controller.request.CreateProductRequest;
import axon.service.demo.product.command.controller.request.DeleteProductRequest;
import axon.service.demo.product.command.controller.request.UpdateProductRequest;
import axon.service.demo.product.command.model.command.CreateProductCommand;
import axon.service.demo.product.command.model.command.DeleteProductCommand;
import axon.service.demo.product.command.model.command.UpdateProductCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;
import java.util.concurrent.Future;

@RestController
@RequestMapping(ProductEndpoint.PRODUCT_ENDPOINT)
public class ProductController {

    private CommandGateway commandGateway;

    public ProductController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public Future<String> create(@RequestBody CreateProductRequest request){
        String uuid = UUID.randomUUID().toString();
        return commandGateway.send(new CreateProductCommand(uuid, request.getProductName()));
    }

    @PutMapping
    public Future<Void> update(@RequestBody UpdateProductRequest request){
        return commandGateway.send(new UpdateProductCommand(request.getProductId(), request.getProductName()));
    }

    @DeleteMapping
    public Future<Void> delete(@RequestBody DeleteProductRequest request){
        return commandGateway.send(new DeleteProductCommand(request.getProductId()));
    }

}
