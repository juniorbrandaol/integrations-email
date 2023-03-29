package com.eblj.email_integration.services;

import com.eblj.email_integration.dto.EmailDTO;
import com.eblj.email_integration.services.exceptions.EmailException;
import com.eblj.email_integration.services.impl.EmailServiceImpl;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sendgrid.SendGrid;

import java.io.IOException;

@Service
public class EmailService implements EmailServiceImpl {
    @Autowired
    private SendGrid sendGrid;

    private static Logger LOG = LoggerFactory.getLogger(EmailService.class);
    @Override
    public void sendEmail(EmailDTO dto) {

      Email from = new Email(dto.getFromEmail(),dto.getFromName());
      Email to = new Email(dto.getTo());
      Content content = new Content(dto.getContentType(),dto.getBody());
      Mail mail = new Mail(from,dto.getSubject(),to,content);

      Request request = new Request();

      try{
         request.setMethod(Method.POST);
         request.setEndpoint("mail/send");
         request.setBody(mail.build());
         LOG.info("Sending email to: " +dto.getTo());
         Response response = sendGrid.api(request);
         if(response.getStatusCode()>=400 && response.getStatusCode()<=500){
             LOG.error("Error sending email " + response.getBody());
             throw new EmailException(response.getBody());
         }
         LOG.info("Email sent! status= " + response.getStatusCode());
      }
      catch(IOException e){
          throw new EmailException(e.getMessage());
      }
    }
}

