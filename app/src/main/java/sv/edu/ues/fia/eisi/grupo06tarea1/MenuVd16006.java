package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MenuVd16006 extends AppCompatActivity {
    String idusuario = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_vd16006);
        Intent recibir = getIntent();
        idusuario = recibir.getStringExtra("idusuario");

    }
}
