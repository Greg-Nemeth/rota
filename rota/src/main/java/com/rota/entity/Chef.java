package com.rota.entity;

import io.micronaut.data.annotation.GeneratedValue;
import static io.micronaut.data.annotation.GeneratedValue.Type.AUTO;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import java.util.Arrays;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;

@MappedEntity
public class Chef {

    @Id
    @GeneratedValue(AUTO)
    private Long chef_id;

    @NotNull
    private String f_name;

    @NotNull
    private String l_name;

    @NotNull
    private Float h_wage;

    @NotNull
    private String contact_no; //format +447...

    public Chef(Long chef_id, String f_name, String l_name, Float h_wage, String contact_no) {
        this.chef_id = chef_id;
        this.f_name = f_name;
        this.l_name = l_name;
        this.h_wage = h_wage;
        this.contact_no = contact_no;
    }

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


    
    @Override
    public String toString() {
        
        List<String> attributes = Arrays.asList("chef_Id","First Name", "Last Name",  "Hourly wage","Contact no");
        List<Object> valList = Arrays.asList(this.chef_id,this.f_name,this.l_name,this.h_wage,this.contact_no);
        
        String h_border = StringUtils.repeat("-", 35)+"\n";
        
        String filling = attributes.stream()
            .map(x-> {String temp = "|  "+x+" : " + valList.get(attributes.indexOf(x)).toString();
                        String padding = StringUtils.repeat(" ", 34-temp.length()) + "|";
                        String rtrn = temp+padding+"\n";
                        return rtrn;})
            .reduce("",String::concat);
        
        String resy = h_border+ filling + h_border;
        return resy;
    }

}