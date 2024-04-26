package ventanas;

import java.awt.EventQueue;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JInternalFrame;

import Logica.IContOferta;
import Logica.Oferta;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Map;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class OfertasMasVisitadas extends JInternalFrame {
	private JLabel 
	lblTitulo1, lblTitulo2, lblTitulo3, lblTitulo4, lblTitulo5,
	lblRank1, lblRank2, lblRank3, lblRank4,lblRank5,
	lblEmpresa1,lblEmpresa2,lblEmpresa3,lblEmpresa4,lblEmpresa5,
	lblOferta1,lblOferta2,lblOferta3,lblOferta4,lblOferta5, 
	lblTipo1, lblTipo2, lblTipo3, lblTipo4, lblTipo5,
	lblCant1, lblCant2, lblCant3, lblCant4, lblCant5;
	private JButton btnCancelar;
	
	public OfertasMasVisitadas(IContOferta contOferta) {
		setResizable(true);
        setMaximizable(true);
        setTitle("Ofertas Mas Visitadas");
        setBounds(100, 100, 640, 480);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] {30, 30, 120, 120, 120, 120, 30};
        gridBagLayout.rowHeights = new int[] {30, 30, 30, 30, 30, 30, 30, 40};
        gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0};
        gridBagLayout.columnWeights = new double[]{};
        getContentPane().setLayout(gridBagLayout);
        
        lblTitulo1 = new JLabel("#");
        GridBagConstraints gbc_lblTitulo1 = new GridBagConstraints();
        gbc_lblTitulo1.insets = new Insets(5, 5, 5, 5);
        gbc_lblTitulo1.gridx = 1;
        gbc_lblTitulo1.gridy = 1;
        getContentPane().add(lblTitulo1, gbc_lblTitulo1);
        
        lblTitulo2 = new JLabel("Oferta Laboral");
        GridBagConstraints gbc_lblTitulo2 = new GridBagConstraints();
        gbc_lblTitulo2.insets = new Insets(5, 5, 5, 5);
        gbc_lblTitulo2.gridx = 2;
        gbc_lblTitulo2.gridy = 1;
        getContentPane().add(lblTitulo2, gbc_lblTitulo2);
        
        lblTitulo3 = new JLabel("Empresa");
        GridBagConstraints gbc_lblTitulo3 = new GridBagConstraints();
        gbc_lblTitulo3.insets = new Insets(5, 5, 5, 5);
        gbc_lblTitulo3.gridx = 3;
        gbc_lblTitulo3.gridy = 1;
        getContentPane().add(lblTitulo3, gbc_lblTitulo3);
        
        lblTitulo4 = new JLabel("Tipo de publicacion");
        GridBagConstraints gbc_lblTitulo4 = new GridBagConstraints();
        gbc_lblTitulo4.insets = new Insets(5, 5, 5, 5);
        gbc_lblTitulo4.gridx = 4;
        gbc_lblTitulo4.gridy = 1;
        getContentPane().add(lblTitulo4, gbc_lblTitulo4);
        
        lblTitulo5 = new JLabel("Cantidad de visitas");
        GridBagConstraints gbc_lblTitulo5 = new GridBagConstraints();
        gbc_lblTitulo5.insets = new Insets(5, 5, 5, 5);
        gbc_lblTitulo5.gridx = 5;
        gbc_lblTitulo5.gridy = 1;
        getContentPane().add(lblTitulo5, gbc_lblTitulo5);
        
        lblRank1 = new JLabel("1");
        GridBagConstraints gbc_Rank1 = new GridBagConstraints();
        gbc_Rank1.insets = new Insets(5, 5, 5, 5);
        gbc_Rank1.gridx = 1;
        gbc_Rank1.gridy = 2;
        getContentPane().add(lblRank1, gbc_Rank1);
        
        lblRank2 = new JLabel("2");
        GridBagConstraints gbc_Rank2 = new GridBagConstraints();
        gbc_Rank2.insets = new Insets(5, 5, 5, 5);
        gbc_Rank2.gridx = 1;
        gbc_Rank2.gridy = 3;
        getContentPane().add(lblRank2, gbc_Rank2);
        
        lblRank3 = new JLabel("3");
        GridBagConstraints gbc_Rank3 = new GridBagConstraints();
        gbc_Rank3.insets = new Insets(5, 5, 5, 5);
        gbc_Rank3.gridx = 1;
        gbc_Rank3.gridy = 4;
        getContentPane().add(lblRank3, gbc_Rank3);
        
        lblRank4 = new JLabel("4");
        GridBagConstraints gbc_Rank4 = new GridBagConstraints();
        gbc_Rank4.insets = new Insets(5, 5, 5, 5);
        gbc_Rank4.gridx = 1;
        gbc_Rank4.gridy = 5;
        getContentPane().add(lblRank4, gbc_Rank4);
        
        lblRank5 = new JLabel("5");
        GridBagConstraints gbc_Rank5 = new GridBagConstraints();
        gbc_Rank5.insets = new Insets(5, 5, 5, 5);
        gbc_Rank5.gridx = 1;
        gbc_Rank5.gridy = 6;
        getContentPane().add(lblRank5, gbc_Rank5);
        
        lblOferta1 = new JLabel("");
        GridBagConstraints gbc_lblOferta1 = new GridBagConstraints();
        gbc_lblOferta1.insets = new Insets(5, 5, 5, 5);
        gbc_lblOferta1.gridx = 2;
        gbc_lblOferta1.gridy = 2;
        getContentPane().add(lblOferta1, gbc_lblOferta1);
        
        lblOferta2 = new JLabel("");
        GridBagConstraints gbc_lblOferta2 = new GridBagConstraints();
        gbc_lblOferta2.insets = new Insets(5, 5, 5, 5);
        gbc_lblOferta2.gridx = 2;
        gbc_lblOferta2.gridy = 3;
        getContentPane().add(lblOferta2, gbc_lblOferta2);
        
        lblOferta3 = new JLabel("");
        GridBagConstraints gbc_lblOferta3 = new GridBagConstraints();
        gbc_lblOferta3.insets = new Insets(5, 5, 5, 5);
        gbc_lblOferta3.gridx = 2;
        gbc_lblOferta3.gridy = 4;
        getContentPane().add(lblOferta3, gbc_lblOferta3);
        
        lblOferta4 = new JLabel("");
        GridBagConstraints gbc_lblOferta4 = new GridBagConstraints();
        gbc_lblOferta4.insets = new Insets(5, 5, 5, 5);
        gbc_lblOferta4.gridx = 2;
        gbc_lblOferta4.gridy = 5;
        getContentPane().add(lblOferta4, gbc_lblOferta4);
        
        lblOferta5 = new JLabel("");
        GridBagConstraints gbc_lblOferta5 = new GridBagConstraints();
        gbc_lblOferta5.insets = new Insets(5, 5, 5, 5);
        gbc_lblOferta5.gridx = 2;
        gbc_lblOferta5.gridy = 6;
        getContentPane().add(lblOferta5, gbc_lblOferta5);
        
        lblEmpresa1 = new JLabel("");
        GridBagConstraints gbc_lblEmpresa1 = new GridBagConstraints();
        gbc_lblEmpresa1.insets = new Insets(5, 5, 5, 5);
        gbc_lblEmpresa1.gridx = 3;
        gbc_lblEmpresa1.gridy = 2;
        getContentPane().add(lblEmpresa1, gbc_lblEmpresa1);
        
        lblEmpresa2 = new JLabel("");
        GridBagConstraints gbc_lblEmpresa2 = new GridBagConstraints();
        gbc_lblEmpresa2.insets = new Insets(5, 5, 5, 5);
        gbc_lblEmpresa2.gridx = 3;
        gbc_lblEmpresa2.gridy = 3;
        getContentPane().add(lblEmpresa2, gbc_lblEmpresa2);
        
        lblEmpresa3 = new JLabel("");
        GridBagConstraints gbc_lblEmpresa3 = new GridBagConstraints();
        gbc_lblEmpresa3.insets = new Insets(5, 5, 5, 5);
        gbc_lblEmpresa3.gridx = 3;
        gbc_lblEmpresa3.gridy = 4;
        getContentPane().add(lblEmpresa3, gbc_lblEmpresa3);
        
        lblEmpresa4 = new JLabel("");
        GridBagConstraints gbc_lblEmpresa4 = new GridBagConstraints();
        gbc_lblEmpresa4.insets = new Insets(5, 5, 5, 5);
        gbc_lblEmpresa4.gridx = 3;
        gbc_lblEmpresa4.gridy = 5;
        getContentPane().add(lblEmpresa4, gbc_lblEmpresa4);
        
        lblEmpresa5 = new JLabel("");
        GridBagConstraints gbc_lblEmpresa5 = new GridBagConstraints();
        gbc_lblEmpresa5.insets = new Insets(5, 5, 5, 5);
        gbc_lblEmpresa5.gridx = 3;
        gbc_lblEmpresa5.gridy = 6;
        getContentPane().add(lblEmpresa5, gbc_lblEmpresa5);
        
        lblTipo1 = new JLabel("");
        GridBagConstraints gbc_lblTipo1 = new GridBagConstraints();
        gbc_lblTipo1.insets = new Insets(5, 5, 5, 5);
        gbc_lblTipo1.gridx = 4;
        gbc_lblTipo1.gridy = 2;
        getContentPane().add(lblTipo1, gbc_lblTipo1);
        
        lblTipo2 = new JLabel("");
        GridBagConstraints gbc_lblTipo2 = new GridBagConstraints();
        gbc_lblTipo2.insets = new Insets(5, 5, 5, 5);
        gbc_lblTipo2.gridx = 4;
        gbc_lblTipo2.gridy = 3;
        getContentPane().add(lblTipo2, gbc_lblTipo2);
        
        lblTipo3 = new JLabel("");
        GridBagConstraints gbc_lblTipo3 = new GridBagConstraints();
        gbc_lblTipo3.insets = new Insets(5, 5, 5, 5);
        gbc_lblTipo3.gridx = 4;
        gbc_lblTipo3.gridy = 4;
        getContentPane().add(lblTipo3, gbc_lblTipo3);
        
        lblTipo4 = new JLabel("");
        GridBagConstraints gbc_lblTipo4 = new GridBagConstraints();
        gbc_lblTipo4.insets = new Insets(5, 5, 5, 5);
        gbc_lblTipo4.gridx = 4;
        gbc_lblTipo4.gridy = 5;
        getContentPane().add(lblTipo4, gbc_lblTipo4);
        
        lblTipo5 = new JLabel("");
        GridBagConstraints gbc_lblTipo5 = new GridBagConstraints();
        gbc_lblTipo5.insets = new Insets(5, 5, 5, 5);
        gbc_lblTipo5.gridx = 4;
        gbc_lblTipo5.gridy = 6;
        getContentPane().add(lblTipo5, gbc_lblTipo5);
        
        lblCant1 = new JLabel("");
        GridBagConstraints gbc_lblCant1 = new GridBagConstraints();
        gbc_lblCant1.insets = new Insets(5, 5, 5, 5);
        gbc_lblCant1.gridx = 5;
        gbc_lblCant1.gridy = 2;
        getContentPane().add(lblCant1, gbc_lblCant1);
        
        lblCant2 = new JLabel("");
        GridBagConstraints gbc_lblCant2 = new GridBagConstraints();
        gbc_lblCant2.insets = new Insets(5, 5, 5, 5);
        gbc_lblCant2.gridx = 5;
        gbc_lblCant2.gridy = 3;
        getContentPane().add(lblCant2, gbc_lblCant2);
        
        lblCant3 = new JLabel("");
        GridBagConstraints gbc_lblCant3 = new GridBagConstraints();
        gbc_lblCant3.insets = new Insets(5, 5, 5, 5);
        gbc_lblCant3.gridx = 5;
        gbc_lblCant3.gridy = 4;
        getContentPane().add(lblCant3, gbc_lblCant3);
        
        lblCant4 = new JLabel("");
        GridBagConstraints gbc_lblCant4 = new GridBagConstraints();
        gbc_lblCant4.insets = new Insets(5, 5, 5, 5);
        gbc_lblCant4.gridx = 5;
        gbc_lblCant4.gridy = 5;
        getContentPane().add(lblCant4, gbc_lblCant4);
        
        lblCant5 = new JLabel("");
        GridBagConstraints gbc_lblCant5 = new GridBagConstraints();
        gbc_lblCant5.insets = new Insets(5, 5, 5, 5);
        gbc_lblCant5.gridx = 5;
        gbc_lblCant5.gridy = 6;
        getContentPane().add(lblCant5, gbc_lblCant5);
        
        btnCancelar = new JButton("Salir");
        GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
        gbc_btnCancelar.insets = new Insets(5, 5, 0, 5);
        gbc_btnCancelar.fill = GridBagConstraints.BOTH;
        gbc_btnCancelar.gridx = 5;
        gbc_btnCancelar.gridy = 7;
        getContentPane().add(btnCancelar, gbc_btnCancelar);
        
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           /* 	if (listaUsuarios.getSelectedIndex() != -1) {
            	resetCampos((String) listaUsuarios.getSelectedItem(), controladorUsuario);
            } */           	
            	setVisible(false);
}
        });
	}

	public void actualizarOfertas(IContOferta contOferta) {
		Oferta[] top = new Oferta[5];
        Map<Oferta, Integer> ofertas = contOferta.ofertasMasVistas();
        List<Map.Entry<Oferta, Integer>> listaOrdenada = ofertas.entrySet().stream()
                .sorted(Map.Entry.<Oferta, Integer>comparingByValue().reversed())
                .collect(Collectors.toList());

        List<Map.Entry<Oferta, Integer>> primeros5 = listaOrdenada.stream()
                .limit(5)
                .collect(Collectors.toList());
        int i = 0;
        for (Map.Entry<Oferta, Integer> entry : primeros5) {
        	top[i] = entry.getKey();
            i ++;
        }
        if (top[0] != null) {
        	lblEmpresa1.setText(top[0].getNombreEmpresa());
        	lblOferta1.setText(top[0].getNombre());
        	lblTipo1.setText(top[0].getTOferta().getNombre());
        	lblCant1.setText(Integer.toString(top[0].getCantVisitas()));
        }
        if (top[1] != null) {
        	lblEmpresa2.setText(top[1].getNombreEmpresa());
        	lblOferta2.setText(top[1].getNombre());
        	lblTipo2.setText(top[1].getTOferta().getNombre());
        	lblCant2.setText(Integer.toString(top[1].getCantVisitas()));
        }
        if (top[2] != null) {
        	lblEmpresa3.setText(top[2].getNombreEmpresa());
        	lblOferta3.setText(top[2].getNombre());
        	lblTipo3.setText(top[2].getTOferta().getNombre());
        	lblCant3.setText(Integer.toString(top[2].getCantVisitas()));
        }
        if (top[3] != null) {
        	lblEmpresa4.setText(top[3].getNombreEmpresa());
        	lblOferta4.setText(top[3].getNombre());
        	lblTipo4.setText(top[3].getTOferta().getNombre());
        	lblCant4.setText(Integer.toString(top[3].getCantVisitas()));
        }
        if (top[4] != null) {
        	lblEmpresa5.setText(top[4].getNombreEmpresa());
        	lblOferta5.setText(top[4].getNombre());
        	lblTipo5.setText(top[4].getTOferta().getNombre());
        	lblCant5.setText(Integer.toString(top[4].getCantVisitas()));
        }
	}
}
