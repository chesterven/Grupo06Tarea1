//Autor: Maria Abigail Gil Cordova
//Carnet:GC16001
//Grupo de trabajo: 06

package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DiasNoHabiles_Actualizar extends AppCompatActivity {
    DBHelperInicial DBHelper;
    EditText fechaAnterior;
    EditText fechaNueva;
    EditText cicloNuevo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dias_no_habiles__actualizar);
        DBHelper = new DBHelperInicial(this);
        fechaAnterior = (EditText) findViewById(R.id.fechaAnterior);
        fechaNueva = (EditText) findViewById(R.id.fechaNueva);
        cicloNuevo = (EditText) findViewById(R.id.cicloActualizado);
    }
    public void actualizarDia(View v)
    {
        if(fechaAnterior.getText().toString().equals("") |
                fechaNueva.getText().toString().equals("")|
                cicloNuevo.getText().toString().equals(""))
        {
            Toast.makeText(this,"Ingrese datos en los campos", Toast.LENGTH_SHORT).show();
        }
        else {
            DBHelper.abrir();
            int resultadoCiclo = DBHelper.consultarCiclo(cicloNuevo.getText().toString());
            String mensaje= DBHelper.actualizarDia(fechaAnterior.getText().toString(),fechaNueva.getText().toString(),resultadoCiclo);
            DBHelper.cerrar();
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();


        }
    }
    public void limpiarTexto(View v) {
        fechaNueva.setText("");
        fechaAnterior.setText("");
        cicloNuevo.setText("");


    }
}
