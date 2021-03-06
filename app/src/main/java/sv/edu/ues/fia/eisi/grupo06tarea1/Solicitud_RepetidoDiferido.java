//Autor: Roberto Eliezer Ventura Dominguez
//Carnet:VD16006
//Grupo de trabajo: 06
package sv.edu.ues.fia.eisi.grupo06tarea1;

public class Solicitud_RepetidoDiferido {
    private int idSolicitud;
    private String carnet;
    private int idEvaluacion;
    private int idTipoSolicitud;
    private String motivoSolicitud;
    private boolean aprobado;

    public Solicitud_RepetidoDiferido(){

    }

    public Solicitud_RepetidoDiferido(int idSolicitud, String carnet, int idEvaluacion, int idTipoSolicitud, String motivoSolicitud, boolean aprobado) {
        this.idSolicitud = idSolicitud;
        this.carnet = carnet;
        this.idEvaluacion = idEvaluacion;
        this.idTipoSolicitud = idTipoSolicitud;
        this.motivoSolicitud = motivoSolicitud;
        this.aprobado = aprobado;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public int getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(int idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public int getIdTipoSolicitud() {
        return idTipoSolicitud;
    }

    public void setIdTipoSolicitud(int idTipoSolicitud) {
        this.idTipoSolicitud = idTipoSolicitud;
    }

    public String getMotivoSolicitud() {
        return motivoSolicitud;
    }

    public void setMotivoSolicitud(String motivoSolicitud) {
        this.motivoSolicitud = motivoSolicitud;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }
}
