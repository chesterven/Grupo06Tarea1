//Autor: Roberto Eliezer Ventura Dominguez
//Carnet:VD16006
//Grupo de trabajo: 06

package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TipoSolicitud_Insertar extends AppCompatActivity {

    DBHelperInicial DBHelper;
    EditText nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_solicitud__insertar);
        nombre = (EditText) findViewById(R.id.nombreInsertar);
    }

    public void insertarTipoSolicitud(View v){
        if(nombre.getText().toString().equals("")){
            Toast.makeText(this,"Ingrese un nombre en el campo", Toast.LENGTH_SHORT).show();
        }
        else{

            DBHelper = new DBHelperInicial(this);
            DBHelper.abrir();
            String mensaje =DBHelper.insertarTipoSolicitud(nombre.getText().toString());
            DBHelper.cerrar();
            Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show();
        }

    }

    public void limpiarTexto(View v){
        nombre.setText("");

    }


}
