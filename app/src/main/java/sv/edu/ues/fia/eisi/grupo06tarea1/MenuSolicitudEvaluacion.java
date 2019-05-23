
package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

//Autor: Roberto Eliezer Ventura Dominguez
//Carnet:VD16006
//Grupo de trabajo: 06
public class MenuSolicitudEvaluacion extends AppCompatActivity {

    String idusuario = "";
    DBHelperInicial DBHelper;
    ArrayList<String> arrayAccesos = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_solicitud_evaluacion);
        Intent recibir = getIntent();
        idusuario = recibir.getStringExtra("idusuario");

        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        arrayAccesos = DBHelper.consultarAccesos(idusuario);
        DBHelper.cerrar();
    }

    public void solicitudEvaluacionInsertar(View v){

        String codOpcion = "032";
        boolean permitido = false;
        for(int i=0; i<arrayAccesos.size();i++){
            if(codOpcion.equals(arrayAccesos.get(i))){
                permitido = true;
            }
        }
        if(permitido || idusuario.equals("01")){
            Toast.makeText(this,"Tiene Acceso", Toast.LENGTH_SHORT).show();
            Intent ven=new Intent(this,SolicitudEvaluacion_Insertar.class);
            startActivity(ven);
        }
        else{
            Toast.makeText(this,"No Tiene Acceso", Toast.LENGTH_SHORT).show();
        }

    }

    public void solicitudEvaluacionEliminar(View v){

        String codOpcion = "035";
        boolean permitido = false;
        for(int i=0; i<arrayAccesos.size();i++){
            if(codOpcion.equals(arrayAccesos.get(i))){
                permitido = true;
            }
        }
        if(permitido || idusuario.equals("01")){
            Toast.makeText(this,"Tiene Acceso", Toast.LENGTH_SHORT).show();
            Intent ven=new Intent(this,SolicitudEvaluacion_Eliminar.class);
            startActivity(ven);
        }
        else{
            Toast.makeText(this,"No Tiene Acceso", Toast.LENGTH_SHORT).show();
        }

    }

    public void solicitudEvaluacionActualizar(View v){

        String codOpcion = "033";
        boolean permitido = false;
        for(int i=0; i<arrayAccesos.size();i++){
            if(codOpcion.equals(arrayAccesos.get(i))){
                permitido = true;
            }
        }
        if(permitido || idusuario.equals("01")){
            Toast.makeText(this,"Tiene Acceso", Toast.LENGTH_SHORT).show();
            Intent ven=new Intent(this,SolicitudEvaluacion_Actualizar.class);
            startActivity(ven);
        }
        else{
            Toast.makeText(this,"No Tiene Acceso", Toast.LENGTH_SHORT).show();
        }


    }

    public void solicitudEvaluacionConsultar(View v){

        String codOpcion = "034";
        boolean permitido = false;
        for(int i=0; i<arrayAccesos.size();i++){
            if(codOpcion.equals(arrayAccesos.get(i))){
                permitido = true;
            }
        }
        if(permitido || idusuario.equals("01")){
            Toast.makeText(this,"Tiene Acceso", Toast.LENGTH_SHORT).show();
            Intent ven=new Intent(this,SolicitudEvaluacion_Consultar.class);
            startActivity(ven);
        }
        else{
            Toast.makeText(this,"No Tiene Acceso", Toast.LENGTH_SHORT).show();
        }

    }
}
