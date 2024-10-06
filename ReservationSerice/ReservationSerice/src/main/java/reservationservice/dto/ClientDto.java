package reservationservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClientDto {

    private Integer id;
    private String email;
    private LocalDate dateOfBirth;
    private boolean isBlocked;
    private String name;
    private String surname;
    private int number_of_reservations;
    private String password;
    private String username;

    @Override
    public String toString() {
        return "ClientDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", isBlocked=" + isBlocked +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", number_of_reservations=" + number_of_reservations +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
