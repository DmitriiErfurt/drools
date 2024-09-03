package com.example.drools.droolsUtils;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.drools.io.ByteArrayResource;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code KieSessionManager} class manages the creation and storage of Drools {@link StatelessKieSession} instances.
 * <p>
 * It handles the compilation of DRL content into a Kie session and maintains a list of active sessions.
 * </p>
 */
@Component
@Getter
@Log4j2
public class KieSessionManager {

    /**
     * List of active {@link StatelessKieSession} instances.
     */
    private final List<StatelessKieSession> sessions = new ArrayList<>();

    /**
     * Path to the DRL file used for creating Kie sessions.
     * <p>
     * This path is used when writing the DRL content to a file system in order to compile it into a Kie session.
     * </p>
     */
    private static final String DRL_FILE_PATH = "src/main/resources/dynamicRules.drl";

    /**
     * Constructs a new {@code KieSessionManager} instance.
     * <p>
     * This default constructor initializes the {@code KieSessionManager} class and its session list.
     * </p>
     */
    public KieSessionManager() {
        // Default constructor
    }

    /**
     * Creates a new {@link StatelessKieSession} from the provided DRL content.
     * <p>
     * This method compiles the given DRL content into a new Kie session using the Drools API.
     * </p>
     *
     * @param drl the DRL content to be compiled into a Kie session
     * @return a {@link StatelessKieSession} ready to execute rules
     * @throws RuntimeException if an error occurs during the creation of the Kie session
     */
    public StatelessKieSession createSession(String drl) {
        try {
            KieServices services = KieServices.Factory.get();
            KieFileSystem kieFileSystem = services.newKieFileSystem();

            ByteArrayResource drlResource = new ByteArrayResource(drl.getBytes(StandardCharsets.UTF_8));
            kieFileSystem.write(DRL_FILE_PATH, drlResource);

            KieBuilder kieBuilder = services.newKieBuilder(kieFileSystem).buildAll();

            Results results = kieBuilder.getResults();
            if (results.hasMessages(Message.Level.ERROR)) {
                throw new IllegalStateException("Errors while building KieBase: " + results.getMessages());
            }

            KieContainer container = services.newKieContainer(services.getRepository().getDefaultReleaseId());
            KieBase kieBase = container.getKieBase();
            StatelessKieSession kieSession = kieBase.newStatelessKieSession();

            log.info("Created new StatelessKieSession.");
            return kieSession;
        } catch (Exception e) {
            log.error("Error creating Kie session: ", e);
            throw new RuntimeException("Failed to create Kie session.", e);
        }
    }

    /**
     * Adds a {@link StatelessKieSession} to the list of active sessions.
     * <p>
     * If the session is not {@code null}, it is added to the list of active sessions.
     * </p>
     *
     * @param session the {@link StatelessKieSession} to be added
     */
    public void addSession(StatelessKieSession session) {
        if (session != null) {
            sessions.add(session);
            log.info("Added new StatelessKieSession. Total sessions: {}", sessions.size());
        } else {
            log.warn("Attempted to add a null StatelessKieSession.");
        }
    }

    /**
     * Retrieves all active {@link StatelessKieSession} instances.
     * <p>
     * Returns a new list containing all currently active sessions.
     * </p>
     *
     * @return a list of active sessions
     */
    public List<StatelessKieSession> getSessions() {
        return new ArrayList<>(sessions);
    }

    /**
     * Clears all active {@link StatelessKieSession} instances.
     * <p>
     * Removes all sessions from the list of active sessions.
     * </p>
     */
    public void clearSessions() {
        sessions.clear();
        log.info("Cleared all StatelessKieSessions.");
    }
}