package com.raf.clientapplication.view.manager;

import com.raf.clientapplication.ClientApplication;
import com.raf.clientapplication.model.GymTableModel;
import com.raf.clientapplication.restclient.GymServiceRestClient;
import com.raf.clientapplication.restclient.ReservationServiceRestClient;
import com.raf.clientapplication.restclient.UserServiceRestClient;
import com.raf.clientapplication.restclient.dto.ClientDto;
import com.raf.clientapplication.restclient.dto.GymDto;
import com.raf.clientapplication.restclient.dto.GymListDto;
import com.raf.clientapplication.restclient.dto.ManagerDto;
import com.raf.clientapplication.view.LoginView;
import com.raf.clientapplication.view.client.EditClientView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ManagerGymsView extends JPanel{

    private GymTableModel gymTableModel;
    private JTable gymTable;
    private GymServiceRestClient gymServiceRestClient;
    private ReservationServiceRestClient reservationServiceRestClient;
    private UserServiceRestClient userServiceRestClient;
    private LoginView loginView;
    private ClientDto clientDto;
    private GymDto gymDto;
    private TextField textField;
    private JButton jButton;
    private int managerId;


    private String managerRoomName;

    public ManagerGymsView() throws IllegalAccessException, NoSuchMethodException {
        super();
        this.setSize(400, 400);

        gymTableModel = new GymTableModel();
        gymServiceRestClient = new GymServiceRestClient();
        reservationServiceRestClient = new ReservationServiceRestClient();
        userServiceRestClient = new UserServiceRestClient();
        gymTable = new JTable(gymTableModel);
        jButton = new JButton();

        this.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(gymTable);
        this.add(scrollPane, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        JButton buttonEdit = new JButton("Edit gym parameters");
        panel.add(buttonEdit);
        this.add(panel, BorderLayout.CENTER);


        buttonEdit.addActionListener((event)->{
            ManagerDto managerDto = null;
            try {
                managerId = ClientApplication.getInstance().getLoginView().getManagerId();
                gymDto = gymServiceRestClient.fetchGym(gymTable.getSelectedRow());
                managerDto= userServiceRestClient.fetchManager(managerId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(managerDto.getRoomName().equalsIgnoreCase(gymDto.getName())) {
                JFrame jFrame = new EditGymView(gymDto.getId(), gymDto.getName(), gymDto.getShortDescription(), gymDto.getNumberOfPersonalTrainers());
                jFrame.setSize(new Dimension(400, 400));
                jFrame.setVisible(true);
            }else System.out.println("Ne moze");

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
}
