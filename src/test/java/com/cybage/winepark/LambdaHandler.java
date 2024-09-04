public class LambdaHandler implements RequestHandler<Object, String> {

    @Override
    public String handleRequest(Object input, Context context) {
        return "{\"message\": \"Hello, AWS Lambda!\"}";
    }
}

