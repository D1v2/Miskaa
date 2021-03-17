package com.example.miskaa;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.miskaa.PojoClasses.ImageRegistration;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MiskaaAdapter extends RecyclerView.Adapter<MiskaaAdapter.ViewHolder> {
    List<ImageRegistration>imageRegistrations;
    private Context context;
    public MiskaaAdapter() {
    }

    public void setData(List<ImageRegistration> imageRegistrations) {
        this.imageRegistrations = imageRegistrations;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        return new MiskaaAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_show,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ImageRegistration imageRegistration=imageRegistrations.get(position);
        String Cname=imageRegistration.getName();
        String Ccapital=imageRegistration.getCapital();
        String Csubregion=imageRegistration.getSubregion();
        String Cregion=imageRegistration.getRegion();
        String Cpopulation=imageRegistration.getPopulation().toString();
        holder.name.setText(Cname);
        holder.population.setText(Cpopulation);
        holder.region.setText(Cregion);
        holder.subregion.setText(Csubregion);
        holder.capital.setText(Ccapital);
        int j=imageRegistrations.get(position).getBorders().size();
        StringBuilder sb=new StringBuilder();
        String bor="";
        for (int i=0;i<j;i++){
            bor=imageRegistrations.get(position).getBorders().get(i);
            sb.append(bor);
            sb.append(",");
        }
        String bord=sb.toString();
        holder.border.setText(bord);
        Glide.with(context).load(imageRegistration.getFlag()).into(holder.flags);
        holder.flagurl.setText(imageRegistration.getFlag());
    }

    @Override
    public int getItemCount() {
        return imageRegistrations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView flags;
        TextView name,capital,subregion,region,population,border,flagurl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            flags=itemView.findViewById(R.id.flag);
            name=itemView.findViewById(R.id.name);
            capital=itemView.findViewById(R.id.capital);
            subregion=itemView.findViewById(R.id.subregion);
            region=itemView.findViewById(R.id.region);
            population=itemView.findViewById(R.id.population);
            border=itemView.findViewById(R.id.borders);
            flagurl=itemView.findViewById(R.id.flagurl);
        }
    }
}
