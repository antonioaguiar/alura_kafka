package br.com.alura.ecommerce;

import java.io.Serializable;

public class Email implements Serializable {
    private String subject, body;

    public Email(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    @Override
    public String toString() {
        return "Email{" +
                "subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
