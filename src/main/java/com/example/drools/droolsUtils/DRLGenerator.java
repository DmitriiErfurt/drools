package com.example.drools.droolsUtils;

import com.example.drools.model.Rule;
import lombok.extern.log4j.Log4j2;
import org.drools.template.ObjectDataCompiler;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

/**
 * The {@code DRLGenerator} class is responsible for generating DRL (Drools Rule Language) files from a list of {@link Rule} objects.
 * <p>
 * It utilizes Drools' {@link ObjectDataCompiler} to compile the rules based on a predefined DRL template.
 * </p>
 */
@Component
@Log4j2
public class DRLGenerator {

    /**
     * The path to the DRL template file.
     * <p>
     * This path is used by the {@link #generateDRL(List)} method to load the template required for compiling the rules.
     * </p>
     */
    private static final String DRL_TEMPLATE_PATH = "templates/DynamicRules.drl";

    /**
     * Constructs a new {@code DRLGenerator} instance.
     * <p>
     * This default constructor initializes the {@code DRLGenerator} class, ready to generate DRL files.
     * </p>
     */
    public DRLGenerator() {
        // Default constructor
    }

    /**
     * Generates a DRL string based on the provided list of {@link Rule} objects.
     *
     * @param rules the list of rules to be compiled into DRL
     * @return a string containing the compiled DRL
     * @throws IllegalStateException if the DRL template cannot be found
     */
    public String generateDRL(List<Rule> rules) {
        ObjectDataCompiler compiler = new ObjectDataCompiler();
        InputStream template = Thread.currentThread().getContextClassLoader().getResourceAsStream(DRL_TEMPLATE_PATH);
        if (template == null) {
            log.error("DRL template not found at path: {}", DRL_TEMPLATE_PATH);
            throw new IllegalStateException("DRL template not found.");
        }
        String drl = compiler.compile(rules, template);
        log.info("Generated DRL:\n{}", drl);
        return drl;
    }
}