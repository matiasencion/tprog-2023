package ventanas;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

import Logica.IContOferta;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;



public class AltaTipoPublicacionOferta extends JInternalFrame {

	private JTextField txtNombre, txtDescripcion, txtExposicion, txtDuracion, txtCosto, txtFechaAlta;
	private JButton btnRegistrar, btnCancelar;
	private JPanel body;
	private JPanel footer;
	

	public AltaTipoPublicacionOferta(IContOferta ContOf) {
		setResizable(true);
    	setMaximizable(true);
		setTitle("Alta de Tipo de Publicación de Oferta Laboral");
		setBounds(100, 100, 600, 400);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		body = new JPanel();
		getContentPane().add(body, BorderLayout.CENTER);
		GridBagLayout gbl_body = new GridBagLayout();
		gbl_body.columnWidths = new int[] { 120, 120, 120, 0 };
		gbl_body.rowHeights = new int[] {40, 40, 40, 40, 40, 40};
		gbl_body.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_body.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		body.setLayout(gbl_body);
		
		JLabel lblNombre = new JLabel("Nombre:");	
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets =  new Insets(0, 0, 5, 5);
		gbc_lblNombre.fill = GridBagConstraints.BOTH;
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 0;
		body.add(lblNombre, gbc_lblNombre);

		txtNombre = new JTextField();
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.gridwidth = 2;
		gbc_txtNombre.fill = GridBagConstraints.BOTH;
		gbc_txtNombre.insets =  new Insets(0, 5, 5, 5);
		gbc_txtNombre.gridx = 1;
		gbc_txtNombre.gridy = 0;
		body.add(txtNombre, gbc_txtNombre);

		JLabel lblDescripcion = new JLabel("Descripción:");		
		GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
		gbc_lblDescripcion.fill = GridBagConstraints.BOTH;

		gbc_lblDescripcion.gridx = 0;
		gbc_lblDescripcion.insets =  new Insets(0, 0, 5, 5);
		gbc_lblDescripcion.gridy = 1;
		body.add(lblDescripcion, gbc_lblDescripcion);

		txtDescripcion = new JTextField();
		GridBagConstraints gbc_textDescripcion = new GridBagConstraints();
		gbc_textDescripcion.gridwidth = 2;
		gbc_textDescripcion.fill = GridBagConstraints.BOTH;
		gbc_textDescripcion.insets =  new Insets(0, 5, 5, 5);
		gbc_textDescripcion.gridx = 1;
		gbc_textDescripcion.gridy = 1;
		body.add(txtDescripcion, gbc_textDescripcion);

		JLabel lblExposicion = new JLabel("Exposición (Orden en listados):");
		GridBagConstraints gbc_lblExposicion = new GridBagConstraints();
		gbc_lblExposicion.fill = GridBagConstraints.BOTH;
		gbc_lblExposicion.insets =  new Insets(0, 0, 5, 5);
		gbc_lblExposicion.gridx = 0;
		gbc_lblExposicion.gridy = 2;
		body.add(lblExposicion, gbc_lblExposicion);

		txtExposicion = new JTextField();
		GridBagConstraints gbc_txtExposicion = new GridBagConstraints();
		gbc_txtExposicion.gridwidth = 2;
		gbc_txtExposicion.fill = GridBagConstraints.BOTH;
		gbc_txtExposicion.insets =  new Insets(0, 5, 5, 5);
		gbc_txtExposicion.gridx = 1;
		gbc_txtExposicion.gridy = 2;
		body.add(txtExposicion, gbc_txtExposicion);


		JLabel lblDuracion = new JLabel("Duración (días):");
		GridBagConstraints gbc_lblDuracion = new GridBagConstraints();
		gbc_lblDuracion.fill = GridBagConstraints.BOTH;
		gbc_lblDuracion.insets =  new Insets(0, 0, 5, 5);
		gbc_lblDuracion.gridx = 0;
		gbc_lblDuracion.gridy = 3;
		body.add(lblDuracion, gbc_lblDuracion);


		txtDuracion = new JTextField();
		GridBagConstraints gbc_txtDuracion = new GridBagConstraints();
		gbc_txtDuracion.gridwidth = 2;
		gbc_txtDuracion.fill = GridBagConstraints.BOTH;
		gbc_txtDuracion.insets =  new Insets(0, 5, 5, 5);
		gbc_txtDuracion.gridx = 1;
		gbc_txtDuracion.gridy = 3;
		body.add(txtDuracion, gbc_txtDuracion);

		JLabel lblCosto = new JLabel("Costo:");
		GridBagConstraints gbc_lblCosto = new GridBagConstraints();
		gbc_lblCosto.fill = GridBagConstraints.BOTH;
		gbc_lblCosto.insets =  new Insets(0, 0, 5, 5);
		gbc_lblCosto.gridx = 0;
		gbc_lblCosto.gridy = 4;
		body.add(lblCosto, gbc_lblCosto);

		txtCosto = new JTextField();
		GridBagConstraints gbc_txtCosto = new GridBagConstraints();
		gbc_txtCosto.gridwidth = 2;
		gbc_txtCosto.fill = GridBagConstraints.BOTH;
		gbc_txtCosto.insets =  new Insets(0, 5, 5, 5);
		gbc_txtCosto.gridx = 1;
		gbc_txtCosto.gridy = 4;
		body.add(txtCosto, gbc_txtCosto);

		JLabel lblFechaAlta = new JLabel("Fecha de Alta:");
		GridBagConstraints gbc_lblFechaAlta = new GridBagConstraints();
		gbc_lblFechaAlta.fill = GridBagConstraints.BOTH;
		gbc_lblFechaAlta.insets =  new Insets(0, 0, 5, 5);
		gbc_lblFechaAlta.gridx = 0;
		gbc_lblFechaAlta.gridy = 5;
		body.add(lblFechaAlta, gbc_lblFechaAlta);

		txtFechaAlta = new JTextField();
		txtFechaAlta.setToolTipText("dd/mm/aaaa");
		GridBagConstraints gbc_txtFechaAlta = new GridBagConstraints();
		gbc_txtFechaAlta.gridwidth = 2;
		gbc_txtFechaAlta.fill = GridBagConstraints.BOTH;
		gbc_txtFechaAlta.insets =  new Insets(0, 5, 5, 5);
		gbc_txtFechaAlta.gridx = 1;
		gbc_txtFechaAlta.gridy = 5;
		body.add(txtFechaAlta, gbc_txtFechaAlta);

		footer = new JPanel();
		FlowLayout flowLayout = (FlowLayout) footer.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(footer, BorderLayout.SOUTH);
		
		
		btnRegistrar = new JButton("Registrar");
		
		btnRegistrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

				if (txtNombre.getText().isEmpty() || txtDescripcion.getText().isEmpty()
						|| txtExposicion.getText().isEmpty() || txtDuracion.getText().isEmpty()
						|| txtCosto.getText().isEmpty() || txtFechaAlta.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Debe completar todos los campos", "Alta tipo publicacion",
							JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						ContOf.agregarTOferta(txtNombre.getText().toLowerCase(), txtDescripcion.getText(), Integer.parseInt(txtExposicion.getText()),
								Integer.parseInt(txtDuracion.getText()), Float.parseFloat(txtCosto.getText()), 
								LocalDate.parse(txtFechaAlta.getText(), dateFormatter)
								);

						System.out.println( (LocalDate.parse(txtFechaAlta.getText(), dateFormatter)) );
						System.out.println( (LocalDate.parse(txtFechaAlta.getText(), dateFormatter)).plusDays( Integer.parseInt(txtDuracion.getText()) ) );


						JOptionPane.showMessageDialog(null, "Tipo de publicacion registrada con éxito", "Alta Tipo Publicacion",
								JOptionPane.INFORMATION_MESSAGE);
						limpiarCampos();
						setVisible(false);

					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Alta Tipo Publicacion",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		footer.add(btnRegistrar);
		
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
				setVisible(false);
			}
		});
		footer.add(btnCancelar);
	}

	private void limpiarCampos() {
		txtNombre.setText("");
		txtDescripcion.setText("");
		txtExposicion.setText("");
		txtDuracion.setText("");
		txtCosto.setText("");
		txtFechaAlta.setText("");
	}
}

