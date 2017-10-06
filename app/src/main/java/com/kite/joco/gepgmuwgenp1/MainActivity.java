package com.kite.joco.gepgmuwgenp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    private static final String LOGTAG = "SORSZAMTESZT";
    TextView tvAktSorszam;
    Button btnGet, btnSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAktSorszam = (TextView) findViewById(R.id.tvSorszam);
        btnGet = (Button) findViewById(R.id.btnGet);
        btnSet = (Button) findViewById(R.id.btnSet);


    }

    public void onClick (View v) {
        switch (v.getId()) {
            case (R.id.btnGet) : getSorszam();
                break;
            case (R.id.btnSet) : setSorszam();
                break;
            default:
        }
    }

    void getSorszam() {
        String kiirandosorszam = getAktSorszam();
        if (kiirandosorszam != null) {
            tvAktSorszam.setText(kiirandosorszam);
        } else {
            Toast.makeText(this,"Hiba a kommunikációban",Toast.LENGTH_SHORT).show();
        }
    }

    void setSorszam() {
        String novelendoSorszam = getAktSorszam();
        if (novelendoSorszam != null) {
            AlfaNumSorGenerator asGen = new AlfaNumSorGenerator();
            StringBuffer sb = new StringBuffer(novelendoSorszam);
            StringBuffer kovSorszam = asGen.getNext(sb);
            Log.i(LOGTAG,"Uj sorszam lesz: " + kovSorszam);
            Gmuwsorszam uj = new Gmuwsorszam(1,kovSorszam.toString());
            savenext(uj);
        }

    }

    String getAktSorszam(){
        Call<Gmuwsorszam> aktcall = GepService.getGepRestService().getSorszam();
        Gmuwsorszam aktSorszam = null;
        try {
           aktSorszam = aktcall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return aktSorszam.getSorszam();
    }

    void savenext(Gmuwsorszam g){
        Call<Gmuwsorszam> editcall = GepService.getGepRestService().edit(g);
        try {
            editcall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
