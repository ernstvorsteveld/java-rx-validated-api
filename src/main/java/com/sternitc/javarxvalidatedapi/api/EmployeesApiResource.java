package com.sternitc.javarxvalidatedapi.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.sternitc.generated.api.EmployeesApi;
import com.sternitc.generated.model.*;
import com.sternitc.javarxvalidatedapi.api.create.CreateEmployeeService;
import com.sternitc.javarxvalidatedapi.api.get.GetEmployeeService;
import com.sternitc.javarxvalidatedapi.domain.Employee;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/employees")
public class EmployeesApiResource implements EmployeesApi {

    private GetEmployeeService getEmployeeService;
    private CreateEmployeeService createEmployeeService;
    private PatchEmployeeService patchEmployeeService;
    private ObjectMapper objectMapper;

    @RequestMapping(
            method = RequestMethod.POST
    )
    @Override
    public Mono<ResponseEntity<CreateEmployeeResponse>> createEmployee(
            @Valid @RequestBody Mono<CreateEmployeeRequest> createEmployeeRequest,
            final ServerWebExchange exchange) {
        return createEmployeeRequest
                .map(EmployeeMapper.INSTANCE::toCreateCommand)
                .map(createEmployeeService::create)
                .map(uuid -> new ResponseBuilder(exchange).created(uuid));
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/{id}",
            produces = {"application/json"}
    )
    @ResponseBody
    @Override
    public Mono<ResponseEntity<GetEmployeeResponse>> getEmployee(
            @Pattern(regexp = "uuid") @PathVariable String id,
            final ServerWebExchange exchange) {
        return Mono.just(getEmployeeService.get(id))
                .map(EmployeeMapper.INSTANCE::toApi)
                .map(response -> new ResponseBuilder(exchange).getEmployee(response));
    }

    @RequestMapping(
            method = RequestMethod.PATCH,
            value = "/{id}",
            consumes = {"application/json-patch+json"}
    )
    public Mono<ResponseEntity<PatchEmployeeResponse>> patchEmployee(
            @Pattern(regexp = "uuid") @PathVariable("id") String id,
            @Valid @RequestBody Mono<String> patchEmployeeRequest,
            final ServerWebExchange exchange) {
        return patchEmployeeRequest
                .log()
                .map(p -> patchEmployeeService.patch(id, p))
                .map(e -> new ResponseBuilder(exchange)
                        .patched(EmployeeMapper.INSTANCE.toPatchResponse(e)));
    }

    private record ResponseBuilder(ServerWebExchange exchange) {
        public ResponseEntity<CreateEmployeeResponse> created(String uuid) {
            return ResponseEntity
                    .created(URI.create(exchange.getRequest().getURI().toString() + "/" + uuid))
                    .build();
        }

        public ResponseEntity<GetEmployeeResponse> getEmployee(GetEmployeeResponse response) {
            return ResponseEntity.ok(response);
        }

        public ResponseEntity<PatchEmployeeResponse> patched(PatchEmployeeResponse response) {
            return ResponseEntity.ok(response);
        }
    }
}
