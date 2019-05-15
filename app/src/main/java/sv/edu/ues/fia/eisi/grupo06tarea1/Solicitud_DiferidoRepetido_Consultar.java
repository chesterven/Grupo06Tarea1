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

public class Solicitud_DiferidoRepetido_Consultar extends AppCompatActivity {
    DBHelperInicial DBHelper;
    EditText carnetSoli,evaluacionSoli, tipoSoli,aceptado, motivo;
    Spinner evaluaciones;
    Cursor idEvaluacion,Solicitud;
    ArrayList<String> arrayEvaluaciones = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud__diferido_repetido__consultar);
        carnetSoli = (EditText) findViewById(R.id.carnetSoliDyRCon);
        tipoSoli = (EditText) findViewById(R.id.tipoSoliDyRMostrar);
        motivo = (EditText) findViewById(R.id.MotivoSoliDyRMostrar) ;
        aceptado = (EditText) findViewById(R.id.AceptadaSoliDyRMostrar); //Para agregar un text dependiendo del estado.
        evaluaciones = (Spinner) findViewById(R.id.spinnerResultadosDiferidoRepConsultar);
    }

    public void consultarEvaluacionesRepDif(View v){

        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        if(carnetSoli.getText().toString().equals("")){
            Toast.makeText(this,"Ingrese el carnet",Toast.LENGTH_SHORT).show();
        }
        else{
            idEvaluacion = DBHelper.consultarSolicitudesDiferidoRepetido(carnetSoli.getText().toString());
            if(idEvaluacion.moveToFirst()){
                do{
                    arrayEvaluaciones.add(DBHelper.consultarEvaluacionesSolicitud(idEvaluacion.getInt(0)));
                }while(idEvaluacion.moveToNext());
                if(!(arrayEvaluaciones.size()==0)){
                    arrayEvaluaciones.add(0,"Seleccione evaluacion");
                    ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayEvaluaciones);
                    evaluaciones.setAdapter(adaptador);
                    Toast.makeText(this,"Seleccione evaluacion",Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this,"No hay revisiones",Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void consultarTipoSolicitud(View v){
        if(!(arrayEvaluaciones.size()==0)){

            if(carnetSoli.getText().toString().equals("") | evaluaciones.getSelectedItem().toString().equals("Seleccione evaluacion")){
                Toast.makeText(this,"Seleccione una evaluacion",Toast.LENGTH_SHORT).show();
            }
            else{
                String eval = evaluaciones.getSelectedItem().toString();
                String[] evalPart = eval.split(" ");
                int idEval = Integer.valueOf(evalPart[0]);
                Solicitud = DBHelper.consultarSolicitudesDifRep(idEval, carnetSoli.getText().toString());
                if(Solicitud.moveToFirst()) {
                    motivo.setText(Solicitud.getString(0));
                    if (Solicitud.getInt(1) == 0) {
                        aceptado.setText("No aprobada");
                    } else {
                        aceptado.setText("Aprobada");
                    }
                    if(Solicitud.getInt(2)==1){
                        tipoSoli.setText("Repetido");
                    }else{
                        tipoSoli.setText("Diferido");
                    }

                }else{
                    Toast.makeText(this,"No encontrado",Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            Toast.makeText(this,"Tiene que consultar evaluaciones",Toast.LENGTH_SHORT).show();
        }


    }


    public void limpiarTexto(View v){
        carnetSoli.setText("");
        tipoSoli.setText("");
        aceptado.setText("");
        motivo.setText("");
        arrayEvaluaciones.clear();
        evaluaciones.setAdapter(null);

    }
}
