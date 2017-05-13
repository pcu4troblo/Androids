package com.example.johnathan.myapplication2;

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


    private Bundle pasaListas;
    private ArrayList<String>listaProductos;
    private ArrayList<Integer>listaPrecios;
    private ArrayAdapter<String>spAdapter;
    private ArrayList<String>factura;  //la lista que será enviada a el activity "factura"
    int cuentaFactura;

    private Spinner spProductos;
    private EditText edtCantidad;
    private Button btnAddVenta;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventas);

        /*
        Resibe listas enviadas por la clase Confirmar_Listas
         */
        pasaListas = getIntent().getExtras();
        listaProductos = pasaListas.getStringArrayList("listaProductos"); //Recibe la lista de productos que envia el anterior activity
        listaPrecios = pasaListas.getIntegerArrayList("listaPrecios");

        //Conecta Spinner herramientas
        spProductos = (Spinner) findViewById(R.id.SPProductos);
        edtCantidad = (EditText)findViewById(R.id.TBCantidad);
        btnAddVenta = (Button) findViewById(R.id.btnAddventa);

        //crea el adaptador para listaProductos
        spAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,listaProductos);
        //envía la lista adaptada al Spinner
        spProductos.setAdapter(spAdapter);







    }

    @Override
    public void onClick(View v) {

    }
    public void addVenta(View v){
        String cant = edtCantidad.getText().toString();
        if(cant.equals("")){
            AlertDialog.Builder mensajeError2 = new AlertDialog.Builder(this);
            mensajeError2.setMessage("No se ha ingresado ningún producto");
            mensajeError2.setTitle("Error: Falta información");

            AlertDialog mensajeDeError1 = mensajeError2.create();
            mensajeDeError1.show();
            return;
        }
        factura = agregaProducto(spProductos, cant, listaProductos, listaPrecios);
        edtCantidad.setText("");
    }

    public ArrayList<String> agregaProducto (Spinner sp,String cantidad, ArrayList<String>productos, ArrayList<Integer>precio){
        int cantProducto = Integer.parseInt(cantidad);
        int x = sp.getSelectedItemPosition();
        int precioi = precio.get(x);
        int cuenta = precioi*cantProducto;
        String producto = productos.get(x);
        String precioString = ""+cuenta;

        cuentaFactura = cuentaFactura + cuenta;

        ArrayList<String> facturaAux = new ArrayList<>();
        facturaAux.add(cantidad);
        facturaAux.add(producto);
        facturaAux.add(precioString);
        return facturaAux;
    }
}
