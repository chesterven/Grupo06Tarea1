//CS16008 Castro Sánchez José Andrés
package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuSolicitudImpresion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_solicitud_impresion);
    }

    public void consultarSolicitudImpresion(View v){
        Intent ven=new Intent(this,SolicitudImpresion_Consultar.class);
        startActivity(ven);
    }

    public void insertarSolicitudImpresion(View v){
        Intent ven=new Intent(this,SolicitudImpresion_Insertar.class);
        startActivity(ven);
    }

    public void actualizarSolicitudImpresion(View v){
        Intent ven=new Intent(this,SolicitudImpresion_Actualizar.class);
        startActivity(ven);
    }

    public void eliminarSolicitudImpresion(View v){
        Intent ven=new Intent(this,SolicitudImpresion_Eliminar.class);
        startActivity(ven);
    }
}
