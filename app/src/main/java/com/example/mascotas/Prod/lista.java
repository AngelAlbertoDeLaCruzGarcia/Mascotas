package com.example.mascotas.Prod;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mascotas.R;

public class lista extends Fragment {
    View view;
    ImageView a;
    TextView txt;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lista, container, false);
        a=view.findViewById(R.id.ivFoto);
        txt=view.findViewById(R.id.tvNombre);
        return view;
    }
}
