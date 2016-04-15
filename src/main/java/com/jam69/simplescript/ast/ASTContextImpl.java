/*
 *  Copyright &copy; Indra 2016
 */
package com.jam69.simplescript.ast;

import com.jam69.simplescript.ExecutionException;
import com.jam69.simplescript.IntrospectorHelper;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jamartinm
 */
public class ASTContextImpl extends HashMap implements ASTContext
{
private final static Logger log = LoggerFactory.getLogger(ASTContextImpl.class);

private IntrospectorHelper helper;

    public Object getValue(String name){

        int p;
        if( ( p=name.indexOf('.'))!=-1){
            String objName=name.substring(0,p);
            String propName=name.substring(p);
            Object target=super.get(objName);
            if(target==null){
                throw new ExecutionException("Object with name '"+objName+"' not found");
            }
            Object ret=helper.getProperty(target,propName);
            return ret;
        }
        else
        {
            return super.get(name);

        }

    }

     public Object callProcedure(String procName ,Object... args){
         // TODO

        // Split procName by '.'
         // Method m=findMethod(target.class,argsClass
         // m.invoke(target,args);
         return null;
    }


    public void dump(){
         log.info("beginDump");
        for (Object key:keySet()){
            log.info("-->"+key+"="+get(key));
        }
           log.info("endDump");
    }
}
