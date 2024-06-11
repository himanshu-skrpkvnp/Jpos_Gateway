package org.jpos.tutorial;

import bsh.util.JConsole;
import org.jpos.iso.*;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.packager.XMLPackager;
import org.jpos.transaction.Context;
import org.jpos.util.LogEvent;
import org.jpos.util.Logger;
import org.jpos.util.Log ;
import org.jpos.util.NameRegistrar ;
import org.slf4j.LoggerFactory;

import javax.xml.transform.Source;
import java.io.Console;
import java.io.IOException;

public class Test implements ISORequestListener  {

    Logger logger = new Logger() ;


    @Override
    public boolean process(ISOSource isoSource, ISOMsg isoMsg) {

        MUX mux = null;
        LogEvent evt = new LogEvent (String.valueOf(logger), "MY_Event" );
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

            isoSource.send(isoMsg);
//
        } catch (ISOException e) {
               e.printStackTrace();
        }
        catch (IOException e ){
               e.printStackTrace();
        }

        return  true  ;
    }
}
