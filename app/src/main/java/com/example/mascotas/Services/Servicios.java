package com.example.mascotas.Services;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.renderscript.Long4;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mascotas.Menu;
import com.example.mascotas.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.widget.Toast.LENGTH_SHORT;

public class Servicios extends Fragment {
    private ArrayList<TamItem> ListaTam;
    private ArrayList<RazaItem> ListaRaz;
    public ArrayList<clsServicios> ListaServ;
    private RazaAdapter RAdapter;
    private TamAdapter TAdapter;
    Spinner spRaza,spTam;
    Button btnGuardar,btnReservar;

    private String clickname="";Double precioRaza=0.0,precioFinal=0.0,precioTam=0.0,precioCorte=0.0,precioB1=0.0,precioB2=0.0,precioParcial=0.0,precioUnico=0.0;
    String clicktam="";
    String corte,b1,b2; String strId="", r="",tam="",cp="";

    RequestQueue respues;
    StringRequest respuesta;
    private ServiciosAdapter gridAdapter;

    TextView txtP;
    EditText edtduen;
    CheckBox Corte,B1,B2;
    GridView gvMascotas;
    View view;int x;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_servicios, container, false);
        txtP=(TextView)view.findViewById(R.id.txtP);
        Corte=(CheckBox) view.findViewById(R.id.Corte);
        B1=(CheckBox)view.findViewById(R.id.B1);
        B2=(CheckBox)view.findViewById(R.id.B2);
        gvMascotas=view.findViewById(R.id.gvMascotas);

        spRaza=(Spinner)view.findViewById(R.id.spRaza);
        Lista(view);
        spRaza.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                RazaItem clickedItem =(RazaItem) parent.getItemAtPosition(position);
                clickname=clickedItem.getQueraza();
                precioRaza=clickedItem.getCostoraza();
                //Toast.makeText(getContext(),"Seleccionó "+precioRaza, LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        //Llena el spinner de Tamaño
        spTam=(Spinner)view.findViewById(R.id.spTam);
        Listartam(view);
        TAdapter=new TamAdapter(getContext(),ListaTam);
        spTam.setAdapter(TAdapter);
        spTam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TamItem clickedItem =(TamItem) parent.getItemAtPosition(position);
                clicktam=clickedItem.getQuetam();
                //Toast.makeText(getContext(),"Seleccionó "+clicktam,LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ///////Cargar GRID
        ListaServ=new ArrayList<>();
        btnGuardar=(Button) view.findViewById(R.id.btnGuardar);
        botonGuardar(view);

        //Eliminar dato del Grid
        gvMascotas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View vew, int position, long arg3) {
                final clsServicios clickserv =(clsServicios) parent.getItemAtPosition(position);
                precioUnico=clickserv.getCostoParcial();
                final CharSequence[] opciones={"Aceptar","Cancelar"};
                final AlertDialog.Builder dialogo=new AlertDialog.Builder(getContext());
                dialogo.setTitle("Esta seguro que desea ELIMINAR:");
                dialogo.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) { {

                            if(opciones[i].equals("Aceptar"))
                            {
                                precioFinal-=precioUnico;
                                txtP.setText("Costo Total: $"+precioFinal);
                                ListaServ.remove(clickserv);
                                gridAdapter= new ServiciosAdapter(getContext(),ListaServ);
                                gvMascotas.setAdapter(gridAdapter);
                            }
                            else
                            {
                                dialog.dismiss();
                            }
                        }
                    }
                });
                dialogo.show();


                return true;
            }
        });

        btnReservar=(Button)view.findViewById(R.id.btnReservar);
        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final CharSequence[] opciones={"Aceptar","Registrar"};
                final AlertDialog.Builder dialogo=new AlertDialog.Builder(getContext());
                edtduen=new EditText(getContext());
                edtduen.setHint("CODIGO");
                dialogo.setView(edtduen);
                dialogo.setTitle("Ingresar codigo de usuario o registrarse");

                dialogo.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) { {

                        if(opciones[i].equals("Aceptar"))
                        {
                            strId= edtduen.getText().toString();
                            //strId="2";
                            agregarserv();

                        }else {
                            if (opciones[i].equals("Registrar")){
                                Bundle bundle = new Bundle();

                                bundle.putString("amount",txtP.getText().toString());
                                Navigation.findNavController(view).navigate(R.id.action_nav_servicios_to_nav_DetService, bundle);


                            }
                            else
                            {
                                dialog.dismiss();
                            }
                        }
                    }
                    }
                });
                dialogo.show();

            }
        });
        return view;
    }


    ///Cargar Grid
    public void botonGuardar(View view){
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Corte.isChecked() || B1.isChecked() || B2.isChecked() ) {
                    switch (clicktam) {
                        case "Chico":
                            precioTam = 8.0;
                            break;
                        case "Mediano":
                            precioTam = 10.0;
                            break;
                        case "Grande":
                            precioTam = 12.0;
                            break;

                    }

                    if (Corte.isChecked()) {
                        precioCorte=precioRaza*precioTam;
                        corte="Si";
                    }else corte="No";
                    if (B1.isChecked()) {
                        precioB1=precioRaza*precioTam;
                        b1="Si";
                    }else b1="No";
                    if (B2.isChecked()) {
                        precioB2=precioRaza*precioTam;
                        b2="Si";
                    }else b2="No";
                    precioParcial=precioCorte+precioB1+precioB2;
                    ListaServ.add(new clsServicios(corte, b1, b2, clickname, clicktam,precioParcial));

                    gridAdapter = new ServiciosAdapter(getContext(), ListaServ);
                    gvMascotas.setAdapter(gridAdapter);
                    precioFinal+=precioParcial;
                    txtP.setText("Costo Total: $"+precioFinal);
                }else Toast.makeText(getContext(),"Debe seleccionar al menos un servicio",LENGTH_SHORT).show();
            }
        });

    }

    public  void Lista(View view){
        //String PRODUCTOS_URL = "http://henry.practicaweb3c.com/Android/ListRaza.php";
        String PRODUCTOS_URL = "http://192.168.1.0:81/Android/ListRaza.php";
        //String PRODUCTOS_URL = "http://192.168.43.187/Android/ListRaza.php";
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, PRODUCTOS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String nom = null,foto=null;
                        Double pre=0.0;
                        try {
                            JSONArray datos = new JSONArray(response);
                            ListaRaz=new ArrayList<>();
                            for (int indice = 0; indice < datos.length(); indice++) {

                                JSONObject valores = datos.getJSONObject(indice);
                                nom = valores.getString("vchNombre_Raza");
                                foto= valores.getString("vchImagen");
                                pre=valores.getDouble("fltCosto_Raza");
                                ListaRaz.add(new RazaItem(nom,foto,pre));
                                //Toast.makeText(getContext(), "SELECCIONO "+foto, Toast.LENGTH_LONG).show();

                            }

                            RAdapter=new RazaAdapter(getContext(),ListaRaz);
                            spRaza.setAdapter(RAdapter);

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

    public void Listartam(View view){
        ListaTam = new ArrayList<>();
        ListaTam.add(new TamItem("Chico"));
        ListaTam.add(new TamItem("Mediano"));
        ListaTam.add(new TamItem("Grande"));
    }




    public void agregarserv() {
        final ProgressDialog progreso = new ProgressDialog(getContext());
        progreso.setMessage("Cargando...");
        progreso.show();

        int x=0;
        for (x = 0; x <=ListaServ.size();x++) {
            final int x2=x;

            String URLSer="http://192.168.1.0:81/Android/guardarService.php";
            respuesta=new StringRequest(Request.Method.POST, URLSer, new Response.Listener<String>() {

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
                    //Toast.makeText(getContext(),"",Toast.LENGTH_SHORT).show();
                    progreso.hide();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {


                    Map<String,String> parametros=new HashMap<>();
                    parametros.put("corte",ListaServ.get(x2).getCorte());
                    parametros.put("b1", ListaServ.get(x2).getB1());
                    parametros.put("b2",ListaServ.get(x2).getB2() );
                    parametros.put("raza",ListaServ.get(x2).getRaza() );
                    parametros.put("tam",ListaServ.get(x2).getTam() );
                    parametros.put("Total",ListaServ.get(x2).getCostoParcial().toString());
                    parametros.put("id",strId);

                    return parametros;
                }
            };
            respues= Volley.newRequestQueue(getContext());
            respues.add(respuesta);
        }

    }
    public Servicios(){}
}
