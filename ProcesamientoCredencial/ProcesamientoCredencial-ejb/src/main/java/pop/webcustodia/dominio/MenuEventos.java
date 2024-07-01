/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.dominio;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jyoverar
 */
public class MenuEventos {
     
    private int cmenuId;
    private int cmenu;
    private String menuA;
    private String menuAUrl;
    
    private Menu menu;
    
    
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
    public Menu getMenus(){
        return menu;
    }
    public void setMenus(Menu obj){
        this.menu = obj;
    }
    
}
