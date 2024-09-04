package com.cybage.winepark;

import com.amazonaws.services.lambda.runtime.Context;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LambdaHandlerTest {

    @Test
    public void testHandleRequest() {
        LambdaHandler handler = new LambdaHandler();
        String result = handler.handleRequest(null, null);
        assertEquals("Hello, AWS Lambda!", result);
    }
}

