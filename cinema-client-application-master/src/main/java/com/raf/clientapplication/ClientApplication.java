package com.raf.clientapplication;

import java.awt.*;

import javax.swing.*;

import com.raf.clientapplication.view.admin.AdminClientView;
import com.raf.clientapplication.view.admin.AdminNotificationView;
import com.raf.clientapplication.view.client.EditClientView;
import com.raf.clientapplication.view.client.FilterView;
import com.raf.clientapplication.view.LoginView;
import com.raf.clientapplication.view.client.GymsView;
import com.raf.clientapplication.view.manager.ManagerGymsView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientApplication extends JFrame {

	private String token;
	private LoginView loginView;
	private GymsView gymsView;
	private FilterView filterView;
	private EditClientView editClientView;
	private ManagerGymsView managerGymsView;
	private AdminClientView adminClientView;
	private AdminNotificationView adminNotificationView;


	private ClientApplication() throws IllegalAccessException, NoSuchMethodException {
		this.setTitle("Manager application");
		this.setSize(1000, 800);
		this.setLayout(new BorderLayout());

		loginView = new LoginView();
		this.add(loginView, BorderLayout.NORTH);

		//Client
		JFrame jFrame = new JFrame("Client application");
		jFrame.setSize(new Dimension(1000,1000));
		jFrame.setVisible(true);
		gymsView = new GymsView();
		filterView = new FilterView();
		JPanel panel = new JPanel();
		panel.add(gymsView, BorderLayout.CENTER);
		panel.add(filterView,BorderLayout.SOUTH);
		jFrame.add(panel);

		//Manager
		managerGymsView = new ManagerGymsView();
		this.add(managerGymsView, BorderLayout.CENTER);

		//Admin
		JFrame jFrameAdmin = new JFrame("Admin application");
		jFrameAdmin.setSize(new Dimension(1000,1000));
		jFrameAdmin.setVisible(true);
		adminClientView = new AdminClientView();
		adminNotificationView = new AdminNotificationView();
		JPanel panelAdmin = new JPanel();
		panelAdmin.add(adminClientView, BorderLayout.CENTER);
		panelAdmin.add(adminNotificationView, BorderLayout.SOUTH);
		jFrameAdmin.add(panelAdmin);










		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private static class InstanceHolder {
		private static ClientApplication instance;

		static {
			try {
				instance = new ClientApplication();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
	}


	public static ClientApplication getInstance() {
		return InstanceHolder.instance;
	}
}
