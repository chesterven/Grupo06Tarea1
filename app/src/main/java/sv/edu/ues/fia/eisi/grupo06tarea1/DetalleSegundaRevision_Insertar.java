//Autor: Maria Abigail Gil Cordova
//Carnet:GC16001
//Grupo de trabajo: 06

package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class DetalleSegundaRevision_Insertar extends AppCompatActivity {

    DBHelperInicial DBHelper;
    Spinner descripciones;
    EditText carnetIn;
   CheckBox asistencia;
   EditText notaIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_segunda_revision__insertar);
        descripciones = (Spinner) findViewById(R.id.spinnerResultadosDescripcionEvalua);
        carnetIn = (EditText) findViewById(R.id.carnetDetalleSegundaIn);
        asistencia = (CheckBox) findViewById(R.id.asistenciaSegundaRevision);
        notaIn = (EditText) findViewById(R.id.notaDetalleSegundaIn);
    }
    public void insertarDetalleSegundaRevision(View v)
    {

    }
    public void limpiarTexto(View v) {
        carnetIn.setText("");
        notaIn.setText("");

    }

}
