//Autor: Christian Ariel Zelaya Tejada
//Carnet:ZT12002
//Grupo de trabajo: 06
package sv.edu.ues.fia.eisi.grupo06tarea1;

public class Evaluaciones {
    private int idEvaluacion;
    private int idTipoevaluacion;
    private int numGrupo;
    private String codMateria;
    private int idCiclo;
    private String fechaEvaluacion;
    private String nombreEvaluacion;

    public Evaluaciones(){

    }
    public Evaluaciones(int idEvaluacion, int idTipoevaluacion, int numGrupo,
                        String codMateria, int idCiclo, String fechaEvaluacion,
                        String nombreEvaluacion, String descripcion){
        this.idEvaluacion =  idEvaluacion;
        this.idTipoevaluacion = idEvaluacion;
        this.numGrupo = numGrupo;
        this.codMateria = codMateria;
        this.idCiclo = idCiclo;
        this.fechaEvaluacion = fechaEvaluacion;
        this.nombreEvaluacion = nombreEvaluacion;
        this.descripcion = descripcion;

    }

    public int getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(int idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public int getIdTipoevaluacion() {
        return idTipoevaluacion;
    }

    public void setIdTipoevaluacion(int idTipoevaluacion) {
        this.idTipoevaluacion = idTipoevaluacion;
    }

    public int getNumGrupo() {
        return numGrupo;
    }

    public void setNumGrupo(int numGrupo) {
        this.numGrupo = numGrupo;
    }

    public String getCodMateria() {
        return codMateria;
    }

    public void setCodMateria(String codMateria) {
        this.codMateria = codMateria;
    }

    public int getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(int idCiclo) {
        this.idCiclo = idCiclo;
    }

    public String getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setFechaEvaluacion(String fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }

    public String getNombreEvaluacion() {
        return nombreEvaluacion;
    }

    public void setNombreEvaluacion(String nombreEvaluacion) {
        this.nombreEvaluacion = nombreEvaluacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    private String descripcion;


}
