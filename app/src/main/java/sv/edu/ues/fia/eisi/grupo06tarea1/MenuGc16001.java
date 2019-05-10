package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MenuGc16001 extends AppCompatActivity {
    String idusuario="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_gc16001);
        Intent recibir = getIntent();
        idusuario = recibir.getStringExtra("idusuario");
        EditText iduser;
        iduser = (EditText) findViewById(R.id.editText);
        iduser.setText(idusuario);
    }
}
