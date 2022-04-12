package ca.sait.lab9PWDCHG.servlets;

import ca.sait.lab9PWDCHG.models.User;
import ca.sait.lab9PWDCHG.services.*;
import ca.sait.lab9PWDCHG.utilities.CookieUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Valued Customer
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate(); // just by going to the login page the user is logged out :-) 
        
        //add 3 lines
        Cookie[] cookies = request.getCookies();
        String email = CookieUtil.getCookieValue(cookies, "email");
        request.setAttribute("email", email);
        
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        // save email to a cookie
        Cookie cookie = new Cookie("email", email);
        cookie.setMaxAge(60 * 60 * 24 * 365 * 3);
        response.addCookie(cookie);
        
        AccountService as = new AccountService();
        
        // add 1 line, 1 parameter:path
        String path = getServletContext().getRealPath("/WEB-INF");
        User user = as.login(email, password, path);

        if (user == null) {
            // add 1 line
            request.setAttribute("email", email);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        
        HttpSession session = request.getSession();
        session.setAttribute("user", user);   // CHECK to MODIFY
        session.setAttribute("email", email);
        session.setAttribute("role", user.getRole().getRoleId());
        
        if (user.getRole().getRoleId() == 1) {
            response.sendRedirect("admin");
        } else {
            response.sendRedirect("notes");
        }
    }
}
