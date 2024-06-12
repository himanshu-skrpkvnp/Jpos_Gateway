package org.jpos.tutorial;

import org.jpos.core.Configurable;
import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.GroupSelector;
import org.jpos.util.Log;
import org.jpos.util.LogEvent;
import org.jpos.util.Logger;

import java.io.Serializable;

import static org.jpos.transaction.ContextConstants.*;

public class selectorParticipant implements GroupSelector , Configurable {

    Configuration cfg ;
    Logger logger = new Logger();
    @Override
    public  String select(long l , Serializable serializable){
        Context ctx = (Context)serializable;
        ISOMsg resIsoMsg = (ISOMsg)ctx.get(REQUEST);
        String selector = "" ;
        try {
            selector = cfg.get( resIsoMsg.getMTI() ,  null );
            LogEvent evt = new LogEvent (String.valueOf(logger), "MY_Event__"+selector  );

        }
        catch (ISOException e) {
            e.printStackTrace();
            LogEvent evt = new LogEvent (String.valueOf(logger), "MY_Event__"+selector  );

        }
        return selector;
    }


    @Override
    public int prepare(long l, Serializable serializable) {
        return PREPARED | NO_JOIN | READONLY ;
    }

    @Override
    public void setConfiguration(Configuration configuration) throws ConfigurationException {
        this.cfg = configuration ;
    }
}
