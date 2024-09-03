package com.example.drools.droolsUtils;

import lombok.extern.log4j.Log4j2;
import org.drools.core.rule.consequence.KnowledgeHelper;
import org.springframework.util.StringUtils;

import java.util.Set;
import java.util.UUID;

/**
 * Utility class providing functions for working with Drools.
 * <p>
 * Includes a method to add unique identifiers of fired rules
 * to the specified {@code searchResponse} set.
 * </p>
 */
@Log4j2
public class DroolsFunctions {

    /**
     * Constructs a new {@code DroolsFunctions} instance.
     * <p>
     * This default constructor initializes the {@code DroolsFunctions} class.
     * </p>
     */
    public DroolsFunctions() {
        // Default constructor
    }

    /**
     * Adds the identifier of a fired rule to the {@code searchResponse} set.
     * <p>
     * The method extracts the rule's name from the {@code KnowledgeHelper} object,
     * assuming that the rule's name is a string in UUID format. If the string is not a valid UUID,
     * the method logs a warning and does not add the identifier to the set.
     * </p>
     *
     * @param knowledgeHelper the {@code KnowledgeHelper} object providing access to the fired rule's information.
     *                       This parameter must not be {@code null}.
     * @param searchResponse the set to which the unique identifiers of fired rules will be added.
     *                       This parameter must not be {@code null}.
     * @throws IllegalArgumentException if the rule's name is not a valid UUID.
     */
    public static void addFiredRule(final KnowledgeHelper knowledgeHelper, Set<UUID> searchResponse) {
        if (knowledgeHelper == null) {
            log.warn("knowledgeHelper == null");
            return;
        }

        if (knowledgeHelper.getRule() == null || !StringUtils.hasLength(knowledgeHelper.getRule().getName())) {
            log.warn("Rule can't be null or without the name");
            return;
        }

        String ruleName = knowledgeHelper.getRule().getName();
        try {
            UUID ruleUUID = UUID.fromString(ruleName);
            searchResponse.add(ruleUUID);
        } catch (IllegalArgumentException e) {
            log.warn("Rule name is not a valid UUID: " + ruleName, e);
        }
    }
}