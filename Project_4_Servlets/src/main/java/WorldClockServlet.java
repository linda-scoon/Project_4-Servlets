
import java.io.IOException;
import java.io.PrintWriter;
import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WorldClockServlet
 */
public class WorldClockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WorldClockServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String timeZone = request.getParameter("time_zone");
		ZonedDateTime time;

		if (timeZone == null || timeZone.isBlank()) {// no entry
			timeZone = ZonedDateTime.now().getZone() + "";
		}

		out.append("<html><title>WorldClockServlet</title><body>");
		try {
			time = ZonedDateTime.now(ZoneId.of(timeZone.trim()));
			out.append("<h1>Current Time</h1>");
			out.append(String.format("<p>The current time in %s is %s:%s:%s</p>", timeZone, time.getHour(),
					time.getMinute(), time.getSecond()));
			out.append(
					"<form action=\"http://localhost:8080/Project_4_Servlets/WorldClock.html\"><button type=\"submit\">Enter Time Zone</button></form>");
		} catch (DateTimeException e) {// wrong entry
			out.append("<h1 style=\"color: red;\">ERROR: Time Zone Not Recognised</h1>");
			out.append("<h1>LIST OF TIME ZONES</h1>");
			out.append(String.format("<p>%s</p>", ZoneId.getAvailableZoneIds()));
		}
		out.append("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
