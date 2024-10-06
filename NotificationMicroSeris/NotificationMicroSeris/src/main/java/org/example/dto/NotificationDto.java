package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class NotificationDto {


   private String email;
   private String text;
   private String tip;
   private String username;
   private LocalDate createdDate;

   public NotificationDto(String email, String text, String tip, String username, LocalDate createdDate) {
      this.email = email;
      this.text = text;
      this.tip = tip;
      this.username = username;
      this.createdDate = createdDate;
   }

   public NotificationDto() {
   }

   @Override
   public String toString() {
      return "NotificationDto{" +
              "email='" + email + '\'' +
              ", text='" + text + '\'' +
              ", tip='" + tip + '\'' +
              ", username='" + username + '\'' +
              ", createdDate=" + createdDate +
              '}';
   }
}
