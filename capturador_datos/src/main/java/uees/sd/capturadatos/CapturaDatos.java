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
        form.SetName("Name" + random.nextInt(100));
        form.SetAge(random.nextInt(100));
        form.SetAddress("Address" + random.nextInt(100));
        // ... otros campos

        return gson.toJson(form);
    }
}

class CensusForm {
    private String name;
    private int age;
    private String address;
    
    public void SetName(String name){
        this.name=name;
    }
    public void SetAge(int age){
        this.age=age;
    }
    public void SetAddress(String address){
        this.address=address;
    }
}
