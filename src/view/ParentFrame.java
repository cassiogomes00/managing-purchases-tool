package view;

import java.awt.GridLayout;
import javax.swing.JFrame;

public class ParentFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public ParentFrame(int cols) {
		super();

		GridLayout layout = new GridLayout(0, cols, 2, 2);

		this.setTitle("Registro de compras");
		this.setSize(360, 640);
		this.setLayout(layout);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
}
