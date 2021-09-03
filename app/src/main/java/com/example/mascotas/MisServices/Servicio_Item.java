package com.example.mascotas.MisServices;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mascotas.R;

public class Servicio_Item extends Fragment {
    View view;
    TextView textCort,textB1,textB2,textRaz,textT,textPP;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.servicio_item, container, false);
        textCort=view.findViewById(R.id.textCort);
        textB1=view.findViewById(R.id.textB1);
        textB2=view.findViewById(R.id.textB2);
        textRaz=view.findViewById(R.id.textRaz);
        textT=view.findViewById(R.id.textT);
        textPP=view.findViewById(R.id.textPP);
        return view;
    }
}
