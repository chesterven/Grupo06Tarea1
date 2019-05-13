//Autor: Roberto Eliezer Ventura Dominguez
//Carnet:VD16006
//Grupo de trabajo: 06

package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class Solicitud_DiferidoRepetido_Actualizar extends AppCompatActivity {
    DBHelperInicial DBHelper;
    EditText carnetSoli, idEvaluacionSoli, motivoSoli;
    CheckBox aprobado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud__diferido_repetido__actualizar);
        carnetSoli = (EditText) findViewById(R.id.carnetSoliDyRAct);
        idEvaluacionSoli = (EditText) findViewById(R.id.idEvaluacionSoliDyRIn);
        motivoSoli = (EditText) findViewById(R.id.motivoSoliDyFIn);
        aprobado = (CheckBox) findViewById(R.id.checkBoxAprobadoSoliDyRIn);



    }

    public void limpiarTexto(View v){
        carnetSoli.setText("");
        idEvaluacionSoli.setText("");
        motivoSoli.setText("");
        aprobado.setSelected(false);
    }
}
