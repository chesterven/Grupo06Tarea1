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

public class DetallePrimeraRevision_Consultar extends AppCompatActivity {

    DBHelperInicial DBHelper;
    EditText asistencia,notaAnt,notaNueva,motivo,codDoc,carnet;
    Spinner primerasRevs;
    Cursor evaluaciones;
    String resultRevisiones="";
    String idEvaluacion="";
    String siAlumno;
    Boolean existe;
    ArrayList<String> arrayRevs=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_primera_revision__consultar);

        carnet = (EditText)findViewById(R.id.carnetDetallePrimeraCon);
        codDoc=(EditText)findViewById(R.id.codigoDocenteDetallePrimeraCon);
        asistencia=(EditText)findViewById(R.id.asistenciaDetallePrimeraMostrar);
        notaAnt=(EditText)findViewById(R.id.notaAntDetallePrimeraMostrar);
        notaNueva=(EditText)findViewById(R.id.nuevaNotaDetallePrimeraMostrar);
        motivo=(EditText)findViewById(R.id.motivoDetallPrimeraMostrar);
        primerasRevs= (Spinner)findViewById(R.id.spinnerResultadosPrimerasRevisionesEvaluaCon);
    }

    public void consultarReviPrimeraReviDetalleCon (View v)
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
                               arrayRevs.add(resultRevisiones);
                            }}
                        while (evaluaciones.moveToNext());
                    }
                }while(resul.moveToNext());
                if(arrayRevs.size()==0)
                {
                    Toast.makeText(this,"No hay primeras revisiones para el docente", Toast.LENGTH_SHORT).show();
                }
                else{
                    arrayRevs.add(0,"Seleccione su revision");
                    ArrayAdapter<CharSequence> adaptadorr = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayRevs);
                    primerasRevs.setAdapter(adaptadorr);
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
    public void consultarDetallePrimeraRevision(View v) {
        if (!(arrayRevs.size() == 0)) {

            if(primerasRevs.getSelectedItem().toString().equals("Seleccione su revision")|
                    carnet.getText().toString().equals(""))
            {
                Toast.makeText(this, "Debe seleccionar una revision e ingresar su carnet", Toast.LENGTH_SHORT).show();
            }
            else {
                DBHelper = new DBHelperInicial(this);
                DBHelper.abrir();
                String revision = "";
                String mensaje = "";

                revision = primerasRevs.getSelectedItem().toString();
                String[] revisionParte = revision.split(" ");
                idEvaluacion = DBHelper.consultarPrimeraRevisionConId(revisionParte[0]);
                /*idSoli*/   siAlumno = DBHelper.consultarAlumnoSoliPrimeraRevisionAntesDetalle(Integer.valueOf(idEvaluacion), carnet.getText().toString());
                if(siAlumno.equals(""))
                {
                    Toast.makeText(this, "El estudiante no tiene una solicitud de segunda revision", Toast.LENGTH_SHORT).show();
                }
                else {

                    existe = DBHelper.consultarDetallePrimeraAntesDeAcciones(Integer.valueOf(revisionParte[0]), Integer.valueOf(siAlumno));


                    if (existe) {
                        DetallePrimeraRevision detalles = new DetallePrimeraRevision();
                        detalles=DBHelper.consultarDetallePrimeraRevision(Integer.valueOf(revisionParte[0]),Integer.valueOf(siAlumno));
                        Toast.makeText(this, "Primera Revisión encontrada", Toast.LENGTH_SHORT).show();

                        if(detalles.isAsitenciaPrimerRevision()==true)
                        {
                            asistencia.setText("Asistió");
                        }
                        else
                        {
                            asistencia.setText("No Asistió");

                        }
                        notaAnt.setText(String.valueOf(detalles.getNotaOriginal()));
                        notaNueva.setText(String.valueOf(detalles.getNotaNueva()));
                        motivo.setText(String.valueOf(detalles.getMotivoCambio()));

                    } else {
                        Toast.makeText(this, "No existe ese detalle de primera revisión", Toast.LENGTH_SHORT).show();
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

    public void limpiarTexto(View v) {
        carnet.setText("");
        asistencia.setText("");
        notaAnt.setText("");
        notaNueva.setText("");
        codDoc.setText("");
        arrayRevs.clear();
        primerasRevs.setAdapter(null);
        motivo.setText("");
    }
}
