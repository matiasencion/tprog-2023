package ventanas;

import javax.swing.*;
import java.awt.*;

public class ConsultarPerfilUsuario extends JInternalFrame {

    private JComboBox<String> listaUsuarios;
    private JTextArea detalleUsuario, detalleOfertaLaboral;
    private JComboBox<String> listaOfertasLaborales;
    private JButton btnConsultarOferta;

    public ConsultarPerfilUsuario() {
    	setResizable(true);
    	setMaximizable(true);
        setTitle("Consultar Perfil de Usuario");
        setBounds(100, 100, 640, 480);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] {130, 100, 100};
        gridBagLayout.rowHeights = new int[]{36, 36, 36, 36, 36, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
                
                        GridBagConstraints gbc = new GridBagConstraints();
                        gbc.fill = GridBagConstraints.VERTICAL;
                        gbc.insets = new Insets(0, 0, 5, 5);
                        gbc.gridx = 0;
                        gbc.gridy = 0;
                        JLabel label_1 = new JLabel("Usuarios:");
                        getContentPane().add(label_1, gbc);
                        listaUsuarios = new JComboBox<>();
                        GridBagConstraints gbc_listaUsuarios = new GridBagConstraints();
                        gbc_listaUsuarios.fill = GridBagConstraints.BOTH;
                        gbc_listaUsuarios.insets = new Insets(0, 0, 5, 5);
                        gbc_listaUsuarios.gridx = 1;
                        gbc_listaUsuarios.gridy = 0;
                        getContentPane().add(listaUsuarios, gbc_listaUsuarios);
                                
                                        GridBagConstraints gbc_1 = new GridBagConstraints();
                                        gbc_1.fill = GridBagConstraints.VERTICAL;
                                        gbc_1.insets = new Insets(0, 0, 5, 5);
                                        gbc_1.gridx = 0;
                                        gbc_1.gridy = 1;
                                        JLabel label_2 = new JLabel("Detalles del Usuario:");
                                        getContentPane().add(label_2, gbc_1);
                                detalleUsuario = new JTextArea();
                                detalleUsuario.setEditable(false);
                                JScrollPane scrollDetalles = new JScrollPane(detalleUsuario);
                                GridBagConstraints gbc_scrollDetalles = new GridBagConstraints();
                                gbc_scrollDetalles.fill = GridBagConstraints.BOTH;
                                gbc_scrollDetalles.insets = new Insets(0, 0, 5, 5);
                                gbc_scrollDetalles.gridx = 1;
                                gbc_scrollDetalles.gridy = 1;
                                getContentPane().add(scrollDetalles, gbc_scrollDetalles);
                        
                                GridBagConstraints gbc_2 = new GridBagConstraints();
                                gbc_2.fill = GridBagConstraints.VERTICAL;
                                gbc_2.insets = new Insets(0, 0, 5, 5);
                                gbc_2.gridx = 0;
                                gbc_2.gridy = 2;
                                JLabel label_4 = new JLabel("Ofertas Laborales:");
                                getContentPane().add(label_4, gbc_2);
                        listaOfertasLaborales = new JComboBox<>();
                        GridBagConstraints gbc_listaOfertasLaborales = new GridBagConstraints();
                        gbc_listaOfertasLaborales.fill = GridBagConstraints.BOTH;
                        gbc_listaOfertasLaborales.insets = new Insets(0, 0, 5, 5);
                        gbc_listaOfertasLaborales.gridx = 1;
                        gbc_listaOfertasLaborales.gridy = 2;
                        getContentPane().add(listaOfertasLaborales, gbc_listaOfertasLaborales);
                
                        btnConsultarOferta = new JButton("Consultar Oferta");
                        GridBagConstraints gbc_btnConsultarOferta = new GridBagConstraints();
                        gbc_btnConsultarOferta.fill = GridBagConstraints.VERTICAL;
                        gbc_btnConsultarOferta.insets = new Insets(0, 0, 5, 5);
                        gbc_btnConsultarOferta.gridx = 1;
                        gbc_btnConsultarOferta.gridy = 3;
                        getContentPane().add(btnConsultarOferta, gbc_btnConsultarOferta);
        
                GridBagConstraints gbc_4 = new GridBagConstraints();
                gbc_4.insets = new Insets(0, 0, 5, 5);
                gbc_4.gridx = 0;
                gbc_4.gridy = 4;
                JLabel label = new JLabel("Detalle Oferta:");
                getContentPane().add(label, gbc_4);
        detalleOfertaLaboral = new JTextArea();
        detalleOfertaLaboral.setEditable(false);
        JScrollPane scrollOferta = new JScrollPane(detalleOfertaLaboral);
        GridBagConstraints gbc_scrollOferta = new GridBagConstraints();
        gbc_scrollOferta.insets = new Insets(0, 0, 5, 5);
        gbc_scrollOferta.fill = GridBagConstraints.BOTH;
        gbc_scrollOferta.gridx = 1;
        gbc_scrollOferta.gridy = 4;
        getContentPane().add(scrollOferta, gbc_scrollOferta);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            ConsultarPerfilUsuario frame = new ConsultarPerfilUsuario();
            frame.setVisible(true);
        });
    }
}


