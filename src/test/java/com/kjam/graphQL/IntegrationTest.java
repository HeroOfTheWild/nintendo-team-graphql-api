package com.kjam.graphQL;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

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

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

	private void verifyGraphQLResponse(String requestFile, String expectedResponseFile) throws IOException, JSONException {
		verifyGraphQLResponse(requestFile, expectedResponseFile, null);
	}

	private void verifyGraphQLResponse(String requestFile, String expectedResponseFile, ObjectNode variables) throws IOException, JSONException {
		var response = retrieveGraphQLResponse(String.format(GRAPHQL_QUERY_REQUEST_PATH, requestFile), variables);
        var expectedResponse = toJsonResponse(String.format(GRAPHQL_QUERY_RESPONSE_PATH, expectedResponseFile));
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONAssert.assertEquals(expectedResponse, response.getRawResponse().getBody(), true);
	}

	private String toJsonResponse(String jsonFileLocation) throws IOException {
        return IOUtils.toString(new ClassPathResource(jsonFileLocation).getInputStream(), StandardCharsets.UTF_8);
    }

    private GraphQLResponse retrieveGraphQLResponse(String queryFileLocation, ObjectNode variables) throws IOException {
        return testTemplate.perform(queryFileLocation, null, variables, Collections.emptyList());
    }

	@Test
	void query_name_successfully() throws IOException, JSONException {
		var fileName = "name";
		verifyGraphQLResponse(fileName, fileName);
	}

	@Test
	void query_myNintendoAccount_successfully() throws IOException, JSONException {
		var fileName = "myNintendoAccount";
		var variables = new ObjectNode(JsonNodeFactory.instance);
		variables.put("nintendoId", "nin0001");
		verifyGraphQLResponse(fileName, fileName, variables);
	}

	@Test
	void query_myPrimaryTeam_successfully() throws IOException, JSONException {
		var fileName = "myPrimaryTeam";
		var variables = new ObjectNode(JsonNodeFactory.instance);
		variables.put("nintendoId", "nin9999");
		verifyGraphQLResponse(fileName, fileName, variables);
	}

	@Test
	void query_myTeamInfo_successfully() throws IOException, JSONException {
		var fileName = "myTeams";
		verifyGraphQLResponse(fileName, fileName);
	}

    @Test
	void query_team_successfully() throws IOException, JSONException {
		var fileName = "team";
		verifyGraphQLResponse(fileName, fileName);
	}

    @Test
	void query_teammates_successfully() throws IOException, JSONException {
		var fileName = "teammates";
		verifyGraphQLResponse(fileName, fileName);
	}

	// Scalar Validation
	@Test
	void query_myPrimaryTeam_returnErrorWithInvalidId() throws IOException, JSONException {
		var requestFile = "myPrimaryTeam";
		var expectedResponseFile = "invalidNintendoId";
		var variables = new ObjectNode(JsonNodeFactory.instance);
		variables.put("nintendoId", "niError");
		verifyGraphQLResponse(requestFile, expectedResponseFile, variables);
	}
}
