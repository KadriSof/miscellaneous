package chapter01.pojo;

import java.util.Objects;

// A good example of what most POJOs look like.
public class Message {
    String text;

    public Message(String text) {
        setText(text);
    }

    public String getText() {
        return text;
    }

    private void setText(String text) {
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Message)) return false;
        Message message = (Message) obj;
        return Objects.equals(getText(), message.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getText());
    }

    @Override
    public String toString() {
        return String.format("Message{text='%s'}", getText());
    }
}
