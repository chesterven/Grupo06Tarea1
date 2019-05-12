//Autor: Roberto Eliezer Ventura Dominguez
//Carnet:VD16006
//Grupo de trabajo: 06

package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuVd16006 extends AppCompatActivity {
    String idusuario = "";
    DBHelperInicial DBHelper;
    ArrayList<String> arrayAccesos = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_vd16006);
        Intent recibir = getIntent();
        idusuario = recibir.getStringExtra("idusuario");

        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        arrayAccesos = DBHelper.consultarAccesos(idusuario);
        DBHelper.cerrar();
    }

    public void solicitudDiferidoRepetido(View v){
        String codOpcion = "006";
        boolean permitido = false;
        for(int i=0; i<arrayAccesos.size();i++){
            if(codOpcion.equals(arrayAccesos.get(i))){
                permitido = true;
            }
        }
        if(permitido || idusuario.equals("01")){
            Toast.makeText(this,"Tiene Acceso", Toast.LENGTH_SHORT).show();
            Intent ven=new Intent(this,MenuSolicitudDiferidoRepetido.class);
            startActivity(ven);
        }
        else{
            Toast.makeText(this,"No Tiene Acceso", Toast.LENGTH_SHORT).show();
        }

    }
    public void solicitudEvaluacion(View v){
        String codOpcion = "014";
        boolean permitido = false;
        for(int i=0; i<arrayAccesos.size();i++){
            if(codOpcion.equals(arrayAccesos.get(i))){
                permitido = true;
            }
        }
        if(permitido || idusuario.equals("01")){
            Toast.makeText(this,"Tiene Acceso", Toast.LENGTH_SHORT).show();
            Intent ven=new Intent(this,MenuSolicitudEvaluacion.class);
            startActivity(ven);
        }
        else{
            Toast.makeText(this,"No Tiene Acceso", Toast.LENGTH_SHORT).show();
        }
    }
    public void tipoSolicitud(View v) {
        String codOpcion = "015";
        boolean permitido = false;
        for (int i = 0; i < arrayAccesos.size(); i++) {
            if (codOpcion.equals(arrayAccesos.get(i))) {
                permitido = true;
            }
        }
        if (permitido || idusuario.equals("01")) {
            Toast.makeText(this, "Tiene Acceso", Toast.LENGTH_SHORT).show();
            Intent ven = new Intent(this, MenuTipoSolicitud.class);
            startActivity(ven);
        } else {
            Toast.makeText(this, "No Tiene Acceso", Toast.LENGTH_SHORT).show();
        }
    }
}
