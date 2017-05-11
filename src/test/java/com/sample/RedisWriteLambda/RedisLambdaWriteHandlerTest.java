package com.sample.RedisWriteLambda;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class RedisLambdaWriteHandlerTest {

    private static Map<String,String> input;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
    	input = new HashMap<String, String>();
        input.put("Name", "Jemmy");
        input.put("Company", "TCS");
        input.put("Location", "Kolkata");
        
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

    @Test
    public void testLambdaFunctionHandler() {
    	RedisLambdaWriteHandler handler = new RedisLambdaWriteHandler();
        Context ctx = createContext();

        Object output = handler.handleRequest(input, ctx);       
        
        if (output != null) {
            System.out.println(output.toString());
        }
    }
}
