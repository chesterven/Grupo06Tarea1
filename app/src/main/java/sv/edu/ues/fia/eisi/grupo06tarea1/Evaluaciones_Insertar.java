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
import android.widget.Toast;

import java.util.ArrayList;

public class Evaluaciones_Insertar extends AppCompatActivity {
    DBHelperInicial DBHelper;
    public Spinner tipoeva;
    public Spinner numgrupo;
    public Spinner codmateria;
    public Spinner idciclo;
    EditText fechaevaluacion;
    EditText nombreeva;
    EditText descripcion;
    ArrayList<String> tipoevas = new ArrayList<>();
    ArrayList<String> numgrupos = new ArrayList<>();
    ArrayList<String> codmaterias = new ArrayList<>();
    ArrayList<String> idciclos = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluaciones__insertar);
        tipoeva = (Spinner) findViewById(R.id.spinnertg);
        numgrupo = (Spinner) findViewById(R.id.spinnerng);
        codmateria = (Spinner) findViewById(R.id.spinnermateria);
        idciclo = (Spinner) findViewById(R.id.spinnerCiclo);
        fechaevaluacion = (EditText) findViewById(R.id.fechaEvaluacion);
        nombreeva = (EditText) findViewById(R.id.nombreEva);
        descripcion = (EditText) findViewById(R.id.descrip);
        tipoevas.add("Seleccione una evaluacion");
        numgrupos.add("Seleccione el tipo de grupo");
        codmaterias.add("Seleccione una materia");
        idciclos.add("Seleccione el ciclo");
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        Cursor resultado = DBHelper.consultarMat();
        if(resultado.moveToFirst()){
            do{
                codmaterias.add(resultado.getString(0) + " " + resultado.getString(1));
            }while (resultado.moveToNext());
        }
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,codmaterias);
        codmateria.setAdapter(adaptador);
        /*LLenado de spinner Docente*/
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        Cursor resultado2 = DBHelper.consultarTG();
        if(resultado2.moveToFirst()){
            do{
                numgrupos.add(resultado2.getString(0) + " " + resultado2.getString(1));
            }while (resultado2.moveToNext());
        }
        ArrayAdapter<CharSequence> adaptador2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,numgrupos);
        numgrupo.setAdapter(adaptador2);
        /*LLenado de spinner Evaluacion*/
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        Cursor resultado3 = DBHelper.consultarEva();
        if(resultado3.moveToFirst()){
            do{
                tipoevas.add(resultado3.getString(0) + " " + resultado3.getString(1));
            }while (resultado3.moveToNext());
        }
        ArrayAdapter<CharSequence> adaptador3 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,tipoevas);
        tipoeva.setAdapter(adaptador3);

        /*LLenado de spinner Ciclo*/
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        Cursor resultado4 = DBHelper.consultarCi();
        if(resultado4.moveToFirst()){
            do{
                idciclos.add(resultado4.getString(0) + " " + resultado4.getString(1));
            }while (resultado4.moveToNext());
        }
        ArrayAdapter<CharSequence> adaptador4 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,idciclos);
        idciclo.setAdapter(adaptador4);
    }

    public void InsertarEvaluacion(View v){
        if(codmateria.getSelectedItem().equals("Seleccione una materia")|| numgrupo.getSelectedItem().equals("Seleccione el tipo de grupo")||
        tipoeva.getSelectedItem().equals("Seleccione una evaluacion")||idciclo.getSelectedItem().equals("Seleccione el ciclo")||
        fechaevaluacion.getText().toString().equals("")||nombreeva.getText().toString().equals("")||descripcion.getText().toString().equals("")){
            Toast.makeText(this,"Complete todos los campos", Toast.LENGTH_SHORT).show();
        }else{
            String tipoe = "";
            String numgr = "";
            String codma = "";
            String idcic = "";
            Evaluaciones eva = new Evaluaciones();
            eva.setFechaEvaluacion(fechaevaluacion.getText().toString());
            eva.setNombreEvaluacion(nombreeva.getText().toString());
            eva.setDescripcion(descripcion.getText().toString());
            tipoe = tipoeva.getSelectedItem().toString();
            String[] tipoeparte = tipoe.split(" ");
            eva.setIdTipoevaluacion(Integer.valueOf(tipoeparte[0]));
            numgr = numgrupo.getSelectedItem().toString();
            String[] numgrupoparte = numgr.split(" ");
            eva.setNumGrupo(Integer.valueOf(numgrupoparte[0]));
            codma = codmateria.getSelectedItem().toString();
            String[] codmaparte = codma.split(" ");
            eva.setCodMateria(codmaparte[0]);
            idcic =  idciclo.getSelectedItem().toString();
            String[] idcicparte = idcic.split(" ");
            eva.setIdCiclo(Integer.valueOf(idcicparte[0]));
            DBHelper = new DBHelperInicial(this);
            DBHelper.abrir();
            String mensaje = DBHelper.insertarEvaluacion(eva);
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();

        }

    }

    public void limpiarTexto(View v){
        tipoeva.setSelection(0);
        codmateria.setSelection(0);
        numgrupo.setSelection(0);
        idciclo.setSelection(0);
        fechaevaluacion.setText("");
        nombreeva.setText("");
        descripcion.setText("");

    }
}
