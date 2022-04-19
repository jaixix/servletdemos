package listenersdemo;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
@WebListener
public class ReqAttrListenerDemo implements ServletRequestListener, ServletRequestAttributeListener {

	public void requestDestroyed(ServletRequestEvent sre)  { 
		System.out.println("Request Destroyed!" + sre.getSource());
    }

    public void requestInitialized(ServletRequestEvent sre)  {
    	System.out.println("Request Initialized!" + sre.getSource());
    }
	
    public void attributeRemoved(ServletRequestAttributeEvent srae)  {
    	System.out.println("Attribute Removed - " + srae.getName() + ", " + srae.getValue());
    }
    
    public void attributeAdded(ServletRequestAttributeEvent srae)  {
    	System.out.println("Attribute Added - " + srae.getName() + ", " + srae.getValue());
    }
    
    public void attributeReplaced(ServletRequestAttributeEvent srae)  { 
    	System.out.println("Attribute Replaced - " + srae.getName() + ", " + srae.getValue());
    }
}
