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
    ArrayList<String> resultados = new ArrayList<>();
    ArrayList<String> rsx = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_ciclo__actualizar);
        numgrupo = (EditText) findViewById(R.id.NumGrupo);
        Docente = (Spinner) findViewById(R.id.spinnerdoc);
        materia = (Spinner) findViewById(R.id.spinnerMateria);
        tipogr = (Spinner) findViewById(R.id.spinnertg);
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

        /*Consultar Docente*/
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        Cursor resultado5 = DBHelper.consultarDoc();
        if(resultado5.moveToFirst()){
            do{
                Docentes.add(resultado5.getString(0) + " " + resultado5.getString(1));
            }while (resultado5.moveToNext());
        }
        ArrayAdapter<CharSequence> adaptador5 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,Docentes);
        Docente.setAdapter(adaptador5);
        /*Consultar Tipo de grupo*/
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
        /*Consultar Ciclo*/
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        Cursor resultado7 = DBHelper.consultarCi();
        if(resultado7.moveToFirst()){
            do{
                ciclos.add(resultado7.getString(0) + " " + resultado7.getString(1));
            }while (resultado7.moveToNext());
        }
        ArrayAdapter<CharSequence> adaptador7 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,ciclos);
        ciclo.setAdapter(adaptador7);

    }

    public void consultar(View v){
    if(materia.getSelectedItem().toString().equals("Seleccione la materia")){
        Toast.makeText(this,"Seleccione la materia", Toast.LENGTH_SHORT).show();
    }
    else{
        /*LLenado Spinner Resultado*/
        String mate = "";

        MateriaCicloLogica mat = new MateriaCicloLogica();
        mate = materia.getSelectedItem().toString();
        String[] mateparte = mate.split(" ");
        mat.setCodMateria(mateparte[0]);

        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        Cursor resultado2 = DBHelper.consultarMateria(mat);
        if(resultado2.moveToFirst()){
            do{
                resultados.add(DBHelper.consultarcate(resultado2.getString(3)) + " " + "Grupo: "+resultado2.getString(0) + " " + DBHelper.consultarTipog(resultado2.getString(2)) + " " + "Ciclo:" +DBHelper.consultarcicl(resultado2.getString(1)));
            }while (resultado2.moveToNext());
        }
        ArrayAdapter<CharSequence> adaptador2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,resultados);
        resultado.setAdapter(adaptador2);
    }

    }

    public void actualizarMateriaCi(View v){
        if(materia.getSelectedItem().toString().equals("Seleccione la materia")||resultado.getSelectedItem().toString().equals("")||
        numgrupo.getText().toString().equals("")|| Docente.getSelectedItem().toString().equals("Selecciona el docente")||
        tipogr.getSelectedItem().toString().equals("Selecciona el tipo de grupo")||ciclo.getSelectedItem().toString().equals("Selecciona el ciclo")){
            Toast.makeText(this,"Complete el formulario", Toast.LENGTH_SHORT).show();
        }
        else {
            String mate = "";
            MateriaCicloLogica mat = new MateriaCicloLogica();
            mate = materia.getSelectedItem().toString();
            String[] mateparte = mate.split(" ");
            String delete = "";
            delete = resultado.getSelectedItem().toString();
            String[] deleteparte = delete.split(" ");
            /*deleteparte[3]=numgrupo
             *deleteparte[0]=nombredocente*/
            String docent = "";
            docent = Docente.getSelectedItem().toString();
            String[] docentparte = docent.split(" ");
            /*********************************************/
            String tipogru = "";
            tipogru = tipogr.getSelectedItem().toString();
            String[] tipogruparte = tipogru.split(" ");
            /*********************************************/
            /*********************************************/
            String ciclu = "";
            ciclu = ciclo.getSelectedItem().toString();
            String[] cicluparte = ciclu.split(" ");
            /*********************************************/
            mat.setCodDocente(docentparte[0]);
            mat.setCodMateria(mateparte[0]);
            mat.setIdTipoGrupo(Integer.valueOf(tipogruparte[0]));
            mat.setIdCiclo(Integer.valueOf(cicluparte[0]));
            mat.setNumGrupo(Integer.valueOf(numgrupo.getText().toString()));
            String cod = DBHelper.consultarcatebyname(deleteparte[0]);
            String mensaje = DBHelper.actualizarMateriaCiclo(mat,cod,deleteparte[3],mateparte[0]);
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
            limpiar(v);
        }


    }

    public void limpiar(View v){
        finish();
        startActivity(getIntent());
    }
}
