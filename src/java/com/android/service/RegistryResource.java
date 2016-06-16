package com.android.service;

import com.android.entity.Registry;
import com.android.repository.RegistryRepository;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("registry")
public class RegistryResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("todb")
    public void toDatabase(String reg_id) {
        System.out.println("TEST : " + reg_id);
        JsonObject jsonReg = Json.createReader(new StringReader(reg_id)).readObject();
        Registry reg_info = new Registry();
        reg_info.setRegistrationId(jsonReg.getString("reg_id"));

        RegistryRepository repo = new RegistryRepository();
        repo.persist(reg_info);
        repo.close();
        System.out.println("ID : " + reg_info.getId());
        System.out.println("RegistrationId : " + reg_info.getRegistrationId());
    }
}
