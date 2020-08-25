package com.example.sajalassigmentone;

public class Student {
     public String reg,name,phn;
     public double cgp;

     public  Student() {}


    public Student(String reg, String name, String phn, double cgp) {
        this.reg = reg;
        this.name = name;
        this.phn = phn;
        this.cgp = cgp;
    }

    public String getReg() {
        return reg;
    }

    public String getName() {
        return name;
    }

    public String getPhn() {
        return phn;
    }

    public double getCgp() {
        return cgp;
    }
}
