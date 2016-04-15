/*
 *  Copyright &copy; Indra 2016
 */
package com.jam69.simplescript.ast;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jamartinm
 */
public class ASTStmtIf implements AST
{
     private final static Logger log = LoggerFactory.getLogger(ASTStmtIf.class);

    private final AST condition;
    private final AST ifPart;
    private final AST elsePart;

    public ASTStmtIf(AST condition,AST ifPart,AST elsePart){
        this.condition=condition;
        this.ifPart=ifPart;
        this.elsePart=elsePart;
    }

    @Override
    public Object getValue(Object obj, Date d)
    {
        Object v=condition.getValue(obj, d);
        if( isTrue(v)){
            return ifPart.getValue(obj, d);
        }else{
            return elsePart.getValue(obj, d);
        }
    }

     @Override
    public Object execute(ASTContext context)
    {
        Object v=condition.execute(context);
        if( isTrue(v)){
            return ifPart.execute(context);
        }else{
            return elsePart.execute(context);
        } }

    private boolean isTrue(Object v){
        if(v==null) {
            return false;
        }
        if (v instanceof Boolean){
            return ((Boolean)v).booleanValue();
        }
        if(v instanceof Integer){
            return ((Integer)v).intValue()!=0;
        }
        if(v instanceof String){
            return !((String)v).isEmpty();
        }
         if(v instanceof Double){
            return ((Double)v).intValue()!=0;
        }

        log.warn("NO puedo saber si es true o false '"+ v+"'");
        return false;
    }

    public String toString(){
        StringBuffer sb=new StringBuffer();
        sb.append("if (").append(condition).append(")\n")
            .append(ifPart)
            .append("\nelse\n")
            .append(elsePart);
        return sb.toString();
    }


}
