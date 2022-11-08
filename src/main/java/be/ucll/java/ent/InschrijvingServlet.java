package be.ucll.java.ent;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/inschrijving")
public class InschrijvingServlet extends HttpServlet {
    // private static final String COOKIE_STUDENT = "StudentInfo";
    private String stud_name = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        /*
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie: cookies){
                if (cookie != null && cookie.getName() != null && cookie.getName().trim().equalsIgnoreCase(COOKIE_STUDENT)) {
                    String tmp = cookie.getValue();
                    if (tmp != null && tmp.trim().length() > 0) {
                        stud_name = URLDecoder.decode(tmp.trim(), StandardCharsets.UTF_8.toString());
                    }
                }
            }
        } else {
            this.getServletContext().log("No cookies found");
        }
        */

        try (PrintWriter pw = response.getWriter()) {
            pw.println("<html>");
            pw.println("<head>");
            pw.println("<title>Inschrijving</title>");
            pw.println("</head>");
            pw.println("<body>");
            pw.println("  <p><h3>Schrijf u in voor het opleidingsonderdeel 'Java Enterprise'.</h3>");
            pw.println("  <form action='" + request.getContextPath() + request.getServletPath() + "' method='post'>");
            pw.println("    Student naam: <input type='text' name='stud_name' size='50' maxlength='50' value='" + stud_name + "' /><br/><br/> ");
            pw.println("    <input type='submit' value='Inschrijven'/>");
            pw.println("  </form></p>");
            pw.println("</body>");
            pw.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        stud_name = request.getParameter("stud_name");

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter pw = response.getWriter()) {
            pw.println("<html>");
            pw.println("<head>");
            pw.println("  <title>Hello Servlets</title>");
            pw.println("</head>");
            pw.println("<body>");
            if (stud_name != null && stud_name.trim().length() > 0) {
                pw.println("  <p><h3>Bedankt " + stud_name + ". U bent ingeschreven</h3>");

                /*
                // Create a cookie. Een 'cookie' kan zeer weinig info bevatten en de inhoud die je wil opslaan hou je best onder de 4000 bytes.
                String encodedStr = URLEncoder.encode(stud_name, StandardCharsets.UTF_8.toString());
                Cookie cookie = new Cookie(COOKIE_STUDENT, encodedStr);
                cookie.setMaxAge(60 * 60 * 24 * 7); // Seconds, 60 * 60 * 24 * 7 = 1 week
                response.addCookie(cookie);
                this.getServletContext().log("Added cookie with name: " + COOKIE_STUDENT + ", value: " + encodedStr);
                */
            } else {
                pw.println("  <p><h3>De naam ontbreekt. U bent niet ingeschreven</h3>");
            }
            pw.println("  <br/><a href='." + request.getServletPath() + "'>Keer terug naar inschrijving</a>");
            pw.println("  <br/><br/>Session Id: " + request.getSession().getId());
            pw.println("</body>");
            pw.println("</html>");
        }
    }

}