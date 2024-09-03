package com.example.drools.web;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import jakarta.validation.constraints.Size;
import jakarta.validation.Valid;

@Data
public class KundenDTO {
    @Min(18)
    @Max(150)
    @Schema(description = "The age of the customer. Must be between 18 and 150 inclusive.")
    private int alter;
    @Valid
    private Lokation lokation;

    @Data
    public static class Lokation {
        @Size(min = 1, max = 60)
        @Schema(description = "The street address of the customer. Must be between 1 and 60 characters long.")
        private String strasse;
    }
}
