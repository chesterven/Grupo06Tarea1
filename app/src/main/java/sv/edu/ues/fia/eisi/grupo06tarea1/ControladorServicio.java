package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ControladorServicio {
    RequestQueue requestQueue;
    String mensaje="";
    Solicitud_RepetidoDiferido soli = new Solicitud_RepetidoDiferido();


    public String ejecutarServicio(String URL, Solicitud_RepetidoDiferido solicitud, Context context) {
        soli = solicitud;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mensaje="Guardado";
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                mensaje="No guardado";
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("idEvaluacion", String.valueOf(soli.getIdEvaluacion()));
                parametros.put("carnet", soli.getCarnet());
                parametros.put("motivo", soli.getMotivoSolicitud());
                parametros.put("aprobado", String.valueOf(soli.isAprobado()));
                parametros.put("idTipoSolicitud", String.valueOf(soli.getIdTipoSolicitud()));

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return mensaje;
    }
}
