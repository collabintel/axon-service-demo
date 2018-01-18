package axon.service.demo.product.command.controller;

import axon.service.demo.product.command.controller.endpoint.ProductEndpoint;
import axon.service.demo.product.command.controller.request.CreateProductRequest;
import axon.service.demo.product.command.controller.request.DeleteProductRequest;
import axon.service.demo.product.command.controller.request.UpdateProductRequest;
import axon.service.demo.product.command.model.command.CreateProductCommand;
import axon.service.demo.product.command.model.command.UpdateProductCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.concurrent.CompletableFuture;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
@Import(RestHelper.class)
public class ProductControllerTest {

    private static final String PRODUCT_ID = "PRODUCT_ID";
    private static final String PRODUCT_NAME = "PRODUCT_NAME";

    @Autowired
    private RestHelper restHelper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommandGateway commandGateway;

    @Test
    public void should_createProduct() throws Exception {
        CreateProductRequest request = new CreateProductRequest();
        request.setProductName(PRODUCT_NAME);

        CompletableFuture completedFuture = CompletableFuture.completedFuture(PRODUCT_ID);

        when(commandGateway.send(any(CreateProductCommand.class))).thenReturn(completedFuture);
        MvcResult mvcResult = this.mockMvc.perform(post(ProductEndpoint.PRODUCT_ENDPOINT).contentType(restHelper.contentType()).content(restHelper.json(request)))
                .andExpect(request().asyncStarted())
                .andReturn();
        this.mockMvc
                .perform(asyncDispatch(mvcResult))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(PRODUCT_ID)));
    }

    @Test
    public void should_updateProduct() throws Exception {
        UpdateProductRequest request = new UpdateProductRequest();
        request.setProductId(PRODUCT_ID);
        request.setProductName(PRODUCT_NAME);

        CompletableFuture completedFuture = CompletableFuture.completedFuture(null);

        when(commandGateway.send(any(UpdateProductCommand.class))).thenReturn(completedFuture);
        MvcResult mvcResult = this.mockMvc.perform(put(ProductEndpoint.PRODUCT_ENDPOINT).contentType(restHelper.contentType()).content(restHelper.json(request)))
                .andExpect(request().asyncStarted())
                .andReturn();
        this.mockMvc
                .perform(asyncDispatch(mvcResult))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void should_deleteProduct() throws Exception {
        DeleteProductRequest request = new DeleteProductRequest();
        request.setProductId(PRODUCT_ID);

        CompletableFuture completedFuture = CompletableFuture.completedFuture(null);

        when(commandGateway.send(any(DeleteProductRequest.class))).thenReturn(completedFuture);
        MvcResult mvcResult = this.mockMvc.perform(delete(ProductEndpoint.PRODUCT_ENDPOINT).contentType(restHelper.contentType()).content(restHelper.json(request)))
                .andExpect(request().asyncStarted())
                .andReturn();
        this.mockMvc
                .perform(asyncDispatch(mvcResult))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private CreateProductRequest createCreateProductRequest(String productName) {
        CreateProductRequest request = new CreateProductRequest();
        request.setProductName(productName);
        return request;
    }

}
