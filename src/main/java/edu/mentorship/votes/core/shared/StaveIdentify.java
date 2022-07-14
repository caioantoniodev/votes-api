package edu.mentorship.votes.core.shared;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public final class StaveIdentify {

    private final String value;

    public StaveIdentify() {
        var now = Instant.now();
        value = UUID.nameUUIDFromBytes(now.toString().getBytes()).toString();
    }
}
