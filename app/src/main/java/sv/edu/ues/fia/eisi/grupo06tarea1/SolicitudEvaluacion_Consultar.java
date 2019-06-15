//Autor: Roberto Eliezer Ventura Dominguez
//Carnet:VD16006
//Grupo de trabajo: 06
package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class SolicitudEvaluacion_Consultar extends AppCompatActivity {

    DBHelperInicial DBHelper;
    EditText codDocente,carnet;
    Spinner evalua, tiposoli,solicitudes;
    ArrayList<String> evaluaciones = new ArrayList<String>();
    ArrayList<String> tipoSolicitud = new ArrayList<String>();
    String resultadosEvaluaciones="";
    String[] evaluacionPart;
    ArrayList<String> solicitudesResultado = new ArrayList<String>();
    TextView nota;
    Cursor soliEvaluacion;
    ControladorServicio controladorServicio;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_evaluacion__consultar);
        codDocente = (EditText) findViewById(R.id.SoliDifyRepEvaluacionDocenteCon);
        evalua = (Spinner) findViewById(R.id.spinnerSolicitudDifRepEvaluacionCon);
        tiposoli = (Spinner) findViewById(R.id.spinnerTipoSolicitudCon);
        solicitudes = (Spinner) findViewById(R.id.spinnerSolicitudDifRepSolicitudesCon);
        nota = (TextView) findViewById(R.id.notaConsultarSolicitudEvaluacion);
        carnet = (EditText) findViewById(R.id.SolicitudEvaluacionCarnet);
        tipoSolicitud.add("Seleccione tipo evaluacion");
        tipoSolicitud.add("2 Repetido");
        tipoSolicitud.add("3 Diferida");
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
                String tipo = tiposoli.getSelectedItem().toString();
                String[] tipoPart = tipo.split(" ");
                Cursor soli =DBHelper.consultarSolicitudesSoliEva2(Integer.valueOf(evaluacionPart[0]));
                if(soli.moveToFirst()){
                    do{
                        Cursor sl = DBHelper.consultarSolicitudesSoliEva3(soli.getInt(0));
                        if(sl.moveToFirst()){
                            do{
                                solicitudesResultado.add(sl.getInt(0)+" "+sl.getString(1));
                            }while(sl.moveToNext());
                        }
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
    public void consultarNotaSolicitudEvaluacion(View v){

        if(!(solicitudesResultado.size()==0)){
            if(solicitudes.getSelectedItem().toString().equals("Seleccione solicitud")){
                Toast.makeText(this, "Seleccione una solicitud", Toast.LENGTH_SHORT).show();
            }
            else{
                DBHelper = new DBHelperInicial(this);
                DBHelper.abrir();
                String soli = solicitudes.getSelectedItem().toString();
                String[] soliPart = soli.split(" ");
                soliEvaluacion = DBHelper.ConsultarSolicitudEvaluacion(Integer.valueOf(evaluacionPart[0]),Integer.valueOf(soliPart[0]));
                if(soliEvaluacion.moveToFirst()){
                    nota.setText(String.valueOf(soliEvaluacion.getFloat(2)));

                }
                else{
                    Toast.makeText(this, "No tiene registrada ninguna nota", Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            Toast.makeText(this, "Consulte las solicitudes", Toast.LENGTH_SHORT).show();
        }
    }

    public void consultarNotaSoliEvaluacionFtp(View v){
                DBHelper = new DBHelperInicial(this);
                DBHelper.abrir();
                String evaluacion = evalua.getSelectedItem().toString();
                evaluacionPart = evaluacion.split(" ");
                Cursor c = DBHelper.consultarSolicitudesDifRep(Integer.valueOf(evaluacionPart[0]),carnet.getText().toString());
                if(c.moveToFirst()){
                    controladorServicio = new ControladorServicio();
                    buscarNotaSoliEvaluacion("https://eisi.fia.ues.edu.sv/GPO06/Tarea/notaSoliEvaluacion.php?idEvaluacion="+evaluacionPart[0]+"&idSolicitud="+String.valueOf(c.getInt(3)),SolicitudEvaluacion_Consultar.this);


                }else{
                    Toast.makeText(this, "No hay solicitud", Toast.LENGTH_SHORT).show();
                }


    }

   public void buscarNotaSoliEvaluacion(String URL, Context context) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        if(String.valueOf(jsonObject.getDouble("notaSoliEvaluacion")).equals("")){

                        }else {
                            nota.setText(String.valueOf(jsonObject.getDouble("notaSoliEvaluacion")));
                        }
                    } catch (JSONException e) {

                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
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
