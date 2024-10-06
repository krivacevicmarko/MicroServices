package com.raf.clientapplication.view.client;

import com.raf.clientapplication.model.TrainingTableModel;
import com.raf.clientapplication.restclient.GymServiceRestClient;
import com.raf.clientapplication.restclient.ReservationServiceRestClient;
import com.raf.clientapplication.restclient.UserServiceRestClient;
import com.raf.clientapplication.restclient.dto.TrainingListDto;
import com.raf.clientapplication.view.LoginView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class FilterView extends JPanel {
    private TrainingTableModel trainingTableModel;
    private JTable trainingTable;
    private GymServiceRestClient gymServiceRestClient;
    private ReservationServiceRestClient reservationServiceRestClient;
    private UserServiceRestClient userServiceRestClient;
    private LoginView loginView;
    private TextField textField;
    private int userId;
    private int gymId;

    private JButton jButton;

    public FilterView() throws IllegalAccessException, NoSuchMethodException {
        super();
        this.setSize(400, 400);

        trainingTableModel = new TrainingTableModel();
        gymServiceRestClient = new GymServiceRestClient();
        reservationServiceRestClient = new ReservationServiceRestClient();
        userServiceRestClient = new UserServiceRestClient();
        trainingTable = new JTable(trainingTableModel);
        textField = new TextField();
        textField.setPreferredSize(new Dimension(100,20));

        this.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(trainingTable);
        this.add(scrollPane, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        JButton buttonFilter = new JButton("Filter");
        panel.add(buttonFilter);
        String[] filterOptions = {"Filter by Day", "Filter by Trainer", "Filter by Type"};
        JComboBox<String> filterComboBox = new JComboBox<>(filterOptions);
        panel.add(filterComboBox);
        panel.add(textField);

        this.add(panel, BorderLayout.CENTER);




        buttonFilter.addActionListener((event)->{
            TrainingListDto trainingListDto = null;
            try {
                trainingTableModel.setRowCount(0);
                if(textField.getText().isEmpty()){
                    trainingListDto = gymServiceRestClient.getTrainings();
                }else if(filterComboBox.getSelectedIndex() == 0) {
                    trainingListDto = gymServiceRestClient.getFilterDanList(textField.getText());
                }else if(filterComboBox.getSelectedIndex() == 1){
                    trainingListDto = gymServiceRestClient.getFilterTrenerList(textField.getText());
                }else if(filterComboBox.getSelectedIndex() == 2){
                    trainingListDto = gymServiceRestClient.getFilterTipList(textField.getText());
                }
                trainingListDto.getContent().forEach(gymDto -> {
                    System.out.println("REZAAAAAA " + gymDto.toString());

                    trainingTableModel.addRow(new Object[]{gymDto.getId() , gymDto.getDatum(), gymDto.getDan(), gymDto.getPocetak(), gymDto.getKraj(), gymDto.getTip(), gymDto.getTrener(), gymDto.getCapacity(), gymDto.getTrainingType().getName(), gymDto.getTrainingType().getPrice()});
                });

               trainingTableModel.fireTableDataChanged();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void init() throws IOException {
        this.setVisible(true);

        TrainingListDto trainingListDto = gymServiceRestClient.getTrainings();
        System.out.println(trainingListDto.toString());
        trainingListDto.getContent().forEach(gymDto -> {
            System.out.println("REZAAAAAA " + gymDto.toString());
            trainingTableModel.addRow(new Object[]{gymDto.getId(),gymDto.getDatum(),gymDto.getDan(),gymDto.getPocetak(),gymDto.getKraj(),gymDto.getTip(),gymDto.getTrener(),gymDto.getCapacity(),gymDto.getTrainingType().getName(),gymDto.getTrainingType().getPrice()});
        });

    }

}
