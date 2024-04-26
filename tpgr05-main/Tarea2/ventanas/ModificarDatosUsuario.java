package ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import Logica.Fabrica;
import Logica.IContUsuario;
import Logica.Postulante;
import Logica.ContUsuario;
import Logica.Empresa;
import Logica.Usuario;
import excepciones.UsuarioNoExisteException;

public class ModificarDatosUsuario extends JInternalFrame {

    private JComboBox<String> listaUsuarios;
    private JTextField campoNombre, campoApellido, campoNickname, campoEmail, campoContraseña, campoFoto, campoFecha, campoNacionalidad, campoEmpresa, campoDescripcion, campoLink;
    private JButton btnCancelar, btnAceptar;
    private JLabel lblFecha,lblNacionalidad, lblEmpresa, lblDescripcion, lblLink;
    
    public ModificarDatosUsuario(IContUsuario controladorUsuario) {
        setResizable(true);
        setMaximizable(true);
        setTitle("Modificar Datos del Usuario");
        setBounds(100, 100, 640, 480);

        GridBagLayout gridBagLayout = new GridBagLayout();
        getContentPane().setLayout(gridBagLayout);

        // Add components in a grid layout fashion
        addComponent(new JLabel("Usuario:"), 0, 0);
        listaUsuarios = new JComboBox<>();
        addComponent(listaUsuarios, 1, 0);
        
        listaUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDatosUsuario((String) listaUsuarios.getSelectedItem(),controladorUsuario);
                try {
					if (controladorUsuario.getUsuario((String) listaUsuarios.getSelectedItem()).getClass() == Postulante.class) {
						campoFecha.setVisible(true);
						campoNacionalidad.setVisible(true);
						lblFecha.setVisible(true);
						lblNacionalidad.setVisible(true);
						campoEmpresa.setVisible(false);
						campoDescripcion.setVisible(false);
						campoLink.setVisible(false);
						lblEmpresa.setVisible(false);
						lblDescripcion.setVisible(false);
						lblLink.setVisible(false);
					} else {
						campoFecha.setVisible(false);
						campoNacionalidad.setVisible(false);
						lblFecha.setVisible(false);
						lblNacionalidad.setVisible(false);
						campoEmpresa.setVisible(true);
						campoDescripcion.setVisible(true);
						campoLink.setVisible(true);
						lblEmpresa.setVisible(true);
						lblDescripcion.setVisible(true);
						lblLink.setVisible(true);
						
					}
				} catch (UsuarioNoExisteException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Modificar Usuario",
					JOptionPane.ERROR_MESSAGE);
				}
            }
        });
        
        addComponent(new JLabel("Nombre:"), 0, 1);
        addComponent(campoNombre = new JTextField(), 1, 1);

        addComponent(new JLabel("Apellido:"), 0, 2);
        addComponent(campoApellido = new JTextField(), 1, 2);

        addComponent(new JLabel("Nickname:"), 0, 3);
        addComponent(campoNickname = new JTextField(), 1, 3);
        campoNickname.setEditable(false);

        addComponent(new JLabel("Email:"), 0, 4);
        addComponent(campoEmail = new JTextField(), 1, 4);
        campoEmail.setEditable(false);
        
        addComponent(new JLabel("Contraseña:"), 0, 5);
        addComponent(campoContraseña = new JTextField(), 1, 5);
        campoContraseña.setEditable(true);
        
        addComponent(new JLabel("Foto:"), 0, 6);
        addComponent(campoFoto = new JTextField(), 1, 6);
        campoFoto.setEditable(true);
        
        addComponent(lblFecha = new JLabel("Fecha:"), 0, 7);
        addComponent(campoFecha = new JTextField(), 1, 7);
        campoFecha.setEditable(true);
        lblFecha.setVisible(false);
        campoFecha.setVisible(false);
        
        addComponent(lblNacionalidad = new JLabel("Nacionalidad:"), 0, 8);
        addComponent(campoNacionalidad = new JTextField(), 1, 8);
        campoNacionalidad.setEditable(true);
        lblNacionalidad.setVisible(false);
        campoNacionalidad.setVisible(false);
        
        addComponent(lblEmpresa = new JLabel("Empresa:"), 0, 7);
        addComponent(campoEmpresa = new JTextField(), 1, 7);
        campoEmpresa.setEditable(true);
        lblEmpresa.setVisible(false);
        campoEmpresa.setVisible(false);
        
        addComponent(lblDescripcion = new JLabel("Descripcion:"), 0, 8);
        addComponent(campoDescripcion = new JTextField(), 1, 8);
        campoDescripcion.setEditable(true);
        lblDescripcion.setVisible(false);
        campoDescripcion.setVisible(false);
        
        addComponent(lblLink = new JLabel("Link:"), 0, 9);
        addComponent(campoLink = new JTextField(), 1, 9);
        campoLink.setEditable(true);
        lblLink.setVisible(false);
        campoLink.setVisible(false);
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (listaUsuarios.getSelectedIndex() != -1) {
            	resetCampos((String) listaUsuarios.getSelectedItem(), controladorUsuario);
            }            	
            	setVisible(false);
}
        });
        
        addComponent(btnCancelar, 0, 10);
        btnAceptar = new JButton("Aceptar");
        addComponent(btnAceptar, 1, 10);
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarDatosUsuario(controladorUsuario);
            }
        });

       

        this.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameActivated(InternalFrameEvent e) {
                actualizarListaUsuarios(controladorUsuario);
            }
        });
    }

    private void addComponent(Component comp, int x, int y) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = x;
        c.gridy = y;
        c.insets = new Insets(5, 5, 5, 5);
        if (comp instanceof JTextField || comp instanceof JComboBox) {
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 0.5;
        }
        getContentPane().add(comp, c);
    }

    private void mostrarDatosUsuario(String nickname,IContUsuario controladorUsuario) {
        try {
            if(nickname == null) return;
            Usuario usuario = controladorUsuario.getUsuario(nickname);
            campoNombre.setText(usuario.getNombre());
            campoApellido.setText(usuario.getApellido());
            campoNickname.setText(usuario.getNickname());
            campoEmail.setText(usuario.getEmail());
            campoContraseña.setText(usuario.getCon());
            campoFoto.setText(usuario.getFoto());
            if (controladorUsuario.getUsuario(nickname).getClass() == Postulante.class) {
            	Postulante aux1 = (Postulante) controladorUsuario.getUsuario(nickname);
            	campoFecha.setText(aux1.getFechaNac().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString());
            	campoNacionalidad.setText(aux1.getNacionalidad());
            } else {
            	Empresa aux2 = (Empresa) controladorUsuario.getUsuario(nickname);
            	campoEmpresa.setText(aux2.getEmpresa());
            	campoDescripcion.setText(aux2.getDescripcion());
            	campoLink.setText(aux2.getLink());
            }
        } catch (UsuarioNoExisteException e) {
           
            JOptionPane.showMessageDialog(this, "Error al obtener información del usuario", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarDatosUsuario(IContUsuario controladorUsuario ) {
        String nickname = campoNickname.getText();
        String nombre = campoNombre.getText();
        String apellido = campoApellido.getText();
        String contraseña = campoContraseña.getText();
        String foto = campoFoto.getText();
        String fecha = campoFecha.getText();
        String nacionalidad = campoNacionalidad.getText();
        String empresa = campoEmpresa.getText();
        String descripcion = campoDescripcion.getText();
        String link = campoLink.getText();
        try {
			if (controladorUsuario.getUsuario(nickname).getClass() == Postulante.class) {
				try {
			        controladorUsuario.modificarPostulante(nickname, nombre, apellido, contraseña, foto, fecha, nacionalidad);
				    JOptionPane.showMessageDialog(this, "Usuario actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			    } catch(Exception ERROR) {
			    	JOptionPane.showMessageDialog(null, ERROR.getMessage(), "Modificar Usuario",
							JOptionPane.ERROR_MESSAGE);
			    }
			} else {
				try {
			        controladorUsuario.modificarEmpresa(nickname, nombre, apellido, contraseña, foto, empresa, descripcion, link);
				    JOptionPane.showMessageDialog(this, "Usuario actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			    } catch(Exception ERROR) {
			    	JOptionPane.showMessageDialog(null, ERROR.getMessage(), "Modificar Usuario",
							JOptionPane.ERROR_MESSAGE);
			    }
			}
		} catch (HeadlessException | UsuarioNoExisteException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Modificar Usuario",
					JOptionPane.ERROR_MESSAGE);
		}        
    }

    private void actualizarListaUsuarios(IContUsuario controladorUsuario) {
        listaUsuarios.removeAllItems();
        for (String nickname : controladorUsuario.listarUsuarios()) {
            listaUsuarios.addItem(nickname);
        }
    }
    
    private void resetCampos(String nickname,IContUsuario controladorUsuario) {
        try {
            Usuario usuario = controladorUsuario.getUsuario(nickname);
            campoNombre.setText(usuario.getNombre());
            campoApellido.setText(usuario.getApellido());
        } catch (UsuarioNoExisteException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al obtener información del usuario", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

