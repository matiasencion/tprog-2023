package ventanas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import Logica.IContUsuario;
import Logica.Fabrica;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import Logica.DTUsuario;

public class AltaPostulante extends JInternalFrame {
	private JLabel lblNickname;
	private JLabel lblName;
	private JLabel lblLastName;
	private JLabel lblEmail;
	private JLabel lblContraseña;
	private JLabel lblRepetirContraseña;
	private JLabel lblFoto;
	private JLabel lblBirthday;
	private JLabel lblNationality;
	private JTextField textNickname;
	private JTextField textName;
	private JTextField textLastName;
	private JTextField textEmail;
	private JTextField textContraseña;
	private JTextField textRepetirContraseña;
	private JTextField textFoto;
	private JTextField textBirthday;
	private JTextField textNationality;

	/**
	 * Create the frame.
	 */
	public AltaPostulante(IContUsuario ContU) {
		setResizable(true);
		setMaximizable(true);

		setTitle("Alta Postulante");
		setBounds(100, 100, 640, 480);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel footer = new JPanel();
		getContentPane().add(footer, BorderLayout.SOUTH);
		footer.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (checkFields()) {
					if (!textContraseña.getText().equals(textRepetirContraseña.getText())) {
	            		JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Registrar Postulante",
	                            JOptionPane.ERROR_MESSAGE);
	            		textContraseña.setText("");
	            		textRepetirContraseña.setText("");
	            	} else {
					try {

						ContU.CrearPostulante(textNickname.getText(), textName.getText(), textLastName.getText(),
								textEmail.getText(),textContraseña.getText(), textFoto.getText(), textBirthday.getText(), textNationality.getText());

						JOptionPane.showMessageDialog(null, "Postulante registrado con éxito", "Registrar Postulante",
								JOptionPane.INFORMATION_MESSAGE);

						clearAll();
						setVisible(false);

					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Registrar Usuario",
								JOptionPane.ERROR_MESSAGE);
					}

				}
				}

			

			}
		});
		footer.add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				clearAll();
				setVisible(false);

			}
		});
		footer.add(btnCancelar);

		JPanel body = new JPanel();
		getContentPane().add(body, BorderLayout.CENTER);
		GridBagLayout gbl_body = new GridBagLayout();
		gbl_body.columnWidths = new int[] { 120, 120, 120, 0 };
		gbl_body.rowHeights = new int[] {40, 40, 40, 40, 40, 40};
		gbl_body.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_body.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		body.setLayout(gbl_body);

		lblNickname = new JLabel("Nickname");
		GridBagConstraints gbc_lblNickname = new GridBagConstraints();
		gbc_lblNickname.insets = new Insets(0, 0, 5, 5);
		gbc_lblNickname.gridx = 0;
		gbc_lblNickname.gridy = 0;
		body.add(lblNickname, gbc_lblNickname);

		textNickname = new JTextField();
		GridBagConstraints gbc_textNickname = new GridBagConstraints();
		gbc_textNickname.gridwidth = 2;
		gbc_textNickname.insets = new Insets(0, 5, 5, 5);
		gbc_textNickname.fill = GridBagConstraints.BOTH;
		gbc_textNickname.gridx = 1;
		gbc_textNickname.gridy = 0;
		body.add(textNickname, gbc_textNickname);
		textNickname.setColumns(10);

		lblName = new JLabel("Nombre");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		body.add(lblName, gbc_lblName);

		textName = new JTextField();
		GridBagConstraints gbc_textName = new GridBagConstraints();
		gbc_textName.gridwidth = 2;
		gbc_textName.insets = new Insets(0, 5, 5, 5);
		gbc_textName.fill = GridBagConstraints.BOTH;
		gbc_textName.gridx = 1;
		gbc_textName.gridy = 1;
		body.add(textName, gbc_textName);
		textName.setColumns(10);

		lblLastName = new JLabel("Apellido");
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastName.gridx = 0;
		gbc_lblLastName.gridy = 2;
		body.add(lblLastName, gbc_lblLastName);

		textLastName = new JTextField();
		GridBagConstraints gbc_textLastName = new GridBagConstraints();
		gbc_textLastName.gridwidth = 2;
		gbc_textLastName.insets = new Insets(0, 5, 5, 5);
		gbc_textLastName.fill = GridBagConstraints.BOTH;
		gbc_textLastName.gridx = 1;
		gbc_textLastName.gridy = 2;
		body.add(textLastName, gbc_textLastName);
		textLastName.setColumns(10);

		lblEmail = new JLabel("Email");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 3;
		body.add(lblEmail, gbc_lblEmail);

		textEmail = new JTextField();
		GridBagConstraints gbc_textEmail = new GridBagConstraints();
		gbc_textEmail.gridwidth = 2;
		gbc_textEmail.insets = new Insets(0, 5, 5, 5);
		gbc_textEmail.fill = GridBagConstraints.BOTH;
		gbc_textEmail.gridx = 1;
		gbc_textEmail.gridy = 3;
		body.add(textEmail, gbc_textEmail);
		textEmail.setColumns(10);
		
		lblContraseña = new JLabel("Contraseña");
		GridBagConstraints gbc_lblContraseña = new GridBagConstraints();
		gbc_lblContraseña.insets = new Insets(0, 0, 5, 5);
		gbc_lblContraseña.gridx = 0;
		gbc_lblContraseña.gridy = 4;
		body.add(lblContraseña, gbc_lblContraseña);

		textContraseña = new JTextField();
		GridBagConstraints gbc_textContraseña = new GridBagConstraints();
		gbc_textContraseña.gridwidth = 2;
		gbc_textContraseña.insets = new Insets(0, 5, 5, 5);
		gbc_textContraseña.fill = GridBagConstraints.BOTH;
		gbc_textContraseña.gridx = 1;
		gbc_textContraseña.gridy = 4;
		body.add(textContraseña, gbc_textContraseña);
		textContraseña.setColumns(10);
		
		lblRepetirContraseña = new JLabel("Repetir contraseña");
		GridBagConstraints gbc_lblRepetirContraseña = new GridBagConstraints();
		gbc_lblRepetirContraseña.insets = new Insets(0, 0, 5, 5);
		gbc_lblRepetirContraseña.gridx = 0;
		gbc_lblRepetirContraseña.gridy = 5;
		body.add(lblRepetirContraseña, gbc_lblRepetirContraseña);

		textRepetirContraseña = new JTextField();
		GridBagConstraints gbc_textRepetirContraseña = new GridBagConstraints();
		gbc_textRepetirContraseña.gridwidth = 2;
		gbc_textRepetirContraseña.insets = new Insets(0, 5, 5, 5);
		gbc_textRepetirContraseña.fill = GridBagConstraints.BOTH;
		gbc_textRepetirContraseña.gridx = 1;
		gbc_textRepetirContraseña.gridy = 5;
		body.add(textRepetirContraseña, gbc_textRepetirContraseña);
		textRepetirContraseña.setColumns(10);
		
		lblFoto = new JLabel("Foto (opcional)");
		GridBagConstraints gbc_lblFoto = new GridBagConstraints();
		gbc_lblFoto.insets = new Insets(0, 0, 5, 5);
		gbc_lblFoto.gridx = 0;
		gbc_lblFoto.gridy = 6;
		body.add(lblFoto, gbc_lblFoto);

		textFoto = new JTextField();
		GridBagConstraints gbc_textFoto = new GridBagConstraints();
		gbc_textFoto.gridwidth = 2;
		gbc_textFoto.insets = new Insets(0, 5, 5, 5);
		gbc_textFoto.fill = GridBagConstraints.BOTH;
		gbc_textFoto.gridx = 1;
		gbc_textFoto.gridy = 6;
		body.add(textFoto, gbc_textFoto);
		textFoto.setColumns(10);

		lblBirthday = new JLabel("Fecha de Nacimiento");
		GridBagConstraints gbc_lblBirthday = new GridBagConstraints();
		gbc_lblBirthday.insets = new Insets(0, 0, 5, 5);
		gbc_lblBirthday.gridx = 0;
		gbc_lblBirthday.gridy = 7;
		body.add(lblBirthday, gbc_lblBirthday);

		textBirthday = new JTextField();
		textBirthday.setToolTipText("dd/mm/aaaa");
		GridBagConstraints gbc_textBirthday = new GridBagConstraints();
		gbc_textBirthday.gridwidth = 2;
		gbc_textBirthday.insets = new Insets(0, 5, 5, 5);
		gbc_textBirthday.fill = GridBagConstraints.BOTH;
		gbc_textBirthday.gridx = 1;
		gbc_textBirthday.gridy = 7;
		body.add(textBirthday, gbc_textBirthday);
		textBirthday.setColumns(10);

		lblNationality = new JLabel("Nacionalidad");
		GridBagConstraints gbc_lblNationality = new GridBagConstraints();
		gbc_lblNationality.insets = new Insets(0, 0, 0, 5);
		gbc_lblNationality.gridx = 0;
		gbc_lblNationality.gridy = 8;
		body.add(lblNationality, gbc_lblNationality);

		textNationality = new JTextField();
		GridBagConstraints gbc_textNationality = new GridBagConstraints();
		gbc_textNationality.gridwidth = 2;
		gbc_textNationality.insets = new Insets(0, 5, 5, 5);
		gbc_textNationality.fill = GridBagConstraints.BOTH;
		gbc_textNationality.gridx = 1;
		gbc_textNationality.gridy = 8;
		body.add(textNationality, gbc_textNationality);
		textNationality.setColumns(10);

	}

	private boolean checkFields() {
		if (textNickname.getText().equals("") || textName.getText().equals("") || textLastName.getText().equals("")
				|| textEmail.getText().equals("") || textContraseña.getText().equals("") || textRepetirContraseña.getText().equals("") || textBirthday.getText().equals("")
				|| textNationality.getText().equals("")) {

			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Registrar Usuario",
					JOptionPane.ERROR_MESSAGE);

			return false;
		} else {

			return true;
		}
	}

	private void clearAll() {
		textNickname.setText("");
		textName.setText("");
		textLastName.setText("");
		textEmail.setText("");
		textContraseña.setText("");
		textRepetirContraseña.setText("");
		textFoto.setText("");
		textBirthday.setText("");
		textNationality.setText("");
	}

}
