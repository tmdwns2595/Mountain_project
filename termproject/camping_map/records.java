package org.jjcouple.termproject.camping_map;

import com.google.firebase.database.PropertyName;

public class records {
    private String camping_name;
    private String roadaddress;
    private String localaddress;
    private String camping_comb;
    private String camping_fee;
    private String camping_num;
    private String camping_safe;
    private String camping_time;

    public records(){}

    @PropertyName("camping_comb")
    public String getCamping_comb() {
        return camping_comb;
    }

    public void setCamping_comb(String camping_comb) {
        this.camping_comb = camping_comb;
    }
    @PropertyName("camping_fee")
    public String getCamping_fee() {
            return camping_fee;
    }

    public void setCamping_fee(String camping_fee) {
        this.camping_fee = camping_fee;
    }
    @PropertyName("camping_num")
    public String getCamping_num() {
        return camping_num;
    }

    public void setCamping_num(String camping_num) {
        this.camping_num = camping_num;
    }
    @PropertyName("camping_safe")
    public String getCamping_safe() {
        return camping_safe;
    }

    public void setCamping_safe(String camping_safe) {
        this.camping_safe = camping_safe;
    }
    @PropertyName("camping_time")
    public String getCamping_time() {
        return camping_time;
    }

    public void setCamping_time(String camping_time) {
        this.camping_time = camping_time;
    }

    @PropertyName("camping_name")
    public String getName() {
        return camping_name;
    }

    public void setName(String camping_name) {
        this.camping_name = camping_name;
    }

    @PropertyName("roadaddress")
    public String getAddress1() {
        return roadaddress;
    }

    public void setAddress1(String roadaddress) {
        this.roadaddress = roadaddress;
    }

    @PropertyName("localaddress")
    public String getAddress2() {
        return localaddress;
    }

    public void setAddress2(String localaddress) {
        this.localaddress = localaddress;
    }


}
