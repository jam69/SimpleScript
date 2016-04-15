/*
 *  Copyright &copy; Indra 2016
 */
package com.jam69.simplescript.ast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jamartinm
 */
public class ASTArgList implements AST
{
    private final  List<AST> list=new ArrayList<AST>();
    private Object[] arguments;
    private Class[] argTypes;

    public void add(AST ast){
        list.add(ast);
    }
    

    @Override
    public Object getValue(Object obj, Date d)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object execute(ASTContext context)
    {
        List<Object> ret=new ArrayList<>();
        for(AST a:list){
            Object x=a.execute(context);
            ret.add(x);
        }
        arguments=ret.toArray(new Object[0]);
        argTypes=new Class[ret.size()];
        for(int i=0;i<ret.size();i++){
            if(arguments[i]!=null){
                argTypes[i] =arguments[i].getClass();
            }else{
                argTypes[i]=null;
            }
        }

        return ret;
    }

    public String toString(){
        StringBuffer sb=new StringBuffer();
        for(AST a:list){
           sb.append(a);
           sb.append(",");
        }
        return sb.toString();
    }

    public Object[] getArguments(){
        return arguments;
    }

    public Class[] getArgTypes(){
        return argTypes;
    }

}
