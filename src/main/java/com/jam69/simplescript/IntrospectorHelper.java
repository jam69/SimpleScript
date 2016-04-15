/*
 *  Copyright &copy; Indra 2016
 */
package com.jam69.simplescript;

import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jamartinm
 */
public class IntrospectorHelper
{
      private final static Logger log = LoggerFactory.getLogger(Parser.class);

    private static IntrospectorHelper instance;

    public static final IntrospectorHelper getInstance(){
        if(instance==null){
            instance=new IntrospectorHelper();
        }
        return instance;
    }

    private IntrospectorHelper(){

    }

    public Object getProperty(Object target,String propertyName){
        // TODO

        return "Prop";
    }


     public Method findMethod(Class targetClass,String name, Class[] argsTypes){
        try
        {
            Method m=targetClass.getMethod(name, argsTypes);
            return m;

        } catch (NoSuchMethodException | SecurityException ex)
        {
            log.error("No encuentro el metodo "+name+" para la clase "+targetClass,ex);
            return null;
        }
    }



}
