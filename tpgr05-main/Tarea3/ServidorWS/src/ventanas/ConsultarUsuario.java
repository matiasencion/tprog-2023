package ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Logica.IContOferta;
import Logica.IContUsuario;
import Excepciones.OfertaNoExisteException;

import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.util.Vector;

public class ConsultarUsuario extends JInternalFrame {
    private JPanel body;
    private JLabel lblNewLabel;
    private JComboBox listaUsuarios;
    private JLabel lblNewLabel_1;
    private JTextArea areaInformacionUsuario;
    private JLabel lblNewLabel_4;
    private JComboBox listarOfertas;
    private JLabel lblNewLabel_6;
    private JTextArea areaInformacionOferta;
    private JButton btnNewButton;
    private JButton btnNewButtonCancelar;
    private JScrollPane scrollPane;

    public ConsultarUsuario(IContUsuario contU, IContOferta ContO ,ConsultaOfertaLaboral consultaOferta) {
    	setResizable(true);
    	setMaximizable(true);

        setTitle("Consulta de Usuario");
        setBounds(100, 100, 640, 540);
        getContentPane().setLayout(new BorderLayout(0, 0));

        body = new JPanel();
        getContentPane().add(body, BorderLayout.CENTER);
        GridBagLayout gbl_body = new GridBagLayout();
        gbl_body.columnWidths = new int[] {120, 140};
        gbl_body.rowHeights = new int[] {30, 150, 30, 150, 30, 0};
        gbl_body.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
        gbl_body.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
        body.setLayout(gbl_body);

        lblNewLabel = new JLabel("Usuarios");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 0;
        body.add(lblNewLabel, gbc_lblNewLabel);

        listaUsuarios = new JComboBox();

        listaUsuarios.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                Vector<String> usuarios = (Vector<String>) contU.listarUsuarios();
                mostrarUsuarios(usuarios);
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });

        listaUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String usuario = (String) listaUsuarios.getSelectedItem();
                if (usuario != null) {
                    String info = contU.infoUsuario(usuario);
                    areaInformacionUsuario.setText(info);
                }

            }
        });
        GridBagConstraints gbc_listaUsuarios = new GridBagConstraints();
        gbc_listaUsuarios.gridwidth = 2;
        gbc_listaUsuarios.fill = GridBagConstraints.BOTH;
        gbc_listaUsuarios.insets = new Insets(0, 0, 5, 0);
        gbc_listaUsuarios.gridx = 1;
        gbc_listaUsuarios.gridy = 0;
        body.add(listaUsuarios, gbc_listaUsuarios);

        lblNewLabel_1 = new JLabel("Datos Usuarios");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 0;
        gbc_lblNewLabel_1.gridy = 1;
        body.add(lblNewLabel_1, gbc_lblNewLabel_1);

        areaInformacionUsuario = new JTextArea();
        areaInformacionUsuario.setEditable(false);
        areaInformacionUsuario.setLineWrap(true);
        GridBagConstraints gbc_areaInformacionUsuario = new GridBagConstraints();
        gbc_areaInformacionUsuario.gridwidth = 2;
        gbc_areaInformacionUsuario.insets = new Insets(0, 0, 5, 0);
        gbc_areaInformacionUsuario.fill = GridBagConstraints.BOTH;
        gbc_areaInformacionUsuario.gridx = 1;
        gbc_areaInformacionUsuario.gridy = 1;
        body.add(areaInformacionUsuario, gbc_areaInformacionUsuario);

        lblNewLabel_4 = new JLabel("Ofertas Laborales");
        GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
        gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_4.gridx = 0;
        gbc_lblNewLabel_4.gridy = 2;
        body.add(lblNewLabel_4, gbc_lblNewLabel_4);

        listarOfertas = new JComboBox();
        listarOfertas.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                  listarOfertas.removeAllItems();
                  String nickUsuario = (String) listaUsuarios.getSelectedItem();
                Vector<String> ofertas = (Vector<String>) contU.getNombresOfertas(nickUsuario);
                for (String o : ofertas) {
                    listarOfertas.addItem(o);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });

        GridBagConstraints gbc_listarOfertas = new GridBagConstraints();
        gbc_listarOfertas.insets = new Insets(0, 0, 5, 0);
        gbc_listarOfertas.gridwidth = 2;
        gbc_listarOfertas.fill = GridBagConstraints.HORIZONTAL;
        gbc_listarOfertas.gridx = 1;
        gbc_listarOfertas.gridy = 2;
        body.add(listarOfertas, gbc_listarOfertas);

        lblNewLabel_6 = new JLabel("Datos Oferta");
        GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
        gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_6.gridx = 0;
        gbc_lblNewLabel_6.gridy = 3;
        body.add(lblNewLabel_6, gbc_lblNewLabel_6);

        areaInformacionOferta = new JTextArea();
        areaInformacionOferta.setEditable(false);
        areaInformacionOferta.setLineWrap(true);
        GridBagConstraints gbc_areaInformacionOferta = new GridBagConstraints();
        gbc_areaInformacionOferta.gridwidth = 2;
        gbc_areaInformacionOferta.insets = new Insets(0, 0, 5, 0);
        gbc_areaInformacionOferta.fill = GridBagConstraints.BOTH;
        gbc_areaInformacionOferta.gridx = 1;
        gbc_areaInformacionOferta.gridy = 3;
        body.add(areaInformacionOferta, gbc_areaInformacionOferta);
        
        listarOfertas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {

                 String oferta = (String) listarOfertas.getSelectedItem();
                    if (oferta != null) {
                        
                        String info = null;
												try {
													info = ContO.getInfoOfertaBasico(oferta);
												} catch (OfertaNoExisteException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
                        areaInformacionOferta.setText(info);
                    }
        		

        	}

        });

        btnNewButton = new JButton("Consultar Oferta Laboral");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            		String oferta = (String) listarOfertas.getSelectedItem();
                
                

                if (oferta != null) {
                	String empresa = null;
									try {
										empresa = ContO.getEmpresaOferta(oferta);
									} catch (OfertaNoExisteException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
                    String info = null;
										try {
											info = ContO.getInfoOfertaString(oferta);
										} catch (OfertaNoExisteException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
                    consultaOferta.setOferta(oferta,empresa,info, contU);
                    consultaOferta.setVisible(true); 
                } 

            }
        });
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
        gbc_btnNewButton.gridx = 2;
        gbc_btnNewButton.gridy = 4;
        body.add(btnNewButton, gbc_btnNewButton);
        
        btnNewButtonCancelar = new JButton("Cancelar");
        btnNewButtonCancelar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {

        		limpiarCampos();
        		setVisible(false);
        	}
        });
        btnNewButtonCancelar.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_btnNewButtonCancelar = new GridBagConstraints();
        gbc_btnNewButtonCancelar.gridx = 2;
        gbc_btnNewButtonCancelar.gridy = 5;
        body.add(btnNewButtonCancelar, gbc_btnNewButtonCancelar);
        
    }

    private void limpiarCampos() {
        listaUsuarios.setSelectedIndex(-1);
        listarOfertas.setSelectedIndex(-1);
        areaInformacionUsuario.setText("");
        areaInformacionOferta.setText("");

    }

    public void mostrarUsuarios(Vector<String> usuarios) {
        listaUsuarios.removeAllItems();
        for (String u : usuarios) {
            listaUsuarios.addItem(u);

        }
    }





}