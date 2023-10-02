package io.angularpay.resources.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class GenericResourceApiModel {

    @NotEmpty
    private String name;

    @NotEmpty
    private String type;

    @NotEmpty
    @JsonProperty("base_64_image_string")
    private String base64ImageString;

    @NotEmpty
    private String category;
}
