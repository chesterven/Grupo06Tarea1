//Autor: Roberto Eliezer Ventura Dominguez
//Carnet:VD16006
//Grupo de trabajo: 06

package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Solicitud_DiferidoRepetido_Actualizar extends AppCompatActivity {
    DBHelperInicial DBHelper;
    EditText carnetSoli, motivoSoli;
    Spinner evaluaciones;
    CheckBox aprobado;
    Cursor idEvaluacion;
    Solicitud_RepetidoDiferido solicitud =new Solicitud_RepetidoDiferido();
    ArrayList<String> arrayEvaluaciones = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud__diferido_repetido__actualizar);
        carnetSoli = (EditText) findViewById(R.id.carnetSoliDyRAct);
        evaluaciones = (Spinner) findViewById(R.id.spinnerResultadosDiferidoRepActualizar);
        motivoSoli = (EditText) findViewById(R.id.motivoSoliDyFIn);
        aprobado = (CheckBox) findViewById(R.id.checkBoxAprobadoSoliDyRIn);
    }

    public void consultarTipoSolicitud(View v){
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        if(carnetSoli.getText().toString().equals("")){
            Toast.makeText(this,"Ingrese el carnet",Toast.LENGTH_SHORT).show();
        }
        else{
            idEvaluacion = DBHelper.consultarSolicitudesDiferidoRepetido(carnetSoli.getText().toString());
            if(idEvaluacion.moveToFirst()){
                do{
                    arrayEvaluaciones.add(DBHelper.consultarEvaluacionesSolicitud(idEvaluacion.getInt(0)));
                }while(idEvaluacion.moveToNext());
                if(!(arrayEvaluaciones.size()==0)){
                    arrayEvaluaciones.add(0,"Seleccione evaluacion");
                    ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayEvaluaciones);
                    evaluaciones.setAdapter(adaptador);
                }
            }else{
                Toast.makeText(this,"No hay revisiones",Toast.LENGTH_SHORT).show();
            }

        }

    }

    public void actualizarSolicitud(View v){
        String mensaje="";
        if(!(arrayEvaluaciones.size()==0)){
            if(!(carnetSoli.getText().toString().equals("") | evaluaciones.getSelectedItem().toString().equals("Seleccione evaluacion"))){
                DBHelper = new DBHelperInicial(this);
                DBHelper.abrir();
                solicitud.setCarnet(carnetSoli.getText().toString());
                String eval = evaluaciones.getSelectedItem().toString();
                String[]evalPart = eval.split(" ");
                solicitud.setIdEvaluacion(Integer.valueOf(evalPart[0]));
                solicitud.setMotivoSolicitud(motivoSoli.getText().toString());
                if(aprobado.isChecked()){
                    solicitud.setAprobado(true);
                }
                else{
                    solicitud.setAprobado(false);
                }
                mensaje = DBHelper.actualizarSolicitudDiferidoRepetido(solicitud);
                Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this,"Llene los campos necesarios",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,"Consulte evaluciones",Toast.LENGTH_SHORT).show();
        }

    }

    public void limpiarTexto(View v){
        carnetSoli.setText("");
        motivoSoli.setText("");
        aprobado.setChecked(false);
        arrayEvaluaciones.clear();
        evaluaciones.setAdapter(null);
    }
}
