package com.sternitc.javarxvalidatedapi.api;

import com.sternitc.generated.model.CreateEmployeeRequest;
import com.sternitc.generated.model.GetEmployeeResponse;
import com.sternitc.generated.model.JSONPatchRequestAddReplaceTest;
import com.sternitc.generated.model.PatchEmployeeResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.IdGenerator;

import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = EmployeesApiResource.class, excludeAutoConfiguration = ApiIdGeneratorConfiguration.class)
@Import({ApiConfiguration.class, EmployeeApiTestConfiguration.class})
@AutoConfigureMockMvc
class EmployeesApiResourceTest {

    @Autowired
    private WebTestClient webClient;

    @Autowired
    IdGenerator idGenerator;

    @Test
    public void should_create_and_get_employee() {
        CreateEmployeeRequest request = new CreateEmployeeRequest("John", "Doe");
        request.dateOfBirth("2017-07-21");
        WebTestClient.ResponseSpec exchange = webClient.post()
                .uri("http://localhost:8080/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectHeader()
                .location("http://localhost:8080/employees/" + idGenerator.generateId().toString());

        GetEmployeeResponse response = new GetEmployeeResponse("John", "Doe");
        response.setDateOfBirth("2017-07-21");
        webClient.get()
                .uri("http://localhost:8080/employees/" + idGenerator.generateId().toString())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(GetEmployeeResponse.class)
                .value(r -> r.getFirstName(), equalTo("John"))
                .value(r -> r.getLastName(), equalTo("Doe"))
                .value(r -> r.getDateOfBirth(), equalTo("21/07/2017, 00:00"));
    }

    @Test
    public void should_create_and_patch() {
        CreateEmployeeRequest request = new CreateEmployeeRequest("John", "Doe");
        request.dateOfBirth("2017-07-21");
        WebTestClient.ResponseSpec exchange = webClient.post()
                .uri("http://localhost:8080/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange();

        String patch = "[{ \"op\": \"replace\", \"path\": \"/name/last\", \"value\": \"changed\" }]";
        webClient.patch()
                .uri("http://localhost:8080/employees/" + idGenerator.generateId().toString())
                .header("Content-Type", "application/json-patch+json")
                .bodyValue(patch)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(PatchEmployeeResponse.class)
                .value(r -> r.getLastName(), equalTo("changed"));

    }

}