package marketplace.selfapps.rav.marketplace.authentification;

import android.content.Context;
import android.content.Loader;

import com.google.gson.Gson;

import java.util.Currency;

import static marketplace.selfapps.rav.marketplace.utils.Logs.*;

import marketplace.selfapps.rav.marketplace.authentification.model.JWToken;
import marketplace.selfapps.rav.marketplace.authentification.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */

public class UserLoader extends Loader<JWToken> {
    private static final String BASE_URL ="ENTER_HERE_BASE_URL_ADDRESS";//TODO: !!!!!!!!!!!!!!!!!!!!!!!!ENTER_HERE_BASE_URL_ADDRESS
    private AuthInterface service;
    private User user = null;
    private Gson gson = new Gson();
    private JWToken token = null;


    /**
     * Stores away the application context associated with context.
     * Since Loaders can be used across multiple activities it's dangerous to
     * store the context directly; always use {@link #getContext()} to retrieve
     * the Loader's Context, don't use the constructor argument directly.
     * The Context returned by {@link #getContext} is safe to use across
     * Activity instances.
     *
     * @param context used to retrieve the application context.
     */
    public UserLoader(Context context) {
        super(context);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(AuthInterface.class);


    }


    @Override
    public void deliverResult(JWToken data) {
        log(getContext(),"deliverResult = "+data.toString());
        super.deliverResult(data);
    }

    @Override
    protected void onStartLoading() {
        log(getContext(),"Start Loading.............");
        super.onStartLoading();

    }

    @Override
    protected boolean onCancelLoad() {
        return super.onCancelLoad();
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        log(getContext(),"onForceLoad()");
        service.getSignIn(gson.toJson(user)).enqueue(new Callback<JWToken>() {
            @Override
            public void onResponse(Call<JWToken> call, Response<JWToken> response) {
                token = response.body();//Parsing value to JWToken.class

                log(getContext(),"token = "+ token.toString());
                deliverResult(token);
            }

            @Override
            public void onFailure(Call<JWToken> call, Throwable t) {
                log(getContext(),"Call<JWToken> error: "+ t.getMessage());
            }
        });
    }


    public void login(User user){
        this.user = user;
        this.forceLoad();
    }
}
