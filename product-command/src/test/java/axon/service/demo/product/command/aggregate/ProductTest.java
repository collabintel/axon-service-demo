package axon.service.demo.product.command.aggregate;

import axon.service.demo.product.command.exception.ProductAlreadyExistsException;
import axon.service.demo.product.command.model.command.CreateProductCommand;
import axon.service.demo.product.command.model.event.ProductCreatedEvent;
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
    public void given_noActivity_when_createProductCommand_then_createProduct() throws Exception {
        testFixture.givenNoPriorActivity()
                    .when(new CreateProductCommand(PRODUCT_ID, PRODUCT_NAME))
                    .expectEvents(new ProductCreatedEvent(PRODUCT_ID, PRODUCT_NAME));
    }

//    @Test
//    public void given_createdProduct_when_createProductCommand_then_createProduct() throws Exception {
//        testFixture.given(new ProductCreatedEvent(PRODUCT_ID, PRODUCT_NAME))
//                .when(new CreateProductCommand(PRODUCT_ID, PRODUCT_NAME))
//                .expectException(ProductAlreadyExistsException.class)
//                .expectNoEvents();
//    }
}
