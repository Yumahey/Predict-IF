package fr.insalyon.dasi.metier.service;

import fr.insalyon.dasi.dao.ClientDao;
import fr.insalyon.dasi.dao.EmployeDao;
import fr.insalyon.dasi.dao.MediumDao;
import fr.insalyon.dasi.dao.SpiriteDao;
import fr.insalyon.dasi.dao.CartomancienDao;
import fr.insalyon.dasi.dao.AstrologueDao;
import fr.insalyon.dasi.dao.ConsultationDao;
import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Spirite;
import fr.insalyon.dasi.metier.modele.Cartomancien;
import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.ProfilAstral;
import fr.insalyon.dasi.util.AstroTest;
import fr.insalyon.dasi.util.Message;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author DASI Team
 */
public class Service {

    protected ClientDao clientDao = new ClientDao();
    protected EmployeDao employeDao = new EmployeDao();
    protected MediumDao mediumDao = new MediumDao();
    protected SpiriteDao spiriteDao = new SpiriteDao();
    protected CartomancienDao cartomancienDao = new CartomancienDao();
    protected AstrologueDao astrologueDao = new AstrologueDao();
    protected ConsultationDao consultationDao = new ConsultationDao();
    
    // Client
    
    public Long inscrireClient(Client client) {
        Long resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            clientDao.creer(client);
            JpaUtil.validerTransaction();
            resultat = client.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service inscrireClient(client)", ex);
            JpaUtil.annulerTransaction();
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public Client rechercherClientParId(Long id) {
        Client resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = clientDao.chercherParId(id);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherClientParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public Client authentifierClient(String mail, String motDePasse) {
        Client resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche du client
            Client client = clientDao.chercherParMail(mail);
            if (client != null) {
                // Vérification du mot de passe
                if (client.getMotDePasse().equals(motDePasse)) {
                    resultat = client;
                }
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service authentifierClient(mail,motDePasse)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public List<Client> listerClients() {
        List<Client> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = clientDao.listerClients();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerClients()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    // return true si la modification s'est bien passée
    public boolean updateClient(Client client, String nom, String prenom, String mail, String motDePasse, String tel, int jour, int mois, int annee){
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setMail(mail);
        client.setMotDePasse(motDePasse);
        client.setTel(tel);
        client.setDateNaissance(annee, mois, jour);
        return true;
    }
    
    
    // graphiques : obtenirClassementMediums, ...
    
    
    // Employe
    
    public Long inscrireEmploye(Employe employe) {
        Long resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            employeDao.creer(employe);
            JpaUtil.validerTransaction();
            resultat = employe.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service inscrireEmploye(employe)", ex);
            JpaUtil.annulerTransaction();
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public Employe rechercherEmployeParId(Long id) {
        Employe resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = employeDao.chercherParId(id);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherEmployeParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public Employe authentifierEmploye(String mail, String motDePasse) {
        Employe resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche du employe
            Employe employe = employeDao.chercherParMail(mail);
            if (employe != null) {
                // Vérification du mot de passe
                if (employe.getMotDePasse().equals(motDePasse)) {
                    resultat = employe;
                }
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service authentifierEmploye(mail,motDePasse)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public List<Employe> listerEmployes() {
        List<Employe> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = employeDao.listerEmployes();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerEmployes()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    // Medium
    
    public Long inscrireSpirite(Spirite spirite) {
        Long resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            spiriteDao.creer(spirite);
            JpaUtil.validerTransaction();
            resultat = spirite.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service inscrireSpirite(spirite)", ex);
            JpaUtil.annulerTransaction();
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public Long inscrireCartomancien(Cartomancien cartomancien) {
        Long resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            cartomancienDao.creer(cartomancien);
            JpaUtil.validerTransaction();
            resultat = cartomancien.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service inscrireCartomancien(cartomancien)", ex);
            JpaUtil.annulerTransaction();
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public Long inscrireAstrologue(Astrologue astrologue) {
        Long resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            astrologueDao.creer(astrologue);
            JpaUtil.validerTransaction();
            resultat = astrologue.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service inscrireAstrologue(astrologue)", ex);
            JpaUtil.annulerTransaction();
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public Medium rechercherMediumParId(Long id) {
        Medium resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = mediumDao.chercherParId(id);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherMediumParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public Medium rechercherMediumParDenom(String denom) {
        Medium resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = mediumDao.chercherParDenom(denom);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherMediumParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public List<Medium> listerMediums() {
        List<Medium> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = mediumDao.listerMediums();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerMediums()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    // Consultation
    
    public Long enregistrerConsultation(Consultation consultation) {
        Long resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            consultationDao.creer(consultation);
            JpaUtil.validerTransaction();
            resultat = consultation.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service enregistrerConsultation(consultation)", ex);
            JpaUtil.annulerTransaction();
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    // renvoie toutes les consultations effectuees liees a un client, pour avoir le commentaire et les autres infos (date, employe, medium)
    public List<Consultation> rechercherConsultsParClient(Client client){
        List<Consultation> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = consultationDao.chercherParClient(client);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service obtenirCommentairesClient()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    
    
    // TODO
    // cherche un employe pour incarner un medium donné
    // return true si il y a un employé dispo, et lui envoie une notification
    // return false si aucun employé correspondant n'est disponible
    public Consultation contacterMedium(Medium medium, Client client){
        // trouver le bon employe
        Employe employe = null;
        JpaUtil.creerContextePersistance();
        try {
            employe = employeDao.chercherEmployePourConsultation(medium.getGenre());
            System.out.println("Employé choisi pour la consultation : " + employe.toString());
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service contacterMedium()", ex);
            employe = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        // si aucun employe disponible
        if(employe == null){
            return null;
        }
        
        Consultation consult = new Consultation(employe, client, medium);
        
        // persister consult
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DASI-PU");
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            em.persist(consult);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service contacterMedium()", ex);
            try {
                em.getTransaction().rollback();
            }
            catch (IllegalStateException ex2) {
                // Ignorer cette exception...
            }
        } finally {
            em.close();
        }
        
        // envoyer notif a l'employe
        StringWriter message = new StringWriter();
        PrintWriter notificationWriter = new PrintWriter(message);
        
        notificationWriter.println("Bonjour " + employe.getPrenom() + ".");
        notificationWriter.println("Consultation requise pour M/Mme " + client.getPrenom() + " " + client.getNom() + ".");
        notificationWriter.println("Medium a incarner : " + medium.getDenom());

        Message.envoyerNotification(
                employe.getTel(),
                message.toString()
            );
                
        
        return consult;
    }
    
    public List<String> obtenirPredictions(Client c, int amour, int sante, int travail){
        
        AstroTest astro = new AstroTest();
        try{
            return astro.getPredictions(c.getProfil().getCouleur(), c.getProfil().getAnimal(), amour, sante, travail);
        }catch(Exception e){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service obtenirPredictions()", e);
            return null;
        }
    }
    
    
    // déplacée comme méthode membre de la classe Client pour plus de cohérence
    /*public ProfilAstral obtenirPA(Client client){
        
        ProfilAstral profil;
        
        try{
            AstroTest astro = new AstroTest();
            List<String> res = astro.getProfil(client.getPrenom(), client.getDateNaissance());

            profil = new ProfilAstral(res.get(0), res.get(1), res.get(2), res.get(3));
        }catch(Exception e){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service obtenirPA()", e);
            profil = null;
        }
        
        return profil;
    }*/
    
    
    
    // inutile ?
    
    public Spirite rechercherSpiriteParId(Long id) {
        Spirite resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = spiriteDao.chercherParId(id);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherSpiriteParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public List<Spirite> listerSpirites() {
        List<Spirite> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = spiriteDao.listerSpirites();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerSpirites()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    
    public Cartomancien rechercherCartomancienParId(Long id) {
        Cartomancien resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = cartomancienDao.chercherParId(id);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherCartomancienParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public List<Cartomancien> listerCartomanciens() {
        List<Cartomancien> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = cartomancienDao.listerCartomanciens();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerCartomanciens()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    
    public Astrologue rechercherAstrologueParId(Long id) {
            Astrologue resultat = null;
            JpaUtil.creerContextePersistance();
            try {
                    resultat = astrologueDao.chercherParId(id);
            } catch (Exception ex) {
                    Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherAstrologueParId(id)", ex);
                    resultat = null;
            } finally {
                    JpaUtil.fermerContextePersistance();
            }
            return resultat;
    }

    public List<Astrologue> listerAstrologues() {
        List<Astrologue> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
                resultat = astrologueDao.listerAstrologues();
        } catch (Exception ex) {
                Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerAstrologues()", ex);
                resultat = null;
        } finally {
                JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    
}