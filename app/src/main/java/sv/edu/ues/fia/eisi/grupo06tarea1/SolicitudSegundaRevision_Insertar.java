package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class SolicitudSegundaRevision_Insertar extends AppCompatActivity {
    DBHelperInicial DBHelper;
    EditText carnetSoliIn, motivoSoliIn;
    Spinner spinnerResultado;
    Cursor datos;
    ArrayList<String> evaluaciones = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_segunda_revision_insertar);
    }

    public void limpiarTexto(View v){
        carnetSoliIn.setText("");
        motivoSoliIn.setText("");
        spinnerResultado.setAdapter(null);
        evaluaciones.clear();
    }
}
