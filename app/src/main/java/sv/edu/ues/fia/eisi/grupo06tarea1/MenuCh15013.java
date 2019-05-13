//Autor: Cordero Hernández, Oscar Emmanuel
//Carnet:CH15013
//Grupo de trabajo: 06

package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuCh15013 extends AppCompatActivity {
    String idusuario = "";
    DBHelperInicial DBHelper;
    ArrayList<String> arrayAccesos = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_ch15013);
        Intent recibir = getIntent();
        idusuario = recibir.getStringExtra("idusuario");
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        arrayAccesos = DBHelper.consultarAccesos(idusuario);
        DBHelper.cerrar();
    }
    public void solicitudSegundaRevision(View v){
        String codOpcion = "005";
        boolean permitido = false;
        for(int i=0; i<arrayAccesos.size();i++){
            if(codOpcion.equals(arrayAccesos.get(i))){
                permitido = true;
            }
        }
        if(permitido || idusuario.equals("01")){
            Toast.makeText(this,"Tiene Acceso", Toast.LENGTH_SHORT).show();
            Intent ven=new Intent(this,MenuSolicitudSegundaRevision.class);
            startActivity(ven);
        }
        else{
            Toast.makeText(this,"No Tiene Acceso", Toast.LENGTH_SHORT).show();
        }

    }
    public void primeraRevision(View v){
        String codOpcion = "007";
        boolean permitido = false;
        for(int i=0; i<arrayAccesos.size();i++){
            if(codOpcion.equals(arrayAccesos.get(i))){
                permitido = true;
            }
        }
        if(permitido || idusuario.equals("01")){
            Toast.makeText(this,"Tiene Acceso", Toast.LENGTH_SHORT).show();
            Intent ven=new Intent(this,MenuPrimeraRevision.class);
            startActivity(ven);
        }
        else{
            Toast.makeText(this,"No Tiene Acceso", Toast.LENGTH_SHORT).show();
        }
    }
    public void detallePrimeraRevisión(View v) {
        String codOpcion = "011";
        boolean permitido = false;
        for (int i = 0; i < arrayAccesos.size(); i++) {
            if (codOpcion.equals(arrayAccesos.get(i))) {
                permitido = true;
            }
        }
        if (permitido || idusuario.equals("01")) {
            Toast.makeText(this, "Tiene Acceso", Toast.LENGTH_SHORT).show();
            Intent ven = new Intent(this, MenuDetallePrimeraRevision.class);
            startActivity(ven);
        } else {
            Toast.makeText(this, "No Tiene Acceso", Toast.LENGTH_SHORT).show();
        }
    }
}