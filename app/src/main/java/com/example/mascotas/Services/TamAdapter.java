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

public class TamAdapter extends ArrayAdapter<TamItem> {
    TamItem Tam;
    public TamAdapter(Context context, ArrayList<TamItem> ListaRaza){
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
                    R.layout.spinner_item_tam,parent,false);
        }
        TextView txtTam=convertView.findViewById(R.id.txtTam);
        TamItem currentTam= getItem(position);

        if (currentTam!=null) {

            txtTam.setText(currentTam.getQuetam());

        }
        return convertView;

    }

}
