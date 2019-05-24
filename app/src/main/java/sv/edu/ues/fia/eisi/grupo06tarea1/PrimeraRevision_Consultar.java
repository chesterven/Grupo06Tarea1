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

public class PrimeraRevision_Consultar extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primera_revision__consultar);

        codDocente = (EditText)findViewById(R.id.idCodigoDocenteConsultarPrimera);
        local=(EditText) findViewById(R.id.localMostrarPrimeraRevision);
        fecha=(EditText)findViewById(R.id.fechaMostrarPrimeraRevision);
        hora = (EditText) findViewById(R.id.horaMostrarPrimeraRevision);
        descripcion=(EditText)findViewById(R.id.descMostrarPrimeraRevision);
        revisiones = (Spinner)findViewById(R.id.spinnerRevisionesDocentePrimeraCon);
    }

    public void consultarEvaPrimeraRevisionCon(View v) {
        String[] parteEvaluacion;
        String idEvaluacion = "";
        if (!(codDocente.getText().toString().equals(""))) {
            DBHelper = new DBHelperInicial(this);
            DBHelper.abrir();
            Cursor resul = DBHelper.consultarMateriasDocente(codDocente.getText().toString());
            if (resul.moveToFirst()) {
                do {
                    evaluaciones = DBHelper.consultarEvaluaciones(resul.getInt(0), resul.getString(1), resul.getInt(2));
                    if (evaluaciones.moveToFirst()) {
                        do {
                            resultRevisiones = (DBHelper.consultarPrimeraRevisionExiste(String.valueOf(evaluaciones.getInt(0))));
                            if (resultRevisiones.equals("")) {

                            } else {

                                listaRevisiones.add(resultRevisiones);


                            }
                        }
                        while (evaluaciones.moveToNext());
                    }


                } while (resul.moveToNext());
                if (listaRevisiones.size() == 0) {
                    Toast.makeText(this, "No hay primeras revisiones para el docente", Toast.LENGTH_SHORT).show();
                } else {
                    listaRevisiones.add(0, "Seleccione su revision");
                    ArrayAdapter<CharSequence> adaptadorr = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaRevisiones);
                    revisiones.setAdapter(adaptadorr);
                    Toast.makeText(this, "Revisiones encontrados", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "No existe el código de ese docente", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Ingrese el código del docente", Toast.LENGTH_SHORT).show();
        }

    }

    public void consultarPrimeraRevision(View view)
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
                PrimeraRevision primera = DBHelper.consultarPrimeraRevision(Integer.valueOf(revisionParte[0]));

                String nombreLocal = DBHelper.obtenerLocal(primera.getIdLocal());
                hora.setText(primera.getHora());
                fecha.setText(primera.getFechaPrimeraRevision());
                local.setText(nombreLocal);
                descripcion.setText(primera.getDescripcion());


                if (primera == null) {
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
