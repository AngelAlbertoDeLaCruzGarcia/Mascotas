package com.example.mascotas.Prod;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.net.Uri;
import android.os.Bundle;

import com.example.mascotas.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.math.BigDecimal;
import java.util.ArrayList;

public class DetailsProd extends AppCompatActivity {

    ImageView imgp;
    TextView tvNombre, tvPrecio, tvExistencia;
    Producto producto;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_prod);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="https://www.facebook.com/Clinica-Veterinaria-cotas-1614726852083243/";
                Intent newfb=new Intent(Intent.ACTION_VIEW);
                newfb.setData(Uri.parse(url));
                startActivity(newfb);
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        imgp =(ImageView) findViewById(R.id.ivFoto);
        tvNombre =(TextView) findViewById(R.id.tvNombre);
        tvPrecio =(TextView) findViewById(R.id.tvPrecio);
        tvExistencia =(TextView) findViewById(R.id.tvExistencia);
        b=(Button)findViewById(R.id.btnComprar);

        producto = (Producto)getIntent().getExtras().getSerializable("producto");

        verimagen("http://192.168.1.0:81/Android/img/"+producto.getFoto());
        //verimagen("http://henry.practicaweb3c.com/"+producto.getFoto());

        tvNombre.setText("Nombre: "+producto.getNombre());
        tvPrecio.setText("$"+producto.getPrecio());
        tvExistencia.setText(producto.getDescripcion());
        getSupportActionBar().setTitle(producto.getNombre());
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


    }

    public void verimagen(String link){

        Picasso.get().load(link).into(imgp);
        Picasso.get().areIndicatorsEnabled();
    }


    static class AdapterProducto extends RecyclerView.Adapter<AdapterProducto.ViewHolderProducto> {

        Activity context;
        public ArrayList<Producto> listaProductos;

        public AdapterProducto(Activity context, ArrayList<Producto> listaProductos) {
            this.context = context;
            this.listaProductos = listaProductos;

        }


        @NonNull
        @Override
        public ViewHolderProducto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_lista, null, false);

            return new ViewHolderProducto(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolderProducto holder, final int position) {

            //Picasso.get().load("http://192.168.1.0:81/Android/img/" + listaProductos.get(position).getFoto()).resize(400, 600).into(holder.ivFoto);
            Picasso.get().load("http://henry.practicaweb3c.com/" + listaProductos.get(position).getFoto()).resize(400, 600).into(holder.ivFoto);

            holder.tvNombre.setText(listaProductos.get(position).getNombre());

            holder.ivFoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

/*
                    Toast.makeText(v.getContext(), "SELECCIONO " + listaProductos.get(position).getNombre(), Toast.LENGTH_LONG).show();
*/
                    Intent intent = new Intent();
                    intent.setClass(v.getContext(), DetailsProd.class);
                    intent.putExtra("producto", listaProductos.get(position));
                    context.startActivity(intent);

                }
            });
        }

        @Override
        public int getItemCount() {
            return listaProductos.size();
        }

        public class ViewHolderProducto extends RecyclerView.ViewHolder {
            ImageView ivFoto;
            TextView tvNombre;

            public ViewHolderProducto(View view) {
                super(view);

                ivFoto = itemView.findViewById(R.id.ivFoto);
                tvNombre = itemView.findViewById(R.id.tvNombre);
            }
        }
    }


}
