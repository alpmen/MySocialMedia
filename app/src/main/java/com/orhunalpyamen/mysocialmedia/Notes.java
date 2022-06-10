package com.orhunalpyamen.mysocialmedia;

public class Notes {

    private String note_id,note_mail,note_text;

    public Notes(String note_id,String note_mail, String note_text) {
        this.note_id = note_id;
        this.note_text = note_text;
        this.note_mail = note_mail;
    }

    public String getNote_id() {
        return note_id;
    }

    public void setNote_id(String note_id) {
        this.note_id = note_id;
    }

    public String getNote_text() {
        return note_text;
    }

    public void setNote_text(String note_text) {
        this.note_text = note_text;
    }

    public String getNote_mail() {
        return note_mail;
    }

    public void setNote_date(String note_mail) {
        this.note_mail = note_mail;
    }
}
