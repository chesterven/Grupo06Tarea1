//Autor: Roberto Eliezer Ventura Dominguez
//Carnet:VD16006
//Grupo de trabajo: 06

package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Solicitud_DiferidoRepetido_Consultar extends AppCompatActivity {
    DBHelperInicial DBHelper;
    EditText carnetSoli,evaluacionSoli, tipoSoli,aceptado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud__diferido_repetido__consultar);
        carnetSoli = (EditText) findViewById(R.id.carnetSoliDyRCon);
        evaluacionSoli = (EditText) findViewById(R.id.evaluSoliDyRCon);
        tipoSoli = (EditText) findViewById(R.id.tipoSoliDyRMostrar);
        aceptado = (EditText) findViewById(R.id.AceptadaSoliDyRMostrar); //Para agregar un text dependiendo del estado.
    }

    public void limpiarTexto(View v){
        carnetSoli.setText("");
        evaluacionSoli.setText("");
        tipoSoli.setText("");
        aceptado.setText("");
    }
}
