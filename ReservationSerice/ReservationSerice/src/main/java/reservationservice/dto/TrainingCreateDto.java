package reservationservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import reservationservice.domain.Tip;
import reservationservice.domain.TrainingType;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class TrainingCreateDto {
    private LocalDate datum;
    private String dan;
    private LocalTime pocetak;
    private LocalTime kraj;
    @JsonProperty("trainingType")
    private Integer trainingTypeId;
    private Tip tip;
    private String trener;
    private int capacity;
}
