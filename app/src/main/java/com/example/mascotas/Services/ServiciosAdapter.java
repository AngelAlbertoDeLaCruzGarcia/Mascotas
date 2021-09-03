package com.example.mascotas.Services;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.mascotas.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static java.security.AccessController.getContext;


public class ServiciosAdapter extends ArrayAdapter<clsServicios> {

    TextView txtCort,txtB1,txtB2,txtRaz,txtT,txtPP;
        public ServiciosAdapter(Context context, ArrayList<clsServicios>ListaServ){
            super(context,0,ListaServ);
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
                R.layout.listserv,parent,false);
            }
            txtCort=convertView.findViewById(R.id.txtCort);
            txtB1=convertView.findViewById(R.id.txtB1);
            txtB2=convertView.findViewById(R.id.txtB2);
            txtRaz=convertView.findViewById(R.id.txtRaz);
            txtT=convertView.findViewById(R.id.txtT);
            txtPP=convertView.findViewById(R.id.txtPP);
            clsServicios currentServ= getItem(position);
            if (currentServ!=null) {

                txtCort.setText(currentServ.getCorte());
                txtB1.setText(currentServ.getB1());
                txtB2.setText(currentServ.getB2());
                txtRaz.setText(currentServ.getRaza());
                txtT.setText(currentServ.getTam());
                txtPP.setText(""+currentServ.getCostoParcial());
            }
            return convertView;
        }
}
