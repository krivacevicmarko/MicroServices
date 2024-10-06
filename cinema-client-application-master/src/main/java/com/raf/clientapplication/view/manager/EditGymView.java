package com.raf.clientapplication.view.manager;

import com.raf.clientapplication.ClientApplication;
import com.raf.clientapplication.restclient.GymServiceRestClient;
import com.raf.clientapplication.restclient.UserServiceRestClient;
import com.raf.clientapplication.restclient.dto.ClientDto;
import com.raf.clientapplication.restclient.dto.GymDto;
import com.raf.clientapplication.restclient.dto.ManagerDto;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class EditGymView extends JFrame {

    private JTextField nameField;
    private JTextField descriptionField;
    private JTextField numberOfPersonalTrainersField;

    private GymServiceRestClient gymServiceRestClient;

    private UserServiceRestClient userServiceRestClient;
    private int clientId;
    private int clientNumRes;
    private String clientUsername;
    private String clientPassword;
    private String clientEmail;
    private String clientDate;
    private String clientName;
    private String clientSurname;
    private boolean clientIsBlocked;


    public EditGymView(int id, String name, String description, int numberOfPersonalTrainers) {

        gymServiceRestClient = new GymServiceRestClient();
        nameField = new JTextField();
        descriptionField = new JTextField();
        numberOfPersonalTrainersField = new JTextField();

        JButton editButton = new JButton("Edit");
        //clientDto =  ClientApplication.getInstance().getLoginView().getClientDto();
        this.setLayout(new GridLayout(10, 2));
        add(new JLabel("Name:"));
        add(nameField);
        nameField.setText(name);
        add(new JLabel("Description:"));
        add(descriptionField);
        descriptionField.setText(description);
        add(new JLabel("NumberOfTrainers"));
        add(numberOfPersonalTrainersField);
        numberOfPersonalTrainersField.setText(String.valueOf(numberOfPersonalTrainers));
        add(editButton);
        editButton.addActionListener((event) -> {
            GymDto gymDto = new GymDto(id,nameField.getText(),descriptionField.getText(),Integer.parseInt(numberOfPersonalTrainersField.getText()));
            try {
                System.out.println("Novi dto: " + gymDto.toString());
                gymServiceRestClient.editGym(gymDto);
                this.dispose();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        this.setSize(new Dimension(400, 400));

    }
}
