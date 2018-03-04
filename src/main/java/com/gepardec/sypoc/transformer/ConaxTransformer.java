package com.gepardec.sypoc.transformer;


import com.gepardec.sypoc.constants.ServiceDefinitions;
import com.gepardec.sypoc.wsdl.conax.xml.messagerequest.Message;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConaxTransformer {
    /**
     * Transforms incoming message to a format that can be read by conax
     *
     * @param messageType
     * @return
     */

    private static SimpleDateFormat sdfFrom = new SimpleDateFormat(
            "dd.MM.yyyy HH:mm:ss");
    private static SimpleDateFormat sdfTo = new SimpleDateFormat("yyyyMMddHHmm");

    public static String request10ToConax(
            com.gepardec.sypoc.xml.fis.conax.xml.messagerequest.Message requestMessage) {
        if (requestMessage == null || requestMessage.getService() == null
                || requestMessage.getService().getAction() == null
                || requestMessage.getService().getAction().getData() == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(requestMessage.getService().getAction().getData()
                                     .getCountry());
        newline(builder);

        builder.append(requestMessage.getService().getAction().getData()
                                     .getOrderNo());
        newline(builder);

        builder.append(String.format("%08d", requestMessage.getService()
                                                           .getAction().getData().getProductId()));
        newline(builder);

        try {
            Date subStart = sdfFrom.parse(requestMessage.getService().getAction()
                                                        .getData().getSubscriptionStart());

            builder.append(sdfTo.format(subStart));
            newline(builder);
        } catch (ParseException e) {
            newline(builder);
            newline(builder);
        }

        try {
            Date subEnd = sdfFrom.parse(requestMessage.getService().getAction()
                                                      .getData().getSubscriptionEnd());

            builder.append(sdfTo.format(subEnd));
            newline(builder);
        } catch (ParseException e) {
            newline(builder);
            newline(builder);
        }

        builder.append("U");
        newline(builder);

        builder.append(requestMessage.getService().getAction().getData()
                                     .getPriority());
        newline(builder);

        builder.append("U");
        newline(builder);

        builder.append(requestMessage.getService().getAction().getData()
                                     .getNofsmartcards());
        newline(builder);

        builder.append(requestMessage.getService().getAction().getData()
                                     .getSmartcardList());
        newline(builder);

        builder.append("ZZZ");
        newline(builder);

        return builder.toString();
    }

    private static void newline(StringBuilder builder) {
        builder.append(System.getProperty("line.separator"));
    }

    public static Message conaxToResponse10(
            String conaxString) {
        Message responseMessage = new Message();
        responseMessage.setService(new Message.Service());
        responseMessage.getService()
                       .setAction(new Message.Service.Action());
        responseMessage.getService()
                       .getAction()
                       .setData(new Message.Service.Action.Data());

        String[] strings = conaxString.split("\\r?\\n");
        if (strings.length < 11) {
            System.err
                    .println("Error while parsing conax result file. Wrong format: number of strings is "
                                     + strings.length
                                     + " in "
                                     + conaxString
                                     + " but expected 11");
            return null;
        }

        try {
            responseMessage.setName("feedback");
            responseMessage.getService().setName(ServiceDefinitions.MessageType.TYPE_DIGITV);
            responseMessage.getService().getAction().setName("activate");
            responseMessage.getService().getAction().getData().setOrderList(strings[1]);
            responseMessage.getService().getAction().getData().setStatus("OK");
            responseMessage.getService().getAction().getData().setErrorTimestamp("OK");
            responseMessage.getService().getAction().getData().setErrorDesc("Success");
            responseMessage.getService().getAction().getData().setCountry(strings[0]);
            responseMessage.getService().getAction().getData()
                           .setNofsmartcards(Byte.parseByte(strings[8]));
            responseMessage.getService().getAction().getData().setPriority(strings[6]);
            responseMessage.getService().getAction().getData().setProductId(strings[3]);
            responseMessage.getService().getAction().getData()
                           .setSubscriptionStart(Long.parseLong(strings[3]));
            responseMessage.getService().getAction().getData()
                           .setSubscriptionEnd(Long.parseLong(strings[4]));
            responseMessage.getService().getAction().getData().setSmartcard(strings[9]);
        } catch (Exception e) {
            System.err.println("Exception while parsing conax result file");
            e.printStackTrace();
            return null;
        }

        return responseMessage;
    }
}
