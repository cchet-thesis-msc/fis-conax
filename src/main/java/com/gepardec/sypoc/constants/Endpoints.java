package com.gepardec.sypoc.constants;

/**
 * This class holds the string representation of the camel endpoints used by this application.
 *
 * @author Thomas Herzog <herzog.thomas81@gmail.com>
 * @since 3/3/18
 */
public class Endpoints {

    public static final String INCOMING_MESSAGE = "IncomingMessage";
    public static final String CONEX_RESULT = "ConaxResult";
    public static final String DIRECT_CONEX_RESULT = String.format("direct:%s", CONEX_RESULT);
    public static final String TV_OUTGOING = "TvOutgoing";
    public static final String DIRECT_TV_OUTGOING = String.format("direct:%s", TV_OUTGOING);
}
