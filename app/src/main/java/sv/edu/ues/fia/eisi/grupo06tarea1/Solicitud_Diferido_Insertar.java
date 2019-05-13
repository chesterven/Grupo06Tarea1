//Autor: Roberto Eliezer Ventura Dominguez
//Carnet:VD16006
//Grupo de trabajo: 06

package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Spinner;

public class Solicitud_Diferido_Insertar extends AppCompatActivity {
    DBHelperInicial DBHelper;
    EditText carnetSoliIn, codMateriaIn, numGrupoSoliIn, motivoSoliIn;
    Spinner tipoGrupo, spinnerResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud__diferido__insertar);

        carnetSoliIn = (EditText) findViewById(R.id.carnetSoliDiferidoIn);
        codMateriaIn = (EditText) findViewById(R.id.codMateriaSoliDiferidoIn);
        tipoGrupo = (Spinner) findViewById(R.id.spinner);
        numGrupoSoliIn = (EditText) findViewById(R.id.numGrupoSoliDiferidoIn);

        spinnerResultado = (Spinner) findViewById(R.id.spinnerResultados);
        motivoSoliIn = (EditText) findViewById(R.id.motivoSoliDiferidoIn);

    }

    public void limpiarTexto(View v){
        carnetSoliIn.setText("");
        codMateriaIn.setText("");
        numGrupoSoliIn.setText("");
        motivoSoliIn.setText("");
        spinnerResultado.setAdapter(null);
    }
}
