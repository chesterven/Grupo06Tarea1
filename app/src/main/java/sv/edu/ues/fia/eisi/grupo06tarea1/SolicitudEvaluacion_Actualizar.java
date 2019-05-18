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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SolicitudEvaluacion_Actualizar extends AppCompatActivity {

    DBHelperInicial DBHelper;
    EditText codDocente, nota;
    Spinner evalua, tiposoli,solicitudes;
    ArrayList<String> evaluaciones = new ArrayList<String>();
    ArrayList<String> tipoSolicitud = new ArrayList<String>();
    String resultadosEvaluaciones="";
    String[] evaluacionPart;
    ArrayList<String> solicitudesResultado = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_evaluacion__actualizar);
        codDocente = (EditText) findViewById(R.id.SoliDifyRepEvaluacionDocenteAct);
        evalua = (Spinner) findViewById(R.id.spinnerSolicitudDifRepEvaluacionAct);
        tiposoli = (Spinner) findViewById(R.id.spinnerTipoSolicitudAct);
        solicitudes = (Spinner) findViewById(R.id.spinnerSolicitudDifRepSolicitudesAct);
        nota = (EditText) findViewById(R.id.notaActualizarSolicitudEvaluacion);
        tipoSolicitud.add("Seleccione tipo evaluacion");
        tipoSolicitud.add("1 Repetido");
        tipoSolicitud.add("2 Diferida");
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,tipoSolicitud);
        tiposoli.setAdapter(adaptador);
    }

    public void solicitudEvaluacionBuscar(View v){
        if(!(codDocente.getText().toString().equals("") | tiposoli.getSelectedItem().toString().equals("Seleccione tipo evaluacion"))) {
            DBHelper = new DBHelperInicial(this);
            DBHelper.abrir();
            String tipo = tiposoli.getSelectedItem().toString();
            String[] tipoPart = tipo.split(" ");
            Cursor materiaCiclo = DBHelper.consultarMateriasDocente(codDocente.getText().toString());

            if (materiaCiclo.moveToFirst()) {
                do {
                    resultadosEvaluaciones=DBHelper.consultarEvaluacionDifRepEvaluacion(materiaCiclo.getInt(0), materiaCiclo.getString(1), materiaCiclo.getInt(2),tipoPart[0]);

                    if(resultadosEvaluaciones.equals(""))
                    {

                    }
                    else{

                        evaluaciones.add(resultadosEvaluaciones);

                    }
                } while (materiaCiclo.moveToNext());

                if(evaluaciones.size()==0)
                {
                    Toast.makeText(this, "No hay evaluaciones para ese docente", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    evaluaciones.add(0,"Seleccione su evaluación");
                    ArrayAdapter<CharSequence> adaptadorr = new ArrayAdapter(this, android.R.layout.simple_spinner_item, evaluaciones);
                    evalua.setAdapter(adaptadorr);
                    Toast.makeText(this, "Evaluaciones encontradas", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "No existe el código del docente", Toast.LENGTH_SHORT).show();
            }

        }


        else
        {
            Toast.makeText(this,"Ingrese el código del docente o seleccione tipo solicitud", Toast.LENGTH_SHORT).show();
        }
    }

    public void buscarSolicitudes(View v){
        if(!(evaluaciones.size()==0)){
            if(evalua.getSelectedItem().toString().equals("Seleccione su evaluación")){
                Toast.makeText(this,"Seleccione una evaluacion",Toast.LENGTH_SHORT).show();
            }
            else{
                DBHelper = new DBHelperInicial(this);
                DBHelper.abrir();
                String evaluacion = evalua.getSelectedItem().toString();
                evaluacionPart = evaluacion.split(" ");
                Cursor soli =DBHelper.consultarSolicitudesSoliEva(Integer.valueOf(evaluacionPart[0]));
                if(soli.moveToFirst()){
                    do{
                        solicitudesResultado.add(soli.getInt(0)+" "+soli.getString(1));
                    }while(soli.moveToNext());
                    solicitudesResultado.add(0,"Seleccione solicitud");
                    ArrayAdapter<CharSequence> adaptadorr = new ArrayAdapter(this, android.R.layout.simple_spinner_item, solicitudesResultado);
                    solicitudes.setAdapter(adaptadorr);
                }
                else{
                    Toast.makeText(this, "No hay solicitudes de esa evaluacion", Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            Toast.makeText(this, "Consulte evaluaciones", Toast.LENGTH_SHORT).show();
        }

    }

    public void ActualizarSolicitudEvaluacion(View v){
        if(!(solicitudesResultado.size()==0)) {
            if (solicitudes.getSelectedItem().toString().equals("Seleccione solicitud")) {
                Toast.makeText(this, "Seleccione una solicitud", Toast.LENGTH_SHORT).show();
            } else {
                DBHelper = new DBHelperInicial(this);
                DBHelper.abrir();
                String sol = solicitudes.getSelectedItem().toString();
                String solPart[] = sol.split(" ");
                String Mensaje =DBHelper.actualizarSolicitudEvaluacion(evaluacionPart[0],solPart[0],nota.getText().toString());
                Toast.makeText(this, Mensaje, Toast.LENGTH_SHORT).show();
            }
        }else{
                Toast.makeText(this, "Consulte las solicitudes", Toast.LENGTH_SHORT).show();
            }

    }


    public void limpiarTexto(View v){
        codDocente.setText("");
        nota.setText("");
        solicitudes.setAdapter(null);
        evalua.setAdapter(null);
        evaluaciones.clear();
        solicitudesResultado.clear();
        tiposoli.setSelection(0);
    }
}
