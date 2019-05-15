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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class SegundaRevision_Actualizar extends AppCompatActivity {
    DBHelperInicial DBHelper;
    EditText codDocente;
    EditText fecha;
    EditText descripcion;
    Spinner locales;
    Spinner revisiones;
    String evaluaciones="";
    ArrayList<String> listaLocales=new ArrayList<>();
    ArrayList<String> listaRevisiones = new ArrayList<>();
    String resultRevisiones="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_revision__actualizar);
        codDocente=(EditText)findViewById(R.id.idCodigoDocenteActualizarSegunda);
        fecha=(EditText) findViewById(R.id.fechaSegundaRevisionAct);
        descripcion= (EditText) findViewById(R.id.descripcionSegundaRevisionAct);
        locales=(Spinner) findViewById(R.id.spinnerLocalesAct);
        revisiones = (Spinner) findViewById(R.id.spinnerRevisionesDocenteSegundaAct);
        listaLocales.add("Seleccione el local");
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        Cursor resultado=DBHelper.consultarLocales();
        if(resultado.moveToFirst())
        {
            do {
                listaLocales.add(String.valueOf(resultado.getInt(0)) + " " + resultado.getString(1));
            }while(resultado.moveToNext());
        }
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaLocales);
        locales.setAdapter(adaptador);


    }
    public void consultarEvaSegundaReviAct(View v) {

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
                        listaRevisiones.add("Seleccione su revision");
                        ArrayAdapter<CharSequence> adaptadorr = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaRevisiones);
                        revisiones.setAdapter(adaptadorr);
                        listaRevisiones.add(resultRevisiones);

                        Toast.makeText(this, "Revisiones encontrados", Toast.LENGTH_SHORT).show();
                    }

                }while(resul.moveToNext());

            }

            else{
                Toast.makeText(this,"No existe el código de ese docente", Toast.LENGTH_SHORT).show();
            }

        }
        else
        {
            Toast.makeText(this,"Ingrese datos en los campos", Toast.LENGTH_SHORT).show();
        }


    }
    public void actualizarSegundaRevision(View v)
    {
        if(revisiones.getSelectedItem().toString().equals("Seleccione su revisión")|
                locales.getSelectedItem().toString().equals("Seleccione el local")|
                fecha.getText().toString().equals("") |
                descripcion.getText().toString().equals(""))
        {
            Toast.makeText(this,"Los campos son obligatorios", Toast.LENGTH_SHORT).show();
        }
        else {
            DBHelper = new DBHelperInicial(this);
            DBHelper.abrir();
            SegundaRevision segunda = new SegundaRevision();
            String mensaje = "";
            String revision = "";
            String local = "";

            revision = revisiones.getSelectedItem().toString();
            String[] revisionParte = revision.split(" ");

            local = locales.getSelectedItem().toString();
            String[] localParte = local.split(" ");

            segunda.setIdLocal(Integer.valueOf(localParte[0]));

            segunda.setId_Segunda_Revision(Integer.valueOf(revisionParte[0]));
            segunda.setDescripcion(descripcion.getText().toString());
            segunda.setFechaSegundaRevision(fecha.getText().toString());

            mensaje = DBHelper.actualizarSegundaRevision(segunda);
            Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarTexto(View v)
    {

        codDocente.setText("");
        fecha.setText("");
        descripcion.setText("");

    }
}
