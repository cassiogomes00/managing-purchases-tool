package view;

import javax.swing.JOptionPane;

public class HandleExceptions {
	public void showException(Exception e) {
		JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
	}
}
