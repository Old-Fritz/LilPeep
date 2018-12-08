package Security;

import Entities.User;
import com.iplanet.sso.SSOToken;
import com.iplanet.sso.SSOTokenManager;
import com.sun.identity.authentication.AuthContext;
import com.sun.identity.authentication.spi.AuthLoginException;

import javax.ejb.Stateless;
import javax.security.auth.callback.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Stateless
public class OpenAM {

    private String loginIndexName = "SingleAuth";
    private SSOTokenManager manager;

    public OpenAM()
    {

    }

    public long getUserID(String tokenID)
    {
        try{
            manager = SSOTokenManager.getInstance();
            SSOToken token = manager.createSSOToken(tokenID);
            if(manager.isValidToken(token))
            {
                return Long.parseLong(token.getProperty("userID"));
            }
            return -1;
        }
        catch (Exception e)
        {
            return -1;
        }
    }

    protected AuthContext getAuthContext(String orgName)
            throws AuthLoginException, MalformedURLException {
        AuthContext lc = new AuthContext(orgName);
        AuthContext.IndexType indexType = AuthContext.IndexType.MODULE_INSTANCE;
        lc.login(indexType, loginIndexName);

        return lc;
    }

    public String login(User user)
    {
        try{
            AuthContext lc = getAuthContext(user.getUserKind().getOpenSSORealm());
            Callback[] callbacks;

            while(lc.hasMoreRequirements())
            {
                callbacks = lc.getRequirements();
                if(callbacks!=null)
                {
                    addLoginCallbackMessage(callbacks, user);
                    lc.submitRequirements(callbacks);
                }
            }

            if(lc.getStatus()==AuthContext.Status.SUCCESS)
            {
                lc.getSSOToken().setProperty("userID",user.getId()+"");
                return lc.getSSOToken().getTokenID().toString();
            }

            return null;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public void logout(User user)
    {
        try{
            AuthContext lc = getAuthContext(user.getUserKind().getOpenSSORealm());
            lc.logoutUsingTokenID();
        }
        catch (Exception e) {

        }
    }

    private void addLoginCallbackMessage(Callback[] callbacks, User user)
            throws UnsupportedCallbackException {
        int i = 0;
        try {
            for (i = 0; i < callbacks.length; i++) {
                if (callbacks[i] instanceof NameCallback) {
                    handleNameCallback((NameCallback)callbacks[i],user);
                } else if (callbacks[i] instanceof PasswordCallback) {
                    handlePasswordCallback((PasswordCallback)callbacks[i],user);
                } else if (callbacks[i] instanceof TextInputCallback) {
                    handleTextInputCallback((TextInputCallback)callbacks[i],user);
                } else if (callbacks[i] instanceof ChoiceCallback) {
                    handleChoiceCallback((ChoiceCallback)callbacks[i], user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new UnsupportedCallbackException(callbacks[i],e.getMessage());
        }
    }


    private void handleNameCallback(NameCallback nc, User user)
            throws IOException {
        nc.setName(user.getId()+"");
    }

    private void handleTextInputCallback(TextInputCallback tic, User user)
            throws IOException {
        tic.setText(user.getId() + "");
    }

    private void handlePasswordCallback(PasswordCallback pc, User user)
            throws IOException {
        pc.setPassword(user.getPassword().toCharArray());
    }

    private void handleChoiceCallback(ChoiceCallback cc, User user)
            throws IOException {
        cc.setSelectedIndex((int)user.getUserKind().getId());
    }
}
