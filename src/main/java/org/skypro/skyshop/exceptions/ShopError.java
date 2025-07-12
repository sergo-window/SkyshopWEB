package org.skypro.skyshop.exceptions;


import com.fasterxml.jackson.annotation.JsonProperty;

public class ShopError {
    @JsonProperty("error_code")
    private final String code;

    @JsonProperty("error_message")
    private final String message;

    public ShopError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
