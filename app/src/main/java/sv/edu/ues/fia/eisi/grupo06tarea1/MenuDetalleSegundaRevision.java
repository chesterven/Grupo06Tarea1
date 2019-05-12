//Autor: Maria Abigail Gil Cordova
//Carnet:GC16001
//Grupo de trabajo: 06

package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuDetalleSegundaRevision extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detalle_segunda_revision);
    }

    public void detalleSegundaRevisionInsertar(View v){
        Intent ven=new Intent(this,DetalleSegundaRevision_Insertar.class);
        startActivity(ven);
    }

    public void detalleSegundaRevisionEliminar(View v){
        Intent ven=new Intent(this,DetalleSegundaRevision_Eliminar.class);
        startActivity(ven);
    }

    public void detalleSegundaRevisionActualizar(View v){
        Intent ven=new Intent(this,DetalleSegundaRevision_Actualizar.class);
        startActivity(ven);
    }

    public void detalleSegundaRevisionConsultar(View v){
        Intent ven=new Intent(this,DetalleSegundaRevision_Consultar.class);
        startActivity(ven);
    }
}
