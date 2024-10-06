package com.raf.clientapplication.model;

import com.raf.clientapplication.restclient.dto.ClientDto;
import com.raf.clientapplication.restclient.dto.ClientListDto;


import javax.swing.table.DefaultTableModel;

public class ClientTableModel extends DefaultTableModel {

    public ClientTableModel() throws IllegalAccessException, NoSuchMethodException {
        super(new String[]{"ID","Username","Password","Email","DateOfBirth","Name","Surname","NumberOfReservations","IsBlocked"},0);
    }

    private ClientListDto clientListDto = new ClientListDto();

    @Override
    public void addRow(Object[] row) {
        super.addRow(row);
        ClientDto dto = new ClientDto();
        dto.setId((Integer)row[0]);
        clientListDto.getContent().add(dto);
    }
}
