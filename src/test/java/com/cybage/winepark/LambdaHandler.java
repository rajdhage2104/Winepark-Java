package com.cybage.winepark;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class LambdaHandler implements RequestHandler<Object, String> {

    private static ApplicationContext applicationContext;

    static {
        // Initialize the Spring context
        applicationContext = new SpringApplicationBuilder(WineParkApplication.class)
                .run();
    }

    @Override
    public String handleRequest(Object input, Context context) {
        // Use the Spring context to get beans and handle the request
        // Example: Call a service bean and return the result
        MyService myService = applicationContext.getBean(MyService.class);
        return myService.processRequest(input);
    }
}
