//Autor: Christian Ariel Zelaya Tejada
//Carnet:ZT12002
//Grupo de trabajo: 06
package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.ArrayList;
import android.widget.Toast;

public class MateriaCiclo_Insertar extends AppCompatActivity {
    DBHelperInicial DBHelper;
    public Spinner docente;
    public Spinner ciclo;
    public Spinner tipogrupo;
    public Spinner materia;
    public EditText numGrupo;
    ArrayList<String> docentes = new ArrayList<>();
    ArrayList<String> ciclos = new ArrayList<>();
    ArrayList<String> tipogrupos = new ArrayList<>();
    ArrayList<String> materias = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_ciclo__insertar);
        DBHelper = new DBHelperInicial(this);
        docente = (Spinner) findViewById(R.id.spinnerdoc);
        ciclo = (Spinner) findViewById(R.id.spinnerciclo);
        tipogrupo = (Spinner) findViewById(R.id.spinnertg);
        materia = (Spinner) findViewById(R.id.spinnerMateria);
        numGrupo = (EditText) findViewById(R.id.NumGrupo);
        docentes.add("Seleccione el docente");
        ciclos.add("Seleccione el ciclo");
        tipogrupos.add("Seleccione el tipo de grupo");
        materias.add("Seleccione la materia");
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        /*LLenado del spinner de docentes*/
        Cursor resultado = DBHelper.consultarDoc();
        if(resultado.moveToFirst()){
            do{
                docentes.add(resultado.getString(0) + " " + resultado.getString(1) + " " + resultado.getString(2));
            }while (resultado.moveToNext());
        }
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,docentes);
        docente.setAdapter(adaptador);
        /*LLenado del spinner de los ciclos*/
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        Cursor resultado2 = DBHelper.consultarCi();
        if(resultado2.moveToFirst()){
            do{
                ciclos.add(String.valueOf(resultado2.getInt(0)) + " " + resultado2.getString(1));
            }while (resultado2.moveToNext());
        }
        ArrayAdapter<CharSequence> adaptador2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,ciclos);
        ciclo.setAdapter(adaptador2);

        /*LLenado del spinner de los tipos de grupos*/
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        Cursor resultado3 = DBHelper.consultarTG();
        if(resultado3.moveToFirst()){
            do{
                tipogrupos.add(String.valueOf(resultado3.getInt(0)) + " " + resultado3.getString(1));
            }while (resultado3.moveToNext());
        }
        ArrayAdapter<CharSequence> adaptador3 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,tipogrupos);
        tipogrupo.setAdapter(adaptador3);
        /*Llenado del spinner de materias*/
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

    public void InsertarMateriaCic(View v){
        if(docente.getSelectedItem().toString().equals("Seleccione el docente") || tipogrupo.getSelectedItem().toString().equals("Seleccione el tipo de grupo")
        || ciclo.getSelectedItem().toString().equals("Seleccione el ciclo") || materia.getSelectedItem().toString().equals("Seleccione la materia") ||
        numGrupo.getText().equals("")){
            Toast.makeText(this,"Selecciona un parametro", Toast.LENGTH_SHORT).show();
        }
        else{
            String docen = "";
            String mate = "";
            String cicl = "";
            String tipog = "";
            MateriaCicloLogica mat = new MateriaCicloLogica();
            mat.setNumGrupo(Integer.parseInt(numGrupo.getText().toString()));
            docen = docente.getSelectedItem().toString();
            String[] doceParte = docen.split(" ");
            mat.setCodDocente(doceParte[0]);
            mate = materia.getSelectedItem().toString();
            String[] mateparte = mate.split(" ");
            mat.setCodMateria(mateparte[0]);
            cicl = ciclo.getSelectedItem().toString();
            String[]  ciclparte = cicl.split(" ");
            mat.setIdCiclo(Integer.parseInt(ciclparte[0]));
            tipog = tipogrupo.getSelectedItem().toString();
            String[] tipoparte = tipog.split(" ");
            mat.setIdTipoGrupo(Integer.parseInt(tipoparte[0]));
            DBHelper = new DBHelperInicial(this);
            DBHelper.abrir();
            String mensaje = DBHelper.insertarMateriaCiclo(mat);
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
            finish();
            startActivity(getIntent());

        }

    }
}
