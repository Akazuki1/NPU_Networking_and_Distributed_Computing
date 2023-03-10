
package com.todolist;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "todolist", targetNamespace = "http://www.todolist.com")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Todolist {


    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg4
     * @param arg1
     * @param arg0
     * @return
     *     returns int
     * @throws ParseException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "add", targetNamespace = "http://www.todolist.com", className = "com.todolist.Add")
    @ResponseWrapper(localName = "addResponse", targetNamespace = "http://www.todolist.com", className = "com.todolist.AddResponse")
    @Action(input = "http://www.todolist.com/todolist/addRequest", output = "http://www.todolist.com/todolist/addResponse", fault = {
        @FaultAction(className = ParseException_Exception.class, value = "http://www.todolist.com/todolist/add/Fault/ParseException")
    })
    public int add(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        String arg4)
        throws ParseException_Exception
    ;

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "register", targetNamespace = "http://www.todolist.com", className = "com.todolist.Register")
    @ResponseWrapper(localName = "registerResponse", targetNamespace = "http://www.todolist.com", className = "com.todolist.RegisterResponse")
    @Action(input = "http://www.todolist.com/todolist/registerRequest", output = "http://www.todolist.com/todolist/registerResponse")
    public boolean register(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "clear", targetNamespace = "http://www.todolist.com", className = "com.todolist.Clear")
    @ResponseWrapper(localName = "clearResponse", targetNamespace = "http://www.todolist.com", className = "com.todolist.ClearResponse")
    @Action(input = "http://www.todolist.com/todolist/clearRequest", output = "http://www.todolist.com/todolist/clearResponse")
    public boolean clear(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "delete", targetNamespace = "http://www.todolist.com", className = "com.todolist.Delete")
    @ResponseWrapper(localName = "deleteResponse", targetNamespace = "http://www.todolist.com", className = "com.todolist.DeleteResponse")
    @Action(input = "http://www.todolist.com/todolist/deleteRequest", output = "http://www.todolist.com/todolist/deleteResponse")
    public boolean delete(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.util.List<java.lang.String>
     * @throws ParseException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "query", targetNamespace = "http://www.todolist.com", className = "com.todolist.Query")
    @ResponseWrapper(localName = "queryResponse", targetNamespace = "http://www.todolist.com", className = "com.todolist.QueryResponse")
    @Action(input = "http://www.todolist.com/todolist/queryRequest", output = "http://www.todolist.com/todolist/queryResponse", fault = {
        @FaultAction(className = ParseException_Exception.class, value = "http://www.todolist.com/todolist/query/Fault/ParseException")
    })
    public List<String> query(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        String arg3)
        throws ParseException_Exception
    ;

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "loginCheck", targetNamespace = "http://www.todolist.com", className = "com.todolist.LoginCheck")
    @ResponseWrapper(localName = "loginCheckResponse", targetNamespace = "http://www.todolist.com", className = "com.todolist.LoginCheckResponse")
    @Action(input = "http://www.todolist.com/todolist/loginCheckRequest", output = "http://www.todolist.com/todolist/loginCheckResponse")
    public boolean loginCheck(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "isExist", targetNamespace = "http://www.todolist.com", className = "com.todolist.IsExist")
    @ResponseWrapper(localName = "isExistResponse", targetNamespace = "http://www.todolist.com", className = "com.todolist.IsExistResponse")
    @Action(input = "http://www.todolist.com/todolist/isExistRequest", output = "http://www.todolist.com/todolist/isExistResponse")
    public boolean isExist(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "isOccupied", targetNamespace = "http://www.todolist.com", className = "com.todolist.IsOccupied")
    @ResponseWrapper(localName = "isOccupiedResponse", targetNamespace = "http://www.todolist.com", className = "com.todolist.IsOccupiedResponse")
    @Action(input = "http://www.todolist.com/todolist/isOccupiedRequest", output = "http://www.todolist.com/todolist/isOccupiedResponse")
    public boolean isOccupied(
        @WebParam(name = "arg0", targetNamespace = "")
        User arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        XMLGregorianCalendar arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        XMLGregorianCalendar arg2);

    /**
     * 
     * @param arg0
     * @return
     *     returns com.todolist.User
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findUser", targetNamespace = "http://www.todolist.com", className = "com.todolist.FindUser")
    @ResponseWrapper(localName = "findUserResponse", targetNamespace = "http://www.todolist.com", className = "com.todolist.FindUserResponse")
    @Action(input = "http://www.todolist.com/todolist/findUserRequest", output = "http://www.todolist.com/todolist/findUserResponse")
    public User findUser(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}
