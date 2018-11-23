package com.example.xero.cardtradeapp.Entities;
// Generated May 12, 2018 6:06:40 PM by Hibernate Tools 4.3.1



/**
 * Reporte generated by hbm2java
 */
public class Reporte implements java.io.Serializable {


     private Integer id;
     private Subasta subasta;
     private Reportetipo reportetipo;
     private String description;
     private String route;

    public Reporte() {
    }

    public Reporte(Subasta subasta, Reportetipo reportetipo, String description, String route) {
       this.subasta = subasta;
       this.reportetipo = reportetipo;
       this.description = description;
       this.route = route;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Subasta getSubasta() {
        return this.subasta;
    }
    
    public void setSubasta(Subasta subasta) {
        this.subasta = subasta;
    }
    public Reportetipo getReportetipo() {
        return this.reportetipo;
    }
    
    public void setReportetipo(Reportetipo reportetipo) {
        this.reportetipo = reportetipo;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public String getRoute() {
        return this.route;
    }
    
    public void setRoute(String route) {
        this.route = route;
    }




}


