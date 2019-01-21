package Users.Admin;

import DataBaseAcces.CrudServices.DocumentKindCrudService;
import DataBaseAcces.CrudServices.FieldCrudService;
import DataBaseAcces.CrudServices.FieldTypeCrudService;
import DataBaseAcces.CrudServices.PictureCrudService;
import DataBaseAcces.Entities.DocumentKind;
import DataBaseAcces.Entities.Field;
import DataBaseAcces.Entities.FieldType;
import DataBaseAcces.Entities.Picture;
import ExternalServices.Rabbit.RabbitSender;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Сервлет изменения документа администратора
 */

@WebServlet(name="EditDocumentServlet", urlPatterns = {"/admin/editDocument"})
public class EditDocumentServlet extends HttpServlet {

    @EJB
    private DocumentKindCrudService documentKindCrudService;
    @EJB
    private FieldTypeCrudService fieldTypeCrudService;
    @EJB
    private FieldCrudService fieldCrudService;
    @EJB
    private PictureCrudService pictureCrudService;

    @EJB
    private RabbitSender sender;

    private DocumentKind documentKind;
    private boolean isNew = false;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            documentKind = (DocumentKind)req.getSession().getAttribute("newDocument");
            if(documentKind == null){
                long documentID=Long.parseLong(req.getParameter("documentID"));
                documentKind=documentKindCrudService.findById(documentID);
                if(documentKind==null)
                    throw new Exception();
            }
            else
                isNew=true;
        }
        catch (Exception e){
            sender.sendErr("Не удалось загрузить документ: " + e.toString());
            resp.sendRedirect(req.getContextPath()+"/admin/");
            return;
        }
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        if(type == null || type.equals("page")) {
            req.getRequestDispatcher("/Users/Admin/JSP/EditDocument.jsp").forward(req,resp);
            return;
        }

        // return only types of field
        if(type.equals("types")) {
            List<FieldType> types = fieldTypeCrudService.findAll();
            // send document in jsp to show
            req.setAttribute("types",types);
            req.getRequestDispatcher("/Users/Admin/JSP/FieldTypesList.jsp").forward(req,resp);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        if(type!=null && type.equals("field"))
        {
            try{
                addField(req);
            } catch (Exception e){
                sender.sendErr("Не удалось добавить поле: " + e.toString());
            }
            return;
        }

        String name = req.getParameter("name");
        if(name!=null)
            documentKind.setName(name);
        String description = req.getParameter("description");
        if(description!=null)
            documentKind.setDescription(description);
        String pictureUrl = req.getParameter("picture");
        if(pictureUrl!=null)
        {
            Picture picture;
            picture=pictureCrudService.findByURL(pictureUrl);
            if(picture==null)
            {
                picture=new Picture(pictureUrl);
                pictureCrudService.save(picture);
            }
            documentKind.setPicture(picture);
        }
        if(isNew)
            documentKindCrudService.save(documentKind);
    }

    private void addField(HttpServletRequest req) throws Exception {
        long typeID = Long.parseLong(req.getParameter("typeID"));
        String name = req.getParameter("name");
        if(name==null)
            name = "";
        FieldType fieldType = fieldTypeCrudService.findById(typeID);
        if(fieldType==null)
            throw new Exception();
        Field field = new Field(name,documentKind.getFieldsCount(),documentKind,fieldType);
        documentKind.setFieldsCount(documentKind.getFieldsCount()+1);
        if(isNew)
            documentKind.getFields().add(field);
        else
            fieldCrudService.save(field);
    }
}