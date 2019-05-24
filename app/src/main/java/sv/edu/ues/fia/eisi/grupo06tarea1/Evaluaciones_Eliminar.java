//Autor: Christian Ariel Zelaya Tejada
//Carnet:ZT12002
//Grupo de trabajo: 06
package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Evaluaciones_Eliminar extends AppCompatActivity {
    DBHelperInicial DBHelper;
    public Spinner materia;
    public Spinner resultado;

    ArrayList<String> materias = new ArrayList<>();
    ArrayList<String> resultados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluaciones__eliminar);
        materia = (Spinner) findViewById(R.id.spinnermateria);
        resultado = (Spinner) findViewById(R.id.spinnerCiclo);
        materias.add("Seleccione la materia");
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        Cursor resultado4 = DBHelper.consultarMat();
        if(resultado4.moveToFirst()){
            do{
                materias.add(resultado4.getString(0) + " " + resultado4.getString(1));
            }while (resultado4.moveToNext());
        }
        ArrayAdapter<CharSequence> adaptador4 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,materias);
        materia.setAdapter(adaptador4);
    }

    public void consultarEva(View v){
        if(materia.getSelectedItem().equals("Seleccione la materia")){
            Toast.makeText(this, "Selecciona la materia primero", Toast.LENGTH_SHORT).show();
        }else{
            String mate = "";
            Evaluaciones eva = new Evaluaciones();
            mate = materia.getSelectedItem().toString();
            String[] mateparte = mate.split(" ");
            eva.setCodMateria(mateparte[0]);
            DBHelper = new DBHelperInicial(this);
            DBHelper.abrir();
            Cursor resultado2 = DBHelper.consultarEvaluacio(eva);
            if(resultado2.moveToFirst()){
                do{
                    resultados.add(resultado2.getString(4)+ " " +resultado2.getString(5) +" "+resultado2.getString(6));
                }while (resultado2.moveToNext());
            }
            ArrayAdapter<CharSequence> adaptador2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,resultados);
            resultado.setAdapter(adaptador2);
        }
    }

    public void Eliminarevaluacion(View v){
        String fecha = "";
        String mater = "";
        Evaluaciones eva = new Evaluaciones();
        fecha = resultado.getSelectedItem().toString();
        String[] fechaparte = fecha.split(" ");
        eva.setFechaEvaluacion(fechaparte[0]);
        mater = materia.getSelectedItem().toString();
        String[] materparte = mater.split(" ");
        eva.setCodMateria(materparte[0]);
        String mensaje = DBHelper.EliminarEva(eva);
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();

    }
    public void limpiarText(View v){
        finish();
        startActivity(getIntent());
    }



}
