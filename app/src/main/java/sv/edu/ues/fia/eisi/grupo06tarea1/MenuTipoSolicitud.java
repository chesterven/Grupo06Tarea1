package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuTipoSolicitud extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_tipo_solicitud);
    }

    public void tipoSolicitudInsertar(View v){
        Intent ven=new Intent(this,TipoSolicitud_Insertar.class);
        startActivity(ven);
    }

    public void tipoSolicitudEliminar(View v){
        Intent ven=new Intent(this,TipoSolicitud_Eliminar.class);
        startActivity(ven);
    }

    public void tipoSolicitudActualizar(View v){
        Intent ven=new Intent(this,TipoSolicitud_Actualizar.class);
        startActivity(ven);
    }

    public void tipoSolicitudConsultar(View v){
        Intent ven=new Intent(this,TipoSolicitud_Consultar.class);
        startActivity(ven);
    }
}
