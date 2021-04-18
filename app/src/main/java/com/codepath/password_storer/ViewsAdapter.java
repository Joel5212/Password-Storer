package com.codepath.password_storer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewsAdapter extends RecyclerView.Adapter<ViewsAdapter.ViewHolder> {

    private static final String TAG = "ViewsAdapter";
    private Context context;
    private List<Credential> views;

    public ViewsAdapter(Context context, List<Credential> views)
    {
        this.context = context;
        this.views = views;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Credential view = views.get(position);
    holder.bind(view);
    }

    @Override
    public int getItemCount() {
        return views.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView tvWebsiteName;
        private TextView tvUsername;
        private TextView tvPassword;
        private TextView tvURL;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWebsiteName = itemView.findViewById(R.id.tvWebsiteName);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvPassword = itemView.findViewById(R.id.tvPassword);
            tvURL = itemView.findViewById(R.id.tvURL);

        }

        public void bind(Credential view) {
            //Bind the post data to the view elements

            if(view.getWebsiteName().isEmpty() || view.getSavedUsername().isEmpty() || view.getSavedPassword().isEmpty() || view.getURL().isEmpty())
            {
                Log.d(TAG, "Unable to extract view from server");
            }
            else
            {
                tvWebsiteName.setText(view.getWebsiteName());
                tvUsername.setText(view.getSavedUsername());
                tvPassword.setText(view.getSavedPassword());
                tvURL.setText(view.getURL());
            }

        }
    }
}
