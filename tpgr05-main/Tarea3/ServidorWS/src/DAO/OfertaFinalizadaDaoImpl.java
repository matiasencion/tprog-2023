package DAO;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import Logica.Empresa;
import Logica.Postulacion;
import Logica.Postulante;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import ofertasFinalizadas.EmpresaOF;
import ofertasFinalizadas.OfertaFinalizada;
import ofertasFinalizadas.PostulacionId;
import ofertasFinalizadas.PostulacionOF;
import ofertasFinalizadas.PostulanteOF;


public class OfertaFinalizadaDaoImpl {

	private EntityManagerFactory factory;
	public void shutdown() {
	    EntityManager entityManager = factory.createEntityManager();
	    EntityTransaction transaction = entityManager.getTransaction();

	    try {
	        transaction.begin();
	        entityManager.createNativeQuery("SHUTDOWN").executeUpdate();
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction.isActive()) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        if (entityManager != null) {
	            entityManager.close();
	        }
	    }
	}

    public OfertaFinalizadaDaoImpl() {
        this.factory = Persistence.createEntityManagerFactory("ofertasFinalizadas");
    }
    

    private PostulanteOF existePostulante(String nickname) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            PostulanteOF postulante = entityManager.createQuery(
                    "SELECT p FROM PostulanteOF p WHERE p.nickname = :nickname", PostulanteOF.class)
                    .setParameter("nickname", nickname)
                    .getSingleResult();
            return postulante;
        } catch (Exception e) {
            if (e instanceof jakarta.persistence.NoResultException) {
                return null;
            } else {
                e.printStackTrace();
                return null;
            }
        } finally {
            entityManager.close();
        }
    }
    private EmpresaOF existeEmpresa(String nickname) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            EmpresaOF empresa = entityManager.createQuery("SELECT e FROM EmpresaOF e WHERE e.nickname = :nickname", EmpresaOF.class)
                                             .setParameter("nickname", nickname)
                                           .getSingleResult();
            return empresa;
        } catch (Exception e) {
            if (e instanceof jakarta.persistence.NoResultException) {
                return null;
            } else {
                e.printStackTrace();
                return null;
            }
        } finally {
            entityManager.close();
        }
    }





    public void agregar(String nombre, String descripcion, String horario, String sueldo, String departamento, String ciudad, String nombreOferta, String fechaDatosPublicacion, String fechaActual, String costo, String paquete, Collection<Postulacion> postulantes, Empresa Empresa) {
    	EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        
        try {
        	transaction.begin();
            EmpresaOF empresaExistente = existeEmpresa(Empresa.getNickname());
            EmpresaOF empresa = null;
            if(empresaExistente == null)
            {
            	empresa = new EmpresaOF();
                empresa.setNickname(Empresa.getNickname());
                empresa.setEmail(Empresa.getEmail());
                empresa.setNombre(Empresa.getNombre());
                empresa.setApellido(Empresa.getApellido());
                empresa.setTipoUsuario("Empresa");
                empresa.setDescripcion(Empresa.getDescripcion());
                empresa.setSitioWeb(Empresa.getLink());
            }
            
            
            
            OfertaFinalizada ofertaFinalizada = new OfertaFinalizada();
            ofertaFinalizada.setNombre(nombre);
            ofertaFinalizada.setDescripcion(descripcion);
            ofertaFinalizada.setHorario(horario);
            ofertaFinalizada.setRemuneracion(Double.parseDouble(sueldo));
            ofertaFinalizada.setDepartamento(departamento);
            ofertaFinalizada.setCiudad(ciudad);
            ofertaFinalizada.setTipoPublicacion(nombreOferta);
            ofertaFinalizada.setFechaAlta(fechaDatosPublicacion);
            ofertaFinalizada.setFechaBaja(fechaActual);
            ofertaFinalizada.setCosto(Double.parseDouble(costo));
            ofertaFinalizada.setPaquete(paquete);
            if(empresaExistente == null)
            {
            	ofertaFinalizada.setEmpresa(empresa);
            }
            else {
            	ofertaFinalizada.setEmpresa(empresaExistente);

            }
        
            

            for (Postulacion postulacion : postulantes) {
            	Postulante post = postulacion.getPostulante();
                PostulanteOF postulante = new PostulanteOF();
                PostulanteOF postulanteExistente = existePostulante(post.getNickname());
                if (postulanteExistente == null) {
                	postulante.setNickname(post.getNickname());
                    postulante.setEmail(post.getEmail());
                    postulante.setNombre(post.getNombre());
                    postulante.setApellido(post.getApellido());
                    postulante.setTipoUsuario("Postulante");
                    postulante.setNacionalidad(post.getNacionalidad());
                    postulante.setFechaNacimiento(post.getFechaNac());
                    PostulacionOF postulacionOF = new PostulacionOF();
                    PostulacionId postulacionId = new PostulacionId(postulante.getId(), ofertaFinalizada.getId());
                    postulacionOF.setId(postulacionId);
                    postulacionOF.setPostulante(postulante);
                    postulacionOF.setOferta(ofertaFinalizada);
                    postulacionOF.setCv(postulacion.getCv());
                    postulacionOF.setMotivacion(postulacion.getDescripcion());
                    postulacionOF.setFechaPostulacion(postulacion.getFecha());
                    postulacionOF.setPostulante(postulante);                    
                    ofertaFinalizada.getPostulaciones().add(postulacionOF);
                    
                } else {
                	PostulacionOF postulacionOF = new PostulacionOF();
                    PostulacionId postulacionId = new PostulacionId(postulanteExistente.getId(), ofertaFinalizada.getId());
                    postulacionOF.setId(postulacionId);
                    postulacionOF.setPostulante(postulante);
                    postulacionOF.setOferta(ofertaFinalizada);
                    postulacionOF.setCv(postulacion.getCv());
                    postulacionOF.setMotivacion(postulacion.getDescripcion());
                    postulacionOF.setFechaPostulacion(postulacion.getFecha());
                    postulacionOF.setPostulante(postulanteExistente);
                    ofertaFinalizada.getPostulaciones().add(postulacionOF);
                    

                }

               
            }
            
            entityManager.persist(ofertaFinalizada);
            transaction.commit();
        }
            catch (Exception e) {
                if (transaction.isActive()) {
                	transaction.rollback();
                }
                e.printStackTrace(); } finally {
                    if (entityManager != null) {
                        shutdown();
                    }
                    
                }
        
            }
    
        
    
        


  
}


