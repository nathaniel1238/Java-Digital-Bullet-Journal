package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record WeekJson(
    @JsonProperty("Title") String title,
    @JsonProperty("Max-Daily-Tasks") int maxTasks,
    @JsonProperty("Max-Daily-Events") int maxEvents,
    @JsonProperty("Theme") String theme,
    @JsonProperty("Days") List<DayJson> days
) {
}
