/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.iface;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jhon Yovera
 */
public interface IMaeDashboardDao {
   ArrayList<List<Object>> fetchAll();
   
   Boolean loadDataFPH();
   Boolean loadDataFEM();
   Boolean loadDataFMY();
   Boolean loadDataFPO();
   
}
