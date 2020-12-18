package dtos;


public class EmailDTO {
    private String clienteCode;
    private String subject;
    private String message;

    public EmailDTO() {
    }

    public EmailDTO(String clienteCode, String subject, String message) {
        this.clienteCode = clienteCode;
        this.subject = subject;
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getClienteCode() {
        return clienteCode;
    }

    public void setClienteCode(String clienteCode) {
        this.clienteCode = clienteCode;
    }
}

