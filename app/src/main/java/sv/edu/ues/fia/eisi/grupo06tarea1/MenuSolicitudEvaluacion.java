package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuSolicitudEvaluacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_solicitud_evaluacion);
    }

    public void solicitudEvaluacionInsertar(View v){
        Intent ven=new Intent(this,SolicitudEvaluacion_Insertar.class);
        startActivity(ven);
    }

    public void solicitudEvaluacionEliminar(View v){
        Intent ven=new Intent(this,SolicitudEvaluacion_Eliminar.class);
        startActivity(ven);
    }

    public void solicitudEvaluacionActualizar(View v){
        Intent ven=new Intent(this,SolicitudEvaluacion_Actualizar.class);
        startActivity(ven);
    }

    public void solicitudEvaluacionConsultar(View v){
        Intent ven=new Intent(this,SolicitudEvaluacion_Consultar.class);
        startActivity(ven);
    }
}
