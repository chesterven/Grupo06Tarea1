package sv.edu.ues.fia.eisi.grupo06tarea1;

public class DetallePrimeraRevision {

    private int idPrimeraRevision;
    private int idSolicitudPrimerRevision;
    private boolean asitenciaPrimerRevision;
    private float notaOriginal;
    private float notaNueva;
    private String motivoCambio;

    public DetallePrimeraRevision() {
    }

    public DetallePrimeraRevision(int idPrimeraRevision, int idSolicitudPrimerRevision, boolean asitenciaPrimerRevision, float notaOriginal, float notaNueva, String motivoCambio) {
        this.idPrimeraRevision = idPrimeraRevision;
        this.idSolicitudPrimerRevision = idSolicitudPrimerRevision;
        this.asitenciaPrimerRevision = asitenciaPrimerRevision;
        this.notaOriginal = notaOriginal;
        this.notaNueva = notaNueva;
        this.motivoCambio = motivoCambio;
    }

    public int getIdPrimeraRevision() {
        return idPrimeraRevision;
    }

    public void setIdPrimeraRevision(int idPrimeraRevision) {
        this.idPrimeraRevision = idPrimeraRevision;
    }

    public int getIdSolicitudPrimerRevision() {
        return idSolicitudPrimerRevision;
    }

    public void setIdSolicitudPrimerRevision(int idSolicitudPrimerRevision) {
        this.idSolicitudPrimerRevision = idSolicitudPrimerRevision;
    }

    public boolean isAsitenciaPrimerRevision() {
        return asitenciaPrimerRevision;
    }

    public void setAsitenciaPrimerRevision(boolean asitenciaPrimerRevision) {
        this.asitenciaPrimerRevision = asitenciaPrimerRevision;
    }

    public float getNotaOriginal() {
        return notaOriginal;
    }

    public void setNotaOriginal(float notaOriginal) {
        this.notaOriginal = notaOriginal;
    }

    public float getNotaNueva() {
        return notaNueva;
    }

    public void setNotaNueva(float notaNueva) {
        this.notaNueva = notaNueva;
    }

    public String getMotivoCambio() {
        return motivoCambio;
    }

    public void setMotivoCambio(String motivoCambio) {
        this.motivoCambio = motivoCambio;
    }
}
