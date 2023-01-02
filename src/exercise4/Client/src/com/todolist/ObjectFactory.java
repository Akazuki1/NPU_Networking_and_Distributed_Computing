
package com.todolist;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.todolist package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Query_QNAME = new QName("http://www.todolist.com", "query");
    private final static QName _AddResponse_QNAME = new QName("http://www.todolist.com", "addResponse");
    private final static QName _Add_QNAME = new QName("http://www.todolist.com", "add");
    private final static QName _FindUser_QNAME = new QName("http://www.todolist.com", "findUser");
    private final static QName _Delete_QNAME = new QName("http://www.todolist.com", "delete");
    private final static QName _Clear_QNAME = new QName("http://www.todolist.com", "clear");
    private final static QName _QueryResponse_QNAME = new QName("http://www.todolist.com", "queryResponse");
    private final static QName _RegisterResponse_QNAME = new QName("http://www.todolist.com", "registerResponse");
    private final static QName _DeleteResponse_QNAME = new QName("http://www.todolist.com", "deleteResponse");
    private final static QName _FindUserResponse_QNAME = new QName("http://www.todolist.com", "findUserResponse");
    private final static QName _LoginCheckResponse_QNAME = new QName("http://www.todolist.com", "loginCheckResponse");
    private final static QName _IsOccupied_QNAME = new QName("http://www.todolist.com", "isOccupied");
    private final static QName _Register_QNAME = new QName("http://www.todolist.com", "register");
    private final static QName _IsExist_QNAME = new QName("http://www.todolist.com", "isExist");
    private final static QName _IsOccupiedResponse_QNAME = new QName("http://www.todolist.com", "isOccupiedResponse");
    private final static QName _ParseException_QNAME = new QName("http://www.todolist.com", "ParseException");
    private final static QName _IsExistResponse_QNAME = new QName("http://www.todolist.com", "isExistResponse");
    private final static QName _LoginCheck_QNAME = new QName("http://www.todolist.com", "loginCheck");
    private final static QName _ClearResponse_QNAME = new QName("http://www.todolist.com", "clearResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.todolist
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Add }
     * 
     */
    public Add createAdd() {
        return new Add();
    }

    /**
     * Create an instance of {@link FindUser }
     * 
     */
    public FindUser createFindUser() {
        return new FindUser();
    }

    /**
     * Create an instance of {@link AddResponse }
     * 
     */
    public AddResponse createAddResponse() {
        return new AddResponse();
    }

    /**
     * Create an instance of {@link Query }
     * 
     */
    public Query createQuery() {
        return new Query();
    }

    /**
     * Create an instance of {@link Clear }
     * 
     */
    public Clear createClear() {
        return new Clear();
    }

    /**
     * Create an instance of {@link Delete }
     * 
     */
    public Delete createDelete() {
        return new Delete();
    }

    /**
     * Create an instance of {@link IsOccupied }
     * 
     */
    public IsOccupied createIsOccupied() {
        return new IsOccupied();
    }

    /**
     * Create an instance of {@link FindUserResponse }
     * 
     */
    public FindUserResponse createFindUserResponse() {
        return new FindUserResponse();
    }

    /**
     * Create an instance of {@link LoginCheckResponse }
     * 
     */
    public LoginCheckResponse createLoginCheckResponse() {
        return new LoginCheckResponse();
    }

    /**
     * Create an instance of {@link DeleteResponse }
     * 
     */
    public DeleteResponse createDeleteResponse() {
        return new DeleteResponse();
    }

    /**
     * Create an instance of {@link QueryResponse }
     * 
     */
    public QueryResponse createQueryResponse() {
        return new QueryResponse();
    }

    /**
     * Create an instance of {@link RegisterResponse }
     * 
     */
    public RegisterResponse createRegisterResponse() {
        return new RegisterResponse();
    }

    /**
     * Create an instance of {@link ClearResponse }
     * 
     */
    public ClearResponse createClearResponse() {
        return new ClearResponse();
    }

    /**
     * Create an instance of {@link LoginCheck }
     * 
     */
    public LoginCheck createLoginCheck() {
        return new LoginCheck();
    }

    /**
     * Create an instance of {@link ParseException }
     * 
     */
    public ParseException createParseException() {
        return new ParseException();
    }

    /**
     * Create an instance of {@link IsExistResponse }
     * 
     */
    public IsExistResponse createIsExistResponse() {
        return new IsExistResponse();
    }

    /**
     * Create an instance of {@link IsOccupiedResponse }
     * 
     */
    public IsOccupiedResponse createIsOccupiedResponse() {
        return new IsOccupiedResponse();
    }

    /**
     * Create an instance of {@link IsExist }
     * 
     */
    public IsExist createIsExist() {
        return new IsExist();
    }

    /**
     * Create an instance of {@link Register }
     * 
     */
    public Register createRegister() {
        return new Register();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Query }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.todolist.com", name = "query")
    public JAXBElement<Query> createQuery(Query value) {
        return new JAXBElement<Query>(_Query_QNAME, Query.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.todolist.com", name = "addResponse")
    public JAXBElement<AddResponse> createAddResponse(AddResponse value) {
        return new JAXBElement<AddResponse>(_AddResponse_QNAME, AddResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Add }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.todolist.com", name = "add")
    public JAXBElement<Add> createAdd(Add value) {
        return new JAXBElement<Add>(_Add_QNAME, Add.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.todolist.com", name = "findUser")
    public JAXBElement<FindUser> createFindUser(FindUser value) {
        return new JAXBElement<FindUser>(_FindUser_QNAME, FindUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Delete }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.todolist.com", name = "delete")
    public JAXBElement<Delete> createDelete(Delete value) {
        return new JAXBElement<Delete>(_Delete_QNAME, Delete.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Clear }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.todolist.com", name = "clear")
    public JAXBElement<Clear> createClear(Clear value) {
        return new JAXBElement<Clear>(_Clear_QNAME, Clear.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.todolist.com", name = "queryResponse")
    public JAXBElement<QueryResponse> createQueryResponse(QueryResponse value) {
        return new JAXBElement<QueryResponse>(_QueryResponse_QNAME, QueryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.todolist.com", name = "registerResponse")
    public JAXBElement<RegisterResponse> createRegisterResponse(RegisterResponse value) {
        return new JAXBElement<RegisterResponse>(_RegisterResponse_QNAME, RegisterResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.todolist.com", name = "deleteResponse")
    public JAXBElement<DeleteResponse> createDeleteResponse(DeleteResponse value) {
        return new JAXBElement<DeleteResponse>(_DeleteResponse_QNAME, DeleteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.todolist.com", name = "findUserResponse")
    public JAXBElement<FindUserResponse> createFindUserResponse(FindUserResponse value) {
        return new JAXBElement<FindUserResponse>(_FindUserResponse_QNAME, FindUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginCheckResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.todolist.com", name = "loginCheckResponse")
    public JAXBElement<LoginCheckResponse> createLoginCheckResponse(LoginCheckResponse value) {
        return new JAXBElement<LoginCheckResponse>(_LoginCheckResponse_QNAME, LoginCheckResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsOccupied }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.todolist.com", name = "isOccupied")
    public JAXBElement<IsOccupied> createIsOccupied(IsOccupied value) {
        return new JAXBElement<IsOccupied>(_IsOccupied_QNAME, IsOccupied.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Register }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.todolist.com", name = "register")
    public JAXBElement<Register> createRegister(Register value) {
        return new JAXBElement<Register>(_Register_QNAME, Register.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsExist }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.todolist.com", name = "isExist")
    public JAXBElement<IsExist> createIsExist(IsExist value) {
        return new JAXBElement<IsExist>(_IsExist_QNAME, IsExist.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsOccupiedResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.todolist.com", name = "isOccupiedResponse")
    public JAXBElement<IsOccupiedResponse> createIsOccupiedResponse(IsOccupiedResponse value) {
        return new JAXBElement<IsOccupiedResponse>(_IsOccupiedResponse_QNAME, IsOccupiedResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.todolist.com", name = "ParseException")
    public JAXBElement<ParseException> createParseException(ParseException value) {
        return new JAXBElement<ParseException>(_ParseException_QNAME, ParseException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsExistResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.todolist.com", name = "isExistResponse")
    public JAXBElement<IsExistResponse> createIsExistResponse(IsExistResponse value) {
        return new JAXBElement<IsExistResponse>(_IsExistResponse_QNAME, IsExistResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginCheck }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.todolist.com", name = "loginCheck")
    public JAXBElement<LoginCheck> createLoginCheck(LoginCheck value) {
        return new JAXBElement<LoginCheck>(_LoginCheck_QNAME, LoginCheck.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClearResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.todolist.com", name = "clearResponse")
    public JAXBElement<ClearResponse> createClearResponse(ClearResponse value) {
        return new JAXBElement<ClearResponse>(_ClearResponse_QNAME, ClearResponse.class, null, value);
    }

}
