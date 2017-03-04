package marketplace.selfapps.rav.marketplace.authentification;


import marketplace.selfapps.rav.marketplace.authentification.model.JWToken;
import marketplace.selfapps.rav.marketplace.authentification.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Retrofit REST interface
 */
public interface AuthInterface {
    @Headers( "Content-Type: application/json" )
    @POST("signin/")
    Call<String> getSignIn(@Body User user);

    @Headers( "Content-Type: application/json" )
    @POST("register/")
    Call<String> getRegistration(@Body User user);
}
