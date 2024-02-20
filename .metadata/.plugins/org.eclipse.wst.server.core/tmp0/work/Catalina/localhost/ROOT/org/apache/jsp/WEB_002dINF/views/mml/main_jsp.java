/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.83
 * Generated at: 2024-02-20 05:11:20 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.mml;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class main_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
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

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
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

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta charset=\"UTF-8\">\n");
      out.write("<title>Insert title here</title>\n");
      out.write("</head>\n");
      out.write("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css\">\n");
      out.write("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9\" crossorigin=\"anonymous\">\n");
      out.write("<style>\n");
      out.write("body{\n");
      out.write("	background-color : black;\n");
      out.write("	color: white;\n");
      out.write("	padding: 0;\n");
      out.write("	margin: 0;\n");
      out.write("}\n");
      out.write(".parentsDiv{\n");
      out.write("	height: 100vh;\n");
      out.write("	width: 100vw;\n");
      out.write("	display: flex;\n");
      out.write("	flex-direction: column;\n");
      out.write("}\n");
      out.write(".HeaderDiv{\n");
      out.write("	height: 60px;\n");
      out.write("}\n");
      out.write(".MidDiv{\n");
      out.write("	height: calc(100vh - 224px);\n");
      out.write("	min-height: calc(100vh - 224px);\n");
      out.write("	display:flex;\n");
      out.write("}\n");
      out.write(".FooterDiv{\n");
      out.write("	height: 154px;\n");
      out.write("	padding: 0;\n");
      out.write("	margin: 0;\n");
      out.write("	position:sticky;\n");
      out.write("	top: calc(100vh - 224px);\n");
      out.write("}\n");
      out.write(".SearchLineDiv{\n");
      out.write("\n");
      out.write("}\n");
      out.write(".UserDetailDiv{\n");
      out.write("	width: 250px;\n");
      out.write("	min-width: 250px;\n");
      out.write("}\n");
      out.write(".ContentsDiv{\n");
      out.write("	width: 100%;\n");
      out.write("	height:calc(100vw-550px);\n");
      out.write("	display: flex;\n");
      out.write("	justify-content: space-between;\n");
      out.write("	overflow: scroll;\n");
      out.write("}\n");
      out.write(".ContentDiv{\n");
      out.write("	width: 60%;\n");
      out.write("	height: calc(100vw-550px);\n");
      out.write("	overflow: scroll;\n");
      out.write("	align-items: flex-start;\n");
      out.write("}\n");
      out.write(".ContentDetailsDiv{\n");
      out.write("	width: 40%;\n");
      out.write("	min-width: 350px;\n");
      out.write("	height: 100%\n");
      out.write("}\n");
      out.write(".MusicBox{\n");
      out.write("	padding: 0;\n");
      out.write("	margin: 5px;\n");
      out.write("    \n");
      out.write("}\n");
      out.write(".searchDiv{\n");
      out.write("	background-color: black;\n");
      out.write("	width: 650px;\n");
      out.write("	height: 75px;\n");
      out.write("	margin: 5px;\n");
      out.write("}\n");
      out.write(".searchDiv img{\n");
      out.write("	margin-left: 10px;\n");
      out.write("}\n");
      out.write(".searchDiv span{\n");
      out.write("	text-align: center;\n");
      out.write("}\n");
      out.write(".playIcon {\n");
      out.write("	display: inline;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".navDiv{\n");
      out.write("	position:sticky;\n");
      out.write("	padding-left: 10px;\n");
      out.write("	padding-right: 10px;\n");
      out.write("}\n");
      out.write(".nav {\n");
      out.write("    --bs-nav-link-color: white;\n");
      out.write("    --bs-nav-link-hover-color: white;\n");
      out.write("}\n");
      out.write(".navbar{\n");
      out.write("	background-color: black;\n");
      out.write("}\n");
      out.write(".nav-link{\n");
      out.write("	color: gold;\n");
      out.write("}\n");
      out.write(".nav-link:hover {\n");
      out.write("	color: white;\n");
      out.write("}\n");
      out.write(".nav-link:focus {\n");
      out.write("	color: white;\n");
      out.write("}\n");
      out.write(".table{\n");
      out.write("	max-width: 650px;\n");
      out.write("}\n");
      out.write("</style>\n");
      out.write("<body>\n");
      out.write("<div class=\"parentsDiv\">\n");
      out.write("	<div class=\"HeaderDiv\">\n");
      out.write("		<div class=\"SearchLineDiv\">\n");
      out.write("				<div class=\"navDiv\">\n");
      out.write("					<nav class=\"navbar border-bottom border-body\" data-bs-theme=\"dark\">\n");
      out.write("						<ul class=\"nav nav-underline justify-content-end\">\n");
      out.write("							<li class=\"nav-item\"><a class=\"nav-link\" aria-current=\"page\" href=\"#\">Active</a></li>\n");
      out.write("							<li class=\"nav-item\"><a class=\"nav-link\" href=\"#\">Link</a></li>\n");
      out.write("							<li class=\"nav-item\"><a class=\"nav-link\" href=\"#\">Link</a></li>\n");
      out.write("						</ul>\n");
      out.write("						<form class=\"d-flex\" role=\"search\">\n");
      out.write("        					<input class=\"form-control me-2 \" id=\"searchKeyword\" type=\"search\" placeholder=\"Search\" aria-label=\"Search\" onkeypress=\"searchEnter(e)\">\n");
      out.write("        					<button class=\"btn\" type=\"button\" id=\"searchBtn\">Search</button>\n");
      out.write("      					</form>\n");
      out.write("					</nav>\n");
      out.write("				</div>\n");
      out.write("		</div>	\n");
      out.write("	</div>\n");
      out.write("	<div class=\"MidDiv\">\n");
      out.write("		<div class=\"UserDetailDiv\">UserDetailDiv</div>\n");
      out.write("		<div class=\"ContentsDiv\">\n");
      out.write("			<div class=\"contentDiv\">\n");
      out.write("				<div class=\"searchDiv\">\n");
      out.write("						<ul class=\"nav nav-underline justify-content-center SearchTab\">\n");
      out.write("							<li class=\"nav-item\"><a class=\"nav-link SearchTabAlbums\" href=\"#\">Albums</a></li>\n");
      out.write("							<li class=\"nav-item\"><a class=\"nav-link SearchTabArtists\" href=\"#\">Artists</a></li>\n");
      out.write("							<li class=\"nav-item\"><a class=\"nav-link SearchTabTracks\" href=\"#\">Tracks</a></li>\n");
      out.write("						</ul>\n");
      out.write("						<table id=\"SearchBox\" class=\"table table-dark table-hover\">\n");
      out.write(" 					<tr>\n");
      out.write("						<th></th>\n");
      out.write("						<th>Artist</th>\n");
      out.write("						<th>Type</th>\n");
      out.write("						<th>Release Date</th>\n");
      out.write("						<th>Name</th>\n");
      out.write("						<th></th>\n");
      out.write("					</tr>\n");
      out.write("					<tr>\n");
      out.write("						<td>\n");
      out.write("						<img alt=\"\" src=\"https://i.scdn.co/image/ab67616d0000b273a3b39c1651a617bb09800fd8\" style=\"height:75px; width:75px; display:inline;\">\n");
      out.write("						</td>\n");
      out.write("						<td>찰리푸스</td>\n");
      out.write("						<td>Type</td>\n");
      out.write("						<td>Release Date</td>\n");
      out.write("						<td>Name</td>\n");
      out.write("						<td><i class=\"bi bi-play-circle-fill\"></i></td>\n");
      out.write("					</tr> \n");
      out.write("				</table>\n");
      out.write("				</div>\n");
      out.write("			</div>\n");
      out.write("			<div class=\"ContentDetailsDiv\">ContentDetailsDiv</div>		\n");
      out.write("		</div>\n");
      out.write("	</div>\n");
      out.write("	<div class=\"FooterDiv\">\n");
      out.write("		<div class=\"MusicBox\" id=\"MusicBox\">\n");
      out.write("				<iframe style=\"border-radius: 12px\"\n");
      out.write("					src=\"https://open.spotify.com/embed/artist/6VuMaDnrHyPL1p4EHjYLi7?utm_source=generator&autoplay=1\"\n");
      out.write("					width=\"100%\" height=\"152\" frameBorder=\"0\" allowfullscreen=\"\"\n");
      out.write("					allow=\"autoplay; clipboard-write; encrypted-media; fullscreen; picture-in-picture\"\n");
      out.write("					loading=\"lazy\"> </iframe>\n");
      out.write("			</div>\n");
      out.write("	</div>\n");
      out.write("	<div></div>\n");
      out.write("</div>\n");
      out.write("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js\" integrity=\"sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa\" crossorigin=\"anonymous\"></script>\n");
      out.write("<script src=\"/resources/js/spotifyAPI.js\"></script>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
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
