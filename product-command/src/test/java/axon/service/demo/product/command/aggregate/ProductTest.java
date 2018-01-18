package axon.service.demo.product.command.aggregate;

import axon.service.demo.product.command.model.command.CreateProductCommand;
import axon.service.demo.product.command.model.command.DeleteProductCommand;
import axon.service.demo.product.command.model.command.UpdateProductCommand;
import axon.service.demo.product.command.model.event.ProductCreatedEvent;
import axon.service.demo.product.command.model.event.ProductDeletedEvent;
import axon.service.demo.product.command.model.event.ProductUpdatedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.Before;
import org.junit.Test;

public class ProductTest {

    private static final String PRODUCT_ID = "PRODUCT_ID";
    private static final String PRODUCT_NAME = "PRODUCT_NAME";

    private AggregateTestFixture<Product> testFixture;

    @Before
    public void setUp() throws Exception {
        testFixture = new AggregateTestFixture<>(Product.class);
    }

    @Test
    public void given_noActivity_when_createProductCommand_then_productCreated() throws Exception {
        testFixture.givenNoPriorActivity()
                    .when(new CreateProductCommand(PRODUCT_ID, PRODUCT_NAME))
                    .expectEvents(new ProductCreatedEvent(PRODUCT_ID, PRODUCT_NAME));
    }

    @Test
    public void given_createdProduct_when_updateProductCommand_then_productUpdated() throws Exception {
        testFixture.given(new ProductCreatedEvent(PRODUCT_ID, PRODUCT_NAME))
                .when(new UpdateProductCommand(PRODUCT_ID, PRODUCT_NAME))
                .expectEvents(new ProductUpdatedEvent(PRODUCT_ID, PRODUCT_NAME));
    }

    @Test
    public void given_createdProduct_when_deleteProductCommand_then_productDeleted() throws Exception {
        testFixture.given(new ProductCreatedEvent(PRODUCT_ID, PRODUCT_NAME))
                .when(new DeleteProductCommand(PRODUCT_ID))
                .expectEvents(new ProductDeletedEvent(PRODUCT_ID));
    }

    @Test
    public void given_deletedProduct_when_updateProductCommand_then_cantUpdateDeletedProduct() throws Exception {
        testFixture.given(new ProductCreatedEvent(PRODUCT_ID, PRODUCT_NAME))
                .andGiven(new ProductDeletedEvent(PRODUCT_ID))
                .when(new UpdateProductCommand(PRODUCT_ID, PRODUCT_NAME))
                .expectException(IllegalStateException.class)
                .expectNoEvents();
    }
}
