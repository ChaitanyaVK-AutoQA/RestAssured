package utils;
import io.restassured.response.Response;
import reporting.ExtentReportManager;
public class ApiLogger {
    public static void logRequest(String method, String endpoint, String body) {
        ExtentReportManager.logInfo("🌐 API Request: " + method + " " + endpoint);
        if (body != null && !body.isEmpty()) ExtentReportManager.logInfo("📦 Request Body: <pre>" + body + "</pre>");
    }
    public static void logResponse(Response response) {
        ExtentReportManager.logInfo("🔎 Response Code: " + response.getStatusCode());
        ExtentReportManager.logInfo("⏱️ Response Time: " + response.getTime() + " ms");
        ExtentReportManager.logInfo("📄 Response Body: <pre>" + response.getBody().asPrettyString() + "</pre>");
    }
}
