package edu.mentorship.votes.core.shared;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public final class StaveIdentify {

    private final String value;

    public StaveIdentify() {
        var now = Instant.now();
        value = UUID.nameUUIDFromBytes(now.toString().getBytes()).toString();
    }
}
