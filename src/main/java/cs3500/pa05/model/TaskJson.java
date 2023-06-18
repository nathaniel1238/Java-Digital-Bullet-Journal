package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TaskJson(
    @JsonProperty("Name") String name,
    @JsonProperty("Description") String description,
    @JsonProperty("Day") String day,
    @JsonProperty("Completion") boolean bool
) {
}
