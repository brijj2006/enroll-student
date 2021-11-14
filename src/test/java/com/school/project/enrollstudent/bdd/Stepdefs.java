package com.school.project.enrollstudent.bdd;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@CucumberContextConfiguration
@TestPropertySource("classpath:test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Stepdefs extends Utils {

    private static final Logger logger = LoggerFactory.getLogger(Stepdefs.class);

    @Value("${url_get_students}")
    private String url_get_students;

    @Value("${url_get_students_byClass}")
    private String url_get_students_byClass;

    @Value("${url_get_students_byId}")
    private String url_get_students_byId;

    @Value("${url_students}")
    private String url_students;

    @Given("user is entitled to access the school record")
    public void userIsEntitledToAccessTheSchoolRecord() {
    }

    @When("user search for all student records")
    public void userSearchForAllStudentRecords() throws IOException {
        CloseableHttpResponse response = getResponse(url_get_students);
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
        CloseableHttpResponse response = getResponse(url_get_students_byId + "/" + id);
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
    public void enrollANewStudent(DataTable dataTable) throws IOException {
        CloseableHttpResponse response = postResponse(url_students, createRequestBody(dataTable));
        ResponseContext.setStatusCode(String.valueOf(response.getStatusLine().getStatusCode()));
    }

    @Then("record is executed successfully")
    public void recordIsExecutedSuccessfully(DataTable table) {
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
        CloseableHttpResponse response = getResponse(url_get_students_byClass + "/" + className);
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

    @When("update an existing student record")
    public void updateAnExistingStudentRecord(DataTable dataTable) throws IOException {
        CloseableHttpResponse response = putResponse(url_students, createRequestBody(dataTable));
        ResponseContext.setStatusCode(String.valueOf(response.getStatusLine().getStatusCode()));
    }

    @When("delete an existing student record")
    public void deleteAnExistingStudentRecord(DataTable dataTable) throws IOException {
        CloseableHttpResponse response = deleteResponse(url_get_students, createRequestBody(dataTable));
        ResponseContext.setStatusCode(String.valueOf(response.getStatusLine().getStatusCode()));
    }
}
