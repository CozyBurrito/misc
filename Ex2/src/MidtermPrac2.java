import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class MidtermPrac2 {

	private JFrame frame;
	private JTextField first_name;
	private JTextField last_name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MidtermPrac2 window = new MidtermPrac2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MidtermPrac2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Exam Gui");
		frame.setBounds(100, 100, 624, 318);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel Names = new JPanel();
		Names.setBorder(new TitledBorder(null, "Names", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(Names);
		Names.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		Names.add(scrollPane, BorderLayout.CENTER);
		
		JTextArea names = new JTextArea();
		scrollPane.setViewportView(names);
		
		JPanel Actions = new JPanel();
		Actions.setBorder(new TitledBorder(null, "Actions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(Actions);
		GridBagLayout gbl_Actions = new GridBagLayout();
		gbl_Actions.columnWidths = new int[]{0, 0, 0};
		gbl_Actions.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_Actions.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_Actions.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		Actions.setLayout(gbl_Actions);
		
		JLabel lblFirstName = new JLabel("First Name:");
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.anchor = GridBagConstraints.EAST;
		gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstName.gridx = 0;
		gbc_lblFirstName.gridy = 0;
		Actions.add(lblFirstName, gbc_lblFirstName);
		
		first_name = new JTextField();
		GridBagConstraints gbc_first_name = new GridBagConstraints();
		gbc_first_name.insets = new Insets(0, 0, 5, 0);
		gbc_first_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_first_name.gridx = 1;
		gbc_first_name.gridy = 0;
		Actions.add(first_name, gbc_first_name);
		first_name.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name:");
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.anchor = GridBagConstraints.EAST;
		gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastName.gridx = 0;
		gbc_lblLastName.gridy = 1;
		Actions.add(lblLastName, gbc_lblLastName);
		
		last_name = new JTextField();
		GridBagConstraints gbc_last_name = new GridBagConstraints();
		gbc_last_name.insets = new Insets(0, 0, 5, 0);
		gbc_last_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_last_name.gridx = 1;
		gbc_last_name.gridy = 1;
		Actions.add(last_name, gbc_last_name);
		last_name.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!first_name.getText().isEmpty()){
					names.append( first_name.getText() + " " + last_name.getText() + "\n" );
				}
				
				first_name.setText("");
				last_name.setText("");
			}
		});
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdd.gridx = 1;
		gbc_btnAdd.gridy = 2;
		Actions.add(btnAdd, gbc_btnAdd);
		
		JList list = new JList();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 1;
		gbc_list.gridy = 6;
		Actions.add(list, gbc_list);
		
	}

}
