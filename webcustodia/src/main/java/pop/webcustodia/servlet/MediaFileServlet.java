/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcustodia.servlet;

import java.io.File;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import org.omnifaces.servlet.FileServlet;

/**
 *
 * @author Jyoverar
 */
@WebServlet("/media/*")
public class MediaFileServlet extends FileServlet  {

    private File folder;

    @Override
    public void init() throws ServletException {
        folder = new File("//192.168.70.15/public"); 
    }

   
    @Override
    protected File getFile(HttpServletRequest hsr) throws IllegalArgumentException {
       String pathInfo = hsr.getPathInfo();

        if (pathInfo == null || pathInfo.isEmpty() || "/".equals(pathInfo)) {
            throw new IllegalArgumentException();
        }
        return new File(folder, pathInfo);
    }
}
