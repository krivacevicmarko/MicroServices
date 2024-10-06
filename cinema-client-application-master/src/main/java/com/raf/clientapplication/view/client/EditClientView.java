package com.raf.clientapplication.view.client;

import com.raf.clientapplication.ClientApplication;
import com.raf.clientapplication.restclient.UserServiceRestClient;
import com.raf.clientapplication.restclient.dto.ClientDto;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

@Getter
@Setter
public class EditClientView extends JFrame {


    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField emailField;
    private JTextField dateOfBirth;
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




    public EditClientView(int id, String username, String password, String name, String surname, String email, String dateOfBirthStr,int numberOfReservations,String isBlocked){
        userServiceRestClient = new UserServiceRestClient();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        nameField = new JTextField();
        surnameField = new JTextField();
        emailField = new JTextField();
        dateOfBirth = new JTextField();
        clientId = id;
        clientNumRes = numberOfReservations;
        clientUsername = username;
        clientPassword = password;
        clientEmail = email;
        clientName = name;
        clientSurname = surname;
        clientDate = dateOfBirthStr;
        clientIsBlocked = Boolean.parseBoolean(isBlocked);
        JButton editButton = new JButton("Edit");
        //clientDto =  ClientApplication.getInstance().getLoginView().getClientDto();
        this.setLayout(new GridLayout(10, 2));
        add(new JLabel("Username:"));
        add(usernameField);
        usernameField.setText(username);
        add(new JLabel("Password:"));
        add(passwordField);
        passwordField.setText(password);
        add(new JLabel("Name"));
        add(nameField);
        nameField.setText(name);
        add(new JLabel("Surname"));
        add(surnameField);
        surnameField.setText(surname);
        add(new JLabel("Email"));
        add(emailField);
        emailField.setText(email);
        add(new JLabel("DateOfBirth"));
        add(dateOfBirth);
        dateOfBirth.setText(dateOfBirthStr);
        add(new JLabel("NumberOfReservations"));
        add(new JLabel(Integer.toString(numberOfReservations)));
        add(new JLabel("IsBlocked"));
        add(new JLabel(isBlocked));
        add(editButton);
        editButton.addActionListener((event)->{
            ClientDto dto = new ClientDto(id,numberOfReservations,usernameField.getText(),password,emailField.getText(),dateOfBirth.getText(),nameField.getText(),surnameField.getText(),clientIsBlocked);
            try {
                System.out.println("Novi dto: " + dto.toString());
                userServiceRestClient.editUserProfile(dto);
                this.dispose();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        this.setSize(new Dimension(400,400));


    }

}
