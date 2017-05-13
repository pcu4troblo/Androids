package com.example.johnathan.myapplication2;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.view.View.OnClickListener;
import java.util.ArrayList;

public class ventas extends AppCompatActivity implements OnClickListener{
    //Se declaran los elementos que se van a usar
    private Bundle pasaListas;
    private ArrayList<String>listaProductos;
    private ArrayList<Integer>listaPrecios;
    private ArrayAdapter<String>spAdapter;
    int ventasTotal;

    private Spinner spProductos;
    private EditText edtCantidad;
    private Button btnAddVenta;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        //Se define la interfaz grafica ventas.xml
        setContentView(R.layout.ventas);


        //Lo usamos para obtener las listas enviadas por la clase Confirmar_Listas

        pasaListas = getIntent().getExtras();

        //Recibe la lista de productos que envia el anterior activity
        listaProductos = pasaListas.getStringArrayList("listaProductos");
        listaPrecios = pasaListas.getIntegerArrayList("listaPrecios");

        //Capturamos el spinner, un boton y un campo para editar texto de la vista
        spProductos = (Spinner) findViewById(R.id.SPProductos);
        edtCantidad = (EditText)findViewById(R.id.TBCantidad);
        btnAddVenta = (Button) findViewById(R.id.btnAddventa);

        //Crea el adaptador para con la lista de productos
        spAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,listaProductos);
        //Envía el adaptador al spinner para que se muestren los productos
        spProductos.setAdapter(spAdapter);


    }

    @Override
    public void onClick(View v) {

    }

    /**
     *
     * @param v
     */
    //Metodo que se inicia cuando damos click a agregar a la venta
    public void addVenta(View v){
        //Obtenemos el texto del objeto edtCantidad
        String cant = edtCantidad.getText().toString();

        //Validamos si el campo esta vacio
        if(cant.equals("")){
            AlertDialog.Builder mensajeError2 = new AlertDialog.Builder(this);
            mensajeError2.setMessage("No se ha ingresado la cantidad de productos");
            mensajeError2.setTitle("Error: Falta información");
            mensajeError2.create().show();
            return;
        }
        //Con este intent mandamos la siguiente actividad confirmar_listas
        Intent i = new Intent(this, Factura.class);
        //Al intent le pasamos las listas
        i.putStringArrayListExtra("listaProductos",listaProductos);
        i.putIntegerArrayListExtra("listaPrecios",listaPrecios);
        //Mandamos la actividad
        startActivity(i);
    }
}
