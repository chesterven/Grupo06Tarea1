//Autor: Maria Abigail Gil Cordova
//Carnet:GC16001
//Grupo de trabajo: 06

package sv.edu.ues.fia.eisi.grupo06tarea1;

public class DetalleSegundaRevision {
    private int id_Segunda_Revision;
    private int idSoliSegundaRevision;
    private boolean asitencia_SegRevision;
    private float nota_SegRevision;

    public DetalleSegundaRevision() {
    }

    public DetalleSegundaRevision(int id_Segunda_Revision, int idSoliSegundaRevision, boolean asitencia_SegRevision, float nota_SegRevision) {
        this.id_Segunda_Revision = id_Segunda_Revision;
        this.idSoliSegundaRevision = idSoliSegundaRevision;
        this.asitencia_SegRevision = asitencia_SegRevision;
        this.nota_SegRevision = nota_SegRevision;
    }



    public int getId_Segunda_Revision() {
        return id_Segunda_Revision;
    }

    public void setId_Segunda_Revision(int id_Segunda_Revision) {
        this.id_Segunda_Revision = id_Segunda_Revision;
    }

    public int getIdSoliSegundaRevision() {
        return idSoliSegundaRevision;
    }

    public void setIdSoliSegundaRevision(int idSoliSegundaRevision) {
        this.idSoliSegundaRevision = idSoliSegundaRevision;
    }

    public boolean isAsitencia_SegRevision() {
        return asitencia_SegRevision;
    }

    public void setAsitencia_SegRevision(boolean asitencia_SegRevision) {
        this.asitencia_SegRevision = asitencia_SegRevision;
    }

    public float getNota_SegRevision() {
        return nota_SegRevision;
    }

    public void setNota_SegRevision(float nota_SegRevision) {
        this.nota_SegRevision = nota_SegRevision;
    }
}
