package com.namoo.shop.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.namoosori.namooshop.service.facade.CustomerService;
import com.namoosori.namooshop.service.factory.NamooShopServiceFactory;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 2548161997944471543L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		CustomerService customerService = NamooShopServiceFactory.getInstance().getCustomerService();
		if (session.getAttribute("loginId") != null) {
			resp.sendRedirect("main");
			return;
		}
		
		if(customerService.login(req.getParameter("loginId"), req.getParameter("password"))){
				session.setAttribute("loginId", req.getParameter("loginId"));
				resp.sendRedirect("main");
			}
			
		else if(req.getParameter("loginId")!=null&&req.getParameter("password")!=null&&(!customerService.login(req.getParameter("loginId"), req.getParameter("password")))){
			session.setAttribute("hi", "error");
			resp.sendRedirect("login");
		}

		
	}
	
	

}
