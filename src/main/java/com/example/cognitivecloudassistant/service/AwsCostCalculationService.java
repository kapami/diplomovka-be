package com.example.cognitivecloudassistant.service;

import com.example.cognitivecloudassistant.dto.ArchitectureDTO;
import com.example.cognitivecloudassistant.dto.ResponseItemDTO;
import com.example.cognitivecloudassistant.dto.ServiceItemDTO;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.pricing.PricingClient;
import software.amazon.awssdk.services.pricing.model.Filter;
import software.amazon.awssdk.services.pricing.model.GetProductsRequest;
import software.amazon.awssdk.services.pricing.model.GetProductsResponse;

import java.io.File;

@Service
public class AwsCostCalculationService implements CalculationStrategy{

    @Override
    public void calculateCosts(ResponseItemDTO resourceDetailsDTO) {
        Dotenv dotenv;
        if(new File("/etc/secrets/.env").exists()){
            dotenv = Dotenv.configure()
                    .directory("/etc/secrets/.env")
                    .ignoreIfMalformed()
                    .ignoreIfMissing()
                    .load();
        } else {
            dotenv = Dotenv.configure()
                    .directory(".env")
                    .ignoreIfMalformed()
                    .ignoreIfMissing()
                    .load();
        }

        String accessKey = dotenv.get("AWS_ACCESS_KEY");
        String secretKey = dotenv.get("AWS_SECRET_KEY");

        // Create an instance of AwsCredentials
        AwsCredentials awsCredentials = AwsBasicCredentials.create(accessKey, secretKey);

        // Create an instance of AwsCredentialsProvider using static credentials
        AwsCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(awsCredentials);

        // Specify the AWS region
        Region region = Region.EU_CENTRAL_1; // Replace with your desired region

        // Create a PricingClient with credentials and region
        PricingClient pricingClient = PricingClient.builder()
                .credentialsProvider(credentialsProvider)
                .region(region)
                .build();

        // Specify the service code for the AWS service you want pricing information for
        String serviceCode = "AmazonEC2"; // Replace with the desired service code
        String targetRegion = "eu-west-3"; // Replace with your desired region



// Create a Filter object with a valid member.type property
        Filter locationFilter = Filter.builder()
                .field("regionCode")
                .type("EQUALS") // Specify the type of the filter value
                .value(targetRegion)
                .build();

        GetProductsRequest getProductsRequest = GetProductsRequest.builder()
                .serviceCode(serviceCode)
                .filters(locationFilter)
                .build();

        // Call the Pricing API to get pricing information
        GetProductsResponse getProductsResponse = pricingClient.getProducts(getProductsRequest);

        // Extract and print pricing information
        System.out.println(getProductsResponse.toString());

        // Close the PricingClient
        pricingClient.close();
    }

    ///////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////

    //////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////


    @Override
    public void calculateArchitectureCost(ArchitectureDTO architecture) {
        Dotenv dotenv;
        if(new File("/etc/secrets/.env").exists()){
            System.out.println("pipeline");
            dotenv = Dotenv.configure()
                    .directory("/etc/secrets/.env")
                    .ignoreIfMalformed()
                    .ignoreIfMissing()
                    .load();
        } else {
            System.out.println("local");
            dotenv = Dotenv.configure()
                    .ignoreIfMalformed()
                    .ignoreIfMissing()
                    .load();
        }

        String accessKey = dotenv.get("AWS_ACCESS_KEY");
        String secretKey = dotenv.get("AWS_SECRET_KEY");

        for(ServiceItemDTO serviceItem : architecture.getResources()){
            calculateServiceCost(serviceItem, accessKey, secretKey);
        }
    }

    private void calculateServiceCost(ServiceItemDTO serviceItem, String accessKey, String secretKey) {
        if(serviceItem.getName() == null){
            return;
        }

        System.out.println("Service name -> " + serviceItem.getName());

        // Create an instance of AwsCredentials
        AwsCredentials awsCredentials = AwsBasicCredentials.create(accessKey, secretKey);

        // Create an instance of AwsCredentialsProvider using static credentials
        AwsCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(awsCredentials);

        // Specify the AWS region
        Region region = Region.EU_CENTRAL_1;

        // Create a PricingClient with credentials and region
        PricingClient pricingClient = PricingClient.builder()
                .credentialsProvider(credentialsProvider)
                .region(region)
                .build();

        // Add additional relevant filters based on your specific service
        Filter locationFilter = Filter.builder()
                .field("regionCode")
                .type("EQUALS") // Specify the type of the filter value
                .value("eu-west-2")
                .build();

        // Create GetProductsRequest with filters
        GetProductsRequest getProductsRequest = GetProductsRequest.builder()
                .serviceCode(serviceItem.getName().replaceAll(" ","")).filters(locationFilter)
                .build();

        // Call the Pricing API with filters
        GetProductsResponse getProductsResponse = pricingClient.getProducts(getProductsRequest);
        System.out.println(getProductsResponse.toString());
        System.out.println("///////////////////////////////////////////////////////////////////////////////");
        pricingClient.close();
    }

}
