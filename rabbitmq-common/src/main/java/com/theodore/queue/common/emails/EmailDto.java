package com.theodore.queue.common.emails;

import java.io.Serializable;
import java.util.List;

public record EmailDto(List<String> to, String subject, String body) implements Serializable {
}
