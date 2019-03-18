package ExternalServices.Security;

import DataBaseAcces.CrudServices.UserCrudService;
import DataBaseAcces.Entities.User;
import DataBaseAcces.Entities.UserKind;
import ExternalServices.Rabbit.RabbitSender;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Менеджер системы единого входа
 */
@Stateful
@Local
public class SSOManager {

    @EJB
    private UserCrudService userCrudService;

    @EJB
    private OpenAM openAM;

    @EJB
    private RabbitSender sender;

    private String tokenCookieName = "iPlanetDirectoryPro";

    public SSOManager(){

    }

    /**
     * Получение текущего пользователя
     * @param req HTTP-запрос
     * @return текущий пользователь
     */
    public User getCurrentUser(HttpServletRequest req) {
        try{
            String ssoToken = getSSOToken(req.getCookies());
            long userID = openAM.getUserID(ssoToken);
            return userCrudService.findById(userID);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * Авторизация пользлователя
     * @param resp HTTP-ответ
     * @param email e-mail пользователя
     * @param password пароль пользователя
     * @param userKind тип учётной записи
     * @return true при удачной авторизации, false — при неудачной
     */
    public boolean login(HttpServletRequest req, HttpServletResponse resp, String email, String password, UserKind userKind){
        User user = userCrudService.findByEmailAndKind(email,userKind);
        if(user == null) {
            sender.sendOut(req, "Такого пользователя не существует");
            return false;
        }

        if(!user.getPassword().equals(password)) {
            sender.sendOut(req, "Неверный пароль");
            return false;
        }

        String SSOTokenId = openAM.login(user);
        if(SSOTokenId==null) {
            sender.sendErr(req, "Случился БИБИБ");
            return false;
        }

        resp.addCookie(new Cookie(tokenCookieName, SSOTokenId));
        return true;
    }

    /**
     * Выход пользователя из системы
     * @param req HTTP-запрос
     * @param resp HTTP-ответ
     */
    public void logout(HttpServletRequest req, HttpServletResponse resp)
    {
        User user = getCurrentUser(req);
        if(user != null)
            openAM.logout(user);
        Cookie delCookie = deleteSSOCookie(req.getCookies());
        if(delCookie!=null)
            resp.addCookie(delCookie);
    }


    /**
     * Регистрация пользователя
     * @param email e-mail пользователя
     * @param password пароль пользователя
     * @param userKind тип учётной записи
     * @return true при удачной регистрации, false — при неудачной
     */
    public boolean register(HttpServletRequest req, String email, String password, UserKind userKind) {
        User user = userCrudService.findByEmailAndKind(email,userKind);
        // can't register existed user
        if(user!=null) {
            sender.sendOut(req, "Это имя пользователя уже занято");
            return false;
        }

        user = new User(email,password,userKind);
        try{
            userCrudService.save(user);
        }
        catch (Exception e)
        {
            sender.sendErr(req, "Не зарегистрировать пользователя: " + e.toString());
            return false;
        }

        return true;
    }

    /**
     * Получениее SSO-токена через cookie-файлы
     * @param cookies cookie-файлы
     * @return SSO-токен ввиде строки
     */
    private String getSSOToken(Cookie[] cookies)
    {
        if(cookies==null)
            return null;
        for(Cookie cookie : cookies)
        {
            if(cookie.getName().equals(tokenCookieName))
                return cookie.getValue();
        }

        return null;
    }

    /**
     * Поиск cookie-файла для удаления
     * @param cookies cookie-файлы
     * @return файл для удаления, или null, если такой не найден
     */

    private Cookie deleteSSOCookie(Cookie[] cookies)
    {
        for(Cookie cookie : cookies)
        {
            if(cookie.getName().equals(tokenCookieName))
            {
                cookie.setValue(null);
                cookie.setMaxAge(0);
                return cookie;
            }
        }
        return null;
    }
}