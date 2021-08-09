package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;

public class CrudPage extends ParentFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	Connection connection;
	String table;
	JButton buttonCreate;
	JButton buttonRetrieve;
	JButton buttonBack;

	public CrudPage(Connection connection, String table) {
		super(1);

		this.connection = connection;
		this.table = table;

		this.buttonCreate = new JButton("Create");
		this.buttonRetrieve = new JButton("Retrieve");
		this.buttonBack = new JButton("Back");

		this.buttonCreate.addActionListener(this);
		this.buttonRetrieve.addActionListener(this);
		this.buttonBack.addActionListener(this);

		add(this.buttonCreate);
		add(this.buttonRetrieve);
		add(this.buttonBack);
	}

	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand().toLowerCase();

		switch (command) {
		case "create":
			new EditPage(this.connection, this.table, command, null);
			break;

		case "retrieve":
			try {
				new RetrievePage(connection, this.table);
			} catch (Exception e) {
				new HandleExceptions().showException(e);
			}

			break;
		case "back":
			this.setVisible(false);
			break;
		}
	}
}
