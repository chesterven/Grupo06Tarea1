//Autor: Maria Abigail Gil Cordova
//Carnet:GC16001
//Grupo de trabajo: 06

package sv.edu.ues.fia.eisi.grupo06tarea1;

import java.sql.Date;

public class SegundaRevision {
    private int id_Segunda_Revision;
    private int idEvaluacion;
    private int idLocal;
    private String fechaSegundaRevision;
    private String descripcion;

    public SegundaRevision() {

    }

    public SegundaRevision(int id_Segunda_Revision, int idEvaluacion, int idLocal, String fechaSegundaRevision, String descripcion) {
        this.id_Segunda_Revision = id_Segunda_Revision;
        this.idEvaluacion = idEvaluacion;
        this.idLocal = idLocal;
        this.fechaSegundaRevision = fechaSegundaRevision;
        this.descripcion = descripcion;
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

    public String getFechaSegundaRevision() {
        return fechaSegundaRevision;
    }

    public void setFechaSegundaRevision(String fechaSegundaRevision) {
        this.fechaSegundaRevision = fechaSegundaRevision;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
