package it.unica.euforici.ragazzieuforici;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class DatiAnagrafici extends AppCompatActivity {

    Dati datiUtente, datiSalvati;
    EditText insNome, insCognome, insIndirizzo , insTelefono, insMail;
    CheckBox checkBox;
    Button avanti;
    File fileSalvataggio;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datianagrafici);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* ASSEGNAMENTI VARI */

        datiUtente = new Dati();
        datiSalvati = new Dati();

        insNome = (EditText) this.findViewById(R.id.insNome);
        insCognome = (EditText) this.findViewById(R.id.insCognome);
        insIndirizzo = (EditText) this.findViewById(R.id.insIndirizzo);
        insTelefono = (EditText) this.findViewById(R.id.insTelefono);
        insMail = (EditText) this.findViewById(R.id.insMail);
        checkBox = (CheckBox) this.findViewById(R.id.checkBox);
        avanti = (Button) this.findViewById(R.id.avanti);

        fileSalvataggio = new File (getApplicationContext().getFilesDir() + "saveddata.obj");

        if(fileSalvataggio.exists()){
            datiSalvati = leggiDatiSalvati(); // se esiste già un file salvato vuol dire che l'utente aveva già richiesto il salvataggio e recupero quei dati
        popolaDati();} // compilo il form co i dati che già ho
    }

    public void inviaDati(View view) { //onclick sul tasto di conferma

        Intent intent = new Intent(this, ConfermaDati.class);
        if (checkForm()){ //se il form è ok
            creaPersona(); //metto i dati
            salvaDati(datiUtente); // salvo i dati sul file
            startActivity(intent); // vado alla prossima activity
        }
    }

    public void popolaDati(){ // compilo il form con i dati salvati

        insNome.setText(datiSalvati.getNome());
        insCognome.setText(datiSalvati.getCognome());
        insIndirizzo.setText(datiSalvati.getIndirizzo());
        insTelefono.setText(datiSalvati.getTelefono());
        insMail.setText(datiSalvati.getMail());
    }

    private boolean checkForm(){ // controllo errori del form
        int errors = 0;

        if(insNome.getText() == null || insNome.getText().length() == 0){
            insNome.setError("Il nome è obbligatorio");
            errors++;
        }else{
            insNome.setError(null);
        }

        if(insCognome.getText() == null || insCognome.getText().length() == 0){
            insCognome.setError("Il cognome è obbligatorio");
            errors++;
        }else{
            insCognome.setError(null);
        }

        if(insIndirizzo.getText() == null || insIndirizzo.getText().length()  == 0){
            insIndirizzo.setError("L'indirizzo è obbligatorio");
            errors++;
        }else{
            insIndirizzo.setError(null);
        }

        if(insTelefono.getText() == null || insTelefono.getText().length()  == 0){
            insTelefono.setError("Il numero  è obbligatorio");
            errors++;
        }else{
            insTelefono.setError(null);
        }

        if(insMail.getText() == null || insMail.getText().length()  == 0){
            insMail.setError("L'email è obbligatoria");
            errors++;
        }else{
            insMail.setError(null);
        }

        if(checkBox.isChecked()){
            checkBox.setError(null);
        }else{
            checkBox.setError("Devi accettare il trattamento dei tuoi dati ");
            errors++;
        }

        return errors == 0;
    }

 private void creaPersona(){ // mette i dati dove servono

     this.datiUtente.setNome(this.insNome.getText().toString());
     this.datiUtente.setCognome(this.insCognome.getText().toString());
     this.datiUtente.setIndirizzo(this.insIndirizzo.getText().toString());
     this.datiUtente.setTelefono(this.insTelefono.getText().toString());
     this.datiUtente.setMail(this.insMail.getText().toString());
 }

    public void salvaDati(Dati dati){ //TODO integrarlo nella classe

        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(getApplicationContext().getFilesDir() + "user.obj"));
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







