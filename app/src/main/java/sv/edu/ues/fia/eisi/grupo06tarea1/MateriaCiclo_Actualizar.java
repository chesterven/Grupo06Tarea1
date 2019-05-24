//Autor: Christian Ariel Zelaya Tejada
//Carnet:ZT12002
//Grupo de trabajo: 06
package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class MateriaCiclo_Actualizar extends AppCompatActivity {
    DBHelperInicial DBHelper;
    public Spinner Docente;
    public Spinner tipogr;
    public Spinner ciclo;
    public Spinner materia;
    public Spinner resultado;

    EditText numgrupo;
    ArrayList<String> Docentes = new ArrayList<>();
    ArrayList<String> tipogrs = new ArrayList<>();
    ArrayList<String> ciclos = new ArrayList<>();
    ArrayList<String> materias = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_ciclo__actualizar);
        numgrupo = (EditText) findViewById(R.id.NumGrupo);
        Docente = (Spinner) findViewById(R.id.spinnerdoc);
        tipogr = (Spinner) findViewById(R.id.spinnerMateria);
        ciclo = (Spinner) findViewById(R.id.spinnerciclo);
        resultado = (Spinner) findViewById(R.id.spinnerres);
        materias.add("Seleccione la materia");
        ciclos.add("Selecciona el ciclo");
        tipogrs.add("Selecciona el tipo de grupo");
        Docentes.add("Selecciona el docente");
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
        /*LLenado de spinner Ciclo*/
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        Cursor resultado5 = DBHelper.consultarCi();
        if(resultado5.moveToFirst()){
            do{
                ciclos.add(resultado5.getString(0) + " " + resultado5.getString(1));
            }while (resultado5.moveToNext());
        }
        ArrayAdapter<CharSequence> adaptador5 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,ciclos);
        ciclo.setAdapter(adaptador5);
        /*LLenado de Tipo Grupo*/
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        Cursor resultado6 = DBHelper.consultarTG();
        if(resultado6.moveToFirst()){
            do{
                tipogrs.add(resultado6.getString(0) + " " + resultado6.getString(1));
            }while (resultado6.moveToNext());
        }
        ArrayAdapter<CharSequence> adaptador6 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,tipogrs);
        tipogr.setAdapter(adaptador6);

    }

    public void actualizarMateriaCi(View v){

    }
}
