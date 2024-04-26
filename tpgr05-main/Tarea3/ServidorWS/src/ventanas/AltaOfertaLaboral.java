package ventanas;

import javax.swing.*;
import java.util.HashSet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.FocusEvent;
import java.util.Vector;
import Logica.IContOferta;
import java.util.Set;
import Excepciones.OfertaRepetidaException;
import Excepciones.TOfertaNoExiste;



public class AltaOfertaLaboral extends JInternalFrame {

	private  JComboBox listarEmpresas;
	private  JComboBox tiposPublicacion;
	private JComboBox listakeywords;  
	private JButton btnAceptar, btnCancelar, btnKeywords;
	private JTextField textCiudad;
	private JTextField textNombre;
	private JTextField textDescripcion;
	private JTextField textHorarioI;
	private JTextField textRemuneracion;
	private JTextField textHorarioF;
	private JTextField textDepartamento;
	private JTextField textFecha;
	Set<String> keywords = new HashSet<String>();
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JTextField foto;
	private JComboBox paquete;

	public AltaOfertaLaboral(IContOferta contOferta) {
		setResizable(true);
		setMaximizable(true);
		setTitle("Alta Oferta Laboral");
		setBounds(100, 100, 640, 580);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel footer = new JPanel();
		FlowLayout flowLayout = (FlowLayout) footer.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(footer, BorderLayout.SOUTH);
		

		btnAceptar = new JButton("Crear Oferta");

		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent e ){
				if (checkFields()) {
				DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				String empresa = (String) listarEmpresas.getSelectedItem();
				String tipo = (String) tiposPublicacion.getSelectedItem();
				String nombre = textNombre.getText().toLowerCase();
				String descripcion = textDescripcion.getText();
				String horarioI = textHorarioI.getText();
				String horarioF = textHorarioF.getText();
				String remu = textRemuneracion.getText();
				Float remuneracion = Float.parseFloat(remu);
				String ciudad = textCiudad.getText();
				String departamento = textDepartamento.getText();
				LocalDate fechaAlta = null;
				String pic = foto.getText();
				String paq = (String) paquete.getSelectedItem();
				try {
				fechaAlta = LocalDate.parse(textFecha.getText(), dateFormatter);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Formato fecha es dd/mm/aaaa", "Alta Oferta Laboral",
							JOptionPane.ERROR_MESSAGE);
							textNombre.setText("");
				}

				if (fechaAlta != null) {
				try {
					contOferta.altaOferta(empresa, tipo, nombre, descripcion, horarioI, horarioF, remuneracion, ciudad, departamento,fechaAlta, keywords, paq, pic);
					limpiarCampos();
				setVisible(false);
				} catch (OfertaRepetidaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage() + "\n" +  "Se limpiaron las keywords seleccionadas", "Alta Oferta Laboral",
							JOptionPane.ERROR_MESSAGE);
							textNombre.setText("");
							keywords.clear();
				}
				
				}}
			}
		});

		footer.add(btnAceptar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
				setVisible(false);

			}
		});
		footer.add(btnCancelar);

		JPanel body = new JPanel();
		getContentPane().add(body, BorderLayout.CENTER);
		GridBagLayout gbl_body = new GridBagLayout();
		gbl_body.columnWidths = new int[] {120, 0, 0, 0};
		gbl_body.rowHeights = new int[] {30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 0, 0};
		gbl_body.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0};
		gbl_body.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		body.setLayout(gbl_body);

		tiposPublicacion = new JComboBox();
		tiposPublicacion.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent arg0) {

				Vector<String> tipos = (Vector<String>) contOferta.listarTOfertas();
				mostrarTiposPublicacion(tipos);  
			}
			public void focusLost(FocusEvent arg0) {
			}
		});

		JLabel lblNewLabel = new JLabel("Empresas");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		body.add(lblNewLabel, gbc_lblNewLabel);

		listarEmpresas = new JComboBox();
		listarEmpresas.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent arg0) {

				Vector<String> empresas = (Vector<String>) contOferta.listarEmpresas();
				mostrarEmpresas(empresas);
			}
			public void focusLost(FocusEvent arg0) {
			}
		});
		GridBagConstraints gbc_listarEmpresas = new GridBagConstraints();
		gbc_listarEmpresas.gridwidth = 2;
		gbc_listarEmpresas.fill = GridBagConstraints.BOTH;
		gbc_listarEmpresas.insets = new Insets(5, 5, 5, 5);
		gbc_listarEmpresas.gridx = 1;
		gbc_listarEmpresas.gridy = 0;
		body.add(listarEmpresas, gbc_listarEmpresas);

		JLabel lblNewLabel_1 = new JLabel("Tipo de Publicacion");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		body.add(lblNewLabel_1, gbc_lblNewLabel_1);
		GridBagConstraints gbc_tiposPublicacion = new GridBagConstraints();
		gbc_tiposPublicacion.gridwidth = 2;
		gbc_tiposPublicacion.fill = GridBagConstraints.BOTH;
		gbc_tiposPublicacion.insets = new Insets(0, 5, 5, 5);
		gbc_tiposPublicacion.gridx = 1;
		gbc_tiposPublicacion.gridy = 1;
		body.add(tiposPublicacion, gbc_tiposPublicacion);

		JLabel lblNewLabel_2 = new JLabel("Nombre");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		body.add(lblNewLabel_2, gbc_lblNewLabel_2);

		textCiudad = new JTextField();
		GridBagConstraints gbc_textCiudad = new GridBagConstraints();
		gbc_textCiudad.fill = GridBagConstraints.BOTH;
		gbc_textCiudad.insets = new Insets(0, 5, 5, 5);
		gbc_textCiudad.gridwidth = 2;
		gbc_textCiudad.gridx = 1;
		gbc_textCiudad.gridy = 6;
		body.add(textCiudad, gbc_textCiudad);
		textCiudad.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Descripcion");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_3.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 3;
		body.add(lblNewLabel_3, gbc_lblNewLabel_3);

		textNombre = new JTextField();
		textNombre.setColumns(10);
		GridBagConstraints gbc_textNombre = new GridBagConstraints();
		gbc_textNombre.fill = GridBagConstraints.BOTH;
		gbc_textNombre.insets = new Insets(0, 5, 5, 5);
		gbc_textNombre.gridwidth = 2;
		gbc_textNombre.gridx = 1;
		gbc_textNombre.gridy = 2;
		body.add(textNombre, gbc_textNombre);

		JLabel lblNewLabel_4 = new JLabel("Horario (hh:mm)");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_4.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 4;
		body.add(lblNewLabel_4, gbc_lblNewLabel_4);

		textDescripcion = new JTextField();
		textDescripcion.setColumns(10);
		GridBagConstraints gbc_textDescripcion = new GridBagConstraints();
		gbc_textDescripcion.fill = GridBagConstraints.BOTH;
		gbc_textDescripcion.insets = new Insets(0, 5, 5, 5);
		gbc_textDescripcion.gridwidth = 2;
		gbc_textDescripcion.gridx = 1;
		gbc_textDescripcion.gridy = 3;
		body.add(textDescripcion, gbc_textDescripcion);

		JLabel lblNewLabel_5 = new JLabel("Remuneracion");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_5.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 5;
		body.add(lblNewLabel_5, gbc_lblNewLabel_5);

		textHorarioI = new JTextField();
		textHorarioI.setColumns(10);
		GridBagConstraints gbc_textHorarioI = new GridBagConstraints();
		gbc_textHorarioI.fill = GridBagConstraints.BOTH;
		gbc_textHorarioI.insets = new Insets(0, 5, 5, 5);
		gbc_textHorarioI.gridx = 1;
		gbc_textHorarioI.gridy = 4;
		body.add(textHorarioI, gbc_textHorarioI);

		JLabel lblNewLabel_6 = new JLabel("Ciudad");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_6.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 6;
		body.add(lblNewLabel_6, gbc_lblNewLabel_6);

		textRemuneracion = new JTextField();
		textRemuneracion.setColumns(10);
		GridBagConstraints gbc_textRemuneracion = new GridBagConstraints();
		gbc_textRemuneracion.fill = GridBagConstraints.BOTH;
		gbc_textRemuneracion.insets = new Insets(0, 5, 5, 5);
		gbc_textRemuneracion.gridwidth = 2;
		gbc_textRemuneracion.gridx = 1;
		gbc_textRemuneracion.gridy = 5;
		body.add(textRemuneracion, gbc_textRemuneracion);

		JLabel lblNewLabel_7 = new JLabel("Departamento");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_7.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel_7.gridx = 0;
		gbc_lblNewLabel_7.gridy = 7;
		body.add(lblNewLabel_7, gbc_lblNewLabel_7);

		textHorarioF = new JTextField();
		textHorarioF.setColumns(10);
		GridBagConstraints gbc_textHorarioF = new GridBagConstraints();
		gbc_textHorarioF.fill = GridBagConstraints.BOTH;
		gbc_textHorarioF.insets = new Insets(0, 5, 5, 5);
		gbc_textHorarioF.gridx = 2;
		gbc_textHorarioF.gridy = 4;
		body.add(textHorarioF, gbc_textHorarioF);

		listakeywords = new JComboBox();

		listakeywords.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent arg0) {

				Vector<String> vKeywords = (Vector<String>) contOferta.listarKeywords();
				for (String i : keywords) {
					vKeywords.remove(i);
				}				
				mostrarKeywords(vKeywords);
			}
			public void focusLost(FocusEvent arg0) {
			}
		});	
		
		

		textDepartamento = new JTextField();
		textDepartamento.setColumns(10);
		GridBagConstraints gbc_textDepartamento = new GridBagConstraints();
		gbc_textDepartamento.fill = GridBagConstraints.BOTH;
		gbc_textDepartamento.insets = new Insets(0, 5, 5, 5);
		gbc_textDepartamento.gridwidth = 2;
		gbc_textDepartamento.gridx = 1;
		gbc_textDepartamento.gridy = 7;
		body.add(textDepartamento, gbc_textDepartamento);
		
		JLabel lblFecha = new JLabel("Fecha Alta (dd/mm/aaaa)");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblFecha = new GridBagConstraints();
		gbc_lblFecha.fill = GridBagConstraints.BOTH;
		gbc_lblFecha.insets = new Insets(0, 5, 5, 5);
		gbc_lblFecha.gridx = 0;
		gbc_lblFecha.gridy = 8;
		body.add(lblFecha, gbc_lblFecha);
		
		textFecha = new JTextField();
		textFecha.setColumns(10);
		GridBagConstraints gbc_textFecha = new GridBagConstraints();
		gbc_textFecha.fill = GridBagConstraints.BOTH;
		gbc_textFecha.insets = new Insets(0, 5, 5, 5);
		gbc_textFecha.gridwidth = 2;
		gbc_textFecha.gridx = 1;
		gbc_textFecha.gridy = 8;
		body.add(textFecha, gbc_textFecha);

		JLabel lblNewLabel_8 = new JLabel("Keywords");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_8.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel_8.gridx = 0;
		gbc_lblNewLabel_8.gridy = 9;
		body.add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		GridBagConstraints gbc_listakeywords = new GridBagConstraints();
		gbc_listakeywords.fill = GridBagConstraints.BOTH;
		gbc_listakeywords.insets = new Insets(0, 5, 5, 5);
		gbc_listakeywords.gridx = 1;
		gbc_listakeywords.gridy = 9;
		body.add(listakeywords, gbc_listakeywords);

		btnKeywords = new JButton("Aniadir Key");
		GridBagConstraints gbc_btnKeywords = new GridBagConstraints();
		gbc_btnKeywords.insets = new Insets(0, 5, 5, 5);
		gbc_btnKeywords.fill = GridBagConstraints.BOTH;
		gbc_btnKeywords.gridx = 2;
		gbc_btnKeywords.gridy = 9;
		body.add(btnKeywords, gbc_btnKeywords);
		
		lblNewLabel_9 = new JLabel("Paquete");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_9.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel_9.gridx = 0;
		gbc_lblNewLabel_9.gridy = 10;
		body.add(lblNewLabel_9, gbc_lblNewLabel_9);
		
		paquete = new JComboBox();
		GridBagConstraints gbc_paquete = new GridBagConstraints();
		gbc_paquete.gridwidth = 2;
		gbc_paquete.insets = new Insets(0, 5, 5, 5);
		gbc_paquete.fill = GridBagConstraints.BOTH;
		gbc_paquete.gridx = 1;
		gbc_paquete.gridy = 10;
		body.add(paquete, gbc_paquete);
		paquete.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent arg0) {

				Vector<String> p = (Vector<String>) contOferta.listarPaquetes();
				p.add(0, "");
				mostrarPaquete(p);  
			}
			public void focusLost(FocusEvent arg0) {
			}
		});
		
		lblNewLabel_10 = new JLabel("Foto");
		GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
		gbc_lblNewLabel_10.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_10.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel_10.gridx = 0;
		gbc_lblNewLabel_10.gridy = 11;
		body.add(lblNewLabel_10, gbc_lblNewLabel_10);
		
		foto = new JTextField();
		GridBagConstraints gbc_foto = new GridBagConstraints();
		gbc_foto.gridwidth = 2;
		gbc_foto.insets = new Insets(0, 5, 5, 5);
		gbc_foto.fill = GridBagConstraints.BOTH;
		gbc_foto.gridx = 1;
		gbc_foto.gridy = 11;
		body.add(foto, gbc_foto);
		foto.setColumns(10);
		btnKeywords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keywords.add((String) listakeywords.getSelectedItem());
				System.out.println(keywords);
			}
			
		});
		

	}
	private void limpiarCampos() {
		keywords.clear();
		listakeywords.setSelectedIndex(-1);
		listarEmpresas.setSelectedIndex(-1);
		tiposPublicacion.setSelectedIndex(-1);
		textCiudad.setText("");
		textNombre.setText("");
		textDescripcion.setText("");
		textHorarioI.setText("");
		textRemuneracion.setText("");
		textHorarioF.setText("");
		textDepartamento.setText("");
		textFecha.setText("");
	}
	
	private boolean checkFields() {
		if (textNombre.getText().equals("") || textCiudad.getText().equals("") || textDescripcion.getText().equals("")
				|| textHorarioI.getText().equals("") || textHorarioF.getText().equals("")
				|| textRemuneracion.getText().equals("") || textDepartamento.getText().equals("") || textFecha.getText().equals("") 
				 || listarEmpresas.getSelectedIndex()==-1) {

			JOptionPane.showMessageDialog(this, "No puede haber campos vacios", "Alta Oferta",
					JOptionPane.ERROR_MESSAGE);

			return false;
		} else {

			return true;
		}
	}
	public void mostrarEmpresas(Vector<String> nombresEmpresas) {
		listarEmpresas.removeAllItems();
		for (String empresa : nombresEmpresas) {
			listarEmpresas.addItem(empresa);
		}}

	public void mostrarTiposPublicacion(Vector<String> tipos) {
		tiposPublicacion.removeAllItems();
		for (String t : tipos) {
			tiposPublicacion.addItem(t);
		}
	}

	public void mostrarKeywords(Vector<String> keywords) {
		listakeywords.removeAllItems();
		for (String k : keywords) {
			listakeywords.addItem(k);
		}

	}
	
	public void mostrarPaquete(Vector<String> p) {
		paquete.removeAllItems();
		for (String k : p) {
			paquete.addItem(k);
		}

	}

	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}





}



