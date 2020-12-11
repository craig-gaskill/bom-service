package com.cagst.bom.spring.webflux.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * A {@link ResponseStatusException} that indicates that the resource could not be found.  Should be used
 * in workflows where it is not acceptable for the resource to not be found, like when trying to
 * find a resource by its unique identifier.
 *
 * @author Craig Gaskill
 */
public class NotFoundResourceException extends ResponseStatusException {
    /**
     * Constructs a new resource exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public NotFoundResourceException() {
        super(HttpStatus.NOT_FOUND);
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
    public NotFoundResourceException(String message) {
        super(HttpStatus.NOT_FOUND, message);
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
    public NotFoundResourceException(String message, Throwable cause) {
        super(HttpStatus.NOT_FOUND, message, cause);
    }
}
