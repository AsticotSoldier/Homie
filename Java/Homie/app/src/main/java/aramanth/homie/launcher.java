package aramanth.homie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

public class launcher extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        ImageView logo_app;
        logo_app = (ImageView) findViewById(R.id.logo_appli);
        logo_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(launcher.this, Accueil.class);
                startActivity(intent);
            }
        });

        AlphaAnimation alpha = new AlphaAnimation(0F, 1F);
        alpha.setDuration(5000);
        alpha.setFillAfter(true);
        logo_app.startAnimation(alpha);

    }

}
