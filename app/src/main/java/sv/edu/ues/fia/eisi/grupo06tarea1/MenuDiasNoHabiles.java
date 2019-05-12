//Autor: Maria Abigail Gil Cordova
//Carnet:GC16001
//Grupo de trabajo: 06

package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuDiasNoHabiles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_dias_no_habiles);
    }

    public void diasNoHabilesInsertar(View v){
        Intent ven=new Intent(this,DiasNoHabiles_Insertar.class);
        startActivity(ven);
    }

    public void diasNoHabilesEliminar(View v){
        Intent ven=new Intent(this,DiasNoHabiles_Eliminar.class);
        startActivity(ven);
    }

    public void diasNoHabilesActualizar(View v){
        Intent ven=new Intent(this,DiasNoHabiles_Actualizar.class);
        startActivity(ven);
    }

    public void diasNoHabilesConsultar(View v){
        Intent ven=new Intent(this,DiasNoHabiles_Consultar.class);
        startActivity(ven);
    }
}
