//Autor: Maria Abigail Gil Cordova
//Carnet:GC16001
//Grupo de trabajo: 06

package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class DetalleSegundaRevision_Actualizar extends AppCompatActivity {

    DBHelperInicial DBHelper;
    EditText carnet;
    EditText nota;
    Spinner descripciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_segunda_revision__actualizar);
        carnet = (EditText) findViewById(R.id.carnetDetalleSegundaAct);
        nota=(EditText) findViewById(R.id.carnetDetalleSegundaAct);
        descripciones=(Spinner)findViewById(R.id.spinnerResultadosDescripcionEvalua);

    }
    public  void actualizarSegundaRevision(View v)
    {
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();





        DBHelper.cerrar();
    }
    public void limpiarTexto(View v) {
        carnet.setText("");
        nota.setText("");


    }
}
