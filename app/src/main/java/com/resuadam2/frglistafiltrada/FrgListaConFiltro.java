package com.resuadam2.frglistafiltrada;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FrgListaConFiltro extends Fragment {
    OnFrgListaConFiltro listener;
    String[] textos;
    ListView lista;

    interface OnFrgListaConFiltro {
        void onHaCambiadoSeleccion(String texto);
        // etc.
    }

    public void setOnFrgListaConFiltro(OnFrgListaConFiltro listener, String[] textos) {
        this.listener = listener;
        this.textos = textos;
        poblarLista("");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.frg_listaconfiltro, container);
        lista = layout.findViewById(R.id.lista);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String texto = adapterView.getItemAtPosition(position).toString();
                listener.onHaCambiadoSeleccion(texto);
            }
        });
        EditText etBuscar = layout.findViewById(R.id.etBuscar);
        etBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                poblarLista(editable.toString());
            }
        });
        return layout;
    }

    public void poblarLista(String texto) {
        // mejorable filtrando sin repoblar ?
        ArrayList<String> textosFiltrados = new ArrayList<>();
        for (String t : textos)
            if (t.contains(texto))
                textosFiltrados.add(t);
        ArrayAdapter adaptador = new ArrayAdapter(
                getActivity(),
                android.R.layout.simple_expandable_list_item_1,
                textosFiltrados);
        lista.setAdapter(adaptador);
    }
}