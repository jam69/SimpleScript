/*
 *  Copyright &copy; Indra 2016
 */
package com.jam69.simplescript.ast;

import java.util.Date;

/**
 *
 * @author jamartinm
 */
public class ASTCte implements AST
{
    private final Object cte;
    
    public ASTCte(Object cte){
        this.cte=cte;
    }
    @Override
    public Object getValue(Object obj, Date d)
    {
        return cte;
    }

    @Override
    public Object execute(ASTContext context)
    {
        return cte;
    }

    public String toString(){
        return cte!=null? cte.toString() : "null" ;
    }

}
