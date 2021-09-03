package com.example.mascotas.MisServices;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mascotas.Prod.DetailsProd;
import com.example.mascotas.Prod.Producto;
import com.example.mascotas.R;
import com.example.mascotas.Services.clsServicios;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class miserv extends Fragment {
    View view;
    ArrayList<clsServicios> ListaServices;
    ImageView img;
    RecyclerView rvListServices;

    String PRODUCTOS_URL = "http://192.168.1.0:81/Android/ListMisServ.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.miserv, container, false);

        rvListServices = view.findViewById(R.id.rvListServices);
        rvListServices.setLayoutManager(new GridLayoutManager(getContext(), 1));


        ListaServices = new ArrayList<clsServicios>();

        obtenerServ(view);

        return view;

    }
    public void obtenerServ(View view) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, PRODUCTOS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String BanoG = null,BanoM=null,Corte=null,DuenoMas=null,Hora=null,Fecha=null;
                        Double Costo=0.0;
                        try {
                            JSONArray datos = new JSONArray(response);
                            for (int indice = 0; indice < datos.length(); indice++) {

                                JSONObject valores = datos.getJSONObject(indice);

                                BanoM = valores.getString("BanoM");
                                BanoG= valores.getString("BanoG");
                                Corte = valores.getString("Corte");
                                Fecha= valores.getString("dt_Fecha_de_atencion");
                                Costo=valores.getDouble("fltTotal");
                                DuenoMas= valores.getString("intID_Dueno");
                                Hora= valores.getString("timHora_de_atencion");
                                ListaServices.add(new clsServicios(Corte,BanoM,BanoG,DuenoMas,Fecha+" "+Hora,Costo));
                                //Toast.makeText(getContext(), "SELECCIONO "+foto, Toast.LENGTH_LONG).show();

                            }

                            AdapterServ adaptador = new AdapterServ(getContext(), ListaServices);
                            rvListServices.setAdapter(adaptador);

                        }catch(JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(stringRequest);
    }

    static class AdapterServ extends RecyclerView.Adapter<AdapterServ.ViewHolderServices> {

        Context context;
        public ArrayList<clsServicios> ls;

        public AdapterServ(Context context, ArrayList<clsServicios> ls) {
            this.context = context;
            this.ls = ls;

        }

        @NonNull
        @Override
        public ViewHolderServices onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.servicio_item, null, false);

            return new ViewHolderServices(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolderServices holder, final int position) {

            holder.textCort.setText(ls.get(position).getCorte());
            holder.textB1.setText(ls.get(position).getB1());
            holder.textB2.setText(ls.get(position).getB2());
            holder.textRaz.setText(ls.get(position).getRaza());
            holder.textT.setText(ls.get(position).getTam());
            holder.textPP.setText(""+ls.get(position).getCostoParcial());
        }

        @Override
        public int getItemCount() { return ls.size(); }

        public class ViewHolderServices extends RecyclerView.ViewHolder {
            TextView textCort,textB1,textB2,textRaz,textT,textPP;
            public ViewHolderServices(View view) {
                super(view);

                textCort=itemView.findViewById(R.id.textCort);
                textB1=itemView.findViewById(R.id.textB1);
                textB2=itemView.findViewById(R.id.textB2);
                textRaz=itemView.findViewById(R.id.textRaz);
                textT=itemView.findViewById(R.id.textT);
                textPP=itemView.findViewById(R.id.textPP);

            }
        }
    }
    public miserv() {
        // Required empty public constructor
    }
}
