package filters;

import ca.sait.lab9PWDCHG.models.User;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author USER
 */
public class AdminFilter implements Filter {
    
    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        // code that is executed before the servlet
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        
        HttpSession session = httpRequest.getSession();
        User user = (User) session.getAttribute("user");
        //session.setAttribute("role", user.getRole().getRoleId());    
            
        // Check if user if an admin or not
        if(user.getRole().getRoleId() == 1) { // If admin:
           chain.doFilter(request, response); 
        } else { // If not admin:
            httpResponse.sendRedirect("/notes");
        }
        /*
        String email = (String)session.getAttribute("email");  
        if (email == null) {
                httpResponse.sendRedirect("login");
                return;
        }
            
        chain.doFilter(request, response); // execute the servlet
        */
        
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {  
        
    }
    
}
