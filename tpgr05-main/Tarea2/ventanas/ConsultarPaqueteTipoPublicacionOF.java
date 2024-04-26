package ventanas;

import javax.swing.*;
import Logica.IContOferta;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ConsultarPaqueteTipoPublicacionOF extends JInternalFrame {

    private JComboBox<String> listaPaquetes;
    private JComboBox<String> listaTipos;
    private JTextArea areaInformacionPaquete;
    private JTextArea areaInformacionTipo;
    private JButton btnCancelar;

    public ConsultarPaqueteTipoPublicacionOF(IContOferta ContO) {
    	setResizable(true);
    	setMaximizable(true);
        setTitle("Consultar Paquete Tipo Publicación OF");
        setBounds(100, 100, 640, 480);
        
        JPanel body = new JPanel();
        getContentPane().add(body, BorderLayout.NORTH);
        GridBagLayout gbl_body = new GridBagLayout();
        gbl_body.columnWidths = new int[]{209, 241, 0, 0};
        gbl_body.rowHeights = new int[]{30, 150, 30, 150, 30, 0, 0};
        gbl_body.columnWeights = new double[]{1.0, 1.0, 4.9E-324, Double.MIN_VALUE};
        gbl_body.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        body.setLayout(gbl_body);
        
        JLabel lblPaquetes = new JLabel("Paquetes");
        lblPaquetes.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_lblPaquetes = new GridBagConstraints();
        gbc_lblPaquetes.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblPaquetes.insets = new Insets(5, 5, 5, 5);
        gbc_lblPaquetes.gridx = 0;
        gbc_lblPaquetes.gridy = 0;
        body.add(lblPaquetes, gbc_lblPaquetes);
        
        listaPaquetes = new JComboBox();
        listaPaquetes.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                Vector<String> paquetes = (Vector<String>) ContO.listarPaquetes();
                mostrarPaquetes(paquetes);
            }
            @Override
            public void focusLost(FocusEvent e) {
            }
        });

        GridBagConstraints gbc_listaPaquete = new GridBagConstraints();
        gbc_listaPaquete.fill = GridBagConstraints.BOTH;
        gbc_listaPaquete.gridwidth = 2;
        gbc_listaPaquete.insets = new Insets(5, 5, 5, 5);
        gbc_listaPaquete.gridx = 1;
        gbc_listaPaquete.gridy = 0;
        body.add(listaPaquetes, gbc_listaPaquete);
        
        JLabel lblNewLabel_1 = new JLabel("Datos Paquete");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblNewLabel_1.insets = new Insets(0, 5, 5, 5);
        gbc_lblNewLabel_1.gridx = 0;
        gbc_lblNewLabel_1.gridy = 1;
        body.add(lblNewLabel_1, gbc_lblNewLabel_1);
        
         areaInformacionPaquete = new JTextArea();
         areaInformacionPaquete.setEditable(false);
         listaPaquetes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String paquete = (String) listaPaquetes.getSelectedItem();
                if (paquete != null) {
                    String info = ContO.getInfoPaquete(paquete);
                    areaInformacionPaquete.setText(info);       
                }
            }
        });
         
        areaInformacionPaquete.setLineWrap(true);
        GridBagConstraints gbc_areaInformacionPaquete = new GridBagConstraints();
        gbc_areaInformacionPaquete.fill = GridBagConstraints.BOTH;
        gbc_areaInformacionPaquete.gridwidth = 2;
        gbc_areaInformacionPaquete.insets = new Insets(0, 5, 5, 5);
        gbc_areaInformacionPaquete.gridx = 1;
        gbc_areaInformacionPaquete.gridy = 1;
        body.add(areaInformacionPaquete, gbc_areaInformacionPaquete);
        
        JLabel lblNewLabel_4 = new JLabel("Tipos de Publicacion del Paquete");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
        gbc_lblNewLabel_4.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblNewLabel_4.insets = new Insets(0, 5, 5, 5);
        gbc_lblNewLabel_4.gridx = 0;
        gbc_lblNewLabel_4.gridy = 2;
        body.add(lblNewLabel_4, gbc_lblNewLabel_4);
        
         listaTipos = new JComboBox();

         listaTipos.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                String paquete = (String) listaPaquetes.getSelectedItem();
                if (paquete != null) {
                    Vector<String> tipos = (Vector<String>) ContO.getNombresTOPaquetes(paquete);
                    mostrarTipos(tipos);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
            }

        });
        GridBagConstraints gbc_listaTipos = new GridBagConstraints();
        gbc_listaTipos.fill = GridBagConstraints.HORIZONTAL;
        gbc_listaTipos.gridwidth = 2;
        gbc_listaTipos.insets = new Insets(0, 5, 5, 5);
        gbc_listaTipos.gridx = 1;
        gbc_listaTipos.gridy = 2;
        body.add(listaTipos, gbc_listaTipos);
        
        JLabel lblNewLabel_6 = new JLabel("Datos Tipo de Oferta");
        lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
        gbc_lblNewLabel_6.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblNewLabel_6.insets = new Insets(0, 5, 5, 5);
        gbc_lblNewLabel_6.gridx = 0;
        gbc_lblNewLabel_6.gridy = 3;
        body.add(lblNewLabel_6, gbc_lblNewLabel_6);
        
        areaInformacionTipo = new JTextArea();
        areaInformacionTipo.setEditable(false);
        listaTipos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                String tipo = (String) listaTipos.getSelectedItem();
                if (tipo != null) {
                    String info = ContO.getInfoTipo(tipo);
                    areaInformacionTipo.setText(info);
                }
            }
        });
        areaInformacionTipo.setLineWrap(true);
        GridBagConstraints gbc_areaInformacionTipo = new GridBagConstraints();
        gbc_areaInformacionTipo.fill = GridBagConstraints.BOTH;
        gbc_areaInformacionTipo.gridwidth = 2;
        gbc_areaInformacionTipo.insets = new Insets(0, 5, 5, 5);
        gbc_areaInformacionTipo.gridx = 1;
        gbc_areaInformacionTipo.gridy = 3;
        body.add(areaInformacionTipo, gbc_areaInformacionTipo);
        
        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        
        JButton btnNewButtonCancelar = new JButton("Cancelar");

        btnNewButtonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
                setVisible(false);
            }
        });


        btnNewButtonCancelar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnNewButtonCancelar.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(btnNewButtonCancelar);


} 
       public void mostrarPaquetes(Vector<String> paquetes) {
        listaPaquetes.removeAllItems();
        for (String p : paquetes) {
            listaPaquetes.addItem(p);
        } 

           } 

        private void limpiarCampos() {
        areaInformacionPaquete.setText("");
        areaInformacionTipo.setText("");
        listaTipos.removeAllItems();
        listaPaquetes.removeAllItems();
      
        }
        private void mostrarTipos(Vector<String> tipos) {
            listaTipos.removeAllItems();
            for (String t : tipos) {
                listaTipos.addItem(t);
            }
        }
 

}


