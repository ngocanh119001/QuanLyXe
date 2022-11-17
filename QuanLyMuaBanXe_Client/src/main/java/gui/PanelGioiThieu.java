/**
	 * @param Nguyễn Minh Quang 19487761
	 **/

package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class PanelGioiThieu extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelGioiThieu() {
		setBounds(1, 0, 1087, 706);
		setLayout(null);
		setVisible(true);

		JLabel lblNewLabel = new JLabel("GI\u1EDAI THI\u1EC6U \u1EE8NG D\u1EE4NG");
		lblNewLabel.setForeground(new Color(255, 99, 71));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 1067, 63);
		add(lblNewLabel);
		
		JTextArea txtaGioiThieu = new JTextArea();
		txtaGioiThieu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtaGioiThieu.setText("");
		txtaGioiThieu.setEditable(false);
		txtaGioiThieu.setBounds(10, 71, 1068, 624);
		add(txtaGioiThieu);

	}
}