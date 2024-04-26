package ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import Logica.Fabrica;
import Logica.IContUsuario;
import Logica.ContUsuario;
import Logica.Usuario;
import excepciones.UsuarioNoExisteException;

public class ModificarDatosUsuario extends JInternalFrame {

    private JComboBox<String> listaUsuarios;
    private JTextField campoNombre, campoApellido, campoNickname, campoEmail;
    private JButton btnCancelar, btnAceptar;
    
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
        
        addComponent(btnCancelar, 0, 5);
        btnAceptar = new JButton("Aceptar");
        addComponent(btnAceptar, 1, 5);
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
        } catch (UsuarioNoExisteException e) {
           
            JOptionPane.showMessageDialog(this, "Error al obtener información del usuario", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarDatosUsuario(IContUsuario controladorUsuario ) {
        String nickname = campoNickname.getText();
        String nombre = campoNombre.getText();
        String apellido = campoApellido.getText();
        try {
            controladorUsuario.modificarUsuario(nickname, nombre, apellido);
        } catch(Exception ERROR) {
            System.out.println(ERROR);
        }
        JOptionPane.showMessageDialog(this, "Usuario actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
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

