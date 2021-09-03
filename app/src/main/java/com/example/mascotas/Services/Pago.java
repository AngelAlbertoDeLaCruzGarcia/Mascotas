package com.example.mascotas.Services;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mascotas.Prod.DetailsProd;
import com.example.mascotas.Prod.Producto;
import com.example.mascotas.R;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import android.app.ProgressDialog;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Pago extends Fragment {

    TextView tvMontoT;
    EditText edtNombre,edtDomicilio,edtCel,edtCorreo,edtNumTar,edtCodigo;
    Button btnCancel,btnPagar;

    RequestQueue respues;
    StringRequest respuesta;
    String nom,dom,cel,correo,ID_Dueno;
    public String getID_Dueno(){
        return ID_Dueno;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.act_pago, container, false);
        tvMontoT=(TextView)view.findViewById(R.id.tvMontoT);
        tvMontoT.setText(getArguments().getString("amount"));
        edtNombre=(EditText)view.findViewById(R.id.edtNombre);
        edtDomicilio=(EditText)view.findViewById(R.id.edtDomicilio);
        edtCel=(EditText)view.findViewById(R.id.edtCel);
        edtCorreo=(EditText)view.findViewById(R.id.edtCorreo);
        edtNumTar=(EditText)view.findViewById(R.id.edtNumTar);
        edtCodigo=(EditText)view.findViewById(R.id.edtCodigo);
        //btnCancel=(Button) view.findViewById(R.id.btnCancel);
        btnPagar=(Button) view.findViewById(R.id.btnPagar);
        btnPagar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                opciones(view);
            }
        });

        return view;
    }
    public void opciones(View v){
        final CharSequence[] opciones={"Aceptar","Registrar"};
        final AlertDialog.Builder dialogo=new AlertDialog.Builder(getContext());
        dialogo.setTitle("Ingresar codigo de usuario o registrarse");

        dialogo.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) { {

                if(opciones[i].equals("Aceptar"))
                {
                    //addServ();
                    addUser();

                }else
                    dialog.dismiss();
            }
            }
        });
        dialogo.show();
    }


    public void can(View view){
        Navigation.findNavController(view).navigate(R.id.action_pop_out_of_game);
    }


    public void addServ() {
        int x=0;
        final Servicios s = new Servicios();
        for (x = 0; x <= s.ListaServ.size();x++) {
            final int x2=x;
            final ProgressDialog progreso = new ProgressDialog(getContext());
            progreso.setMessage("Cargando...");
            progreso.show();
            String url = "http://192.168.1.0:81/Android/guardarServ.php";
            respuesta = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    progreso.hide();
                    Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                    if (response.trim().equalsIgnoreCase("")) {


                        Toast.makeText(getContext(), "Registrado:)", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "No se ha registrado ", Toast.LENGTH_SHORT).show();
                        Log.i("RESPUESTA: ", "" + response);
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "No se ha podido conectar:(", Toast.LENGTH_SHORT).show();
                    progreso.hide();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {


                    Map<String, String> parametros = new HashMap<>();
                    parametros.put("corte", s.ListaServ.get(x2).getCorte());
                    parametros.put("b1", s.ListaServ.get(x2).getB1());
                    parametros.put("b2", s.ListaServ.get(x2).getB2());
                    parametros.put("raza", s.ListaServ.get(x2).getRaza());
                    parametros.put("tam", s.ListaServ.get(x2).getTam());
                    parametros.put("Total",""+s.ListaServ.get(x2).getCostoParcial());
                    parametros.put("id",ID_Dueno);



                    return parametros;
                }
            };
            respues = Volley.newRequestQueue(getContext());
            respues.add(respuesta);
        }
    }



    String URLa = "http://192.168.1.0:81/Android/guardarDueno.php";
    public void addUser()
    {
        final ProgressDialog progreso = new ProgressDialog(getContext());
        progreso.setMessage("Cargando...");
        progreso.show();

        respuesta=new StringRequest(Request.Method.POST, URLa, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                progreso.hide();
                Toast.makeText(getContext(),response,Toast.LENGTH_SHORT).show();
                if (response.trim().equalsIgnoreCase("")){


                    Toast.makeText(getContext(),"Registrado:)",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(),"No se ha registrado ",Toast.LENGTH_SHORT).show();
                    Log.i("RESPUESTA: ",""+response);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"No se ha podido conectar:(",Toast.LENGTH_SHORT).show();
                progreso.hide();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String,String> parametros=new HashMap<>();
                parametros.put("nom",edtNombre.getText().toString());
                parametros.put("dom",edtDomicilio.getText().toString());
                parametros.put("cel",edtCel.getText().toString());
                parametros.put("correo",edtCorreo.getText().toString());

                return parametros;
            }
        };
        respues= Volley.newRequestQueue(getContext());
        respues.add(respuesta);

        /*
        StringRequest respuesta=new StringRequest(Request.Method.POST, URLa, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.equals("null"))
                {
                    try {
                        JSONArray datos = new JSONArray(response);
                        for (int indice = 0; indice < datos.length(); indice++) {

                            JSONObject valores = datos.getJSONObject(indice);
                            //ID_Dueno = valores.getString("ID_Dueno");
                        }

                    }catch(Exception e)
                    {
                        Toast.makeText(getContext(), "No se pudieron obtener los datos",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getContext(), "ERROR",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Verifique conexion wifi o datos", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String, String>();
                parametros.put("nom",edtNombre.getText().toString());
                parametros.put("dom",edtDomicilio.getText().toString());
                parametros.put("cel",edtCel.getText().toString());
                parametros.put("correo",edtCorreo.getText().toString());
                return parametros;
            }
        };
        RequestQueue respues= Volley.newRequestQueue(getContext());
        respues.add(respuesta);
        */
    }
    public Pago(){}
}
