package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuSolicitudPrimerRevision extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_solicitud_primer_revision);
    }

    public void insertarSolicitudPrimerRevision(View v){
        Intent ven=new Intent(this,SolicitudPrimerRevision_Insertar.class);
        startActivity(ven);
    }

    public void consultarSolicitudPrimerRevision(View v){
        Intent ven=new Intent(this,SolicitudPrimerRevision_Consultar.class);
        startActivity(ven);
    }

    public void actualizarSolicitudPrimerRevision(View v){
        Intent ven=new Intent(this,SolicitudPrimerRevision_Actualizar.class);
        startActivity(ven);
    }

    public void eliminarSolicitudPrimerRevision(View v){
        Intent ven=new Intent(this,SolicitudPrimerRevision_Eliminar.class);
        startActivity(ven);
    }

}
