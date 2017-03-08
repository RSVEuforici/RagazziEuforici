package it.unica.euforici.ragazzieuforici;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.view.View;
        import android.widget.CheckBox;
        import android.widget.TextView;
        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.ObjectInputStream;
        import java.io.ObjectOutputStream;

public class ConfermaDati extends AppCompatActivity {

    Dati datiUtente, datiSalvati;
    TextView insNome, insCognome, insIndirizzo, insTelefono, insMail;
    CheckBox checkBox;
    File fileDati, fileSalvataggio;
    TextView etichettaCheckBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conferma_dati);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* ASSEGNAMENTI VARI */
        datiUtente = leggiDati();
        insNome = (TextView) findViewById(R.id.insNome);
        insCognome = (TextView) findViewById(R.id.insCognome);
        insIndirizzo = (TextView) findViewById(R.id.insIndirizzo);
        insTelefono = (TextView) findViewById(R.id.insTelefono);
        insMail = (TextView) findViewById(R.id.insMail);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        etichettaCheckBox = (TextView) findViewById(R.id.textView);

        updateUi();

    }

    private void updateUi() {

        fileDati = new File(getApplicationContext().getFilesDir() + "user.obj");
        fileSalvataggio = new File(getApplicationContext().getFilesDir() + "saveddata.obj");


        insNome.setText(datiUtente.getNome());
        insCognome.setText(datiUtente.getCognome());
        insIndirizzo.setText(datiUtente.getIndirizzo());
        insTelefono.setText(datiUtente.getTelefono());
        insMail.setText(datiUtente.getMail());

        if (fileSalvataggio.exists()) {

            datiSalvati = leggiDatiSalvati(); // se esiste il file, leggi i dati

            if (    datiSalvati.getNome().equals(datiUtente.getNome()) &&
                    datiSalvati.getCognome().equals(datiUtente.getCognome()) &&
                    datiSalvati.getIndirizzo().equals(datiUtente.getIndirizzo()) &&
                    datiSalvati.getTelefono().equals((datiUtente.getTelefono())) &&
                    datiSalvati.getMail().equals(datiUtente.getMail())  ){ // TODO aggiungere funzione isEqual per l'oggetto Dati
                etichettaCheckBox.setText("Elimina dati salvati"); // se i dati sono uguali a quelli salvati, il pulsante elimina i dati
            } else {

                etichettaCheckBox.setText("Aggiorna i dati salvati"); // se i dati sono diversi da quelli già salvati posso aggiornali

            }
        }
    }


    public void confermaDati(View view) { // onClick su conferma
        Intent ii = new Intent (this, CercaRifiuti.class);
        startActivity(ii);
    }

    public void ricordaDati (View view) {

        fileSalvataggio = new File(getApplicationContext().getFilesDir() + "saveddata.obj");

        if (checkBox.isChecked()) {

            if (fileSalvataggio.exists()) {

                datiSalvati = leggiDatiSalvati(); // leggi i dati salvati nel file (se esiste)

                if (datiSalvati.getNome().equals(datiUtente.getNome()) &&
                        datiSalvati.getCognome().equals(datiUtente.getCognome()) &&
                        datiSalvati.getIndirizzo().equals(datiUtente.getIndirizzo()) &&
                        datiSalvati.getTelefono().equals((datiUtente.getTelefono())) &&
                        datiSalvati.getMail().equals(datiUtente.getMail())) { // TODO vedi sopra
                    fileSalvataggio.delete(); // se i dati sono uguali e la checkbox è checkata elimina il file di salvataggio
                } else {

                    salvaDatiSalvati(datiUtente); // salva i dati modificati
                }
            } else {
                    salvaDatiSalvati(datiUtente); // salva i nuovi dati
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

    public void salvaDatiSalvati(Dati dati){ //TODO integrarlo nella classe

        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(getApplicationContext().getFilesDir() + "saveddata.obj"));
            os.writeObject(dati);
            os.close();
        } catch (FileNotFoundException e){
            System.out.println("nessun file");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("non posso scrivere");
            e.printStackTrace();
        }
    }

    public Dati leggiDatiSalvati() { //TODO integrarlo nella classe
        Dati dati = new Dati();
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(getApplicationContext().getFilesDir() + "saveddata.obj"));
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

    public void help(View view) { // onClick su tasto aiuto
        Intent ii = new Intent (this, Regole.class);
        startActivity(ii);
    }

}
