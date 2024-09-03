package com.example.drools.service;

import com.example.drools.droolsUtils.KieSessionManager;
import com.example.drools.web.KundenDTO;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Service for performing searches using Drools sessions.
 * <p>
 * This service uses the {@link KieSessionManager} to retrieve active Drools sessions and execute rules based on customer data.
 * </p>
 */
@Service
public class SearchService {

    /**
     * Manages the Drools sessions used for executing rules.
     * <p>
     * This field is used to interact with the Drools engine and obtain the necessary sessions to perform searches.
     * </p>
     */
    private final KieSessionManager kieSessionManager;

    /**
     * Creates an instance of {@link SearchService}.
     * <p>
     * The constructor initializes the service with the provided {@link KieSessionManager}.
     * </p>
     *
     * @param kieSessionManager the manager of Drools sessions. Must not be {@code null}.
     */
    @SuppressWarnings("unused")
    public SearchService(KieSessionManager kieSessionManager) {
        this.kieSessionManager = kieSessionManager;
    }

    /**
     * Executes the search operation using the provided customer data.
     * <p>
     * This method iterates over all active Drools sessions, sets the global variable for each session,
     * and executes the rules with the provided {@link KundenDTO} data.
     * </p>
     *
     * @param kundenDTO The customer data used for the search. Must not be {@code null}.
     * @return A set of UUIDs representing the results of the search.
     * @throws IllegalArgumentException if {@code kundenDTO} is {@code null}.
     */
    public Set<UUID> search(KundenDTO kundenDTO) {
        if (kundenDTO == null) {
            throw new IllegalArgumentException("KundenDTO must not be null");
        }

        var activeSessions = kieSessionManager.getSessions();

        Set<UUID> searchResponse = new HashSet<>();

        // Iterate over each Drools session
        activeSessions.forEach(kieSession -> {
            // Set the global variable 'searchResponse' for the current session
            kieSession.setGlobal("searchResponse", searchResponse);
            // Execute rules in the session using the provided customer data
            kieSession.execute(kundenDTO);
        });

        return searchResponse;
    }
}