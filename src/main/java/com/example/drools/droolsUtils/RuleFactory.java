package com.example.drools.droolsUtils;

import com.example.drools.model.Condition;
import com.example.drools.model.Rule;
import com.example.drools.web.KundenDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * The {@code RuleFactory} class is responsible for creating Drools {@link Rule} objects based on provided {@link Condition} instances.
 * <p>
 * This class validates the input condition to ensure that it contains the necessary information before creating a rule.
 * It handles the conversion of the condition's details into a {@link Rule} object suitable for use with the Drools engine.
 * </p>
 */
@Component
@Log4j2
public class RuleFactory {

    /**
     * Default constructor for {@code RuleFactory}.
     * <p>
     * This constructor initializes an instance of {@code RuleFactory}. It is provided explicitly here
     * to ensure proper documentation and to avoid Javadoc warnings about the default constructor.
     * </p>
     */
    public RuleFactory() {
        // Default constructor
    }

    /**
     * Creates a {@link Rule} object based on the provided {@link Condition}.
     * <p>
     * The method validates that the {@code condition} is not {@code null}, that its ID is not {@code null},
     * and that its value is not empty. If any of these conditions are not met, a warning is logged and {@code null} is returned.
     * If the condition is valid, a new {@code Rule} object is created and returned.
     * </p>
     *
     * @param condition the condition used to generate the rule. Must not be {@code null}.
     * @return a {@link Rule} object if the condition is valid; {@code null} otherwise.
     */
    public Rule createRule(Condition condition) {
        if (condition == null || condition.getId() == null || !StringUtils.hasLength(condition.getValue())) {
            log.warn("Condition, ID, and value cannot be null or empty. Condition: {}", condition);
            return null;
        }
        return new Rule(condition.getId().toString(), KundenDTO.class.getName(), condition.getValue());
    }
}