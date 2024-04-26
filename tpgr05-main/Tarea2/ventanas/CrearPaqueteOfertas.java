package ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Logica.IContOferta;
import excepciones.TOfertaRepetidaException;

public class CrearPaqueteOfertas extends JInternalFrame {

    private JTextField txtNombre, txtDescripcion, txtPeriodoValidez, txtDescuento, txtFechaAlta, txtFoto;
    private JButton btnRegistrar, btnCancelar;

    public CrearPaqueteOfertas( IContOferta ContO) {
    	setResizable(true);
    	setMaximizable(true);
        setTitle("Crear Paquete de Tipos de Publicación de Ofertas Laborales");
        setBounds(100, 100, 640, 480);

        getContentPane().setLayout(new GridLayout(9, 2, 10, 10)); // 9 filas, 2 columnas y separación de 10 entre ellas

        getContentPane().add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        getContentPane().add(txtNombre);

        getContentPane().add(new JLabel("Descripción:"));
        txtDescripcion = new JTextField();
        getContentPane().add(txtDescripcion);

        getContentPane().add(new JLabel("Período de Validez (días):"));
        txtPeriodoValidez = new JTextField();
        getContentPane().add(txtPeriodoValidez);

        getContentPane().add(new JLabel("Descuento (%):"));
        txtDescuento = new JTextField();
        getContentPane().add(txtDescuento);

        getContentPane().add(new JLabel("Fecha de Alta: dd/mm/aa"));
        txtFechaAlta = new JTextField();
        txtFechaAlta.setToolTipText("dd/mm/aa");
        getContentPane().add(txtFechaAlta);
        
        getContentPane().add(new JLabel("Foto"));
        txtFoto = new JTextField();
        getContentPane().add(txtFoto);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (checkFields()) {
               try{
                ContO.agregarPaquete(txtNombre.getText(), txtDescripcion.getText(), txtPeriodoValidez.getText(), txtDescuento.getText(), txtFechaAlta.getText(), txtFoto.getText());
                JOptionPane.showMessageDialog(null, "Paquete registrado con éxito", "Crear Paquete",
                JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
                clearFields();
               }
                catch(TOfertaRepetidaException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Crear Paquete",
                    JOptionPane.ERROR_MESSAGE);
                    txtNombre.setText("");
                }}
             
            }
        });
        getContentPane().add(btnRegistrar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
                setVisible(false);
            }
        });
        getContentPane().add(btnCancelar);

        this.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
                clearFields();
            }
        });
    }

    private void clearFields() {
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtPeriodoValidez.setText("");
        txtDescuento.setText("");
        txtFechaAlta.setText("");
    }

    private boolean checkFields() {
		if (txtNombre.getText().equals("") || txtDescripcion.getText().equals("")
				|| txtDescuento.getText().equals("") || txtPeriodoValidez.getText().equals("")
				|| txtFechaAlta.getText().equals("")) {

			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Alta Paquete",
					JOptionPane.ERROR_MESSAGE);

			return false;
		} else {

			return true;
		}
	}
    
}


