package co.com.bancolombia.usecase.interactionstatics;

import co.com.bancolombia.dynamodb.DynamoDBTemplateAdapter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InteractionStaticsUseCase {

    private final DynamoDBTemplateAdapter repositroyAdapter;

    public void createInteractionStatics() {
        repositroyAdapter.save(repositroyAdapter);
    }
}
