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

import Logica.ContUsuario;
import Logica.IContOferta;
import Logica.IContUsuario;
import java.util.Set;
import Excepciones.OfertaRepetidaException;
import Excepciones.TOfertaNoExiste;

public class VerificarOfertaLaboral extends JInternalFrame {
	private JButton btnAceptar, btnCancelar;
	private JRadioButton rdbtnConfirmar, rdbtnRechazar;
	private JComboBox<String> comboEmpresa, comboOferta;
	private JLabel lblEmpresa, lblOferta;

	public VerificarOfertaLaboral(IContOferta contOferta, IContUsuario contUsuario) {
		  setResizable(true);
	        setMaximizable(true);
	        setTitle("Verificar Oferta Laboral");
	        setBounds(100, 100, 640, 480);

	        GridBagLayout gridBagLayout = new GridBagLayout();
	        gridBagLayout.rowHeights = new int[] {30, 30, 30, 30, 30, 30, 30, 40};
	        gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0};
	        gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0};
	        getContentPane().setLayout(gridBagLayout);
	        
	        lblEmpresa = new JLabel("Empresa: ");
	        GridBagConstraints gbc_lblEmpresa = new GridBagConstraints();
	        gbc_lblEmpresa.fill = GridBagConstraints.HORIZONTAL;
	        gbc_lblEmpresa.insets = new Insets(5, 5, 5, 5);
	        gbc_lblEmpresa.gridx = 1;
	        gbc_lblEmpresa.gridy = 1;
	        getContentPane().add(lblEmpresa, gbc_lblEmpresa);
	        
	        comboEmpresa = new JComboBox<String>();
	        GridBagConstraints gbc_comboEmpresa = new GridBagConstraints();
	        gbc_comboEmpresa.fill = GridBagConstraints.BOTH;
	        gbc_comboEmpresa.gridwidth = 3;
	        gbc_comboEmpresa.insets = new Insets(5, 5, 5, 5);
	        gbc_comboEmpresa.gridx = 2;
	        gbc_comboEmpresa.gridy = 1;
	        getContentPane().add(comboEmpresa, gbc_comboEmpresa);
	        
	        lblOferta = new JLabel("Oferta: ");
	        GridBagConstraints gbc_lblOferta = new GridBagConstraints();
	        gbc_lblOferta.fill = GridBagConstraints.HORIZONTAL;
	        gbc_lblOferta.insets = new Insets(5, 5, 5, 5);
	        gbc_lblOferta.gridx = 1;
	        gbc_lblOferta.gridy = 3;
	        getContentPane().add(lblOferta, gbc_lblOferta);
	        
	        comboOferta = new JComboBox<String>();
	        GridBagConstraints gbc_comboOferta = new GridBagConstraints();
	        gbc_comboOferta.fill = GridBagConstraints.BOTH;
	        gbc_comboOferta.gridwidth = 3;
	        gbc_comboOferta.insets = new Insets(5, 5, 5, 5);
	        gbc_comboOferta.gridx = 2;
	        gbc_comboOferta.gridy = 3;
	        getContentPane().add(comboOferta, gbc_comboOferta);
	        
	        rdbtnConfirmar = new JRadioButton("Confirmar");
	        GridBagConstraints gbc_rdbtnConfirmar = new GridBagConstraints();
	        gbc_rdbtnConfirmar.insets = new Insets(5, 5, 5, 5);
	        gbc_rdbtnConfirmar.fill = GridBagConstraints.VERTICAL;
	        gbc_rdbtnConfirmar.gridx = 2;
	        gbc_rdbtnConfirmar.gridy = 5;
	        getContentPane().add(rdbtnConfirmar, gbc_rdbtnConfirmar);
	        
	        btnAceptar = new JButton("Aceptar");
	        btnAceptar.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        	}
	        });
	        
	        rdbtnRechazar = new JRadioButton("Rechazar");
	        GridBagConstraints gbc_rdbtnRechazar = new GridBagConstraints();
	        gbc_rdbtnRechazar.insets = new Insets(5, 5, 5, 5);
	        gbc_rdbtnRechazar.fill = GridBagConstraints.VERTICAL;
	        gbc_rdbtnRechazar.gridx = 3;
	        gbc_rdbtnRechazar.gridy = 5;
	        getContentPane().add(rdbtnRechazar, gbc_rdbtnRechazar);
	        GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
	        gbc_btnAceptar.fill = GridBagConstraints.BOTH;
	        gbc_btnAceptar.insets = new Insets(5, 5, 5, 5);
	        gbc_btnAceptar.gridx = 3;
	        gbc_btnAceptar.gridy = 7;
	        getContentPane().add(btnAceptar, gbc_btnAceptar);
	        
	        btnCancelar = new JButton("Cancelar");
	        GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
	        gbc_btnCancelar.insets = new Insets(5, 5, 5, 5);
	        gbc_btnCancelar.fill = GridBagConstraints.BOTH;
	        gbc_btnCancelar.gridx = 4;
	        gbc_btnCancelar.gridy = 7;
	        getContentPane().add(btnCancelar, gbc_btnCancelar);
	        
	        ButtonGroup g = new ButtonGroup();
	        g.add(rdbtnRechazar);
	        g.add(rdbtnConfirmar);
	        
	        btnCancelar.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	           /* 	if (listaUsuarios.getSelectedIndex() != -1) {
	            	resetCampos((String) listaUsuarios.getSelectedItem(), controladorUsuario);
	            } */           	
	            	setVisible(false);
	}
	        });
	        
	        comboEmpresa.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent arg0) {

					Vector<String> empresas = (Vector<String>) contOferta.listarEmpresas();
					mostrarEmpresas(empresas);
				}
				public void focusLost(FocusEvent arg0) {
				}
			});
	        
	        comboOferta.addFocusListener(new FocusListener() {
	            @Override
	            public void focusLost(FocusEvent e) {
	            }
	            @Override
	            public void focusGained(FocusEvent e) {
	                comboOferta.removeAllItems();
	                String empresa = (String) comboEmpresa.getSelectedItem();

	                for (String of : contUsuario.getNombresOfertasIngresadas(empresa)) {
	                    comboOferta.addItem(of);
	                }
	            }
	        });
	        
	        btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed( ActionEvent e ){
					if (comboEmpresa.getSelectedIndex() == -1 || comboOferta.getSelectedIndex() == -1 || g.getSelection() == null) {
						JOptionPane.showMessageDialog(null, "No puede haber campos vacios", "Verificar Oferta Laboral",
								JOptionPane.ERROR_MESSAGE);
					} else {
					if (rdbtnConfirmar.isSelected()) {
						boolean aux = true;
						try {
							contOferta.verificarOferta(((String)comboOferta.getSelectedItem()), aux, LocalDate.now());
							JOptionPane.showMessageDialog(null, "Aceptado", "Verificar Oferta Laboral",
									JOptionPane.INFORMATION_MESSAGE);
							setVisible(false);
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "Verificar Oferta Laboral",
									JOptionPane.ERROR_MESSAGE);
						}
					} else if (rdbtnRechazar.isSelected()){
						boolean aux = false;
						try {
							contOferta.verificarOferta(((String)comboOferta.getSelectedItem()), aux, LocalDate.now());
							JOptionPane.showMessageDialog(null, "Rechazado", "Verificar Oferta Laboral",
									JOptionPane.INFORMATION_MESSAGE);
							setVisible(false);
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "Verificar Oferta Laboral",
									JOptionPane.ERROR_MESSAGE);
						}
					}	
					}
					 
				}
				
			});
	}
	
	public void mostrarEmpresas(Vector<String> nombresEmpresas) {
		comboEmpresa.removeAllItems();
		for (String empresa : nombresEmpresas) {
			comboEmpresa.addItem(empresa);
		}}
	
}
