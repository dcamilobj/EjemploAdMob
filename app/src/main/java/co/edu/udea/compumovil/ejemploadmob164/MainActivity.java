package co.edu.udea.compumovil.ejemploadmob164;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity{

    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AdView mAdView = (AdView)findViewById(R.id.adView);             //Referencia al objeto de tipo AdView
        AdRequest mAdRequest= new AdRequest.Builder().build();          //Se trae un anuncio
        mAdView.loadAd(mAdRequest);                                     //Se carga el anuncio en el elemento mAdView

        //Intancias para el anuncio interstitial
        mInterstitialAd= new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        //Incluir AdListener
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
            }
        });

        requestNewInterstitial();

        Button b=(Button)findViewById(R.id.buttonS);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mInterstitialAd.isLoaded())
                    mInterstitialAd.show();
                else
                    Toast.makeText(getApplicationContext(),"No se ha cargado el anuncio",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void requestNewInterstitial() {

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
}

