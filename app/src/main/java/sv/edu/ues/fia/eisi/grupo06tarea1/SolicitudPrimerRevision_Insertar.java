//CS16008 Castro Sánchez José Andrés
package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SolicitudPrimerRevision_Insertar extends AppCompatActivity {

    DBHelperInicial DBHelper;

    EditText carnet;
    EditText idEvaluacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_primer_revision__insertar);

        idEvaluacion=(EditText)findViewById(R.id.editTextIdEvaluacionSolicitudPrimerRevision);
        carnet=(EditText) findViewById(R.id.editTextCarnetSolicitudPrimerRevision);

        DBHelper= new DBHelperInicial(this);
    }

    public void insertarSolicitudPrimerRevision(View v){
        if(idEvaluacion.getText().toString().equals("")||carnet.getText().toString().equals("")){
            Toast.makeText(this, "Existen campos vacíos en el formulario", Toast.LENGTH_LONG).show();
        }
        else{
            DBHelper.abrir();
            String msj=DBHelper.insertarSolicitudPrimerRevision(idEvaluacion.getText().toString(),carnet.getText().toString().toUpperCase());
            DBHelper.cerrar();
            Toast.makeText(this, msj, Toast.LENGTH_LONG).show();
            limpiarTexto();
        }
    }
    public void limpiarTexto(View v){
        idEvaluacion.setText("");
        carnet.setText("");
    }
    public void limpiarTexto(){
        idEvaluacion.setText("");
        carnet.setText("");
    }
}
