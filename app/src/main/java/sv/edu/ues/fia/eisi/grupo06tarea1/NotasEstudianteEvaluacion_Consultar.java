//CS16008 Castro Sánchez José Andrés
package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NotasEstudianteEvaluacion_Consultar extends AppCompatActivity {

    DBHelperInicial DBHelper;

    EditText evalua;
    EditText carnet;
    EditText nota;

    ControladorServicio controladorServicio;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas_estudiante_evaluacion__consultar);

        evalua= (EditText) findViewById(R.id.EvaluacionesNotaEstudianteEvaluacionConsultar);
        carnet=(EditText) findViewById(R.id.carnetNotaEstudianteEvaluacionConsultar);
        nota= (EditText) findViewById(R.id.mostrarNota);

        DBHelper = new DBHelperInicial(this);
    }

    public void buscarNota(String URL,Context context) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        if(!String.valueOf(jsonObject.getDouble("notaEvaluacion")).equals("")){
                            nota.setText(String.valueOf(jsonObject.getDouble("notaEvaluacion")));
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

    public void consultarNotaFTP(View v){

        nota.setText("");
        if(carnet.getText().toString().toUpperCase().equals("")||evalua.getText().toString().equals("")){
            Toast.makeText(this, "Posee campos en blanco en el formulario", Toast.LENGTH_SHORT).show();
        }
        else{
            DBHelper = new DBHelperInicial(this);
            DBHelper.abrir();
            Cursor msj=DBHelper.consultarNotaEstudianteEvaluacion(carnet.getText().toString().toUpperCase(),evalua.getText().toString());
            if(msj.moveToFirst()){
                controladorServicio = new ControladorServicio();
                buscarNota("https://eisi.fia.ues.edu.sv/GPO06/cs16008/consultarNota.php?idEvaluacion="+evalua.getText().toString()+"&carnet="+carnet.getText().toString().toUpperCase(),this);
            }else{
                Toast.makeText(this, "No hay nota registrada con los párametros ingresados", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void consultarNota(View v){
        nota.setText("");
        if(carnet.getText().toString().toUpperCase().equals("")||evalua.getText().toString().equals("")){
            Toast.makeText(this, "Posee campos en blanco en el formulario", Toast.LENGTH_SHORT).show();
        }
        else{
            nota.setText("");
            DBHelper.abrir();
            Cursor msj=DBHelper.consultarNotaEstudianteEvaluacion(carnet.getText().toString().toUpperCase(),evalua.getText().toString());
            if(msj.moveToFirst()){
                nota.setText(String.valueOf(msj.getFloat(2)));
            }
            else {
                Toast.makeText(this, "No hay nota registrada con los párametros ingresados", Toast.LENGTH_SHORT).show();
            }
            DBHelper.cerrar();
        }
    }

    public void limpiarTexto(View v){
        carnet.setText("");
        evalua.setText("");
        nota.setText("");

    }
}
