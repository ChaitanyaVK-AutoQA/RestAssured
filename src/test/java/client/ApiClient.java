package client;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import utils.ApiLogger;
import utils.ConfigReader;
public class ApiClient {
    private static final String BASE = ConfigReader.get("base.uri");
    public static Response get(String endpoint) {
        ApiLogger.logRequest("GET", endpoint, null);
        Response response = RestAssured.given().baseUri(BASE).when().get(endpoint);
        ApiLogger.logResponse(response);
        return response;
    }
    public static Response post(String endpoint, String body) {
        ApiLogger.logRequest("POST", endpoint, body);
        Response response = RestAssured.given().baseUri(BASE)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(endpoint);
        ApiLogger.logResponse(response);
        return response;
    }
    public static Response put(String endpoint, String body) {
        ApiLogger.logRequest("PUT", endpoint, body);
        Response response = RestAssured.given().baseUri(BASE)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .put(endpoint);
        ApiLogger.logResponse(response);
        return response;
    }
    public static Response delete(String endpoint) {
        ApiLogger.logRequest("DELETE", endpoint, null);
        Response response = RestAssured.given().baseUri(BASE).when().delete(endpoint);
        ApiLogger.logResponse(response);
        return response;
    }
}
