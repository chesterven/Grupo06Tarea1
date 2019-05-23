package sv.edu.ues.fia.eisi.grupo06tarea1;

public class SolicitudSegundaRevision {
    private int idSoliSegundaRevision;
    private int idEvaluacion;
    private String carnet;
    private String motivo;
    private int idPrimerRevision;
    private int idSoliPrimerRevision;
    private boolean aprobado;


    public SolicitudSegundaRevision() {
    }

    public SolicitudSegundaRevision(int idSoliSegundaRevision, int idEvaluacion, String carnet, int idPrimerRevision, int idSoliPrimerRevision, boolean aprobado) {
        this.idSoliSegundaRevision = idSoliSegundaRevision;
        this.idEvaluacion = idEvaluacion;
        this.carnet = carnet;
        this.idPrimerRevision = idPrimerRevision;
        this.idSoliPrimerRevision = idSoliPrimerRevision;
        this.aprobado = aprobado;
    }

    public int getIdSoliSegundaRevision() {
        return idSoliSegundaRevision;
    }

    public void setIdSoliSegundaRevision(int idSoliSegundaRevision) {
        this.idSoliSegundaRevision = idSoliSegundaRevision;
    }

    public int getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(int idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getIdPrimerRevision() {
        return idPrimerRevision;
    }

    public void setIdPrimerRevision(int idPrimerRevision) {
        this.idPrimerRevision = idPrimerRevision;
    }

    public int getIdSoliPrimerRevision() {
        return idSoliPrimerRevision;
    }

    public void setIdSoliPrimerRevision(int idSoliPrimerRevision) {
        this.idSoliPrimerRevision = idSoliPrimerRevision;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }
}
