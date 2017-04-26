/*
 * LoginInterceptor.java
 *
 * Created on September 8, 2009, 6:20 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.general;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;

/**
 *
 * @author miracle
 */
    public class LoginInterceptor extends AbstractInterceptor implements StrutsStatics{
    
    /** Creates a new instance of LoginInterceptor */
    public LoginInterceptor() {
    }
    
   public String intercept(ActionInvocation actionInvocation) throws Exception {
        ActionContext context = actionInvocation.getInvocationContext();
        HttpServletRequest request= (HttpServletRequest) context.get(HTTP_REQUEST);
        HttpSession session =  request.getSession(false);
      //  if(session==null) {
           // return "sessionExpire";
       // }
        // System.out.println("session-->"+session);
        //new
        Object sessionvar=ServletActionContext.getServletContext().getAttribute("sessionvar");
       // System.out.println("sessionvar-->"+sessionvar);
        if(sessionvar !=null)
        {
            //System.out.println("sessionvar-->"+sessionvar);
            
        if(session==null &&sessionvar.equals("E")) {
            return "Emp_SessionExpire";
        }
         if(session==null &&sessionvar.equals("C")) {
            return "Cust_SessionExpire";
        }
         if(session==null &&sessionvar.equals("V")) {
            return "Vendor_SessionExpire";
        }
        }           
             
//
//      String userName=(String)request.getAttribute("name");
//      System.out.println(userName.length());
//    if(userName.length()>0) return "login";
//     if(userName.length()==0) return "sessionExpire";
//      System.out.println(" output : "+userName);
        return actionInvocation.invoke();
    }
    
}
