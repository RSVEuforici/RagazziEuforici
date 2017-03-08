package it.unica.euforici.ragazzieuforici;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;


public class FinePrenotazione extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fineprenotazione);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* ASSEGNAMENTI VARI */
        button = (Button) findViewById(R.id.button6); //TODO cambiere gli id con qualcosa di esplicativo

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // onClick sul tasto avanti

                Intent ii = new Intent (FinePrenotazione.this, MainActivity.class);
                startActivity(ii);
            }
        });
    }

    public void help(View view) { // onClick su tasto aiuto
        Intent ii = new Intent (this, Regole.class);
        startActivity(ii);
    }


}
