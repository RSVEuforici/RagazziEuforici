package it.unica.euforici.ragazzieuforici;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;



public class CercaRifiuti extends AppCompatActivity {


    private ArrayList<String> arrayList;
    private EditText txtInput;
    private TextView txtItem, txtItem1, txtItem2;
    private ImageView delImage, delImage1, delImage2;
    private Button addItem;
    private Spinner spinner, spinner1, spinner2;
    private String[] oggetti;

    Prenotazione prenotazione;

    private static final int MAXOBJ = 3;

    int totalObj=0;
    int totalClick=0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerca);

        /* ASSEGNAMENTI VARI */
        addItem = (Button) findViewById(R.id.additem);

        txtItem = (TextView) findViewById(R.id.txtItem);
        txtItem1 = (TextView) findViewById(R.id.txtItem1);
        txtItem2 = (TextView) findViewById(R.id.txtItem2);

        delImage = (ImageView) findViewById(R.id.delImage);
        delImage1 = (ImageView) findViewById(R.id.delImage1);
        delImage2 = (ImageView) findViewById(R.id.delImage2);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);

        oggetti =  getResources().getStringArray(R.array.oggetti);
        prenotazione = new Prenotazione();


      //  visibilità iniziale oggetti (NASCOSTA)
        spinner.setVisibility(View.GONE);
        txtItem.setVisibility(View.GONE);
        delImage.setVisibility(View.GONE);

        spinner1.setVisibility(View.GONE);
        txtItem1.setVisibility(View.GONE);
        delImage1.setVisibility(View.GONE);

        spinner2.setVisibility(View.GONE);
        txtItem2.setVisibility(View.GONE);
        delImage2.setVisibility(View.GONE);

        // Autocompletamento e lista
        String [] items={};
        arrayList=new ArrayList<> (Arrays.asList(items));
        txtInput=(EditText)findViewById(R.id.autocomplete);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* perdonatemi questa serie di IF */

                if (totalObj==MAXOBJ){ // 3 oggetti già inseriti
                    Toast.makeText(
                            getApplicationContext(),
                            "Puoi Inserire massimo 3 \n rifiuti per prenotazione",
                            Toast.LENGTH_LONG
                    ).show();

                }else  { // meno di 3 oggetti
                    String newItem=txtInput.getText().toString();
                    if (Arrays.asList(oggetti).contains(newItem)){ // oggetto presente nella lista

                        totalClick++;
                        totalObj++;
                        arrayList.add(newItem);
                        addItem(totalClick, newItem);
                        txtInput.setText("");
                        txtInput.setHint("Inserisci altro rifiuto");
                    }else{
                        Toast.makeText( // oggetto non presente nella lista
                                getApplicationContext(),
                                "Oggetto non trovato",
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
            }
        });

        /**
         * Brutto modo di cancellare gli oggetti,
         * utilizzando lo stesso truccheto dell'aggiunta di uno nuovo oggetto,
         * facile per 3 ma si può fare di meglio (un altro giorno)
         * perdonatemi pure questo.
         */

        delImage.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                AlertDialog.Builder builder1 = new AlertDialog.Builder(CercaRifiuti.this );
                builder1.setMessage("Sei sicuro di voler eliminare la voce "+txtItem.getText().toString() +" ?");
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Si",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                if (txtItem1.getText().toString().equals("")){

                                    spinner.setVisibility(View.GONE);
                                    txtItem.setVisibility(View.GONE);
                                    delImage.setVisibility(View.GONE);
                                    txtItem.setText("");
                                    totalClick--;
                                    totalObj--;
                                }else if (!txtItem1.getText().toString().equals("") && txtItem2.getText().toString().equals("")) {

                                    txtItem.setText(txtItem1.getText().toString());
                                    txtItem1.setText("");
                                    spinner1.setVisibility(View.GONE);
                                    txtItem1.setVisibility(View.GONE);
                                    delImage1.setVisibility(View.GONE);
                                    totalClick--;
                                    totalObj--;
                                }else if(!txtItem1.getText().toString().equals("") && !txtItem2.getText().toString().equals("")){

                                    txtItem.setText(txtItem1.getText().toString());
                                    txtItem1.setText(txtItem2.getText().toString());
                                    txtItem2.setText("");
                                    spinner2.setVisibility(View.GONE);
                                    txtItem2.setVisibility(View.GONE);
                                    delImage2.setVisibility(View.GONE);
                                    totalClick--;
                                    totalObj--;
                                }

                                dialog.cancel();
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


        delImage1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                AlertDialog.Builder builder1 = new AlertDialog.Builder(CercaRifiuti.this );
                builder1.setMessage("Sei sicuro di voler eliminare la voce "+txtItem1.getText().toString() +" ?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Si",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                if (txtItem2.getText().toString().equals("")){

                                    spinner1.setVisibility(View.GONE);
                                    txtItem1.setVisibility(View.GONE);
                                    delImage1.setVisibility(View.GONE);
                                    txtItem1.setText("");
                                    totalClick--;
                                    totalObj--;
                                }else if (!txtItem2.getText().toString().equals("")) {

                                    txtItem1.setText(txtItem2.getText().toString());
                                    txtItem2.setText("");
                                    spinner2.setVisibility(View.GONE);
                                    txtItem2.setVisibility(View.GONE);
                                    delImage2.setVisibility(View.GONE);
                                    totalClick--;
                                    totalObj--;
                                }

                                dialog.cancel();
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

        delImage2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                AlertDialog.Builder builder1 = new AlertDialog.Builder(CercaRifiuti.this );
                builder1.setMessage("Sei sicuro di voler eliminare la voce "+txtItem2.getText().toString() +" ?");
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Si",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                    spinner2.setVisibility(View.GONE);
                                    txtItem2.setVisibility(View.GONE);
                                    delImage2.setVisibility(View.GONE);
                                    txtItem2.setText("");
                                    totalClick--;
                                    totalObj--;
                                dialog.cancel();
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
        /** FINE CANCELLA OGGETTI **/


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AutoCompleteTextView autocomplete = (AutoCompleteTextView) findViewById(R.id.autocomplete);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, oggetti);
        autocomplete.setAdapter(adapter);

        autocomplete.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int pos,long id) { //mostro il toast con l'oggetto selezionato
                String selected = (String)adapter.getItemAtPosition(pos);
                Toast.makeText(
                        getApplicationContext(),
                        "Hai Selezionato "+selected,
                        Toast.LENGTH_LONG
                ).show();
            }
        });


    }

    private void addItem (int n, String s){ //visualizza gli oggetti in base a quante volte ho cliccato, barbatrucco, ma funziona.

        if (n==0){
            spinner.setVisibility(View.GONE);
            txtItem.setVisibility(View.GONE);
            delImage.setVisibility(View.GONE);

            spinner1.setVisibility(View.GONE);
            txtItem1.setVisibility(View.GONE);
            delImage1.setVisibility(View.GONE);

            spinner2.setVisibility(View.GONE);
            txtItem2.setVisibility(View.GONE);
            delImage2.setVisibility(View.GONE);
        }
        if (n==1){
            
            txtItem.setText(s);
            txtItem.setVisibility(View.VISIBLE);
            spinner.setVisibility(View.VISIBLE);
            delImage.setVisibility(View.VISIBLE);

            spinner1.setVisibility(View.GONE);
            txtItem1.setVisibility(View.GONE);
            delImage1.setVisibility(View.GONE);

            spinner2.setVisibility(View.GONE);
            txtItem2.setVisibility(View.GONE);
            delImage2.setVisibility(View.GONE);
        }
        if (n==2){
            txtItem.setVisibility(View.VISIBLE);
            spinner.setVisibility(View.VISIBLE);
            delImage.setVisibility(View.VISIBLE);

            txtItem1.setText(s);
            txtItem1.setVisibility(View.VISIBLE);
            spinner1.setVisibility(View.VISIBLE);
            delImage1.setVisibility(View.VISIBLE);

            spinner2.setVisibility(View.GONE);
            txtItem2.setVisibility(View.GONE);
            delImage2.setVisibility(View.GONE);
        }
        if (n==3){

            spinner.setVisibility(View.VISIBLE);
            txtItem.setVisibility(View.VISIBLE);
            delImage.setVisibility(View.VISIBLE);

            spinner1.setVisibility(View.VISIBLE);
            txtItem1.setVisibility(View.VISIBLE);
            delImage1.setVisibility(View.VISIBLE);

            txtItem2.setText(s);
            txtItem2.setVisibility(View.VISIBLE);
            spinner2.setVisibility(View.VISIBLE);
            delImage2.setVisibility(View.VISIBLE);
        }
    }

    public void fineOggetti(View view) {
        if (totalObj<1){ // non ci sono abbastanza oggetti
            Toast.makeText(
                    getApplicationContext(),
                    "Inserisci almeno un oggetto.",
                    Toast.LENGTH_LONG
            ).show();

        }else{ //prossima activity
            Intent ii = new Intent (this, SelezionaData.class);
            creaPrenotazione();
            salvaPrenotazione(prenotazione);
            startActivity(ii);
        }
    }

    private void creaPrenotazione() { //mette i dati dove servono

        this.prenotazione.setOggetto1(this.txtItem.getText().toString());
        this.prenotazione.setQuantita1("1");
        this.prenotazione.setOggetto2(this.txtItem1.getText().toString());
        this.prenotazione.setQuantita2("1");
        this.prenotazione.setOggetto3(this.txtItem2.getText().toString());
        this.prenotazione.setQuantita3("1");
    }

    public void salvaPrenotazione(Prenotazione prenotazione){ //TODO integrarlo nella classe

        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(getApplicationContext().getFilesDir() + "reserve.obj"));
            os.writeObject(prenotazione);
            os.close();
        } catch (FileNotFoundException e){
            System.out.println("nessun file");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("non posso scrivere");
            e.printStackTrace();
        }
    }
    public void help(View view) { // onClick su tasto aiuto
        Intent ii = new Intent (this, Regole.class);
        startActivity(ii);
    }
}
