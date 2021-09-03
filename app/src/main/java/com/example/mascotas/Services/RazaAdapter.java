package com.example.mascotas.Services;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mascotas.R;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RazaAdapter extends ArrayAdapter<RazaItem> {
    ImageView imgRaza;

    public RazaAdapter(Context context, ArrayList<RazaItem> ListaRaza){
        super(context,0,ListaRaza);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return iniciarVista(position,convertView,parent);

    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return iniciarVista(position,convertView,parent);
    }
    private View iniciarVista(int position,View convertView,ViewGroup parent){
        if (convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(
                    R.layout.spinner_item_mascotas,parent,false);
        }
        imgRaza=convertView.findViewById(R.id.ivRaza);
        TextView txtRaza=convertView.findViewById(R.id.txtRaza);
        RazaItem currentRaza= getItem(position);
        if (currentRaza!=null) {
            //verimagen("http://henry.practicaweb3c.com/"+currentRaza.getQueimg());
            //verimagen("http://192.168.43.187/"+currentRaza.getQueimg());
            verimagen("http://192.168.1.0:81/"+currentRaza.getQueimg());
            txtRaza.setText(currentRaza.getQueraza());
        }
        return convertView;

    }
    public void verimagen(String link){

        Picasso.get().load(link).into(imgRaza);
        Picasso.get().areIndicatorsEnabled();
    }
}
