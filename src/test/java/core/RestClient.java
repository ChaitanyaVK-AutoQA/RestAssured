package core;
import io.restassured.response.Response;
import utils.ConfigReader;
import java.io.File;
import java.util.Map;
import static io.restassured.RestAssured.*;
public class RestClient {
    private static final String BASE_URI = ConfigReader.get("base.uri");
    private static final String AUTH_TOKEN = ConfigReader.get("auth.token");
    private static io.restassured.specification.RequestSpecification requestSpec() {
        io.restassured.specification.RequestSpecification spec = given()
                .baseUri(BASE_URI)
                .header("Content-Type", "application/json");
        if (AUTH_TOKEN != null && !AUTH_TOKEN.isEmpty()) spec.header("Authorization", AUTH_TOKEN);
        return spec;
    }
    public static Response get(String endpoint) {
        return requestSpec().when().get(endpoint).then().extract().response();
    }
    public static Response get(String endpoint, Map<String, ?> queryParams) {
        return requestSpec().queryParams(queryParams).when().get(endpoint).then().extract().response();
    }
    public static Response get(String endpoint, Map<String, ?> pathParams, Map<String, ?> queryParams) {
        return requestSpec().pathParams(pathParams).queryParams(queryParams).when().get(endpoint).then().extract().response();
    }
    public static Response post(String endpoint, File jsonFile) {
        return requestSpec().body(jsonFile).when().post(endpoint).then().extract().response();
    }
    public static Response put(String endpoint, File jsonFile) {
        return requestSpec().body(jsonFile).when().put(endpoint).then().extract().response();
    }
    public static Response delete(String endpoint) {
        return requestSpec().when().delete(endpoint).then().extract().response();
    }
}
