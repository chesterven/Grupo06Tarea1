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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_revision__eliminar);
        revisiones = (Spinner) findViewById(R.id.spinnerRevisionesDocenteSegundaCon);
        codDocente = (EditText) findViewById(R.id.idCodigoDocenteConsultarSegundaCon);
        revisionesLista.add("Seleccione su revision");
        ArrayAdapter<CharSequence> adaptadorr = new ArrayAdapter(this, android.R.layout.simple_spinner_item, revisionesLista);
        revisiones.setAdapter(adaptadorr);

    }
    public  void consultarEvaSegundaReviCon (View v)
    {

        String [] parteEvaluacion;
        String idEvaluacion="";
        if(!(codDocente.getText().toString().equals(""))) {
            DBHelper = new DBHelperInicial(this);
            DBHelper.abrir();
            Cursor resul = DBHelper.consultarMateriasDocente(codDocente.getText().toString());
            if(resul.moveToFirst())
            {
                do {
                    evaluaciones = DBHelper.consultarEvaluaciones(resul.getInt(0), resul.getString(1), resul.getInt(2));
                    parteEvaluacion = evaluaciones.split(" ");
                    idEvaluacion=parteEvaluacion[0];
                    revisionesLista.add(DBHelper.consultarSegundaRevisionExiste(idEvaluacion));

                }while(resul.moveToNext());
                Toast.makeText(this,"Revisiones encontrados", Toast.LENGTH_SHORT).show();
            }

           ArrayAdapter<CharSequence> adaptadorr = new ArrayAdapter(this, android.R.layout.simple_spinner_item, revisionesLista);
            revisiones.setAdapter(adaptadorr);

        }
        else
        {
            Toast.makeText(this,"Ingrese datos en los campos", Toast.LENGTH_SHORT).show();
        }
    }
    public void eliminarSegundaRevision(View v)
    {
        if(revisiones.getSelectedItem().toString().equals("Seleccione su revision"))
        {
            Toast.makeText(this,"Debe seleccionar la revision", Toast.LENGTH_SHORT).show();
        }
        else {
            DBHelper = new DBHelperInicial(this);
            DBHelper.abrir();
            String revision = "";
            String mensaje = "";
            revision = revisiones.getSelectedItem().toString();
            String[] revisionParte = revision.split(" ");

            mensaje = DBHelper.eliminarSegundaRevision(Integer.valueOf(revisionParte[0]));
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
            DBHelper.cerrar();
        }
    }
    public void limpiarTexto(View v) {
        codDocente.setText("");


    }
}
