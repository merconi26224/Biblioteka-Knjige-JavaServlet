package dzonitest;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ServletPregledKnjiga extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String tomcatBase = System.getProperty("catalina.base");
        String datoteka = tomcatBase + "/webapps/listaknjiga.blk";

        ArrayList<Knjiga> lk = new ArrayList<Knjiga>();

        File fDatoteka = new File(datoteka);

        if (!fDatoteka.exists() || fDatoteka.length() == 0) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Greska</title></head><body>");
            out.println("<h2>Greska: Lista knjiga nije pronadjena.</h2>");
            out.println("<p><a href=\"index.html\">Pocetna strana</a></p>");
            out.println("</body></html>");
            return;
        }

        try (FileInputStream fis = new FileInputStream(datoteka);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            lk = (ArrayList<Knjiga>) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Biblioteka: Lista knjiga</title>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"stilovi.css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Lista knjiga</h1>");

        for (Knjiga temp : lk) {
            out.println("<p>Naziv: " + temp.getNaziv() + " | Opis: " + temp.getOpis() + "</p>");
        }

        out.println("<p><a href=\"index.html\">Pocetna strana</a></p>");
        out.println("</body>");
        out.println("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}