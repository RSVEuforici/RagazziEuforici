package it.unica.euforici.ragazzieuforici;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class FinePrenotazioneData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fineprenotazionedata);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public void backToHome(View view){ // onClick su tasto conferma
        Intent ii = new Intent (this, MainActivity.class);
        startActivity(ii);
    }


}
