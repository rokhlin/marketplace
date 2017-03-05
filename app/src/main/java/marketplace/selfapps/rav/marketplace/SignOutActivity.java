package marketplace.selfapps.rav.marketplace;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import marketplace.selfapps.rav.marketplace.authentification.model.JWToken;

import static marketplace.selfapps.rav.marketplace.utils.Logs.log;

public class SignOutActivity extends AppCompatActivity {
    private static final String TOKEN = "TOKEN" ;
    private SharedPreferences sPref;
    private AlertDialog.Builder ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_out);

        ad = new AlertDialog.Builder(this);
        ad.setTitle("Sign Out");  // заголовок
        ad.setMessage(""); // сообщение
        ad.setPositiveButton("Sign Out", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
               log(getClass(),"Sign Out button pressed");
            }
        });
        ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                log(getClass(),"Cancel button pressed");
            }
        });
        ad.setCancelable(true);


        Button signOut = (Button) findViewById(R.id.btn_sign_out);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad.show();
            }
        });
    }

    /**
     * Save actual TOKEN in SharedPreferences
     * @param token of the authentication will saved in  SharedPreferences
     */
    public void saveToken(JWToken token) {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(TOKEN, token.toString());
        ed.apply();
        log(SignOutActivity.this," token saved!");
    }

    /**
     * Load actual TOKEN from the SharedPreferences if contain
     * @return token of the authentication will returned from the SharedPreferences
     */
    public JWToken loadToken() {
        sPref = getPreferences(MODE_PRIVATE);
        String token = sPref.getString(TOKEN, null);
        if(token!= null)
            log(SignOutActivity.this," token loaded from preferences!" );
        return token!= null ? new JWToken(token): null;
    }


}
