//Autor: Maria Abigail Gil Cordova
//Carnet:GC16001
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

public class SegundaRevision_Consultar extends AppCompatActivity {

    DBHelperInicial DBHelper;
    EditText codDocente;
    Spinner revisiones;
    EditText local;
    EditText fecha;
    EditText descripcion;
    String evaluaciones="";
    ArrayList<String> listaRevisiones = new ArrayList<>();
    String resultRevisiones="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_revision__consultar);
        codDocente = (EditText)findViewById(R.id.idCodigoDocenteConsultarSegundaCon);
        local=(EditText) findViewById(R.id.localMostrarSegundaRevision);
        fecha=(EditText)findViewById(R.id.fechaMostrarSegundaRevision);
        descripcion=(EditText)findViewById(R.id.descripcionMostrarSegundaRevision);
        revisiones = (Spinner)findViewById(R.id.spinnerRevisionesDocenteSegundaCon);
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        listaRevisiones.add("Seleccione su evaluación");
        ArrayAdapter<CharSequence> adaptadorr = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaRevisiones);
        revisiones.setAdapter(adaptadorr);
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

                        parteEvaluacion = evaluaciones.split(" ");
                        idEvaluacion = parteEvaluacion[0];

                        resultRevisiones=(DBHelper.consultarSegundaRevisionExiste(idEvaluacion));
                        if(resultRevisiones.equals(""))
                        {
                            Toast.makeText(this,"No hay segundas revisiones para el docente", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            listaRevisiones.add(resultRevisiones);

                            Toast.makeText(this, "Revisiones encontrados", Toast.LENGTH_SHORT).show();
                        }

                }while(resul.moveToNext());

            }
            else{
                Toast.makeText(this,"No existe el código de ese docente", Toast.LENGTH_SHORT).show();
            }


            ArrayAdapter<CharSequence> adaptadorr = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaRevisiones);
            revisiones.setAdapter(adaptadorr);

        }
        else
        {
            Toast.makeText(this,"Ingrese datos en los campos", Toast.LENGTH_SHORT).show();
        }

    }
    public void consultarSegundaRevision(View view)
    {
        if(revisiones.getSelectedItem().toString().equals("Seleccione su evaluación"))
        {
            Toast.makeText(this,"Debe seleccionar una evaluacion", Toast.LENGTH_SHORT).show();
        }
        else
        {
            DBHelper = new DBHelperInicial(this);
            DBHelper.abrir();
            String revision = "";
            String mensaje = "";
            revision = revisiones.getSelectedItem().toString();
            String[] revisionParte = revision.split(" ");
            SegundaRevision segunda =DBHelper.consultarSegundaRevision(Integer.valueOf(revisionParte[0]));

            String nombreLocal=DBHelper.obtenerLocal(segunda.getIdLocal());

            fecha.setText(segunda.getFechaSegundaRevision());
            local.setText(nombreLocal);
            descripcion.setText(segunda.getDescripcion());


            if(segunda ==null)
            {
                Toast.makeText(this,"No existe la segunda revision", Toast.LENGTH_SHORT).show();
            }
            DBHelper.cerrar();
        }
    }
    public void limpiarTexto(View v)
    {

        codDocente.setText("");
        fecha.setText("");
        descripcion.setText("");
        local.setText("");

    }



}
