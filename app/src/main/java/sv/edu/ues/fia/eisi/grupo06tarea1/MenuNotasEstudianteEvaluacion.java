//CS16008 Castro Sánchez José Andrés
package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuNotasEstudianteEvaluacion extends AppCompatActivity {

    String idusuario = "";
    DBHelperInicial DBHelper;
    ArrayList<String> arrayAccesos = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_notas_estudiante_evaluacion);
        Intent recibir = getIntent();//Recibo el string de idusuario del menu de mi carnet
        idusuario = recibir.getStringExtra("idusuario");

        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        arrayAccesos = DBHelper.consultarAccesos(idusuario);
        DBHelper.cerrar();
    }

    public void insertarNotaEstudianteEvaluacion(View v){
        String codOpcion = "025";
        boolean permitido = false;
        for(int i=0; i<arrayAccesos.size();i++){
            if(codOpcion.equals(arrayAccesos.get(i))){
                permitido = true;
            }
        }
        if(permitido || idusuario.equals("01")){
            Toast.makeText(this,"Tiene Acceso", Toast.LENGTH_SHORT).show();
            Intent ven=new Intent(this,NotasEstudianteEvaluacion_Insertar.class);
            startActivity(ven);
        }
        else{
            Toast.makeText(this,"No Tiene Acceso", Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminarNotaEstudianteEvaluacion(View v){
        String codOpcion = "026";
        boolean permitido = false;
        for(int i=0; i<arrayAccesos.size();i++){
            if(codOpcion.equals(arrayAccesos.get(i))){
                permitido = true;
            }
        }
        if(permitido || idusuario.equals("01")){
            Toast.makeText(this,"Tiene Acceso", Toast.LENGTH_SHORT).show();
            Intent ven=new Intent(this,NotasEstudianteEvaluacion_Eliminar.class);
            startActivity(ven);
        }
        else{
            Toast.makeText(this,"No Tiene Acceso", Toast.LENGTH_SHORT).show();
        }
    }

    public void consultarNotaEstudianteEvaluacion(View v){
        String codOpcion = "024";
        boolean permitido = false;
        for(int i=0; i<arrayAccesos.size();i++){
            if(codOpcion.equals(arrayAccesos.get(i))){
                permitido = true;
            }
        }
        if(permitido || idusuario.equals("01")){
            Toast.makeText(this,"Tiene Acceso", Toast.LENGTH_SHORT).show();
            Intent ven=new Intent(this,NotasEstudianteEvaluacion_Consultar.class);
            startActivity(ven);
        }
        else{
            Toast.makeText(this,"No Tiene Acceso", Toast.LENGTH_SHORT).show();
        }
    }

    public void actualizarNotaEstudianteEvaluacion(View v){
        String codOpcion = "027";
        boolean permitido = false;
        for(int i=0; i<arrayAccesos.size();i++){
            if(codOpcion.equals(arrayAccesos.get(i))){
                permitido = true;
            }
        }
        if(permitido || idusuario.equals("01")){
            Toast.makeText(this,"Tiene Acceso", Toast.LENGTH_SHORT).show();
            Intent ven=new Intent(this,NotasEstudianteEvaluacion_Actualizar.class);
            startActivity(ven);
        }
        else{
            Toast.makeText(this,"No Tiene Acceso", Toast.LENGTH_SHORT).show();
        }
    }
}
