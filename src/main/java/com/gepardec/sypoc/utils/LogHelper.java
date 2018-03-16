package com.gepardec.sypoc.utils;

/**
 * @author Thomas Herzog <herzog.thomas81@gmail.com>
 * @since 03/03/18
 */
public class LogHelper {

    public static final String TEMPLATE_RECEVIED_MESSAGE_ON_ENDPOINT = "Received message on endpoint '%s'";
    public static final String TEMPLATE_DEFAULT_CASE_REACHED = "Message could not be handled at all. Endpoint: '%s' | Message: '%s'";
    public static final String PROCESSOR_STARTING = "Processing message ....";
    public static final String PROCESSOR_FINISHED = "Processed message";
    public static final String ROUTE_BUILDER_STARTING = "Building routes ....";
    public static final String ROUTE_BUILDER_FINISHED = "Built routes";
    public static final String CALLING_WEBSERVICE_STARTING = "Calling webservice ....";
    public static final String CALLING_WEBSERVICE_FINISHED = "Called webservice ";
    public static final String CALLING_RESTSERVICE_STARTING = "Calling rest service ....";
    public static final String CALLING_RESTSERVICE_FINISHED = "Called rest service";

    private LogHelper() {
    }

    public static String buildReceivedMessageOnEndpoint(final String endpoint) {
        return String.format(TEMPLATE_RECEVIED_MESSAGE_ON_ENDPOINT, endpoint);
    }

    public static String buildDefaultCaeReached(final String endpoint){
        return String.format(TEMPLATE_DEFAULT_CASE_REACHED, endpoint, "${body}");
    }
}
