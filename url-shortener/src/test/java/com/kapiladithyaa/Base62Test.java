package com.kapiladithyaa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Base62Test {

    @Test
    void encodeZeroReturnsZero() {
        assertEquals("0", Base62.encode(0));
    }

    @Test
    void encodeKnownValues() {
        assertEquals("Z", Base62.encode(61));
        assertEquals("10", Base62.encode(62));
        assertEquals("21", Base62.encode(125));
    }

    @Test
    void decodeKnownValues() {
        assertEquals(125, Base62.decode("21"));
        assertEquals(61, Base62.decode("Z"));
    }

    @Test
    void decodeReversesEncode() {
        for (long i = 0; i < 100000; i++) {
            assertEquals(i, Base62.decode(Base62.encode(i)));
        }
    }

    @Test
    void decodeRejectsInvalidCharacter() {
        assertThrows(
            IllegalArgumentException.class,
            () -> Base62.decode("ab$")
        );
    }
}