package it.unica.euforici.ragazzieuforici;

import java.io.Serializable;

public class Prenotazione implements Serializable {

    private String oggetto1;
    private String quantita1;
    private String oggetto2;
    private String quantita2;
    private String oggetto3;
    private String quantita3;

    public Prenotazione () {

        this.oggetto1 = "";
        this.quantita1 = "";
        this.oggetto2 = "";
        this.quantita2 = "";
        this.oggetto3 = "";
        this.quantita3 = "";
    }

    public Prenotazione (String oggetto1, String quantita1, String oggetto2, String quantita2, String oggetto3, String quantita3) {
        this.setOggetto1(oggetto1);
        this.setQuantita1(quantita1);
        this.setOggetto2(oggetto2);
        this.setQuantita2(quantita2);
        this.setOggetto3(oggetto3);
        this.setQuantita3(quantita3);
    }

    /* GETter & SETter */

    public String getOggetto1() { return oggetto1; }
    public void setOggetto1(String oggetto1) { this.oggetto1 = oggetto1; }

    public String getQuantita1() { return quantita1; }
    public void setQuantita1(String quantita1) { this.quantita1 = quantita1; }

    public String getOggetto2() { return oggetto2; }
    public void setOggetto2(String oggetto2) { this.oggetto2 = oggetto2; }

    public String getQuantita2() { return quantita2; }
    public void setQuantita2(String quantita2) { this.quantita2 = quantita2; }

    public String getOggetto3() { return oggetto3; }
    public void setOggetto3(String oggetto3) { this.oggetto3 = oggetto3; }

    public String getQuantita3() { return quantita3; }
    public void setQuantita3(String quantita3) { this.quantita3 = quantita3; }


}
