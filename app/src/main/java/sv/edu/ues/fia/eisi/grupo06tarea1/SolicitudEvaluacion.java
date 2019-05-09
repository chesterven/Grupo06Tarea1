package sv.edu.ues.fia.eisi.grupo06tarea1;

public class SolicitudEvaluacion {
    private int idSolicitud;
    private int idEvaluacion;
    private float notaSoliEvaluacion;

    public SolicitudEvaluacion(int idSolicitud, int idEvaluacion, float notaSoliEvaluacion) {
        this.idSolicitud = idSolicitud;
        this.idEvaluacion = idEvaluacion;
        this.notaSoliEvaluacion = notaSoliEvaluacion;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public int getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(int idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public float getNotaSoliEvaluacion() {
        return notaSoliEvaluacion;
    }

    public void setNotaSoliEvaluacion(float notaSoliEvaluacion) {
        this.notaSoliEvaluacion = notaSoliEvaluacion;
    }
}
