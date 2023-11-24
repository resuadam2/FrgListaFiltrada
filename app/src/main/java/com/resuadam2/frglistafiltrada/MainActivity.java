package com.resuadam2.frglistafiltrada;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] datos=new String[] {"vaca","pollo","caballo","gallina","cerdo","oveja","cabra","perro","gato"};
        FragmentManager gestor=this.getSupportFragmentManager();
        FrgListaConFiltro frg=(FrgListaConFiltro) gestor.findFragmentById(R.id.idListaConFiltro);
        frg.setOnFrgListaConFiltro(
                new FrgListaConFiltro.OnFrgListaConFiltro() {
                    @Override public void onHaCambiadoSeleccion(String texto) { HaCambiadoSeleccion(texto);}
                },datos);
    }

    public void HaCambiadoSeleccion(String texto) {
        Toast.makeText(this,"Ha cambiado la selección a "+texto,Toast.LENGTH_LONG).show();
    }
}