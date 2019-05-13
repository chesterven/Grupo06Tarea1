//Nombre: Maria Abigail Gil Cordova
//Carnet:GC16001
//Grupo de trabajo: 06
package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class DetalleSegundaRevision_Consultar extends AppCompatActivity {

    DBHelperInicial DBHelper;
    Spinner resultados;
    EditText carnet;
    EditText asistencia;
    EditText nota;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_segunda_revision__consultar);
        resultados = (Spinner) findViewById(R.id.spinnerResultadosDescripcionEvalua);
        carnet = (EditText) findViewById(R.id.carnetDetalleSegundaCon);
        asistencia = (EditText) findViewById(R.id.asistenciaMostrarSegun);
        nota = (EditText) findViewById(R.id.notaSegundaRevisionMostrarSegun);
    }
    public void consultarDetalleSegundaRevision(View v)
    {
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();



        DBHelper.cerrar();

    }
    public void limpiarTexto(View v) {
        carnet.setText("");
        asistencia.setText("");
        nota.setText("");

    }
}