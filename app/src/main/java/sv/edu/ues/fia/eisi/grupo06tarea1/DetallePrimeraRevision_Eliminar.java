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

public class DetallePrimeraRevision_Eliminar extends AppCompatActivity {
    EditText carnet,codDoc;
    Spinner revisiones;

    DBHelperInicial DBHelper;
    String siAlumno;
    String idEvaluacion="";
    ArrayList<String> listaRevisiones = new ArrayList<>();
    String resultRevisiones="";
    Cursor evaluaciones;
    Boolean existe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_primera_revision__eliminar);

        carnet=(EditText)findViewById(R.id.carnetDetallePrimeraEli);
        codDoc=(EditText)findViewById(R.id.codigoDocenteDetallePrimeraEli);
        revisiones=(Spinner)findViewById(R.id.spinnerResultadosPrimerasRevisionesEvaluaEli);
    }

    public void consultarReviPrimeraReviDetalleEli(View v)
    {
        String [] parteEvaluacion;
        String idEvaluacion="";
        if(!(codDoc.getText().toString().equals(""))  ) {
            DBHelper = new DBHelperInicial(this);
            DBHelper.abrir();
            Cursor resul = DBHelper.consultarMateriasDocente(codDoc.getText().toString());
            if(resul.moveToFirst())
            {
                do {
                    evaluaciones = DBHelper.consultarEvaluaciones(resul.getInt(0), resul.getString(1), resul.getInt(2));
                    if(evaluaciones.moveToFirst())
                    {
                        do{
                            resultRevisiones=(DBHelper.consultarPrimeraRevisionExiste(String.valueOf(evaluaciones.getInt(0))));
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

    public void eliminarDetallePrimeraRevision(View v)
    {
        if(!(listaRevisiones.size()==0))
        {
            if(revisiones.getSelectedItem().toString().equals("Seleccione su revision")|
                    carnet.getText().toString().equals(""))
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
                idEvaluacion = DBHelper.consultarPrimeraRevisionConId(revisionParte[0]);
                siAlumno = DBHelper.consultarAlumnoSoliPrimeraRevisionAntesDetalle(Integer.valueOf(idEvaluacion), carnet.getText().toString());
                if(siAlumno.equals(""))
                {
                    Toast.makeText(this, "El estudiante no tiene una solicitud de segunda revision", Toast.LENGTH_SHORT).show();
                }
                else {

                    existe = DBHelper.consultarDetallePrimeraAntesDeAcciones(Integer.valueOf(revisionParte[0]), Integer.valueOf(siAlumno));


                    if (existe == true) {
                        mensaje = DBHelper.eliminarDetallePrimeraRevision(Integer.valueOf(revisionParte[0]), Integer.valueOf(siAlumno));
                        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "No existe ese detalle de segunda revisión", Toast.LENGTH_SHORT).show();
                    }
                    DBHelper.cerrar();
                }
            }
        }
        else {
            Toast.makeText(this, "No ha consultado las revisiones", Toast.LENGTH_SHORT).show();
        }
    }


    public void limpiarTexto(View v)
    {
        carnet.setText("");
        codDoc.setText("");
        listaRevisiones.clear();
        revisiones.setAdapter(null);
    }
}
