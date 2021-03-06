/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.tp_dasi_web;

import fr.insalyon.dasi.dao.JpaUtil;

import fr.insalyon.dasi.ihm.web.action.Action;
import fr.insalyon.dasi.ihm.web.action.AstroNetAction;
import fr.insalyon.dasi.ihm.web.action.AuthentifierClientAction;
import fr.insalyon.dasi.ihm.web.action.AuthentifierEmployeAction;
import fr.insalyon.dasi.ihm.web.action.ContacterMediumAction;
import fr.insalyon.dasi.ihm.web.action.DemarrerConsultAction;
import fr.insalyon.dasi.ihm.web.action.GetInfosConsultationAction;
import fr.insalyon.dasi.ihm.web.action.GetListeMediumsAction;
import fr.insalyon.dasi.ihm.web.action.GetProfilClientAction;
import fr.insalyon.dasi.ihm.web.action.GetMediumByIdAction;
import fr.insalyon.dasi.ihm.web.action.InfosEmployeAction;
import fr.insalyon.dasi.ihm.web.action.InscrireClientAction;
import fr.insalyon.dasi.ihm.web.action.LacherCommentaireAction;
import fr.insalyon.dasi.ihm.web.action.ModifierProfilClientAction;
import fr.insalyon.dasi.ihm.web.serialisation.AstroNetSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.ConfirmationContactSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.DemarrerConsultSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.GetInfosConsultationSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.GetListeMediumsSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.MediumSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.ProfilClientSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.GetProfilClientSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.InfosEmployeSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.InscrireClientSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.LacherCommentaireSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.ModifierProfilClientSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.ProfilEmployeSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.Serialisation;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Yumahey
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        JpaUtil.init();
    }

    @Override
    public void destroy() {
        JpaUtil.destroy();
        super.destroy();
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(true);
        request.setCharacterEncoding("UTF-8");

        String todo = request.getParameter("todo");

        Action action = null;
        Serialisation serialisation = null;

        if (todo != null) {
            switch (todo) {
                case "connecterClient":
                    action = new AuthentifierClientAction();
                    serialisation = new ProfilClientSerialisation();
                    break;
                case "connecterEmploye":
                    action = new AuthentifierEmployeAction();
                    serialisation = new ProfilEmployeSerialisation();
                    break;
                case "getListeMediums":
//                    System.out.println("getListeMediums (Mein Kontrollherr Muller)");
                    action = new GetListeMediumsAction();
                    serialisation = new GetListeMediumsSerialisation();
                    break;
                case "getMediumById":
//                    System.out.println("getMediumById (controleur)");
                    action = new GetMediumByIdAction();
                    serialisation = new MediumSerialisation();
                    break;
                case "contacterMedium":
//                    System.out.println("contacterMedium (controleur)");
                    action = new ContacterMediumAction();
                    serialisation = new ConfirmationContactSerialisation();
                    break;
                case "getProfilClient":
//                    System.out.println("getProfilClient (controleur)");
                    action = new GetProfilClientAction();
                    serialisation = new GetProfilClientSerialisation();
                    break;
                case "modifierProfilClient":
//                    System.out.println("modifierProfilClient (controleur)");
                    action = new ModifierProfilClientAction();
                    serialisation = new ModifierProfilClientSerialisation();
                    break;
                case "inscrireClient":
//                    System.out.println("inscrireClient (controleur)");
                    action = new InscrireClientAction();
                    serialisation = new InscrireClientSerialisation();
                    break;
                case "infosEmploye":
//                    System.out.println("infosEmploye (controleur)");
                    action = new InfosEmployeAction();
                    serialisation = new InfosEmployeSerialisation();
                    break;
                case "getInfosConsultation":
//                    System.out.println("getInfosConsultation (controleur)");
                    action = new GetInfosConsultationAction();
                    serialisation = new GetInfosConsultationSerialisation();
                    break;
                case "demarrerConsult":
//                    System.out.println("astroNet (controleur)");
                    action = new DemarrerConsultAction();
                    serialisation = new DemarrerConsultSerialisation();
                    break;
                case "lacherCommentaire":
//                    System.out.println("lacherCommentaire (controleur)");
                    action = new LacherCommentaireAction();
                    serialisation = new LacherCommentaireSerialisation();
                    break;
                case "astroNet":
//                    System.out.println("astroNet (controleur)");
                    action = new AstroNetAction();
                    serialisation = new AstroNetSerialisation();
                    break;
            }
        }
        
        if (action != null) {
            action.executer(request);
            serialisation.serialiser(request, response);
        }
        else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erreur dans les paramètres de la requête");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
