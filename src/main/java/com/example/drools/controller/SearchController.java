package com.example.drools.controller;

import com.example.drools.service.SearchService;
import com.example.drools.web.KundenDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.Set;
import java.util.UUID;

/**
 * REST controller for handling search requests.
 * <p>
 * This controller exposes a POST endpoint to perform searches based on customer data.
 * It uses the {@link SearchService} to process the search logic and return the results.
 * </p>
 */
@RestController
@Validated
public class SearchController {

    /**
     * Service for performing search operations.
     * <p>
     * This field is used by the controller to interact with the search service and obtain search results.
     * </p>
     */
    private final SearchService searchService;

    /**
     * Creates an instance of {@link SearchController}.
     * <p>
     * The constructor initializes the controller with the provided {@link SearchService}.
     * </p>
     *
     * @param searchService the service used for performing search operations. Must not be {@code null}.
     */
    @SuppressWarnings("unused")
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    /**
     * Performs a search based on the provided customer data.
     * <p>
     * The method accepts a {@link KundenDTO} object, validates it, and uses the {@link SearchService}
     * to perform the search operation. It returns a set of UUIDs representing the results of the search.
     * </p>
     *
     * @param kundenDTO The customer data used for the search. Must not be {@code null} or invalid.
     * @return A set of UUIDs representing the results of the search.
     */
    @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Set<UUID> search(
            @RequestBody @Valid KundenDTO kundenDTO) {
        return searchService.search(kundenDTO);
    }

}