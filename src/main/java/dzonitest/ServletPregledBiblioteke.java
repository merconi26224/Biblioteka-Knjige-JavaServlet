package dzonitest;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ServletPregledBiblioteke extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String tomcatBase    = System.getProperty("catalina.base");
        String fileBiblioteka = tomcatBase + "/webapps/biblioteka.bbl";
        String fileKnjige     = tomcatBase + "/webapps/listaknjiga.blk";

        
        String obrisiParam = request.getParameter("obrisi");
        if (obrisiParam != null) {
            try {
                int index = Integer.parseInt(obrisiParam);
                File fk = new File(fileKnjige);
                if (fk.exists() && fk.length() > 0) {
                    ArrayList<Knjiga> lk;
                    try (FileInputStream fis = new FileInputStream(fileKnjige);
                         ObjectInputStream ois = new ObjectInputStream(fis)) {
                        lk = (ArrayList<Knjiga>) ois.readObject();
                    } catch (ClassNotFoundException e) {
                        lk = new ArrayList<>();
                    }
                    if (index >= 0 && index < lk.size()) {
                        lk.remove(index);
                        try (FileOutputStream fos = new FileOutputStream(fileKnjige);
                             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                            oos.writeObject(lk);
                        }
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
         
            response.sendRedirect("ServletPregledBiblioteke");
            return;
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Pregled biblioteke</title>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"stilovi.css\">");
        out.println("</head>");
        out.println("<body>");

        // ---- PRIKAZ PODATAKA O BIBLIOTECI ----
        File fBib = new File(fileBiblioteka);
        if (!fBib.exists() || fBib.length() == 0) {
            out.println("<h2>Podaci o biblioteci nisu pronadjeni.</h2>");
            out.println("<p><a href=\"index.html\">Pocetna strana</a></p>");
            out.println("</body></html>");
            return;
        }

        Biblioteka bib = new Biblioteka();
        try (FileInputStream fis = new FileInputStream(fileBiblioteka);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            bib = (Biblioteka) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        out.println("<h1>Biblioteka</h1>");
        out.println("<p>Naziv: " + bib.getNaziv() + "</p>");
        out.println("<p>Adresa: " + bib.getAdresa() + "</p>");
        out.println("<p>Mesto: " + bib.getMesto() + "</p>");
        out.println("<p>Zip: " + bib.getZip() + "</p>");
        out.println("<p>PIB: " + bib.getPib() + "</p>");

        out.println("<hr>");

        // ---- PRIKAZ KNJIGA ----
        File fKnjige = new File(fileKnjige);

        if (!fKnjige.exists() || fKnjige.length() == 0) {
            out.println("<h2>Biblioteka ne sadrzi nijednu knjigu.</h2>");
        } else {
            ArrayList<Knjiga> lk = new ArrayList<>();
            try (FileInputStream fis = new FileInputStream(fileKnjige);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                lk = (ArrayList<Knjiga>) ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            if (lk.isEmpty()) {
                out.println("<h2>Biblioteka ne sadrzi nijednu knjigu.</h2>");
            } else {
                out.println("<h2>Ukupan broj knjiga: " + lk.size() + "</h2>");
                out.println("<h3>Spisak knjiga:</h3>");
                for (int i = 0; i < lk.size(); i++) {
                    Knjiga k = lk.get(i);
                    out.println("<p>");
                    out.println("Naziv: " + k.getNaziv() + " | Opis: " + k.getOpis());
                    out.println(" &nbsp; <a href=\"ServletPregledBiblioteke?obrisi=" + i + "\" "
                              + "onclick=\"return confirm('Obrisi knjigu: " + k.getNaziv() + "?')\""
                              + " style=\"color:red;\">[Obrisi]</a>");
                    out.println("</p>");
                }
            }
        }

        out.println("<hr>");
        out.println("<p><a href=\"index.html\">Pocetna strana</a></p>");
        out.println("</body>");
        out.println("</html>");
    }

    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}