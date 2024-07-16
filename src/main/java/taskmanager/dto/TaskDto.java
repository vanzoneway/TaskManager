package taskmanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class TaskDto {

    private Long id;
    private String title;
    private String description;

    @JsonProperty(namespace = "start_date")
    private LocalDateTime startDate;

    @JsonProperty(namespace = "end_date")
    private LocalDateTime endDate;

    @JsonProperty(namespace = "employee_id")
    private Long employeeId;


}
