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
public class ServletKnjiga extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String naziv = request.getParameter("naziv");
        String opis  = request.getParameter("opis");
        Knjiga k = new Knjiga(naziv, opis);
        String tomcatBase = System.getProperty("catalina.base");
        String datoteka = tomcatBase + "/webapps/listaknjiga.blk";

        System.out.println("=== PUTANJA FAJLA: " + datoteka + " ===");

        ArrayList<Knjiga> lk = new ArrayList<Knjiga>();
        File fDatoteka = new File(datoteka);
        if (!fDatoteka.exists()) {
            fDatoteka.createNewFile();
            System.out.println("File is created!");
        } else {
            if (fDatoteka.length() > 0) {
                try (FileInputStream fis = new FileInputStream(datoteka);
                     ObjectInputStream ois = new ObjectInputStream(fis)) {
                    lk = (ArrayList<Knjiga>) ois.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        lk.add(k);
        try (FileOutputStream fos = new FileOutputStream(datoteka);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(lk);
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
        out.println("<h2>Knjiga :: uneti podaci</h2>");
        out.println("<p>Naziv: " + naziv + "</p>");
        out.println("<p>Opis: " + opis + "</p>");

        out.println("<p style=\"color:gray; font-size:12px;\">Fajl sacuvan na: " + datoteka + "</p>");

        out.println("<p><a href=\"index.html\">Pocetna strana</a></p>");
        out.println("</body>");
        out.println("</html>");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}