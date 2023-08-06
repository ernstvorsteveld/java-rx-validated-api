package com.sternitc.javarxvalidatedapi.api.patch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.openapitools.jackson.nullable.JsonNullable;

@AllArgsConstructor
@Getter
@Builder
public class PatchCommand {

    public enum PatchOp {
        ADD("add"),
        REPLACE("replace"),
        TEST("test"),
        REMOVE("remove"),
        MOVE("move"),
        COPY("copy");

        private String value;

        PatchOp(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public String toString() {
            return String.valueOf(value);
        }

        public static PatchCommand.PatchOp from(String value) {
            for (PatchCommand.PatchOp b : PatchCommand.PatchOp.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }

    private String path;
    private PatchCommand.PatchOp patchOp;
    private JsonNullable<Object> value;

}
