package Security;

import CrudServices.UserCrudService;
import Entities.User;
import Entities.UserKind;
import com.iplanet.sso.SSOToken;
import com.iplanet.sso.SSOTokenManager;

import javax.ejb.EJB;
import javax.ejb.Stateless;



@Stateless
public class SSOManager {

    private SSOTokenManager manager;
    private SSOToken token;

    @EJB
    UserCrudService userCrudService;

    public int validateUser(String ssoToken)
    {
        // return user ID
        return 0;
    }

    public boolean logIn(String email, String password, UserKind userKind)
    {
        return false;
    }

    public void logOut(long id)
    {

    }

    public boolean register(String email, String password, UserKind userKind)
    {
        return false;
    }
}
