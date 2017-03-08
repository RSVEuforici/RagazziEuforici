package it.unica.euforici.ragazzieuforici;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Riepilogo extends AppCompatActivity {

    Dati datiUtente;
    Prenotazione resUtente;
    Data dataPrenotazione;

    TextView insNome, insIndirizzo, insTelefono, insMail;
    TextView rifiuti;
    TextView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riepilogo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        datiUtente = leggiDati();
        resUtente = leggiPrenotazione();
        dataPrenotazione = leggiData();
        insNome = (TextView) findViewById(R.id.nomeUtente);
        insIndirizzo = (TextView) findViewById(R.id.textView6);
        insTelefono = (TextView) findViewById(R.id.textView8);
        insMail = (TextView) findViewById(R.id.textView9);
        rifiuti = (TextView) findViewById(R.id.textView16);
        data = (TextView) findViewById(R.id.primoRifiuto);


        updateUi();


    }

    private void updateUi(){

        insNome.setText(datiUtente.getNome() +" "+ datiUtente.getCognome());
        insIndirizzo.setText(datiUtente.getIndirizzo());
        insTelefono.setText(datiUtente.getTelefono());
        insMail.setText(datiUtente.getMail());
        data.setText(dataPrenotazione.getDataRitiro());
        if(resUtente.getOggetto3().equals("") &&  !resUtente.getOggetto2().equals("")){
        rifiuti.setText(resUtente.getOggetto1()+" x "+ resUtente.getQuantita1()+
                "\n" + resUtente.getOggetto2()+" x "+ resUtente.getQuantita2() );
        }else { if (resUtente.getOggetto2().equals("")){

            rifiuti.setText(resUtente.getOggetto1()+" x "+ resUtente.getQuantita1());

        }else {

            rifiuti.setText(resUtente.getOggetto1()+" x "+ resUtente.getQuantita1() +
                    "\n" + resUtente.getOggetto2()+" x "+ resUtente.getQuantita2() +
                    "\n" + resUtente.getOggetto3()+" x "+ resUtente.getQuantita3());
        }
        }
        }


    public Dati leggiDati() { //TODO integrarlo nella classe
        Dati dati = new Dati();
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(getApplicationContext().getFilesDir() + "user.obj"));
            dati = (Dati) is.readObject();
            is.close();
        } catch (FileNotFoundException e){
            System.out.println("nessun file esiste");
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return dati;
    }

    public Prenotazione leggiPrenotazione() { //TODO integrarlo nella classe
        Prenotazione prenotazione = new Prenotazione();
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(getApplicationContext().getFilesDir() + "reserve.obj"));
            prenotazione = (Prenotazione) is.readObject();
            is.close();
        } catch (FileNotFoundException e){
            System.out.println("nessun file esiste");
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return prenotazione;
    }


    public Data leggiData() { //TODO integrarlo nella classe
        Data data = new Data();
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(getApplicationContext().getFilesDir() + "data.obj"));
            data = (Data) is.readObject();
            is.close();
        } catch (FileNotFoundException e){
            System.out.println("nessun file esiste");
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return data;

    }

    public void confermaPrenotazione(View view){ //onclick sul bottone di conferma

        Intent ii = new Intent (this, FinePrenotazione.class);
        startActivity(ii);

    }

    public void help(View view) { // onClick su tasto aiuto
        Intent ii = new Intent (this, Regole.class);
        startActivity(ii);
    }

}
