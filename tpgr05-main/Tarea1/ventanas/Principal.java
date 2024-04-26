package ventanas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.beans.PropertyVetoException;
import java.time.LocalDate;
import java.util.Set;
import java.util.HashSet;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JComboBox;
import Logica.Fabrica;
import Logica.IContOferta;
import Logica.IContUsuario;
import Logica.ManejadorOferta;
import excepciones.KeywordRepetida;
import excepciones.OfertaRepetidaException;
import excepciones.TOfertaRepetidaException;
import Logica.IContOferta;
import javax.swing.SwingConstants;
import java.awt.Insets;
import Logica.CargaDatos;


public class Principal extends JFrame {

    private AltaPostulante altaPostulanteIF;
    private AltaEmpresa altaEmpresaIF;
    private IContUsuario ContU;
    private IContOferta ContO;
    private ModificarDatosUsuario ModificarDatosUsuarioIF;
    private AgregarTipoPublicacionAlPaquete agregarTipoPublicacionAlPaqueteIF;
    private ConsultarPaqueteTipoPublicacionOF ConsultarPaqueteTipoPublicacionOFIF;
    private AltaTipoPublicacionOferta AltaTipoPublicacionOfertaIF;
    private PostulacionOfertaLaboral postulacionOfertaLaboralIF;
    private CrearPaqueteOfertas crearPaqueteOfertasIF;
    private AltaOfertaLaboral AltaOfertaLaboralIF;
    private ConsultarUsuario ConsultarUsuarioIF;
    private ConsultaOfertaLaboral ConsultaOfertaLaboralIF;
    private static final long serialVersionUID = 1L;
    private JDesktopPane escritorio;
    private CargaDatos CargaDatosIF;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Principal frame = new Principal();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Principal() {
		Fabrica fabrica = Fabrica.getInstancia();
		ContU = fabrica.getIContUsuario();
		ContO = fabrica.getIContOferta();

		configurarVentana();
		configurarMenu();
		inicializarVentanasInternas();		
	}
	private void configurarVentana() {
		setBounds(0, 0, 800, 600);
		setLocationRelativeTo(null);
		escritorio = new JDesktopPane();
		getContentPane().add(escritorio, BorderLayout.CENTER);
	}
		private void configurarMenu() {
			JMenuBar barraMenu = new JMenuBar();
			setJMenuBar(barraMenu);

			JMenu menuUsuario = new JMenu("Usuario");
			barraMenu.add(menuUsuario);

			JMenu submenuAltaUsuario = new JMenu("Alta Usuario");
			menuUsuario.add(submenuAltaUsuario);

			JMenuItem itemAltaEmpresa = new JMenuItem("Empresa");
			itemAltaEmpresa.addActionListener(e -> altaEmpresaIF.setVisible(true));
			submenuAltaUsuario.add(itemAltaEmpresa);

			JMenuItem itemAltaPostulante = new JMenuItem("Postulante");
			itemAltaPostulante.addActionListener(e -> altaPostulanteIF.setVisible(true));
			submenuAltaUsuario.add(itemAltaPostulante);

			JMenuItem itemConsultarUsuario = new JMenuItem("Consultar Usuario");
			itemConsultarUsuario.addActionListener(e -> ConsultarUsuarioIF.setVisible(true));
			menuUsuario.add(itemConsultarUsuario);

			JMenuItem itemModificarUsuario = new JMenuItem("Modificar Datos Usuario");
			itemModificarUsuario.addActionListener(e -> ModificarDatosUsuarioIF.setVisible(true));
			menuUsuario.add(itemModificarUsuario);

			JMenu menuOfertas = new JMenu("Ofertas");
			barraMenu.add(menuOfertas);

			JMenuItem itemAltaOferta = new JMenuItem("Alta de Oferta");
			itemAltaOferta.addActionListener(e -> AltaOfertaLaboralIF.setVisible(true));
			menuOfertas.add(itemAltaOferta);

			JMenuItem itemConsultaOferta = new JMenuItem("Consulta Oferta");
			itemConsultaOferta.addActionListener(e -> ConsultaOfertaLaboralIF.setVisible(true));
			menuOfertas.add(itemConsultaOferta);

			JMenuItem itemPostularseOferta = new JMenuItem("Postularse a Oferta");
			itemPostularseOferta.addActionListener(e -> postulacionOfertaLaboralIF.setVisible(true));
			menuOfertas.add(itemPostularseOferta);

			JMenu menuTiposPublicacion = new JMenu("Tipos de Publicación");
			barraMenu.add(menuTiposPublicacion);

			JMenuItem itemAltaTipoPublicacion = new JMenuItem("Alta de Tipo de Publicación");
			itemAltaTipoPublicacion.addActionListener(e -> AltaTipoPublicacionOfertaIF.setVisible(true));
			menuTiposPublicacion.add(itemAltaTipoPublicacion);

			JMenu menuPaquetesPublicacion = new JMenu("Paquetes de Tipo de Publicación");
			barraMenu.add(menuPaquetesPublicacion);

			JMenuItem itemCrearPaquete = new JMenuItem("Crear Paquete");
			itemCrearPaquete.addActionListener(e -> crearPaqueteOfertasIF.setVisible(true));
			menuPaquetesPublicacion.add(itemCrearPaquete);

			JMenuItem itemAgregarTipoPaquete = new JMenuItem("Agregar Tipo a Paquete");
			itemAgregarTipoPaquete.addActionListener(e -> agregarTipoPublicacionAlPaqueteIF.setVisible(true));
			menuPaquetesPublicacion.add(itemAgregarTipoPaquete);

			JMenuItem itemConsultaPaquete = new JMenuItem("Consulta de Paquete");
			itemConsultaPaquete.addActionListener(e -> ConsultarPaqueteTipoPublicacionOFIF.setVisible(true));
			menuPaquetesPublicacion.add(itemConsultaPaquete);

			JMenu menuKeywords = new JMenu("Palabras Clave");
			barraMenu.add(menuKeywords);

			JMenu menuSistema = new JMenu("Sistema");
			barraMenu.add(menuSistema);

			JMenuItem itemCargarDatos = new JMenuItem("Cargar datos");
			itemCargarDatos.addActionListener(e -> {
				 CargaDatosIF = new CargaDatos(ContU, ContO);
                 CargaDatosIF.cargarUsuarios();
				 CargaDatosIF.cargarTipoPublicacion();
				 CargaDatosIF.cargarKeywords();
				 CargaDatosIF.cargarOfertas();
				 CargaDatosIF.cargarPostulaciones(); 
				 CargaDatosIF.cargarPaquetes();
				 

				
			});
			
			menuSistema.add(itemCargarDatos);
			
			JMenuItem mntmNewMenuItem = new JMenuItem("Salir");
			mntmNewMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					dispose();
				}
			});
			menuSistema.add(mntmNewMenuItem);
		}
	private void inicializarVentana(JInternalFrame frame) throws PropertyVetoException {
		frame.setClosed(false);
		frame.setVisible(false);
		frame.setLocation(34, 11);
		escritorio.add(frame);
	}

	private void inicializarVentanasInternas() {
		try {
			inicializarVentana(altaPostulanteIF = new AltaPostulante(ContU));
			inicializarVentana(ModificarDatosUsuarioIF = new ModificarDatosUsuario(ContU));
			inicializarVentana(ConsultarPaqueteTipoPublicacionOFIF = new ConsultarPaqueteTipoPublicacionOF(ContO));
			inicializarVentana(agregarTipoPublicacionAlPaqueteIF = new AgregarTipoPublicacionAlPaquete( ContO));
			inicializarVentana(crearPaqueteOfertasIF = new CrearPaqueteOfertas(ContO));
			inicializarVentana(postulacionOfertaLaboralIF = new PostulacionOfertaLaboral(ContU, ContO));
			inicializarVentana(AltaOfertaLaboralIF = new AltaOfertaLaboral(ContO));
			inicializarVentana(ConsultaOfertaLaboralIF = new ConsultaOfertaLaboral(ContU, ContO));
			inicializarVentana(AltaTipoPublicacionOfertaIF = new AltaTipoPublicacionOferta(ContO));
			inicializarVentana(ConsultarUsuarioIF = new ConsultarUsuario(ContU, ContO,ConsultaOfertaLaboralIF));
			inicializarVentana(altaEmpresaIF = new AltaEmpresa(ContU));
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}
}
