package controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetTheWeather;
import model.weatherBean;

/**
 * Servlet implementation class OWservlet
 */
@WebServlet("/OWservlet")
public class OWservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OWservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
		HttpSession session = request.getSession();
	String cityStr = request.getParameter("city");
	String countryStr = request.getParameter("country");
	boolean b = true;
	
	try {
		Cookie cArr[] = request.getCookies();
		for(Cookie c : cArr) {
			if(c.getValue().equals(cityStr+"_"+countryStr)) {
				b=false;
				System.out.println("cookie is identical");
			}
		}
		
	} catch (Exception e) {
		System.out.println(e);
	}
	
	if(b) {
	Cookie ck = new Cookie("rec", cityStr+"_"+countryStr);
	ck.setMaxAge(1200);
	response.addCookie(ck);
	
	weatherBean wBean = new weatherBean(cityStr, countryStr);
	
	GetTheWeather.getWeather(wBean);
	
	session.setAttribute("wBean", wBean);

	RequestDispatcher rd = request.getRequestDispatcher("showWeather.jsp");
	rd.forward(request, response);
	} else {
	RequestDispatcher rd = request.getRequestDispatcher("showWeather.jsp");
	rd.forward(request, response);}


	
	}

}
