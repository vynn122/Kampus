package com.example.kampus.common.config;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*
 * Global Jackson configuration.
 *
 * Handles:
 * - Java <-> JSON conversion
 * - Date/time formatting
 * - Null field exclusion
 * - Unknown JSON fields
 */
@Configuration
public class JacksonConfig {

    /*
     * Register ObjectMapper as a Spring Bean
     *
     * Spring will automatically use this
     * ObjectMapper globally across the app.
     */

    @Bean
    public ObjectMapper objectMapper() {


        /*
         * Main Jackson class used for:
         * - Serialization (Java -> JSON)
         * - Deserialization (JSON -> Java)
         */
        ObjectMapper mapper = new ObjectMapper();


        /*
         * Support Java 8 Date/Time API
         *
         * Enables proper handling for:
         * - LocalDate
         * - LocalDateTime
         * - Instant
         * - ZonedDateTime
         */

        mapper.registerModule(new JavaTimeModule());


        /*
         * Disable timestamp array formatting
         *
         * Without this:
         *
         * "createdAt": [2026,5,11,18,30]
         *
         * With this:
         *
         * "createdAt": "2026-05-11T18:30:00"
         *
         * Much cleaner for:
         * - Android
         * - Flutter
         * - React
         * - APIs
         */
        mapper.disable(
                SerializationFeature.WRITE_DATES_AS_TIMESTAMPS
        );



        /*
         * Ignore unknown JSON properties
         *
         * Prevents errors if frontend sends
         * extra fields not موجود in DTO.
         *
         * Example:
         *
         * Frontend sends:
         * {
         *   "username": "thea",
         *   "extraField": "abc"
         * }
         *
         * App ignores extraField safely.
         */

        mapper.disable(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
        );
        /*
         * Exclude null fields from JSON responses
         *
         * Example:
         *
         * Without:
         * {
         *   "nickname": null
         * }
         *
         * With NON_NULL:
         * {}
         *
         * Cleaner API responses.
         */
        mapper.setDefaultPropertyInclusion(
                JsonInclude.Include.NON_NULL
        );
        /*
         * Ignore unknown JSON fields during deserialization.
         *
         * Prevents errors if request JSON contains
         * extra fields not defined in the DTO.
         *
         * Example:
         *
         * JSON:
         * {
         *   "username": "theavin",
         *   "age": 20
         * }
         *
         * DTO:
         * private String username;
         *
         * "age" will be ignored safely.
         */
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }
}
