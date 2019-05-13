package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuSolicitudSegundaRevision extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_solicitud_segunda_revision);
    }

    public void insertarSolicitudSegundaRevision(View v){
        Intent inte=new Intent(this,SolicitudSegundaRevision_Insertar.class);
        startActivity(inte);
    }

    public void consultarSolicitudSegundaRevision(View v){
        Intent inte=new Intent(this,SolicitudSegundaRevision_Consultar.class);
        startActivity(inte);
    }

    public void actualizarSolicitudSegundaRevision(View v){
        Intent inte=new Intent(this,SolicitudSegundaRevision_Actualizar.class);
        startActivity(inte);
    }

    public void eliminarSolicitudSegundaRevision(View v){
        Intent inte=new Intent(this,SolicitudSegundaRevision_Eliminar.class);
        startActivity(inte);
    }
}
