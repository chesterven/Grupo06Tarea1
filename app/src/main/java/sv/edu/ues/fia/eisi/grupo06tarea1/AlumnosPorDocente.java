package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.ArrayAdapter;
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

public class AlumnosPorDocente extends AppCompatActivity {
    DBHelperInicial DBHelper;
    Spinner docenteSpinner;
    Spinner alumnoSpinner;
    ArrayList<String> docentes = new ArrayList<>();
    ArrayList<String> alumnos = new ArrayList<>();


    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnos_por_docente);
        docenteSpinner = (Spinner) findViewById(R.id.spinner);
        alumnoSpinner = (Spinner) findViewById(R.id.spinnerAlumno);
        docentes.add("Seleccione un docente");
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        Cursor resultado4 = DBHelper.consultarDoc();
        if(resultado4.moveToFirst()){
            do{
                docentes.add(resultado4.getString(0) + " " + resultado4.getString(1));
            }while (resultado4.moveToNext());
        }
        ArrayAdapter<CharSequence> adaptador4 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,docentes);
        docenteSpinner.setAdapter(adaptador4);
    }

    public void consultarAlumnoDocenteFTP(View v){
        if(!(docentes.size()==0)) {
            if (docenteSpinner.getSelectedItem().toString().equals("Seleccione un docente")) {
                Toast.makeText(this, "Debe seleccionar un docente", Toast.LENGTH_SHORT).show();

            } else {
                String revision = "";
                revision = docenteSpinner.getSelectedItem().toString();

                String[] mateparte = revision.split(" ");

                buscarDatosSolicitudes("https://eisi.fia.ues.edu.sv/GPO06/Tarea/consultaAlumnoDocente.php?codDocente="+mateparte[0],this);

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
                        if(!String.valueOf(jsonObject.getString("codMateria")).equals("")){
                            String codDocente = String.valueOf(jsonObject.getString("codMateria"));
                            DBHelper.abrir();
                            Cursor resultado2 = DBHelper.consultarAlumnoMateria(codDocente);
                            if(resultado2.moveToFirst()){
                                do{
                                    Log.d(resultado2.getString(0), "onResponse: ");
                                    Cursor resultado3 = DBHelper.consultarAlumnos(resultado2.getString(0));
                                    if(resultado3.moveToFirst()){
                                        do{
                                            alumnos.add(resultado3.getString(0)+ " " +resultado3.getString(1)+ " " +resultado3.getString(2));
                                        }while (resultado3.moveToNext());
                                        fillspinner(alumnos);
                                    }


                                }while (resultado2.moveToNext());
                            }



                            /*local.setText(nombreLocal);
                            hora.setText(String.valueOf(jsonObject.getString("horaSegundaRevision")));
                            fecha.setText(String.valueOf(jsonObject.getString("fechaSegundaRevision")));
                            descripcion.setText(String.valueOf(jsonObject.getString("descripcionSegundaRevision")));*/


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


    public void fillspinner(ArrayList al){

        ArrayAdapter<CharSequence> adaptador3 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,al);
        alumnoSpinner.setAdapter(adaptador3);
    }
    public void limpiartTextoa(View v){
        finish();
        startActivity(getIntent());

    }
}
