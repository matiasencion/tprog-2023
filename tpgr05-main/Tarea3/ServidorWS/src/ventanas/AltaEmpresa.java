package ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Logica.IContUsuario;
import types.DTUsuario;

public class AltaEmpresa extends JInternalFrame {

    private JTextField campoNickname, campoNombre, campoApellido, campoEmail, campoContrasena, campoRepetirContrasena, campoFoto, campoSitioWeb, campoNombreEmpresa,
            campoDescripcion;
    private JButton btnRegistrar, btnCancel;

    public AltaEmpresa(IContUsuario ContU) {
    	setResizable(true);
    	setMaximizable(true);
        setTitle("Alta de Empresa");
        setBounds(100, 100, 640, 480);

        GridBagLayout gridBagLayout = new GridBagLayout();
        getContentPane().setLayout(gridBagLayout);

        // Add components
        addComponent(new JLabel("Nickname:"), 0, 0);
        addComponent(campoNickname = new JTextField(), 1, 0);

        addComponent(new JLabel("Nombre:"), 0, 1);
        addComponent(campoNombre = new JTextField(), 1, 1);

        addComponent(new JLabel("Apellido:"), 0, 2);
        addComponent(campoApellido = new JTextField(), 1, 2);

        addComponent(new JLabel("Correo Electronico:"), 0, 3);
        addComponent(campoEmail = new JTextField(), 1, 3);
        
        addComponent(new JLabel("Contrasena:"), 0, 4);
        addComponent(campoContrasena = new JTextField(), 1, 4);
        
        addComponent(new JLabel("Repetir contrasena:"), 0, 5);
        addComponent(campoRepetirContrasena = new JTextField(), 1, 5);
        
        addComponent(new JLabel("URL de la foto (Opcional):"), 0, 6);
        addComponent(campoFoto = new JTextField(), 1, 6);

        addComponent(new JLabel("Nombre de la Empresa:"), 0, 7);
        addComponent(campoNombreEmpresa = new JTextField(), 1, 7);

        addComponent(new JLabel("Descripcion:"), 0, 8);
        addComponent(campoDescripcion = new JTextField(), 1, 8);

        addComponent(new JLabel("Sitio Web (Opcional):"), 0, 9);
        addComponent(campoSitioWeb = new JTextField(), 1, 9);

        addComponent(btnRegistrar = new JButton("Registrar Empresa"), 0, 10);
        addComponent(btnCancel = new JButton("Cancelar"), 1, 10);

        btnRegistrar.addActionListener(e -> {
            if (campoNickname.getText().isEmpty() || campoNombre.getText().isEmpty()
                    || campoApellido.getText().isEmpty() || campoEmail.getText().isEmpty() || campoContrasena.getText().isEmpty() || campoRepetirContrasena.getText().isEmpty()
                    || campoNombreEmpresa.getText().isEmpty() || campoDescripcion.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe completar todos los campos", "Registrar Empresa",
                        JOptionPane.ERROR_MESSAGE);
            } else {
            	if (!campoContrasena.getText().equals(campoRepetirContrasena.getText())) {
            		JOptionPane.showMessageDialog(null, "Las contrasenas no coinciden", "Registrar Empresa",
                            JOptionPane.ERROR_MESSAGE);
            		campoContrasena.setText("");
            		campoRepetirContrasena.setText("");
            	} else {
	            	try {
	                    ContU.altaEmpresa(campoNickname.getText().toLowerCase(), campoNombre.getText(), campoApellido.getText(),
	                            campoEmail.getText().toLowerCase(),campoContrasena.getText(), campoFoto.getText(), campoNombreEmpresa.getText(),
	                            campoDescripcion.getText(), campoSitioWeb.getText());
	
	                    for (DTUsuario u : ContU.getUsuarios()) {
	                        System.out.println(u.getNickname());
	                    }
	
	                    JOptionPane.showMessageDialog(null, "Empresa registrada con exito", "Registrar Empresa",
	                            JOptionPane.INFORMATION_MESSAGE);
	                    limpiarCampos();
	                    setVisible(false);
	
	                } catch (Exception e1) {
	                    JOptionPane.showMessageDialog(null, e1.getMessage(), "Registrar Empresa",
	                            JOptionPane.ERROR_MESSAGE);
	                }	
            	}
                
            }
        });

        btnCancel.addActionListener(e -> {
            limpiarCampos();
            setVisible(false);
        });
    }

    private void addComponent(Component comp, int x, int y) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = x;
        c.gridy = y;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5, 5);
        c.weightx = 0.5;
        getContentPane().add(comp, c);
    }

    private void limpiarCampos() {
        campoNickname.setText("");
        campoNombre.setText("");
        campoApellido.setText("");
        campoEmail.setText("");
        campoContrasena.setText("");
        campoRepetirContrasena.setText("");
        campoFoto.setText("");
        campoNombreEmpresa.setText("");
        campoDescripcion.setText("");
        campoSitioWeb.setText("");
    }
}

