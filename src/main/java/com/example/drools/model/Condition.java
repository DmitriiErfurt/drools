package com.example.drools.model;

import lombok.Data;

import java.util.UUID;

/**
 * Represents a condition with a unique identifier and a value.
 * <p>
 * This class encapsulates the properties of a condition used in the Drools rule engine,
 * including an identifier and a string value.
 * </p>
 */
@Data
public class Condition {
    /**
     * A unique identifier for this condition.
     */
    private UUID id;

    /**
     * The value associated with this condition.
     */
    private String value;

    /**
     * Constructs a new Condition with the specified id and value.
     *
     * @param id the unique identifier for this condition
     * @param value the value associated with this condition
     */
    public Condition(UUID id, String value) {
        this.id = id;
        this.value = value;
    }
}

