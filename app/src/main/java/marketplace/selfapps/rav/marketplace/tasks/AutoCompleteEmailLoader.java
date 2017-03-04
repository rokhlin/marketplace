package marketplace.selfapps.rav.marketplace.tasks;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

import marketplace.selfapps.rav.marketplace.LoginActivity;
import marketplace.selfapps.rav.marketplace.interfaces.ProfileQuery;


public abstract class AutoCompleteEmailLoader implements LoaderManager.LoaderCallbacks<Cursor> {

    Context context;


    public AutoCompleteEmailLoader(Context context) {
        this.context = context;
    }

    /**
     * Async Task load data to  AutoCompleteTextView from Contacts
     * @param i
     * @param bundle saved instance
     * @return Cursor object that contain same information of the entered in AutoCompleteTextView
     */
    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(context,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    /**
     * Get list of the suitable information from Loader
     * @param cursorLoader  data from method onCreateLoader
     * @param cursor if suitable information contained in the contacts would return cursor to first value
     */
    @Override
    public abstract void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor);

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
    }
}
