/*
 *  Copyright &copy; Indra 2016
 */
package com.jam69.simplescript.ast;

import java.util.Date;


/**
 *
 * @author jamartinm
 */
public interface AST
{
    public Object execute(ASTContext context);
    public Object getValue(Object obj,Date d);
}
