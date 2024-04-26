package ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.util.Vector;
import java.awt.event.FocusEvent;
import Logica.IContOferta;
import excepciones.TOfertaRepetidaException;

public class AgregarTipoPublicacionAlPaquete extends JInternalFrame {

    private JComboBox<String> listaPaquetes_1;
    private JComboBox<String> listaTipos;
    private JTextArea areaInformacionTipo;
    private JButton btnNewButton, btnNewButtonCancelar;

    public AgregarTipoPublicacionAlPaquete( IContOferta ContO) {
    	setResizable(true);
    	setMaximizable(true);
        setTitle("Agregar Tipo de Publicación al Paquete");
        setBounds(100, 100, 640, 480);

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.rowHeights = new int[]{311};
        gridBagLayout.rowWeights = new double[]{1.0};
        gridBagLayout.columnWeights = new double[]{1.0};
        getContentPane().setLayout(gridBagLayout);
        
        JPanel body = new JPanel();
        GridBagConstraints gbc_body = new GridBagConstraints();
        gbc_body.fill = GridBagConstraints.BOTH;
        gbc_body.gridx = 0;
        gbc_body.gridy = 0;
        getContentPane().add(body, gbc_body);
        GridBagLayout gbl_body = new GridBagLayout();
        gbl_body.columnWidths = new int[]{209, 241, 0, 0};
        gbl_body.rowHeights = new int[]{30, 52, 30, 150, 30, 47, 0};
        gbl_body.columnWeights = new double[]{1.0, 1.0, 4.9E-324, Double.MIN_VALUE};
        gbl_body.rowWeights = new double[]{1.0, 1.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        body.setLayout(gbl_body);
        
        JLabel lblPaquetes = new JLabel("Paquetes");
        GridBagConstraints gbc_lblPaquetes = new GridBagConstraints();
        gbc_lblPaquetes.fill = GridBagConstraints.VERTICAL;
        gbc_lblPaquetes.insets = new Insets(0, 0, 5, 5);
        gbc_lblPaquetes.gridx = 0;
        gbc_lblPaquetes.gridy = 0;
        body.add(lblPaquetes, gbc_lblPaquetes);
        
         listaPaquetes_1 = new JComboBox();
         listaPaquetes_1.addFocusListener(new FocusListener() {
         	public void focusGained(FocusEvent arg0) {
         		Vector<String> paquetes = (Vector<String>) ContO.listarPaquetes();
         		listaPaquetes_1.removeAllItems();
         		for (String p : paquetes) {
         			listaPaquetes_1.addItem(p);
         		}
         	}
         	public void focusLost(FocusEvent arg0) {
         	}
         });

        GridBagConstraints gbc_listaPaquetes_1 = new GridBagConstraints();
        gbc_listaPaquetes_1.fill = GridBagConstraints.BOTH;
        gbc_listaPaquetes_1.gridwidth = 2;
        gbc_listaPaquetes_1.insets = new Insets(0, 0, 5, 0);
        gbc_listaPaquetes_1.gridx = 1;
        gbc_listaPaquetes_1.gridy = 0;
        body.add(listaPaquetes_1, gbc_listaPaquetes_1);
        
        JLabel lblNewLabel_4 = new JLabel("Tipos de Publicacion del Paquete");
        GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
        gbc_lblNewLabel_4.fill = GridBagConstraints.VERTICAL;
        gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_4.gridx = 0;
        gbc_lblNewLabel_4.gridy = 2;
        body.add(lblNewLabel_4, gbc_lblNewLabel_4);
        
         listaTipos = new JComboBox();

          listaTipos.addFocusListener(new FocusListener() {
          	public void focusGained(FocusEvent arg0) {
          		Vector<String> tipos = (Vector<String>) ContO.getNombreTOfertas();
          		listaTipos.removeAllItems();
          		for (String t : tipos) {
          			listaTipos.addItem(t);
          		}
          	}
          	public void focusLost(FocusEvent arg0) {
          	}
          });
        GridBagConstraints gbc_listaTipos = new GridBagConstraints();
        gbc_listaTipos.fill = GridBagConstraints.BOTH;
        gbc_listaTipos.gridwidth = 2;
        gbc_listaTipos.insets = new Insets(0, 0, 5, 0);
        gbc_listaTipos.gridx = 1;
        gbc_listaTipos.gridy = 2;
        body.add(listaTipos, gbc_listaTipos);
        
        JLabel lblNewLabel_6 = new JLabel("Cantidad");
        GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
        gbc_lblNewLabel_6.fill = GridBagConstraints.VERTICAL;
        gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_6.gridx = 0;
        gbc_lblNewLabel_6.gridy = 4;
        body.add(lblNewLabel_6, gbc_lblNewLabel_6);
        
         areaInformacionTipo = new JTextArea();
        areaInformacionTipo.setLineWrap(true);
        GridBagConstraints gbc_areaInformacionTipo = new GridBagConstraints();
        gbc_areaInformacionTipo.fill = GridBagConstraints.BOTH;
        gbc_areaInformacionTipo.gridwidth = 2;
        gbc_areaInformacionTipo.insets = new Insets(0, 0, 5, 0);
        gbc_areaInformacionTipo.gridx = 1;
        gbc_areaInformacionTipo.gridy = 4;
        body.add(areaInformacionTipo, gbc_areaInformacionTipo);
        
         btnNewButton = new JButton("Agregar");
       btnNewButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	if (checkFields()) {
                    String paquete = (String) listaPaquetes_1.getSelectedItem();
                    String tipo = (String) listaTipos.getSelectedItem();
                    String cantidad = areaInformacionTipo.getText();
                    if (paquete != null && tipo != null && cantidad != null) {
                         
                        try {
							ContO.agregarPTP(paquete, tipo,Integer.parseInt(cantidad));
						} catch (NumberFormatException | TOfertaRepetidaException e1) {
							 JOptionPane.showMessageDialog(null, e1.getMessage(), "Agregar tipo Paquete",
					                    JOptionPane.ERROR_MESSAGE);
						}
                        limpiarCampos();
                    }
                }}
            });

        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.anchor = GridBagConstraints.EAST;
        gbc_btnNewButton.fill = GridBagConstraints.VERTICAL;
        gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
        gbc_btnNewButton.gridx = 1;
        gbc_btnNewButton.gridy = 5;
        body.add(btnNewButton, gbc_btnNewButton);
        
         btnNewButtonCancelar = new JButton("Cancelar");
        btnNewButtonCancelar.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_btnNewButtonCancelar = new GridBagConstraints();
        gbc_btnNewButtonCancelar.fill = GridBagConstraints.BOTH;
        gbc_btnNewButtonCancelar.gridx = 2;
        gbc_btnNewButtonCancelar.gridy = 5;
        body.add(btnNewButtonCancelar, gbc_btnNewButtonCancelar);


   

        btnNewButtonCancelar.addActionListener(e -> {
            limpiarCampos();
            setVisible(false);
        });
    }

 

    private void limpiarCampos() {
        listaPaquetes_1.setSelectedIndex(-1);
        listaTipos.setSelectedIndex(-1);
        areaInformacionTipo.setText("");   
    }

    private boolean checkFields() {
		if (areaInformacionTipo.getText().equals("") || listaPaquetes_1.getSelectedIndex()==-1 || listaTipos.getSelectedIndex()==-1) {

			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Alta Oferta",
					JOptionPane.ERROR_MESSAGE);

			return false;
		} else {

			return true;
		}
	}
  
}


