//Autor: Roberto Eliezer Ventura Dominguez
//Carnet:VD16006
//Grupo de trabajo: 06

package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Solicitud_DiferidoRepetido_Eliminar extends AppCompatActivity {
    DBHelperInicial DBHelper;
    EditText carnet,evaluacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud__diferido_repetido__eliminar);
        carnet = (EditText) findViewById(R.id.carnetEliminarSoliDyR);
        evaluacion = (EditText) findViewById(R.id.evaluEliminarSoliDyR);
    }

    public void limpiarTexto(View v){
        carnet.setText("");
        evaluacion.setText("");
    }
}
