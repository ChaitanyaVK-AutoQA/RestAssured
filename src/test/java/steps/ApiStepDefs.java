package steps;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import core.RestClient;
import utils.ApiUtils;
import utils.DynamicSchemaUtils;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
public class ApiStepDefs {
    private Response response;
    @Given("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        response = RestClient.get(endpoint);
    }
    @Given("I send a GET request to {string} with query params")
    public void i_send_a_get_request_with_query_params(String endpoint, Map<String, String> queryParams) {
        response = RestClient.get(endpoint, queryParams);
    }
    @Given("I send a GET request to {string} with path param {string} as {string}")
    public void i_send_a_get_request_with_path_param(String endpoint, String key, String value) {
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put(key, value);
        response = RestClient.get(endpoint, pathParams, new HashMap<>());
    }
    @Given("I send a POST request to {string} with payload {string}")
    public void i_send_a_post_request_to_with_payload(String endpoint, String payloadFile) {
        File file = new File("src/test/resources/payloads/" + payloadFile);
        response = RestClient.post(endpoint, file);
    }
    @Given("I send a PUT request to {string} with payload {string}")
    public void i_send_a_put_request_to_with_payload(String endpoint, String payloadFile) {
        File file = new File("src/test/resources/payloads/" + payloadFile);
        response = RestClient.put(endpoint, file);
    }
    @Given("I send a DELETE request to {string}")
    public void i_send_a_delete_request_to(String endpoint) {
        response = RestClient.delete(endpoint);
    }
    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int statusCode) {
        ApiUtils.validateStatusCode(response, statusCode);
    }
    @Then("the response should contain {string}")
    public void the_response_should_contain(String text) {
        ApiUtils.validateBodyContains(response, text);
    }
    @Then("I generate schema from response and save as {string}")
    public void generate_schema_from_response(String schemaFile) throws Exception {
        DynamicSchemaUtils.generateSchema(response, schemaFile);
    }
    @Then("the response should match schema {string}")
    public void the_response_should_match_schema(String schemaFile) {
        ApiUtils.validateJsonSchema(response, schemaFile);
    }
    @Then("the response should match dynamic schema {string}")
    public void validate_with_dynamic_schema(String schemaFile) {
        DynamicSchemaUtils.validateWithSchema(response, schemaFile);
    }
}
