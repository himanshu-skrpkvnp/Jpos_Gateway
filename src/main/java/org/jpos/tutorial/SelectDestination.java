package org.jpos.tutorial;

import org.jpos.core.*;
import org.jpos.util.* ;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.ContextConstants;
import org.jpos.transaction.TransactionParticipant;

import java.io.Serializable ;
import java.util.logging.Logger;

public class SelectDestination implements TransactionParticipant, Configurable {
    Configuration cfg;
    org.jpos.util.Logger logger = new org.jpos.util.Logger() ;
    @Override
    public int prepare(long id, Serializable context) {
        Context ctx = (Context) context;
        LogEvent evt  = new LogEvent( "___________in the  custom participant--------" ) ;
        logger.log(evt);
        ISOMsg m = (ISOMsg) ctx.get(ContextConstants.REQUEST.toString());
        if (m != null && (m.hasField(2) || m.hasField(35))) {
            try {
                Card card = Card.builder().isomsg(m).build();
                String s = cfg.get("bin." + card.getBin(), null);
                s = "myMux" ;

                ctx.put(ContextConstants.DESTINATION.toString(), s);



            } catch (InvalidCardException ignore) {

            }
        }
        return PREPARED | NO_JOIN | READONLY;
    }
    public void setConfiguration (Configuration cfg) {
        this.cfg = cfg;
    }
}
