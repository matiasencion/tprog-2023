package ventanas;

import javax.swing.*;

import Logica.IContOferta;
import Logica.IContUsuario;
import excepciones.UsuarioNoExisteException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PostulacionOfertaLaboral extends JInternalFrame {

	private JComboBox<String> listaOfertasLaborales, listaPostulantes, listaEmpresas;
	private JTextField fechaPostulacion;
	private JTextArea cvReducido;
	private JTextArea motivacion;
	private JTextArea detalleOfertaLaboral;

	public PostulacionOfertaLaboral(IContUsuario ContU, IContOferta ContO) {
		setResizable(true);
		setMaximizable(true);
		setTitle("Postulación a Oferta Laboral");
		setBounds(100, 100, 640, 480);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 130, 100, 350 };
		gridBagLayout.rowHeights = new int[] { 30, 30, 100, 30, 56, 61, 30, 30, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, Double.MIN_VALUE, 1.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblEmpresas = new JLabel("Empresas:");
		GridBagConstraints gbc_lblEmpresas = new GridBagConstraints();
		gbc_lblEmpresas.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmpresas.gridx = 0;
		gbc_lblEmpresas.gridy = 0;
		getContentPane().add(lblEmpresas, gbc_lblEmpresas);

		listaEmpresas = new JComboBox<String>();
        listaEmpresas.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
			}
			@Override
			public void focusGained(FocusEvent e) {
                listaOfertasLaborales.setSelectedIndex(-1);
				listaEmpresas.removeAllItems();

				for (String em : ContU.getNombresEmpresas()) {
					listaEmpresas.addItem(em);
				}
			}
		});

        
		GridBagConstraints gbc_listaEmpresas = new GridBagConstraints();
		gbc_listaEmpresas.fill = GridBagConstraints.BOTH;
		gbc_listaEmpresas.gridwidth = 2;
		gbc_listaEmpresas.insets = new Insets(0, 0, 5, 0);
		gbc_listaEmpresas.gridx = 1;
		gbc_listaEmpresas.gridy = 0;  
        getContentPane().add(listaEmpresas, gbc_listaEmpresas);




		JLabel lblOfertasLaborales = new JLabel("Ofertas Laborales:");
		GridBagConstraints gbc_lblOfertasLaborales = new GridBagConstraints();
		gbc_lblOfertasLaborales.insets = new Insets(0, 0, 5, 5);
		gbc_lblOfertasLaborales.gridx = 0;
		gbc_lblOfertasLaborales.gridy = 1;
		getContentPane().add(lblOfertasLaborales, gbc_lblOfertasLaborales);

		listaOfertasLaborales = new JComboBox<>();
        listaOfertasLaborales.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {
            }
            @Override
            public void focusGained(FocusEvent e) {

                listaOfertasLaborales.removeAllItems();
                String empresa = (String) listaEmpresas.getSelectedItem();

                for (String of : ContU.getNombresOfertas(empresa)) {
                    listaOfertasLaborales.addItem(of);
                }

            }

        });


        
		GridBagConstraints gbc_listaOfertasLaborales = new GridBagConstraints();
		gbc_listaOfertasLaborales.gridwidth = 2;
		gbc_listaOfertasLaborales.fill = GridBagConstraints.BOTH;
		gbc_listaOfertasLaborales.insets = new Insets(0, 0, 5, 0);
		gbc_listaOfertasLaborales.gridx = 1;
		gbc_listaOfertasLaborales.gridy = 1;
		getContentPane().add(listaOfertasLaborales, gbc_listaOfertasLaborales);

		JLabel lblDetalleOferta = new JLabel("Detalle Oferta:");
		GridBagConstraints gbc_lblDetalleOferta = new GridBagConstraints();
		gbc_lblDetalleOferta.insets = new Insets(0, 0, 5, 5);
		gbc_lblDetalleOferta.gridx = 0;
		gbc_lblDetalleOferta.gridy = 2;
		getContentPane().add(lblDetalleOferta, gbc_lblDetalleOferta);

		JLabel lblPostulantes = new JLabel("Postulantes:");
		GridBagConstraints gbc_lblPostulantes = new GridBagConstraints();
		gbc_lblPostulantes.insets = new Insets(0, 0, 5, 5);
		gbc_lblPostulantes.gridx = 0;
		gbc_lblPostulantes.gridy = 3;
		getContentPane().add(lblPostulantes, gbc_lblPostulantes);

		listaPostulantes = new JComboBox<String>();
        listaPostulantes.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {
            }
            @Override
            public void focusGained(FocusEvent e) {
                listaPostulantes.removeAllItems();

                for (String em : ContU.getNombresPostulantes()) {
                    listaPostulantes.addItem(em);
                }
            }
        });
		GridBagConstraints gbc_listaPostulantes = new GridBagConstraints();
		gbc_listaPostulantes.gridwidth = 2;
		gbc_listaPostulantes.fill = GridBagConstraints.BOTH;
		gbc_listaPostulantes.insets = new Insets(0, 0, 5, 0);
		gbc_listaPostulantes.gridx = 1;
		gbc_listaPostulantes.gridy = 3;
		getContentPane().add(listaPostulantes, gbc_listaPostulantes);

		JLabel lblCvReducido = new JLabel("CV Reducido:");
		GridBagConstraints gbc_lblCvReducido = new GridBagConstraints();
		gbc_lblCvReducido.insets = new Insets(0, 0, 5, 5);
		gbc_lblCvReducido.gridx = 0;
		gbc_lblCvReducido.gridy = 4;
		getContentPane().add(lblCvReducido, gbc_lblCvReducido);

		JLabel lblMotivacion = new JLabel("Motivación:");
		GridBagConstraints gbc_lblMotivacion = new GridBagConstraints();
		gbc_lblMotivacion.insets = new Insets(0, 0, 5, 5);
		gbc_lblMotivacion.gridx = 0;
		gbc_lblMotivacion.gridy = 5;
		getContentPane().add(lblMotivacion, gbc_lblMotivacion);

		JLabel lblFecha = new JLabel("Fecha de Postulación: ");
		GridBagConstraints gbc_lblFecha = new GridBagConstraints();
		gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
		gbc_lblFecha.gridx = 0;
		gbc_lblFecha.gridy = 6;
		getContentPane().add(lblFecha, gbc_lblFecha);

		fechaPostulacion = new JTextField();
		fechaPostulacion.setToolTipText("dd/mm/aa");
		GridBagConstraints gbc_fechaPostulacion = new GridBagConstraints();
		gbc_fechaPostulacion.gridwidth = 2;
		gbc_fechaPostulacion.fill = GridBagConstraints.HORIZONTAL;
		gbc_fechaPostulacion.insets = new Insets(0, 0, 5, 5);
		gbc_fechaPostulacion.gridx = 1;
		gbc_fechaPostulacion.gridy = 6;
		getContentPane().add(fechaPostulacion, gbc_fechaPostulacion);

		detalleOfertaLaboral = new JTextArea();
        listaOfertasLaborales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(listaOfertasLaborales.getSelectedIndex() == -1) return;
                String oferta = (String) listaOfertasLaborales.getSelectedItem();
                String info = ContO.getInfoOfertaString(oferta);
            

                detalleOfertaLaboral.setText(info);
            }
        });
 
     	detalleOfertaLaboral.setLineWrap(true);
		detalleOfertaLaboral.setEditable(false);
		GridBagConstraints gbc_detalleOfertaLaboral = new GridBagConstraints();
		gbc_detalleOfertaLaboral.gridwidth = 2;
		gbc_detalleOfertaLaboral.insets = new Insets(0, 0, 5, 0);
		gbc_detalleOfertaLaboral.fill = GridBagConstraints.BOTH;
		gbc_detalleOfertaLaboral.gridx = 1;
		gbc_detalleOfertaLaboral.gridy = 2;
		getContentPane().add(detalleOfertaLaboral, gbc_detalleOfertaLaboral);

		cvReducido = new JTextArea();
		cvReducido.setLineWrap(true);
		GridBagConstraints gbc_cvReducido = new GridBagConstraints();
		gbc_cvReducido.gridwidth = 2;
		gbc_cvReducido.insets = new Insets(0, 0, 5, 0);
		gbc_cvReducido.fill = GridBagConstraints.BOTH;
		gbc_cvReducido.gridx = 1;
		gbc_cvReducido.gridy = 4;
		getContentPane().add(cvReducido, gbc_cvReducido);

		motivacion = new JTextArea();
		motivacion.setLineWrap(true);
		GridBagConstraints gbc_motivacion = new GridBagConstraints();
		gbc_motivacion.gridwidth = 2;
		gbc_motivacion.insets = new Insets(0, 0, 5, 5);
		gbc_motivacion.fill = GridBagConstraints.BOTH;
		gbc_motivacion.gridx = 1;
		gbc_motivacion.gridy = 5;
		getContentPane().add(motivacion, gbc_motivacion);

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 2;
		gbc_panel_1.gridy = 7;
		getContentPane().add(panel_1, gbc_panel_1);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
             if (checkFields()) {
            	 
            	 String oferta = (String) listaOfertasLaborales.getSelectedItem();
            	 String postulante = (String) listaPostulantes.getSelectedItem();
            	 String cv = cvReducido.getText();
            	 String mot = motivacion.getText();
            	 String fecha = fechaPostulacion.getText();
            	 try {
            		 ContO.PostulacionOfertaLaboral(oferta, postulante, cv, mot, fecha);
            		 JOptionPane.showMessageDialog(null, "Postulación registrada con éxito", "Postulación registrada", JOptionPane.INFORMATION_MESSAGE);
            		 limpiarCampos();
            		 setVisible(false);
            	 } catch (UsuarioNoExisteException e1) {
            		 JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            	 }
             }
             }
		});
			
		panel_1.add(btnRegistrar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
				setVisible(false);
			}

		});
		panel_1.add(btnCancelar);





	}

	private void limpiarCampos() {
		listaEmpresas.removeAllItems();
		listaOfertasLaborales.removeAllItems();
		listaPostulantes.removeAllItems();
		cvReducido.setText("");
		motivacion.setText("");
		fechaPostulacion.setText("");
		detalleOfertaLaboral.setText("");
      

	}
	
	private boolean checkFields() {
		if (cvReducido.getText().equals("") || motivacion.getText().equals("") || fechaPostulacion.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Postulacion",
							JOptionPane.ERROR_MESSAGE);

					return false;
				} else {

					return true;
				}
		}
	}

