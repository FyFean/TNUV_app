package si.uni_lj.fe.tnuv.tnuv_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class VajaPodrobnosti extends AppCompatActivity implements View.OnClickListener {

    Vaja clickedVaja;
    Context context;
    TextView imeVaje;
    TextView recommendation;
    TextView description;
    ImageView excercisePic;
    Button nazajPodrobnosti;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nazajPodrobnosti:
                super.onBackPressed();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_excercise_details);
        clickedVaja = getIntent().getParcelableExtra("vajaList");
        context = this;


        imeVaje = findViewById(R.id.imeVaje);
        imeVaje.setText(clickedVaja.getImeVaje());

        recommendation = findViewById(R.id.recommendation);
        recommendation.setText("Priporočilo: "+clickedVaja.getRecommendedSets()+ " setov, "+ clickedVaja.getRecommendedReps()+" ponovitev");

        System.out.println("clickedVaja: "+clickedVaja);
        description = findViewById(R.id.navodila);
        description.setText(clickedVaja.getDesc());
        System.out.println("description: "+clickedVaja.getDesc());

        nazajPodrobnosti = findViewById(R.id.nazajPodrobnosti);
        nazajPodrobnosti.setOnClickListener(this);

        excercisePic = findViewById(R.id.slikaVaje);
        excercisePic.setImageResource(clickedVaja.getImgVaje());



//        excercisePic = findViewById(R.id.slikaVaje);
//        excercisePic.set
    }


}
