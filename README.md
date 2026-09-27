# Biblioteka i knjige — Java web projekat

Studentski web projekat za unos i pregled podataka o biblioteci i knjigama. Korisnički deo čine HTML stranice, a zahteve obrađuju Jakarta servleti.

## Sadržaj

- `src/main/java/dzonitest/` — klase `Biblioteka` i `Knjiga`, kao i servleti za unos i pregled podataka;
- `src/main/webapp/` — HTML stranice, CSS i `WEB-INF/web.xml` sa podešavanjima servleta.

Podaci se čuvaju u datotekama na lokalnom Tomcat serveru. Projekat ne koristi bazu podataka.

## Pokretanje

Uvesti projekat u Eclipse kao web projekat i pokrenuti ga na serveru koji podržava **Jakarta Servlet 5.0**. Zatim otvoriti početnu stranicu aplikacije (`index.html`) preko lokalne adrese servera.

Folder `build` sadrži kompajlirane klase i nije deo repozitorijuma.
