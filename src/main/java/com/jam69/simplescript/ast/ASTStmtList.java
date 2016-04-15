/*
 *  Copyright &copy; Indra 2016
 */
package com.jam69.simplescript.ast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jamartinm
 */
public class ASTStmtList implements AST
{

    private final static Logger log = LoggerFactory.getLogger(ASTStmtList.class);

    private final List<AST> block=new ArrayList<>();

    public ASTStmtList(){

    }

    public void add(AST f){
        block.add(f);
    }

    @Override
    public Object getValue(Object obj, Date d)
    {
        Object ret=null;
        for(AST f:block){
            ret=f.getValue(obj, d);
        }
        return ret;
    }

    public String toString(){
        StringBuffer sb=new StringBuffer();
        sb.append("{\n");
        for(AST f:block){
            sb.append(f.toString()).append(";\n");
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public Object execute(ASTContext context)
    {

        Object ret=null;
        for (AST a:block){
            log.info("Execute:"+a);
            ret=a.execute(context);
        }
        return ret;

    }


}
