package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MenuCarnet extends AppCompatActivity {

   String idusuario = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_carnet);
        Intent recibir = getIntent();
        idusuario = recibir.getStringExtra("idusuario");
    }

    public void gc16001(View v){
        Intent ven=new Intent(this,MenuGc16001.class);
        ven.putExtra("idusuario",idusuario);
        startActivity(ven);
    }

    public void ch15013(View v){
        Intent ven=new Intent(this,MenuCh15013.class);
        ven.putExtra("idusuario",idusuario);
        startActivity(ven);
    }

    public void vd16006(View v){
        Intent ven=new Intent(this,MenuVd16006.class);
        ven.putExtra("idusuario",idusuario);
        startActivity(ven);
    }

    public void cs16008(View v){
        Intent ven=new Intent(this,MenuCs16008.class);
        ven.putExtra("idusuario",idusuario);
        startActivity(ven);
    }

}
