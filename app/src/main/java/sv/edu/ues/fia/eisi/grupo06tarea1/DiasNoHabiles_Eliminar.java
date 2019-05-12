//Autor: Maria Abigail Gil Cordova
//Carnet:GC16001
//Grupo de trabajo: 06

package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DiasNoHabiles_Eliminar extends AppCompatActivity {
    DBHelperInicial DBHelper;
    EditText fechaEliminar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dias_no_habiles__eliminar);
        DBHelper = new DBHelperInicial(this);
        fechaEliminar = (EditText) findViewById(R.id.fechaEliminarNH);
    }
    public void eliminarDia(View v)
    {
        if(fechaEliminar.getText().toString().equals("") ){
            Toast.makeText(this,"Ingrese datos en los campos", Toast.LENGTH_SHORT).show();
        }
        else {
            DBHelper.abrir();

            String mensaje = DBHelper.eliminarDia(fechaEliminar.getText().toString());
            DBHelper.cerrar();
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();


        }
    }
    public void limpiarTexto(View v) {
        fechaEliminar.setText("");


    }
}
