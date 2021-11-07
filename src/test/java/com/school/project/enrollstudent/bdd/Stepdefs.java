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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Stepdefs {

    private static final Logger logger = LoggerFactory.getLogger(Stepdefs.class);

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
                    case "message":
                        Assert.assertEquals(map.get(key), ResponseContext.getMessage());
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
        if (response.getStatusLine().getStatusCode() == 200) {
            ResponseContext.setFirstName(jsonObject.getString("firstName"));
            ResponseContext.setLastName(jsonObject.getString("lastName"));
            ResponseContext.setClassName(jsonObject.getString("className"));
            ResponseContext.setNationality(jsonObject.getString("nationality"));
        } else {
            ResponseContext.setMessage(jsonObject.getString("message"));
        }

    }

    @When("enroll a new student")
    public void enrollANewStudent(DataTable table) throws IOException {
        StringBuilder requestBody = new StringBuilder();
        requestBody.append("{");
        int i = 1;
        List<Map<String, String>> maps = table.asMaps(String.class, String.class);
        for (Map map : maps) {
            Set<String> keys = map.keySet();
            for (String key : keys) {
                requestBody.append("\"" + key + "\" : \"" + map.get(key) + "\"");
                if (i == keys.size()) {
                    break;
                } else {
                    requestBody.append(",");
                    i = i + 1;
                }
            }
            requestBody.append("}");
        }
        String postBody = requestBody.toString();
        logger.info(postBody);

        CloseableHttpResponse response = postResponse("http://localhost:8080/students", postBody);
        ResponseContext.setStatusCode(String.valueOf(response.getStatusLine().getStatusCode()));
    }

    @Then("record is created successfully")
    public void recordIsCreatedSuccessfully(DataTable table) {
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
                    case "message":
                        Assert.assertEquals(map.get(key), ResponseContext.getMessage());
                        break;
                }
            }
        });
    }

    @When("user search student record for class {string}")
    public void userSearchStudentRecordForClass(String className) throws IOException, JSONException {
        CloseableHttpResponse response = getResponse("http://localhost:8080//fetchStudents/class/" + className);
        ResponseContext.setStatusCode(String.valueOf(response.getStatusLine().getStatusCode()));
        String json = IOUtils.toString(response.getEntity().getContent(), "UTF-8");

        if (response.getStatusLine().getStatusCode() == 200) {
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject = jsonArray.getJSONObject(0);

            ResponseContext.setFirstName(jsonObject.getString("firstName"));
            ResponseContext.setLastName(jsonObject.getString("lastName"));
            ResponseContext.setClassName(jsonObject.getString("className"));
            ResponseContext.setNationality(jsonObject.getString("nationality"));
        } else {
            JSONObject jsonObject = new JSONObject(json);
            ResponseContext.setMessage(jsonObject.getString("message"));
        }
    }
}
