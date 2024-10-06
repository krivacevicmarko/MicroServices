package com.raf.clientapplication.restclient.dto;

import com.fasterxml.jackson.annotation.JsonFormat;


import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class GymDto {

	private int id;
	private String name;
	private String shortDescription;
	private int numberOfPersonalTrainers;
	private Training training;

	public GymDto() {
	}

	public GymDto(int id, String name, String shortDescription, int numberOfPersonalTrainers) {
		this.id = id;
		this.name = name;
		this.shortDescription = shortDescription;
		this.numberOfPersonalTrainers = numberOfPersonalTrainers;
	}

	// Constructors, getters, and setters

	public static class Training {
		private int id;

		private String datum;
		private String dan;
		private String pocetak;
		private String kraj;
		private String tip;
		private String trener;
		private int capacity;
		private TrainingTypeDto trainingType;

		// Constructors, getters, and setters

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

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getDatum() {
			return datum;
		}

		public void setDatum(String datum) {
			this.datum = datum;
		}

		public String getDan() {
			return dan;
		}

		public void setDan(String dan) {
			this.dan = dan;
		}

		public String getPocetak() {
			return pocetak;
		}

		public void setPocetak(String pocetak) {
			this.pocetak = pocetak;
		}

		public String getKraj() {
			return kraj;
		}

		public void setKraj(String kraj) {
			this.kraj = kraj;
		}

		public String getTip() {
			return tip;
		}

		public void setTip(String tip) {
			this.tip = tip;
		}

		public String getTrener() {
			return trener;
		}

		public void setTrener(String trener) {
			this.trener = trener;
		}

		public int getCapacity() {
			return capacity;
		}

		public void setCapacity(int capacity) {
			this.capacity = capacity;
		}

		public TrainingTypeDto getTrainingType() {
			return trainingType;
		}

		public void setTrainingType(TrainingTypeDto trainingType) {
			this.trainingType = trainingType;
		}

		@Override
		public String toString() {
			return "Training{" +
					"id=" + id +
					", datum='" + datum + '\'' +
					", dan='" + dan + '\'' +
					", pocetak='" + pocetak + '\'' +
					", kraj='" + kraj + '\'' +
					", tip='" + tip + '\'' +
					", trener='" + trener + '\'' +
					", capacity=" + capacity +
					", trainingType=" + trainingType +
					'}';
		}
	}
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

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public int getNumberOfPersonalTrainers() {
		return numberOfPersonalTrainers;
	}

	public void setNumberOfPersonalTrainers(int numberOfPersonalTrainers) {
		this.numberOfPersonalTrainers = numberOfPersonalTrainers;
	}

	public Training getTraining() {
		return training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}

	@Override
	public String toString() {
		return "GymDto{" +
				"id=" + id +
				", name='" + name + '\'' +
				", shortDescription='" + shortDescription + '\'' +
				", numberOfPersonalTrainers=" + numberOfPersonalTrainers +
				", training=" + training +
				'}';
	}
}
