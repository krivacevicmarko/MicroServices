package com.raf.clientapplication.view;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raf.clientapplication.ClientApplication;
import com.raf.clientapplication.restclient.UserServiceRestClient;
import com.raf.clientapplication.restclient.dto.ClientDto;
import com.raf.clientapplication.view.client.RegisterView;
import com.raf.clientapplication.view.manager.ManagerRegisterView;

public class LoginView extends JPanel {

	private JPanel inputPanel;

	private JLabel emailLabel;
	private JLabel passwordLabel;
	private JTextField emailInput;
	private JPasswordField passwordInput;

	private JButton loginButton;
	private JButton loginButtonManager;
	private JButton adminButton;
	private JButton registerButton;
	private JButton registerButtonManager;
	private ClientDto clientDto;
	private int id;
	private int managerId;
	private UserServiceRestClient userServiceRestClient = new UserServiceRestClient();

	private ObjectMapper objectMapper = new ObjectMapper();

	public LoginView()  {

		super();
		this.setSize(400, 400);

		this.setLayout(new BorderLayout());

		initInputPanel();
		adminButton = new JButton("Admin");
		loginButton = new JButton("Login");
		loginButtonManager = new JButton("Manager Login");
		JPanel loginPanel = new JPanel();
		loginPanel.add(loginButton);
		loginPanel.add(loginButtonManager);
		this.add(adminButton,BorderLayout.WEST);
		this.add(loginPanel, BorderLayout.SOUTH);
		loginButton.addActionListener((event) -> {

			try {
				String token = userServiceRestClient
					.login(emailInput.getText(), String.valueOf(passwordInput.getPassword()));
				this.setVisible(false);
				ClientApplication.getInstance().setToken(token);
				System.out.println(token);
				ClientApplication.getInstance().getGymsView().init();
				ClientApplication.getInstance().getFilterView().init();

				System.out.println(userServiceRestClient.fetchID(String.valueOf(emailInput.getText())));
				id = userServiceRestClient.fetchID(String.valueOf(emailInput.getText()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		loginButtonManager.addActionListener((event)->{
			try {
				String token = userServiceRestClient
						.loginManager(emailInput.getText(), String.valueOf(passwordInput.getPassword()));
				this.setVisible(false);
				ClientApplication.getInstance().setToken(token);
				System.out.println(token);
				ClientApplication.getInstance().getManagerGymsView().init();


				managerId = userServiceRestClient.fetchManagerId(emailInput.getText());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		registerButton = new JButton("Register");
		registerButtonManager = new JButton("Manager Register");
		JPanel registerPanel = new JPanel();
		registerPanel.add(registerButton);
		registerPanel.add(registerButtonManager);
		this.add(registerPanel,BorderLayout.EAST);
		registerButton.addActionListener((event)->{
			JFrame jFrame = new RegisterView();
			jFrame.setSize(400,400);
			jFrame.setVisible(true);
		});

		registerButtonManager.addActionListener((event)->{
			JFrame jFrame = new ManagerRegisterView();
			jFrame.setSize(400,400);
			jFrame.setVisible(true);
		});

		adminButton.addActionListener((event)->{
			try {
				ClientApplication.getInstance().getAdminClientView().init();
				ClientApplication.getInstance().getAdminNotificationView().init();
			}catch (Exception e){
				e.printStackTrace();
			}
		});



	}

	private void initInputPanel() {

		inputPanel = new JPanel();

		emailLabel = new JLabel("Email: ");
		passwordLabel = new JLabel("Password: ");

		emailInput = new JTextField(20);
		passwordInput = new JPasswordField(20);

		inputPanel.add(emailLabel);
		inputPanel.add(emailInput);

		inputPanel.add(passwordLabel);
		inputPanel.add(passwordInput);

		this.add(inputPanel, BorderLayout.CENTER);
	}

	public int getId() {
		return id;
	}

	public ClientDto getClientDto() {
		return clientDto;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
}
