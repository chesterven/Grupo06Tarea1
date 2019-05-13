//Autor: Maria Abigail Gil Cordova
//Carnet:GC16001
//Grupo de trabajo: 06
package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class DetalleSegundaRevision_Eliminar extends AppCompatActivity {

    DBHelperInicial DBHelper;
    EditText carnetEliminar;
    Spinner descripcionEvaluacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_segunda_revision__eliminar);
        carnetEliminar = (EditText) findViewById(R.id.carnetDetalleSegundaElimi);
        descripcionEvaluacion = (Spinner) findViewById(R.id.spinnerResultadosDescripcionEvalua);
    }
    public void eliminarDetalleSegundaRevision(View v)
    {
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();


        DBHelper.cerrar();

    }


    public void limpiarTexto(View v) {
       carnetEliminar.setText("");
    }
}
