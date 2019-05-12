//Autor: Maria Abigail Gil Cordova
//Carnet:GC16001
//Grupo de trabajo: 06

package sv.edu.ues.fia.eisi.grupo06tarea1;

import java.sql.Date;

public class SegundaRevision {
    private int id_Segunda_Revision;
    private int idEvaluacion;
    private int idLocal;
    private Date fechaSegundaRevision;
    private String descripcion;

    public SegundaRevision(int id_Segunda_Revision, int idEvaluacion, int idLocal, Date fechaSegundaRevision) {
        this.id_Segunda_Revision = id_Segunda_Revision;
        this.idEvaluacion = idEvaluacion;
        this.idLocal = idLocal;
        this.fechaSegundaRevision = fechaSegundaRevision;
    }

    public int getId_Segunda_Revision() {
        return id_Segunda_Revision;
    }

    public void setId_Segunda_Revision(int id_Segunda_Revision) {
        this.id_Segunda_Revision = id_Segunda_Revision;
    }

    public int getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(int idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public Date getFechaSegundaRevision() {
        return fechaSegundaRevision;
    }

    public void setFechaSegundaRevision(Date fechaSegundaRevision) {
        this.fechaSegundaRevision = fechaSegundaRevision;
    }
}
