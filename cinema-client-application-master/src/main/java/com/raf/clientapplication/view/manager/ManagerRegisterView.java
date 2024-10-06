package com.raf.clientapplication.view.manager;

import com.raf.clientapplication.restclient.UserServiceRestClient;
import com.raf.clientapplication.restclient.dto.ClientDto;
import com.raf.clientapplication.restclient.dto.ManagerDto;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ManagerRegisterView extends JFrame {

    private JTextField roomNameField;
    private JTextField usernameField;
    private JTextField employmentDateField;
    private JTextField passwordField;
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField emailField;
    private JTextField dateOfBirth;
    private UserServiceRestClient userServiceRestClient;

    private ManagerDto managerDto = null;
    private String managerRoom;

    public ManagerRegisterView() {
        userServiceRestClient = new UserServiceRestClient();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        nameField = new JTextField();
        surnameField = new JTextField();
        emailField = new JTextField();
        dateOfBirth = new JTextField();
        roomNameField = new JTextField();
        employmentDateField = new JTextField();

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
        add(new JLabel("RoomName"));
        add(roomNameField);
        add(new JLabel("EmploymentDate"));
        add(employmentDateField);


        add(editButton);
        managerRoom = roomNameField.getText();
        editButton.addActionListener((event) -> {
             managerDto = new ManagerDto(1,roomNameField.getText(),usernameField.getText(),passwordField.getText(),emailField.getText(),dateOfBirth.getText(),employmentDateField.getText(),nameField.getText(),surnameField.getText());
            try {
                System.out.println("Novi dto: " + managerDto.toString());
                userServiceRestClient.registerManager(managerDto);
                this.dispose();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        this.setSize(new Dimension(400, 400));

    }

   public String managerRoom(){
        return managerRoom;
   }

    public String getRoomNameField() {
        return roomNameField.getText();
    }
}
