package axon.service.demo.product.command.aggregate;

import axon.service.demo.product.command.model.command.CreateProductCommand;
import axon.service.demo.product.command.model.event.ProductCreatedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.Before;
import org.junit.Test;

public class ProductTest {

    private AggregateTestFixture<Product> testFixture;

    @Before
    public void setUp() throws Exception {
        testFixture = new AggregateTestFixture<>(Product.class);
    }

    @Test
    public void given_noActivity_when_createProductCommand_then_createProduct() throws Exception {
        testFixture.givenNoPriorActivity()
                    .when(new CreateProductCommand("productId", "productName"))
                    .expectEvents(new ProductCreatedEvent("productId", "productName"));
    }
}
