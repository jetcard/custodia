/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;
import pop.webcustodia.conn.Conexion;
import pop.webcustodia.dominio.Correo;
import pop.webcustodia.dominio.Menu;
import pop.webcustodia.dominio.MenuEventos;
import pop.webcustodia.dominio.Rol;
import pop.webcustodia.dominio.Telefono;
import pop.webcustodia.dominio.Usuario;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbCredencial")
public class SessionCredencial {
    public boolean validarUsuario(Usuario oUsuario) throws Exception {
        System.out.println("     <i> validarUsuario ");
        boolean rpta = false;

        OracleConnection cn = null;
        Conexion conex = new Conexion();
        cn = conex.ConexionOpen("segur");
        
        String sp = "{call PKG_USUARIO.SP_VERI_USUARIO(?,?,?,?,?,?)}";
        OracleCallableStatement ocs = (OracleCallableStatement) cn.prepareCall(sp);
        ocs.setInt(1, 3);
        ocs.setString(2, oUsuario.getUsuarioId().trim());

        String contrasenia = oUsuario.getContrasenia().trim();
        SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
        byte[] digest = digestSHA3.digest(contrasenia.getBytes());
        System.out.println("     ["+oUsuario.getUsuarioId().trim()+"] " + " SHA3-512 = " + Hex.toHexString(digest));

        ocs.setString(3, Hex.toHexString(digest));
        ocs.registerOutParameter(4, OracleTypes.INTEGER);
        ocs.registerOutParameter(5, OracleTypes.VARCHAR);
        ocs.registerOutParameter(6, OracleTypes.VARCHAR);
  
        java.util.Date dini = new java.util.Date();
        System.out.println("PKG_USUARIO_2.SP_VERI_USUARIO.ini");
        ocs.execute();
        java.util.Date dfin = new java.util.Date();
        long diferencia = ( dini.getTime() - dfin.getTime() );//MILLSECS_PER_DAY; 
              System.out.println("este es "+ocs.getObject(4));
        System.out.println("PKG_USUARIO_2.SP_VERI_USUARIO.fin:"+diferencia);
        

        if ((int) ocs.getObject(4) > 0) {
            rpta = true;
        }
        cn.close();
        System.out.println("     <f> validarUsuario " + rpta);
        return rpta;
    }

    public Usuario obtenerUsuario(Usuario oUsuario) throws Exception {
        System.out.println("   <i> obtenerUsuario ");
        Usuario newUsuario = oUsuario;
        newUsuario.setEstado(false);
        newUsuario.setEstado(validarUsuario(newUsuario));
        
         System.out.println("   <i> obtenerUsuarioPASOPOR ACA ");
        if (newUsuario.isEstado()) {
            OracleConnection cn = null;
            Conexion conex = new Conexion();
            
            cn = conex.ConexionOpen("segur");
            String sp = "{call PKG_USUARIO.SP_BUS_USUARIO(?,?,?,?,?,?)}";
            OracleCallableStatement ocs = (OracleCallableStatement) cn.prepareCall(sp);
            ocs.setInt(1,3 );    
            ocs.setString(2, oUsuario.getUsuarioId().trim());    
            String contrasenia = oUsuario.getContrasenia().trim();
            SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
            byte[] digest = digestSHA3.digest(contrasenia.getBytes());
            System.out.println("   ["+oUsuario.getUsuarioId().trim()+"] " + " SHA3-512 = " + Hex.toHexString(digest));

            ocs.setString(3, Hex.toHexString(digest));
            ocs.registerOutParameter(4, OracleTypes.CURSOR);
            ocs.registerOutParameter(5, OracleTypes.VARCHAR);
            ocs.registerOutParameter(6, OracleTypes.VARCHAR);
            
            java.util.Date dini = new java.util.Date();
            System.out.println("PKG_USUARIO_2.SP_BUS_USUARIO.ini");
            ocs.execute();
            java.util.Date dfin = new java.util.Date();
            long diferencia = ( dini.getTime() - dfin.getTime() );//MILLSECS_PER_DAY; 
            System.out.println("PKG_USUARIO_2.SP_BUS_USUARIO.fin:"+diferencia);
            
            OracleResultSet rs = (OracleResultSet) ocs.getObject(4);

            while (rs.next()) {
                newUsuario.setUsuarioId(rs.getString("C_USUARIO_ID"));
                newUsuario.setPerId(rs.getInt("C_PERSONA_ID"));
                newUsuario.setPerNom(rs.getString("D_NOMBRES"));
                newUsuario.setPerApePat(rs.getString("D_APE_PAT"));
                newUsuario.setPerApeMat(rs.getString("D_APE_MAT"));
                newUsuario.setBcontrasenia(rs.getString("B_CONTRASENIA"));
                Rol newRol = new Rol();
                newRol.setRolId(rs.getInt("C_ROL_ID"));
                newRol.setRolNombre(rs.getString("D_NOMBRE"));
                newRol.setRolUsuarioId(rs.getInt("C_USUARIO_ROL_ID"));
                newUsuario.setRol(newRol);
                List<Menu> maeMenus = obtenerMenu(oUsuario);
                newUsuario.setMenu(maeMenus);
                List<Menu> maeOpcionesPermisosMenus= obtenerOpcionesPermisosMenu(oUsuario);
                newUsuario.setOpcionesPermisosmenu(maeOpcionesPermisosMenus);
            }
            newUsuario.setMensaje("ok.");
            cn.close();
        } else {
            newUsuario.setMensaje("Error al ingresar los datos, por favor verificar.");
        }
        System.out.println("   <f> obtenerUsuario ");
        return newUsuario;
    }

    private List<Menu> obtenerMenu(Usuario oUsuario) throws Exception {
        List<Menu> menuList = new ArrayList<>();
        OracleConnection cn = null;
        Conexion conex = new Conexion();
        cn = conex.ConexionOpen("segur");
        String sp = "{call PKG_USUARIO.SP_BUS_USU_MENU(?,?,?,?,?)}";
        OracleCallableStatement ocs = (OracleCallableStatement) cn.prepareCall(sp);
        ocs.setInt(1, 3);
        ocs.setString(2, oUsuario.getUsuarioId().trim());
        ocs.registerOutParameter(3, OracleTypes.CURSOR);
        ocs.registerOutParameter(4, OracleTypes.VARCHAR);
        ocs.registerOutParameter(5, OracleTypes.VARCHAR);
        
        java.util.Date dini = new java.util.Date();
        System.out.println("PKG_USUARIO_2.SP_BUS_USU_MENU.ini");
        ocs.execute();
        java.util.Date dfin = new java.util.Date();
        long diferencia = ( dini.getTime() - dfin.getTime() );//MILLSECS_PER_DAY; 
        System.out.println("PKG_USUARIO_2.SP_BUS_USU_MENU:"+diferencia);
        
        OracleResultSet rs = (OracleResultSet) ocs.getObject(3);

        List<Menu> menuListEvaluar = new ArrayList<>();
        while (rs.next()) {
            Menu newMenu = new Menu();
            newMenu.setCmenuId(rs.getInt("C_MENU_ID"));
            newMenu.setCmenu(rs.getInt("C_MENU"));
            newMenu.setCmenuPadre(rs.getInt("C_MENU_PADRE"));
            newMenu.setMenuA(rs.getString("D_NOMBRE"));
            newMenu.setMenuAUrl(rs.getString("D_URL"));
            newMenu.setSimbolo(rs.getString("D_SIMBOLO"));
            newMenu.setMenuAE("none");
            menuListEvaluar.add(newMenu);
        }

        for (Menu oMaeMenu : menuListEvaluar) {
            if (oMaeMenu.getCmenuPadre() == 0) {
                Menu newMenu = oMaeMenu;
                newMenu.setoMenuList(buscaHijos(menuListEvaluar, newMenu.getCmenu()));
                newMenu.setoMenuList2(buscaHijos2(menuListEvaluar, newMenu.getCmenu()));
                menuList.add(newMenu);
            }
        }
       
        cn.close();
        return menuList;
    }
    
  
    private List<Menu> buscaHijos(List<Menu> list, int padre) {
        List<Menu> menuListEvaluar = new ArrayList<>();
        for (Menu maeMenu : list) {
            if (maeMenu.getCmenuPadre() == padre) {
                menuListEvaluar.add(maeMenu);
            }
        }
        return menuListEvaluar;
    }
    
    
    private List<Menu> buscaHijos2(List<Menu> list, int padre) {
        List<Menu> menuListEvaluar = new ArrayList<>();
        List<Menu> menuListEvaluar2 = new ArrayList<>();
     
        for (Menu maeMenu : list) {
            if (maeMenu.getCmenuPadre() == padre) {
                menuListEvaluar.add(maeMenu);
            }
        }
        int x=0;
      
        for (Menu omaeMenu : menuListEvaluar) {
            if (omaeMenu.getCmenuPadre() == padre ) {
                Menu rnewMenu = omaeMenu;
                for (Menu maeMenu : list) {
          
                    System.out.println("maeMenu.getCmenuPadre() == rnewMenu.getCmenu() "+ maeMenu.getCmenuPadre() +"=="+ rnewMenu.getCmenu());


                   if (maeMenu.getCmenuPadre() == rnewMenu.getCmenu()  ) {
                       
                         menuListEvaluar2.add(maeMenu);
                     }
                    x=x+1;
                 }
              
              }
        }
        
        return menuListEvaluar2;
    
    }
    
    
    public Usuario cambiarUsuario(Usuario oUsuario) throws Exception {
        System.out.println("   <i> cambiarContrasenia ");
        boolean rpta = false;
        Usuario newUsuario = oUsuario;
        newUsuario.setEstado(false);
        newUsuario.setEstado(validarUsuario(newUsuario));
        if (newUsuario.isEstado()) {
            OracleConnection cn = null;
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen("segur");
            String sp = "{call PKG_USUARIO.SP_UPD_USUARIO_CONTRA(?,?,?,?,?)}";
            OracleCallableStatement ocs = (OracleCallableStatement) cn.prepareCall(sp);
            ocs.setInt(1, 3);
            ocs.setString(2, oUsuario.getUsuarioId().trim());

            String contrasenia = oUsuario.getContraseniaB().trim();
            SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
            byte[] digest = digestSHA3.digest(contrasenia.getBytes());
            System.out.println("   ["+oUsuario.getUsuarioId().trim()+"] " + " SHA3-512 = " + Hex.toHexString(digest));
            ocs.setString(3, Hex.toHexString(digest));
            ocs.registerOutParameter(4, OracleTypes.VARCHAR);
            ocs.registerOutParameter(5, OracleTypes.VARCHAR);
            
            java.util.Date dini = new java.util.Date();
            System.out.println("PKG_USUARIO_2.SP_UPD_USUARIO_CONTRA.ini");
            ocs.execute();
            java.util.Date dfin = new java.util.Date();
            long diferencia = ( dini.getTime() - dfin.getTime() );//MILLSECS_PER_DAY; 
            System.out.println("PKG_USUARIO_2.SP_UPD_USUARIO_CONTRA.fin:"+diferencia);
            
            if (ocs.getString(4).equals("0")){
                rpta = true;
            }
            newUsuario.setEstado(rpta);
        }
                
        System.out.println("   <f> cambiarContrasenia ");
        
        return newUsuario;
    }

    //LISTARA TODAS LAS OPCIONES QUE TIENE PERMISO UN USUARIO, NO NECESARIAMENTE TIENEN QUE MOSTRARSE 
    //EN EL MENU DEL EVA
    private List<Menu> obtenerOpcionesPermisosMenu(Usuario oUsuario) throws Exception {
        List<Menu> menuList = new ArrayList<>();
        OracleConnection cn = null;
        Conexion conex = new Conexion();
        cn = conex.ConexionOpen("segur");
        String sp = "{call PKG_USUARIO.SP_OPCS_PERMIS_USU(?,?,?,?,?)}";
        OracleCallableStatement ocs = (OracleCallableStatement) cn.prepareCall(sp);
        ocs.setInt(1, 3);
        ocs.setString(2, oUsuario.getUsuarioId().trim());
        ocs.registerOutParameter(3, OracleTypes.CURSOR);
        ocs.registerOutParameter(4, OracleTypes.VARCHAR);
        ocs.registerOutParameter(5, OracleTypes.VARCHAR);
        
        java.util.Date dini = new java.util.Date();
        System.out.println("PKG_USUARIO_2.SP_OPCS_PERMIS_USU.ini");
        ocs.execute();
        java.util.Date dfin = new java.util.Date();
        long diferencia = ( dini.getTime() - dfin.getTime() );//MILLSECS_PER_DAY; 
        System.out.println("PKG_USUARIO_2.SP_OPCS_PERMIS_USU.fin:"+diferencia);

        
        OracleResultSet rs = (OracleResultSet) ocs.getObject(3);

        List<Menu> menuListEvaluar = new ArrayList<>();
        while (rs.next()) {
            Menu newMenu = new Menu();
            newMenu.setCmenuId(rs.getInt("C_MENU_ID"));
            newMenu.setCmenu(rs.getInt("C_MENU"));
            newMenu.setCmenuPadre(rs.getInt("C_MENU_PADRE"));
            newMenu.setMenuA(rs.getString("D_NOMBRE"));
            newMenu.setMenuAUrl(rs.getString("D_URL"));
            newMenu.setSimbolo(rs.getString("D_SIMBOLO"));
            newMenu.setMenuAE("none");
            menuListEvaluar.add(newMenu);
        }

        for (Menu oMaeMenu : menuListEvaluar) {
            if (oMaeMenu.getCmenuPadre() == 0) {
                Menu newMenu = oMaeMenu;
                newMenu.setoMenuList(buscaHijos(menuListEvaluar, newMenu.getCmenu()));
                newMenu.setoMenuList2(buscaHijos2(menuListEvaluar, newMenu.getCmenu()));
                menuList.add(newMenu);
            }
        }
        
        cn.close();
        
        obtenerMenuEventos(oUsuario, menuList);
        
        System.out.println("opciones:"+menuList.size());
        return menuList;
    }   
    
    private void obtenerMenuEventos(Usuario oUsuario, List<Menu> listaMenu) throws Exception {
        
        OracleConnection cn = null;
        Conexion conex = new Conexion();
        cn = conex.ConexionOpen("segur");
        String sp = "{call PKG_USUARIO.SP_EVENTOS_MENU(?,?,?,?,?)}";
        OracleCallableStatement ocs = (OracleCallableStatement) cn.prepareCall(sp);
        ocs.setInt(1, 3);
        ocs.setString(2, oUsuario.getUsuarioId().trim());
        ocs.registerOutParameter(3, OracleTypes.CURSOR);
        ocs.registerOutParameter(4, OracleTypes.VARCHAR);
        ocs.registerOutParameter(5, OracleTypes.VARCHAR);
        
        java.util.Date dini = new java.util.Date();
        System.out.println("PKG_USUARIO_2.SP_EVENTOS_MENU.ini");
        ocs.execute();
        java.util.Date dfin = new java.util.Date();
        long diferencia = ( dini.getTime() - dfin.getTime() );//MILLSECS_PER_DAY; 
        System.out.println("PKG_USUARIO_2.SP_EVENTOS_MENU.fin: "+diferencia);

        
        OracleResultSet rs = (OracleResultSet) ocs.getObject(3);

       
        while (rs.next()) {
            
            System.out.println("eventos1: "+rs.getInt("C_MENU_ID"));
            
            for( Menu padre : listaMenu){
                for( Menu oMenu : padre.getoMenuList2()){
                    if (oMenu.getCmenuId() == rs.getInt("C_MENU_ID")){

                        MenuEventos eventos = new MenuEventos();                 
                        eventos.setCmenuId(rs.getInt("C_MENU_ID"));
                        eventos.setCmenu(rs.getInt("C_MENU"));
                        eventos.setMenuA(rs.getString("D_NOMBRE"));
                        eventos.setMenuAUrl(rs.getString("D_URL"));


                        oMenu.getListaEventos().add(eventos);
                        break;
                    }
                }
            }
        }
     
        
        cn.close();
        
        System.out.println("eventos2: fin");
        
    }
    
}
