//Autor: Christian Ariel Zelaya Tejada
//Carnet:ZT12002
//Grupo de trabajo: 06
package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuParametros extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_parametros);
    }
    public void insertarParametros(View v){
        Intent ven=new Intent(this,Parametros_Insertar.class);
        startActivity(ven);
    }

    public void consultarParametros(View v){
        Intent ven=new Intent(this,Parametros_Consultar.class);
        startActivity(ven);
    }

    public void actualizarParametros(View v){
        Intent ven=new Intent(this,Parametros_Actualizar.class);
        startActivity(ven);
    }

    public void eliminarParametros(View v){
        Intent ven=new Intent(this,Parametros_Eliminar.class);
        startActivity(ven);
    }
}
