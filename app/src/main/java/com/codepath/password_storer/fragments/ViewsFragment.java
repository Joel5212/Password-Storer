package com.codepath.password_storer.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.password_storer.Credential;
import com.codepath.password_storer.R;
import com.codepath.password_storer.ViewsAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class ViewsFragment extends Fragment {

    public static final String TAG = "PostsFragment";
    private RecyclerView rvViews;
    private ViewsAdapter adapter;
    private List<Credential> allViews;
    int count = 0;

    public ViewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_views, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvViews = view.findViewById(R.id.rvViews);


        allViews = new ArrayList<>();
        adapter = new ViewsAdapter(getContext(), allViews);

        rvViews.setAdapter(adapter);
        rvViews.setLayoutManager(new LinearLayoutManager(getContext()));





       queryPosts();
    }


    private void queryPosts()
    {
        ParseQuery<Credential> query = ParseQuery.getQuery(Credential.class);
        query.include(Credential.KEY_USER);
        query.whereEqualTo(Credential.KEY_USER, ParseUser.getCurrentUser());
        query.setLimit(20);
        query.addDescendingOrder(Credential.KEY_CREATED_KEY);
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
                allViews.addAll(credentials);
                adapter.notifyDataSetChanged();
            }
        });
    }
}