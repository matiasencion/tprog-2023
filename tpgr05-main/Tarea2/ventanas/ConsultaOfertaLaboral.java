package ventanas;

import javax.swing.*;

import Logica.IContOferta;
import Logica.IContUsuario;
import excepciones.OfertaNoExisteException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Vector;

public class ConsultaOfertaLaboral extends JInternalFrame {

	private JComboBox<String> listaEmpresas, listaOfertasLaborales;
	private JButton btnCancelar;
	private JScrollPane scrollPane;
	private JTextArea detalleOfertaLaboral;

	public ConsultaOfertaLaboral(IContUsuario ContU, IContOferta ContO) {
		setResizable(true);
		setMaximizable(true);
		setTitle("Consulta de Oferta Laboral");
		setBounds(100, 100, 640, 480);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {120, 400};
		gridBagLayout.rowHeights = new int[] {40, 45, 45, 250, 45, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		 
		 scrollPane = new JScrollPane();
		 GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		 gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		 gbc_scrollPane.fill = GridBagConstraints.BOTH;
		 gbc_scrollPane.gridx = 1;
		 gbc_scrollPane.gridy = 3;
		 getContentPane().add(scrollPane, gbc_scrollPane);
		 
		 detalleOfertaLaboral = new JTextArea();
		 detalleOfertaLaboral.setLineWrap(true);
		 detalleOfertaLaboral.setEditable(false);
		 scrollPane.setViewportView(detalleOfertaLaboral);

		JLabel lblEmpresas = new JLabel("Empresas:");
		GridBagConstraints gbc_lblEmpresas = new GridBagConstraints();
		gbc_lblEmpresas.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmpresas.gridx = 0;
		gbc_lblEmpresas.gridy = 1;
		getContentPane().add(lblEmpresas, gbc_lblEmpresas);

		listaEmpresas = new JComboBox<String>();
		GridBagConstraints gbc_listaEmpresas = new GridBagConstraints();
		gbc_listaEmpresas.fill = GridBagConstraints.BOTH;
		gbc_listaEmpresas.insets = new Insets(0, 0, 5, 5);
		gbc_listaEmpresas.gridx = 1;
		gbc_listaEmpresas.gridy = 1;
		getContentPane().add(listaEmpresas, gbc_listaEmpresas);
		listaEmpresas.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
			}
			@Override
			public void focusGained(FocusEvent e) {
				listaEmpresas.removeAllItems();

				for (String em : ContU.getNombresEmpresas()) {
					listaEmpresas.addItem(em);
				}
			}
		});


		
		JLabel lblOfertasLaborales = new JLabel("Ofertas Laborales:");
		GridBagConstraints gbc_lblOfertasLaborales = new GridBagConstraints();
		gbc_lblOfertasLaborales.insets = new Insets(0, 0, 5, 5);
		gbc_lblOfertasLaborales.gridx = 0;
		gbc_lblOfertasLaborales.gridy = 2;
		getContentPane().add(lblOfertasLaborales, gbc_lblOfertasLaborales);

		listaOfertasLaborales = new JComboBox<String>();
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
		gbc_listaOfertasLaborales.fill = GridBagConstraints.BOTH;
		gbc_listaOfertasLaborales.insets = new Insets(0, 0, 5, 5);
		gbc_listaOfertasLaborales.gridx = 1;
		gbc_listaOfertasLaborales.gridy = 2;
		getContentPane().add(listaOfertasLaborales, gbc_listaOfertasLaborales);

        listaOfertasLaborales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

             String oferta = (String) listaOfertasLaborales.getSelectedItem();
                    if (oferta != null) {
                        
                        String info = null;
												try {
													info = ContO.getInfoOfertaString(oferta);
												} catch (OfertaNoExisteException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
                        try {
													info = info + "Postulantes:" + "\n" + ContO.getInfoPostulantesString(oferta);
												} catch (OfertaNoExisteException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
                        detalleOfertaLaboral.setText(info);
                        
             
            }
        } }) ;

		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				limpiarCampos();
				setVisible(false);
			}
		});

		JLabel lblDetalleOferta = new JLabel("Detalle Oferta:");
		GridBagConstraints gbc_lblDetalleOferta = new GridBagConstraints();
		gbc_lblDetalleOferta.insets = new Insets(0, 0, 5, 5);
		gbc_lblDetalleOferta.gridx = 0;
		gbc_lblDetalleOferta.gridy = 3;        
		getContentPane().add(lblDetalleOferta, gbc_lblDetalleOferta);



		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.anchor = GridBagConstraints.EAST;
		gbc_btnCancelar.gridx = 1;
		gbc_btnCancelar.gridy = 4;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
	}

	private void limpiarCampos() {
		listaEmpresas.removeAllItems();
		listaOfertasLaborales.removeAllItems();
		detalleOfertaLaboral.setText("");

	}



	public void setOferta(String oferta,String empresa,String info, IContUsuario ContU) {
		listaOfertasLaborales.removeAllItems();
		
		for (String em : ContU.getNombresEmpresas()) {
			listaEmpresas.addItem(em);
		}
		for (String of : ContU.getNombresOfertas(empresa)) {
			listaOfertasLaborales.addItem(of);
		}

	detalleOfertaLaboral.setText(info); 
    
		listaEmpresas.setSelectedItem(empresa);
		listaOfertasLaborales.setSelectedItem(oferta);
		
	}
}
