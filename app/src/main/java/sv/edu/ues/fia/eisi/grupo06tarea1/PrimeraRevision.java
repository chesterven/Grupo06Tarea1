package sv.edu.ues.fia.eisi.grupo06tarea1;

public class PrimeraRevision {

    private int idPrimeraRevision;
    private int idEvaluacion;
    private int idLocal;
    private String fechaPrimeraRevision;
    private String descripcion;
    private  String hora;

    public PrimeraRevision(int idPrimeraRevision, int idEvaluacion, int idLocal, String fechaPrimeraRevision, String descripcion, String hora) {
        this.idPrimeraRevision = idPrimeraRevision;
        this.idEvaluacion = idEvaluacion;
        this.idLocal = idLocal;
        this.fechaPrimeraRevision = fechaPrimeraRevision;
        this.descripcion = descripcion;
        this.hora = hora;
    }

    public PrimeraRevision() {
    }

    public int getIdPrimeraRevision() {
        return idPrimeraRevision;
    }

    public void setIdPrimeraRevision(int idPrimeraRevision) {
        this.idPrimeraRevision = idPrimeraRevision;
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

    public String getFechaPrimeraRevision() {
        return fechaPrimeraRevision;
    }

    public void setFechaPrimeraRevision(String fechaPrimeraRevision) {
        this.fechaPrimeraRevision = fechaPrimeraRevision;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
