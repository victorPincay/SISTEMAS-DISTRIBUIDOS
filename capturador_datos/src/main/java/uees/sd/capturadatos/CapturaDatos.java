/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uees.sd.capturadatos;

import com.google.gson.Gson;
import java.util.Random;

/**
 *
 * @author ninoska
 */
public class CapturaDatos {
    private static final Gson gson = new Gson();
    private static final Random random = new Random();

    public static String GenerarFormulario() throws Exception {
        CensusForm form = new CensusForm();
        form.id = "id" + random.nextInt(100);
        form.nombre = "Nombre" + random.nextInt(100);
        form.apellido = "Apellido" + random.nextInt(100);
        form.edad = random.nextInt(100);
        form.genero = random.nextBoolean() ? "Masculino" : "Femenino";
        form.direccion = "Direccion" + random.nextInt(100);
        form.ciudad = "Ciudad" + random.nextInt(100);
        form.estado = "Estado" + random.nextInt(100);
        form.codigoPostal = "CP" + random.nextInt(10000);
        form.pais = "Pais" + random.nextInt(100);
        form.telefono = "Telefono" + random.nextInt(1000000);
        form.email = "email" + random.nextInt(100) + "@example.com";
        form.ocupacion = "Ocupacion" + random.nextInt(100);
        form.estadoCivil = random.nextBoolean() ? "Soltero" : "Casado";
        form.numeroDeHijos = random.nextInt(10);
        form.nivelEducativo = "Nivel" + random.nextInt(10);
        form.ingresoAnual = random.nextDouble() * 100000;
        form.tieneMascotas = random.nextBoolean();
        form.numeroDeMascotas = random.nextInt(10);
        form.tamanoHogar = random.nextInt(10);
        return gson.toJson(form);
    }
}
