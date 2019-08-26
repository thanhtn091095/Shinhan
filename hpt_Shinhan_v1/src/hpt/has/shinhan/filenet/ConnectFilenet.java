package hpt.has.shinhan.filenet;

import java.util.Iterator;

import javax.security.auth.Subject;

import com.filenet.api.collection.ObjectStoreSet;
import com.filenet.api.core.Connection;
import com.filenet.api.core.Domain;
import com.filenet.api.core.Factory;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.util.UserContext;

public class ConnectFilenet {

	public static ObjectStore OpenConnection(String uri, String username, String password, String optionalJAASStanzaName, String objectName) {
		// Make connection.
		ObjectStore store = null;
	    try
	    {
	    	Connection conn = Factory.Connection.getConnection(uri);
		    Subject subject = UserContext.createSubject(conn, username, password, optionalJAASStanzaName);
		    UserContext.get().pushSubject(subject);
		    
	       // Get default domain.
	       Domain domain = Factory.Domain.fetchInstance(conn, null, null);
	       System.out.println("hpt.has.shinhan.filenet.ConnectFilenet.OpenConnection.Info: Domain: " + domain.get_Name());

	       // Get object stores for domain.
	       ObjectStoreSet osSet = domain.get_ObjectStores();

	       Iterator osIter = osSet.iterator();	       
	       while (osIter.hasNext()) 
	       {
	          store = (ObjectStore) osIter.next();
	          System.out.println("hpt.has.shinhan.filenet.ConnectFilenet.OpenConnection.Info: Object store: " + store.get_Name());
	          
	          if(store.get_Name().equals(objectName))
	        	  break;
	       }
	       System.out.println("hpt.has.shinhan.filenet.ConnectFilenet.OpenConnection.Info: Connection to Content Platform Engine successful");
	    }
	    catch (Exception e) {
			// TODO: handle exception
	    	System.out.println("hpt.has.shinhan.filenet.ConnectFilenet.OpenConnection.Error: " + e);
		}
			
	    return store;
	}
	
	public static void CloseConnection() {
	       UserContext.get().popSubject();
	       System.out.println("hpt.has.shinhan.filenet.ConnectFilenet.OpenConnection.Info: Connection to FileNet is closed");
	}
	
}
