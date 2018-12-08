import CrudServices.*;
import Entities.*;
import Security.SSOManager;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Servlet", urlPatterns = {"/servlet2"})
public class Servlet extends HttpServlet {

    @EJB
    UserKindCrudService userKindCrudService;

    @EJB
    SSOManager ssoManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ssoManager.login("qwe@mail.ru", "abcd", userKindCrudService.findById(12));
        /*
        UserKind simpleUser = new UserKind("Simple user");
        userKindCrudService.save(simpleUser);
        User user1 = new User("qwe@mail.ru","abcd",simpleUser);
        User user2 = new User("abc@mail.ru","qwer",simpleUser);
        userCrudService.save(user1);
        userCrudService.save(user2);

        Picture picture = new Picture("https://sun9-4.userapi.com/68tYs55HM47h_Ay8Od--wu228wiGC8LS-rNqZw/vE8bcKm3SxQ.jpg");
        pictureCrudService.save(picture);

        DocumentKind documentKind1 = new DocumentKind(3,"docSimple1", "coolDoc1", 0,picture);
        DocumentKind documentKind2 = new DocumentKind(2,"docWithLongName", "coolDoc2", 1,picture);
        DocumentKind documentKind3 = new DocumentKind(4,"docSimple2", "coolDoc3", 2,picture);
        documentKindCrudService.save(documentKind1);
        documentKindCrudService.save(documentKind2);
        documentKindCrudService.save(documentKind3);

        FieldType fieldType = new FieldType("Text");
        fieldTypeCrudService.save(fieldType);

        Field field11 = new Field("field1",0,documentKind1,fieldType);
        Field field12 = new Field("field2",1,documentKind1,fieldType);
        Field field13 = new Field("field3",2,documentKind1,fieldType);

        Field field21 = new Field("field1",0,documentKind2,fieldType);
        Field field22 = new Field("field2",1,documentKind2,fieldType);

        Field field31 = new Field("field1",0,documentKind3,fieldType);
        Field field32 = new Field("field2",1,documentKind3,fieldType);
        Field field33 = new Field("field3",2,documentKind3,fieldType);
        Field field34 = new Field("field4",3,documentKind3,fieldType);

        fieldCrudService.save(field11);
        fieldCrudService.save(field12);
        fieldCrudService.save(field13);

        fieldCrudService.save(field21);
        fieldCrudService.save(field22);

        fieldCrudService.save(field31);
        fieldCrudService.save(field32);
        fieldCrudService.save(field33);
        fieldCrudService.save(field34);

        UserDocument document1 = new UserDocument(documentKind1, user1);
        UserDocument document2 = new UserDocument(documentKind2, user1);
        UserDocument document3 = new UserDocument(documentKind3, user1);

        UserDocument document4 = new UserDocument(documentKind1, user2);
        UserDocument document5 = new UserDocument(documentKind3, user2);

        userDocumentCrudService.save(document1);
        userDocumentCrudService.save(document2);
        userDocumentCrudService.save(document3);
        userDocumentCrudService.save(document4);
        userDocumentCrudService.save(document5);

        resp.getWriter().write("loadDone");
        */
    }
}
