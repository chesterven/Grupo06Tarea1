//Autor: Christian Ariel Zelaya Tejada
//Carnet:ZT12002
//Grupo de trabajo: 06
package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuEvaluaciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_evaluaciones);
    }
    public void insertarEvaluaciones(View v){
        Intent ven=new Intent(this,Evaluaciones_Insertar.class);
        startActivity(ven);
    }

    public void consultarEvaluaciones(View v){
        Intent ven=new Intent(this,Evaluaciones_Consultar.class);
        startActivity(ven);
    }

    public void actualizarEvaluaciones(View v){
        Intent ven=new Intent(this,Evaluaciones_Actualizar.class);
        startActivity(ven);
    }

    public void eliminarEvaluaciones(View v){
        Intent ven=new Intent(this,Evaluaciones_Eliminar.class);
        startActivity(ven);
    }
}
