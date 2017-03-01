package marketplace.selfapps.rav.marketplace.authentification.model;

import android.os.Parcel;
import android.os.Parcelable;



public class User implements Parcelable {

    private String email;
    private String userName;
    private String password;
    private String role;


    public User() {
    }

    public User(String email, String password, String role) {
        this.email = email;

        this.password = password;
        this.role = role;
    }

    public User(String email,String password) {
        this.email = email;
        this.password = password;

    }

    protected User(Parcel in) {
        this.email = in.readString();
        this.userName = in.readString();
        this.password = in.readString();
        this.role = in.readString();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.email);
        dest.writeString(this.userName);
        dest.writeString(this.password);
        dest.writeString(this.role);
    }



    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
