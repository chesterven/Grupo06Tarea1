//Autor: Christian Ariel Zelaya Tejada
//Carnet:ZT12002
//Grupo de trabajo: 06
package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;

public class MenuZT12002 extends AppCompatActivity {
    String idusuario = "";
    DBHelperInicial DBHelper;
    ArrayList<String> arrayAccesos = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_zt12002);
        Intent recibir = getIntent();
        idusuario = recibir.getStringExtra("idusuario");
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        arrayAccesos = DBHelper.consultarAccesos(idusuario);
        DBHelper.cerrar();
    }

    public void Parametros(View v){
        String codOpcion = "001";
        boolean permitido = false;
        for(int i=0; i<arrayAccesos.size();i++){
            if(codOpcion.equals(arrayAccesos.get(i))){
                permitido=true;
            }
        }
        if(permitido || idusuario.equals("01")){
            Toast.makeText( this,"Tiene Acceso",Toast.LENGTH_SHORT).show();
            Intent ven=new Intent(this,MenuParametros.class);
            startActivity(ven);
        }
        else{
            Toast.makeText(this,"No Tiene Acceso", Toast.LENGTH_SHORT).show();
        }
    }
    public void Evaluaciones(View v){
        String codOpcion = "002";
        boolean permitido = false;
        for(int i=0; i<arrayAccesos.size();i++){
            if(codOpcion.equals(arrayAccesos.get(i))){
                permitido=true;
            }
        }
        if(permitido || idusuario.equals("01")){
            Toast.makeText( this,"Tiene Acceso",Toast.LENGTH_SHORT).show();
            Intent ven=new Intent(this,MenuEvaluaciones.class);
            startActivity(ven);
        }
        else{
            Toast.makeText(this,"No Tiene Acceso", Toast.LENGTH_SHORT).show();
        }
    }
    public void MateriaCiclo(View v){
        String codOpcion = "013";
        boolean permitido = false;
        for(int i=0; i<arrayAccesos.size();i++){
            if(codOpcion.equals(arrayAccesos.get(i))){
                permitido=true;
            }
        }
        if(permitido || idusuario.equals("01")){
            Toast.makeText( this,"Tiene Acceso",Toast.LENGTH_SHORT).show();
            Intent ven=new Intent(this,MenuMateriaCiclo.class);
            startActivity(ven);
        }
        else{
            Toast.makeText(this,"No Tiene Acceso", Toast.LENGTH_SHORT).show();
        }
    }
    public void AlumnoDocente(View v){
        String codOpcion = "013";
        boolean permitido = false;
        for(int i=0; i<arrayAccesos.size();i++){
            if(codOpcion.equals(arrayAccesos.get(i))){
                permitido=true;
            }
        }
        if(permitido || idusuario.equals("01")){
            Toast.makeText( this,"Tiene Acceso",Toast.LENGTH_SHORT).show();
            Intent ven=new Intent(this,AlumnosPorDocente.class);
            startActivity(ven);
        }
        else{
            Toast.makeText(this,"No Tiene Acceso", Toast.LENGTH_SHORT).show();
        }
    }
}
