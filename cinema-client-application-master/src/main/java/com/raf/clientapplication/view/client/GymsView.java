package com.raf.clientapplication.view.client;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import com.raf.clientapplication.ClientApplication;
import com.raf.clientapplication.model.GymTableModel;
import com.raf.clientapplication.restclient.GymServiceRestClient;
import com.raf.clientapplication.restclient.ReservationServiceRestClient;
import com.raf.clientapplication.restclient.UserServiceRestClient;
import com.raf.clientapplication.restclient.dto.ClientDto;
import com.raf.clientapplication.restclient.dto.GymListDto;
import com.raf.clientapplication.view.LoginView;

public class GymsView extends JPanel {

	private GymTableModel gymTableModel;
	private JTable gymTable;

	private GymServiceRestClient gymServiceRestClient;
	private ReservationServiceRestClient reservationServiceRestClient;
	private UserServiceRestClient userServiceRestClient;
	private LoginView loginView;
	private ClientDto clientDto;
	private TextField textField;
	private int userId;
	private int gymId;

	private JButton jButton;

	public GymsView() throws IllegalAccessException, NoSuchMethodException {
		super();
		this.setSize(400, 400);

		gymTableModel = new GymTableModel();
		gymServiceRestClient = new GymServiceRestClient();
		reservationServiceRestClient = new ReservationServiceRestClient();
		userServiceRestClient = new UserServiceRestClient();
		gymTable = new JTable(gymTableModel);

		this.setLayout(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane(gymTable);
		this.add(scrollPane, BorderLayout.NORTH);

		jButton = new JButton("Create Order");
		JPanel panel = new JPanel();
		JButton buttonSort = new JButton("Sort");
		JButton buttonEdit = new JButton("Edit user profile");
		panel.add(buttonSort);
		panel.add(jButton);
		panel.add(buttonEdit);
		this.add(panel, BorderLayout.CENTER);

		jButton.addActionListener((event) -> {
			userId = ClientApplication.getInstance().getLoginView().getId();
			gymId = gymTableModel.getGymListDto().getContent().get(gymTable.getSelectedRow()).getId();
			System.out.println("UserID: " + userId);
			System.out.println("GymID: " + gymId);
			try {
				reservationServiceRestClient.addReservation(userId, gymId);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		});
		buttonSort.addActionListener((event) -> {
			try {
				gymTableModel.setRowCount(0);
				GymListDto gymListDto = gymServiceRestClient.getSortedList();

				gymListDto.getContent().forEach(gymDto -> {
					System.out.println("REZAAAAAA " + gymDto.toString());

					gymTableModel.addRow(new Object[]{gymDto.getId(), gymDto.getName(), gymDto.getShortDescription(), gymDto.getNumberOfPersonalTrainers(), gymDto.getTraining().getDatum(), gymDto.getTraining().getDan(), gymDto.getTraining().getPocetak(), gymDto.getTraining().getKraj(), gymDto.getTraining().getTip(), gymDto.getTraining().getTrener(), gymDto.getTraining().getCapacity(), gymDto.getTraining().getTrainingType().getName(), gymDto.getTraining().getTrainingType().getPrice()});
				});

				gymTableModel.fireTableDataChanged();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});

		buttonEdit.addActionListener((event)->{
			try {
				userId = ClientApplication.getInstance().getLoginView().getId();
				clientDto = userServiceRestClient.fetchUser(userId);
				EditClientView jFrame = new EditClientView(clientDto.getId(),clientDto.getUsername(),clientDto.getPassword(),clientDto.getName(),clientDto.getSurname(),clientDto.getEmail(),clientDto.getDateOfBirth(),clientDto.getNumberOfReservations(),Boolean.toString(clientDto.isBlocked()));
				jFrame.setSize(new Dimension(400,400));
				jFrame.setPreferredSize(new Dimension(400,400));
				jFrame.setVisible(true);
				ClientApplication.getInstance().getEditClientView();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

	}

	public void init() throws IOException {
		this.setVisible(true);

		GymListDto gymListDto = gymServiceRestClient.getGyms();
		System.out.println(gymListDto.toString());
		gymListDto.getContent().forEach(gymDto -> {
			System.out.println("REZAAAAAA " + gymDto.toString());
			gymTableModel.addRow(new Object[]{gymDto.getId(),gymDto.getName(),gymDto.getShortDescription(),gymDto.getNumberOfPersonalTrainers(),gymDto.getTraining().getDatum(),gymDto.getTraining().getDan(),gymDto.getTraining().getPocetak(),gymDto.getTraining().getKraj(),gymDto.getTraining().getTip(),gymDto.getTraining().getTrener(),gymDto.getTraining().getCapacity(),gymDto.getTraining().getTrainingType().getName(),gymDto.getTraining().getTrainingType().getPrice()});
		});

	}

	public GymTableModel getMovieTableModel() {
		return gymTableModel;
	}

	public JTable getGymTable() {
		return gymTable;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
