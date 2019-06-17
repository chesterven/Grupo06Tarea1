//CS16008 Castro Sánchez José Andrés
package sv.edu.ues.fia.eisi.grupo06tarea1;


import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SolicitudPrimerRevision_Consultar extends AppCompatActivity {

    DBHelperInicial DBHelper;
    EditText idSoli;
    EditText idEva;
    EditText carnet;
    CheckBox aprobado;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_primer_revision__consultar);

        idSoli= (EditText) findViewById(R.id.idSolicitudPrimerRevisionConsultar);
        idEva= (EditText) findViewById(R.id.mostrarEvaluacionId);
        carnet= (EditText) findViewById(R.id.mostrarCarnetConsultar);
        aprobado= (CheckBox) findViewById(R.id.checkBoxAprobadoSolicitudPrimerRevision);

        DBHelper=new DBHelperInicial(this);
    }

    public void consultarPrimerRevision(View v){
        carnet.setText("");
        idEva.setText("");
        aprobado.setChecked(false);
        if(idSoli.getText().toString().equals("")){
            Toast.makeText(this, "Debe ingresar un id de solicitud", Toast.LENGTH_SHORT).show();
        }
        else{
            DBHelper.abrir();
            Cursor c=DBHelper.consultarSolicitudPrimerRevision(idSoli.getText().toString());
            if(c.moveToFirst()){
                limpiarTexto();
                carnet.setText(c.getString(2));
                idEva.setText(c.getString(1));
                final boolean b = c.getInt(c.getColumnIndex("aprobado")) != 0;
                if(b==true){
                    aprobado.setChecked(true);
                }
            }
            else{
                Toast.makeText(this, "No se encontró solicitud con el id ingresado", Toast.LENGTH_SHORT).show();
            }
            DBHelper.cerrar();
        }
    }

    public void consultarSoliPrimerRevisionFTP(View v){
       carnet.setText("");
        idEva.setText("");
        aprobado.setChecked(false);
        if(idSoli.getText().toString().equals("")){
            Toast.makeText(this, "Debe ingresar un id de solicitud", Toast.LENGTH_SHORT).show();
        }
        else{
            DBHelper.abrir();
            buscarSoliPrimeraRevision("https://eisi.fia.ues.edu.sv/GPO06/Tarea/consultarSolicitudPrimeraRevision.php?idSoliPrimerRevision="+idSoli.getText().toString()+"",SolicitudPrimerRevision_Consultar.this);
            DBHelper.cerrar();
        }
    }

    public void buscarSoliPrimeraRevision(String URL, Context context) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        idEva.setText(String.valueOf(jsonObject.getInt("idEvaluacion")));
                        carnet.setText(String.valueOf(jsonObject.getString("carnet")));
                        if(jsonObject.getString("aprobado").equals("1")){
                            aprobado.setChecked(true);
                        }else{
                            aprobado.setChecked(false);

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
        idSoli.setText("");
        carnet.setText("");
        idEva.setText("");

        aprobado.setChecked(false);
    }

    public void limpiarTexto(){
        carnet.setText("");
        idEva.setText("");
        aprobado.setChecked(false);
    }
}
