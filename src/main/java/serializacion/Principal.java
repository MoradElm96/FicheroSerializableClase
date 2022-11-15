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

    
    private static String asignatura= null;
    private static Double nota = null;
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        File f = new File("notasSerializable.dat");
        Curso curso =  null;
        
        int opcion = menu(sc);
        
        while(opcion!=4){
            switch(opcion){
                case 1:
                    crearFichero(f);
                    break;
                case 2:
                    escribir(f,sc,curso);
                    break;
                case 3:
                    leer(f);
                    break;
                default:
                    System.out.println("Operacion incorrecta");
                    
                    break;
            }
            opcion= menu(sc);        }
        
        
    }
    
    private static int menu(Scanner sc){
        int opcion;
        System.out.println("1)Crear Fichero");
        System.out.println("2)Añadir asignaturas y notas");
        System.out.println("3)Mostrar informacion");
        System.out.println("4)Salir");
        
        opcion = sc.nextInt();
        return opcion;
        
    }

    private static void crearFichero(File f) throws FileNotFoundException, IOException {

        if(f.exists()){
            System.out.println("El fichero ya esta creado");
            
        } else {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            System.out.println("Fichero creado");
            oos.close();
            fos.close();
            
        }
    }

    private static void escribir(File f, Scanner sc, Curso curso) throws FileNotFoundException, IOException {

        if(!f.exists())
            crearFichero(f);
        
        FileOutputStream fos = new FileOutputStream(f,true);
        ClaseOutput oos = new ClaseOutput(fos);
        
        System.out.println("Introduce asignatura");
        asignatura = sc.next();
        
        
        System.out.println("Introduce nota");
        nota = sc.nextDouble();
                
        
        
        curso = new Curso(asignatura, nota);
        oos.writeObject(curso);
        oos.close();
        fos.close();
        
        
        
        
    }

    private static void leer(File f) throws FileNotFoundException, IOException, ClassNotFoundException {

        Curso curso;
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        try{
            while(true){
                curso = (Curso) ois.readObject();
                System.out.println(curso.toString());
            }
        }catch(EOFException e){
            System.out.println("Fin del fichero");
        }
    }
    
}
