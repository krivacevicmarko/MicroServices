package com.raf.clientapplication.restclient.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainingDto {

    private int id;

    private String datum;
    private String dan;
    private String pocetak;
    private String kraj;
    private String tip;
    private String trener;
    private int capacity;
    private TrainingTypeDto trainingType;
    public static class TrainingTypeDto {
        private int id;
        private String name;
        private double price;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "TrainingTypeDto{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", price=" + price +
                    '}';
        }

        // Constructors, getters, and setters
    }

    public TrainingDto() {
    }


}
