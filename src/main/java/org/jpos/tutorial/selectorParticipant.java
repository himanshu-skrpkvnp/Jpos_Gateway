package org.jpos.tutorial;

import org.jpos.core.Configurable;
import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.GroupSelector;

import java.io.Serializable;

import static org.jpos.transaction.ContextConstants.*;

public class selectorParticipant implements GroupSelector , Configurable {

    Configuration cfg ;

    @Override
    public  String select(long l , Serializable serializable){
        Context ctx = (Context)serializable;
        ISOMsg resIsoMsg = (ISOMsg)ctx.get(REQUEST);
        String selector = "";
        try {
            selector = cfg.get(resIsoMsg.getMTI());
        } catch (ISOException e) {
            e.printStackTrace();
        }
        return selector;
    }


    @Override
    public int prepare(long l, Serializable serializable) {
        return 0;
    }

    @Override
    public void setConfiguration(Configuration configuration) throws ConfigurationException {
        this.cfg = configuration ;
    }
}
