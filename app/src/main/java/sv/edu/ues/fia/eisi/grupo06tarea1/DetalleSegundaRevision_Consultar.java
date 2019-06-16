//Nombre: Maria Abigail Gil Cordova
//Carnet:GC16001
//Grupo de trabajo: 06
package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
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

public class DetalleSegundaRevision_Consultar extends AppCompatActivity {

    DBHelperInicial DBHelper;
    Spinner revisiones;
    EditText carnetCon;
    EditText notaCon;
    EditText codDocente;
    EditText asistenciaCon;
    String siAlumno;
    Boolean existe;
    String idEvaluacion="";
    ArrayList<String> listaRevisiones = new ArrayList<>();
    String resultRevisiones="";
    Cursor evaluaciones;
    DetalleSegundaRevision detalles = new DetalleSegundaRevision();
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_segunda_revision__consultar);
        carnetCon = (EditText) findViewById(R.id.carnetDetalleSegundaCon);
        codDocente = (EditText) findViewById(R.id.codigoDocenteDetalleSegundaCon);
        asistenciaCon = (EditText) findViewById(R.id.asistenciaMostrarSegundaDetalle);
        notaCon= (EditText) findViewById(R.id.notaSegundaRevisionDetalleMostrar);
        revisiones=(Spinner) findViewById(R.id.spinnerResultadosRevisionesEvaluaCon);

    }
    public void consultarReviSegundaReviDetalleCon (View v)
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
    public void consultarDetalleSegundaRevision(View v) {
        if (!(listaRevisiones.size() == 0)) {

            if(revisiones.getSelectedItem().toString().equals("Seleccione su revision")|
                    carnetCon.getText().toString().equals(""))
            {
                Toast.makeText(this, "Debe seleccionar una revision e ingresar su carnet", Toast.LENGTH_SHORT).show();
            }
            else {
                DBHelper = new DBHelperInicial(this);
                DBHelper.abrir();
                String revision = "";
                String mensaje = "";

                revision = revisiones.getSelectedItem().toString();
                String[] revisionParte = revision.split(" ");
                idEvaluacion = DBHelper.consultarSegundaRevisionConId(revisionParte[0]);
             /*idSoli*/   siAlumno = DBHelper.consultarAlumnoSoliSegundaRevisionAntesDetalle(Integer.valueOf(idEvaluacion), carnetCon.getText().toString());
                if(siAlumno.equals(""))
                {
                    Toast.makeText(this, "El estudiante no tiene una solicitud de segunda revision", Toast.LENGTH_SHORT).show();
                }
                else {

                    existe = DBHelper.consultarDetalleSegundaAntesDeAcciones(Integer.valueOf(revisionParte[0]), Integer.valueOf(siAlumno));


                    if (existe == true) {
                        detalles=DBHelper.consultarDetalleSegundaRevision(Integer.valueOf(revisionParte[0]),Integer.valueOf(siAlumno));
                        Toast.makeText(this, "Segunda Revisión encontrada", Toast.LENGTH_SHORT).show();

                        if(detalles.isAsitencia_SegRevision()==true)
                        {
                            asistenciaCon.setText("Asistió");
                        }
                        else
                        {
                            asistenciaCon.setText("No Asistió");
                        }
                       notaCon.setText(String.valueOf(detalles.getNota_SegRevision()));

                    } else {
                        Toast.makeText(this, "No existe ese detalle de segunda revisión", Toast.LENGTH_SHORT).show();
                    }
                    DBHelper.cerrar();
                }
            }
    }
    else
        {
            Toast.makeText(this, "No ha consultado las revisiones", Toast.LENGTH_SHORT).show();
        }

    }

    public void consultarDetalleSegundaRevisionFTP(View v) {
        if (!(listaRevisiones.size() == 0)) {

            if(revisiones.getSelectedItem().toString().equals("Seleccione su revision")|
                    carnetCon.getText().toString().equals(""))
            {
                Toast.makeText(this, "Debe seleccionar una revision e ingresar su carnet", Toast.LENGTH_SHORT).show();
            }
            else {
                DBHelper = new DBHelperInicial(this);
                DBHelper.abrir();
                String revision = "";
                String mensaje = "";

                revision = revisiones.getSelectedItem().toString();
                String[] revisionParte = revision.split(" ");
                idEvaluacion = DBHelper.consultarSegundaRevisionConId(revisionParte[0]);
                /*idSoli*/   siAlumno = DBHelper.consultarAlumnoSoliSegundaRevisionAntesDetalle(Integer.valueOf(idEvaluacion), carnetCon.getText().toString());
                if(siAlumno.equals(""))
                {
                    Toast.makeText(this, "El estudiante no tiene una solicitud de segunda revision", Toast.LENGTH_SHORT).show();
                }
                else {


                        buscarDetalleSegundaRevision("https://eisi.fia.ues.edu.sv/GPO06/Tarea/DetalleSegundaRevision.php?idSegundaRevision="+revisionParte[0]+"&idSoliSegundaRevision="+siAlumno+"",DetalleSegundaRevision_Consultar.this);


                    DBHelper.cerrar();
                }
            }
        }
        else
        {
            Toast.makeText(this, "No ha consultado las revisiones", Toast.LENGTH_SHORT).show();
        }

    }

    public void buscarDetalleSegundaRevision(String URL, Context context) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        notaCon.setText(String.valueOf(jsonObject.getDouble("notaSegundaRevision")));
                        if(jsonObject.getString("asistenciaSegundaRevision").equals("1")){
                            asistenciaCon.setText("Asistio");
                        }else{
                            asistenciaCon.setText("No Asistio");
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

    public void limpiarTexto(View v) {
        carnetCon.setText("");
        asistenciaCon.setText("");
        notaCon.setText("");
        codDocente.setText("");
        listaRevisiones.clear();
        revisiones.setAdapter(null);
    }
}