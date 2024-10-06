package com.raf.clientapplication.view.admin;

import com.raf.clientapplication.ClientApplication;
import com.raf.clientapplication.model.ClientTableModel;
import com.raf.clientapplication.model.GymTableModel;
import com.raf.clientapplication.restclient.GymServiceRestClient;
import com.raf.clientapplication.restclient.ReservationServiceRestClient;
import com.raf.clientapplication.restclient.UserServiceRestClient;
import com.raf.clientapplication.restclient.dto.*;
import com.raf.clientapplication.view.LoginView;
import com.raf.clientapplication.view.manager.EditGymView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AdminClientView extends JPanel {


    private ClientTableModel clientTableModel;
    private JTable clientTable;
    private GymServiceRestClient gymServiceRestClient;
    private ReservationServiceRestClient reservationServiceRestClient;
    private UserServiceRestClient userServiceRestClient;
    private LoginView loginView;
    private ClientDto clientDto;
    private GymDto gymDto;
    private TextField textField;
    private JButton jButton;
    private int clientId;


    private String managerRoomName;

    public AdminClientView() throws IllegalAccessException, NoSuchMethodException {
        super();
        this.setSize(400, 400);

        clientTableModel = new ClientTableModel();
        gymServiceRestClient = new GymServiceRestClient();
        reservationServiceRestClient = new ReservationServiceRestClient();
        userServiceRestClient = new UserServiceRestClient();
        clientTable = new JTable(clientTableModel);
        jButton = new JButton();


        this.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(clientTable);
        this.add(scrollPane, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        JButton blockButton = new JButton("Block user");
        JButton unblockButton = new JButton("Unblock user");
        panel.add(blockButton);
        panel.add(unblockButton);
        this.add(panel, BorderLayout.CENTER);


        blockButton.addActionListener((event)->{

            try {
                clientId = clientTable.getSelectedRow() + 1;
                userServiceRestClient.blockClient(clientId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        });

        unblockButton.addActionListener((event)->{

            try {
                clientId = clientTable.getSelectedRow() + 1;
                userServiceRestClient.unblockClient(clientId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        });


    }

    public void init() throws IOException {
        this.setVisible(true);

        ClientListDto clientListDto = userServiceRestClient.getClients();
        System.out.println(clientListDto.toString());
        clientListDto.getContent().forEach(clientDto -> {
            System.out.println("REZAAAAAA " + clientDto.toString());
            clientTableModel.addRow(new Object[]{clientDto.getId(), clientDto.getUsername(), clientDto.getPassword(), clientDto.getEmail(), clientDto.getDateOfBirth(), clientDto.getName(), clientDto.getSurname(), clientDto.getNumberOfReservations(), clientDto.isBlocked()});

        });
    }
}
