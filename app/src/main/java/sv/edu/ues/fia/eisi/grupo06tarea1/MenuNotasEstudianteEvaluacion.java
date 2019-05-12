//CS16008 Castro Sánchez José Andrés
package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MenuNotasEstudianteEvaluacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_notas_estudiante_evaluacion);
    }

    public void insertarNotaEstudianteEvaluacion(View v){
        Intent ven=new Intent(this,NotasEstudianteEvaluacion_Insertar.class);
        startActivity(ven);
    }

    public void eliminarNotaEstudianteEvaluacion(View v){
        Intent ven=new Intent(this,NotasEstudianteEvaluacion_Eliminar.class);
        startActivity(ven);
    }

    public void consultarNotaEstudianteEvaluacion(View v){
        Intent ven=new Intent(this,NotasEstudianteEvaluacion_Consultar.class);
        startActivity(ven);
    }

    public void actualizarNotaEstudianteEvaluacion(View v){
        Intent ven=new Intent(this,NotasEstudianteEvaluacion_Actualizar.class);
        startActivity(ven);
    }

}
