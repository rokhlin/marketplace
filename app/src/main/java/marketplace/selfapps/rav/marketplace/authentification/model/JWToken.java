package marketplace.selfapps.rav.marketplace.authentification.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JWToken implements Parcelable {
    @SerializedName("token")
    @Expose
    String token;


    @Override
    public String toString() {
        return token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.token);
    }

    public JWToken() {
    }

    public JWToken(String token) {
        this.token = token;
    }

    protected JWToken(Parcel in) {
        this.token = in.readString();
    }

    public static final Parcelable.Creator<JWToken> CREATOR = new Parcelable.Creator<JWToken>() {
        @Override
        public JWToken createFromParcel(Parcel source) {
            return new JWToken(source);
        }

        @Override
        public JWToken[] newArray(int size) {
            return new JWToken[size];
        }
    };
}
