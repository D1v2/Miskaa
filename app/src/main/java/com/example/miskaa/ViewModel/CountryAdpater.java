package com.example.miskaa.ViewModel;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import com.bumptech.glide.Glide;
import com.example.miskaa.PojoClasses.ImageRegistration;
import com.example.miskaa.R;

import java.util.List;

public class CountryAdpater extends PagedListAdapter<ImageRegistration,CountryAdpater.CountryViewHolder>{
    private Context context;
    List<ImageRegistration>imageRegistrations;

    public CountryAdpater(Context context, List<ImageRegistration> imageRegistrations){
        super(itemCallback);
        this.context=context;
        this.imageRegistrations = imageRegistrations;
    }
    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CountryAdpater.CountryViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_show,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
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
        String url=imageRegistration.getFlag();
        Log.d("Image Path",imageRegistration.getFlag());
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
    }

    static class CountryViewHolder extends RecyclerView.ViewHolder{
        ImageView flags;
        TextView name,capital,subregion,region,population,border;
        public CountryViewHolder(@NonNull View itemView) {

            super(itemView);
            flags=itemView.findViewById(R.id.flag);
            name=itemView.findViewById(R.id.name);
            capital=itemView.findViewById(R.id.capital);
            subregion=itemView.findViewById(R.id.subregion);
            region=itemView.findViewById(R.id.region);
            population=itemView.findViewById(R.id.population);
            border=itemView.findViewById(R.id.borders);
        }
    }
    static DiffUtil.ItemCallback<ImageRegistration>itemCallback=new DiffUtil.ItemCallback<ImageRegistration>() {
        @Override
        public boolean areItemsTheSame(@NonNull ImageRegistration oldItem, @NonNull ImageRegistration newItem) {
            return oldItem.getDatabase_id==newItem.getDatabase_id();
        }

        @Override
        public boolean areContentsTheSame(@NonNull ImageRegistration oldItem, @NonNull ImageRegistration newItem) {
            return false;
        }
    };
}
