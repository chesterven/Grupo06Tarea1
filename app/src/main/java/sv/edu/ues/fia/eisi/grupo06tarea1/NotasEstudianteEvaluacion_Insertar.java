//CS16008 Castro Sánchez José Andrés

package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;



public class NotasEstudianteEvaluacion_Insertar extends AppCompatActivity {

    DBHelperInicial DBHelper;
    Spinner codDocente;
    Spinner codMateria;
    Spinner numGrupo;
    EditText evalua;
    EditText carnet;
    EditText nota;
    ArrayList<String> docentes=new ArrayList<>();
    ArrayList<MateriaCicloLogica> materias=new ArrayList<>();
    ArrayList<String> listaMaterias=new ArrayList<>();
    ArrayList<String> grupos=new ArrayList<>();
    ArrayList<String> evaluaciones=new ArrayList<>();
    ArrayList<String> alumnos=new ArrayList<>();

    ArrayList<Integer> ciclos=new ArrayList<>();

    ArrayAdapter<CharSequence> adaptador;
    ArrayAdapter<CharSequence> adaptador2;
    ArrayAdapter<CharSequence> adaptador3;
    ArrayAdapter<CharSequence> adaptador4;
    ArrayAdapter<CharSequence> adaptador5;


    MateriaCicloLogica matLogica=new MateriaCicloLogica();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas_estudiante_evaluacion__insertar);

        codDocente = (Spinner)findViewById(R.id.spinnerCodDocenteNotasEstudianteEvaluacionInsertar);
        codMateria= (Spinner)findViewById(R.id.spinnerMateriasNotasEstudianteEvaluacionInsertar);
        numGrupo= (Spinner)findViewById(R.id.spinnerGruposMateriaNotasEstudianteEvaluacionInsertar);
        //evalua= (Spinner)findViewById(R.id.spinnerEvaluacionesGrupoMateriaNotasEstudianteEvaluacionInsertar);
        //carnet=(Spinner)findViewById(R.id.spinnerCarnetGrupoMateriaNotasEstudianteEvaluacionInsertar);
        evalua= (EditText) findViewById(R.id.editTextEvaluacionesGrupoMateriaNotasEstudianteEvaluacionInsertar);
        carnet=(EditText) findViewById(R.id.editTextCarnetInsertarNotaEstudianteEvaluacion);
        nota= (EditText) findViewById(R.id.notaInsertarNotaEstudianteEvaluacion);

        docentes.add("Seleccione");
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        final Cursor resultado=DBHelper.consultarDocentes();
        if(resultado.moveToFirst())
        {
            while(resultado.moveToNext()){
                docentes.add(resultado.getString(0));
            }
        }

        adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,docentes);
        codDocente.setAdapter(adaptador);
        codDocente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             if(position!=0){
                 MateriaCicloLogica materiaCicloLogica = null;
                  Cursor resultado2=DBHelper.consultarMateriasDocente(codDocente.getSelectedItem().toString());
                  materias.clear();
                  listaMaterias.clear();
                  listaMaterias.add("Seleccione");
                  codMateria.setClickable(true);
                  while(resultado2.moveToNext()){
                      materiaCicloLogica= new MateriaCicloLogica();
                      materiaCicloLogica.setNumGrupo(resultado2.getInt(0));
                      materiaCicloLogica.setCodMateria(resultado2.getString(1));
                      materiaCicloLogica.setIdCiclo(resultado2.getInt(2));

                      materias.add(materiaCicloLogica);

                      listaMaterias.add(materiaCicloLogica.getCodMateria());


                      setAdapter2(listaMaterias);
                      codMateria.setAdapter(adaptador2);
                  }
             }
             else{

                 evalua.setText("");
                 carnet.setText("");
                 nota.setText("");

                 materias.clear();
                 listaMaterias.clear();
                 ciclos.clear();
                 setAdapter2(listaMaterias);
                 codMateria.setAdapter(adaptador2);
                 codMateria.setClickable(false);

                 grupos.clear();
                 setAdapter3(grupos);
                 numGrupo.setAdapter(adaptador3);
                 numGrupo.setClickable(false);

                 //evaluaciones.clear();
                 //setAdapter4(evaluaciones);//setadapter4
                 //evalua.setAdapter(adaptador4);
                 //evalua.setClickable(false);
             }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                codMateria.setClickable(false);
            }
        });

        /////////////////////////////////////////////////////////////////////////
        ArrayAdapter<CharSequence> adaptador2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaMaterias);
        codMateria.setAdapter(adaptador2);
        codMateria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){

                    Cursor resultado3=DBHelper.consultarGruposDocente(codDocente.getSelectedItem().toString(),codMateria.getSelectedItem().toString());
                    grupos.clear();
                    grupos.add("Seleccione");
                    numGrupo.setClickable(true);
                    while(resultado3.moveToNext()){
                        grupos.add(String.valueOf(resultado3.getInt(0)));
                        setAdapter3(grupos);
                        numGrupo.setAdapter(adaptador3);
                    }

                    //Log.i("///////////////////////",String.valueOf(codMateria.getSelectedItemPosition()));
                    //Log.i("///////////////////////",String.valueOf(ciclos.get(posicion)));
                }
                else{
                    evalua.setText("");
                    carnet.setText("");
                    nota.setText("");
                    grupos.clear();
                    setAdapter3(grupos);
                    numGrupo.setAdapter(adaptador3);
                    numGrupo.setClickable(false);


                    //evaluaciones.clear();
                    //setAdapter4(evaluaciones);//setadapter4
                    //evalua.setAdapter(adaptador4);
                    //evalua.setClickable(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                numGrupo.setClickable(false);
            }
        });

        ////////////////////////////////////////////////////////////////////////7

        ArrayAdapter<CharSequence> adaptador3 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,grupos);
        numGrupo.setAdapter(adaptador3);


        /*numGrupo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    Cursor resultado4=DBHelper.consultarEvaluacionesMateriaCiclo();
                    evaluaciones.clear();
                    evaluaciones.add("Seleccione");
                    evalua.setClickable(true);

                    while(resultado4.moveToNext()){

                        Log.i("COD MATERIA",resultado4.getString(3));
                        Log.i("NUM GRUPO",String.valueOf(resultado4.getInt(2)));
                        //Log.i("ID CICLO de COD materia",String.valueOf(materias.get(codMateria.getSelectedItemPosition()).getIdCiclo()));

                        if((resultado4.getString(3).equals(codMateria.getSelectedItem().toString())) &&  String.valueOf(resultado4.getInt(2)).equals(numGrupo.getSelectedItem().toString())&&matLogica.getIdCiclo()==resultado4.getInt(4)){
                            evaluaciones.add(resultado4.getString(6));
                            setAdapter4(evaluaciones);//setadapter4
                            evalua.setAdapter(adaptador4);
                        }
                    }
                }
                else{
                    evaluaciones.clear();
                    setAdapter4(evaluaciones);//setadapter4
                    evalua.setAdapter(adaptador4);
                    evalua.setClickable(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adaptador4 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,evaluaciones);
        evalua.setAdapter(adaptador4);
        evalua.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    //Cursor materiaCiclo= DBHelper.consultarMateriasDocente(codDocente.getSelectedItem().toString());
                    Cursor resultado5=DBHelper.consultarEstudianteInscritoMateriaCiclo(Integer.valueOf(numGrupo.getSelectedItem().toString()),codMateria.getSelectedItem().toString(),matLogica.getIdCiclo());
                    alumnos.clear();
                    alumnos.add("Seleccione");
                    carnet.setClickable(true);
                    while(resultado5.moveToNext()){
                        alumnos.add(String.valueOf(resultado5.getString(0)));
                        setAdapter5(alumnos);
                        carnet.setAdapter(adaptador5);
                    }
                }
                else{
                    alumnos.clear();
                    setAdapter5(alumnos);//setadapter4
                    carnet.setAdapter(adaptador5);
                    carnet.setClickable(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ////////////////////
        ArrayAdapter<CharSequence> adaptador5 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,alumnos);
        carnet.setAdapter(adaptador5);*/
    }

    public void setAdapter2(ArrayList list){
        this.adaptador2= new ArrayAdapter(this,android.R.layout.simple_spinner_item,list);
    }
    public void setAdapter3(ArrayList list){
        this.adaptador3= new ArrayAdapter(this,android.R.layout.simple_spinner_item,list);
    }
    public void setAdapter4(ArrayList list){
        this.adaptador4= new ArrayAdapter(this,android.R.layout.simple_spinner_item,list);
    }

    public void setAdapter5(ArrayList list){
        this.adaptador5= new ArrayAdapter(this,android.R.layout.simple_spinner_item,list);
    }


    public void insertarNota(View v){
        if(codDocente.getSelectedItem().toString().equals("")||codMateria.getSelectedItem().toString().equals("")||numGrupo.getSelectedItem().toString().equals("")||carnet.getText().toString().toUpperCase().equals("")||evalua.getText().equals("")||nota.getText().equals("")){
            Toast.makeText(this, "Posee campos en blanco en el formulario", Toast.LENGTH_SHORT).show();
        }
        else{
            if(Integer.valueOf(nota.getText().toString())<0&&Integer.valueOf(nota.getText().toString())>10){
                Toast.makeText(this, "Error en nota ingresada. Debe estar entre 0 y 10 su valor", Toast.LENGTH_SHORT).show();
            }else{
                DBHelper.abrir();
                String msj=DBHelper.insertarNotaEstudianteEvaluacion(carnet.getText().toString().toUpperCase(),evalua.getText().toString(),nota.getText().toString(),codMateria.getSelectedItem().toString(),codDocente.getSelectedItem().toString(),numGrupo.getSelectedItem().toString());
                DBHelper.cerrar();
                Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void limpiarTexto(View v){
        evalua.setText("");
        carnet.setText("");
        nota.setText("");

        docentes.clear();
        docentes.add("Seleccione");
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        final Cursor resultado=DBHelper.consultarDocentes();
        if(resultado.moveToFirst())
        {
            while(resultado.moveToNext()){
                docentes.add(resultado.getString(0));
            }
        }

        adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,docentes);
        codDocente.setAdapter(adaptador);

        materias.clear();
        listaMaterias.clear();
        ciclos.clear();
        setAdapter2(listaMaterias);
        codMateria.setAdapter(adaptador2);
        codMateria.setClickable(false);

        grupos.clear();
        setAdapter3(grupos);
        numGrupo.setAdapter(adaptador3);
        numGrupo.setClickable(false);



    }
}


