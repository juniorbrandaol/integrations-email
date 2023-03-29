package com.eblj.email_integration.services.impl;

import com.eblj.email_integration.dto.EmailDTO;

public interface EmailServiceImpl {

    void sendEmail(EmailDTO dto);
}
