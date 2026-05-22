package resources;

import io.qameta.allure.Allure;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class AllureRestAssuredFilter implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {

        // Execute the request
        Response response = ctx.next(requestSpec, responseSpec);

        // Attach request info
        String requestBody = requestSpec.getBody() != null ? requestSpec.getBody() : "";
        Allure.addAttachment("Request: " + requestSpec.getMethod() + " " + requestSpec.getURI(),
                requestBody);

        // Attach response body
        Allure.addAttachment("Response", response.prettyPrint());

        return response;
    }
}