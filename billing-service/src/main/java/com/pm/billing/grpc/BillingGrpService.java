package com.pm.billing.grpc;

import com.pm.billing.BillingRequest;
import com.pm.billing.BillingResponse;
// BillingServiceImplBase is the generated base class from your .proto file
// You MUST extend this to implement your gRPC service
import com.pm.billing.BillingServiceGrpc.BillingServiceImplBase;
// StreamObserver is gRPC's way of sending responses back to the caller
// Think of it like a callback mechanism
import io.grpc.stub.StreamObserver;
// @GrpcService registers this class as a gRPC server with Spring Boot
// Similar to how @RestController registers a REST controller
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


// @GrpcService tells Spring Boot to expose this as a gRPC endpoint
// It will listen on the grpc.server.port defined in application.properties
@GrpcService
public class BillingGrpService extends BillingServiceImplBase {

    // Standard logger to track incoming requests
    private static final Logger log = LoggerFactory.getLogger(BillingGrpService.class);

    // This method is generated from your .proto file definition:
    // rpc CreateBillingAccount (BillingRequest) returns (BillingResponse)
    // It is called whenever patient-service makes a gRPC call to billing-service
    @Override
    public void createBillingAccount(BillingRequest billingRequest,
                                     StreamObserver<BillingResponse> responseObserver) {

        // Log the incoming request for debugging
        // billingRequest contains patientId, name, email from patient-service
        log.info("createBillingAccount request received {}", billingRequest.toString());

        // TODO: Real business logic goes here
        // e.g. save billing account to database
        // e.g. calculate initial billing amount
        // e.g. integrate with payment gateway

        // Build the response using the generated Builder pattern from .proto
        // setAccountId and setStatus map to the fields in BillingResponse message
        BillingResponse response = BillingResponse.newBuilder()
                .setAccountId("1234")   // hardcoded for now, should be generated/saved
                .setStatus("ACTIVE")    // billing account is active
                .build();

        // Send the response back to the caller (patient-service)
        // onNext() sends the actual response object
        responseObserver.onNext(response);

        // onCompleted() signals that the response is fully sent
        // ALWAYS call this after onNext() otherwise the caller will hang waiting
        responseObserver.onCompleted();
    }
}
