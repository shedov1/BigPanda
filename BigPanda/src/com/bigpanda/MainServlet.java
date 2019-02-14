package com.bigpanda;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String GET_EVENT_COUNT = "getEvent", GET_WORD_COUNT = "getWord";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nameEvent = request.getParameter(GET_EVENT_COUNT);
		String nameWord = request.getParameter(GET_WORD_COUNT);

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0L);

		int counter;

		if (nameEvent != null) {
			counter = LinesData.getInstance().getEventCount(nameEvent);
			response.getWriter().write("Event " + nameEvent + " --> " + counter);
			response.setStatus(HttpServletResponse.SC_OK);
		} else if (nameWord != null) {
			counter = LinesData.getInstance().getWordCount(nameWord);
			response.getWriter().write("Event " + nameWord + " --> " + counter);
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.getWriter().write("No action was taken");
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
