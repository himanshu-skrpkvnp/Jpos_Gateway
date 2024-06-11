package org.jpos.tutorial;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.MUX;
import org.jpos.transaction.Context;
import org.jpos.transaction.ContextConstants;
import org.jpos.transaction.TransactionParticipant;
import org.jpos.util.LogEvent;
import org.jpos.util.NameRegistrar;

import java.io.IOException;
import java.io.Serializable;

public class selectDestination2 implements TransactionParticipant  {

    @Override
    public int prepare(long l, Serializable serializable) {
        Context context = (Context) serializable ;
        MUX mux = null;
//        LogEvent evt = new LogEvent (String.valueOf(logger), "MY_Event" );
        ISOMsg isoMsg = context.get(ContextConstants.REQUEST.toString()) ;
        try {
            mux = (MUX) NameRegistrar.get ("mux.jPOS-AUTORESPONDER");
        } catch (NameRegistrar.NotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
//            isoMsg.setResponseMTI();
            isoMsg.set(  49 , "00");
            isoMsg.set( 89 , "234" );
            ISOMsg request =  mux.request( isoMsg , 3000 ) ;
        } catch (ISOException e) {
            e.printStackTrace();
        }

        return 0 ;
    }


}
