package co.com.bancolombia.dynamodb;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

@DynamoDbBean
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class InteractionStatistics {

    private String date;

    private Integer totalCustomerContact;

    private Integer reasonClaim;

    private Integer reasonWarranty;

    private Integer reasonDoubt;

    private Integer reasonPurchase;

    private Integer reasonCongratulations;

    private Integer reasonChange;

    private String hash;


    @DynamoDbPartitionKey
    public String getDate() {
        return date;
    }

    @DynamoDbAttribute("totalCustomerContact")
    public Integer getTotalCustomerContact() {
        return totalCustomerContact;
    }

    @DynamoDbAttribute("reasonClaim")
    public Integer getReasonClaim() {
        return reasonClaim;
    }

    @DynamoDbAttribute("reasonWarranty")
    public Integer getReasonWarranty() {
        return reasonWarranty;
    }

    @DynamoDbAttribute("reasonDoubt")
    public Integer getReasonDoubt() {
        return reasonDoubt;
    }

    @DynamoDbAttribute("reasonPurchase")
    public Integer getReasonPurchase() {
        return reasonPurchase;
    }

    @DynamoDbAttribute("reasonCongratulations")
    public Integer getReasonCongratulations() {
        return reasonCongratulations;
    }

    @DynamoDbAttribute("reasonChange")
    public Integer getReasonChange() {
        return reasonChange;
    }

    @DynamoDbAttribute("hash")
    public String getHash() {
        return hash;
    }
}
