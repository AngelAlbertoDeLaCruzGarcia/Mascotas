package com.example.mascotas.Prod;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mascotas.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class listprod extends Fragment {
    View view;
    ArrayList<Producto> listaProd;
    ImageView img;
    RecyclerView rvProductos;

    String PRODUCTOS_URL = "http://192.168.1.0:81/Android/ListProd.php";
    //String PRODUCTOS_URL = "http://henry.practicaweb3c.com/Android/ListProd.php";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_listprod, container, false);

        rvProductos = view.findViewById(R.id.rvProductos);
        rvProductos.setLayoutManager(new GridLayoutManager(getContext(), 2));


        listaProd = new ArrayList<Producto>();

        obtenerProductos(view);

        return view;

    }
    public void obtenerProductos(View view) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, PRODUCTOS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String nom = null,pre=null,descripcion=null,foto=null;
                        try {
                            JSONArray datos = new JSONArray(response);
                            for (int indice = 0; indice < datos.length(); indice++) {

                                JSONObject valores = datos.getJSONObject(indice);

                                nom = valores.getString("vchNombre");
                                foto= valores.getString("vchImagen");
                                pre = valores.getString("fltPrecioV");
                                descripcion= valores.getString("txtDescripcion");

                                listaProd.add(new Producto(nom, pre, descripcion,foto));
                                //Toast.makeText(getContext(), "SELECCIONO "+foto, Toast.LENGTH_LONG).show();

                            }

                            DetailsProd.AdapterProducto adaptador = new DetailsProd.AdapterProducto(getActivity(), listaProd);
                            rvProductos.setAdapter(adaptador);

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
}
