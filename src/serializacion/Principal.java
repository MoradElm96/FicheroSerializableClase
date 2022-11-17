/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package serializacion;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 *
 * @author alumnotd
 */
public class Principal {

    private static String asignatura = null;
    private static Double nota = null;

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        File f = new File("notasSerializable.obj");

        int opcion = menu(sc);

        while (opcion != 5) {
            switch (opcion) {
                case 1:
                    crearFichero(f);
                    break;
                case 2:
                    escribir(f, sc);
                    break;
                case 3:
                    leer(f);
                    break;
                case 4:
                    notaMedia(f);
                    break;

                default:
                    System.out.println("Operacion incorrecta");

                    break;
            }
            opcion = menu(sc);
        }

    }

    private static int menu(Scanner sc) {
        int opcion;
        System.out.println("1)Crear Fichero");
        System.out.println("2)Añadir asignaturas y notas");
        System.out.println("3)Mostrar informacion");
        System.out.println("4)Nota Media");
        System.out.println("5)Salir");

        opcion = sc.nextInt();
        return opcion;

    }

    private static void crearFichero(File f) throws FileNotFoundException, IOException {

        if (f.exists()) {
            System.out.println("El fichero ya esta creado");

        } else {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            System.out.println("Fichero creado");
            oos.close();
            fos.close();

        }
    }

    private static void escribir(File f, Scanner sc) throws FileNotFoundException, IOException {

        if (!f.exists()) {
            crearFichero(f);
        }

        FileOutputStream fos = new FileOutputStream(f, true);
        ClaseOutput oos = new ClaseOutput(fos);

        asignatura = "Programacion";
        System.out.println("asignatura : " + asignatura);
        introducirNota(sc);
        Curso c = new Curso(asignatura, nota);
        oos.writeObject(c);

        asignatura = "Lenguaje de marcas";
        System.out.println("asignatura : " + asignatura);
        introducirNota(sc);
        Curso c1 = new Curso(asignatura, nota);
        oos.writeObject(c1);

        asignatura = "Bases de datos";
        System.out.println("asignatura : " + asignatura);
        introducirNota(sc);
        Curso c2 = new Curso(asignatura, nota);
        oos.writeObject(c2);

        asignatura = "Entornos de desarrollo";
        System.out.println("asignatura : " + asignatura);
        introducirNota(sc);
        Curso c3 = new Curso(asignatura, nota);
        oos.writeObject(c3);

        asignatura = "sistemas informaticos";
        System.out.println("asignatura : " + asignatura);
        introducirNota(sc);
        Curso c4 = new Curso(asignatura, nota);
        oos.writeObject(c4);

        asignatura = "Fol";
        System.out.println("asignatura : " + asignatura);
        introducirNota(sc);
        Curso c5 = new Curso(asignatura, nota);
        oos.writeObject(c5);

        oos.close();
        fos.close();

    }

    public static double introducirNota(Scanner sc) {
        System.out.println("Introduce nota");

        nota = sc.nextDouble();

        while ((nota < 0) || (nota > 10)) {

            if ((nota < 0) || (nota > 10)) {
                System.out.println("introduce nota correcta");

            }
            nota = sc.nextDouble();
        }
        return nota;
    }

    private static void leer(File f) throws FileNotFoundException, IOException, ClassNotFoundException {

        Curso curso;
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);

        try {
            while (true) {
                curso = (Curso) ois.readObject();
                System.out.println(curso.toString());
            }
        } catch (EOFException e) {
            System.out.println("Fin del fichero");
        }
    }

    private static void notaMedia(File f) throws FileNotFoundException, IOException, ClassNotFoundException {

        Curso c;
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);

        Double total=0.0;
        Double media = 0.0;
        try {
            while (true) {
                c = (Curso) ois.readObject();
                media = media + c.getNota();

            }
        } catch (EOFException e) {
            System.out.println("Fin del fichero");
        }

        total = media / 6;
        System.out.println("Nota Media : " + total);
    }

}
