//CS16008 Castro Sánchez José Andrés
package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class NotasEstudianteEvaluacion_Actualizar extends AppCompatActivity {

    DBHelperInicial DBHelper;

    EditText evalua;
    EditText carnet;
    EditText nota;


    ArrayAdapter<CharSequence> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas_estudiante_evaluacion__actualizar);
        evalua= (EditText) findViewById(R.id.editTextEvaluacionesGrupoMateriaNotasEstudianteEvaluacionActualizar);
        carnet=(EditText) findViewById(R.id.editTextCarnetNotaEstudianteEvaluacionActualizar);
        nota= (EditText) findViewById(R.id.notaNotaEstudianteEvaluacionActualizar);

        DBHelper = new DBHelperInicial(this);
    }


    public void actualizarNota(View v){
        if(carnet.getText().toString().toUpperCase().equals("")||evalua.getText().toString().equals("")||nota.getText().toString().equals("")){
            Toast.makeText(this, "Posee campos en blanco en el formulario", Toast.LENGTH_SHORT).show();
        }
        else{
            if(Float.valueOf(nota.getText().toString())<0||Float.valueOf(nota.getText().toString())>10){
                Toast.makeText(this, "Error en nota ingresada. Debe estar entre 0 y 10 su valor", Toast.LENGTH_SHORT).show();
            }else{
                DBHelper.abrir();
                String msj=DBHelper.actualizarNotaEstudianteEvaluacion(carnet.getText().toString().toUpperCase(),evalua.getText().toString(),nota.getText().toString());
                DBHelper.cerrar();
                Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void limpiarTexto(View v){
        carnet.setText("");
        evalua.setText("");
        nota.setText("");
    }

}
