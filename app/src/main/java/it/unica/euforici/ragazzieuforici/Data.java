package it.unica.euforici.ragazzieuforici;


import java.io.Serializable;

//TODO generare date in automatico con l'utilizzo della classe Calendar
public class Data implements Serializable {

    private String dataRitiro;

    public Data(){

        this.dataRitiro = "";
    }

    public Data(String dataRitiro){

        this.setDataRitiro(dataRitiro);
    }

        /* GETter & SETter */

    public String getDataRitiro() {
        return dataRitiro;
    }

    public void setDataRitiro(String dataRitiro) {
        this.dataRitiro = dataRitiro;
    }
}
