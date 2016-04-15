/*
 *  Copyright &copy; Indra 2016
 */
package com.jam69.simplescript.ast;

import com.jam69.simplescript.ExecutionException;
import com.jam69.simplescript.IntrospectorHelper;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jamartinm
 */
public class ASTProcedureCall implements AST
{
      private final static org.slf4j.Logger log = LoggerFactory.getLogger(ASTProcedureCall.class);

    private final String procName;
    private final ASTArgList args;

    private IntrospectorHelper helper= IntrospectorHelper.getInstance();
    // Object target=ctx.get(procName);
    // Class[] argClassex= args.executeArgs
    // Method m=find_method(targe.getClass,argClassex);
    // return m.invoke(target,args);

    public ASTProcedureCall(String procName,ASTArgList args){
        this.procName=procName;
        this.args=args;
    }

    @Override
    public Object getValue(Object obj, Date d)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object execute(ASTContext context)
    {
       Object obj= args.execute(context);
//       if(procName.equalsIgnoreCase("SUM")){
//           List<Object> list=(List<Object>)obj;
//           Double sum=0.;
//           for(Object o1:list){
//               Double d=(Double)o1;
//               sum += d;
//           }
//           return sum;
//       }
       


// TODO       return context.callProcedure(procName,args.getArguments());
       return null;
    }

    public String toString(){
        return procName+"("+args+")";
    }

   
}
