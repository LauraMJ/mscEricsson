package com.ericsson.msc.group5.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		request.getRequestDispatcher("logoutredirect.html").include(request, response);

		request.logout();
	}

	// public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
	// HttpServletRequest request = (HttpServletRequest) req;
	// HttpServletResponse response = (HttpServletResponse) res;
	//
	// HttpSession session = request.getSession(false);
	// if (session == null || session.getAttribute("username") == null) {
	// response.sendRedirect("login.html");
	// }
	// else {
	// response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	// response.setHeader("Pragma", "no-cache");
	// response.setDateHeader("Expires", 0);
	// chain.doFilter(req, res);
	// }
	// }
}