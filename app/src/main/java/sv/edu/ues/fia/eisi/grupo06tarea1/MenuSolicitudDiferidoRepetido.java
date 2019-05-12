//Autor: Roberto Eliezer Ventura Dominguez
//Carnet:VD16006
//Grupo de trabajo: 06

package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuSolicitudDiferidoRepetido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_solicitud_diferido_repetido);
    }

    public void solicitudRepetidoInsertar(View v){
        Intent ven=new Intent(this,Solicitud_Repetido_Insertar.class);
        startActivity(ven);
    }

    public void solicitudDiferidoInsertar(View v){
        Intent ven=new Intent(this,Solicitud_Diferido_Insertar.class);
        startActivity(ven);
    }

    public void solicitudDiferidoRepetidoEliminar(View v){
        Intent ven=new Intent(this,Solicitud_DiferidoRepetido_Eliminar.class);
        startActivity(ven);
    }

    public void solicitudDiferidoRepetidoActualizar(View v){
        Intent ven=new Intent(this,Solicitud_DiferidoRepetido_Actualizar.class);
        startActivity(ven);
    }

    public void solicitudDiferidoRepetidoConsultar(View v){
        Intent ven=new Intent(this,Solicitud_DiferidoRepetido_Consultar.class);
        startActivity(ven);
    }
}
