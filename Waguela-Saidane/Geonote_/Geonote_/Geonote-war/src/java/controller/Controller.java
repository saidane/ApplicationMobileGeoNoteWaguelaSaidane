
package controller;

import entity.Utilisateur;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.NoteFacade;
import session.UtilisateurFacade;

/**
 *
 * @author Axel Brice WAGUELA & Hela SAIDANE 
 */
@WebServlet(name = "Controller", loadOnStartup = 1,
urlPatterns = {
    "/login",
    "/admin",
    "/afficher_Notes",
    "/ajouter_Note",
    "/creerParcours", 
    " /modifier_Parcours",
    "/user",
    "/user_Parcours"
})
public class Controller extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB
    private NoteFacade noteFacade;
    @EJB
    private UtilisateurFacade utilisateurFacade;

    /* @Override
     public void init() throws ServletException {

     // store category list in servlet context
     getServletContext().setAttribute("categor", utilisateurFacade.findAll());
     }*/
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. 
             out.println("<html>");
             out.println("<head>");
             out.println("<title>Servlet Controller</title>");            
             out.println("</head>");
             out.println("<body>");
             out.println("<h1>Servlet Controller at " + request.getContextPath() + "</h1>");
             out.println("</body>");
             out.println("</html>");*/
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();
        String email = request.getParameter("InterfaceLogin");
        String password = request.getParameter("InterfacePsw");
        boolean found = false;
        if (userPath.equals("/login")) {
            List<Utilisateur> list = utilisateurFacade.findAll();
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getLogin().equals(email) && list.get(i).getPassword().equals(password)) {
                        found = true;
                        if (list.get(i).getIdUtilisateur().equals(1)) {  //it means the usr is ADMIN
                            userPath = "/admin.html";
                        } else {
                            userPath = "/user.html";
                        }
                    }
                }
                if (found == false) {
                    userPath = "/index.html";
                }
            }

        } else if (userPath.equals("/admin")) {
            userPath = "/admin.html";
        } else if (userPath.equals("/modifier_Parcours")) {
            userPath = "/modifier_Parcours.html";
        } else if (userPath.equals("/ajouter_Note")) {
            userPath = "/ajouter_Note.html";

        } else if (userPath.equals(
                "/Creer_Parcours")) {

            userPath = "/creer_Parcours.html";

        } else if (userPath.equals(
                "/afficher_Notes")) {
            //répurérer interface, mettre dans la bdd
            userPath = "/afficher_Notes.html";

        } else if (userPath.equals(
                "/user")) {
            //répurérer interface, mettre dans la bdd
            userPath = "/user.html";

        }
        else if (userPath.equals(
                "/user_Parcours")) {
            //répurérer interface, mettre dans la bdd
            userPath = "/user_Parcours.html";

        }
        String url = "/WEB-INF/view" + userPath;

        if (userPath.equals("/index.html")) {
            url = userPath;

        }
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
