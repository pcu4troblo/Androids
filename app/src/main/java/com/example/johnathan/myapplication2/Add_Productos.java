package com.example.johnathan.myapplication2;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import java.util.ArrayList;

public class Add_Productos extends AppCompatActivity implements OnClickListener{

    private ArrayList<String> listaProductos = new ArrayList<String>();
    private ArrayList<Integer> listaPrecios = new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Se define la interfaz grafica add_productos.xml
        setContentView(R.layout.add_productos);
        //Se crea el boton agregar producto
        Button btnAgregarProducto = (Button) findViewById(R.id.BtnAgregarProducto);
        //btnAgregarProducto.setOnClickListener(this);
        //Se crea el boton confirmar lista
        Button btnConfirmarLista = (Button) findViewById(R.id.BtnEnviaListas);

    }

    @Override
    public void onClick(View v) {
        //Capturamos el campo donde ingresamos el producto
        EditText TBnomProducto = (EditText) findViewById(R.id.TBaddProducto);
        //Toma el nombre del producto a agregar
        String nomProducto = TBnomProducto.getText().toString();
        //Capturamos el campo donde ingresamos el precio
        EditText TBprecioProducto = (EditText) findViewById(R.id.TBaddPrecio);
        //Toma el nombre del precio a agregar
        String precio = TBprecioProducto.getText().toString();

        //Validamos si los campos estan vacios
        if(precio.equals("") || nomProducto.equals("")){
            //Generamos una alerta para que el usuario se de cuenta de que debe llenar los datos
            AlertDialog.Builder mensajeError = new AlertDialog.Builder(this);
            mensajeError.setMessage("Alguno de los espacios no ha sido llenado");
            mensajeError.setTitle("Error: Falta información");
            //Mostramos el mensaje de alerta
            mensajeError.create().show();


        }else{
            //Generamos una alerta para que el usuario se de cuenta de que ya se
            // llenaron los datos correctamente
            AlertDialog.Builder mensajeError = new AlertDialog.Builder(this);
            mensajeError.setMessage("Los datos se han agregado");
            //Mostramos el mensaje de alerta
            mensajeError.create().show();
            //Agregamos los precios y los productos a las listas
            int valorPrecio = Integer.parseInt(precio);
            listaProductos.add(nomProducto);
            listaPrecios.add(valorPrecio);
            //Limpiamos los campos para que el usuario pueda ingresar unos nuevos
            TBnomProducto.setText("");
            TBprecioProducto.setText("");
        }
    }

    /**
     *
     * @param v
     */
    //Metodo con el cual mandamos las listas con la información
    public void onClickEnviarListas(View v){
        //Validamos que la lista de productos no este vacia
        if(listaProductos.isEmpty()){
            AlertDialog.Builder mensajeError2 = new AlertDialog.Builder(this);
            mensajeError2.setMessage("No se ha ingresado ningún producto");
            mensajeError2.setTitle("Error: Falta información");
            mensajeError2.create().show();
            return;
        }
        //Con este intent mandamos la siguiente actividad confirmar_listas
        Intent i = new Intent(this, Confirmar_Listas.class);
        //Al intent le pasamos las listas
        i.putStringArrayListExtra("listaProductos",listaProductos);
        i.putIntegerArrayListExtra("listaPrecios",listaPrecios);
        //Mandamos la actividad
        startActivity(i);
    }
}
