package SimpleUser;

import CrudServices.UserDocumentCrudService;
import CrudServices.UserDocumentFieldCrudService;
import Entities.UserDocument;
import Entities.UserDocumentField;
import Security.SSOManager;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.Document;
import java.io.IOException;
import java.util.List;


@WebServlet(name="EditUserDocument",urlPatterns = {"/user/editDocument"})
public class EditUserDocumentServlet extends HttpServlet {
    @EJB
    private UserDocumentCrudService userDocumentCrudService;

    @EJB
    private SSOManager ssoManager;

    @EJB
    private UserDocumentFieldCrudService userDocumentFieldCrudService;

    private UserDocument document;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long userID = ssoManager.validateUser(req.getParameter("ssoToken"));
        int documentID;
        try {
            documentID = Integer.parseInt(req.getParameter("documentID"));
        }catch (Exception e) {return;}
        UserDocument document = userDocumentCrudService.findById(documentID);

        // security check
        if(document.getUser().getId()!=userID)
            return;

        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // send document in jsp to show
        req.setAttribute("document",document);
        req.getRequestDispatcher("/SimpleUser/EditUserDocument.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserDocumentField> fields = document.getUserDocumentFields();

        // change all field values
        for(int i = 0;i<fields.size();i++)
        {
            String fieldValue = req.getParameter("field" + i);
            if(fieldValue == null)
                fieldValue = "";
            fields.get(i).setValue(fieldValue);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userDocumentCrudService.deleteById(document.getId());
    }
}
