package Users.SimpleUser;

import DataBaseAcces.CrudServices.ComplaintCrudService;
import DataBaseAcces.CrudServices.UserDocumentCrudService;
import DataBaseAcces.CrudServices.UserFormCrudService;
import DataBaseAcces.Entities.*;
import ExternalServices.Rabbit.RabbitSender;
import ExternalServices.Security.SSOManager;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Сервлет внешней формы
 */
@WebServlet(name="OutServlet", urlPatterns = {"/user/out"})
public class OutServlet extends HttpServlet {
    @EJB
    private UserFormCrudService userFormCrudService;
    @EJB
    private ComplaintCrudService complaintCrudService;
    @EJB
    private UserDocumentCrudService userDocumentCrudService;
    @EJB
    private SSOManager ssoManager;

    @EJB
    private RabbitSender sender;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get and check data
        UserForm form;
        try{
            String returnURL = req.getParameter("returnURL");
            if(returnURL==null)
                throw new Exception();
            long formID = Long.parseLong(req.getParameter("formID"));
            form = userFormCrudService.findById(formID);
            if(form==null)
                throw new Exception();
        }catch(Exception e){
            sender.sendErr("Не удалось получить форму");
            req.getRequestDispatcher("/Common/InvalideForm.jsp").forward(req,resp);
            return;
        }

        req.setAttribute("form", form);
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type=req.getParameter("type");
        if(type.equals("complaint"))
        {
            req.getRequestDispatcher("/Common/Complaint.jsp");
            return;
        }
        req.getRequestDispatcher("/Common/Out.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = ssoManager.getCurrentUser(req);
        if(user==null)
        {
            // RMQ
            return;
        }

        String type = req.getParameter("type");
        if(type!=null&&type.equals("complaint"))
        {
            makeComplaint(req,  user);
        }

        String data = packData(user,(UserForm)req.getAttribute("form"));
        resp.getWriter().write(data);
    }

    /**
     * Создание жалобы
     * @param req HTTP-запрос
     * @param user пользователь
     */
    private void makeComplaint(HttpServletRequest req, User user) {
        User owner = ((UserForm)req.getAttribute("form")).getUser();
        String text = req.getParameter("text");
        if(text==null)
            text="";
        Complaint complaint = new Complaint(new Date(), text,user,owner);
        complaintCrudService.save(complaint);
    }

    /**
     * Упаковка данных для отправки форме
     * @param user пользователь
     * @param userForm форма
     * @return данные для отправки ввиде строки
     */
    private String packData(User user, UserForm userForm) {
        String data = "";
        for(FormDocument document:userForm.getFormDocuments()) {
            data+=document.getId()+'\n';
            UserDocument userDocument = userDocumentCrudService.findByUserAndDocument(user,document.getDocumentKind());
            if(userDocument==null)
                continue;
            for(int i = 0;i<document.getFormDocumentFields().size();i++) {
                String value = "";
                if(userDocument!=null)
                    value = userDocument.getUserDocumentFields().get(i).getValue();
                data+=document.getFormDocumentFields().get(i).getId()+'='+value+'\n';
            }
        }
        return data;
    }


}
