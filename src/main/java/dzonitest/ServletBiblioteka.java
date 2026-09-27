package dzonitest;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

public class ServletBiblioteka extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String naziv  = request.getParameter("naziv");
        String adresa = request.getParameter("adresa");
        String mesto  = request.getParameter("mesto");
        int zip       = Integer.parseInt(request.getParameter("zip"));
        String pib    = request.getParameter("pib");

        Biblioteka bib = new Biblioteka(naziv, adresa, mesto, zip, pib);

        String tomcatBase = System.getProperty("catalina.base");
        String datoteka = tomcatBase + "/webapps/biblioteka.bbl";

        File fDatoteka = new File(datoteka);
        if (!fDatoteka.exists()) {
            fDatoteka.createNewFile();
            System.out.println("File is created!");
        } else {
            System.out.println("File already exists.");
        }

        try (FileOutputStream fos = new FileOutputStream(datoteka);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(bib);
        } catch (IOException e) {
            e.printStackTrace();
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>HtmlServlet</title>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"stilovi.css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Biblioteka :: uneti podaci</h2>");
        out.println("<p>Naziv: " + naziv + "</p>");
        out.println("<p>Adresa: " + adresa + "</p>");
        out.println("<p>Mesto: " + mesto + "</p>");
        out.println("<p>Zip: " + zip + "</p>");
        out.println("<p>PIB: " + pib + "</p>");
        out.println("<p><a href=\"index.html\">Pocetna strana</a></p>");
        out.println("</body>");
        out.println("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}