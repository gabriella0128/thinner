package com.nns.thinner.service;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.nns.thinner.dto.EmailDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

	private final JavaMailSender javaMailSender;

	public EmailDto.Response sendEmail(EmailDto.Info info) {
		try {
			javaMailSender.send(simpleMailMessage(info));
		} catch (MailException e) {
			log.warn("mail send fail");
			log.warn(e.getMessage());
			return EmailDto.Response.builder().result(false).reason("MAIL EXCEPTION").build();

		}

		return EmailDto.Response.builder().result(true).reason("SUCCESS").build();
	}

	private SimpleMailMessage simpleMailMessage(EmailDto.Info emailInfo) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

		simpleMailMessage.setFrom(emailInfo.getFromAddress());
		simpleMailMessage.setTo(emailInfo.getToAddress());
		simpleMailMessage.setSubject(emailInfo.getSubject());
		simpleMailMessage.setText(emailInfo.getMessage());

		return simpleMailMessage;
	}

}
