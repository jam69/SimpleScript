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
public class ASTAssign implements AST
{
    private final static Logger log = LoggerFactory.getLogger(ASTAssign.class);
    private final String left;
    private final AST right;


    public ASTAssign(String left,AST right){
         log.info("New Assign "+left+" to "+ right);
        this.left=left;
        this.right=right;
    }

    @Override
    public Object getValue(Object obj, Date d)
    {
        // set "left"  right.getValue(obj,d);
        Object v=right.getValue(obj,d);
        log.info("Assign "+v+" to "+ left);
        return v;
    }

    @Override
    public Object execute(ASTContext context)
    {
        Object r=right.execute(context);
        context.put(left, r);
        log.info("Assign "+r+" to "+ left);
        return r;
    }

    public String toString(){
        return left+" <- ["+right+"]";
    }

}
