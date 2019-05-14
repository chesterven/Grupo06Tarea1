//Autor: Roberto Eliezer Ventura Dominguez
//Carnet:VD16006
//Grupo de trabajo: 06

package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Solicitud_DiferidoRepetido_Eliminar extends AppCompatActivity {
    DBHelperInicial DBHelper;
    EditText carnet;
    Spinner evaluaciones;
    ArrayList<String> arrayEvaluaciones = new ArrayList<String>();
    Cursor idEvaluacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud__diferido_repetido__eliminar);
        carnet = (EditText) findViewById(R.id.carnetEliminarSoliDyR);
        evaluaciones = (Spinner) findViewById(R.id.spinnerResultadosDiferidoRepEliminar);
        arrayEvaluaciones.add("Seleccione evaluacion");
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayEvaluaciones);
        evaluaciones.setAdapter(adaptador);
    }

    public void consultarEvaluacionesRepDif(View v){
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        if(carnet.getText().toString().equals("")){
            Toast.makeText(this,"Ingrese el carnet",Toast.LENGTH_SHORT).show();
        }
        else{
            idEvaluacion = DBHelper.consultarSolicitudesDiferidoRepetido(carnet.getText().toString());
            if(idEvaluacion.moveToFirst()){

                do{
                    if(idEvaluacion.getInt(2)==0) {
                        arrayEvaluaciones.add(DBHelper.consultarEvaluacionesSolicitud(idEvaluacion.getInt(0)));
                    }else{
                        Toast.makeText(this, "La evaluacion ya se aprobo", Toast.LENGTH_SHORT).show();
                    }
                }while(idEvaluacion.moveToNext());
                }
            else{
                Toast.makeText(this,"No hay evaluaciones",Toast.LENGTH_SHORT).show();
            }

        }
        if(arrayEvaluaciones.size()==1){
            Toast.makeText(this, "Todas las evaluaciones han sido aprobadas", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Puede seleccionar una evaluacion", Toast.LENGTH_SHORT).show();
        }
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayEvaluaciones);
        evaluaciones.setAdapter(adaptador);
    }

    public void eliminarSolicitud(View v){
        String eval;
        if(!(carnet.getText().toString().equals("") | evaluaciones.getSelectedItem().toString().equals("Seleccione evaluacion"))){
            DBHelper = new DBHelperInicial(this);
            DBHelper.abrir();

            eval = evaluaciones.getSelectedItem().toString();
            String[] evalPart = eval.split(" ");

            String mensaje = DBHelper.eliminarSolicitudDiferidoRepetido(Integer.valueOf(evalPart[0]),carnet.getText().toString());
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Seleccione una evaluacion o ingrese un carnet para consultar", Toast.LENGTH_SHORT).show();
        }

    }

    public void limpiarTexto(View v){
        carnet.setText("");
    }
}
