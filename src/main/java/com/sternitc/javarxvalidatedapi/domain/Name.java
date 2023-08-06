package com.sternitc.javarxvalidatedapi.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@NoArgsConstructor
@Getter
public class Name {

    private String first;
    private String middle;
    private String last;

    public Name(String first, String middle, String last) {
        if (last == null || last.isEmpty()) {
            throw new InvalidInputException("The last name must not be null or empty.");
        }
        this.first = first;
        this.middle = middle;
        this.last = last;
    }

    public String fullName() {
        return (StringUtils.hasText(first) ? first + " " : "") +
                (StringUtils.hasText(middle) ? middle + " " : "") +
                (StringUtils.hasText(last) ? last : "");
    }

    public static class InvalidInputException extends RuntimeException {
        public InvalidInputException(String message) {
            super(message);
        }
    }

    public static class NameBuilder {
        private String first;
        private String middle;
        private String last;

        public NameBuilder(String last) {
            if (last == null || last.isEmpty()) {
                throw new InvalidInputException("The last name must not be null or empty.");
            }
            this.last = last;
        }

        public NameBuilder first(String first) {
            this.first = first;
            return this;
        }

        public NameBuilder middle(String middle) {
            this.middle = middle;
            return this;
        }

        public Name build() {
            return new Name(this.first, this.middle, this.last);
        }
    }

}
