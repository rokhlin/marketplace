package marketplace.selfapps.rav.marketplace.authentification;


import marketplace.selfapps.rav.marketplace.authentification.model.JWToken;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Retrofit REST interface
 */
public interface AuthInterface {
    @POST("/signin")
    Call<JWToken> getSignIn(@Body String user);
}
