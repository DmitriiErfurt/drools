package com.example.drools.model;

import lombok.Data;

/**
 * Represents a rule in the Drools rule engine.
 * <p>
 * This class defines a rule with a name, a reference to a {@code kundenDTO} object,
 * and a value. It is used to manage rules within the Drools engine, where each rule
 * is identified by its name and linked to specific data and conditions.
 * </p>
 */
@Data
public class Rule {

    /**
     * The name of this rule.
     */
    private String name;

    /**
     * A reference to a {@code kundenDTO} object associated with this rule.
     */
    private String kundenDTO;

    /**
     * The value associated with this rule.
     */
    private String value;

    /**
     * Constructs a new {@code Rule} with default values.
     * <p>
     * This constructor initializes a {@code Rule} object with default values.
     * </p>
     */
    public Rule() {
        // Default constructor
    }

    /**
     * Constructs a new {@code Rule} with the specified parameters.
     * <p>
     * This constructor initializes a {@code Rule} object with the provided values.
     * </p>
     *
     * @param name the name of the rule
     * @param kundenDTO a reference to the {@code kundenDTO} object associated with this rule
     * @param value the value associated with this rule
     */
    public Rule(String name, String kundenDTO, String value) {
        this.name = name;
        this.kundenDTO = kundenDTO;
        this.value = value;
    }
}