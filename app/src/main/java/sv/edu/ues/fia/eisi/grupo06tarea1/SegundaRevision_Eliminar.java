//Autor: Maria Abigail Gil Cordova
//Carnet:GC16001
//Grupo de trabajo: 06

package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SegundaRevision_Eliminar extends AppCompatActivity {

    DBHelperInicial DBHelper;
    EditText idSegundaRevision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_revision__eliminar);
        idSegundaRevision = (EditText) findViewById(R.id.idEvaluacionSegundaRevisionEli);
    }
    public void eliminarSegundaRevision(View v)
    {
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();

        DBHelper.cerrar();
    }
    public void limpiarTexto(View v) {
        idSegundaRevision.setText("");


    }
}
