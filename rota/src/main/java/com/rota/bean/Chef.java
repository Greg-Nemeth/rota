package com.rota.bean;

import io.micronaut.data.annotation.Id;
import jakarta.inject.Singleton;

@Singleton
public class Chef {
    @Id
    private Long chef_id;

    
    private String f_name;

    
    private String l_name;

   
    private Float h_wage;

    
    private String contact_no; //format +447...

    // public Chef(Long chef_id, String f_name, String l_name, Float h_wage, String contact_no) {
    //     this.chef_id = chef_id;
    //     this.f_name = f_name;
    //     this.l_name = l_name;
    //     this.h_wage = h_wage;
    //     this.contact_no = contact_no;
    // }

    public Long getChef_id() {
        return chef_id;
    }

    public void setChef_id(Long chef_id) {
        this.chef_id = chef_id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public Float getH_wage() {
        return h_wage;
    }

    public void setH_wage(Float h_wage) {
        this.h_wage = h_wage;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }


}