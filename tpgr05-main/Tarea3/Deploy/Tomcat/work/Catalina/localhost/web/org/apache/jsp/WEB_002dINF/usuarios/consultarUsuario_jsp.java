/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/10.1.15
 * Generated at: 2023-11-13 23:28:17 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.usuarios;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;
import java.util.List;
import java.util.Set;
import servidor.DtUsuario;
import com.trabajouy.model.EstadoSesion;

public final class consultarUsuario_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports,
                 org.apache.jasper.runtime.JspSourceDirectives {

  private static final jakarta.servlet.jsp.JspFactory _jspxFactory =
          jakarta.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/media/styles/styleConsultaUsuarios.css", Long.valueOf(1699756072000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("jakarta.servlet");
    _jspx_imports_packages.add("jakarta.servlet.http");
    _jspx_imports_packages.add("jakarta.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("java.util.Set");
    _jspx_imports_classes.add("servidor.DtUsuario");
    _jspx_imports_classes.add("com.trabajouy.model.EstadoSesion");
  }

  private volatile jakarta.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public boolean getErrorOnELNotFound() {
    return false;
  }

  public jakarta.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final jakarta.servlet.http.HttpServletRequest request, final jakarta.servlet.http.HttpServletResponse response)
      throws java.io.IOException, jakarta.servlet.ServletException {

    if (!jakarta.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final jakarta.servlet.jsp.PageContext pageContext;
    jakarta.servlet.http.HttpSession session = null;
    final jakarta.servlet.ServletContext application;
    final jakarta.servlet.ServletConfig config;
    jakarta.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    jakarta.servlet.jsp.JspWriter _jspx_out = null;
    jakarta.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("    <title>Consulta de Usuarios</title>\n");
      out.write("    <style>\n");
      out.write("        ");
      out.write("* {\n");
      out.write("  margin: 0;\n");
      out.write("  padding: 0;\n");
      out.write("  box-sizing: border-box;\n");
      out.write("  font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;\n");
      out.write("}\n");
      out.write("\n");
      out.write("\n");
      out.write("a:link, a:visited, a:active, a:hover {\n");
      out.write("  color: black;\n");
      out.write("  text-decoration: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write("button:hover {\n");
      out.write("  cursor: click;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".main {\n");
      out.write("  display: flex;\n");
      out.write("  gap: 1rem;\n");
      out.write("  margin: 20px;\n");
      out.write("      background-color: #f2f2f2;\n");
      out.write("}\n");
      out.write("\n");
      out.write("\n");
      out.write(".list-item {\n");
      out.write("  display: flex;\n");
      out.write("  gap: 10px;\n");
      out.write("  margin-bottom: 20px;\n");
      out.write("  padding: 10px;\n");
      out.write("  background-color: #fff;\n");
      out.write("  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);\n");
      out.write("}\n");
      out.write("\n");
      out.write(".list-info {\n");
      out.write("  display: flex;\n");
      out.write("  gap: 10px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".list-info img {\n");
      out.write("  width: 100px;\n");
      out.write("  height: 100px;\n");
      out.write("  object-fit: cover;\n");
      out.write("}\n");
      out.write(".list-item {\n");
      out.write("  display: flex;\n");
      out.write("  margin-bottom: 10px;\n");
      out.write("  width: 100%;\n");
      out.write("  background-color: #ffffff;\n");
      out.write("  border-radius: 5px;\n");
      out.write("  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);\n");
      out.write("  transition: transform 0.3s;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".list-item:hover {\n");
      out.write("  box-shadow: 0 6px 25px rgba(0, 0, 0, 0.15);\n");
      out.write("  border: 3px solid #28666e;\n");
      out.write("  transform: scale(1.05);\n");
      out.write("}\n");
      out.write("\n");
      out.write(".list-info {\n");
      out.write("  display: flex;\n");
      out.write("  gap: 20px;\n");
      out.write("  padding: 10px;\n");
      out.write("  flex: 3;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".list-info img {\n");
      out.write("  max-width: 150px;\n");
      out.write("  border-radius: 5px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".list-block {\n");
      out.write("  display: flex;\n");
      out.write("  flex-direction: column;\n");
      out.write("  gap: 10px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".list-block h2 {\n");
      out.write("  font-size: 1.5em;\n");
      out.write("  color: #28666e;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".list-block ul {\n");
      out.write("  padding-left: 20px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".list-buy {\n");
      out.write("  display: flex;\n");
      out.write("  align-items: center;\n");
      out.write("  justify-content: center;\n");
      out.write("  flex: 1;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".center-panel {\n");
      out.write("  flex: 2;\n");
      out.write("  padding: 10px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".center-panel h1 {\n");
      out.write("  font-size: 2em;\n");
      out.write("  margin-bottom: 20px;\n");
      out.write("  color: #28666e;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".center-panel {\n");
      out.write("  padding: 20px;\n");
      out.write("  background-color: #f7f9fa;\n");
      out.write("  border-radius: 10px;\n");
      out.write("  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);\n");
      out.write("}\n");
      out.write("\n");
      out.write("\n");
      out.write("@media screen and (max-width: 768px) {\n");
      out.write("  .main {\n");
      out.write("    flex-direction: column;\n");
      out.write("  }\n");
      out.write("\n");
      out.write("  .left-panel,\n");
      out.write("  .center-panel {\n");
      out.write("    width: 100%;\n");
      out.write("    margin-bottom: 20px;\n");
      out.write("  }\n");
      out.write("\n");
      out.write("  .list-item {\n");
      out.write("    flex-direction: column;\n");
      out.write("  }\n");
      out.write("\n");
      out.write("  .list-info {\n");
      out.write("    flex-direction: column;\n");
      out.write("    align-items: center;\n");
      out.write("  }\n");
      out.write("\n");
      out.write("  .list-buy {\n");
      out.write("    margin-top: 10px;\n");
      out.write("  }\n");
      out.write("\n");
      out.write("  header {\n");
      out.write("    flex-direction: column;\n");
      out.write("    align-items: center;\n");
      out.write("    gap: 10px;\n");
      out.write("  }\n");
      out.write("\n");
      out.write("  .search-box {\n");
      out.write("    width: 90%;\n");
      out.write("    margin-top: 10px;\n");
      out.write("  }\n");
      out.write("\n");
      out.write("  .profile-section {\n");
      out.write("    flex-direction: column;\n");
      out.write("    gap: 10px;\n");
      out.write("  }\n");
      out.write("\n");
      out.write("  .profile-section div {\n");
      out.write("    gap: 10px;\n");
      out.write("  }\n");
      out.write("\n");
      out.write("  .profile-section button {\n");
      out.write("    width: 100%;\n");
      out.write("  }\n");
      out.write("}\n");
      out.write("\n");
      out.write("\n");
      out.write(".button-view {\n");
      out.write("  font-weight: 600;\n");
      out.write("  font-size: larger;\n");
      out.write("  width: 120px;\n");
      out.write("  padding: 10px 15px;\n");
      out.write("  margin-bottom: 10px;\n");
      out.write("  background-color: #28666e;\n");
      out.write("  color: #f4f4f4;\n");
      out.write("  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);\n");
      out.write("  transition: transform 0.2s ease, box-shadow 0.2s ease;\n");
      out.write("  text-align: center;\n");
      out.write("  border-style: none;\n");
      out.write("  border-radius: 5px;\n");
      out.write("}\n");
      out.write("  \n");
      out.write(".button-view:hover {\n");
      out.write("  transform: translateY(-5px);\n");
      out.write("  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15);\n");
      out.write("  cursor: pointer;\n");
      out.write("}");
      out.write("\n");
      out.write("    </style>\n");
      out.write("    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/WEB-INF/template/header.jsp", out, false);
      out.write("\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <div class=\"main\">\n");
      out.write("     \n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/WEB-INF/template/leftPanel.jsp", out, false);
      out.write("\n");
      out.write("        \n");
      out.write("        <div class=\"center-panel\"> \n");
      out.write("         <h1>Usuarios</h1>\n");
      out.write("         		       <div class=\"spinner-container\">\n");
      out.write("		       \n");
      out.write("		    <div id=\"pixel-spinner\" class=\"pixel-spinner\">\n");
      out.write("  			<div class=\"pixel-spinner-inner\">\n");
      out.write("  			</div>  \n");
      out.write("  			  			</div>  \n");
      out.write("  			</div>\n");
      out.write("          <main id=\"main\"> 	\n");
      out.write("		            <script>\n");
      out.write("	document.getElementById('main').style.visibility = 'hidden';\n");
      out.write("		</script>	\n");
      out.write("            ");

                List<DtUsuario> users = (List<DtUsuario>) request.getAttribute("usariosInfo_sesion");
                if (users != null && !users.isEmpty()) {
                    for (DtUsuario user : users) {
            
      out.write("\n");
      out.write("            <div class=\"list-item\">\n");
      out.write("                <div class=\"list-info\">\n");
      out.write("                                    \n");
      out.write("                    <img src=\"/web/media/images/");
      out.print( user.getFoto());
      out.write("\" /> \n");
      out.write("                \n");
      out.write("                    <div class=\"list-block\">\n");
      out.write("                        <div style=\"display:flex;\">\n");
      out.write("		            		<h2>");
      out.print( user.getNickname() );
      out.write("\n");
      out.write("		            		");

		                	servidor.PublicadorService service = new servidor.PublicadorService();
		            		servidor.Publicador portPublicador = service.getPublicadorPort();
		        			if ((EstadoSesion) request.getSession().getAttribute("estado_sesion") == EstadoSesion.POSTULANTE || (EstadoSesion) request.getSession().getAttribute("estado_sesion") == EstadoSesion.EMPRESA)  {
		        				if(!((String) request.getSession().getAttribute("nickname_sesion")).equals(user.getNickname())) {
		        					if(portPublicador.sigueAUsuario((String) request.getSession().getAttribute("nickname_sesion"), user.getNickname())) {
		        		
      out.write("\n");
      out.write("		        				<a href=\"/web/usuarios?unfollow=");
      out.print( user.getNickname());
      out.write("\"><img style=\"vertical-align: middle; width:22px; height:22px;\" src=\"/web/media/images/unfollow.png\"/></a>\n");
      out.write("		        		");

		        					} else {
		        		
      out.write("\n");
      out.write("		        				<a href=\"/web/usuarios?follow=");
      out.print( user.getNickname());
      out.write("\"><img style=\"vertical-align: middle; width:22px; height:22px;\" src=\"/web/media/images/follow.png\"/></a>\n");
      out.write("		        		");
			}
		        				}
                    		}
		        		
      out.write(" \n");
      out.write("		            		</h2>\n");
      out.write("		                \n");
      out.write("		           	 </div>\n");
      out.write("                        <ul>\n");
      out.write("                            <li><b>Nombre: </b>");
      out.print( user.getNombre() );
      out.write("</li>\n");
      out.write("                            <li><b>Apellido: </b>");
      out.print( user.getApellido() );
      out.write("</li>\n");
      out.write("                            <li><b>Email: </b>");
      out.print( user.getEmail() );
      out.write("</li>\n");
      out.write("                        </ul>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"list-buy\">\n");
      out.write("                <form action=\"usuarios\"	 method=\"post\">\n");
      out.write("			<input type=\"hidden\" name=\"buscarUsuario\" value=");
      out.print(user.getNickname() );
      out.write(">\n");
      out.write("			<button class=\"button-view\" type=\"submit\">Ver</button>\n");
      out.write("		</form>\n");
      out.write("             \n");
      out.write("            </div>\n");
      out.write("            </div>\n");
      out.write("            ");

                    }
                } else {
            
      out.write("\n");
      out.write("            <p>No se encontraron usuarios.</p>\n");
      out.write("            ");

                }
            
      out.write("\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    </main> \n");
      out.write("    <script>\n");
      out.write("    document.getElementById('main').style.visibility = 'hidden';\n");
      out.write("\n");
      out.write("    document.addEventListener(\"DOMContentLoaded\", function() {\n");
      out.write("        setTimeout(function() {\n");
      out.write("            document.getElementById('pixel-spinner').style.visibility = 'hidden';\n");
      out.write("            document.getElementById('pixel-spinner').style.display = 'none';\n");
      out.write("            document.getElementById('main').style.visibility = 'visible';\n");
      out.write("        }, 1000); \n");
      out.write("    }); \n");
      out.write("    </script>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof jakarta.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
