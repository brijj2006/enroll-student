package com.school.project.enrollstudent.bdd;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Set;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Stepdefs {

    public static void main(String[] args) throws IOException {
        Stepdefs obj = new Stepdefs();
        obj.getRequest();
    }

    public CloseableHttpResponse getResponse(String uri) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = client.execute(httpGet);
        return response;
    }

    public CloseableHttpResponse postResponse(String uri, String requestBody) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(uri);

        /*set request body*/
        StringEntity stringEntity = new StringEntity(requestBody);
        httpPost.setEntity(stringEntity);

        /*set request headers*/
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");

        CloseableHttpResponse response = client.execute(httpPost);
        return response;
    }

    public void getRequest() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8080/fetchStudents");
        CloseableHttpResponse response = client.execute(httpGet);
        System.out.println("Status : " + response.getStatusLine().getStatusCode());
        client.close();
    }

    private String convertInputStreamToString(InputStream is) throws IOException {

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[8192];
        int length;
        while ((length = is.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }

        // Java 1.1
        return result.toString(StandardCharsets.UTF_8.name());


    }

    @Given("user is entitled to access the school record")
    public void userIsEntitledToAccessTheSchoolRecord() {
    }


    @When("user search for all student records")
    public void userSearchForAllStudentRecords() throws IOException {
        CloseableHttpResponse response = getResponse("http://localhost:8080/fetchStudents");
        ResponseContext.setStatusCode(String.valueOf(response.getStatusLine().getStatusCode()));
    }

    @Then("existing record of students is fetched")
    public void existingRecordOfStudentsIsFetched(DataTable table) {
        List<Map<String, String>> maps = table.asMaps(String.class, String.class);
        maps.forEach(map -> {
            Set<String> keys = map.keySet();
            for (String key : keys) {
                switch (key) {
                    case "status":
                        Assert.assertEquals(map.get(key), ResponseContext.getStatusCode());
                        break;
                    case "firstName":
                        Assert.assertEquals(map.get(key), ResponseContext.getFirstName());
                        break;
                    case "lastName":
                        Assert.assertEquals(map.get(key), ResponseContext.getLastName());
                        break;
                    case "className":
                        Assert.assertEquals(map.get(key), ResponseContext.getClassName());
                        break;
                    case "nationality":
                        Assert.assertEquals(map.get(key), ResponseContext.getNationality());
                        break;
                }
            }
        });

    }

    @When("user search student record for id {int}")
    public void userSearchStudentRecordForId(int id) throws IOException, JSONException {
        CloseableHttpResponse response = getResponse("http://localhost:8080//fetchStudents/id/" + id);
        String json = IOUtils.toString(response.getEntity().getContent(), "UTF-8");

        JSONObject jsonObject = new JSONObject(json);
        ResponseContext.setStatusCode(String.valueOf(response.getStatusLine().getStatusCode()));
        ResponseContext.setFirstName(jsonObject.getString("firstName"));
        ResponseContext.setLastName(jsonObject.getString("lastName"));
        ResponseContext.setClassName(jsonObject.getString("className"));
        ResponseContext.setNationality(jsonObject.getString("nationality"));
    }

    @When("enroll a new student")
    public void enrollANewStudent() {
    }

    @Then("record is created successfully")
    public void recordIsCreatedSuccessfully() {
    }

    @When("user search student record for class {string}")
    public void userSearchStudentRecordForClass(String arg0) {
    }
}
