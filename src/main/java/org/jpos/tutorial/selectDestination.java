package org.jpos.tutorial;

import org.jpos.core.*;
import org.jpos.util.* ;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.ContextConstants;
import org.jpos.transaction.TransactionParticipant;

import java.io.Serializable ;

public class selectDestination implements TransactionParticipant, Configurable {
    Configuration cfg;
    org.jpos.util.Logger logger = new org.jpos.util.Logger() ;
    @Override
    public int prepare(long id, Serializable context) {
        Context ctx = (Context) context;
        LogEvent evt  = new LogEvent(  ContextConstants.DESTINATION.toString() +"  ___________in the  custom participant--------" ) ;
        logger.log(evt);
        ISOMsg m = (ISOMsg) ctx.get(ContextConstants.REQUEST.toString());
        String s = "myMux" ;

        ctx.put( ContextConstants.DESTINATION.toString() ,  s ) ;
        LogEvent  evt2  = new LogEvent( ContextConstants.DESTINATION.toString() , s+"hii there" ) ;

        return PREPARED | NO_JOIN | READONLY;
    }

    public void setConfiguration (Configuration cfg) {
        this.cfg = cfg;
    }
}
