package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuSegundaRevision extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_segunda_revision);
    }

    public void segundaRevisionInsertar(View v){
        Intent ven=new Intent(this,SegundaRevision_Insertar.class);
        startActivity(ven);
    }

    public void segundaRevisionEliminar(View v){
        Intent ven=new Intent(this,SegundaRevision_Eliminar.class);
        startActivity(ven);
    }

    public void segundaRevisionActualizar(View v){
        Intent ven=new Intent(this,SegundaRevision_Actualizar.class);
        startActivity(ven);
    }

    public void segundaRevisionConsultar(View v){
        Intent ven=new Intent(this,SegundaRevision_Consultar.class);
        startActivity(ven);
    }
}
