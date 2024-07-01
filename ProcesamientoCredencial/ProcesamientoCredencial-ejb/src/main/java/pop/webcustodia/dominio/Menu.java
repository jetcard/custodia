/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.dominio;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jyoverar
 */
public class Menu {

    private int cmenuId;
    private int cmenu;
    private int cmenuPadre;
    private String menuA;
    private String menuAUrl;
    private String menuAE;
    private String simbolo;
    private List<Menu> oMenuList = new ArrayList<Menu>();
    private List<Menu> oMenuList2 = new ArrayList<Menu>();
    private List<MenuEventos> listaEventos = new ArrayList<MenuEventos>();

   public Menu(){
       this.listaEventos = new LinkedList<>();
   }
    
    public int getCmenuId() {
        return cmenuId;
    }

    public void setCmenuId(int cmenuId) {
        this.cmenuId = cmenuId;
    }

    public int getCmenu() {
        return cmenu;
    }

    public void setCmenu(int cmenu) {
        this.cmenu = cmenu;
    }

    public int getCmenuPadre() {
        return cmenuPadre;
    }

    public void setCmenuPadre(int cmenuPadre) {
        this.cmenuPadre = cmenuPadre;
    }

    public String getMenuA() {
        return menuA;
    }

    public void setMenuA(String menuA) {
        this.menuA = menuA;
    }

    public String getMenuAUrl() {
        return menuAUrl;
    }

    public void setMenuAUrl(String menuAUrl) {
        this.menuAUrl = menuAUrl;
    }

    public String getMenuAE() {
        return menuAE;
    }

    public void setMenuAE(String menuAE) {
        this.menuAE = menuAE;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public List<Menu> getoMenuList() {
        return oMenuList;
    }

    public void setoMenuList(List<Menu> oMenuList) {
        this.oMenuList = oMenuList;
    }
    
     public List<Menu> getoMenuList2() {
        return oMenuList2;
    }

    public void setoMenuList2(List<Menu> oMenuList2) {
        this.oMenuList2 = oMenuList2;
    }
 
     public List<MenuEventos> getListaEventos() {
        return listaEventos;
    }

    public void setListaEventos(List<MenuEventos> obj) {
        this.listaEventos = obj;
    }    

}
