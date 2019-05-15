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

public class SegundaRevision_Eliminar extends AppCompatActivity {

    DBHelperInicial DBHelper;
    EditText codDocente;
    Spinner revisiones;
    String evaluaciones="";
    ArrayList<String> revisionesLista=new ArrayList<>();
    String resultRevisiones="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_revision__eliminar);
        revisiones = (Spinner) findViewById(R.id.spinnerRevisionesDocenteSegundaCon);
        codDocente = (EditText) findViewById(R.id.idCodigoDocenteConsultarSegundaCon);


    }
    public  void consultarEvaSegundaReviEli (View v)
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

                    }
                    else {

                       revisionesLista.add(resultRevisiones);


                    }

                }while(resul.moveToNext());
                if(revisionesLista.size()==0)
                {
                    Toast.makeText(this,"No hay segundas revisiones para el docente", Toast.LENGTH_SHORT).show();
                }

                revisionesLista.add(0,"Seleccione su revision");
                ArrayAdapter<CharSequence> adaptadorr = new ArrayAdapter(this, android.R.layout.simple_spinner_item, revisionesLista);
                revisiones.setAdapter(adaptadorr);
                Toast.makeText(this, "Revisiones encontrados", Toast.LENGTH_SHORT).show();
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
    public void eliminarSegundaRevision(View v)
    {
        if(!(revisionesLista.size()==0)) {


            if (revisiones.getSelectedItem().toString().equals("Seleccione su revision") )
            {
                Toast.makeText(this, "Debe seleccionar la revision", Toast.LENGTH_SHORT).show();
            } else {
                String revision = "";
                DBHelper = new DBHelperInicial(this);
                DBHelper.abrir();
                String mensaje = "";
                revision = revisiones.getSelectedItem().toString();
                String[] revisionParte = revision.split(" ");

                mensaje = DBHelper.eliminarSegundaRevision(Integer.valueOf(revisionParte[0]));
                Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
                DBHelper.cerrar();
            }
        }
        else {
            Toast.makeText(this, "No ha consultado las revisiones", Toast.LENGTH_SHORT).show();
        }
    }
    public void limpiarTexto(View v) {
        codDocente.setText("");
        revisiones.setAdapter(null);
        revisionesLista.clear();

    }
}
