package marketplace.selfapps.rav.marketplace.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import marketplace.selfapps.rav.marketplace.LoginActivity;
import marketplace.selfapps.rav.marketplace.authentification.AuthInterface;
import marketplace.selfapps.rav.marketplace.authentification.model.JWToken;
import marketplace.selfapps.rav.marketplace.authentification.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static marketplace.selfapps.rav.marketplace.utils.Logs.log;

public abstract class UserLoginTask extends AsyncTask<Void, Void, JWToken> {
    private static final String BASE_URL ="http://192.168.1.22:8082/";//TODO: !!!!!!!!!!!!!!!!!!!!!!!!ENTER_HERE_BASE_URL_ADDRESS
    private final AuthInterface service;
    private User user;
    private JWToken token;
    Gson gson = new GsonBuilder()
            .setLenient()
            .create();


    public UserLoginTask(String email, String password, String role) {
        user = new User(email, password, role);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        service = retrofit.create(AuthInterface.class);
    }

    @Override
    protected JWToken doInBackground(Void... params) {
        log(getClass(), "doInBackground------------------------");
        final String[] token = new String[1];
        try {
            Response<String> response = service.getSignIn(user).execute();
            log(getClass(), "response="+response.toString());
            token[0] = response.headers().get("token");
            //   token[0] = response.body();//Parsing value to JWToken.class
            log(getClass(),"token = "+ token[0]);
            log(getClass(),"response.headers() = "+ response.headers());
            log(getClass(),"response.message() = "+ response.message());

        } catch (IOException e) {
            e.printStackTrace();
            log(getClass(),"Call<JWToken> error: "+ e.getMessage());
        }

        return new JWToken(token[0]);
    }

    @Override
    public abstract void onPostExecute(JWToken token);

    @Override
    public abstract void onCancelled();
}
