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

public class MateriaCiclo_Consultar extends AppCompatActivity {
    DBHelperInicial DBHelper;
    public Spinner materia;
    public Spinner docente;
    public Spinner resultado;
    EditText tipog;
    EditText ciclo;
    ArrayList<String> materias = new ArrayList<>();
    ArrayList<String> docentes = new ArrayList<>();
    ArrayList<String> resultados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_ciclo__consultar);


        materia = (Spinner) findViewById(R.id.spinner);
        resultado =  (Spinner) findViewById(R.id.spinner3);
        materias.add("Seleccione la materia");
        docentes.add("Seleccione el docente");
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

    public void ConsultarMateria(View v){
        if(materia.getSelectedItem().equals("Seleccione la materia") ){
            Toast.makeText(this, "Selecciona la materia primero", Toast.LENGTH_SHORT).show();
        }else{
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

    public void limpiartTexto(View v){
        materia.setSelection(0);
        resultado.setSelection(0);

    }
}
