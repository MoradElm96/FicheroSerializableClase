
package serializacion;
import java.io.Serializable;
/**
 *
 * @author alumnotd
 */
public class Curso implements  Serializable{
    
    private static final long serialVersionUID = 1L;
    String asignatura;
    Double nota;

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Curso(String asignatura, Double nota) {
        this.asignatura = asignatura;
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Curso{" + "asignatura=" + asignatura + ", nota=" + nota + '}';
    }
    
    
    
    
}
