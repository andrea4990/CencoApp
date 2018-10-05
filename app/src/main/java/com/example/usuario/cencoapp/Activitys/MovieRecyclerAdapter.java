package com.example.usuario.cencoapp.Activitys;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.usuario.cencoapp.ModelData.Movie;
import com.example.usuario.cencoapp.R;
import com.example.usuario.cencoapp.Utils.PicassoTrustAll;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Transformation;

import java.util.List;

public class MovieRecyclerAdapter  extends RecyclerView.Adapter<MovieRecyclerHolders> {
    private List<Movie> itemList;
    private Context context;
    private SharedPreferences prefs;
    private String autorization;
    private String ids = "";
    public static final String MESSAGE_PROGRESS = "message_progress";
    private static final int PERMISSION_REQUEST_CODE = 1;
    Activity activity;
    public static onChangeListener changeListener;
    public interface onChangeListener{
        void changeListener(Movie movie);
    }

    public MovieRecyclerAdapter(Context context, List<Movie> itemList) {
        this.itemList = itemList;
        this.context = context;
        prefs =context.getSharedPreferences("com.cav.app", Context.MODE_PRIVATE);

    }

    @Override
    public MovieRecyclerHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, null);
        MovieRecyclerHolders rcv = new MovieRecyclerHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(final MovieRecyclerHolders holder, final int position) {

        holder.desc.setText(itemList.get(position).getOverview());
        //add picasso library
       if( itemList.get(position).getBackdrop_path()!=null) {
           if (!itemList.get(position).getBackdrop_path().equals("")) {

               String url = context.getResources().getString(R.string.url_imagen) + itemList.get(position).getPoster_path();
               PicassoTrustAll.getInstance(context)
                       .load(url)
                       .error(R.drawable.ic_launcher_background)
                       .transform(transformation())
                       .fit()
                       .into(holder.image, new Callback() {
                           @Override
                           public void onSuccess() {
                               holder.progressBar.setVisibility(View.GONE);
                           }

                           @Override
                           public void onError(Exception e) {
                               Log.e("Cargar imagen", "Error: ");
                               holder.progressBar.setVisibility(View.GONE);
                           }
                       });

           } else {
               //holder.image.setImageResource(R.drawable.ic_dummy_img);
           }
       }

        holder.viewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               changeListener.changeListener(itemList.get(position));

            }
        });
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeListener.changeListener(itemList.get(position));

            }
        });


    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public Transformation transformation() {

        Transformation transformation = new Transformation() {

            @Override
            public Bitmap transform(Bitmap source) {
                int targetWidth = 500;//imagen.getWidth();

                double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                int targetHeight = (int) (targetWidth * aspectRatio);
                Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
                if (result != source) {
                    // Same bitmap is returned if sizes are the same
                    source.recycle();
                }
                return result;
            }

            @Override
            public String key() {
                return "transformation" + " desiredWidth";
            }
        };
        return transformation;

    }





}
