package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;

public class StartPage extends ParentFrame implements ActionListener {
	private Connection connection;

	private JButton buttonCustomer;
	private JButton buttonProduct;
	private JButton buttonPucharse;
	private JButton buttonClose;

	private static final long serialVersionUID = 1L;

	public StartPage(Connection connection) {
		super(1);

		this.connection = connection;

		this.buttonCustomer = new JButton("Customer");
		this.buttonProduct = new JButton("Product");
		this.buttonPucharse = new JButton("Pucharse");
		this.buttonClose = new JButton("Close");

		this.buttonCustomer.addActionListener(this);
		this.buttonProduct.addActionListener(this);
		this.buttonPucharse.addActionListener(this);
		this.buttonClose.addActionListener(this);

		add(this.buttonCustomer);
		add(this.buttonProduct);
		add(this.buttonPucharse);
		add(this.buttonClose);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent event) {
		String table = event.getActionCommand().toLowerCase();

		if (table.equals("close")) {
			System.exit(1);
		}

		new CrudPage(connection, table);
	}
}
