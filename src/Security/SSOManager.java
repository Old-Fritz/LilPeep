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
    private UserCrudService userCrudService;

    @EJB
    private OpenAM openAM;

    public long validateUser(String ssoToken)
    {
        // return user ID
        return openAM.validateToken(ssoToken);
    }

    public boolean login(String email, String password, UserKind userKind)
    {
        User user = userCrudService.getByEmailAndKind(email,userKind);
        if(user.getPassword().equals(password))
            if(openAM.login(user))
                return true;

        return false;
    }

    public void logOut(String ssoToken)
    {
        long userID = openAM.validateToken(ssoToken);
        User user = userCrudService.findById(userID);
        openAM.logout(user);
    }

    public boolean register(String email, String password, UserKind userKind)
    {
        return false;
    }
}
