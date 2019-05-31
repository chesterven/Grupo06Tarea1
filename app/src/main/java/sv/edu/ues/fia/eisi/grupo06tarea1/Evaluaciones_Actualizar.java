//Autor: Christian Ariel Zelaya Tejada
//Carnet:ZT12002
//Grupo de trabajo: 06
package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Evaluaciones_Actualizar extends AppCompatActivity {
    DBHelperInicial DBHelper;
    public Spinner materia;
    public Spinner resultad;
    public Spinner tipoeva;
    public Spinner numgrupo;
    public Spinner idciclo;
    EditText nuevafecha;
    EditText nuevonombre;
    EditText descripcion;
    ArrayList<String> materias = new ArrayList<>();
    ArrayList<String> resultados = new ArrayList<>();
    ArrayList<String> tipoevas = new ArrayList<>();
    ArrayList<String> numgrupos = new ArrayList<>();
    ArrayList<String> codmaterias = new ArrayList<>();
    ArrayList<String> idciclos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluaciones__actualizar);
        materia = (Spinner) findViewById(R.id.spinnermateria);
        resultad = (Spinner) findViewById(R.id.spinnerres);
        tipoeva = (Spinner) findViewById(R.id.spinnertg);
        numgrupo = (Spinner) findViewById(R.id.spinnerng);
        idciclo = (Spinner) findViewById(R.id.spinnerCiclo);
        nuevafecha = (EditText) findViewById(R.id.fechaEvaluacion);
        nuevonombre = (EditText) findViewById(R.id.nombreEva);
        descripcion = (EditText) findViewById(R.id.descrip);
        materias.add("Seleccione la materia");
        tipoevas.add("Seleccione una evaluacion");
        numgrupos.add("Seleccione el tipo de grupo");
        resultados.add("");
        idciclos.add("Seleccione el ciclo");
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
        materia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ConsultarEvaluacion(view);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
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
        Cursor resultado5 = DBHelper.consultarCi();
        if(resultado5.moveToFirst()){
            do{
                idciclos.add(resultado5.getString(0) + " " + resultado5.getString(1));
            }while (resultado5.moveToNext());
        }
        ArrayAdapter<CharSequence> adaptador5 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,idciclos);
        idciclo.setAdapter(adaptador5);

    }


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ConsultarEvaluacion(view);
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }




    public void ConsultarEvaluacion(View v){
        if(materia.getSelectedItem().equals("Seleccione la materia")){
            Toast.makeText(this, "Selecciona la materia primero", Toast.LENGTH_SHORT).show();
        }else{
            Evaluaciones eva = new Evaluaciones();
            String mate = "";
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
            resultad.setAdapter(adaptador2);
            resultad.setSelection(1);

        }
    }

    public void actualizar(View v){
if(materia.getSelectedItem().equals("Seleccione la materia")|| numgrupo.getSelectedItem().equals("Seleccione el tipo de grupo")||
tipoeva.getSelectedItem().equals("Seleccione una evaluacion")||idciclo.getSelectedItem().equals("Seleccione el ciclo")||nuevafecha.getText().equals("")
||nuevonombre.getText().equals("")||descripcion.getText().equals("")){
    Toast.makeText(this, "Selecciona una materia", Toast.LENGTH_SHORT).show();
}
else{
    String tipoe = "";
    String numgr = "";
    String codma = "";
    String idcic = "";
    String result = "";
    result = resultad.getSelectedItem().toString();
    String[] resultparte = result.split(" ");

    Evaluaciones eva = new Evaluaciones();
    eva.setFechaEvaluacion(nuevafecha.getText().toString());
    eva.setNombreEvaluacion(nuevonombre.getText().toString());
    eva.setDescripcion(descripcion.getText().toString());
    tipoe = tipoeva.getSelectedItem().toString();
    String[] tipoeparte = tipoe.split(" ");
    eva.setIdTipoevaluacion(Integer.valueOf(tipoeparte[0]));
    numgr = numgrupo.getSelectedItem().toString();
    String[] numgrupoparte = numgr.split(" ");
    eva.setNumGrupo(Integer.valueOf(numgrupoparte[0]));
    codma = materia.getSelectedItem().toString();
    String[] codmaparte = codma.split(" ");
    eva.setCodMateria(codmaparte[0]);
    idcic =  idciclo.getSelectedItem().toString();
    String[] idcicparte = idcic.split(" ");
    eva.setIdCiclo(Integer.valueOf(idcicparte[0]));
    DBHelper = new DBHelperInicial(this);
    DBHelper.abrir();
    String mensaje = DBHelper.ActualizarEva(eva,resultparte[0]);
    Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();

}
    }
    public void limpiarText(View v){
        finish();
        startActivity(getIntent());
    }
}
