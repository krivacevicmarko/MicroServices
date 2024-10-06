package com.raf.clientapplication.model;


import com.raf.clientapplication.restclient.dto.TrainingDto;
import com.raf.clientapplication.restclient.dto.TrainingListDto;

import javax.swing.table.DefaultTableModel;

public class TrainingTableModel extends DefaultTableModel {


    public TrainingTableModel() throws IllegalAccessException, NoSuchMethodException {
        super(new String[]{"ID","Date","Day","Start","Finish","Type","Trainer","Capacity","TypeName","Price"}, 0);
    }

    private TrainingListDto trainingListDto = new TrainingListDto();

    @Override
    public void addRow(Object[] row) {
        super.addRow(row);
        TrainingDto dto = new TrainingDto();
        dto.setId((Integer)row[0]);
        trainingListDto.getContent().add(dto);
    }

    public TrainingListDto getTrainingListDto() {
        return trainingListDto;
    }

    public void setTrainingListDto(TrainingListDto trainingListDto) {
        this.trainingListDto = trainingListDto;
    }
}
