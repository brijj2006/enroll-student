package com.school.project.enrollstudent.bdd;

import io.cucumber.datatable.DataTable;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TestPropertySource(properties = "classpath:test.properties")
public class Utils {

    @Value("${header_accept}")
    private String header_accept;

    @Value("${header_contentType}")
    private String header_contentType;

    private static final Logger logger = LoggerFactory.getLogger(Utils.class);

    /*execute get request*/
    public CloseableHttpResponse getResponse(String uri) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = client.execute(httpGet);
        return response;
    }

    /*execute post request*/
    public CloseableHttpResponse postResponse(String uri, String requestBody) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(uri);

        /*set request body*/
        StringEntity stringEntity = new StringEntity(requestBody);
        httpPost.setEntity(stringEntity);

        /*set request headers*/
        httpPost.setHeader("Accept", header_accept);
        httpPost.setHeader("Content-Type", header_contentType);

        CloseableHttpResponse response = client.execute(httpPost);
        return response;
    }

    /*execute put request*/
    public CloseableHttpResponse putResponse(String uri, String requestBody) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(uri);

        /*set request body*/
        StringEntity stringEntity = new StringEntity(requestBody);
        httpPut.setEntity(stringEntity);

        /*set request headers*/
        httpPut.setHeader("Accept", header_accept);
        httpPut.setHeader("Content-Type", header_contentType);

        CloseableHttpResponse response = client.execute(httpPut);
        return response;
    }

    /*execute delete request*/
    public CloseableHttpResponse deleteResponse(String uri, String requestBody) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpDeleteWithBody httpDelete = new HttpDeleteWithBody(uri);

        /*set request body*/
        StringEntity stringEntity = new StringEntity(requestBody);
        httpDelete.setEntity(stringEntity);

        /*set request headers*/
        httpDelete.setHeader("Accept", header_accept);
        httpDelete.setHeader("Content-Type", header_contentType);

        CloseableHttpResponse response = client.execute(httpDelete);
        return response;
    }

    /*create request body using input parameters from data table*/
    public String createRequestBody(DataTable dataTable) {
        StringBuilder requestBody = new StringBuilder();
        requestBody.append("{");
        int i = 1;
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map map : maps) {
            Set<String> keys = map.keySet();
            for (String key : keys) {
                if (key.equals("id")) {
                    requestBody.append("\"" + key + "\" : " + map.get(key));
                } else {
                    requestBody.append("\"" + key + "\" : \"" + map.get(key) + "\"");
                }
                if (i == keys.size()) {
                    break;
                } else {
                    requestBody.append(",");
                    i = i + 1;
                }
            }
            requestBody.append("}");
        }
        return (requestBody.toString());
    }


}
