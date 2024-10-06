package com.raf.clientapplication.view.client;

import com.raf.clientapplication.restclient.UserServiceRestClient;
import com.raf.clientapplication.restclient.dto.ClientDto;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class RegisterView extends JFrame {

    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField emailField;
    private JTextField dateOfBirth;
    private JTextField numberOfReservationsField;
    private JTextField blocked;
    private UserServiceRestClient userServiceRestClient;

    public RegisterView(){
        userServiceRestClient = new UserServiceRestClient();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        nameField = new JTextField();
        surnameField = new JTextField();
        emailField = new JTextField();
        dateOfBirth = new JTextField();
        numberOfReservationsField = new JTextField();
        blocked = new JTextField();
        JButton editButton = new JButton("Register");
        //clientDto =  ClientApplication.getInstance().getLoginView().getClientDto();
        this.setLayout(new GridLayout(10, 2));
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel("Name"));
        add(nameField);
        add(new JLabel("Surname"));
        add(surnameField);
        add(new JLabel("Email"));
        add(emailField);
        add(new JLabel("DateOfBirth"));
        add(dateOfBirth);
        add(new JLabel("NumberOfReservations"));
        add(numberOfReservationsField);
        numberOfReservationsField.setText("0");
        add(new JLabel("IsBlocked"));
        add(blocked);
        blocked.setText("0");

        add(editButton);
        editButton.addActionListener((event)->{
            ClientDto dto = new ClientDto(1,Integer.parseInt(numberOfReservationsField.getText()),usernameField.getText(),passwordField.getText(),emailField.getText(),dateOfBirth.getText(),nameField.getText(),surnameField.getText(),Boolean.parseBoolean(blocked.getText()));
            try {
                System.out.println("Novi dto: " + dto.toString());
                userServiceRestClient.register(dto);
                this.dispose();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        this.setSize(new Dimension(400,400));


    }

}
