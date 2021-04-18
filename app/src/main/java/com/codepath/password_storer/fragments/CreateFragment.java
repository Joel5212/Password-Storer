package com.codepath.password_storer.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codepath.password_storer.Credential;
import com.codepath.password_storer.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;


public class CreateFragment extends Fragment {

    public static final String TAG = "CreateFragment";
    private EditText etWebsiteName;
    private EditText etSavedPassword;
    private EditText etSavedUsername;
    private EditText etURL;
    private Button btnSave;


    public CreateFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create, container, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        etWebsiteName = view.findViewById(R.id.etWebsiteName);
        etSavedPassword = view.findViewById(R.id.etSavedPassword);
        etSavedUsername = view.findViewById(R.id.etSavedUsername);
        etURL = view.findViewById(R.id.etURL);
        btnSave = view.findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String websiteName = etWebsiteName.getText().toString();
                String password = etSavedPassword.getText().toString();
                String username = etSavedUsername.getText().toString();
                String url = etURL.getText().toString();
                if(password.isEmpty() || username.isEmpty() || url.isEmpty() || websiteName.isEmpty())
                {
                    Toast.makeText(getContext(), "Fields cannot be empty!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ParseUser currentUser= ParseUser.getCurrentUser();
                    savePost(websiteName, password, username, url, currentUser);
                }


            }
        });


    }
    private void savePost(String websiteName, String password, String username,  String url, ParseUser currentUser) {
        Credential credential = new Credential();
        credential.setWebsiteName(websiteName);
        credential.setSavedPassword(password);
        credential.setSavedUsername(username);
        credential.setURL(url);
        credential.setUser(currentUser);
        credential.saveInBackground(new SaveCallback()
        {
            @Override
            public void done(ParseException e) {
                if(e != null)
                {
                    Log.e(TAG, "Error while saving", e);
                    Toast.makeText(getContext(), "Error while saving!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Log.i(TAG, "Post save was successful");
                    etWebsiteName.setText("");
                    etSavedPassword.setText("");
                    etSavedUsername.setText("");
                    etURL.setText("");
                }
            }
        });
    }

    private void queryPosts()
    {
        ParseQuery<Credential> query = ParseQuery.getQuery(Credential.class);
        query.findInBackground(new FindCallback<Credential>() {
            @Override
            public void done(List<Credential> credentials, ParseException e)
            {
                if ( e != null)
                {
                    Log.e(TAG, "Issue with getting credentials", e);
                }
                for(Credential credential : credentials)
                {
                    Log.i(TAG, "Credential: " + credential.getSavedPassword());
                }
            }
        });
    }
}