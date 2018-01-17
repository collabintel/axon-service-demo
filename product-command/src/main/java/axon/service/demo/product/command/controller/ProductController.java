package axon.service.demo.product.command.controller;

import axon.service.demo.product.command.controller.endpoint.ProductEndpoint;
import axon.service.demo.product.command.controller.request.CreateProductRequest;
import axon.service.demo.product.command.model.command.CreateProductCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.concurrent.Future;

@RestController
@RequestMapping(ProductEndpoint.PRODUCT_ENDPOINT)
public class ProductController {

    private CommandGateway commandGateway;

    public ProductController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public Future<String> create(@RequestBody @Valid CreateProductRequest request){
        return commandGateway.send(new CreateProductCommand(request.getProductId(), request.getProductName()));
    }

}
