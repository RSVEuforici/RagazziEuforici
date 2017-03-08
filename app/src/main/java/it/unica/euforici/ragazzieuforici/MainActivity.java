package it.unica.euforici.ragazzieuforici;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;


public class MainActivity extends AppCompatActivity {

    TextView elimina;
    File file;
    Prenotazione resUtente;


    TextView testoPrenotazioni, prenotazione, data, ora, modifica, rifiuti;
    ImageView icona1, icona2, icona3;
    LinearLayout linearLayout;

    RelativeLayout prova1, prova2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* ASSEGNAMENTI VARI
        * TODO cambiere gli id di default con qualcosa di esplicativo */

        elimina = (TextView) findViewById(R.id.aggiungirifiuto);

        testoPrenotazioni = (TextView) findViewById(R.id.testoPrenotazioni);
        prenotazione = (TextView) findViewById(R.id.catPrimoRifiuto);
        data = (TextView) findViewById(R.id.primoRifiuto);
        ora = (TextView) findViewById(R.id.textView3);
        rifiuti = (TextView) findViewById(R.id.textView16);
        modifica = (TextView) findViewById(R.id.rimuovi);

        file = new File(getApplicationContext().getFilesDir() + "reserve.obj");

        icona1 = (ImageView) findViewById(R.id.imageView3);
        icona2 = (ImageView) findViewById(R.id.imageView11);
        icona3 = (ImageView) findViewById(R.id.imageView5);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout1);

        prova1 = (RelativeLayout) findViewById(R.id.prova1);
        prova2 = (RelativeLayout) findViewById(R.id.prova2);

        updateUi();


        elimina.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){ //OnClick  elimina prenotazione

                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this ); //finestra di dialogo per la conferma
                builder1.setMessage("Sei sicuro di voler eliminare la prenotazione?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Si",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(MainActivity.this, CancellaPrenotazione.class);
                                file.delete();
                                updateUi();
                                dialog.cancel();
                                startActivity(intent);


                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void nuovaPrenotazione(View view) { //onClick nuova prenotazione
        Intent intent = new Intent(this, DatiAnagrafici.class);
        startActivity(intent);
    }

    public void modificaData(View view) { // onClick modifica data

        Intent intent = new Intent(this, ModificaData.class);
        startActivity(intent);
    }

    public void updateUi() { //gestione dell'UI in base alle modifiche

        file = new File(getApplicationContext().getFilesDir() + "reserve.obj");

        if (!file.exists()) { //se non esiste una prenotazione salvata, nascondi tutte le etichette e icone

            testoPrenotazioni.setText("Non hai prenotazioni attive al momento");
            prova1.setVisibility(View.GONE);
            prova2.setVisibility(View.GONE);
            data.setVisibility(View.GONE);
            ora.setVisibility(View.GONE);
            modifica.setVisibility(View.GONE);
            elimina.setVisibility(View.GONE);
            prenotazione.setVisibility(View.GONE);
            icona1.setVisibility(View.GONE);
            icona3.setVisibility(View.GONE);
        } else { //altrimenti visualizza la prenotazione nella home prendendo i dati dai vari file TODO: creare una nuova classe che racchiude tutti i dati o accorpare le esistenti(?)
            resUtente = leggiPrenotazione();
            Data dataPrenotazione = leggiData();
            data.setText(dataPrenotazione.getDataRitiro());
            if (resUtente.getOggetto3().equals("") && !resUtente.getOggetto2().equals("")) { //in base al numero di oggeti della prenotazione esistente cambia il testo mostrato
                rifiuti.setText(resUtente.getOggetto1() + " x " + resUtente.getQuantita1() +
                        "\n" + resUtente.getOggetto2() + " x " + resUtente.getQuantita2());
            } else {
                if (resUtente.getOggetto2().equals("")) {

                    rifiuti.setText(resUtente.getOggetto1() + " x " + resUtente.getQuantita1());

                } else {

                    rifiuti.setText(resUtente.getOggetto1() + " x " + resUtente.getQuantita1() +
                            "\n" + resUtente.getOggetto2() + " x " + resUtente.getQuantita2() +
                            "\n" + resUtente.getOggetto3() + " x " + resUtente.getQuantita3());
                }

            }
        }
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

    public void help(View view) { // onClick su tasto aiuto
        Intent ii = new Intent (this, Regole.class);
        startActivity(ii);
    }
}



