//Autor: Roberto Eliezer Ventura Dominguez
//Carnet:VD16006
//Grupo de trabajo: 06

package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuSolicitudDiferidoRepetido extends AppCompatActivity {

    String idusuario = "";
    DBHelperInicial DBHelper;
    ArrayList<String> arrayAccesos = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_solicitud_diferido_repetido);
        Intent recibir = getIntent();
        idusuario = recibir.getStringExtra("idusuario");

        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        arrayAccesos = DBHelper.consultarAccesos(idusuario);
        DBHelper.cerrar();
    }

    public void solicitudRepetidoInsertar(View v){
        String codOpcion = "040";
        boolean permitido = false;
        for(int i=0; i<arrayAccesos.size();i++){
            if(codOpcion.equals(arrayAccesos.get(i))){
                permitido = true;
            }
        }
        if(permitido || idusuario.equals("01")){
            Toast.makeText(this,"Tiene Acceso", Toast.LENGTH_SHORT).show();
            Intent ven=new Intent(this,Solicitud_Repetido_Insertar.class);
            startActivity(ven);
        }
        else{
            Toast.makeText(this,"No Tiene Acceso", Toast.LENGTH_SHORT).show();
        }


    }

    public void solicitudDiferidoInsertar(View v){
        String codOpcion = "041";
        boolean permitido = false;
        for(int i=0; i<arrayAccesos.size();i++){
            if(codOpcion.equals(arrayAccesos.get(i))){
                permitido = true;
            }
        }
        if(permitido || idusuario.equals("01")){
            Toast.makeText(this,"Tiene Acceso", Toast.LENGTH_SHORT).show();
            Intent ven=new Intent(this,Solicitud_Diferido_Insertar.class);
            startActivity(ven);
        }
        else{
            Toast.makeText(this,"No Tiene Acceso", Toast.LENGTH_SHORT).show();
        }


    }

    public void solicitudDiferidoRepetidoEliminar(View v){
        String codOpcion = "044";
        boolean permitido = false;
        for(int i=0; i<arrayAccesos.size();i++){
            if(codOpcion.equals(arrayAccesos.get(i))){
                permitido = true;
            }
        }
        if(permitido || idusuario.equals("01")){
            Toast.makeText(this,"Tiene Acceso", Toast.LENGTH_SHORT).show();
            Intent ven=new Intent(this,Solicitud_DiferidoRepetido_Eliminar.class);
            startActivity(ven);
        }
        else{
            Toast.makeText(this,"No Tiene Acceso", Toast.LENGTH_SHORT).show();
        }

    }

    public void solicitudDiferidoRepetidoActualizar(View v){
        String codOpcion = "042";
        boolean permitido = false;
        for(int i=0; i<arrayAccesos.size();i++){
            if(codOpcion.equals(arrayAccesos.get(i))){
                permitido = true;
            }
        }
        if(permitido || idusuario.equals("01")){
            Toast.makeText(this,"Tiene Acceso", Toast.LENGTH_SHORT).show();
            Intent ven=new Intent(this,Solicitud_DiferidoRepetido_Actualizar.class);
            startActivity(ven);
        }
        else{
            Toast.makeText(this,"No Tiene Acceso", Toast.LENGTH_SHORT).show();
        }

    }

    public void solicitudDiferidoRepetidoConsultar(View v){
        String codOpcion = "043";
        boolean permitido = false;
        for(int i=0; i<arrayAccesos.size();i++){
            if(codOpcion.equals(arrayAccesos.get(i))){
                permitido = true;
            }
        }
        if(permitido || idusuario.equals("01")){
            Toast.makeText(this,"Tiene Acceso", Toast.LENGTH_SHORT).show();

            Intent ven=new Intent(this,Solicitud_DiferidoRepetido_Consultar.class);
            startActivity(ven);
        }
        else{
            Toast.makeText(this,"No Tiene Acceso", Toast.LENGTH_SHORT).show();
        }

    }
}
