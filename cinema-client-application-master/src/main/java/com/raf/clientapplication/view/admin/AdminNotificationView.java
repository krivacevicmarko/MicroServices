package com.raf.clientapplication.view.admin;

import com.raf.clientapplication.ClientApplication;
import com.raf.clientapplication.model.ClientTableModel;
import com.raf.clientapplication.model.NotificationTableModel;
import com.raf.clientapplication.restclient.GymServiceRestClient;
import com.raf.clientapplication.restclient.NotificationServiceRestClient;
import com.raf.clientapplication.restclient.ReservationServiceRestClient;
import com.raf.clientapplication.restclient.UserServiceRestClient;
import com.raf.clientapplication.restclient.dto.*;
import com.raf.clientapplication.view.LoginView;
import com.raf.clientapplication.view.manager.EditGymView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AdminNotificationView extends JPanel {


    private NotificationTableModel notificationTableModel;
    private JTable notificationTable;
    private GymServiceRestClient gymServiceRestClient;


    private NotificationServiceRestClient notificationServiceRestClient;
    private LoginView loginView;
    private ClientDto clientDto;
    private GymDto gymDto;
    private TextField textField;
    private JButton jButton;
    private int managerId;


    private String managerRoomName;

    public AdminNotificationView() throws IllegalAccessException, NoSuchMethodException {
        super();
        this.setSize(400, 400);

        notificationServiceRestClient = new NotificationServiceRestClient();
        notificationTableModel = new NotificationTableModel();
        gymServiceRestClient = new GymServiceRestClient();
        notificationTable = new JTable(notificationTableModel);
        jButton = new JButton();
        textField = new TextField();
        textField.setPreferredSize(new Dimension(100,20));

        this.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(notificationTable);
        this.add(scrollPane, BorderLayout.NORTH);


        JPanel panel = new JPanel();
        JButton buttonFilter = new JButton("Filter");
        panel.add(buttonFilter);
        panel.add(textField);

        this.add(panel, BorderLayout.CENTER);

        buttonFilter.addActionListener((event)->{
            NotificationListDto  notificationListDto = null;
            try {
                notificationTableModel.setRowCount(0);
                if(textField.getText().isEmpty()){
                        notificationListDto = notificationServiceRestClient.getNotifications();
                }else if(!textField.getText().isEmpty()) {
                    notificationListDto = notificationServiceRestClient.getFilterTip(textField.getText());
                }


                notificationListDto.getContent().forEach(notificationDto -> {
                    System.out.println("REZAAAAAA " + notificationDto.toString());

                    notificationTableModel.addRow(new Object[]{notificationDto.getEmail(),notificationDto.getTip(),notificationDto.getText(),notificationDto.getUsername()});
                });

                notificationTableModel.fireTableDataChanged();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    public void init() throws IOException {
        this.setVisible(true);

        NotificationListDto notificationListDto = notificationServiceRestClient.getNotifications();
        System.out.println(notificationListDto.toString());
        notificationListDto.getContent().forEach(notificationDto -> {
            System.out.println("REZAAAAAA " + notificationDto.toString());
            notificationTableModel.addRow(new Object[]{notificationDto.getEmail(),notificationDto.getTip(),notificationDto.getText(),notificationDto.getUsername()});

        });
    }
}
