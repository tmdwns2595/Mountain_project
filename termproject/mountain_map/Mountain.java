package org.jjcouple.termproject.mountain_map;

import com.google.firebase.database.PropertyName;

public class Mountain {
//    private String picture;
    private String mountain_id;
    private String mountain_address;

    public Mountain(){}

    @PropertyName("mountain_id")
    public String getName() {
        return mountain_id;
    }

    public void setName(String name) {
        this.mountain_id = mountain_id;
    }

    @PropertyName("mountain_address")
    public String getAddress() {
        return mountain_address;
    }

    public void setAddress(String address) {
        this.mountain_address = mountain_address;
    }
}

