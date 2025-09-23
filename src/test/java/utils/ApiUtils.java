package utils;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import java.io.File;
import static org.hamcrest.MatcherAssert.assertThat;
public class ApiUtils {
    public static void validateStatusCode(Response response, int expectedStatusCode) {
        assertThat("Status code mismatch!", response.getStatusCode() == expectedStatusCode);
    }
    public static void validateBodyContains(Response response, String expectedText) {
        assertThat("Response does not contain expected text: " + expectedText,
                response.getBody().asString().contains(expectedText));
    }
    public static void validateJsonSchema(Response response, String schemaFileName) {
        File schemaFile = new File("src/test/resources/schemas/" + schemaFileName);
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(schemaFile));
    }
}
