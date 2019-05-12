//Autor: Cordero Hernández, Oscar Emmanuel
//Carnet:CH15013
//Grupo de trabajo: 06

package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;

public class MenuCh15013 extends AppCompatActivity {
    String idusuario = "";
    DBHelperInicial DBHelper;
    ArrayList<String> arrayAccesos = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_ch15013);
        Intent recibir = getIntent();
        idusuario = recibir.getStringExtra("idusuario");
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        arrayAccesos = DBHelper.consultarAccesos(idusuario);
        DBHelper.cerrar();
    }
}
