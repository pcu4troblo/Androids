package com.example.johnathan.myapplication2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Confirmar_Listas extends AppCompatActivity implements OnClickListener{
    //Se declaran los elementos que se van a usar
    private Bundle pasaListas;
    private ArrayList<String> listaProductos;
    private ArrayList<Integer> listaPrecios;
    private ArrayList<String> listaFinal;
    private ArrayAdapter<String>GVdataAdapter;
    private GridView GVProductos;
    private Intent i;
    private Button btnConfirmarListas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Se define la interfaz grafica confirmar_listas.xml
        setContentView(R.layout.confirmar__listas);
        //Lo usamos para obtener las listas
        pasaListas = getIntent().getExtras();
        //Recibe la lista de productos que envia el anterior activity
        listaProductos = pasaListas.getStringArrayList("listaProductos");
        listaPrecios = pasaListas.getIntegerArrayList("listaPrecios");
        //Llamamos al metodo crealista para organizar la lista que vamos a mostrar
        listaFinal = this.creaListaFinal(listaProductos,listaPrecios);
        //Capturamos el gridview de la vista
        GVProductos = (GridView) findViewById(R.id.GVMuestraProductos);
        // Crea el adaptador al cual le llevamos nuestra lista final
        GVdataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listaFinal);
        //Le mandamos el adaptador al grid para que muestre la lista
        GVProductos.setAdapter(GVdataAdapter);
        //Capturamos el boton de confirmar lista de la vista y ejecutamos el metodo onclick
        btnConfirmarListas = (Button) findViewById(R.id.btnConfirmarListas);
        btnConfirmarListas.setOnClickListener(this);
    }

    /**
     *
     * @param productos
     * @param precios
     * @return
     */
    //Metodo que se usa para integrar las listas en una lista final
    public ArrayList<String> creaListaFinal(ArrayList<String> productos, ArrayList<Integer>precios){
        ArrayList<String> listaFinal = new ArrayList<>();
        //Le agregamos los titulos a la lista que vamos a mostrar
        listaFinal.add("Producto");
        listaFinal.add("Precio");
        //Recorremos la lista de productos
        for( int i=0; i<productos.size();i=i+1){
            //Agregamos los productos y los precios a la lista final
            listaFinal.add(productos.get(i));
            listaFinal.add(precios.get(i).toString());
        }
        return listaFinal;

    }


    @Override
    //Cuando se presiona click sobre el boton de confirmar lista
    public void onClick(View v) {
        //Con este intent mandamos la siguiente actividad vantas
        i = new Intent(this, ventas.class);
        //Al intent le pasamos las listas
        i.putStringArrayListExtra("listaProductos",listaProductos);
        i.putIntegerArrayListExtra("listaPrecios",listaPrecios);
        //Mandamos la actividad
        startActivity(i);
    }
}
