//Autor: Christian Ariel Zelaya Tejada
//Carnet:ZT12002
//Grupo de trabajo: 06
package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class MateriaCiclo_Actualizar extends AppCompatActivity {
    DBHelperInicial DBHelper;
    public Spinner Docente;
    public Spinner tipogr;
    public Spinner ciclo;
    public Spinner materia;
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

        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();

    }
}
