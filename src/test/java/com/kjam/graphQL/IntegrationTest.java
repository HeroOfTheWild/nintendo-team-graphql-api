package com.kjam.graphQL;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.kjam.graphQL.configurations.TestDatabaseConfiguration;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@ContextConfiguration(classes = {TestDatabaseConfiguration.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

	private static final String GRAPHQL_QUERY_REQUEST_PATH  = "graphql/queries/requests/%s.graphql";
    private static final String GRAPHQL_QUERY_RESPONSE_PATH = "graphql/queries/responses/%s.json";


	@Autowired
	private GraphQLTestTemplate testTemplate;

	private String toJsonResponse(String jsonFileLocation) throws IOException {
        return IOUtils.toString(new ClassPathResource(jsonFileLocation).getInputStream(), StandardCharsets.UTF_8);
    }

    private void verifySuccessfulGraphQLResponse(GraphQLResponse response, String expectedResponse) throws JSONException {
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONAssert.assertEquals(expectedResponse, response.getRawResponse().getBody(), true);
    }

    private GraphQLResponse retrieveGraphQLResponse(String queryFileLocation) throws IOException {
        return testTemplate.postForResource(queryFileLocation);
    }

	@Test
	void query_name_successfully() throws IOException, JSONException {
		var fileName = "name";
		var response = retrieveGraphQLResponse(String.format(GRAPHQL_QUERY_REQUEST_PATH, fileName));
        var expectedResponse = toJsonResponse(String.format(GRAPHQL_QUERY_RESPONSE_PATH, fileName));
		verifySuccessfulGraphQLResponse(response, expectedResponse);
	}

    @Test
	void query_team_successfully() throws IOException, JSONException {
		var fileName = "team";
		var response = retrieveGraphQLResponse(String.format(GRAPHQL_QUERY_REQUEST_PATH, fileName));
        var expectedResponse = toJsonResponse(String.format(GRAPHQL_QUERY_RESPONSE_PATH, fileName));
		verifySuccessfulGraphQLResponse(response, expectedResponse);
	}

    @Test
	void query_teammates_successfully() throws IOException, JSONException {
		var fileName = "teammates";
		var response = retrieveGraphQLResponse(String.format(GRAPHQL_QUERY_REQUEST_PATH, fileName));
        var expectedResponse = toJsonResponse(String.format(GRAPHQL_QUERY_RESPONSE_PATH, fileName));
		verifySuccessfulGraphQLResponse(response, expectedResponse);
	}
}
