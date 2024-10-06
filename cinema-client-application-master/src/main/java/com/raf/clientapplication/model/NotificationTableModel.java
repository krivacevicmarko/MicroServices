package com.raf.clientapplication.model;

import com.raf.clientapplication.restclient.dto.NotificationDto;
import com.raf.clientapplication.restclient.dto.NotificationListDto;

import javax.swing.table.DefaultTableModel;

public class NotificationTableModel extends DefaultTableModel {

    public NotificationTableModel() throws IllegalAccessException, NoSuchMethodException {
        super(new String[]{"Email","Type","Text","Username"},0);
    }

    private NotificationListDto notificationListDto = new NotificationListDto();

    @Override
    public void addRow(Object[] row) {
        super.addRow(row);
        NotificationDto dto = new NotificationDto();
        notificationListDto.getContent().add(dto);
    }
}
