/*
 *  Copyright &copy; Indra 2016
 */
package com.jam69.simplescript.ast;

import java.util.Date;

/**
 *
 * @author jamartinm
 */
public class ASTVariable implements AST
{
    private final String varName;

    public ASTVariable(String varName){
        this.varName=varName;
    }

    @Override
    public Object getValue(Object obj, Date d)
    {
        //return ctx.get(varName);
        return 33.; // TODO
     }

    @Override
    public Object execute(ASTContext context)
    {
        return context.get(varName);
    }

    public String toString(){
        return "$"+varName;
    }

}
