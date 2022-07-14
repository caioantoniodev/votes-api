package edu.mentorship.votes.infra.exception;

public class IllegalStateStaveException extends IllegalStateException {

    public IllegalStateStaveException(String old, String next) {

        super(String.format("State not pass from %s to %s state", old, next));
    }
}
