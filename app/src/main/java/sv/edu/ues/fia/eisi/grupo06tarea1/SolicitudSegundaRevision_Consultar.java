//Autor: Cordero Hernández, Oscar Emmanuel
//Carnet:CH15013
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
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SolicitudSegundaRevision_Consultar extends AppCompatActivity {
    DBHelperInicial DBHelper;
    EditText etcarnetSoliSegunda,evaSoli,aceptado,motivo;
    Spinner evaluaciones;
    Cursor idEvaluacion,Solicitud;
    ArrayList arrayEva= new ArrayList<String>();
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_segunda_revision__consultar);

        etcarnetSoliSegunda=(EditText)findViewById(R.id.carnetSoliSegundaCon);
        aceptado=(EditText)findViewById(R.id.aceptadaSoliSegunda);
        motivo=(EditText)findViewById(R.id.motivoSoliSegunda);
        evaluaciones=(Spinner)findViewById(R.id.spinnerEvaSoliSegundaConsultar);

    }

    public void consultarEvaluacionesSoliSegunda(View v){

        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        if(etcarnetSoliSegunda.getText().toString().equals("")){
            Toast.makeText(this,"Ingrese el carnet",Toast.LENGTH_SHORT).show();
        }
        else{
            idEvaluacion = DBHelper.consultarSolicitudSegundaEstuiante(etcarnetSoliSegunda.getText().toString());
            if(idEvaluacion.moveToFirst()){
                do{
                    arrayEva.add(DBHelper.consultarEvaluacionesSolicitud(idEvaluacion.getInt(0)));
                }while(idEvaluacion.moveToNext());
                if(!(arrayEva.size()==0)){
                    arrayEva.add(0,"Seleccione evaluacion");
                    ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayEva);
                    evaluaciones.setAdapter(adaptador);
                    Toast.makeText(this,"Seleccione evaluacion",Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this,"No hay revisiones",Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void consultarSolicitudSegunda(View v){
        if(!(arrayEva.size()==0)){

            if(etcarnetSoliSegunda.getText().toString().equals("") | evaluaciones.getSelectedItem().toString().equals("Seleccione evaluacion")){
                Toast.makeText(this,"Seleccione una evaluacion",Toast.LENGTH_SHORT).show();
            }
            else{
                String eval = evaluaciones.getSelectedItem().toString();
                String[] evalPart = eval.split(" ");
                int idEval = Integer.valueOf(evalPart[0]);
                Solicitud = DBHelper.consultarSolicitudSegunda(idEval, etcarnetSoliSegunda.getText().toString());
                if(Solicitud.moveToFirst()) {
                    motivo.setText(Solicitud.getString(0));
                    if (Solicitud.getInt(1) == 0) {
                        aceptado.setText("No aprobada");
                    } else {
                        aceptado.setText("Aprobada");
                    }

                }else {
                    Toast.makeText(this, "No encontrado", Toast.LENGTH_SHORT).show();
                }

            }
        }else{
            Toast.makeText(this,"Tiene que consultar evaluaciones",Toast.LENGTH_SHORT).show();
        }
    }

    public void consultarSolicitudSegundaFTP(View v){
        if(!(arrayEva.size()==0)){

            if(etcarnetSoliSegunda.getText().toString().equals("") | evaluaciones.getSelectedItem().toString().equals("Seleccione evaluacion")){
                Toast.makeText(this,"Seleccione una evaluacion",Toast.LENGTH_SHORT).show();
            }
            else{
                String eval = evaluaciones.getSelectedItem().toString();
                String[] evalPart = eval.split(" ");
                int idEval = Integer.valueOf(evalPart[0]);
                buscarSoliSegundaRevision("https://eisi.fia.ues.edu.sv/GPO06/Tarea/consultarSolicitudSegundaRevision.php?idEvaluacion="+idEval+"&carnet="+etcarnetSoliSegunda.getText().toString(),SolicitudSegundaRevision_Consultar.this);

            }
        }else{
            Toast.makeText(this,"Tiene que consultar evaluaciones",Toast.LENGTH_SHORT).show();
        }
    }

    public void buscarSoliSegundaRevision(String URL, Context context) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        if(!String.valueOf(jsonObject.getInt("idSoliSegundaRevision")).equals("")){
                            motivo.setText(String.valueOf(jsonObject.getString("motivo")));

                            if(jsonObject.getString("aceptadaSegundaRevision").equals("1")){
                                aceptado.setText("Solicitud Aceptada");
                            }else{
                                aceptado.setText("Solicitud Negada");
                            }

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
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

    public void limpiarTextoCon(View v){
        etcarnetSoliSegunda.setText("");
        motivo.setText("");
        aceptado.setText("");
        arrayEva.clear();
        evaluaciones.setAdapter(null);
    }
}
