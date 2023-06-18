package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record DayJson(
    @JsonProperty("Day") String day,
    @JsonProperty("Events") List<EventJson> events,
    @JsonProperty("Tasks") List<TaskJson> tasks
) {
}
