package Users.Admin;

import DataBaseAcces.CrudServices.DocumentKindCrudService;
        import DataBaseAcces.CrudServices.FieldCrudService;
        import DataBaseAcces.CrudServices.FieldTypeCrudService;
        import DataBaseAcces.CrudServices.PictureCrudService;
        import DataBaseAcces.Entities.*;
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

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get creating document or already existing
        DocumentKind documentKind = getDocumentKind(req);
        if(documentKind == null){
            sender.sendErr(req, "Не удалось загрузить документ: ");
            resp.sendRedirect(req.getContextPath()+"/admin/");
            return;
        }

        Boolean isNewDocument = false;
        if(documentKind.getId()<0)
            isNewDocument = true;

        req.setAttribute("documentKind", documentKind);
        req.setAttribute("isNewDocument", isNewDocument);
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        if(type == null || type.equals("page")) {
            req.getRequestDispatcher("/Users/Admin/JSP/EditDocument.jsp").forward(req,resp);
            return;
        }

        if(type.equals("field"))
        {
            try{
                long fieldID = Long.parseLong(req.getParameter("fieldID"));
                Field field = getField(req, fieldID);
                if(field != null)
                {
                    req.setAttribute("field", field);
                    req.getRequestDispatcher("/Users/Admin/includes/AdminField.jsp").forward(req,resp);
                }
                return;
            }catch (Exception e){
                sender.sendErr(req, "Не удалось получить поле документа: " + e.toString());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");


        if(type!=null && type.equals("createField"))
        {
            try{
                createField(req,resp);
            } catch (Exception e){
                sender.sendErr(req, "Не удалось добавить поле: " + e.toString());
            }
            return;
        }

        if(type!=null && type.equals("deleteField"))
        {
            try{
                long fieldID = Long.parseLong(req.getParameter("fieldID"));
                deleteField(req, fieldID);
            } catch (Exception e){
                sender.sendErr(req, "Не удалось удалить поле: " + e.toString());
            }
            return;
        }

        if(type!=null && type.equals("deleteDocument"))
        {
            try{
                deleteDocument(req);
            } catch (Exception e){
                sender.sendErr(req, "Не удалось удалить документ: " + e.toString());
            }
            return;
        }

        try{
            saveDocument(req);
        } catch (Exception e){
            sender.sendErr(req, "Не удалсоь сохранить документ: " + e.toString());
        }


        resp.sendRedirect(req.getContextPath()+"/admin/");
    }

    private void saveDocument(HttpServletRequest req)
    {
        DocumentKind documentKind = (DocumentKind)req.getAttribute("documentKind");
        Boolean isNewDocument = (Boolean)req.getAttribute("isNewDocument");

        // fill document field
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

        // create new document
        DocumentKind newDocumentKind = null;
        if(isNewDocument)
        {
            newDocumentKind = new DocumentKind(documentKind.getFieldsCount(),documentKind.getName(),
                    documentKind.getDescription(),documentKind.getOrder(),documentKind.getPicture());
            documentKindCrudService.save(newDocumentKind);
        }

        List<Field> fields = documentKind.getFields();
        for (Field field : fields)
        {
            String fieldName = req.getParameter("name"+field.getId());
            long typeID = Long.parseLong(req.getParameter("fieldType"+field.getId()));
            FieldType type = fieldTypeCrudService.findById(typeID);
            field.setName(fieldName);
            field.setFieldType(type);
            if(isNewDocument)
            {
                Field newField = new Field(field.getName(), field.getOrder(), newDocumentKind, field.getFieldType());
                newDocumentKind.getFields().add(newField);
                fieldCrudService.save(newField);
            }
            else
                fieldCrudService.update(field);
        }
        if(isNewDocument)
        {
            documentKindCrudService.update(newDocumentKind);
            req.removeAttribute("newDocument");
        }
        else
            documentKindCrudService.update(documentKind);
    }

    private void createField(HttpServletRequest req, HttpServletResponse resp)
    {
        DocumentKind documentKind = (DocumentKind)req.getAttribute("documentKind");
        Boolean isNewDocument = (Boolean)req.getAttribute("isNewDocument");

        try{
            // get document kind in some way
            int order = documentKind.getFieldsCount();
            Field newField = new Field("", order, documentKind, fieldTypeCrudService.findById(1));
            if(isNewDocument)
                newField.setId(documentKind.getFieldsCount());
            else
                fieldCrudService.save(newField);
            documentKind.getFields().add(newField);
            documentKind.setFieldsCount(documentKind.getFieldsCount()+1);

            // return id for next show
            resp.getWriter().write(newField.getId()+"");

        }catch (Exception e){
            sender.sendErr(req, "Ошибка при создании поля: " + e.toString());
        }
    }

    private void deleteField(HttpServletRequest req, long fieldID)
    {
        DocumentKind documentKind = (DocumentKind)req.getAttribute("documentKind");
        Boolean isNewDocument = (Boolean)req.getAttribute("isNewDocument");

        Field field = getField(req, fieldID);
        documentKind.getFields().remove(field);
        documentKind.setFieldsCount(documentKind.getFieldsCount()-1);

        if(!isNewDocument)
        {
            field.setFieldType(null);
            field.setDocumentKind(null);
            fieldCrudService.update(field);
            documentKindCrudService.update(documentKind);
            fieldCrudService.deleteById(fieldID);
        }
    }

    private Field getField(HttpServletRequest req, long fieldID) {
        DocumentKind documentKind = (DocumentKind)req.getAttribute("documentKind");
        List<Field> fields = documentKind.getFields();
        for (Field field : fields)
        {
            if(field.getId() == fieldID)
                return field;
        }

        return null;
    }

    private void deleteDocument(HttpServletRequest req)
    {
        DocumentKind documentKind = (DocumentKind)req.getAttribute("documentKind");
        Boolean isNewDocument = (Boolean)req.getAttribute("isNewDocument");
        if(isNewDocument)
            req.removeAttribute("newDocument");

        List<Field> fields = documentKind.getFields();
        for(Field field : fields)
            fieldCrudService.deleteById(field.getId());
        documentKindCrudService.deleteById(documentKind.getId());
    }

    private DocumentKind getDocumentKind(HttpServletRequest req)
    {
        DocumentKind outDocument;
        String formIDStr = req.getParameter("documentID");
        // if sended without form id this is new form or null
        if(formIDStr == null)
            return (DocumentKind)req.getSession().getAttribute("newDocument");

        // get form by id
        long documentID = Long.parseLong(req.getParameter("documentID"));
        if(documentID<0)
            return (DocumentKind)req.getSession().getAttribute("newDocument");
        outDocument = documentKindCrudService.findById(documentID);


        return outDocument;
    }
}
