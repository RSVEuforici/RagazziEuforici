package it.unica.euforici.ragazzieuforici;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ModificaData extends AppCompatActivity {

    TextView dataText1, dataText2, dataText3;
    RadioButton radioButton, radioButton1, radioButton2;
    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* ASSEGNAMENTI VARI */

        dataText1 = (TextView) findViewById(R.id.textView2);
        dataText2 = (TextView) findViewById(R.id.textView7);
        dataText3 = (TextView) findViewById(R.id.textView3);

        radioButton = (RadioButton) findViewById(R.id.radioButton);
        radioButton1 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton2= (RadioButton) findViewById(R.id.radioButton1);

        //TODO generare date in automatico con l'utilizzo della classe Calendar
        dataText1.setText("Venerdì 10 Marzo");
        dataText2.setText("Lunedì 13 Marzo");
        dataText3.setText("Mercoledì 15 Marzo");

        data = new Data();


    }

    public void onRadioButtonClicked(View view) { //gestione dei radio buttons (non raggruppati)
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radioButton:
                if (checked)

                    radioButton1.setChecked(!checked);
                radioButton2.setChecked(!checked);
                data.setDataRitiro(dataText1.getText().toString());
                salvaData(data);


                break;
            case R.id.radioButton2:
                if (checked)
                    radioButton.setChecked(!checked);
                radioButton2.setChecked(!checked);
                data.setDataRitiro(dataText2.getText().toString());
                salvaData(data);


                break;
            case R.id.radioButton1:
                if (checked)
                    radioButton.setChecked(!checked);
                radioButton1.setChecked(!checked);
                data.setDataRitiro(dataText3.getText().toString());
                salvaData(data);

                break;
        }
    }

    public void confermaData(View view) {  //onClick sul tasto di conferma
        if (radioButton.isChecked() || radioButton1.isChecked() || radioButton2.isChecked()){
            Intent ii = new Intent (this, FinePrenotazioneData.class);
            startActivity(ii);
        }else {

            Toast.makeText(
                    getApplicationContext(),
                    "Seleziona almeno una data", //errore in caso non si sia selezionata una data
                    Toast.LENGTH_LONG
            ).show();

        }
    }


    public void salvaData(Data data){ //TODO integrarlo nella classe
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(getApplicationContext().getFilesDir() + "data.obj"));
            os.writeObject(data);
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
