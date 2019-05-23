//Autor: Maria Abigail Gil Cordova
//Carnet:GC16001
//Grupo de trabajo: 06

package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuDetalleSegundaRevision extends AppCompatActivity {

    String idusuario = "";
    DBHelperInicial DBHelper;
    ArrayList<String> arrayAccesos = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detalle_segunda_revision);
        Intent recibir = getIntent();
        idusuario = recibir.getStringExtra("idusuario");

        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        arrayAccesos = DBHelper.consultarAccesos(idusuario);
        DBHelper.cerrar();
    }

    public void detalleSegundaRevisionInsertar(View v){
        String codOpcion = "028";
        boolean permitido = false;
        for(int i=0; i<arrayAccesos.size();i++){
            if(codOpcion.equals(arrayAccesos.get(i))){
                permitido = true;
            }
        }
        if(permitido || idusuario.equals("01")){
            Toast.makeText(this,"Tiene Acceso", Toast.LENGTH_SHORT).show();
            Intent ven=new Intent(this,DetalleSegundaRevision_Insertar.class);
            startActivity(ven);
        }
        else{
            Toast.makeText(this,"No Tiene Acceso", Toast.LENGTH_SHORT).show();
        }


    }

    public void detalleSegundaRevisionEliminar(View v){

        String codOpcion = "031";
        boolean permitido = false;
        for(int i=0; i<arrayAccesos.size();i++){
            if(codOpcion.equals(arrayAccesos.get(i))){
                permitido = true;
            }
        }
        if(permitido || idusuario.equals("01")){
            Toast.makeText(this,"Tiene Acceso", Toast.LENGTH_SHORT).show();
            Intent ven=new Intent(this,DetalleSegundaRevision_Eliminar.class);
            startActivity(ven);
        }
        else{
            Toast.makeText(this,"No Tiene Acceso", Toast.LENGTH_SHORT).show();
        }



    }

    public void detalleSegundaRevisionActualizar(View v){

        String codOpcion = "029";
        boolean permitido = false;
        for(int i=0; i<arrayAccesos.size();i++){
            if(codOpcion.equals(arrayAccesos.get(i))){
                permitido = true;
            }
        }
        if(permitido || idusuario.equals("01")){
            Toast.makeText(this,"Tiene Acceso", Toast.LENGTH_SHORT).show();
            Intent ven=new Intent(this,DetalleSegundaRevision_Actualizar.class);
            startActivity(ven);
        }
        else{
            Toast.makeText(this,"No Tiene Acceso", Toast.LENGTH_SHORT).show();
        }


    }

    public void detalleSegundaRevisionConsultar(View v){

        String codOpcion = "030";
        boolean permitido = false;
        for(int i=0; i<arrayAccesos.size();i++){
            if(codOpcion.equals(arrayAccesos.get(i))){
                permitido = true;
            }
        }
        if(permitido || idusuario.equals("01")){
            Toast.makeText(this,"Tiene Acceso", Toast.LENGTH_SHORT).show();
            Intent ven=new Intent(this,DetalleSegundaRevision_Consultar.class);
            startActivity(ven);
        }
        else{
            Toast.makeText(this,"No Tiene Acceso", Toast.LENGTH_SHORT).show();
        }


    }
}
