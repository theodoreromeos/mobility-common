package com.theodore.infrastructure.common.utils;

import com.github.f4b6a3.ulid.UlidCreator;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class UlidGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        // Generates a ULID that’s lexically sortable and
        // guaranteed monotonic if called within same ms.
        return UlidCreator.getMonotonicUlid().toString();
    }

}
