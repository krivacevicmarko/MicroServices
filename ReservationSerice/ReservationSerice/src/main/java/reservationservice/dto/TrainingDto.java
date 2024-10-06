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
public class TrainingDto {
    private Integer id;
    private LocalDate datum;
    private String dan;
    private LocalTime pocetak;
    private LocalTime kraj;
    @JsonProperty("trainingType")
    private TrainingTypeDto trainingTypeDto;
    private Tip tip;
    private String trener;
    private int capacity;

    @Override
    public String toString() {
        return "TrainingDto{" +
                "id=" + id +
                ", datum=" + datum +
                ", dan='" + dan + '\'' +
                ", pocetak=" + pocetak +
                ", kraj=" + kraj +
                ", trainingTypeDto=" + trainingTypeDto +
                ", tip=" + tip +
                ", trener='" + trener + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
