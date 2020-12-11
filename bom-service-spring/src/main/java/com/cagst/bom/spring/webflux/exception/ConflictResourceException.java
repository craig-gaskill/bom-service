package com.cagst.bom.spring.webflux.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * A {@link ResponseStatusException} that indicates that the resource would cause a duplicate if created
 * or inserted.
 *
 * @author Craig Gaskill
 */
public class ConflictResourceException extends ResponseStatusException {
    /**
     * Constructs a new resource exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public ConflictResourceException() {
        super(HttpStatus.CONFLICT);
    }

    /**
     * Constructs a new resource exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message
     *     the detail message. The detail message is saved for
     *     later retrieval by the {@link #getMessage()} method.
     */
    public ConflictResourceException(String message) {
        super(HttpStatus.CONFLICT, message);
    }

    /**
     * Constructs a new resource exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this resource exception's detail message.
     *
     * @param message
     *     the detail message (which is saved for later retrieval
     *     by the {@link #getMessage()} method).
     * @param cause
     *     the cause (which is saved for later retrieval by the
     *     {@link #getCause()} method).  (A {@code null} value is
     *     permitted, and indicates that the cause is nonexistent or
     *     unknown.)
     */
    public ConflictResourceException(String message, Throwable cause) {
        super(HttpStatus.CONFLICT, message, cause);
    }
}
