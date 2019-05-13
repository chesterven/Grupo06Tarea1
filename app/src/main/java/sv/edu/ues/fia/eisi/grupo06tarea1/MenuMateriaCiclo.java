//Autor: Christian Ariel Zelaya Tejada
//Carnet:ZT12002
//Grupo de trabajo: 06
package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuMateriaCiclo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_materia_ciclo);
    }
    public void insertarMateriaCiclo(View v){
        Intent ven=new Intent(this,MateriaCiclo_Insertar.class);
        startActivity(ven);
    }

    public void consultarMateriaCiclo(View v){
        Intent ven=new Intent(this,MateriaCiclo_Consultar.class);
        startActivity(ven);
    }

    public void actualizarMateriaCiclo(View v){
        Intent ven=new Intent(this,MateriaCiclo_Actualizar.class);
        startActivity(ven);
    }

    public void eliminarMateriaCiclo(View v){
        Intent ven=new Intent(this,MateriaCiclo_Eliminar.class);
        startActivity(ven);
    }
}
