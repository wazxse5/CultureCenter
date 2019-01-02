package model;

import java.io.Serializable;

public class Branch implements Serializable {
    private static final long serialVersionUID = 364829882076787507L;
    private int idBranch;
    private String name;
    private String address;
    private String phone;

    public Branch(int idBranch, String name, String address, String phone) {
        this.idBranch = idBranch;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
}
