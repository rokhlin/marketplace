package marketplace.selfapps.rav.marketplace.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;

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
    private static final String BASE_URL ="ENTER_HERE_BASE_URL_ADDRESS";//TODO: !!!!!!!!!!!!!!!!!!!!!!!!ENTER_HERE_BASE_URL_ADDRESS
    private final AuthInterface service;
    private User user;
    private JWToken token;
    private Gson gson = new Gson();


    public UserLoginTask(String email, String password, String role) {
        user = new User(email, password, role);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(AuthInterface.class);
    }

    @Override
    protected JWToken doInBackground(Void... params) {
        service.getSignIn(gson.toJson(user)).enqueue(new Callback<JWToken>() {
            @Override
            public void onResponse(Call<JWToken> call, Response<JWToken> response) {
                token = response.body();//Parsing value to JWToken.class

                log(getClass(),"token = "+ token.toString());

            }

            @Override
            public void onFailure(Call<JWToken> call, Throwable t) {
                log(getClass(),"Call<JWToken> error: "+ t.getMessage());
            }
        });
        return token;
    }

    @Override
    public abstract void onPostExecute(JWToken token);

    @Override
    public abstract void onCancelled();
}
