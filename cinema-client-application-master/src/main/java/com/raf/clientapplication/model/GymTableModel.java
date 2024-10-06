package com.raf.clientapplication.model;

import javax.swing.table.DefaultTableModel;

import com.raf.clientapplication.restclient.dto.GymDto;
import com.raf.clientapplication.restclient.dto.GymListDto;

public class GymTableModel extends DefaultTableModel {


	public GymTableModel() throws IllegalAccessException, NoSuchMethodException {
		super(new String[]{"ID","Name", "ShortDescription","NumberOfPersonalTrainers","Date","Day","Start","Finish","Type","Trainer","Capacity","TypeName","Price"}, 0);
	}

	private GymListDto gymListDto = new GymListDto();

	@Override
	public void addRow(Object[] row) {
		super.addRow(row);
		GymDto dto = new GymDto();
		dto.setId((Integer)row[0]);
		gymListDto.getContent().add(dto);
	}

	public GymListDto getGymListDto() {
		return gymListDto;
	}

	public void setGymListDto(GymListDto gymListDto) {
		this.gymListDto = gymListDto;
	}
}
