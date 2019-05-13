//Autor: Roberto Eliezer Ventura Dominguez
//Carnet:VD16006
//Grupo de trabajo: 06

package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Solicitud_Repetido_Insertar extends AppCompatActivity {
    DBHelperInicial DBHelper;
    EditText carnet, evaluacion, motivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud__repetido__insertar);
        carnet = (EditText) findViewById(R.id.carnetRepetidoIn);
        evaluacion = (EditText) findViewById(R.id.idEvaluacionRepetidoIn);
        motivo = (EditText) findViewById(R.id.motivoSoliRepetidoIn);
    }

    public void limpiarTexto(View v){
        carnet.setText("");
        evaluacion.setText("");
        motivo.setText("");
    }
}
