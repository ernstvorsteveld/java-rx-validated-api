package com.sternitc.javarxvalidatedapi.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sternitc.generated.model.JSONPatchRequestAddReplaceTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MapperTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void should_marshall() throws JsonProcessingException {
        JSONPatchRequestAddReplaceTest inner =
                new JSONPatchRequestAddReplaceTest("/name", JSONPatchRequestAddReplaceTest.OpEnum.REPLACE, "new");

        String value = mapper.writeValueAsString(inner);
        assertThat(value).isEqualTo("{\"path\":\"/name\",\"op\":\"replace\",\"value\":{\"present\":true}}");

        JSONPatchRequestAddReplaceTest object = (JSONPatchRequestAddReplaceTest) mapper.readValue(value, JSONPatchRequestAddReplaceTest.class);
        assertThat(object.getValue()).isEqualTo("niks");
    }
}
