//Autor: Christian Ariel Zelaya Tejada
//Carnet:ZT12002
//Grupo de trabajo: 06
package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Parametros_Actualizar extends AppCompatActivity {
    DBHelperInicial DBHelper;
    Spinner nombre;
    ArrayList<String> nombres = new ArrayList<>();
    EditText dias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametros__actualizar);
        DBHelper = new DBHelperInicial(this);
        nombre = (Spinner) findViewById(R.id.spinner);
        dias = (EditText) findViewById(R.id.dias);
        nombres.add("Seleccione el parametro");
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        Cursor resultado = DBHelper.consultarNombre();
        if(resultado.moveToFirst()){
            do{
               nombres.add(String.valueOf(resultado.getInt(0)) + " " + resultado.getString(1));
            }while (resultado.moveToNext());
        }
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,nombres);
        nombre.setAdapter(adaptador);


    }

    public void actualizarParametro(View v)
    {
        if(nombre.getSelectedItem().toString().equals("seleccione el parametro")|
        dias.getText().toString().equals("")){
            Toast.makeText(this,"Ingrese todos los campos", Toast.LENGTH_SHORT).show();
        }
        else{
            DBHelper = new DBHelperInicial(this);
            DBHelper.abrir();
            String nombreTipo = "";
            nombreTipo = nombre.getSelectedItem().toString();
            String[] nombreParte = nombreTipo.split(" ");
            Integer id = Integer.valueOf(nombreParte[0]);
            String mensaje = DBHelper.actualizarParametro(id);
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();



        }
    }


}
