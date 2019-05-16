//Autor: Roberto Eliezer Ventura Dominguez
//Carnet:VD16006
//Grupo de trabajo: 06

package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Solicitud_Diferido_Insertar extends AppCompatActivity {
    DBHelperInicial DBHelper;
    EditText carnetSoliIn, motivoSoliIn;
    Spinner spinnerResultado;
    Cursor datos;
    ArrayList<String> evaluaciones = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud__diferido__insertar);

        carnetSoliIn = (EditText) findViewById(R.id.carnetSoliDiferidoIn);


        spinnerResultado = (Spinner) findViewById(R.id.spinnerResultados);
        motivoSoliIn = (EditText) findViewById(R.id.motivoSoliDiferidoIn);

    }

    public void consultarParaDiferido(View v){
        Cursor eval;
        if(carnetSoliIn.getText().toString().equals(""))
        {
            Toast.makeText(this,"Ingrese su carnet",Toast.LENGTH_SHORT).show();
        }
        else {

            DBHelper = new DBHelperInicial(this);
            DBHelper.abrir();
            datos = DBHelper.consultarEstudianteInscrito(carnetSoliIn.getText().toString());
            if(datos.moveToFirst()) {
                do {
                    eval = DBHelper.consultarEvaluaciones(datos.getInt(0), datos.getString(1), datos.getInt(2));
                    if((eval.moveToFirst())){
                        do{
                            evaluaciones.add(eval.getInt(0)+" "+eval.getString(1)+" "+eval.getString(2));
                        }while(eval.moveToNext());

                    }

                } while (datos.moveToNext());
                if(evaluaciones.size()==0){
                    Toast.makeText(this,"No hay evaluaciones disponibles",Toast.LENGTH_LONG).show();
                }
                else{
                    evaluaciones.add(0,"Seleccione evaluacion");
                    ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,evaluaciones);
                    spinnerResultado.setAdapter(adaptador);
                    Toast.makeText(this, "Puede seleccionar una evaluacion", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this,"No esta inscrito en ninguna materia o no existe ese carnet",Toast.LENGTH_LONG).show();
            }


        }

        }

        public void insertarSolicitudDiferido(View v){
        if(!(evaluaciones.size()==0)){
            if(motivoSoliIn.getText().toString().equals("") | spinnerResultado.getSelectedItem().toString().equals("Seleccione evaluacion")){
                Toast.makeText(this,"Ingrese motivo o seleccione una evaluacion",Toast.LENGTH_SHORT).show();
            }else{
                String mensaje = "";
                String evaluacion;
                Solicitud_RepetidoDiferido solicitud = new Solicitud_RepetidoDiferido();
                evaluacion = spinnerResultado.getSelectedItem().toString();
                String [] evaluacionPartes = evaluacion.split(" ");
                solicitud.setCarnet(carnetSoliIn.getText().toString());
                solicitud.setIdEvaluacion(Integer.valueOf(evaluacionPartes[0]));
                solicitud.setMotivoSolicitud(motivoSoliIn.getText().toString());
                solicitud.setAprobado(false);
                solicitud.setIdTipoSolicitud(2);
                DBHelper.abrir();
                mensaje = DBHelper.insertarSolicitudDiferidoRepetido(solicitud);

                Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,"Tiene que consultar evaluaciones",Toast.LENGTH_SHORT).show();
        }


        }

    public void limpiarTexto(View v){
        carnetSoliIn.setText("");
        motivoSoliIn.setText("");
        spinnerResultado.setAdapter(null);
        evaluaciones.clear();
    }
}
