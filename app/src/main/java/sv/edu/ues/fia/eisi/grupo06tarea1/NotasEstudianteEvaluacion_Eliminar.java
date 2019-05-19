//CS16008 Castro Sánchez José Andrés
package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NotasEstudianteEvaluacion_Eliminar extends AppCompatActivity {

    DBHelperInicial DBHelper;

    EditText evalua;
    EditText carnet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas_estudiante_evaluacion__eliminar);

        evalua= (EditText) findViewById(R.id.EvaluacionEliminarNotaEstudianteEvaluacion);
        carnet=(EditText) findViewById(R.id.carnetEliminarNotaEstudianteEvaluacion);

        DBHelper = new DBHelperInicial(this);
    }

    public void eliminarNota(View v){
        if(carnet.getText().toString().toUpperCase().equals("")||evalua.getText().equals("")){
            Toast.makeText(this, "Posee campos en blanco en el formulario", Toast.LENGTH_SHORT).show();
        }
        else{
            DBHelper.abrir();
            String msj=DBHelper.eliminarNotaEstudianteEvaluacion(carnet.getText().toString().toUpperCase(),evalua.getText().toString());
            DBHelper.cerrar();
            Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarTexto(View v){
        carnet.setText("");
        evalua.setText("");
    }

}
