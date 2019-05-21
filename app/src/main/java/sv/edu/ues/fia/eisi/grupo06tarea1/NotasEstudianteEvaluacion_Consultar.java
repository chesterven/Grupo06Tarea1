//CS16008 Castro Sánchez José Andrés
package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NotasEstudianteEvaluacion_Consultar extends AppCompatActivity {

    DBHelperInicial DBHelper;

    EditText evalua;
    EditText carnet;
    EditText nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas_estudiante_evaluacion__consultar);

        evalua= (EditText) findViewById(R.id.EvaluacionesNotaEstudianteEvaluacionConsultar);
        carnet=(EditText) findViewById(R.id.carnetNotaEstudianteEvaluacionConsultar);
        nota= (EditText) findViewById(R.id.mostrarNota);

        DBHelper = new DBHelperInicial(this);
    }

    public void consultarNota(View v){
        nota.setText("");
        if(carnet.getText().toString().toUpperCase().equals("")||evalua.getText().equals("")){
            Toast.makeText(this, "Posee campos en blanco en el formulario", Toast.LENGTH_SHORT).show();
        }
        else{
            DBHelper.abrir();
            Cursor msj=DBHelper.consultarNotaEstudianteEvaluacion(carnet.getText().toString().toUpperCase(),evalua.getText().toString());
            if(msj.moveToFirst()){
                nota.setText(String.valueOf(msj.getFloat(2)));
            }
            else
            {Toast.makeText(this, "No hay nota registrada", Toast.LENGTH_SHORT).show();
            }
            DBHelper.cerrar();
        }
    }

    public void limpiarTexto(View v){
        carnet.setText("");
        evalua.setText("");
        nota.setText("");

    }
}
