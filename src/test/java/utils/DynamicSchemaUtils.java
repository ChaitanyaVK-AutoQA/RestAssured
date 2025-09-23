package utils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import reporting.ExtentReportManager;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
public class DynamicSchemaUtils {
    private static final String SCHEMA_PATH = "src/test/resources/schemas/";
    public static void generateSchema(Response response, String schemaName) throws IOException {
        File schemaFile = new File(SCHEMA_PATH + schemaName);
        Files.createDirectories(schemaFile.getParentFile().toPath());
        String body = response.getBody().asString();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(body);
        mapper.writerWithDefaultPrettyPrinter().writeValue(schemaFile, node);
        ExtentReportManager.logInfo("Schema generated: " + schemaFile.getAbsolutePath());
    }
    public static void validateWithSchema(Response response, String schemaName) {
        ApiUtils.validateJsonSchema(response, schemaName);
        ExtentReportManager.logPass("Response validated against dynamic schema: " + schemaName);
    }
}
