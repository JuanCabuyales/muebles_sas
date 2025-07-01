package co.com.bancolombia.dynamodb.helper;

import co.com.bancolombia.dynamodb.DynamoDBTemplateAdapter;
import co.com.bancolombia.dynamodb.InteractionStatistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.reactivecommons.utils.ObjectMapper;
import reactor.test.StepVerifier;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class TemplateAdapterOperationsTest {

    @Mock
    private DynamoDbEnhancedAsyncClient dynamoDbEnhancedAsyncClient;

    @Mock
    private ObjectMapper mapper;

    @Mock
    private DynamoDbAsyncTable<InteractionStatistics> customerTable;

    private InteractionStatistics interactionStatistics;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(dynamoDbEnhancedAsyncClient.table("table_name", TableSchema.fromBean(InteractionStatistics.class)))
                .thenReturn(customerTable);

        interactionStatistics = new InteractionStatistics();
        interactionStatistics.setDate("2025-06-30T10:15:30Z");
        interactionStatistics.setTotalCustomerContact(12);
        interactionStatistics.setReasonClaim(20);
        interactionStatistics.setReasonWarranty(11);
        interactionStatistics.setReasonDoubt(14);
        interactionStatistics.setReasonPurchase(15);
        interactionStatistics.setReasonCongratulations(16);
        interactionStatistics.setReasonChange(88);
        interactionStatistics.setHash("demo");

    }

    @Test
    void modelEntityPropertiesMustNotBeNull() {
        InteractionStatistics interactionStatisticsUnderTest = new InteractionStatistics("2025-06-30T10:15:30Z", 12,12,15, 1,25,8,90,"02946f262f2eb0d8d5c8e76c50433ed8");

        assertNotNull(interactionStatisticsUnderTest.getDate());
        assertNotNull(interactionStatisticsUnderTest.getReasonChange());
    }

    @Test
    void testSave() {
        when(customerTable.putItem(interactionStatistics)).thenReturn(CompletableFuture.runAsync(()->{}));
        when(mapper.map(interactionStatistics, InteractionStatistics.class)).thenReturn(interactionStatistics);

        DynamoDBTemplateAdapter dynamoDBTemplateAdapter =
                new DynamoDBTemplateAdapter(dynamoDbEnhancedAsyncClient, mapper);

        StepVerifier.create(dynamoDBTemplateAdapter.save(interactionStatistics))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void testGetById() {
        String id = "id";

        when(customerTable.getItem(
                Key.builder().partitionValue(AttributeValue.builder().s(id).build()).build()))
                .thenReturn(CompletableFuture.completedFuture(interactionStatistics));
        when(mapper.map(interactionStatistics, Object.class)).thenReturn("value");

        DynamoDBTemplateAdapter dynamoDBTemplateAdapter =
                new DynamoDBTemplateAdapter(dynamoDbEnhancedAsyncClient, mapper);

        StepVerifier.create(dynamoDBTemplateAdapter.getById("id"))
                .expectNext("value")
                .verifyComplete();
    }

    @Test
    void testDelete() {
        when(mapper.map(interactionStatistics, InteractionStatistics.class)).thenReturn(interactionStatistics);
        when(mapper.map(interactionStatistics, Object.class)).thenReturn("value");

        when(customerTable.deleteItem(interactionStatistics))
                .thenReturn(CompletableFuture.completedFuture(interactionStatistics));

        DynamoDBTemplateAdapter dynamoDBTemplateAdapter =
                new DynamoDBTemplateAdapter(dynamoDbEnhancedAsyncClient, mapper);

        StepVerifier.create(dynamoDBTemplateAdapter.delete(interactionStatistics))
                .expectNext("value")
                .verifyComplete();
    }
}