//Autor: Maria Abigail Gil Cordova
//Carnet:GC16001
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

public class SegundaRevision_Consultar extends AppCompatActivity {

    DBHelperInicial DBHelper;
    EditText codDocente;
    Spinner revisiones;
    EditText local;
    EditText fecha;
    EditText descripcion;
    EditText hora;
    Cursor evaluaciones;
    ArrayList<String> listaRevisiones = new ArrayList<>();
    String resultRevisiones="";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_revision__consultar);
        codDocente = (EditText)findViewById(R.id.idCodigoDocenteConsultarSegundaCon);
        local=(EditText) findViewById(R.id.localMostrarSegundaRevision);
        fecha=(EditText)findViewById(R.id.fechaMostrarSegundaRevision);
        hora = (EditText) findViewById(R.id.horaMostrarSegundaRevision);
        descripcion=(EditText)findViewById(R.id.descripcionMostrarSegundaRevision);
        revisiones = (Spinner)findViewById(R.id.spinnerRevisionesDocenteSegundaCon);

    }
   public void consultarEvaSegundaReviCon(View v)
    {
        String [] parteEvaluacion;
        String idEvaluacion="";
        if(!(codDocente.getText().toString().equals(""))  ) {
            DBHelper = new DBHelperInicial(this);
            DBHelper.abrir();
            Cursor resul = DBHelper.consultarMateriasDocente(codDocente.getText().toString());
            if(resul.moveToFirst())
            {
                do {
                    evaluaciones = DBHelper.consultarEvaluaciones(resul.getInt(0), resul.getString(1), resul.getInt(2));
                    if(evaluaciones.moveToFirst())
                    {
                        do{
                            resultRevisiones=(DBHelper.consultarSegundaRevisionExiste(String.valueOf(evaluaciones.getInt(0))));
                            if(resultRevisiones.equals(""))
                            {

                            }
                            else {

                                listaRevisiones.add(resultRevisiones);


                            }}
                        while (evaluaciones.moveToNext());
                    }


                }while(resul.moveToNext());
                if(listaRevisiones.size()==0)
                {
                    Toast.makeText(this,"No hay segundas revisiones para el docente", Toast.LENGTH_SHORT).show();
                }
                else{
                    listaRevisiones.add(0,"Seleccione su revision");
                    ArrayAdapter<CharSequence> adaptadorr = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaRevisiones);
                    revisiones.setAdapter(adaptadorr);
                    Toast.makeText(this, "Revisiones encontrados", Toast.LENGTH_SHORT).show();}
            }

            else{
                Toast.makeText(this,"No existe el código de ese docente", Toast.LENGTH_SHORT).show();
            }

        }
        else
        {
            Toast.makeText(this,"Ingrese el código del docente", Toast.LENGTH_SHORT).show();
        }
    }
    //PARTE DEL FTP
    public void consultarEvaSegundaReviConFTP(View v)
    {
        listaRevisiones.clear();
        if (!(codDocente.getText().toString().equals(""))) {
            DBHelper = new DBHelperInicial(this);
            DBHelper.abrir();

            Cursor resul = DBHelper.consultarMateriasDocente(codDocente.getText().toString().toUpperCase());
            if (resul.moveToFirst()) {
                do {
                    evaluaciones = DBHelper.consultarEvaluaciones(resul.getInt(0), resul.getString(1), resul.getInt(2));
                    if (evaluaciones.moveToFirst()) {
                        listaRevisiones.add(0, "Seleccione su revision");
                        do {
                            buscarListaSolicitudes("https://eisi.fia.ues.edu.sv/GPO06/Tarea/consultarSegundaRevision.php?idEvaluacion="+String.valueOf(evaluaciones.getInt(0)),this);
                        }
                        while (evaluaciones.moveToNext());
                    }
                } while (resul.moveToNext());
            }
            if (listaRevisiones.size() == 0) {
                Toast.makeText(this, "No hay segundas revisiones para el docente", Toast.LENGTH_SHORT).show();
            } else {
                ArrayAdapter<CharSequence> adaptadorr = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaRevisiones);
                revisiones.setAdapter(adaptadorr);
                Toast.makeText(this, "Revisiones encontrados", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Ingrese el código del docente", Toast.LENGTH_SHORT).show();
        }
    }
    public void buscarListaSolicitudes(String URL, Context context) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        if(!String.valueOf(jsonObject.getInt("idSegundaRevision")).equals("")){
                            listaRevisiones.add(String.valueOf(jsonObject.getInt("idSegundaRevision")));
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

    public void consultarDatosSegundaRevisionFTP(View v){
        if(!(listaRevisiones.size()==0)) {
            if (revisiones.getSelectedItem().toString().equals("Seleccione su revisión")) {
                Toast.makeText(this, "Debe seleccionar una revisión", Toast.LENGTH_SHORT).show();

            } else {
                String revision = "";
                revision = revisiones.getSelectedItem().toString();
                buscarDatosSolicitudes("https://eisi.fia.ues.edu.sv/GPO06/Tarea/consultarDatosSegundaRevision.php?idSegundaRevision="+revision,this);
            }
        }
        else {
            Toast.makeText(this, "No ha consultado las revisiones", Toast.LENGTH_SHORT).show();
        }
    }

    public void buscarDatosSolicitudes(String URL, Context context) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        if(!String.valueOf(jsonObject.getInt("idSegundaRevision")).equals("")){
                            String nombreLocal = DBHelper.obtenerLocal(jsonObject.getInt("idLocal"));
                            local.setText(nombreLocal);
                            hora.setText(String.valueOf(jsonObject.getString("horaSegundaRevision")));
                            fecha.setText(String.valueOf(jsonObject.getString("fechaSegundaRevision")));
                            descripcion.setText(String.valueOf(jsonObject.getString("descripcionSegundaRevision")));
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

    //FIN DEL FTP
    public void consultarSegundaRevision(View view)
    {
        if(!(listaRevisiones.size()==0)) {

            if (revisiones.getSelectedItem().toString().equals("Seleccione su revisión")) {
                Toast.makeText(this, "Debe seleccionar una revisión", Toast.LENGTH_SHORT).show();

            } else {
                DBHelper = new DBHelperInicial(this);
                DBHelper.abrir();
                String revision = "";
                String mensaje = "";
                revision = revisiones.getSelectedItem().toString();
                String[] revisionParte = revision.split(" ");
                SegundaRevision segunda = DBHelper.consultarSegundaRevision(Integer.valueOf(revisionParte[0]));

                String nombreLocal = DBHelper.obtenerLocal(segunda.getIdLocal());
                hora.setText(segunda.getHora());
                fecha.setText(segunda.getFechaSegundaRevision());
                local.setText(nombreLocal);
                descripcion.setText(segunda.getDescripcion());


                if (segunda == null) {
                    Toast.makeText(this, "No existe la segunda revision", Toast.LENGTH_SHORT).show();
                }
                DBHelper.cerrar();
            }
        }
        else {
            Toast.makeText(this, "No ha consultado las revisiones", Toast.LENGTH_SHORT).show();
        }

    }
    public void limpiarTexto(View v)
    {

        codDocente.setText("");
        fecha.setText("");
        descripcion.setText("");
        local.setText("");
        hora.setText("");
        listaRevisiones.clear();
        revisiones.setAdapter(null);

    }



}
